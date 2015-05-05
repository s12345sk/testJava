package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlTest {
	public static void main(String[] args) throws Exception {
		test();

	}
	public static void test() throws Exception{
		 URL yahoo = new URL("http://10.4.29.112:8079/report/deletefeed?feed_id=1059046&reason=测试");
		  BufferedReader in = new BufferedReader(  
	              new InputStreamReader(  
	              yahoo.openStream()));  
	 
	  String inputLine;  

	   while ((inputLine = in.readLine()) != null)  
	     System.out.println(inputLine);  

	   in.close();  
	}

}
