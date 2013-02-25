package Aquarius.Demo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.api.message.Headers;

import org.tempuri.AQAcquisitionService;
import org.tempuri.IAQAcquisitionService;

import org.datacontract.schemas._2004._07.aquariusdataservice.ArrayOfLocationDTO;
import org.datacontract.schemas._2004._07.aquariusdataservice.ArrayOfTimeSeriesDescription;
import org.datacontract.schemas._2004._07.aquariusdataservice.TimeSeriesDescription;
import org.datacontract.schemas._2004._07.aquariusdataservice.LocationDTO;

public class AccessAquaiursAqAcquisitionService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
			String serviceURL = "http://10.10.222.100/AQUARIUS/AQAcquisitionService.svc";
			AQAcquisitionService aqAcquisitionservice = new AQAcquisitionService(new URL(serviceURL), new QName("http://tempuri.org/", "AQAcquisitionService"));
			IAQAcquisitionService clientPort = aqAcquisitionservice.getWSHttpBindingIAQAcquisitionService();
			((BindingProvider)clientPort).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
			
			String autoToken = clientPort.getAuthToken("admin",  "admin");
			System.out.println("AuthToken is: " + autoToken);
			
			WSBindingProvider bindingProvider = (WSBindingProvider) clientPort;
			bindingProvider.setOutboundHeaders(
			        Headers.create(new QName("AQAuthToken"), autoToken)
			);
			ArrayOfLocationDTO allLocations = clientPort.getAllLocations();
			System.out.println("There are total " + allLocations.getLocationDTO().size() + " Locations: ");
			Iterator<LocationDTO> iter = allLocations.getLocationDTO().iterator();
			
			while(iter.hasNext()) {
				LocationDTO location = iter.next(); 
				System.out.println("\t" + location.getIdentifier());
			}
			
			long locationId = clientPort.getLocationId("01000001"); //A location at 10.10.222.100
			ArrayList<String> tsIdentifierList = new ArrayList<String>();
			ArrayList<String> parameterCodeList = new ArrayList<String>();
			if(locationId>0)
			{
				ArrayOfTimeSeriesDescription tsArray = clientPort.getTimeSeriesList(locationId, null);
				for(TimeSeriesDescription tsDesc : tsArray.getTimeSeriesDescription())
				{
					tsIdentifierList.add(tsDesc.getIdentifier().getValue());
					parameterCodeList.add(tsDesc.getParameterType().getValue());
				}
			}
			
			bindingProvider.close();//Avoid resource leaking
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Enter to quit...");
		try {
	        System.in.read();
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}
}
