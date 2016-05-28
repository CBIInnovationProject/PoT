package com.cybertrend.pot.service;

import java.io.BufferedReader;
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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.cybertrend.pot.util.PropertyLooker;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import tableau.api.rest.bindings.CapabilityType;
import tableau.api.rest.bindings.GranteeCapabilitiesType;
import tableau.api.rest.bindings.ObjectFactory;
import tableau.api.rest.bindings.PermissionsType;
import tableau.api.rest.bindings.SiteRoleType;
import tableau.api.rest.bindings.SiteType;
import tableau.api.rest.bindings.TableauCredentialsType;
import tableau.api.rest.bindings.TsRequest;
import tableau.api.rest.bindings.TsResponse;
import tableau.api.rest.bindings.UserType;
import tableau.api.rest.bindings.ViewListType;
import tableau.api.rest.bindings.WorkbookType;

public class TableauService {

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
        QUERY_USERS_ON_SITE(getApiUriBuilder().path("sites/{siteId}/users")),
        UPDATE_USER(getApiUriBuilder().path("sites/{siteId}/users/{userId}")),
        ADD_WORKBOOK_PERMISSIONS(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/permissions")),
        DELETE_WORKBOOK_PERMISSIONS(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/permissions/users/{userId}/{capabilityName}/{capabilityMode}")),
        GET_WORKBOOK_PERMISSIONS(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/permissions")),
        SIGN_OUT(getApiUriBuilder().path("auth/signout"));
    	
        private final UriBuilder m_builder;

        Operation(UriBuilder builder) {
            m_builder = builder;
        }

        String getUrl(Object... values) {
            return m_builder.build(values).toString();
        }
    	
    }
    
    public static TableauService instance(){
    	if (INSTANCE == null) {
            INSTANCE = new TableauService();
            initialize();
        }
        return INSTANCE;
    }
    
    public static void initialize() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TsRequest.class, TsResponse.class);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new URL(PropertyLooker.get("tableau.server.schema.url")));
            s_jaxbMarshaller = jaxbContext.createMarshaller();
            s_jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            s_jaxbUnmarshaller.setSchema(schema);
            s_jaxbMarshaller.setSchema(schema);
        } catch (JAXBException | SAXException | MalformedURLException ex) {
            throw new IllegalStateException("Failed to initialize the REST API");
        }
    }
    
    private static UriBuilder getApiUriBuilder() {
        return UriBuilder.fromPath(PropertyLooker.get("tableau.server.host") + "/api/2.0");
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
    
    public static ViewListType invokeQueryViews(TableauCredentialsType credential, String siteId, String workbookId, int pageSize, int pageNumber) {

        String url = Operation.QUERY_VIEWS.getUrl(siteId, workbookId);
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
     * Get Trusted Ticket
     */
    public static String getTrustedTicket(String wgserver, String user, String remoteAddr) {

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

			// Send the request
			URL url = new URL("http://" + wgserver + "/trusted");
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
    
}
