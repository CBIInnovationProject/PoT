//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.01.30 at 12:49:43 PM PST
//


package tableau.api.rest.bindings;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for permissionsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="permissionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="datasource" type="{http://tableausoftware.com/api}dataSourceType"/>
 *           &lt;element name="project" type="{http://tableausoftware.com/api}projectType"/>
 *           &lt;element name="workbook" type="{http://tableausoftware.com/api}workbookType"/>
 *         &lt;/choice>
 *         &lt;element name="granteeCapabilities" type="{http://tableausoftware.com/api}granteeCapabilitiesType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "permissionsType", propOrder = {
    "workbook",
    "granteeCapabilities"
})
public class PermissionsType {

    protected WorkbookType workbook;
    protected List<GranteeCapabilitiesType> granteeCapabilities;

    /**
     * Gets the value of the workbook property.
     *
     * @return
     *     possible object is
     *     {@link WorkbookType }
     *
     */
    public WorkbookType getWorkbook() {
        return workbook;
    }

    /**
     * Sets the value of the workbook property.
     *
     * @param value
     *     allowed object is
     *     {@link WorkbookType }
     *
     */
    public void setWorkbook(WorkbookType value) {
        this.workbook = value;
    }

    /**
     * Gets the value of the granteeCapabilities property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the granteeCapabilities property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGranteeCapabilities().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GranteeCapabilitiesType }
     *
     *
     */
    public List<GranteeCapabilitiesType> getGranteeCapabilities() {
        if (granteeCapabilities == null) {
            granteeCapabilities = new ArrayList<GranteeCapabilitiesType>();
        }
        return this.granteeCapabilities;
    }

}
