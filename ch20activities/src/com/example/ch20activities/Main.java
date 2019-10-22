package com.example.ch20activities;




import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;

public class Main extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button myButton = (Button)findViewById(R.id.button1);
		myButton.setOnClickListener(this);
		
		Button myButton2 = (Button)findViewById(R.id.button2);
		myButton2.setOnClickListener(this);
		
		Button myButton3 = (Button)findViewById(R.id.button3);
		myButton3.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
	// when the button is clicked it will take you to the next Activity.
			int id = v.getId();
			if (id == R.id.button1) {
					
			startActivityOSVersion(0);
				
			}
			if (id == R.id.button2) {
				
				startActivityEmulatorTime(0);
					
			}
			if (id == R.id.button3) {
				
				startActivityColors(0);
					
			}
	}
	
	
	public void startActivityOSVersion(int requestCode) {
	// when the button is clicked it will take you to the next Activity.
		
			Intent myIntent = new Intent(Main.this,Second.class);
			myIntent.putExtra("build", android.os.Build.VERSION.RELEASE);
			startActivity(myIntent);
		
		
		
	}
	
	public void startActivityEmulatorTime(int requestCode) {
		
		Intent theIntent = new Intent(Main.this,Second.class);
		theIntent.putExtra("ETime", android.os.SystemClock.uptimeMillis());
		startActivity(theIntent);
		
	}
	
	public void startActivityColors(int requestCode) {
		
		Intent myIntent = new Intent(Main.this,Third.class);
		startActivity(myIntent);
		onResult(0,0,myIntent);
	}

protected void onResult(int requestCode, int resultCode, Intent data) {
		
		Bundle bun = data.getExtras();
		String colorvalue = bun.getString("name");
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Your Favorite Color is: " + colorvalue);
				
		
	}
 }
	
	



