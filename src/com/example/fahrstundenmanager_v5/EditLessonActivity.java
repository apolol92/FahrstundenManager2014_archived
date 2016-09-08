package com.example.fahrstundenmanager_v5;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditLessonActivity extends Activity {



	Spinner spDay, spMonth, spYear, spDrivertyp;
	Button btEditLesson;
	EditText etEditTitle, etEditNormalDrive, etEditRoadDrive,
			etEditHighwayDrive, etEditNightDrive;
	String title;
	LessonInfo MyLesson;
	int id,lesson_id;

	void init() {
		MyLesson = new LessonInfo();
		Bundle extras = getIntent().getExtras();
		id = extras.getInt("id");
		LessonManager LessonReader = new LessonManager(getApplicationContext());
		LessonInfo Info = LessonReader.read_lessonInfo(this.id);
		MyLesson= Info;
		String splits[] = Info.date.split(".");
		this.spDay = (Spinner) findViewById(R.id.spEditDay);
		this.spDay.setSelection(DConverter.getDay());
		//this.spDay.setSelection(Integer.parseInt(splits[0]));
		this.spMonth = (Spinner) findViewById(R.id.spEditMonth);
		this.spMonth.setSelection(DConverter.getMonth());
		//this.spMonth.setSelection(DConverter.convert_month_to_number(splits[1],
				//this));
		this.spYear = (Spinner) findViewById(R.id.spEditYear);
		this.spYear.setSelection(DConverter.getYear()-2011);
		//this.spYear.setSelection(DConverter.get_year_pos(splits[2], this));
		this.spDrivertyp = (Spinner) findViewById(R.id.spEditDriverTyp);
		this.spDrivertyp.setSelection(DConverter.get_drivertyp_pos(Info.license,
				this));
		this.etEditTitle = (EditText) findViewById(R.id.etEditTitle);
		this.etEditTitle.setHint(Info.title);
		this.etEditNormalDrive = (EditText) findViewById(R.id.etEditNormalDrive);
		this.etEditNormalDrive.setHint(Info.normal_cost + "");
		this.etEditRoadDrive = (EditText) findViewById(R.id.etEditRoadDrive);
		this.etEditRoadDrive.setHint(Info.road_cost + "");
		this.etEditHighwayDrive = (EditText) findViewById(R.id.etEditHighwayDrive);
		this.etEditHighwayDrive.setHint(Info.highway_cost + "");
		this.etEditNightDrive = (EditText) findViewById(R.id.etEditNightDrive);
		this.etEditNightDrive.setHint(Info.night_cost + "");
		this.btEditLesson = (Button) findViewById(R.id.btEdit);
		this.btEditLesson.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String date = spDay.getSelectedItem().toString() + "."
						+ spMonth.getSelectedItem().toString() + "."
						+ spYear.getSelectedItem().toString();
				float ndrive, rdrive, hdrive, nightdrive;
				LessonInfo UpdatedLessonInfo = new LessonInfo();
				UpdatedLessonInfo.id = id;
				UpdatedLessonInfo.lessonid = lesson_id;
				if(etEditTitle.getText().toString().compareTo(MyLesson.title)!=0&&etEditTitle.getText().toString().trim().length()>0) {
					UpdatedLessonInfo.title = etEditTitle.getText().toString();
				}
				else {
					UpdatedLessonInfo.title = MyLesson.title;
				}
				if(date.compareTo(MyLesson.date)!=0) {
					UpdatedLessonInfo.date = date;
				}
				else {
					UpdatedLessonInfo.date = MyLesson.date;
				}
				if(MyLesson.license.compareTo(spDrivertyp.getSelectedItem().toString())!=0) {
					UpdatedLessonInfo.license = spDrivertyp.getSelectedItem().toString();
				}
				else {
					UpdatedLessonInfo.license = MyLesson.license;
				}
				if(etEditNormalDrive.getText().toString().trim().length()>0) {
					try {
						ndrive = Float.parseFloat(etEditNormalDrive.getText().toString().trim());
						UpdatedLessonInfo.normal_cost = ndrive;
					}
					catch(Exception ex) {
						UpdatedLessonInfo.normal_cost = MyLesson.normal_cost;
					}
				}
				else {
					UpdatedLessonInfo.normal_cost = MyLesson.normal_cost;
				}
				if(etEditRoadDrive.getText().toString().trim().length()>0) {
					try {
						rdrive = Float.parseFloat(etEditRoadDrive.getText().toString().trim());
						UpdatedLessonInfo.road_cost = rdrive;
					}
					catch(Exception ex) {
						UpdatedLessonInfo.road_cost = MyLesson.road_cost;
					}
				}
				else {
					UpdatedLessonInfo.road_cost = MyLesson.road_cost;
				}
				if(etEditHighwayDrive.getText().toString().trim().length()>0) {
					try {
						hdrive = Float.parseFloat(etEditHighwayDrive.getText().toString().trim());
						UpdatedLessonInfo.highway_cost = hdrive;
					}
					catch(Exception ex) {
						UpdatedLessonInfo.highway_cost = MyLesson.highway_cost;
					}
				}
				else {
					UpdatedLessonInfo.highway_cost = MyLesson.highway_cost;
				}
				if(etEditNightDrive.getText().toString().trim().length()>0) {
					try {
						nightdrive = Float.parseFloat(etEditNightDrive.getText().toString().trim());
						UpdatedLessonInfo.night_cost = nightdrive;
					}
					catch(Exception ex) {
						UpdatedLessonInfo.night_cost = MyLesson.night_cost;
					}
				}
				else {
					UpdatedLessonInfo.night_cost = MyLesson.night_cost;
				}
				LessonManager LessonUpdater = new LessonManager(getApplicationContext());
				LessonUpdater.update_lessoninfo(id, UpdatedLessonInfo);				
				finish();
			}

		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if(extras!=null) {
			this.title = extras.getString("title");
		}
		setContentView(R.layout.activity_editlesson);
		
		init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menu_inflater = getMenuInflater();
		menu_inflater.inflate(R.menu.editlesson_actionbar_items, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.remove_action:
			LessonManager LessonDeleter = new LessonManager(this);
			int i = 0;
			HourInfo hBuffer;
			List<HourInfo>listHours = LessonDeleter.read_all_hours();
			if(listHours!=null) {
				for(HourInfo h : listHours) {
					if(h.lessonid == id) {
						LessonDeleter.delete_hourinfo(h.id);
					}
				}
			}
			LessonDeleter.delete_lessonInfo(id);
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
