package srm_integrator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class CreateIncident {
	private static final String APPLICATION_X_WWW_FORM_URLENCODED ="application/x-www-form-urlencoded";
	private static final String ApplicationJson="application/json";
	private final static String urlAuthenticate = "http://10.41.4.2:8008/api/jwt/login";
	private final static String urlEntryUrl = "http://10.41.4.2:8008/api/arsys/v1/entry/HPD:IncidentInterface_Create";
	private final static String aeUsername = "appadmin";
	private final static String aePass = "pune@123";
	private final static String slackChannel = "D8Z8ZMQVC";
	static String slackURL = "https://slack.com/api/chat.postMessage?token=xoxb-304571486805-t4PycEAFaVNiU5cJE7MbK3KK&channel=";


	public static void getToken(String description) throws Exception {
		String token="Unknown";
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
	
		System.out.println("Authentication token: " + output);
		getReqests(output,description);
		//return output;
	}
	

	public static void getReqests(String token,String description) throws Exception {
		String requestID="Unknown";



         //get header by 'key'
		String requestJSON="{"
				+ " \"values\": "
				+ "{"
				+ " \"First_Name\": \"App\","
				+ " \"Last_Name\": \"Admin\","
				+ " \"Description\": \""+description+"\","
				+ " \"Impact\": \"1-Extensive/Widespread\","
				+ " \"Urgency\": \"1-Critical\","
				+ " \"Status\": \"Assigned\","
				+ " \"Reported Source\": \"Direct Input\","
				+ " \"Service_Type\": \"User Service Restoration\","
				+ " \"z1D_Action\": \"CREATE\" }"
				+ "}";
         Client client = Client.create();
		
    	WebResource webResource = client.resource(urlEntryUrl);
    	WebResource.Builder builder = webResource.accept("application/json");
    	builder.header("Authorization", "AR-JWT " + token);
    	builder.type(ApplicationJson);
    	ClientResponse response = builder.post(ClientResponse.class, requestJSON);
	    String output=response.getLocation().toString();
	   String[] res=output.split("/");
	    requestID=res[8];
         System.out.println("RequestID:"+requestID);
         getIncident(token,output);
    	}
	
	public static void getIncident(String token,String requestID) throws Exception {
		 Client client = Client.create();
		WebResource webResource = client.resource(urlEntryUrl);
    	WebResource.Builder builder = webResource.accept("application/json");
    	builder.header("Authorization", "AR-JWT " + token);
    	builder.type(ApplicationJson);
    	ClientResponse response = builder.get(ClientResponse.class);
		String JsonInput = response.getEntity(String.class);
	//	System.out.println("JsonInput: " + JsonInput);

      ObjectMapper mapper1 = new ObjectMapper();
	    JsonNode actualObj = mapper1.readTree(JsonInput);
	   String entries = actualObj.get("entries").toString().replace("[", "").replace("]", "");
	   actualObj=mapper1.readTree(entries);
	   String values = actualObj.get("values").toString();//.replace("{", "").replace("}", "");
	   actualObj=mapper1.readTree(values);
	   String incidentId=actualObj.get("Incident Number").toString();
	   System.out.println("Incident Number:"+incidentId);
	   slackReply(incidentId);
	}

	public static void slackReply(String incidentId){
		String outputJSON = "";
		slackURL = slackURL +slackChannel+"&text=Your+incident+has+been+created+successfully.+Your+incident+number+is+"+incidentId;
		System.out.println(incidentId);
		try{
			URL url = new URL(String.valueOf(slackURL));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String t2="";
			while ((t2 = br.readLine()) != null) {
				outputJSON = String.valueOf(outputJSON) + t2;
			}
			conn.disconnect();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 
	}
	public static void main(String[] args) throws Exception{
		String inputStr="";
		for(int i=0;i<args.length;i++){
			inputStr=inputStr+args[i]+" ";
		}
		System.out.println("InputStr:"+inputStr);
		getToken(inputStr);
	
		//System.out.println(Status);
		
		
	}
}
