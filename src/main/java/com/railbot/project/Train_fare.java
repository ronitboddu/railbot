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
public class Train_fare implements IAction {
	String Train_number;
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response response = new Response();
		Response_Builder rb = new Response_Builder();
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		Train_number = parameters.get("train_number").toString();
		String res="";
		try {
			String url = String.format("https://erail.in/train-fare/%s",Train_number);
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla")
					.post();
					
			Elements b=doc.select("div.panel-warning");
			//System.out.println(b);
			for(int i=1;i<6;i++){
				for(int j=0;j<5;j++){
					Element d=b.select("tr").get(i);
					if(j==0)
						res+=d.select("td").get(j).text().toString()+"\n";
					if(j==1)
						res+="1AC:   "+d.select("td").get(j).text().toString()+"\n";
					if(j==2)
						res+="2AC:   "+d.select("td").get(j).text().toString()+"\n";
					if(j==3)
						res+="3AC:   "+d.select("td").get(j).text().toString()+"\n";
					if(j==4)
						res+="SL:    "+d.select("td").get(j).text().toString()+"\n";
					if(j==5)
						res+="GN:    "+d.select("td").get(j).text().toString()+"\n";
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		output=rb.String_response(res);
		response.setFulfillmentText(res);
		response.setFulfillmentMessages(output);
		
		return response;
	}

}
