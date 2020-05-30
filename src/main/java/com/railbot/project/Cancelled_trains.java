package com.railbot.project;

import java.io.IOException;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.railbot.project.actions.IAction;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;

public class Cancelled_trains implements IAction{

	@Override
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		String res="";
		Response response = new Response();
		try {
			Document doc = Jsoup.connect("http://www.totaltraininfo.com/cancel-trains.php")
					.userAgent("Mozilla")
					.post();
			 Elements d=doc.select("tr");
			 for(int i=3;i<d.size();i++){
				 Element temp=doc.select("tr").get(i);
				 Element d1=temp.select("td").get(2); 
				 res+=d1.text()+"\n";
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setFulfillmentText(res);
		return response;
	}

}
