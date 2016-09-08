package com.example.fahrstundenmanager_v5;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DriveFragment extends Fragment {
	public int id;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater
				.inflate(R.layout.drive_fragment_container, container,false);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		LinearLayout l = (LinearLayout)getView();
		LessonManager MyReader = new LessonManager(getActivity());
		List<HourInfo> listHours = new ArrayList<HourInfo>();
		listHours = MyReader.read_all_hours();
		if(listHours!=null) {
			l.removeAllViews();
			DriveView MyDriveView = new DriveView(this.getActivity(),l,listHours,this.id);
			MyDriveView.create_view();
			
		}
	}
}
