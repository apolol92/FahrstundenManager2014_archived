package com.example.fahrstundenmanager_v5;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DriveView {
	int id;
	int i;
	Activity main;
	LinearLayout l;
	List<HourInfo> listHours;
	public DriveView(Activity activity, LinearLayout l, List<HourInfo> listHours, int id) {
		// TODO Auto-generated constructor stub
		this.main = activity;
		this.l = l;
		this.listHours = new ArrayList<HourInfo>();
		for(HourInfo h : listHours) {
			if(h.lessonid == id) {
				this.listHours.add(h);
			}
		}
		this.id = id;
	}
	
	public void create_view() {
		i = 0;
		TableLayout table = new TableLayout(this.main);
		LinearLayout.LayoutParams layoutParams0 = new LinearLayout.LayoutParams(
			     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		table.setLayoutParams(layoutParams0);
		this.listHours = DConverter.sort_dates(this.listHours);
		for(final HourInfo h : this.listHours) {
			TableRow tr2 = new TableRow(main);
			TextView tv = new TextView(main);
			tv.setText("h");
			tv.setTextColor(0x00ffffff);
			tr2.addView(tv);
			table.addView(tr2);
			TableRow tr = new TableRow(this.main);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			layoutParams.setMargins(10, 10, 10, 10);
			tr.setLayoutParams(layoutParams);
			if(h.typ.compareTo("Übungsfahrt")==0) {
				tr.setBackgroundColor(0xff4f89f2);
			}
			else {
				tr.setBackgroundColor(0xffff4e52);
			}
			
			ImageView ivEmo = getRightImage(h.emo);
			tr.addView(ivEmo);
			
			TextView tvDate = new TextView(this.main);
			tvDate.setPadding(50, 0, 0, 0);
			tvDate.setTextSize(30);
			tvDate.setText(h.date);
			tr.addView(tvDate);
			
			TextView tvTyp = new TextView(this.main);
			tvTyp.setText(h.typ);
			tvTyp.setPadding(50, 0, 0, 0);
			tvTyp.setTextSize(30);
			tr.addView(tvTyp);
			
			TextView tvLength = new TextView(this.main);
			tvLength.setText(h.length+"h");
			tvLength.setTextSize(30);
			tvLength.setPadding(50, 0, 0, 0);
			tr.addView(tvLength);
			
			tr.setOnLongClickListener(new OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View arg0) {
					// TODO Auto-generated method stub
					//LessonManager Deleter = new LessonManager(main);
					//Deleter.delete_hourinfo(h.id);
					Intent MyIntent = new Intent(main,EditHourActivity.class);
					MyIntent.putExtra("hourid", h.id);
					main.startActivity(MyIntent);
					return false;
				}
			});
			table.addView(tr);
			i++;
		}
		this.l.addView(table);
		
	}
	
	public ImageView getRightImage(int i) {
		ImageView iv = new ImageView(this.main);
		switch(i) {
		case 1:
			iv.setImageResource(R.drawable.smiley);
			break;
		case 2:
			iv.setImageResource(R.drawable.normaly);
			break;
		case 3:
			iv.setImageResource(R.drawable.solala);
			break;
		case 4:
			iv.setImageResource(R.drawable.worry);
			break;
		}
		return iv;
	}

}
