package srm_integrator;

import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.ARServerUser;
import com.bmc.arsys.api.Entry;
import com.bmc.arsys.api.Value;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author sundeepk
 */
public class SRDCreation {

	RemedyConnection rcon=null;
	SearchRemedy remser = new SearchRemedy();
	Entry record=null;
	String RequestID="";
	//String SRDTitle="TestSRDQuestions";
	String Notes="";
	String NavCatagory="Identity Access And Password Management";
	int impact;
	int urgency;
	String ApprovalKey;
	String SRD_Numer="SRD000000001301";
	String submitter="";
	int ServiceRequestApproval;
    String SRDInstanceID="SRGAA5V0G758EAO9FBBJBB4EC1F6W2";
	String TitleFromSRD="TestSRDQuestions";
	String reqid="";
	String SRinstanceID;
	static ARServerUser ctx;
	Entry QRecord=null;
	String QuestionD,QuestionS;
	Entry QtextRecord=null;    
	String QuestionText="",QuestionFormat;
	int QFormat,Order;
	List<Entry> RecordMultiple=null;
	FieldFormatEnum FieldFormat= new FieldFormatEnum();
	String servieceRequestID = "";
	static String slackURL = "https://slack.com/api/chat.postMessage?token=xoxb-304571486805-t4PycEAFaVNiU5cJE7MbK3KK&channel=";
	public void getData(String p, String requestType) throws IOException
	{
		String path=p+"/ServieceRequestQuestions.xls";
		File excel = new File(path);
		FileInputStream fis = new FileInputStream(excel);
		HSSFWorkbook wb=null;
		wb = new HSSFWorkbook(fis);
		HSSFSheet ws = wb.getSheet("ServiceRequestMapping");
		HSSFRow row=null;
		int reqeustIndex = 0;
		int srdNoIndex = 1;
		int srdInstanceIndex = 2;
		int rowNum = ws.getLastRowNum() + 1;
		for (int i = 1; i < rowNum; i++)
		{
			row = ws.getRow(i);
			String excelque  ="\""+cellToString(row.getCell(reqeustIndex)).trim()+"\"";
 			if(requestType.equalsIgnoreCase(excelque))
			{
				SRD_Numer = cellToString(row.getCell(srdNoIndex));
				SRDInstanceID = cellToString(row.getCell(srdInstanceIndex));
				System.out.println("SRD_Number : "+SRD_Numer);
			}
		}
	}
	public void GetRemedyConnection()
	{
		rcon = new RemedyConnection();
		ctx= rcon.getRemedyConnection();  
	}
	public void RetriveSRDdetails(String reqid)
	{
		String RemedyQual= "'1'=\""+reqid+"\"";
		record=remser.FetchSingleRecordWithQual(ctx, RemedyQual,"SRM:RequestInterface_Create");
		
		// Fetch vales from Remedy..
		if(record != null && !(record.isEmpty()))
		{
			for (Integer i : record.keySet()) 
			{
				RequestID=record.getEntryId();
				SRinstanceID=record.get(179).toString();
				servieceRequestID = record.get(1000000829).toString(); //1000000829
			}
			System.out.println("SRD  Details : \n");
			System.out.println("SR Instance ID : "+SRinstanceID);
			System.out.println("RequestID : "+RequestID);
			System.out.println("Seviece Request Id : "+servieceRequestID);
		}
		slackReply("D8Z8ZMQVC", servieceRequestID);
	}
	public void CreateSRD(String srdTitle) throws ARException
	{
		String schema = "SRM:RequestInterface_Create";
		Entry entry = new Entry();
		entry.put(301244700, new Value(srdTitle));

		entry.put(7, new Value(1800));
		entry.put(1000000163, new Value(1000));
		entry.put(1000000162, new Value(1000));

		entry.put(301378004, new Value("Email"));
		entry.put(301303200, new Value(SRDInstanceID));
		entry.put(1000000076, new Value("CREATE"));
		reqid=ctx.createEntry(schema, entry);
		RetriveSRDdetails(reqid);
		System.out.println("Service Request Created with ID:"+reqid);
	}
	public void CreateQuestions(Map<String, String> map,String path) throws ARException, IOException
	{

		String SchemaQuestions="SRM:QuestionSRD";
		String SchemaQuestionText="SRM:QuestionDef";
		String Qual1= "'302797800'=\""+SRDInstanceID+"\"";
		RecordMultiple=remser.FetchRecordWithQual(ctx, Qual1,SchemaQuestions);
		if(RecordMultiple != null && !(RecordMultiple.isEmpty()))
		{
			for(int i=0;i<RecordMultiple.size();i++)
			{
				QRecord=RecordMultiple.get(i);
				if(QRecord != null && !(QRecord.isEmpty()))
				{

					RequestID=QRecord.getEntryId();
					QuestionD=QRecord.get(303828900).toString();
					QuestionS=QRecord.get(179).toString();
					Order=Integer.parseInt(QRecord.get(303824800).toString());
					System.out.println("QuestionD "+QuestionD+" QuestionS  "+QuestionS);
					String qual2="'179'=\""+QuestionD+"\"";
					QtextRecord=remser.FetchSingleRecordWithQual(ctx, qual2,SchemaQuestionText);
					if(QtextRecord != null && !(QtextRecord.isEmpty()))
					{
						QuestionText=QtextRecord.get(303824900).toString();
						QFormat=Integer.parseInt(QtextRecord.get(303857600).toString());
						QuestionFormat=FieldFormat.ReturnStatus(QFormat);

						System.out.println("Question  Details : \n"+QuestionText+"\n"+QuestionFormat);

						String que = getQuestion(QuestionText,path);
						InSertQuestion(map.get(que.trim()).toString());
					}
					QuestionD="";
					QuestionS=""; 
					//  System.out.println("SRD  Details : \n");
					// System.out.println("SR Instance ID : "+SRinstanceID);
					// System.out.println("RequestID : "+RequestID);
				}

			}
		}
	}


