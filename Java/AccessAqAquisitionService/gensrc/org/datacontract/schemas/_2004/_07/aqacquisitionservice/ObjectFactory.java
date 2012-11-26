
package org.datacontract.schemas._2004._07.aqacquisitionservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.aqacquisitionservice package. 
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

    private final static QName _AppendResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", "AppendResult");
    private final static QName _FieldVisitMeasurement_QNAME = new QName("http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", "FieldVisitMeasurement");
    private final static QName _FieldVisitResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", "FieldVisitResult");
    private final static QName _FieldVisit_QNAME = new QName("http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", "FieldVisit");
    private final static QName _ArrayOfFieldVisit_QNAME = new QName("http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", "ArrayOfFieldVisit");
    private final static QName _ArrayOfFieldVisitResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", "ArrayOfFieldVisitResult");
    private final static QName _ArrayOfFieldVisitMeasurement_QNAME = new QName("http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", "ArrayOfFieldVisitMeasurement");
    private final static QName _FieldVisitMeasurementDisplayId_QNAME = new QName("http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", "DisplayId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.aqacquisitionservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfFieldVisit }
     * 
     */
    public ArrayOfFieldVisit createArrayOfFieldVisit() {
        return new ArrayOfFieldVisit();
    }

    /**
     * Create an instance of {@link AppendResult }
     * 
     */
    public AppendResult createAppendResult() {
        return new AppendResult();
    }

    /**
     * Create an instance of {@link FieldVisit }
     * 
     */
    public FieldVisit createFieldVisit() {
        return new FieldVisit();
    }

    /**
     * Create an instance of {@link ArrayOfFieldVisitMeasurement }
     * 
     */
    public ArrayOfFieldVisitMeasurement createArrayOfFieldVisitMeasurement() {
        return new ArrayOfFieldVisitMeasurement();
    }

    /**
     * Create an instance of {@link ArrayOfFieldVisitResult }
     * 
     */
    public ArrayOfFieldVisitResult createArrayOfFieldVisitResult() {
        return new ArrayOfFieldVisitResult();
    }

    /**
     * Create an instance of {@link FieldVisitResult }
     * 
     */
    public FieldVisitResult createFieldVisitResult() {
        return new FieldVisitResult();
    }

    /**
     * Create an instance of {@link FieldVisitMeasurement }
     * 
     */
    public FieldVisitMeasurement createFieldVisitMeasurement() {
        return new FieldVisitMeasurement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppendResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", name = "AppendResult")
    public JAXBElement<AppendResult> createAppendResult(AppendResult value) {
        return new JAXBElement<AppendResult>(_AppendResult_QNAME, AppendResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldVisitMeasurement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", name = "FieldVisitMeasurement")
    public JAXBElement<FieldVisitMeasurement> createFieldVisitMeasurement(FieldVisitMeasurement value) {
        return new JAXBElement<FieldVisitMeasurement>(_FieldVisitMeasurement_QNAME, FieldVisitMeasurement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldVisitResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", name = "FieldVisitResult")
    public JAXBElement<FieldVisitResult> createFieldVisitResult(FieldVisitResult value) {
        return new JAXBElement<FieldVisitResult>(_FieldVisitResult_QNAME, FieldVisitResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldVisit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", name = "FieldVisit")
    public JAXBElement<FieldVisit> createFieldVisit(FieldVisit value) {
        return new JAXBElement<FieldVisit>(_FieldVisit_QNAME, FieldVisit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFieldVisit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", name = "ArrayOfFieldVisit")
    public JAXBElement<ArrayOfFieldVisit> createArrayOfFieldVisit(ArrayOfFieldVisit value) {
        return new JAXBElement<ArrayOfFieldVisit>(_ArrayOfFieldVisit_QNAME, ArrayOfFieldVisit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFieldVisitResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", name = "ArrayOfFieldVisitResult")
    public JAXBElement<ArrayOfFieldVisitResult> createArrayOfFieldVisitResult(ArrayOfFieldVisitResult value) {
        return new JAXBElement<ArrayOfFieldVisitResult>(_ArrayOfFieldVisitResult_QNAME, ArrayOfFieldVisitResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFieldVisitMeasurement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", name = "ArrayOfFieldVisitMeasurement")
    public JAXBElement<ArrayOfFieldVisitMeasurement> createArrayOfFieldVisitMeasurement(ArrayOfFieldVisitMeasurement value) {
        return new JAXBElement<ArrayOfFieldVisitMeasurement>(_ArrayOfFieldVisitMeasurement_QNAME, ArrayOfFieldVisitMeasurement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto", name = "DisplayId", scope = FieldVisitMeasurement.class)
    public JAXBElement<String> createFieldVisitMeasurementDisplayId(String value) {
        return new JAXBElement<String>(_FieldVisitMeasurementDisplayId_QNAME, String.class, FieldVisitMeasurement.class, value);
    }

}
