package com.example.wack_a_mole;

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


public class Game extends Activity implements OnClickListener{
	
	
	
	
		// Set up an array of integers to hold the Button IDs of the "moles"
	ArrayList<Integer> myButtonIDs = new ArrayList<Integer>();
		// The Handler will be used to run a timer in our game
	protected Handler taskHandler = new Handler();
		// The isComplete variable will tell us when time is up!
	protected Boolean isComplete = false;
		// We need to know which mole is the currently visible mole
	ImageButton currentMole;
		// Use the current time as the start time for the game
	long startTime = System.currentTimeMillis();
		// Keep track of how many times the user has hit the mole
	int numWhacks = 0;
		// The Following variables are used to configure the game.
		// Establish default game configuration settings here!
	String playerName = "Default"; 
	int difficultylevel = 2;	     // 1 = hard, 2 = medium, 3 = easy
	int numMoles = 8;				// any value between 3 and 8	
	int duration = 20;			   // any value up to 30 seconds
	
	
	
	
	
	/** Called when the activity is first created**/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		//not needed data coming from prefs not intent
		/*
		Bundle bun = getIntent().getExtras();
		playerName = bun.getString("Name");
		difficultylevel = bun.getInt("Dif");
		numMoles = bun.getInt("Moles");
		duration = bun.getInt("Duration");
		*/
		SharedPreferences  prefs = getSharedPreferences("WhackSettings",MODE_PRIVATE);
		playerName = prefs.getString("Name", playerName);
		difficultylevel = prefs.getInt("Dif", difficultylevel);
		numMoles = prefs.getInt("Moles", numMoles);
		duration = prefs.getInt("Duration", duration);
		
		TextView tvName = (TextView)findViewById(R.id.NameSpace);
		tvName.setText("Player Name: " + playerName);
		
		initButtons();
		setNewMole();
		setTimer(difficultylevel * 1000);
		
	}

	
	
	
	
	
	@Override
	public void onClick(View a) {
		
		if(isComplete) {
			return;
		}
		
		if(a == currentMole) {
			numWhacks ++;
			TextView tvWhack = (TextView)findViewById(R.id.Nonedit2);
			tvWhack.setText("Number of Whacks: " + numWhacks);
			setNewMole();
		}
		
	}
	
	
	
	
	
	
		// This method is called when the game is completed
	public void gameOver() {
			
		isComplete = true;
			//Finds the textView1 element in the XML
		TextView tvScore = (TextView)findViewById(R.id.Nonedit2);
			//Sets the now found textView1 to Game Over! Score: + the Number of whacks
		tvScore.setText("Game Over! Score: " + numWhacks);
		Intent gameOverint = new Intent(this, GameOver.class);
		gameOverint.putExtra("Name",playerName);
		gameOverint.putExtra("Score", numWhacks);
		startActivity(gameOverint);
		finish();
		
	}
	
	
	
	
	
	
		//This method will choose a new button as the current mole
	public void setNewMole() {
		
			// Create a random number generator
		Random generator = new Random();
			// Find a random number between 0 and numMoles - 1
		int randomItem = generator.nextInt(numMoles);
			// Use the random number as an index into the array of button IDs
			// The new mole button ID is the element in the array at our random index
		int newButtonId = myButtonIDs.get(randomItem);
		
		
		
			// Make sure there is a current mole, then hide it on the screen
		if (currentMole != null) {
			currentMole.setVisibility(View.INVISIBLE);
		}
		
			// Get the new mole button using the new Button ID value
		ImageButton newMole = (ImageButton)findViewById(newButtonId);
			// Set our new mole to visible on the screen
		newMole.setVisibility(View.VISIBLE);
			// Return the new mole Button object
		currentMole = newMole;
	}
	
	
	
	
	
	
	// This method will retrieve all mole button IDs and place them into
	// our array of integer button IDs.
	public void initButtons() {
		// A viewGroup will allow us to grab all the controls in the current layout
		ViewGroup group = (ViewGroup)findViewById(R.id.GameLayout);
		View v;
		
		// Now we can loop through all the controls and find just the buttons
		for(int i = 0; i < group.getChildCount(); i++) {
			v = group.getChildAt(i);
			
			// if the current control is button
			if (v instanceof ImageButton) {
				v.setOnClickListener(this);
				
				if(!isComplete) {
					myButtonIDs.add(v.getId()); // Add the Button ID to the array
					v.setVisibility(View.INVISIBLE); // Set the Button to invisible
				}
			}
		}
	}
	
	
	
	
	
	
		// This method will create the timer that will allow us to switch current moles
	protected void setTimer(long time) {
	
			// get the time that we want our timer to last form the input parameter
		final long elapse = time;
		
			// Create a new "runnable" task - this will create a timer feature
		Runnable t = new Runnable() {
			public void run() {
			onTimerTask(); // Change the current mole on the screen
			
				// If game is not complete
			if ( !isComplete) {
				// create the new timer task to go off when the next mole should be shown
				taskHandler.postDelayed(this, elapse);
				}
			}

		 };
		
		
			// create the new timer task to go off when the next mole should be shown
		taskHandler.postDelayed(t, elapse);
	}
	
	
	
	
	
	
	
		// This method will change the current mole whenever the timer goes off
	private void onTimerTask() {
		
			// Calculate our ending time based on the duration settings
		long endTime = startTime + (duration * 1000);
			// If the ending time is greater than the current time, keep the game going
		if (endTime > System.currentTimeMillis()) {
			setNewMole();
		}
		else {
			gameOver();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	

}
