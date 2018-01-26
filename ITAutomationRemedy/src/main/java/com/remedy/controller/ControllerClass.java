package com.remedy.controller;


import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.remedy.bussiness.API_AI_Request;

//import org.codehaus.jackson.map.ObjectMapper;

import com.remedy.model.Parameters;
import com.remedy.model.ResponseMdl;
import com.remedy.model.Result;
import com.remedy.bussiness.AERestCall;
import com.remedy.model.API_Request_Model;
import com.remedy.model.Metadata;
import com.remedy.model.OriginalRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("itautoremedy")
public class ControllerClass {
	static String result = "";

	@GET
	public Response getMsg() {
		System.out.println("received get request.");
		return Response.status(200).entity("Welcome User....!").build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getConf(String inputJSON) throws Exception {

		System.out.println("Request recieved");
		API_AI_Request response = new API_AI_Request();

		//System.out.println("responce : " + response.toString());
		API_Request_Model apiAiResponse = response.jsonToJava(inputJSON);

		System.out.println("apiAiResponse : " + apiAiResponse);

		Result rs = apiAiResponse.getResult();

		System.out.println("rs :" + rs.toString());

	    Metadata m = rs.getMetadata();
		OriginalRequest or = apiAiResponse.getOriginalRequest();
		String token = "UNKNOWN";
		String aeRequestId = "UNKNOWN";		
        String parameter = "UNKNOWN";
		String slackUser = or.getData().getEvent().getUser();
		String slackChannel = or.getData().getEvent().getChannel();
		System.out.println("\nChanel: " + slackChannel);
		System.out.println("\nUser: " + slackUser);
     
		String intentname ="";// m.getIntentName();
        
		try{
		    ObjectMapper mapper1 = new ObjectMapper();
		    JsonNode actualObj = mapper1.readTree(inputJSON);
		    String jsonNode1 = actualObj.get("result").toString();
		     actualObj = mapper1.readTree(jsonNode1);
		     parameter = actualObj.get("parameters").toString();
		   System.out.println("Parameters : "+parameter);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		intentname = m.getIntentName();
		Map<String, String> wfParams = new HashMap<>();
		AERestCall aeRestCall = new AERestCall();
		ResponseMdl res=new ResponseMdl();
		if (intentname.equalsIgnoreCase("software_install")) {
			System.out.println("\nintent name is:" + intentname);
			// AERestCall aeRestCall = new AERestCall();
			token = aeRestCall.authenticate();
			/*if(intentname.contains("- Permenent")){
				
			}
			*/
			
			String jsonInput = "{\"ServiceRequest\":\" "+intentname+"\",\"parameters\": "+parameter+"}";
			System.out.println("JsonInput : "+ jsonInput);
			/*jsonInput = jsonInput.replace("%%SOFTWARE_NAME%%", rs.getParameters().getSoftware());
			jsonInput = jsonInput.replace("%%SLACK_CHANNEL%%",slackChannel);
*/
			// Map<String, String> wfParams = new HashMap<>();
			wfParams.put("clientEmail", aeRestCall.getClientEmail(""));
			wfParams.put("inputjson", jsonInput);
			wfParams.put("slackChannel", slackChannel);
			wfParams.put("slackUser", slackUser);

		//	System.out.println("\ntoken: " + token);
			aeRequestId = aeRestCall.execute(token, "SRDCreationRemedy", wfParams);
		    res.setSource("policyWS");
			res.setSpeech("Please wait while we work on your request.");
			res.setDisplayText("Please wait while we work on your request.");

		}		/*	
		else if (intentname.equalsIgnoreCase("incident_status")) {
			System.out.println("\nintent name is:" + intentname);
			token = aeRestCall.authenticate();
			System.out.println("\ntoken: " + token);
			String aeIncidentNo = rs.getParameters().getIncidentNumber();
			int len = aeIncidentNo.length();
			for (int i = len; i <= 8; i++) {
				if (8 - len != 0) {
					aeIncidentNo = "0" + aeIncidentNo;
					len = aeIncidentNo.length();
				}
			}
			System.out.println("\naeIncidentNo=" + aeIncidentNo);
			// wfParams.put("clientEmail", aeRestCall.getClientEmail("saurabh.kulkarni@vyomlabs.com"));
			wfParams.put("incidentNumber", aeIncidentNo);
			wfParams.put("slackChannel", slackChannel);
			aeRequestId = aeRestCall.execute(token, "Get Remedyforce Incident Status", wfParams);
			res.setSource("policyWS");
			res.setSpeech("Please wait while we work on your request.");
			res.setDisplayText("Please wait while we work on your request.");
		}*/
		else if (intentname.equalsIgnoreCase("incident")) {
			System.out.println("\nintent name is:" + intentname);

			// AERestCall aeRestCall = new AERestCall();
			token = aeRestCall.authenticate();
			// Map<String, String> wfParams = new HashMap<>();
		//	wfParams.put("clientEmail", aeRestCall.getClientEmail(""));
			wfParams.put("description", rs.getParameters().getDescription());
			wfParams.put("slackChannel", slackChannel);
			//wfParams.put("slackUser", slackUser);

		//	System.out.println("\ntoken: " + token);
			aeRequestId = aeRestCall.execute(token, "Create Incident Remedy", wfParams);
			res.setSource("policyWS");
			res.setSpeech("Please wait while we work on your request.");
			res.setDisplayText("Please wait while we work on your request.");
		}

        System.out.println("........................");
		//ResponseMdl res = new ResponseMdl();
		res.setSource("policyWS");
		res.setSpeech("Please wait while we work on your request.");
		res.setDisplayText("Please wait while we work on your request.");
        System.out.println("........................");

		ObjectMapper om = new ObjectMapper();
		String str2 = om.writeValueAsString(res);

		return Response.status(200).entity(str2).header("Content-Type", "application/json").build();
	}
}
