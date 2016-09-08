package com.example.fahrstundenmanager_v5;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class EditHourActivity extends Activity{
	int hourid;
	HourInfo current_hour;
	void init() {
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edithour);
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			hourid = extras.getInt("hourid");
			LessonManager Reader = new LessonManager(getApplicationContext());
			current_hour = Reader.read_hourinfo(hourid);
		}
		init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menu_inflater = getMenuInflater();
		menu_inflater.inflate(R.menu.editlesson_actionbar_items, menu);	//Same actionbar..
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.remove_action:
			LessonManager Deleter = new LessonManager(getApplicationContext());
			Deleter.delete_hourinfo(current_hour.id);
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
