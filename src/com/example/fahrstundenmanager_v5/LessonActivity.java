package com.example.fahrstundenmanager_v5;




import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LessonActivity extends Activity {

	private int id;

	void init() {
		ActionBar ab = getActionBar();
		// Wir wollen Tabs verwenden..
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Tabs initialisieren
		Tab OverviewTab = ab.newTab().setText("Übersicht");
		Tab NormaleFahrtenTab = ab.newTab().setText("Fahrten");
		//Tab SonderFahrtenTab = ab.newTab().setText("Sonderfahrten");
		ab.setStackedBackgroundDrawable(new ColorDrawable(0xfff5f5f5)); 
		//Fragment erzeugen
		OverviewFragment MyOverviewFragment = new OverviewFragment();
		MyOverviewFragment.id = id;
		DriveFragment MyNormalFahrtenFragment = new DriveFragment();
		MyNormalFahrtenFragment.id = id;
		//Fragment MySonderFahrtenFragment = new SpecialDriveFragment();
		//Tablistener hinzufügen
		OverviewTab.setTabListener(new MyTabListener(MyOverviewFragment));
		NormaleFahrtenTab.setTabListener(new MyTabListener(MyNormalFahrtenFragment));
		//SonderFahrtenTab.setTabListener(new MyTabListener(MySonderFahrtenFragment));
		//Tab hinzufügen
		ab.addTab(OverviewTab);
		ab.addTab(NormaleFahrtenTab);
		//ab.addTab(SonderFahrtenTab);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		this.id = extras.getInt("id");
		setContentView(R.layout.activity_lesson);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menu_inflater = getMenuInflater();
		menu_inflater.inflate(R.menu.lesson_actionbar_items, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.action_new_hour:
			Intent MyIntent = new Intent(this,NewHourActivity.class);
			MyIntent.putExtra("id", this.id);
			startActivity(MyIntent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
