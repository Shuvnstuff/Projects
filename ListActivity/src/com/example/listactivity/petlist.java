package com.example.listactivity;

import android.app.ListActivity;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class petlist extends ListActivity {
	
	String [] pets;
	
  public void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     String [] pets = {"Dog", "Cat", "Bird", "Hamster","Ferret", "Snake"};
        
      ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, pets);
      setListAdapter(myAdapter);
	}
	protected void onListItemClick(ListView l, View v,int position, long id) {
		
			// get selected value by position
		   String selection = pets[position];  

		   // create new Intent to hold info
		   Intent resultIntent = new Intent();
		  
		   // store result info in Intent  
		   resultIntent.putExtra("pet", selection); 
		  
		   setResult(RESULT_OK,resultIntent);   // send Intent back to Main

		   finish();  // close the ListActivity
	}
}
