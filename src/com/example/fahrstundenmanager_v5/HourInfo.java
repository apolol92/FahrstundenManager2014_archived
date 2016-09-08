package com.example.fahrstundenmanager_v5;

public class HourInfo {
	public String date, typ;
	public int day,month,year;
	public int id, length, emo;
	public int lessonid;
	
	public HourInfo() {
		
	}
	
	public HourInfo(int lessonid, String date, String typ, int id, int length, int emo) {
		this.lessonid = lessonid;
		this.date = date;
		this.typ = typ;
		this.id = id;
		this.length = length;
		this.emo = emo;
		
	}

}
