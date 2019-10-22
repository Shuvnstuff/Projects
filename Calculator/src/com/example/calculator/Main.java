package com.example.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.Random;
import java.util.ArrayList;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import java.util.Random;

public class Main extends Activity implements OnClickListener {

	
	
	
	Button Button1,Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button0,
	 Buttonadd, Buttonsub, Buttondivide, Buttonmul, Buttonequal, Buttonclr;
	
	double ans, num1, num2;
	
	TextView numholder;
	
	String numberstring;
	String operator = "";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button Button1 = (Button)findViewById(R.id.button1);
		Button1.setOnClickListener(this);
		Button Button2 = (Button)findViewById(R.id.button2);
		Button2.setOnClickListener(this);
		Button Button3 = (Button)findViewById(R.id.button3);
		Button3.setOnClickListener(this);
		Button Button4 = (Button)findViewById(R.id.button4);
		Button4.setOnClickListener(this);
		Button Button5 = (Button)findViewById(R.id.button5);
		Button5.setOnClickListener(this);
		Button Button6 = (Button)findViewById(R.id.button6);
		Button6.setOnClickListener(this);
		Button Button7 = (Button)findViewById(R.id.button7);
		Button7.setOnClickListener(this);
		Button Button8 = (Button)findViewById(R.id.button8);
		Button8.setOnClickListener(this);
		Button Button9 = (Button)findViewById(R.id.button9);
		Button9.setOnClickListener(this);
		Button Button0 = (Button)findViewById(R.id.button0);
		Button0.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		int id = v.getId();
		if (id == R.id.button1) {
				String x=numholder.getText().toString();
				if(x.equals("|")) {
					numberstring = "1";
				}
				else {
					numberstring += 1;
				}
				numholder.setText(numberstring);
			}
		
		
		
		
		
	}

}
