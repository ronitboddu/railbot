package com.railbot.project.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Connection {
	
		public JSONObject Make_conncetion(String url) throws IOException{
			JSONObject myResponse = null;
			try{
			     URL obj = new URL(url);
			     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			     // optional default is GET
			     con.setRequestMethod("GET");
			     //add request header
			     con.setRequestProperty("User-Agent", "Mozilla/5.0");
			     BufferedReader in = new BufferedReader(
			             new InputStreamReader(con.getInputStream()));
			     String inputLine;
			     StringBuffer response = new StringBuffer();
			     while ((inputLine = in.readLine()) != null) {
			     	response.append(inputLine);
			     }
			     in.close();
			     myResponse = new JSONObject(response.toString());
			      
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			return myResponse;
		}
			
		public String CSV_Connection(String url){
			String response = "";
			try{
				 URL obj = new URL(url);
			     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			     // optional default is GET
			     con.setRequestMethod("GET");
			     //add request header
			     con.setRequestProperty("User-Agent", "Mozilla/5.0");
			     BufferedReader in = new BufferedReader(
			             new InputStreamReader(con.getInputStream()));
			     String inputLine;
			     while ((inputLine = in.readLine()) != null) {
			     	response=response+inputLine+"\n";
			     }
			     in.close();
			     
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			return response;
		}

}
