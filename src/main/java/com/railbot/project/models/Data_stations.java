package com.railbot.project.models;

public class Data_stations {
	private String Station_name;
	private String Station_code;
	
	public Data_stations(String Station_name,String Station_code){
		this.Station_name=Station_name;
		this.Station_code=Station_code;
	}
	
	public String getStation_name() {
		return Station_name;
	}
	public void setStation_name(String station_name) {
		Station_name = station_name;
	}
	public String getStation_code() {
		return Station_code;
	}
	public void setStation_code(String station_code) {
		Station_code = station_code;
	}
}
