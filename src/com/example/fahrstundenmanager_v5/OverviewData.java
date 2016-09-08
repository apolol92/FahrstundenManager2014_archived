package com.example.fahrstundenmanager_v5;

import java.util.List;

import android.text.InputFilter.LengthFilter;

public class OverviewData {
	LessonInfo lesson_info;
	List<HourInfo> listHourInfos;
	public int total_hours, total_normals, total_roads, total_highways, total_nights,
			total_emo;
	public float total_costs, normal_costs, road_costs, highway_costs, night_costs,
			emo;
	public float average;

	public OverviewData(LessonInfo lInfo, List<HourInfo> listHourInfos) {
		// TODO Auto-generated constructor stub
		this.lesson_info = lInfo;
		this.listHourInfos = listHourInfos;
		this.total_hours = 0;
		this.total_costs = 0;
		this.total_normals = 0;
		this.normal_costs = 0;
		this.total_roads = 0;
		this.road_costs = 0;
		this.total_highways = 0;
		this.highway_costs = 0;
		this.total_nights = 0;
		this.night_costs = 0;
		this.total_emo = 0;
		this.emo = 0;
		this.average = 0;
		calc_data();
	}

	private void calc_data() {
		for (HourInfo h : this.listHourInfos) {
			if (h.lessonid == this.lesson_info.id) {
				this.total_hours += h.length;
				this.total_emo++;
				this.emo += h.emo;
				if (h.typ.compareTo("Übungsfahrten") == 0) {
					this.total_costs += h.length * lesson_info.normal_cost;
					this.normal_costs += h.length * lesson_info.normal_cost;
					this.total_normals += h.length;
				} else if (h.typ.compareTo("Überlandfahrten") == 0) {
					this.total_costs += h.length * lesson_info.road_cost;
					this.road_costs += h.length * lesson_info.road_cost;
					this.total_roads += h.length;
				} else if (h.typ.compareTo("Autobahnfahrten") == 0) {
					this.total_costs += h.length * lesson_info.highway_cost;
					this.highway_costs += h.length * lesson_info.highway_cost;
					this.total_highways += h.length;
				} else if (h.typ.compareTo("Nachtfahrten") == 0) {
					this.total_costs += h.length * lesson_info.night_cost;
					this.night_costs += h.length * lesson_info.night_cost;
					this.total_nights += h.length;
				}
				else {
										
				}
				this.average = this.emo / this.total_emo;
			}
			
		}
	}

}
