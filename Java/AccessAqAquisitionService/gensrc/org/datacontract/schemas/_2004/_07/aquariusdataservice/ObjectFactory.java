
package org.datacontract.schemas._2004._07.aquariusdataservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringanyType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.aquariusdataservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LocationDTO_QNAME = new QName("http://schemas.datacontract.org/2004/07/AquariusDataService", "LocationDTO");
    private final static QName _ArrayOfLocationDTO_QNAME = new QName("http://schemas.datacontract.org/2004/07/AquariusDataService", "ArrayOfLocationDTO");
    private final static QName _TimeSeriesDescription_QNAME = new QName("http://schemas.datacontract.org/2004/07/AquariusDataService", "TimeSeriesDescription");
    private final static QName _ArrayOfTimeSeriesDescription_QNAME = new QName("http://schemas.datacontract.org/2004/07/AquariusDataService", "ArrayOfTimeSeriesDescription");
    private final static QName _LocationDTOExtendedAttributes_QNAME = new QName("http://schemas.datacontract.org/2004/07/AquariusDataService", "ExtendedAttributes");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.aquariusdataservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LocationDTO }
     * 
     */
    public LocationDTO createLocationDTO() {
        return new LocationDTO();
    }

    /**
     * Create an instance of {@link ArrayOfTimeSeriesDescription }
     * 
     */
    public ArrayOfTimeSeriesDescription createArrayOfTimeSeriesDescription() {
        return new ArrayOfTimeSeriesDescription();
    }

    /**
     * Create an instance of {@link ArrayOfLocationDTO }
     * 
     */
    public ArrayOfLocationDTO createArrayOfLocationDTO() {
        return new ArrayOfLocationDTO();
    }

    /**
     * Create an instance of {@link TimeSeriesDescription }
     * 
     */
    public TimeSeriesDescription createTimeSeriesDescription() {
        return new TimeSeriesDescription();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocationDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AquariusDataService", name = "LocationDTO")
    public JAXBElement<LocationDTO> createLocationDTO(LocationDTO value) {
        return new JAXBElement<LocationDTO>(_LocationDTO_QNAME, LocationDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLocationDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AquariusDataService", name = "ArrayOfLocationDTO")
    public JAXBElement<ArrayOfLocationDTO> createArrayOfLocationDTO(ArrayOfLocationDTO value) {
        return new JAXBElement<ArrayOfLocationDTO>(_ArrayOfLocationDTO_QNAME, ArrayOfLocationDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeSeriesDescription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AquariusDataService", name = "TimeSeriesDescription")
    public JAXBElement<TimeSeriesDescription> createTimeSeriesDescription(TimeSeriesDescription value) {
        return new JAXBElement<TimeSeriesDescription>(_TimeSeriesDescription_QNAME, TimeSeriesDescription.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTimeSeriesDescription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AquariusDataService", name = "ArrayOfTimeSeriesDescription")
    public JAXBElement<ArrayOfTimeSeriesDescription> createArrayOfTimeSeriesDescription(ArrayOfTimeSeriesDescription value) {
        return new JAXBElement<ArrayOfTimeSeriesDescription>(_ArrayOfTimeSeriesDescription_QNAME, ArrayOfTimeSeriesDescription.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringanyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AquariusDataService", name = "ExtendedAttributes", scope = LocationDTO.class)
    public JAXBElement<ArrayOfKeyValueOfstringanyType> createLocationDTOExtendedAttributes(ArrayOfKeyValueOfstringanyType value) {
        return new JAXBElement<ArrayOfKeyValueOfstringanyType>(_LocationDTOExtendedAttributes_QNAME, ArrayOfKeyValueOfstringanyType.class, LocationDTO.class, value);
    }

}
