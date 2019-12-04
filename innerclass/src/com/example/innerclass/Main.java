package com.example.innerclass;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.annotation.SuppressLint;
import android.app.*;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.*;

@SuppressLint("NewApi")
public class Main extends Activity {
	TextView count;
	int num = 0;
   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button add = (Button)findViewById(R.id.button1);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				count = (TextView)findViewById(R.id.textView1);
				num++;
				count.setText(Integer.toString(num));
			}
			
		});
		
		Button min = (Button)findViewById(R.id.button2);
		min.setOnClickListener(new minBttn());
		
		Button color = (Button)findViewById(R.id.button3);
		color.setOnClickListener(new OnClickListener());
		
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class minBttn implements OnClickListener{
		@SuppressLint("NewApi")
		@Override
		public void onClick(View arg0) {
			count = (TextView)findViewById(R.id.textView1);
			num--;
			count.setText(Integer.toString(num));
			
		}
	}
	
	@SuppressLint("NewApi")
	public static class myDialog extends DialogFragment{
		String[] colors = {"red", "blue", "green"};
		
		@SuppressLint("NewApi")
		public Dialog onCreateDialog(Bundle savedInstanceState)
		{
		   AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		   builder.setTitle("Choose your color");
		   builder.setCancelable(true);  
		   builder.setSingleChoiceItems(colors, -1, new android.content.DialogInterface.OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(DialogInterface arg0, int id) {
				Main myActivity = (Main)getActivity();
				 RelativeLayout layout = (RelativeLayout)myActivity.findViewById(R.id.layouts);
				 String color = colors[id];
				 if (color == "red") {
					 layout.setBackgroundColor(Color.RED);
				 }
				 else if (color == "green") {
					 layout.setBackgroundColor(Color.GREEN);
				 }
				 else if (color == "blue"){
					 layout.setBackgroundColor(Color.BLUE);
				 }
				
			}
			   
		   });
		   /*
		   builder.setItems(colors, new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Main myActivity = (Main)getActivity();
					RelativeLayout layout = (RelativeLayout)myActivity.findViewById(R.id.layouts);
					layout.setBackgroundColor(Color.BLUE);
					
				}
				   
			   });
		   builder.setNeutralButton("Green", new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Main myActivity = (Main)getActivity();
					 RelativeLayout layout = (RelativeLayout)myActivity.findViewById(R.id.layouts);
					 layout.setBackgroundColor(Color.GREEN);
					
				}
				   
			   });
		   */
		return builder.create();
		   
		
		}
	}

}
