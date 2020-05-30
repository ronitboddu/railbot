package com.railbot.project;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.railbot.project.actions.IAction;
import com.railbot.project.models.Data;
import com.railbot.project.models.MyCSVReader;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;
import com.railbot.project.models.Response_Builder;

public class live_trn_stats implements IAction  {
	List<Data> searchedData;
	String Train_name;
	String output1,output2;
	String output;
	
	public String Get_train_name(String train_number){
		
		searchedData = MyCSVReader.getTrainName(train_number);
		if(searchedData==null) output="You have entered a non-existing train number.Please enter an existing train number";
		else{
			for(int i = 0 ; i < searchedData.size() ; i++) Train_name = searchedData.get(i).getTrain_name();
		}
		return Train_name;
	}
	public Date detail(int sub) {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_WEEK, sub);
	    return cal.getTime();
	}
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response response = new Response();
		Response_Builder rb = new Response_Builder();
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		Date today = new Date();
		Date yesterday = new Date();
		today=detail(0);
		yesterday=detail(-1);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String to_day = simpleDateformat.format(today)+" "+dateFormat.format(today);
		String yester_day = simpleDateformat.format(yesterday)+" "+dateFormat.format(yesterday);
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		String train_number=parameters.get("train_number1").toString();
		String Train_name=Get_train_name(train_number);
		try{
			Document doc = Jsoup.connect("https://trainstatus.info/running-status/"+train_number+"-aii-af-intercity-today")
					.userAgent("Mozilla")
					.post();

			Document docy = Jsoup.connect("https://trainstatus.info/running-status/"+train_number+"-aii-af-intercity-yesterday")
					.userAgent("Mozilla")
					.post();
			String res="";
			String res1="";
			res=doc.select("div.alert-warning").text();
			res1=docy.select("div.alert-warning").text();
			System.out.println(res+"\n"+res1);
			if(res.equalsIgnoreCase("Train doesn't run on Given Date.")){
				output1=String.format("Train %s(%s) does not run on %s",train_number,Train_name,to_day);
			}
			else{
				output1 = String.format("Train status for %s(%s) that departed on %s:\n"+res, train_number,Train_name
						,to_day);
			}

			if(res1.equalsIgnoreCase("Train doesn't run on Given Date.")){
				output2=String.format("Train %s(%s) does not run on %s",train_number,Train_name,yester_day);
			}
			else{
				output2 = String.format("Train status for %s(%s) that departed on %s:\n"+res1, train_number,Train_name
						,yester_day);
			}
			output=rb.String_response(output1+"\n"+output2);
			response.setFulfillmentText(output1+"\n"+output2);
			response.setFulfillmentMessages(output);
			
			//Train doesn't run on Given Date.
			//System.out.println(res1.substring(0,21).concat(day+"."));
		} catch (Exception e) {
			e.printStackTrace();
	}
		return response;
}
	}