	public void  InSertQuestion(String answer) throws ARException
	{
		Entry entry = new Entry();
		//  entry.put(7, new Value(1800));
		entry.put(301368700, new Value(SRinstanceID));
		entry.put(301020200, new Value(QuestionD));
		entry.put(303867700, new Value(QuestionS));
		entry.put(300992400, new Value(QuestionText));
		entry.put(303632100, new Value(Order));
		entry.put(303632200, new Value(1));
		entry.put(300992500, new Value(QuestionFormat));
		entry.put(303630000, new Value(answer));
		entry.put(303935200, new Value(answer));
		entry.put(303669500, new Value(answer));

		ctx.createEntry("SRD:MultipleQuestionResponse", entry);
		System.out.println("Question Added Successfully...........");
	}

	public static String getQuestion(String que,String p) throws IOException{
		String result="";
		String path=p+"/ServieceRequestQuestions.xls";
		File excel = new File(path);
		FileInputStream fis = new FileInputStream(excel);
		HSSFWorkbook wb=null;
		wb = new HSSFWorkbook(fis);
		HSSFSheet ws = wb.getSheet("QuestionMapping");
		HSSFRow row=null;

		int rowNum = ws.getLastRowNum() + 1;
		int question = 0;
		int key = 1;

		for (int i = 1; i < rowNum; i++)
		{
			row = ws.getRow(i);
			String excelque  =cellToString(row.getCell(question));
			if(que.equalsIgnoreCase(excelque))
			{
				result = cellToString(row.getCell(key));
				System.out.println("selected question : "+result);
			}
		}
		return result;
	}

	public static String cellToString(HSSFCell cell) 
	{	
		String date ="";
		Object result = null;
		switch (cell.getCellType()) 
		{
		case HSSFCell.CELL_TYPE_NUMERIC:
			//Date date = new Date();
			if (HSSFDateUtil.isCellDateFormatted(cell))
			{
				SimpleDateFormat sdfdate = new SimpleDateFormat("MM-dd-yyyy");
				//SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

				date = sdfdate.format(cell.getDateCellValue());
				// System.out.println(date.toString());
				result=date.toString();
			}
			else 
			{
				result = Integer.valueOf((int) cell.getNumericCellValue())
						.toString();
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:

			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();

		}
		return result.toString();
	}
	
	public static void slackReply(String slackChannel, String requestID){
		String outputJSON = "";
		slackURL = slackURL +slackChannel+"&text=Your+ticket+is+successfully+created.+Your+request+Id+is+"+requestID;
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
	
	public static void main(String args[]) throws ARException, IOException
	{

		/*String inputJson = "{\"ServiceRequest\": \"TestSRDQuestions\","
				+"    \"parameters\": {"
				+"                \"Question1Text\": \"Text\","
				+ "          \"Question2RadioButton\": \"RadioButton\","
				+ "          \"Question3CheckBox\": \"CheckBox\","
				+ "          \"Question4Range\": \"12\","
				+ "          \"Question5MenuStatic\": \"MenuStatic\","
				+ "          \"MenuQuery6\": \"MenuQuery\","
				+ "         \"Question7DateTime\": \"14-12-2017 12:25\","
				+ "          \"QuestionDate8\": \"14-12-2017\","
				+ "          \"QuestionTime9\": \"12:25\""
				+ "  }"
				+ "}";*/
		//System.out.println("Service request : "+srdCreate(inputJson));

		
		String path = args[0];
		String inputJson =args[1];
		
		String line = "";
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = 
					new FileReader(inputJson);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
			inputJson ="";
			while((line = bufferedReader.readLine()) != null) {
				inputJson +=line; 
				System.out.println(line);
			}   

			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" + 
							inputJson + "'");                
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ inputJson + "'");                  
		}


		System.out.println("JsonInput : "+ inputJson);
		String params ="";
		String serviceRequest  = "";
		Map<String, String> map = new HashMap<String, String>();
		try{
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(inputJson);
			serviceRequest = actualObj.get("ServiceRequest").toString();
			actualObj = mapper.readTree(inputJson);
			params = actualObj.get("parameters").toString();
			System.out.println("JSon Node : "+inputJson);
			System.out.println("Parameters : "+params);
		}catch (Exception e) {
			e.printStackTrace();
		}

		try {

			ObjectMapper mapper = new ObjectMapper();
			// convert JSON string to Map
			map = mapper.readValue(params, new TypeReference<Map<String, String>>(){});
			System.out.println("Map : "+map);

		}catch(JsonGenerationException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		serviceRequest = serviceRequest.replace("\"\"", "\"");
		SRDCreation rmodify = new SRDCreation();
        rmodify.getData(path, serviceRequest);
		rmodify.GetRemedyConnection();
		rmodify.CreateSRD(serviceRequest);
		rmodify.CreateQuestions(map,path);

		//System.out.println(getQuestion("Question3CheckBox"));
	}
	

}
