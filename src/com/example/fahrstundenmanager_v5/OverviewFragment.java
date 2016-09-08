package com.example.fahrstundenmanager_v5;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class OverviewFragment extends Fragment {
	public int id;
	LinearLayout l;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.overview_fragment_container,
				container, false);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		l = (LinearLayout) getView();
		LessonManager Reader = new LessonManager(getActivity());
		LessonInfo lInfo = Reader.read_lessonInfo(id);
		List<HourInfo> listHourInfo = Reader.read_all_hours();
		if (listHourInfo != null) {
			l.removeAllViews();
			OverviewData OvData = new OverviewData(lInfo, listHourInfo);
			LinearLayout ovLayout = new LinearLayout(getActivity());
			
			TableLayout table_layout = new TableLayout(getActivity());
			TableRow tr1 = new TableRow(getActivity());
			TextView tvBegan = new TextView(getActivity());
			tvBegan.setText("Begonnen:");
			tr1.addView(tvBegan);
			TextView tvBegan2 = new TextView(getActivity());
			tvBegan2.setText(lInfo.date);
			tr1.addView(tvBegan2);
			table_layout.addView(tr1);
			
			
			
			TableRow tr3 = new TableRow(getActivity());
			TextView tvTotalNormalHours = new TextView(getActivity());
			tvTotalNormalHours.setText("Anzahl Übungsfahrten:");
			tr3.addView(tvTotalNormalHours);
			TextView tvTotalNormalHours2 = new TextView(getActivity());
			tvTotalNormalHours2.setText(OvData.total_normals+"("+OvData.normal_costs+"€)");
			tr3.addView(tvTotalNormalHours2);
			table_layout.addView(tr3);
			
			TableRow tr4 = new TableRow(getActivity());
			TextView tvTotalRoadHours = new TextView(getActivity());
			tvTotalRoadHours.setText("Anzahl Überlandfahrten:");
			tr4.addView(tvTotalRoadHours);
			TextView tvTotalRoadHours2 = new TextView(getActivity());
			tvTotalRoadHours2.setText(OvData.total_roads + "(" +OvData.road_costs+"€)");
			tr4.addView(tvTotalRoadHours2);
			table_layout.addView(tr4);
			
			TableRow tr5 = new TableRow(getActivity());
			TextView tvTotalHighwayHours = new TextView(getActivity());
			tvTotalHighwayHours.setText("Anzahl Autobahnfahrten:");
			tr5.addView(tvTotalHighwayHours);
			TextView tvTotalHighwayHours2 = new TextView(getActivity());
			tvTotalHighwayHours2.setText(OvData.total_highways + "(" + OvData.highway_costs+"€)");
			tr5.addView(tvTotalHighwayHours2);
			table_layout.addView(tr5);
			
			TableRow tr6 = new TableRow(getActivity());
			TextView tvTotalNightHours = new TextView(getActivity()); 
			tvTotalNightHours.setText("Anzahl Nachtfahrten:");
			tr6.addView(tvTotalNightHours);
			TextView tvTotalNightHours2 = new TextView(getActivity());
			tvTotalNightHours2.setText(OvData.total_nights + "(" + OvData.night_costs + "€)");
			tr6.addView(tvTotalNightHours2);
			table_layout.addView(tr6);
			
			TableRow tr2 = new TableRow(getActivity());
			TextView tvTotalHours = new TextView(getActivity());
			tvTotalHours.setText("Stunden Gesamt:");
			tr2.addView(tvTotalHours);
			TextView tvTotalHours2 = new TextView(getActivity());
			tvTotalHours2.setText(OvData.total_hours+"("+OvData.total_costs+"€)");
			tr2.addView(tvTotalHours2);
			table_layout.addView(tr2);
			
			TableRow tr7 = new TableRow(getActivity());
			TextView tvTotalEmo = new TextView(getActivity());
			tvTotalEmo.setText("Fahrgefühl:");
			tr7.addView(tvTotalEmo);
			ImageView ivEmo = new ImageView(getActivity());
			if(OvData.average >= 1 && OvData.average < 1.5) {
				ivEmo.setImageResource(R.drawable.smiley);
			}
			else if(OvData.average >= 1.5 && OvData.average < 2.5) {
				ivEmo.setImageResource(R.drawable.normaly);
			}
			if(OvData.average >= 2.5 && OvData.average < 3.5) {
				ivEmo.setImageResource(R.drawable.solala);
			}
			if(OvData.average >= 3.5 && OvData.average <= 4) {
				ivEmo.setImageResource(R.drawable.worry);
			}
			tr7.addView(ivEmo);
			table_layout.addView(tr7);
			l.addView(table_layout);
			
			
			
		}
	}

}
