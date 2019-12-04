package com.example.dateandtime;

import android.os.Build;
import android.os.Bundle;

import java.util.Calendar;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.*;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.content.DialogInterface;


@SuppressLint("NewApi")
public class Main extends Activity {
	Button date;
	Button time;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		date = (Button)findViewById(R.id.dateBttn);
		date.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new Datedialog();
				newFragment.show(getFragmentManager(), "MyAlertDialog");
				
			}
			
		});
		
		time = (Button)findViewById(R.id.timeBttn);
		time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DialogFragment newFragment = new Timedialog();
				newFragment.show(getFragmentManager(), "MyAlertDialog");
				
			}
			
		});
	}

	@SuppressLint("NewApi")
	public static class Datedialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

		@SuppressLint("NewApi")
		public Dialog onCreateDialog(Bundle savedInstancedState) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			
			return new DatePickerDialog(getActivity(), this, year, month, day);
			
		}
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			Main myActivity = (Main)getActivity();
			TextView datetv = (TextView)myActivity.findViewById(R.id.datetv);
			datetv.setText(year + "/" + Integer.toString(monthOfYear+1) + "/" + dayOfMonth);
			
		}
		
	}
	
	public static class Timedialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
		public Dialog onCreateDialog(Bundle savedInstancedState) {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		
		return new TimePickerDialog(getActivity(), this, hour, minute, false);
		}

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			Main myActivity = (Main)getActivity();
			TextView myView = (TextView)myActivity.findViewById(R.id.timetv);
			myView.setText(hourOfDay + ":" + minute);
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
