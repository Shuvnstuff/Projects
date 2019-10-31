package com.example.pizzaorder2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Order extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		Intent startintent = getIntent();
		Bundle bun = startintent.getExtras();
		String name = bun.getString("name");
		String crust = bun.getString("Crust");
		String toppings = bun.getString("Toppings");
		String size = bun.getString("Size");
		String drink = bun.getString("drink");
		String drinkSize = bun.getString("dSize");
		
		TextView nametv = (TextView)findViewById(R.id.textView2);
		TextView crusttv = (TextView)findViewById(R.id.textView4);
		TextView toppingstv = (TextView)findViewById(R.id.textView6);
		TextView sizetv = (TextView)findViewById(R.id.textView8);
		TextView drinktv = (TextView)findViewById(R.id.textView10);
		
		
		nametv.setText(name);
		crusttv.setText(crust);
		toppingstv.setText(toppings);
		sizetv.setText(size);
		drinktv.setText(drinkSize+"Oz. "+drink);
	}
}
