package com.cybertrend.cpot.service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.cybertrend.cpot.util.ReadConfig;
import com.google.common.io.Files;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.XMLConstants;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.MultiPartMediaTypes;
import com.sun.jersey.multipart.file.FileDataBodyPart;

import tableau.api.rest.bindings.CapabilityType;
import tableau.api.rest.bindings.FileUploadType;
import tableau.api.rest.bindings.GranteeCapabilitiesType;
import tableau.api.rest.bindings.ObjectFactory;
import tableau.api.rest.bindings.PermissionsType;
import tableau.api.rest.bindings.ProjectListType;
import tableau.api.rest.bindings.ProjectType;
import tableau.api.rest.bindings.SiteListType;
import tableau.api.rest.bindings.SiteRoleType;
import tableau.api.rest.bindings.SiteType;
import tableau.api.rest.bindings.TableauCredentialsType;
import tableau.api.rest.bindings.TsRequest;
import tableau.api.rest.bindings.TsResponse;
import tableau.api.rest.bindings.UserType;
import tableau.api.rest.bindings.ViewListType;
import tableau.api.rest.bindings.WorkbookType;

public class TableauService {

    private final static String TABLEAU_PAYLOAD_NAME = "request_payload";

	/*
	 * Initialize Tableau REST API
	 * 
	 */
    private static Unmarshaller s_jaxbUnmarshaller;
    private static Marshaller s_jaxbMarshaller;
    private final static String TABLEAU_AUTH_HEADER = "X-Tableau-Auth";
    private static TableauService INSTANCE;
    private enum Operation {
        SIGN_IN(getApiUriBuilder().path("auth/signin")),
        QUERY_WORKBOOKS(getApiUriBuilder().path("sites/{siteId}/users/{userId}/workbooks")),
        QUERY_VIEWS(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/views")),
        GET_USER(getApiUriBuilder().path("sites/{siteId}/users/{userId}")),
        GET_WORKBOOK(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}")),
        GET_SITE(getApiUriBuilder().path("sites/{siteId}")),
        QUERY_PROJECTS(getApiUriBuilder().path("sites/{siteId}/projects")),
        QUERY_SITES(getApiUriBuilder().path("sites")),
        QUERY_USERS_ON_SITE(getApiUriBuilder().path("sites/{siteId}/users")),
        UPDATE_USER(getApiUriBuilder().path("sites/{siteId}/users/{userId}")),
        ADD_WORKBOOK_PERMISSIONS(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/permissions")),
        DELETE_WORKBOOK_PERMISSIONS(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/permissions/users/{userId}/{capabilityName}/{capabilityMode}")),
        GET_WORKBOOK_PERMISSIONS(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/permissions")),
    	QUERY_WORKBOOK_PREVIEW_IMAGE(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/previewImage")),
        SIGN_OUT(getApiUriBuilder().path("auth/signout")),
        APPEND_FILE_UPLOAD(getApiUriBuilder().path("sites/{siteId}/fileUploads/{uploadSessionId}")),
        INITIATE_FILE_UPLOAD(getApiUriBuilder().path("sites/{siteId}/fileUploads")),
    	PUBLISH_WORKBOOK(getApiUriBuilder().path("sites/{siteId}/workbooks"));
    	
        private final UriBuilder m_builder;

        Operation(UriBuilder builder) {
            m_builder = builder;
        }

        UriBuilder getUriBuilder() {
            return m_builder;
        }

        String getUrl(Object... values) {
            return m_builder.build(values).toString();
        }
    	
    }
    
    public static TableauService instance(HttpServletRequest request){
    	if (INSTANCE == null) {
            INSTANCE = new TableauService();
            initialize(request);
        }
        return INSTANCE;
    }
    
    public static void initialize(HttpServletRequest request) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TsRequest.class, TsResponse.class);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(request.getServletContext().getResource(ReadConfig.get("tableau.server.schema.url")));
            s_jaxbMarshaller = jaxbContext.createMarshaller();
            s_jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            s_jaxbUnmarshaller.setSchema(schema);
            s_jaxbMarshaller.setSchema(schema);
        } catch (JAXBException | SAXException | MalformedURLException ex) {
            throw new IllegalStateException("Failed to initialize the REST API");
        }
    }
    
    private static UriBuilder getApiUriBuilder() {
        return UriBuilder.fromPath(ReadConfig.get("tableau.server.host") + "/api/"+ReadConfig.get("tableau.api.version"));
    }
    
    public static UriBuilder getApiUriBuilder(String path){
    	return UriBuilder.fromPath(path);
    }

    private static ObjectFactory m_objectFactory = new ObjectFactory();
	
	public static TsResponse invokeSignIn(String username, String password, String contentUrl) {
		String url = Operation.SIGN_IN.getUrl();
		TsRequest payload = createPayloadForSignin(username, password, contentUrl);
        TsResponse response = post(url, null, payload);
        return response;
    }
	
	public static void invokeSignOut(TableauCredentialsType credential) {
        String url = Operation.SIGN_OUT.getUrl();
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).post(
                ClientResponse.class);
    }
	
