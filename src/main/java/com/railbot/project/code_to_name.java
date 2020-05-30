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

public class code_to_name implements IAction {
	String station_code;
	String station_name;
	String station_list;
	String station_c;
	String temp="";
	List<Data_stations> searchedData;
	List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
	@Override
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response response=new Response();
		Response_Builder rb = new Response_Builder();
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		station_code=parameters.get("Station_code").toString();
		searchedData = CSVReader_Stations.getStationName(station_code);
		
		if(searchedData.size()==0){
			temp="You have entered a non-existing station code.Please enter a valid station "
					+ "code.";
		}
		else{
			station_name=searchedData.get(0).getStation_name();
			temp=station_code+" - "+station_name;
		}
		output=rb.String_response(temp);
		response.setFulfillmentText(temp);
		response.setFulfillmentMessages(output);
		return response;
	}

}
