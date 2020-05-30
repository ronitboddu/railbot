package com.railbot.project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import com.railbot.project.actions.IAction;
import com.railbot.project.models.Connection;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;
import com.railbot.project.models.Response_Builder;

public class Cancel_train implements IAction{
	String date;
	String station_name;
	String station_list="";
	String temp;
	@Override
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response response1=new Response();
		Response_Builder rb = new Response_Builder();
		Connection cn = new Connection();
		List<Map<String, Object>> cancelled_train_resp= new ArrayList<Map<String, Object>>();
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		date=parameters.get("date").toString();
		String pattern="^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$";
		if(date.matches(pattern)){
			String url = String.format("https://api.railwayapi.com/v2/cancelled/date/%s/apikey"
			 		+ "/%s/", date,request.apikey);
			 
			     JSONObject myResponse;
				try {
					myResponse = cn.Make_conncetion(url);
					JSONArray route = myResponse.getJSONArray("trains");
				     if(route!=null){
				    		for(int i=0;i<route.length();i++){
				    			JSONObject rec = route.getJSONObject(i);
				    			station_name = rec.getString("name");
				    			station_list=station_list + station_name + "\n" ;
				    	}
				     }
				     else{
				    	 station_list="not available";
				     }
				}  
				 catch (Exception e) {
					e.printStackTrace();
				}
		}
		else{
			station_list="Please enter the date in the format of dd-mm-yyyy";
		}
		cancelled_train_resp=rb.String_response(station_list);
		response1.setFulfillmentText(station_list);
		response1.setFulfillmentMessages(cancelled_train_resp);
		return response1;
	}
}
