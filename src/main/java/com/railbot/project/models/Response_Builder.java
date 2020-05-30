package com.railbot.project.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import table.Google;
import table.Items;
import table.Payload;
import table.RichResponse;
import table.Rows;
import table.SimpleResponse;
import table.TableCard;
import table.Text;

public class Response_Builder {
	public List<Map<String,Object>> response_for_tableCard(List<String> data,String data_fb , String[] header_array,int z,String audio){
		List<Map<String,Object>> fulfillment = new ArrayList<Map<String,Object>>();
		Text txt = new Text();
		@SuppressWarnings("rawtypes")
		List<Map> it_list = new ArrayList<Map>();
		Items it= new Items();
		Google gl= new Google();
		Payload pl = new Payload();
		RichResponse rs = new RichResponse();
		SimpleResponse sr = new SimpleResponse();
		Map<String,Object> map_text_fb = new HashMap<String,Object>();
		Map<String,Object> map_text_telegram = new HashMap<String,Object>();
		Map<String,Object> map_text_line = new HashMap<String,Object>();
		Map<String,Object> map_text_viber = new HashMap<String,Object>();
		Map<String,Object> map_text_skype = new HashMap<String,Object>();
		Map<String, Object> payload = new HashMap<String,Object>();
		Map<String, Object> sim_res = new HashMap<String,Object>();
		Map<String, Object> tab_card = new HashMap<String,Object>();
		Map<String,Boolean> divideafter = new HashMap<String,Boolean>();
		
		List<Rows> rw_list = new ArrayList<Rows>();
		TableCard tc = new TableCard();
		List<Map<String,String>> cp_list = new ArrayList<Map<String,String>>();
		
		List<String> t = new ArrayList<String>();
		t.add(data_fb);
		txt.setText(t);
		sr.setTextToSpeech(audio);
		it.setSimpleResponse(sr);
		divideafter.put("divideAfter", true);
		int i=0;
		for(int j=0;j<data.size()/z;j++){
			Rows rw = new Rows();
			List<Map<String,String>> cl_list = new ArrayList<Map<String,String>>();
			while(i<data.size()){
				 Map<String, String> map = new HashMap<String, String>();
				 map.put("text", data.get(i));
				 cl_list.add(map);
				 i++;
				 if(i%z==0){
					 break;
				 }
				 
			}
			rw.setCells(cl_list);
			rw.setDivideAfter(true);
			rw_list.add(rw);
		}
		tc.setRows(rw_list);
		
		for(int j=0;j<z;j++){
			Map<String,String> header = new HashMap<String,String>();
			header.put("header", header_array[j]);
			cp_list.add(header);
		}
		tc.setColumnProperties(cp_list);
		gl.setUserStorage("{\"data\":{}}");
		sim_res.put("simpleResponse", sr);
		tab_card.put("tableCard", tc);
		it_list.add(sim_res);
		it_list.add(tab_card);
		
		rs.setItems(it_list);
		gl.setRichResponse(rs);
		gl.setExpectUserResponse(false);
		pl.setGoogle(gl);
		
		
		payload.put("payload", pl);
		
		
		/*Text txt1 = new Text();
		List<String> t1 = new ArrayList<String>();
		t1.add(data_fb);
		txt1.setText(t);*/
		map_text_fb.put("text", txt);
		map_text_telegram.put("text", txt);
		map_text_line.put("text", txt);
		map_text_viber.put("text", txt);
		map_text_skype.put("text", txt);
		map_text_telegram.put("platform", "TELEGRAM");
		map_text_line.put("platform", "LINE");
		map_text_viber.put("platform", "VIBER");
		map_text_skype.put("platform", "SKYPE");
		map_text_fb.put("platform", "FACEBOOK");
		
		fulfillment.add(map_text_fb);
		fulfillment.add(map_text_line);
		fulfillment.add(map_text_viber);
		fulfillment.add(map_text_skype);
		fulfillment.add(map_text_telegram);
		fulfillment.add(payload);
		return fulfillment;
		
	}
	public List<Map<String,Object>> String_response(String data){
		//For google
		List<Map<String,Object>> fulfillment = new ArrayList<Map<String,Object>>();
		/*Map<String,Object> map_text_google = new HashMap<String,Object>();
		Map<String,String> map_string = new HashMap<String,String>();
		List<Map<String,String>> list_string = new ArrayList<Map<String,String>>();
		com.railbot.project.pnr.SimpleResponse sr = new com.railbot.project.pnr.SimpleResponse();
		map_string.put("textToSpeech", data);
		list_string.add(map_string);
		sr.setSimpleResponses(list_string);
		map_text_google.put("platform", "ACTIONS_ON_GOOGLE");
		map_text_google.put("simpleResponses", sr);*/
		
		
		//For Facebook
		Map<String,Object> map_text_fb = new HashMap<String,Object>();
		Map<String,Object> map_text_telegram = new HashMap<String,Object>();
		Map<String,Object> map_text_line = new HashMap<String,Object>();
		Map<String,Object> map_text_viber = new HashMap<String,Object>();
		Map<String,Object> map_text_skype = new HashMap<String,Object>();
		Text txt = new Text();
		List<String> t = new ArrayList<String>();
		t.add(data);
		txt.setText(t);
		
		//For google
		List<Map> it_list = new ArrayList<Map>();
		//Items it= new Items();
		Google gl= new Google();
		Payload pl = new Payload();
		RichResponse rs = new RichResponse();
		SimpleResponse sr1 = new SimpleResponse();
		Map<String, Object> payload = new HashMap<String,Object>();
		Map<String, Object> sim_res = new HashMap<String,Object>();
		
		sr1.setTextToSpeech("This is a simple response.");
		sr1.setDisplayText(data);
		sim_res.put("simpleResponse", sr1);
		it_list.add(sim_res);
		rs.setItems(it_list);
		gl.setRichResponse(rs);
		gl.setExpectUserResponse(false);
		pl.setGoogle(gl);
		payload.put("payload", pl);
		
		map_text_fb.put("text", txt);
		map_text_telegram.put("text", txt);
		map_text_line.put("text", txt);
		map_text_viber.put("text", txt);
		map_text_skype.put("text", txt);
		map_text_telegram.put("platform", "TELEGRAM");
		map_text_line.put("platform", "LINE");
		map_text_viber.put("platform", "VIBER");
		map_text_skype.put("platform", "SKYPE");
		map_text_fb.put("platform", "FACEBOOK");
		
		fulfillment.add(map_text_fb);
		fulfillment.add(map_text_line);
		fulfillment.add(map_text_viber);
		fulfillment.add(map_text_skype);
		fulfillment.add(map_text_telegram);
		fulfillment.add(payload);
		
		
		return fulfillment;
		
	}
}
