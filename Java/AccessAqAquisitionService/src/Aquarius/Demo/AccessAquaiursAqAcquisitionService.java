package Aquarius.Demo;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.tempuri.*;

public class AccessAquaiursAqAcquisitionService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
			String serviceURL = "http://sooke/AQUARIUS/AQAcquisitionService.svc";
			AQAcquisitionService aqAcquisitionservice = new AQAcquisitionService(new URL(serviceURL), new QName("http://tempuri.org/", "AQAcquisitionService"));
			IAQAcquisitionService clientPort = aqAcquisitionservice.getWSHttpBindingIAQAcquisitionService();
			((BindingProvider)clientPort).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
			
			String autoToken = clientPort.getAuthToken("admin",  "admin");
			System.out.println("AuthToken is: " + autoToken);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	

	}

}
