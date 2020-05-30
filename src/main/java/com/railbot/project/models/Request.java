package com.railbot.project.models;

import com.railbot.project.QueryResult;

public class Request {
	private String responseId;
	private QueryResult queryResult;
	public String apikey="bwc2y4x10i";
	
	public String getResponseId() {
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	public QueryResult getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(QueryResult queryResult) {
		this.queryResult = queryResult;
	}
	
}
