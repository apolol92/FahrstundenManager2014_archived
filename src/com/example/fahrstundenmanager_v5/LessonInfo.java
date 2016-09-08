package com.example.fahrstundenmanager_v5;

public class LessonInfo {
	public int id,lessonid;
	public String title, date, license;
	public float normal_cost, road_cost, highway_cost, night_cost;

	public LessonInfo() {
		
	}
	
	public LessonInfo(int id, String title, String date, String license,
			float nc, float rc, float hc, float nic) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.license = license;
		this.normal_cost = nc;
		this.road_cost = rc;
		this.highway_cost = hc;
		this.night_cost = nic;
	}
	
	

}
