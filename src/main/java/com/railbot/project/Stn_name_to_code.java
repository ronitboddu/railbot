package com.railbot.project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.railbot.project.actions.IAction;
import com.railbot.project.models.CSVReader_Stations;
import com.railbot.project.models.Data_stations;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;
import com.railbot.project.models.Response_Builder;

public class Stn_name_to_code implements IAction {
	
	String station_name;
	String code,name;
	String temp="";
	List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
	List<Data_stations> searchedData;
	@Override
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response_Builder rb = new Response_Builder();
		Response response = new Response();
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		station_name=parameters.get("station_names").toString();
		searchedData = CSVReader_Stations.getStationCode(station_name);
			     if(searchedData==null){
			    	 temp = "You have entered a non-existing station name.Please enter a "
			    	 		+ "valid station name";
			     }
			   
			     else{
			    	 for(int j=0;j<searchedData.size();j++){
				    	 name = searchedData.get(j).getStation_name();
				    	 code = searchedData.get(j).getStation_code();
				    	 if(name.equalsIgnoreCase(station_name) || name.equalsIgnoreCase(station_name+" Junction")
				    			 || name.equalsIgnoreCase(station_name+" jn")){
				    		 temp=String.format("Station code for %s is %s", name , code);
				    		 break;
				    	 }
				    	 else{
				    		 temp = temp + String.format("%s - %s \n",name,code);
				    	 }
				     }
			    	 output = rb.String_response(temp);
			    	 response.setFulfillmentText(temp);
			    	 response.setFulfillmentMessages(output);
			     }
		return response;
	}
}
