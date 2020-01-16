package org.tmsandbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.json.JSONArray;
import org.testng.Reporter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientUtils {
	
	public Client client;
    public ClientResponse response;
    public WebResource resource;
    
    public JerseyClientUtils() {
    		client = Client.create();
	}
	

    public ClientResponse getOperation(String uri, String path, MultivaluedMap<String, String> queryParams, Map<String, String> headers){
    		resource = client.resource(uri)
    			.path(path)
    			.queryParams(queryParams);
    		
    		WebResource.Builder builder = resource.getRequestBuilder();
    		
    		if(headers != null){
			for (String key : headers.keySet()){
				builder = builder.header(key, headers.get(key));
			}
    		}	
    		
    		try {
    			response = builder.get(ClientResponse.class);
        		Reporter.log("========================================", true);
           	Reporter.log("GET: "+resource.getURI().toString(), true);
           	Reporter.log("Status: "+response.getStatus()+" "+response.getStatusInfo(), true);
           	Reporter.log("========================================", true);
    		}catch (Exception e) {
    			Reporter.log("========================================", true);
    			Reporter.log("GET: "+resource.getURI().toString(), true);
    			Reporter.log("Status: "+response.getStatus()+" "+response.getStatusInfo(), true);
    			Reporter.log("Response Headers:", true);
    			MultivaluedMap<String, String> respHeaders = response.getHeaders();
    			for (String headerkey : respHeaders.keySet()){
    				Reporter.log(headerkey +"="+respHeaders.get(headerkey), true);	
    			}
    			Reporter.log("========================================", true);
		}

    		return response;
    }
    
    public String getResponseBody(ClientResponse response){
	    	String jsonString = response.getEntity(String.class);
	    	Reporter.log("Response Body: "+jsonString, true);
	    	return jsonString;
	}
    
    public List<Map<String, Object>> getJsonArrayValues(String json){
	    	JSONArray array = new JSONArray(json);
	    	List<Map<String, Object>> jsonValues = new ArrayList<Map<String,Object>>();
	    	for (int i = 0; i < array.length(); i++) {
	    		Map mapper = null;
				try {
					mapper = new ObjectMapper().readValue(array.get(i).toString(), HashMap.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (org.json.JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		jsonValues.add(mapper);
	    	}
	    	return jsonValues;
	}
}
