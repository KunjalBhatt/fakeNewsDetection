package com.parul.fakeNews.newsApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsApi {
	@GetMapping(path = "newsApi")
	public String newsPage(){
		return "newsApi";
	}
	
	
	@RequestMapping(path="showNews")
	@ResponseBody
	public String ShowingnewsByCountry(@RequestParam String country) {
		  StringBuilder resp = new StringBuilder();
		try {

		        DefaultHttpClient httpClient = new DefaultHttpClient();
		        HttpGet getRequest = new HttpGet(
		            "https://newsapi.org/v2/top-headlines?country="+country+"&apiKey=f853aeec2a6145c7900a73c299001bfe");
		        getRequest.addHeader("accept", "application/json");

		        HttpResponse response = httpClient.execute(getRequest);

		        if (response.getStatusLine().getStatusCode() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : "
		               + response.getStatusLine().getStatusCode());
		        }

		        BufferedReader br = new BufferedReader(
		                         new InputStreamReader((response.getEntity().getContent())));

		        String output;
		      
		        System.out.println("Output from Server .... \n");
		        while ((output = br.readLine()) != null) {
		            System.out.println(output);
		            resp.append(output);
		        }
		        System.out.println("Apurva = "+resp );
		        httpClient.getConnectionManager().shutdown();

	      } catch (ClientProtocolException e) {
	    
	        e.printStackTrace();

	      } catch (IOException e) {
	    
	        e.printStackTrace();
	      }
		System.out.println("Kunjal Bhatt = "+resp.toString());
		return resp.toString();
	}
}
