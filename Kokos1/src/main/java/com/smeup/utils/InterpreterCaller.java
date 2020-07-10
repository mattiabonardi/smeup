package com.smeup.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class InterpreterCaller {
	
	private String fun;
	private String interpreter;
	
	public InterpreterCaller(String fun, String interpreter)
	{
		this.fun = fun;
		this.interpreter = interpreter;
	}
	
	public String call() throws IOException
	{
		String response = "";
		
		switch(interpreter)
		{
		case "jariko" :
		{
			response = jarikoCaller();
		}break;
		
		/*case "scp sch" :
		{
			String response = scpschCaller();
		}*/
		
		default: System.out.println("interprete non gestito");
		}
		
		
		return response;
	}
	
	private String jarikoCaller() throws IOException
	{
		
		System.out.println(fun);
		
		String url ="http://172.16.2.146:8200//mu-runtime/executeFun";
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(url);
	 
	    List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("fun", fun));
        
        httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
	    httpPost.setHeader("Accept", "application/xml");
	    httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
	 
	    CloseableHttpResponse response = null;
	    
		try {
			response = client.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    String rs = EntityUtils.toString(response.getEntity());
	    
	    System.out.println("RISPOSTA DA JARIKO -- "+ rs);
	    
	    client.close();
		
		return rs;
		
		
	}

}
