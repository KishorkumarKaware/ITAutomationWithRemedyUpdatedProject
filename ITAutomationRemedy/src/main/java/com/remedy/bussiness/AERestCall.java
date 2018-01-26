package com.remedy.bussiness;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MultivaluedMap;

import com.remedy.model.AutomationRequest;

//import org.codehaus.jackson.map.ObjectMapper;
//import org.json.JSONException;
//import org.json.JSONObject;


import com.remedy.model.WorkletParameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class AERestCall {
	private static final String 
		APPLICATION_JSON_CHARSET_UTF_8 = "application/json";
	private static final String APPLICATION_X_WWW_FORM_URLENCODED = 
			"application/x-www-form-urlencoded";
	private static final String CONTENT_TYPE = "Content-Type";
	private final String urlRaiseRequest = "http://10.41.12.17:8080/aeengine/rest/execute";
	// POST action URL
	// private final String urlPOST =
	// "http://192.168.1.194:8888/aeengine/rest/authenticate";
	private final static String urlAuthenticate = 
			"http://10.41.12.17:8080/aeengine/rest/authenticate";
	//
	private final static String urlExecute = 
			"http://10.41.12.17:8080/aeengine/rest/execute";

	private final static String urlStatus = 
			"http://10.41.12.17:8080/aeengine/rest/execute";

	private final static String aeUsername = "ITAdmin";
	private final static String aePass = "Pune@123";
	private static String automationRequestId = null;
	private static String result = null;
	private static String workflowResponse = null;
	
	public String authenticate() {
		String token;
    	Client client = Client.create();
    	WebResource webResource = client.resource(urlAuthenticate);
    	MultivaluedMap<String, String> x = new MultivaluedMapImpl();
    	ArrayList<String> x1 = new ArrayList<>();
    	ArrayList<String> x2 = new ArrayList<>();
    	x1.add(aeUsername);
    	x2.add(aePass);
    	x.put("username", x1);
    	x.put("password", x2);

		ClientResponse res = webResource.type(
				APPLICATION_X_WWW_FORM_URLENCODED).post(ClientResponse.class, x);
		
		String output = res.getEntity(String.class);
		System.out.println("Authenticate Call Status: " + res.getStatus());
		Matcher m1 = 
			Pattern.compile("\"sessionToken\":\"([^\"]*)\"").matcher(output);

		if(!m1.find()) {
			System.out.println("Authentication call DOESNOT return token.");
		}
		token = m1.group(1);
		System.out.println("Authentication token: " + token);
		
		return token;
	}
	
	public String execute(String token, String wfName, 
			Map<String, String> wfParams) 
	{
		String aeRequestId = "";
		AutomationRequest ar = new AutomationRequest();
		ar.setSource("SlackBot");
		ar.setSourceId(null);
		ar.setWorkflowName(wfName);
		ar.setOrgCode("ITADMIN");
		ar.setResponseMailSubject(null);
		List<WorkletParameter> params = new ArrayList<>();
		for(String x: wfParams.keySet()) {
			WorkletParameter wp = new WorkletParameter();
			wp.setName(x);
			wp.setValue(wfParams.get(x));
		
			params.add(wp);
		}
		ar.setParams(params);
		String requestJSON = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			requestJSON = mapper.writeValueAsString(ar);
			//System.out.println("Execute request JSON: " + requestJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Client client = Client.create();
		

    	WebResource webResource = client.resource(urlExecute);
    	WebResource.Builder builder = webResource.accept("application/json");
    	builder.header("X-session-token", token);
    	builder.type("application/json");
    	ClientResponse response = builder.post(ClientResponse.class, requestJSON);
		String output = response.getEntity(String.class);

    	//System.out.println("........"+output);
//    	"automationRequestId":2428,
		Matcher m1 = 
				Pattern.compile("\"automationRequestId\":([0-9]+)").matcher(output);

		if(!m1.find()) {
			System.out.println("Execute call DOESNOT return ae reqeust id.");
		}
		aeRequestId = m1.group(1);
		System.out.println("AE Request Id: " + aeRequestId);

		return aeRequestId;
	}
	
	public String getClientEmail(String slackUserId) {
		return "saurabh.kulkarni@vyomlabs.com";
	}
	
}
