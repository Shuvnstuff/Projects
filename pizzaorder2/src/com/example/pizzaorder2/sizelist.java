package com.example.pizzaorder2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class sizelist extends ListActivity {
	String[] sizelist = {"small","medium","large","party"};
	
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, sizelist);
	      setListAdapter(myAdapter);
	}
	protected void onListItemClick(ListView l, View v,int position, long id) {
		// get selected value by position
		   String selection = sizelist[position];  

		   // create new Intent to hold info
		   Intent resultIntent = new Intent();
		  
		   // store result info in Intent  
		   resultIntent.putExtra("size", selection); 
		  
		   setResult(RESULT_OK,resultIntent);   // send Intent back to Main

		   finish();  // close the ListActivity
	
	}
	
	
	
}
