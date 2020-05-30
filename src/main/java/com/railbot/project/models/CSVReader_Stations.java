package com.railbot.project.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CSVReader_Stations {
	static String[] attributes;
	
	static Connection con = new Connection();
	private static List<Data_stations> data = null;
	
	public static List<Data_stations> getData(){
		if(data == null){
			data = readData();
		}
		return data;
	}
	public static List<Data_stations> readData(){
		String url = "https://raw.githubusercontent.com/ronitboddu/railbot/master/Book1.csv";
		String t = con.CSV_Connection(url);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(t);
		List<Data_stations> data = new ArrayList<Data_stations>();
		sc.nextLine();
		while(sc.hasNextLine()){
			attributes=sc.nextLine().split(",");
			Data_stations new_data = new Data_stations(attributes[0], attributes[1]);
			data.add(new_data);
		}
		return data;
	}
	public static List<Data_stations> getStationName(String code){
		List<Data_stations> data = getData();
		return data.stream().filter(d->d.getStation_code().toLowerCase().equals(code.toLowerCase())).collect(Collectors.toList());
	}
	public static List<Data_stations> getStationCode(String name){
		List<Data_stations> data = getData();
		return data.stream().filter(d->d.getStation_name().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
	}
}