	public static TsResponse invokeQueryWorkbooks(TableauCredentialsType credential, int pageSize, int pageNumber ) {
        String url = Operation.QUERY_WORKBOOKS.getUrl(credential.getSite().getId(), credential.getUser().getId());
        TsResponse response = get(url, credential.getToken(), pageSize, pageNumber);
        return response;
    }
	
	private static TsRequest createPayloadForSignin(String username, String password, String contentUrl) {
        TsRequest requestPayload = m_objectFactory.createTsRequest();
        TableauCredentialsType signInCredentials = m_objectFactory.createTableauCredentialsType();
        SiteType site = m_objectFactory.createSiteType();
        site.setContentUrl(contentUrl);
        signInCredentials.setSite(site);

        signInCredentials.setName(username);
        signInCredentials.setPassword(password);
        requestPayload.setCredentials(signInCredentials);

        return requestPayload;
    }

    private static TsResponse post(String url, String authToken, TsRequest requestPayload) {
        StringWriter writer = new StringWriter();

        if (requestPayload != null) {
            try {
                s_jaxbMarshaller.marshal(requestPayload, writer);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        }

        String payload = writer.toString();
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken)
                .type(MediaType.TEXT_XML_TYPE).post(ClientResponse.class, payload);

        String responseXML = clientResponse.getEntity(String.class);
        return unmarshalResponse(responseXML);
    }
    
