package com.railbot.project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import com.railbot.project.actions.IAction;
import com.railbot.project.models.CSVReader_Stations;
import com.railbot.project.models.Data_stations;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;
import com.railbot.project.models.Response_Builder;

public class Trn_between_stns implements IAction  {
	List<Data_stations> searchedData;
	String ret="empty";
	String temp="";
	String pattern="^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9]|[0-9])|((1)[0-2]))(\\-)\\d{4}$";
	public String get_station_code(String dest){
		searchedData = CSVReader_Stations.getStationCode(dest);
		if(searchedData!=null){
			for(int j=0;j<searchedData.size();j++){
		    	 String name = searchedData.get(j).getStation_name();
		    	 String code = searchedData.get(j).getStation_code();
		    	 if(name.equalsIgnoreCase(dest) || name.equalsIgnoreCase(dest+" Junction")
		    			 || name.equalsIgnoreCase(dest+" jn")){
		    		 ret=code;
		    		 break;
		    	 }
			}
		}
		return ret;
	}
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response response = new Response();
		Response_Builder rb = new Response_Builder();
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		String dest_station = parameters.get("dest_station_names").toString();
		String source_station = parameters.get("source_station_names").toString();
		String date=parameters.get("Date").toString();
		String dest_code=get_station_code(dest_station);
		String source_code =get_station_code(source_station);
		if(dest_code.equalsIgnoreCase("empty")) temp="You have entered a non existing destination station";
		else if(source_code.equalsIgnoreCase("empty")) temp="You have entered a non existing source station";
		else if(!(date.matches(pattern))) temp = "Please enter query with date in format dd-mm-yyyy\neg:trains"
				+ " between dadar to kalka on 24-05-2019";
		else{
			try{
				String[] individual = date.split("-");
				String url ="https://www.railyatri.in/booking/trains-between-stations?from_code="+source_code.toUpperCase()+"&from_name=+&journey_"
						+ "date="+individual[0]+"%2F"+individual[1]+"%2F"+individual[2]+"&to_code="+dest_code+"&to_name=+&user_id=-1554343350&user_token=493420";
				Document doc = Jsoup.connect(url)
						.userAgent("Mozilla")
						.post();
				Elements d=doc.select("p.train-name-title");
				if(d.size()==1) temp="Sorry! No train(s) find your search criteria.";
				else{
					for(int i=0;i<d.size();i++){
						String text = d.get(i).getElementsByTag("a").text();
						temp=temp+text+"\n";
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		output=rb.String_response(temp);
		response.setFulfillmentText(temp);
		response.setFulfillmentMessages(output);
		
		return response;
	}
}
