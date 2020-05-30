package com.railbot.project;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.railbot.project.actions.IAction;
import com.railbot.project.models.Data;
import com.railbot.project.models.MyCSVReader;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;
import com.railbot.project.models.Response_Builder;

public class Trn_number_to_name implements IAction {
	
	String Train_name;
	String Train_number;
	String source;
	String destination;
	String data_fb="";
	List<String> data = new ArrayList<String>();
	List<Data> searchedData;
	String[] header_array = {"Train number","Train name","Source","Destination"};
	@Override
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response response = new Response();
		Response_Builder rb = new Response_Builder();
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		Train_number = parameters.get("train_number").toString();
		searchedData = MyCSVReader.getTrainName(Train_number);
		
		if(searchedData==null){
			response.setFulfillmentText("You have entered a non-existing train number.Please enter an existing "
					+ "train number");
		}
		else{
			for(int i = 0 ; i < searchedData.size() ; i++){
				Train_name = searchedData.get(i).getTrain_name();
				Train_number = searchedData.get(i).getTrain_number();
				source = searchedData.get(i).getTrain_source_stn();
				destination = searchedData.get(i).getTrain_dest_stn();
				data_fb = data_fb + String.format("%s - %s (%s to %s)\n", Train_number,Train_name,source,destination);
				data.add(Train_number);
				data.add(Train_name);
				data.add(source);
				data.add(destination);
			}
		}
		 output=rb.response_for_tableCard(data, data_fb, header_array, 4,"Here is the train number.");
		 response.setFulfillmentText(data_fb);
		 response.setFulfillmentMessages(output);

		
		return response;
	}

}
