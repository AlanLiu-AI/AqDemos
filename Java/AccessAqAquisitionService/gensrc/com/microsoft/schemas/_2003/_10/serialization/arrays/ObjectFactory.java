
package com.microsoft.schemas._2003._10.serialization.arrays;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.schemas._2003._10.serialization.arrays package. 
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

    private final static QName _ArrayOfKeyValueOfstringanyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfKeyValueOfstringanyType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.schemas._2003._10.serialization.arrays
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfKeyValueOfstringanyType }
     * 
     */
    public ArrayOfKeyValueOfstringanyType createArrayOfKeyValueOfstringanyType() {
        return new ArrayOfKeyValueOfstringanyType();
    }

    /**
     * Create an instance of {@link ArrayOfKeyValueOfstringanyType.KeyValueOfstringanyType }
     * 
     */
    public ArrayOfKeyValueOfstringanyType.KeyValueOfstringanyType createArrayOfKeyValueOfstringanyTypeKeyValueOfstringanyType() {
        return new ArrayOfKeyValueOfstringanyType.KeyValueOfstringanyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringanyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfKeyValueOfstringanyType")
    public JAXBElement<ArrayOfKeyValueOfstringanyType> createArrayOfKeyValueOfstringanyType(ArrayOfKeyValueOfstringanyType value) {
        return new JAXBElement<ArrayOfKeyValueOfstringanyType>(_ArrayOfKeyValueOfstringanyType_QNAME, ArrayOfKeyValueOfstringanyType.class, null, value);
    }

}
