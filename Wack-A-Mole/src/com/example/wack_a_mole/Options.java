package com.example.wack_a_mole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.*;

public class Options extends Activity implements OnClickListener {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		setUpSpinner();
		Button playButton = (Button)findViewById(R.id.PlayButton);
		playButton.setOnClickListener(this);
	}
	private void setUpSpinner() {
		
		Spinner moleSpinner = (Spinner)findViewById(R.id.MoleSpin);
		
		String [] moleArray = {"3","4","5","6","7","8"};
		
		ArrayAdapter<String> moleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,moleArray);
		
		moleSpinner.setAdapter(moleAdapter);
	}
	private void saveSettingsInIntent(int difficulty, String name,int numMoles, int duration, Intent myIntent) {
		
		myIntent.putExtra("Name", name);
		myIntent.putExtra("Dif", difficulty);
		myIntent.putExtra("Moles", numMoles);
		myIntent.putExtra("Duration", duration);
	}
	@Override
	public void onClick(View v) {
		
		if(v.getId() != R.id.PlayButton) {
			
			return;
		}
		else {
			
			Intent gameIntent = new Intent(this, Game.class);
			
			EditText nameText = (EditText)findViewById(R.id.NameBox);
			RadioButton easy = (RadioButton)findViewById(R.id.EasyDif);
			RadioButton medium = (RadioButton)findViewById(R.id.MediumDif);
			SeekBar dursb = (SeekBar)findViewById(R.id.Duration);
			Spinner molesp = (Spinner)findViewById(R.id.MoleSpin);
			
			int dif;
			if(easy.isChecked()) {
				dif = 3;
			}
			else if(medium.isChecked()) {
				dif = 2;
			}
			else {
				dif = 1;
			}
			String name = nameText.getText().toString();
			int duration = dursb.getProgress();
			int numMoles = molesp.getSelectedItemPosition()+3;
			saveSettingsInIntent(dif, name, numMoles, duration, gameIntent);
			startActivity(gameIntent);
		}
	}
	

}
