package com.example.fahrstundenmanager_v5;


import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;

public class DConverter {

	public static int getDay() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	public static int getMonth() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		return month;
	}
	
	public static int getYear() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		return year;
	}

	public static int get_drivertyp_pos(String typ, Activity act) {
		int i = 0;
		String[] typs = act.getResources().getStringArray(R.array.drivertyps);
		for (String t : typs) {
			if (t.compareTo(typ) == 0) {
				return i;
			}
			i++;
		}
		return 0;
	}

	public static List<HourInfo> sort_dates(List<HourInfo> list) {
		for(int i = 0; i < list.size(); i++) {
			try {
			String[] splits = list.get(i).date.split("-");
			list.get(i).day = Integer.parseInt(splits[0]);
			list.get(i).month = Integer.parseInt(splits[1]);
			list.get(i).year = Integer.parseInt(splits[2]);
			}
			catch(Exception ex) {
				
			}
		}
		HourInfo[] array = new HourInfo[list.size()];
		list.toArray(array);
		for (int i = 0; i < array.length; i++) {
			for (int d = 0; d < array.length; d++) {
				if (d != i) {
					//Wenn Jahre verschieden
					if (array[i].year > array[d].year) {
						HourInfo buf = new HourInfo();
						buf = array[i];
						array[i] = array[d];
						array[d] = buf;
					} 
					//Wenn Monate verschieden
					else if (array[i].year == array[d].year && array[i].month != array[d].month) {
						// Sort month
						if (array[i].month > array[d].month) {
							HourInfo buf = new HourInfo();
							buf = array[i];
							array[i] = array[d];
							array[d] = buf;
						}
					}
					//Wenn Tage veschieden
					else if (array[i].year == array[d].year && array[i].month == array[d].month) {
						// Sort month
						if (array[i].day > array[d].day) {
							HourInfo buf = new HourInfo();
							buf = array[i];
							array[i] = array[d];
							array[d] = buf;
						}
					}
					
				}
			}
		}
		list = Arrays.asList(array);
		return list;
	}
}