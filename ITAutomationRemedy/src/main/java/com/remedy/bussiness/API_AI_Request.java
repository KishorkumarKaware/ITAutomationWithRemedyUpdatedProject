package com.remedy.bussiness;

//import org.codehaus.jackson.map.ObjectMapper;

import com.remedy.model.API_Request_Model;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.databind.ObjectMapper;

public class API_AI_Request {
	public API_Request_Model jsonToJava(String json) {
		API_Request_Model jmResponse = null;
		try {
			//System.out.println("...................");
			System.out.println(json);
			try {
				ObjectMapper mapper = new ObjectMapper();
				jmResponse =  mapper.readValue(json, API_Request_Model.class);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return jmResponse;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}return null;
	}

}
