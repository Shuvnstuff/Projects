package com.example.wack_a_mole;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import java.io.*;
import android.content.Intent;
import android.view.View;
import android.view.View.*;
import android.widget.*;


public class GameOver extends Activity implements OnClickListener{
	
	String playerName;
	int numWhacks;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		
		Bundle bun = getIntent().getExtras();
		playerName = bun.getString("Name");
		numWhacks = bun.getInt("Score");
		
		TextView tvHits = (TextView)findViewById(R.id.HitNum);
		TextView tvGameOver = (TextView)findViewById(R.id.GameOvr);
		tvHits.setText("You Hit " + numWhacks + " times!");
		tvGameOver.setText("GameOver: " + playerName);
		
		Button playbttn = (Button)findViewById(R.id.PlayBttn);
		Button scorebttn = (Button)findViewById(R.id.ScoreBttn);
		playbttn.setOnClickListener(this);
		scorebttn.setOnClickListener(this);
		
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED))
		{
		   // we can read and write to the SD card
			saveHighScoreSD();
		}
		else {
			saveHighScoreInternalFile();
		}
		
	}
	// save the player's score into the specified FileOutputStrem
	private void writeToFOS(FileOutputStream fos) {
		try{
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		String endline = System.getProperty("line.separator");
		playerName += endline;
		osw.write(playerName);
		String score = numWhacks + endline.toString();
		osw.write(score);
		
		osw.flush();
		osw.close();
		
		}
		catch(Exception e){
			
		}
		
	}
	// This method will save the player's score into the HighScores.txt file in Internal Memory
	private void saveHighScoreInternalFile() {
		try {
		FileOutputStream fos = openFileOutput("HighScores.txt", MODE_APPEND);
		writeToFOS(fos);
		
		}
		catch(Exception e) {
			
		}
	}
	// This method will save the player's score into the HighScores.txt file on a sd card
	private void saveHighScoreSD() {
		try {
			File privateSD = getExternalFilesDir(null);
			File sdScores = new File(privateSD, "highScoresSd.txt");
			FileOutputStream fos = new FileOutputStream(sdScores, true);
			writeToFOS(fos);
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.PlayBttn) {
			Intent startover = new Intent(this,Game.class);
			startActivity(startover);
			finish();
		}
		else if(v.getId() == R.id.ScoreBttn) {
			Intent startHS = new Intent(this,HighScores.class);
			startActivity(startHS);
			finish();
		}
	}
}
