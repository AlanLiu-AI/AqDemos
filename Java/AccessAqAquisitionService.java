package Aquarius.Demo;

import java.io.*;
import java.net.*;

public class AccessAqAquisitionService {
	
	public static void AccessViaHttp()
	{
		String serviceURL = "http://sooke/AQUARIUS/AQAcquisitionService.svc/Basic";
		System.out.println("Start sending request...");	
		try
		{
			URL url = new URL( serviceURL );  
			HttpURLConnection rc = (HttpURLConnection)url.openConnection();  
			rc.setRequestMethod("POST");  
			rc.setDoOutput( true );  
			rc.setDoInput( true );   
			rc.setRequestProperty( "Content-Type", "text/xml; charset=utf-8" );  
			String reqStr = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">" + 
				"<s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"+
				"<GetAuthToken xmlns=\"http://tempuri.org/\"><user>admin</user><encodedPassword>admin</encodedPassword></GetAuthToken>"+
				"</s:Body></s:Envelope>";  
			int len = reqStr.length();
			rc.setRequestProperty( "Content-Length", Integer.toString( len ) );  
			rc.setRequestProperty("SOAPAction", "GetAuthToken" );  
			rc.connect();      
			OutputStreamWriter out = new OutputStreamWriter( rc.getOutputStream() );   
			out.write( reqStr, 0, len );  
			out.flush();  
			System.out.println("Request sent, reading response ");  
			InputStreamReader read = new InputStreamReader( rc.getInputStream() );  
			 // note that Content-Length is available at this point  
			StringBuilder sb = new StringBuilder();     
			int ch = read.read();  
			while( ch != -1 ){  
				sb.append((char)ch);  
				ch = read.read();  
			}
			String response = sb.toString();
			System.out.println("Response: " + response);
			read.close();
			rc.disconnect();
		} catch(Exception ex) {
			System.out.println("Error occurred: " + ex.toString());
		}	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AccessViaHttp();
	}

}
