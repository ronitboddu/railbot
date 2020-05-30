package com.railbot.project.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MyCSVReader {
	static String[] attributes;
	private static List<Data> data=null;
	static Connection con = new Connection();
	
	public static List<Data> getData(){
		if(data==null){
			data=readData();
		}
		return data;
	}
	
	public static List<Data> readData(){
		String url="https://raw.githubusercontent.com/ronitboddu/railbot/master/trn_name_to_number.csv";
		String t = con.CSV_Connection(url);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(t);
		List<Data> data=new ArrayList<Data>();
		sc.nextLine();
		while(sc.hasNextLine()){
			attributes=sc.nextLine().split(",");
			Data new_data=new Data(Integer.parseInt(attributes[0]),attributes[1],attributes[2],attributes[3]);
			data.add(new_data);
		}
		return data;
	}
	
	public static List<Data> getTrainNumber(String name){
		List<Data> data = getData();
		return data.stream().filter(d->d.getTrain_name().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
	}
	
	public static List<Data> getTrainName(String number){
		List<Data> data = getData();
		return data.stream().filter(d->d.getTrain_number().equals(number)).collect(Collectors.toList());
	}
}
