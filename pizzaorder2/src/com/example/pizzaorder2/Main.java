package com.example.pizzaorder2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class Main extends Activity implements OnClickListener {
	EditText name;
	RadioButton stuffed;
	RadioButton thin;
	RadioButton pan;
	RadioButton deepDish;
	CheckBox Pep;
	CheckBox Bac;
	CheckBox Saus;
	CheckBox Pine;
	CheckBox Ham;
	CheckBox Mush;
	CheckBox Anc;
	CheckBox Onion;
	CheckBox Olive;
	Button order;
	Button size;
	TextView tv;
	Spinner spn;
	String pizzaSize;
	String drinks[] = {"Mt.Dew","Dr.Pepper","Pepsi","RootBeer","RedCreamSoda","Sprite","Coke",""};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		name = (EditText)findViewById(R.id.editText1);
		stuffed = (RadioButton)findViewById(R.id.radio0);
		thin = (RadioButton)findViewById(R.id.radio2);
		pan = (RadioButton)findViewById(R.id.radio1);
		deepDish = (RadioButton)findViewById(R.id.radio3);
		Pep = (CheckBox)findViewById(R.id.checkBox1);
		Bac = (CheckBox)findViewById(R.id.checkBox2);
		Saus = (CheckBox)findViewById(R.id.checkBox3);
		Pine = (CheckBox)findViewById(R.id.checkBox5);
		Ham = (CheckBox)findViewById(R.id.checkBox4);
		Mush = (CheckBox)findViewById(R.id.checkBox6);
		Anc = (CheckBox)findViewById(R.id.checkBox7);
		Onion = (CheckBox)findViewById(R.id.checkBox9);
		Olive = (CheckBox)findViewById(R.id.checkBox8);
		order = (Button)findViewById(R.id.button1);
		size = (Button)findViewById(R.id.button2);
		size.setOnClickListener(this);
		order.setOnClickListener(this);
		ArrayAdapter<String> myAdapter =new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,drinks);
		Spinner spn = (Spinner)findViewById(R.id.spinner1);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn.setAdapter(myAdapter);
		spn.setSelection(0);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v == size) {
			Intent sizeintent = new Intent(Main.this,sizelist.class);
			startActivityForResult(sizeintent,0);
		}
		if(v == order) {
			String custName = name.getText().toString();
			String crust;
			if(stuffed.isChecked()) {
				crust = "stuffed";
			}
			else if(thin.isChecked()){
				crust = "thin";
			}
			else if(pan.isChecked()) {
				crust = "pan";
			}
			else {
				crust = "deepDish";
			}
			String toppings = "";
			if(Pep.isChecked()) {
				toppings += "Peperoni ";
			}
			if(Bac.isChecked()) {
				toppings += "Bacon ";
			}
			if(Saus.isChecked()) {
				toppings += "Sausage ";
			}
			if(Pine.isChecked()) { 
				toppings += "Pineapple ";
			}
			if(Ham.isChecked()) {
				toppings += "Hamburger ";
			}
			if(Mush.isChecked()) {
				toppings += "Mushroom ";			
			}
			if(Anc.isChecked()) {
				toppings += "Anchovies ";
			}
			if(Onion.isChecked()) {
				toppings += "Onion ";
			}
			if(Olive.isChecked()) {
				toppings += "Olive ";
			}
			Spinner spn = (Spinner)findViewById(R.id.spinner1);
			int pos = spn.getSelectedItemPosition();
			String drinkitem = drinks[pos];
			Intent myIntent = new Intent(Main.this,Order.class);
			SeekBar sb = (SeekBar)findViewById(R.id.seekBar1);
			int drinksize = sb.getProgress();
			if (drinksize > 16 && drinksize <=24) {
				drinksize = 24;
				sb.setProgress(24);
			}
			else if(drinksize > 24 && drinksize <=32) {
				drinksize = 32;
				sb.setProgress(32);
			}
			else if(drinksize > 32) {
				drinksize = 64;
				sb.setProgress(64);
			}
			else if(drinksize <16) {
				drinksize = 12;
				sb.setProgress(12);
			}
			myIntent.putExtra("name", custName);
			myIntent.putExtra("Crust", crust);
			myIntent.putExtra("Toppings", toppings);
			myIntent.putExtra("size", pizzaSize);
			myIntent.putExtra("drink",drinkitem);
			myIntent.putExtra("dSize", drinksize);
			startActivity(myIntent);
		}
		
	}
	protected void onActivityResult(int requestCode,int resultCode, Intent data) {
		Bundle bun = data.getExtras();
		String value = bun.getString("size");
		pizzaSize = value;
	}

	
	
	

}
