package com.railbot.project;

import java.util.HashMap;

public class QueryResult {
	private String queryText;
	private String action;
	private HashMap<String, Object> parameters;
	private boolean allRequiredParamsPresent;
	private String fulfillmentText;
	private Intent intent;
	private double intentDetectionConfidence;
	private String languageCode;
	
	
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public HashMap<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}
	public boolean isAllRequiredParamsPresent() {
		return allRequiredParamsPresent;
	}
	public void setAllRequiredParamsPresent(boolean allRequiredParamsPresent) {
		this.allRequiredParamsPresent = allRequiredParamsPresent;
	}
	public String getFulfillmentText() {
		return fulfillmentText;
	}
	public void setFulfillmentText(String fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}
	public Intent getIntent() {
		return intent;
	}
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
	public double getIntentDetectionConfidence() {
		return intentDetectionConfidence;
	}
	public void setIntentDetectionConfidence(double intentDetectionConfidence) {
		this.intentDetectionConfidence = intentDetectionConfidence;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
}
