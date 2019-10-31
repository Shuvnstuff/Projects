package com.example.listactivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Main extends Activity implements OnClickListener{
	Button listBttn;
	TextView tv;
	String [] pets = {"Dog", "Cat", "Bird", "Hamster","Ferret", "Snake"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listBttn = (Button)findViewById(R.id.button1);
		listBttn.setOnClickListener(this);
		
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,pets);
		
		Spinner spn = (Spinner)findViewById(R.id.MySpinner);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn.setAdapter(myAdapter);
		spn.setSelection(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v == listBttn) {
			Intent intent = new Intent(Main.this,petlist.class);
			startActivityForResult(intent,0);
			Spinner spn = (Spinner)findViewById(R.id.MySpinner);
			int pos = spn.getSelectedItemPosition();
			String selected = pets[pos];
			String strSelected = spn.getItemAtPosition(pos).toString();
			
			TextView tv = (TextView)findViewById(R.id.textView1);
		}
		
	}
	protected void onActivityResult(int requestCode,int resultCode, Intent data) {
		Bundle bun = data.getExtras();
		String pet = bun.getString("pet");
		
		if(pet != null) {
		tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Recieved back "+pet);
		}
	}
}
