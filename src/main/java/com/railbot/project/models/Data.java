package com.railbot.project.models;

public class Data {
		private int train_number;
		private String train_name;
		private String train_source_stn;
		private String train_dest_stn;
		
		public Data(int train_number,String train_name,String train_source_stn,String train_dest_stn){
			super();
			this.train_number=train_number;
			this.train_name=train_name;
			this.train_source_stn=train_source_stn;
			this.train_dest_stn=train_dest_stn;
		}
		
		
		
		
		public String getTrain_number() {
			return String.valueOf(train_number);
		}
		public void setTrain_number(int train_number) {
			this.train_number = train_number;
		}
		public String getTrain_name() {
			return train_name;
		}
		public void setTrain_name(String train_name) {
			this.train_name = train_name;
		}
		public String getTrain_source_stn() {
			return train_source_stn;
		}
		public void setTrain_source_stn(String train_source_stn) {
			this.train_source_stn = train_source_stn;
		}
		public String getTrain_dest_stn() {
			return train_dest_stn;
		}
		public void setTrain_dest_stn(String train_dest_stn) {
			this.train_dest_stn = train_dest_stn;
		}
}
