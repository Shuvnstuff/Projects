package com.example.wack_a_mole;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import java.util.Random;
import java.util.ArrayList;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Start extends Activity implements OnClickListener{
	
	Button playbttn;
	Button option;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		playbttn = (Button)findViewById(R.id.start);
		option = (Button)findViewById(R.id.option);
		playbttn.setOnClickListener(this);
		option.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == playbttn) {
			Intent startscr = new Intent(this, Game.class);
			startActivity(startscr);
			finish();
			
		}
		if(v == option) {
			Intent optionscr = new Intent(this, Options.class);
			startActivity(optionscr);
			finish();
			
		}
		
	}
}
