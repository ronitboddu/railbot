package com.railbot.project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.railbot.project.actions.IAction;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;
import com.railbot.project.models.Response_Builder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Pnr implements IAction {
	String output="";
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response response = new Response();
		Response_Builder rb = new Response_Builder();
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		String train_number1 = parameters.get("pnr-number").toString();
		List<Map<String, Object>> pnr_resp= new ArrayList<Map<String, Object>>();
		try {
			Document doc = Jsoup.connect("https://indianrailways.info/pnr_status/")
					.data("pnr_no", train_number1)
					.userAgent("Mozilla")
					.post();
					
			Elements b=doc.select("div.table-responsive");
			Element first = b.get(0);
			Element sec = b.get(1);
			Element table1 = first.select("table").get(0);
			Element table2 = sec.select("table").get(0);
			String train_name = table1.select("tr").get(0).select("td").get(2).getElementsByTag("a").text();
			String train_number = table1.select("tr").get(1).select("td").get(2).getElementsByTag("a").text();
			String travel_date = table1.select("tr").get(2).select("td").get(2).text();
			String travel_class = table1.select("tr").get(3).select("td").get(2).text();
			String boarding_point = table1.select("tr").get(7).select("td").get(2).getElementsByTag("a").text();
			String reservation_upto = table1.select("tr").get(8).select("td").get(2).getElementsByTag("a").text();
			String no_of_passengers = table2.select("thead").get(0).select("th").text();
			Elements rows = table2.select("tbody").get(0).select("tr");
			output = String.format("Train Name : %s\nTrain Number : %s\nTravel Date : %s\nClass : %s"
					+ "\nBoarding Point : %s\nReservation Upto : %s\n%s\nBooking status\tCurrent status\n"
				,train_name,train_number,travel_date,travel_class,boarding_point,reservation_upto
				,no_of_passengers);
			for(int i=0;i<rows.size()-1;i++){
				String booking_status = rows.get(i).select("td").get(1).text();
				String current_status = rows.get(i).select("td").get(2).text();
				output = output + String.format("    %s   \t    %s\n",booking_status, current_status);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
			output="Server is down!!";
		}
		
		pnr_resp = rb.String_response(output);
		response.setFulfillmentMessages(pnr_resp);
		response.setFulfillmentText(output);
		return response;

	}
	
}
