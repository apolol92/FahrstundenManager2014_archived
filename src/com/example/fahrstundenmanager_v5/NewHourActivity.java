package com.example.fahrstundenmanager_v5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class NewHourActivity extends Activity{
	public int id, selection;
	String selected_date;
	Spinner spDay, spMonth, spYear, spDriveTyp, spHours;
	ImageView ivSmiley, ivNormaly, ivSolala, ivWorry;
	private Button btAdd;
	
	
	public void init() {
		this.selection = -1;
		this.spDay = (Spinner)findViewById(R.id.spDayHour);
		this.spDay.setSelection(DConverter.getDay());
		this.spMonth = (Spinner)findViewById(R.id.spMonthHour);
		this.spMonth.setSelection(DConverter.getMonth());
		this.spYear = (Spinner)findViewById(R.id.spYearHour);
		this.spYear.setSelection(DConverter.getYear()-2011);
		this.spDriveTyp = (Spinner)findViewById(R.id.spDriveTyp);
		this.spHours = (Spinner)findViewById(R.id.spHours);
		this.ivSmiley = (ImageView)findViewById(R.id.ivSmiley);
		this.ivSmiley.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selection = 1;
				ivWorry.setImageResource(R.drawable.worry);
				ivSolala.setImageResource(R.drawable.solala);
				ivNormaly.setImageResource(R.drawable.normaly);
				ivSmiley.setImageResource(R.drawable.smileyselected);
			}
		});
		this.ivNormaly = (ImageView)findViewById(R.id.ivNormaly);
		this.ivNormaly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selection = 2;
				ivWorry.setImageResource(R.drawable.worry);
				ivSolala.setImageResource(R.drawable.solala);
				ivNormaly.setImageResource(R.drawable.normalyselected);
				ivSmiley.setImageResource(R.drawable.smiley);
			}
		});
		this.ivSolala = (ImageView)findViewById(R.id.ivSolala);
		this.ivSolala.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selection = 3;
				ivWorry.setImageResource(R.drawable.worry);
				ivSolala.setImageResource(R.drawable.solalaselected);
				ivNormaly.setImageResource(R.drawable.normaly);
				ivSmiley.setImageResource(R.drawable.smiley);
			}
		});
		this.ivWorry = (ImageView)findViewById(R.id.ivWorry);
		this.ivWorry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selection = 4;
				ivWorry.setImageResource(R.drawable.worryselected);
				ivSolala.setImageResource(R.drawable.solala);
				ivNormaly.setImageResource(R.drawable.normaly);
				ivSmiley.setImageResource(R.drawable.smiley);
			}
		});
		this.btAdd = (Button)findViewById(R.id.btAddHour);
		this.btAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selection != -1) {
				//Get data
				HourInfo newHourInfo = new HourInfo();
				newHourInfo.id = -1;
				newHourInfo.lessonid = NewHourActivity.this.id;
				newHourInfo.date = spDay.getSelectedItem().toString() + "-" + spMonth.getSelectedItem().toString() + "-" + spYear.getSelectedItem().toString();
				newHourInfo.typ = spDriveTyp.getSelectedItem().toString();
				newHourInfo.length = Integer.parseInt(spHours.getSelectedItem().toString());
				newHourInfo.emo = selection;
				//Add Hour to table
				LessonManager AddHour = new LessonManager(NewHourActivity.this);
				AddHour.insert_hour(newHourInfo);
				//Finish Activity
				finish();				
				}
			}
		});
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		this.id = extras.getInt("id");
		setContentView(R.layout.activity_newhour);
		init();
	}

}
