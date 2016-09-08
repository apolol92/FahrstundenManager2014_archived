package com.example.fahrstundenmanager_v5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class NewLessonActivity extends Activity {
	Spinner spDay, spMonth, spYear, spDrivertyp;
	Button btAddLesson;
	EditText etTitle, etNormalDrive, etRoadDrive, etHighwayDrive, etNightDrive;

	void init() {
		this.spDay = (Spinner) findViewById(R.id.spDay);
		this.spDay.setSelection(DConverter.getDay());
		this.spMonth = (Spinner) findViewById(R.id.spMonth);
		this.spMonth.setSelection(DConverter.getMonth());
		this.spYear = (Spinner) findViewById(R.id.spYear);
		this.spYear.setSelection(DConverter.getYear()-2011);
		this.spDrivertyp = (Spinner) findViewById(R.id.spDriverTyp);
		this.etTitle = (EditText) findViewById(R.id.etTitle);
		this.etNormalDrive = (EditText) findViewById(R.id.etNormalDrive);
		this.etRoadDrive = (EditText) findViewById(R.id.etRoadDrive);
		this.etHighwayDrive = (EditText) findViewById(R.id.etHighwayDrive);
		this.etNightDrive = (EditText) findViewById(R.id.etNightDrive);
		this.btAddLesson = (Button) findViewById(R.id.btAdd);
		this.btAddLesson.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String title = etTitle.getText().toString();
				String date = spDay.getSelectedItem().toString() + "."
						+ spMonth.getSelectedItem().toString() + "."
						+ spYear.getSelectedItem().toString();
				String drivertyp = spDrivertyp.getSelectedItem().toString();
				try {
					float normalcost = Float.parseFloat(etNormalDrive.getText()
							.toString().replace(",", "."));
					float roadcost = Float.parseFloat(etRoadDrive.getText()
							.toString().replace(",", "."));
					float highwaycost = Float.parseFloat(etHighwayDrive
							.getText().toString().replace(",", "."));
					float nightcost = Float.parseFloat(etNightDrive.getText()
							.toString().replace(",", "."));
					//Füge Unterricht hinzu
					LessonManager AddLesson = new LessonManager(getApplicationContext());
					AddLesson.insert_lessonInfo(new LessonInfo(-1, title, date, drivertyp, normalcost, roadcost, highwaycost, nightcost));
					
					
					finish();
				} catch (Exception ex) {

				}

			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newlesson);
		init();

	}

}
