package com.example.ch20activities;



import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;

public class Second extends Activity{
	

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		Intent myIntent = getIntent();
		
		Bundle bun = myIntent.getExtras();
		
		String Value = bun.getString("build");
		
		if("build" != null) {
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("The Android OS version for this emulator is: " + Value);
		
		
			
		
		}
		
		
	
	
	}

}
