package com.railbot.project;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.railbot.project.actions.IAction;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;

import table.Train_route1;

@Path("webhook")
public class Webhook {
	@POST
	@Path("/process")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response process(Request input){
		String actionName=input.getQueryResult().getAction();
		IAction action = null;
		if(actionName.equalsIgnoreCase("station.name") || actionName.equalsIgnoreCase("Stationnametocode.getStationName")){
			action = new Stn_name_to_code();
		}
		else if(actionName.equalsIgnoreCase("train.route") || actionName.equalsIgnoreCase("Trainroute.getTrainNumber")){
			action = new Train_route1();
		}
		else if(actionName.equalsIgnoreCase("code_to_name") || actionName.equalsIgnoreCase("Stationcodetoname.getStationCode")){
			action = new code_to_name();
		}
		else if(actionName.equalsIgnoreCase("train_number_to_name")){
			action = new Trn_number_to_name();
		}
		else if(actionName.equalsIgnoreCase("cancel.train") || actionName.equalsIgnoreCase("CancelTrain.CancelTrain-custom")){
			action = new Cancelled_trains();
		}
		else if(actionName.equalsIgnoreCase("train_name_to_number")){
			action = new Trn_name_to_number();
		}
		else if(actionName.equalsIgnoreCase("pnr.status")){
			action = new Pnr();
		}
		else if(actionName.equalsIgnoreCase("TrainFare")){
			action=new Train_fare();
		}
		else if(actionName.equalsIgnoreCase("Trains.bet.stn")){
			action=new Trn_between_stns();
		}
		else if(actionName.equalsIgnoreCase("live_train")){
			action=new live_trn_stats();
		}
		
		try {
			return action.performAction(input);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;}
}