    private static TsResponse post(String url, String authToken) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken).post(ClientResponse.class);
        String responseXML = clientResponse.getEntity(String.class);
        return unmarshalResponse(responseXML);
    }
    
    private static TsResponse unmarshalResponse(String responseXML) {
        TsResponse tsResponse = m_objectFactory.createTsResponse();
        try {
            StringReader reader = new StringReader(responseXML);
            tsResponse = s_jaxbUnmarshaller.unmarshal(new StreamSource(reader), TsResponse.class).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return tsResponse;
    }
    
    public static TsResponse get(String url, String authToken, int pageSize, int pageNumber) {
        Client client = Client.create();
        WebResource webResource = client.resource(url).queryParam("pageSize", pageSize+"");
        webResource.queryParam("pageNumber", pageNumber+"");

        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken).get(ClientResponse.class);

        String responseXML = clientResponse.getEntity(String.class);
        return unmarshalResponse(responseXML);
    }
    
    public static TsResponse get(String url, String authToken) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken).get(ClientResponse.class);

        String responseXML = clientResponse.getEntity(String.class);
        return unmarshalResponse(responseXML);
    }
    
    public static void delete(String url, String authToken) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        webResource.header(TABLEAU_AUTH_HEADER, authToken).delete();
    }
    
    public static ViewListType invokeQueryViews(TableauCredentialsType credential, String workbookId, int pageSize, int pageNumber) {

        String url = Operation.QUERY_VIEWS.getUrl(credential.getSite().getId(), workbookId);
        TsResponse response = get(url, credential.getToken(),pageSize,pageNumber);

        if (response.getViews() != null) {
            return response.getViews();
        }
        return null;
    }
    
    public static TsResponse invokeQueryUsersOnSite(TableauCredentialsType credential, int pageSize, int pageNumber) {
        String url = Operation.QUERY_USERS_ON_SITE.getUrl(credential.getSite().getId());
        TsResponse response = get(url, credential.getToken(), pageSize, pageNumber);
        return response;
    }
    
    public static TsResponse invokeUpdateUser(TableauCredentialsType credential, UserType userType, String fullName, String email, String password, SiteRoleType siteRole) {
    	String url = Operation.UPDATE_USER.getUrl(credential.getSite().getId(), userType.getId());
        TsRequest payload = createPayloadForUpdateUser(fullName, email, password, siteRole);
        TsResponse response = put(url, credential.getToken(), payload);
        System.out.println("Response : "+response.getUser().getName());
        return response;
    }
    
    private static TsRequest createPayloadForUpdateUser(String fullName, String email, String password, SiteRoleType siteRole) {
        TsRequest requestPayload = m_objectFactory.createTsRequest();
        UserType userType = m_objectFactory.createUserType();
        userType.setFullName(fullName);
        userType.setEmail(email);
        userType.setPassword(password);
        userType.setSiteRole(siteRole);
        requestPayload.setUser(userType);
        return requestPayload;
    }
    
    public static TsResponse invokeAddPermissionsToWorkbook(TableauCredentialsType credential,
            String workbookId, List<GranteeCapabilitiesType> granteeCapabilities) {
        String url = Operation.ADD_WORKBOOK_PERMISSIONS.getUrl(credential.getSite().getId(), workbookId);

        TsRequest payload = createPayloadForAddingWorkbookPermissions(workbookId, granteeCapabilities);

        TsResponse response = put(url, credential.getToken(), payload);
        return response;
    }
    
    public static void invokeDeletePermissionsToWorkbook(TableauCredentialsType credential, String workbookId, String userId, String capabilityName, String capabilityMode){
    	String url = Operation.DELETE_WORKBOOK_PERMISSIONS.getUrl(credential.getSite().getId(), workbookId, userId, capabilityName, capabilityMode);
    	delete(url, credential.getToken());
    }
    
    private static TsRequest createPayloadForAddingWorkbookPermissions(String workbookId,
            List<GranteeCapabilitiesType> granteeCapabilities) {
        TsRequest requestPayload = m_objectFactory.createTsRequest();

        PermissionsType permissions = m_objectFactory.createPermissionsType();

        WorkbookType workbook = m_objectFactory.createWorkbookType();
        workbook.setId(workbookId);

        permissions.setWorkbook(workbook);
        permissions.getGranteeCapabilities().addAll(granteeCapabilities);

        requestPayload.setPermissions(permissions);

        return requestPayload;
    }
    
    private static TsResponse put(String url, String authToken, TsRequest requestPayload) {
	    StringWriter writer = new StringWriter();
        if (requestPayload != null) {
            try {
                s_jaxbMarshaller.marshal(requestPayload, writer);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        }
        String payload = writer.toString();
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken)
                .type(MediaType.TEXT_XML_TYPE).put(ClientResponse.class, payload);
        String responseXML = clientResponse.getEntity(String.class);
        return unmarshalResponse(responseXML);
    }
    
    public static UserType invokeGetUser(TableauCredentialsType credential, String userId){
    	String url = Operation.GET_USER.getUrl(credential.getSite().getId(), userId);
        TsResponse response = get(url, credential.getToken());
        if (response.getUser() != null) {
            return response.getUser();
        }
        return null;
    }
    
    public static WorkbookType invokeGetWorkbook(TableauCredentialsType credential, String workbookId ){
    	String url = Operation.GET_WORKBOOK.getUrl(credential.getSite().getId(), workbookId);
    	TsResponse response = get(url, credential.getToken());
    	if(response.getWorkbook() != null) {
    		return response.getWorkbook();
    	}
    	return null;
    }
    
    public static SiteType invokeGetSite(TableauCredentialsType credential, String siteId){
    	String url = Operation.GET_WORKBOOK.getUrl(siteId);
    	TsResponse response = get(url, credential.getToken());
    	if(response.getSite() != null) {
    		return response.getSite();
    	}
    	return null;
    }
    
    public static PermissionsType invokeGetWorkbookPermission(TableauCredentialsType credentials, String workbookId) {
    	String url = Operation.GET_WORKBOOK_PERMISSIONS.getUrl(credentials.getSite().getId(), workbookId);
    	TsResponse response = get(url, credentials.getToken());
    	if (response.getPermissions() != null) {
    		return response.getPermissions();
    	} return null;
    }
    
    /*
     * User Capabilities 
     * 
     */
    public static GranteeCapabilitiesType createUserGranteeCapability(UserType user, Map<String, String> capabilitiesMap) {
        GranteeCapabilitiesType granteeCapabilities = m_objectFactory.createGranteeCapabilitiesType();

        // Sets the grantee to the specified user
        granteeCapabilities.setUser(user);
        GranteeCapabilitiesType.Capabilities capabilities = m_objectFactory.createGranteeCapabilitiesTypeCapabilities();

        // Iterates over the list of capabilities and creates a capability element
        for (String key : capabilitiesMap.keySet()) {
            CapabilityType capabilityType = m_objectFactory.createCapabilityType();

            // Sets the capability name and permission mode
            capabilityType.setName(key);
            capabilityType.setMode(capabilitiesMap.get(key));

            // Adds the capability to the list of capabilities for the grantee
            capabilities.getCapability().add(capabilityType);
        }

        // Sets the list of capabilities for the grantee element
        granteeCapabilities.setCapabilities(capabilities);

        return granteeCapabilities;
    }
    
    /*
     * Query WorkbookImage
     */
    public static String invokeQueryWorkbookImage(TableauCredentialsType credential, String workbookId) throws IOException {
    	String url = Operation.QUERY_WORKBOOK_PREVIEW_IMAGE.getUrl(credential.getSite().getId(), workbookId);
    	Client client = Client.create();
        WebResource webResource = client.resource(url);

        // Sets the header and makes a GET request
        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
        File res= clientResponse.getEntity(File.class);
        BufferedImage bimg = ImageIO.read(res);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( bimg, "png", baos );
		baos.flush();
		byte[] imageInByteArray = baos.toByteArray();
		baos.close();
		return "data:image/png;base64, "+DatatypeConverter.printBase64Binary(imageInByteArray);
    }
    
    /*
     * Get Trusted Ticket
     */
    public static String getTrustedTicket(String wgserver, String user, String remoteAddr, String targetSite) {

		String output = null;

		OutputStreamWriter out = null;
		BufferedReader in = null;
		try {
			// Encode the parameters
			StringBuffer data = new StringBuffer();
			data.append(URLEncoder.encode("username", "UTF-8"));
			data.append("=");
			data.append(URLEncoder.encode(user, "UTF-8"));
			data.append("&");
			data.append(URLEncoder.encode("client_ip", "UTF-8"));
			data.append("=");
			data.append(URLEncoder.encode(remoteAddr, "UTF-8"));
			data.append("&");
			data.append(URLEncoder.encode("target_site", "UTF-8"));
			data.append("=");
			data.append(URLEncoder.encode(targetSite, "UTF-8"));

			// Send the request
			URL url = new URL(wgserver + "/trusted");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			out = new OutputStreamWriter(conn.getOutputStream());
			out.write(data.toString());
			out.flush();

			// Read the response
			StringBuffer rsp = new StringBuffer();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			while ((line = in.readLine()) != null) {
				rsp.append(line);
			}

			output = rsp.toString();

		} catch (Exception e) {

			e.printStackTrace();

		}

		finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {

				e.printStackTrace();

			}
		}

		return output;

	}
    
    /*
     * Generate Dashboard URL 
     */
    public static String getDashboardURL(String clientRemoteAddress,
			String user, String siteRoot, String destinationView, String targetSite) {
		String output = null;
		String wgserver = ReadConfig.get("tableau.server.host");
		String ticket = getTrustedTicket(wgserver, user, clientRemoteAddress, targetSite);

		if (!ticket.equals("-1")) {
			output = wgserver + "/trusted/" + ticket + siteRoot + "/views/"
					+ destinationView;
		}
		else {
			output = wgserver+ siteRoot + "/views/"
					+ destinationView;
//			output = "unable to get tableau view URL";
		}
		return output;
	}
    
    /*
     * Publish Workbook Chunked
     * 
     */
    private static WorkbookType invokePublishWorkbookChunked(TableauCredentialsType credential, String siteId,
            String projectId, String workbookName, File workbookFile) {
        FileUploadType fileUpload = invokeInitiateFileUpload(credential, siteId);
        UriBuilder builder = Operation.PUBLISH_WORKBOOK.getUriBuilder()
                .queryParam("uploadSessionId", fileUpload.getUploadSessionId())
                .queryParam("workbookType", Files.getFileExtension(workbookFile.getName()));
        String url = builder.build(siteId, fileUpload.getUploadSessionId()).toString();
        byte[] buffer = new byte[100000];
        try  {
        	FileInputStream inputStream = new FileInputStream(workbookFile.getAbsolutePath());
        	while (inputStream.read(buffer) != -1) {
                invokeAppendFileUpload(credential, siteId, fileUpload.getUploadSessionId(), buffer);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read the workbook file.");
        }
        TsRequest payload = createPayloadToPublishWorkbook(workbookName, projectId);
        TsResponse response = postMultipart(url, credential.getToken(), payload, null);
        if (response.getWorkbook() != null) {
            return response.getWorkbook();
        }
        return null;
    }
    
    /*
     * Publish Workbook Simple
     */
    private static WorkbookType invokePublishWorkbookSimple(TableauCredentialsType credential, String siteId,
            String projectId, String workbookName, File workbookFile) {
        String url = Operation.PUBLISH_WORKBOOK.getUrl(siteId);
        TsRequest payload = createPayloadToPublishWorkbook(workbookName, projectId);
        BodyPart filePart = new FileDataBodyPart("tableau_workbook", workbookFile,
                MediaType.APPLICATION_OCTET_STREAM_TYPE);
        TsResponse response = postMultipart(url, credential.getToken(), payload, filePart);
        if (response.getWorkbook() != null) {
            return response.getWorkbook();
        }
        return null;
    }
    
    /*
     * Publish Workbook
     */
    public static WorkbookType invokePublishWorkbook(TableauCredentialsType credential, String siteId, String projectId,
            String workbookName, File workbookFile, boolean chunkedPublish) {
        if (chunkedPublish) {
            return invokePublishWorkbookChunked(credential, siteId, projectId, workbookName, workbookFile);
        } else {
            return invokePublishWorkbookSimple(credential, siteId, projectId, workbookName, workbookFile);
        }
    }
    
    
    /*
     * Initiate Upload
     */
    private static FileUploadType invokeInitiateFileUpload(TableauCredentialsType credential, String siteId) {
        String url = Operation.INITIATE_FILE_UPLOAD.getUrl(siteId);
        TsResponse response = post(url, credential.getToken());
        if (response.getFileUpload() != null) {
            return response.getFileUpload();
        }
        return null;
    }
    
    /*
     * 
     */
    private static TsResponse postMultipart(String url, String authToken, TsRequest requestPayload, BodyPart filePart) {
        StringWriter writer = new StringWriter();
        if (requestPayload != null) {
            try {
                s_jaxbMarshaller.marshal(requestPayload, writer);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        }
        String payload = writer.toString();
        BodyPart payloadPart = new FormDataBodyPart(TABLEAU_PAYLOAD_NAME, payload);
        MultiPart multipart = new MultiPart();
        multipart.bodyPart(payloadPart);
        if(filePart != null) {
            multipart.bodyPart(filePart);
        }
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken)
                .type(MultiPartMediaTypes.createMixed()).post(ClientResponse.class, multipart);
        String responseXML = clientResponse.getEntity(String.class);
        return unmarshalResponse(responseXML);
    }
    
    private static void invokeAppendFileUpload(TableauCredentialsType credential, String siteId, String uploadSessionId,
            byte[] chunk) {
        String url = Operation.APPEND_FILE_UPLOAD.getUrl(siteId, uploadSessionId);
        try {
        	FileOutputStream outputStream = new FileOutputStream("appendFileUpload.temp");
            outputStream.write(chunk);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create temporary file to append to file upload");
        }
        BodyPart filePart = new FileDataBodyPart("tableau_file", new File("appendFileUpload.temp"),
                MediaType.APPLICATION_OCTET_STREAM_TYPE);
        putMultipart(url, credential.getToken(), null, filePart);
    }
    
    private static TsResponse putMultipart(String url, String authToken, TsRequest requestPayload, BodyPart filePart) {
        StringWriter writer = new StringWriter();
        if (requestPayload != null) {
            try {
                s_jaxbMarshaller.marshal(requestPayload, writer);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        }
        String payload = writer.toString();
        BodyPart payloadPart = new FormDataBodyPart(TABLEAU_PAYLOAD_NAME, payload);
        MultiPart multipart = new MultiPart();
        multipart.bodyPart(payloadPart);

        if(filePart != null) {
            multipart.bodyPart(filePart);
        }
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken)
                .type(MultiPartMediaTypes.createMixed()).put(ClientResponse.class, multipart);
        String responseXML = clientResponse.getEntity(String.class);
        return unmarshalResponse(responseXML);
    }
    
    private static TsRequest createPayloadToPublishWorkbook(String workbookName, String projectId) {
        TsRequest requestPayload = m_objectFactory.createTsRequest();
        WorkbookType workbook = m_objectFactory.createWorkbookType();
        ProjectType project = m_objectFactory.createProjectType();
        project.setId(projectId);
        workbook.setName(workbookName);
        workbook.setProject(project);
        requestPayload.setWorkbook(workbook);
        return requestPayload;
    }
    
    /*
     * Query Projects
     */
    public static ProjectListType invokeQueryProjects(TableauCredentialsType credential, String siteId) {
        String url = Operation.QUERY_PROJECTS.getUrl(siteId);
        TsResponse response = get(url, credential.getToken());
        if (response.getProjects() != null) {
            return response.getProjects();
        }
        return null;
    }
    
    /*
     * Query Sites
     */
    public static SiteListType invokeQuerySites(TableauCredentialsType credential) {
        String url = Operation.QUERY_SITES.getUrl();
        TsResponse response = get(url, credential.getToken());
        if (response.getSites() != null) {
            return response.getSites();
        }
        return null;
    }
}
