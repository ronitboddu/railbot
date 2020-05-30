package com.railbot.project.models;

import java.util.List;
import java.util.Map;

public class Response {
	private String fulfillmentText;
	private List<Map<String, Object>> fulfillmentMessages;
	
	public List<Map<String, Object>> getFulfillmentMessages() {
		return fulfillmentMessages;
	}

	public void setFulfillmentMessages(List<Map<String, Object>> fulfillmentMessages) {
		this.fulfillmentMessages = fulfillmentMessages;
	}

	public String getFulfillmentText() {
		return fulfillmentText;
	}

	public void setFulfillmentText(String fullfilmentText) {
		this.fulfillmentText = fullfilmentText;
	}
}
