package com.example.fahrstundenmanager_v5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LessonView extends LinearLayout {
	LayoutParams params;
	LessonInfo lesson_info;
	Activity main;

	public LessonView(Activity ac, Context context, LessonInfo lInfo) {
		super(context);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(10, 5, 10, 5);
		this.setBackgroundColor(0xffffffff);
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setLayoutParams(params);
		this.setPadding(5, 5, 5, 5);
		this.lesson_info = lInfo;
		this.main = ac;
	}

	public void create() {
		create_iv();
		create_extras();
		// TODO EVENT
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Open Lesson
				Intent MyIntent = new Intent(main, LessonActivity.class);
				MyIntent.putExtra("id", lesson_info.id);
				main.startActivity(MyIntent);

			}
		});
		this.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				LessonView.this.setBackgroundColor(Color.BLUE);
				// Open LessonEdit
				Intent MyIntent = new Intent(main, EditLessonActivity.class);
				MyIntent.putExtra("id", lesson_info.id);
				main.startActivity(MyIntent);
				LessonView.this.setBackgroundColor(0xffffffff);
				return false;
			}
		});

	}


	private void create_iv() {
		ImageView iv = new ImageView(this.main);
		if (this.lesson_info.license.compareTo("Klasse A") == 0) {
			iv.setImageResource(R.drawable.a);

		} else if (this.lesson_info.license.compareTo("Klasse A1") == 0) {
			iv.setImageResource(R.drawable.a1xcf);
		} else if (this.lesson_info.license.compareTo("Klasse A2") == 0) {
			iv.setImageResource(R.drawable.a2xcf);
		}
		else if (this.lesson_info.license.compareTo("Klasse B") == 0) {
			iv.setImageResource(R.drawable.b);
		}
		else if (this.lesson_info.license.compareTo("Klasse BE") == 0) {
			iv.setImageResource(R.drawable.bexcf);
		}
		this.addView(iv);

	}

	private void create_extras() {
		// TODO Auto-generated method stub
		LinearLayout sub = new LinearLayout(this.main);
		sub.setOrientation(LinearLayout.VERTICAL);
		TextView tvTitle = new TextView(this.main);
		tvTitle.setTypeface(null, Typeface.BOLD);
		tvTitle.setTextSize(30);
		tvTitle.setText(this.lesson_info.title);
		sub.addView(tvTitle);
		TextView tvBegan = new TextView(this.main);
		tvBegan.setPadding(0, 5, 0, 0);
		tvBegan.setText("Begonnen: " + this.lesson_info.date);
		tvBegan.setTextColor(Color.LTGRAY);
		sub.addView(tvBegan);
		this.addView(sub);
	}	

}
