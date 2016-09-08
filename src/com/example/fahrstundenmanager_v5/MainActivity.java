package com.example.fahrstundenmanager_v5;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	LinearLayout svMainLinearLayout;

	void init() {
		this.svMainLinearLayout = (LinearLayout) findViewById(R.id.svMainLinearLayout);
	}

	public void read_all_lessons() {
		int id = 1;
		LessonManager LessonReader = new LessonManager(getApplicationContext());
		List<LessonInfo> listLessonInfos = LessonReader.read_allLessonInfos();
		if (listLessonInfos != null) {
			for (LessonInfo l : listLessonInfos) {
				LessonView lView = new LessonView(this,
						getApplicationContext(), l);
				lView.create();
				this.svMainLinearLayout.addView(lView);
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		read_all_lessons();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.svMainLinearLayout.removeAllViews();
		read_all_lessons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menu_inflater = getMenuInflater();
		menu_inflater.inflate(R.menu.main_actionbar_items, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_new_lesson:
			Intent NewLesson = new Intent(this, NewLessonActivity.class);
			startActivity(NewLesson);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
