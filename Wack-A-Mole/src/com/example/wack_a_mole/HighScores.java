package com.example.wack_a_mole;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import java.io.*;
import java.util.*;
import android.widget.*;

public class HighScores extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores);
		
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED))
		{
		   // we can read and write to the SD card
			loadHighScoresSD();
		}
		else {
			loadHighScoresInternalFile();
		}
	}
	// This method will load the player's score into the HighScores.txt file in Internal Memory
		private void loadHighScoresInternalFile() {
			try {
			FileInputStream fis = openFileInput("HighScores.txt");
			readScoresFIS(fis);
			
			}
			catch(Exception e){
				
			}
		}
		// This method will load the player's score into the HighScores.txt file on a SD card
		private void loadHighScoresSD() {
			try {
			File privateLocation = getExternalFilesDir(null);
			File myFile = new File(privateLocation, "highScoresSd.txt");
			FileInputStream fis = new FileInputStream(myFile);
			readScoresFIS(fis);
			
			}
			catch(Exception e) {
				
			}
			
		}
		// this method will load the scores from the FileInputStream
		// sort them by score, and select the top 5 to put in the 
		// high scores list on the screen.
		
		private void readScoresFIS(FileInputStream fis) 
		{
			// create InputStreamReader
			InputStreamReader isr = new InputStreamReader(fis);
			// Get a handle to the two text fields on the screen
			TextView tvName = (TextView)findViewById(R.id.tvPlayerName);
			TextView tvScore = (TextView)findViewById(R.id.tvScore);
			
			// Figure out which character is an endLine on the current device
			String endLine = System.getProperty("line.separator");
			
			LinkedList<String> playerNames = new LinkedList<String>();
			LinkedList<Integer> playerScores = new LinkedList<Integer>();
			
			try 
			{
				// Use a BufferedReader to allow for easy reading of the file data
				BufferedReader buffreader = new BufferedReader(isr);
				// Read in the data, line by line
				String name = buffreader.readLine();
				while (name != null) {
					String strScore = buffreader.readLine();
					int score = Integer.parseInt(strScore);
					ListIterator<Integer> scoreIter = playerScores.listIterator();
					ListIterator<String> playerIter = playerNames.listIterator();
					while (scoreIter.hasNext()){
						Integer thisScore = scoreIter.next();
						playerIter.next();
						// if new score is larger than this one
						if (score >= thisScore) {
							break; // stop looking, we know where to insert!
						}
					}
					// if there are any scores at all, we need to rewind both iterators
					// by one to insert in the correct spot
					if (playerScores.size() > 0) {
						scoreIter.previous();
						playerIter.previous();
					}
					// add this score and name into the linked lists in sorted order
					scoreIter.add(new Integer(score));
					playerIter.add(name);
					
					//read in the next line in the file
					name = buffreader.readLine();
				}
				// Close the BufferedReader object
				buffreader.close();
			}
			catch(Exception e) {
				// file handling error
			}
			// now, iterate again over the sorted list
			ListIterator<Integer> scoreIter = playerScores.listIterator();
			ListIterator<String> playerIter = playerNames.listIterator();
			// these strings will contain the sorted scores and corresponding names
			String sortedNames = "";
			String sortedScores = "";
			
			int numPresent = 0;
			while (scoreIter.hasNext()) {
				// get the score and corresponding name
				Integer score = scoreIter.next();
				String name = playerIter.next();
				// add score and name plus a newline to respective text fields
				sortedScores += score.toString() + endLine;
				sortedNames += name + endLine;
				// count scores and stop after top 5
				numPresent++;
				if(numPresent >= 5) {
					break;
				}
				// put the stored names in the name TextView
				tvName.setText(sortedNames);
				// put the sorted scores in the score TextView
				tvScore.setText(sortedScores);
			}
			
			
			
		}
		
}
