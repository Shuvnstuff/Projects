package com.example.meh;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LinearLayout myPhotos = (LinearLayout)findViewById(R.id.myPhotos);
		myPhotos.addView(createImageView(R.drawable.banan6));
		myPhotos.addView(createImageView(R.drawable.banan7));
		myPhotos.addView(createImageView(R.drawable.banan8));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public View createImageView(int imageid) {
		ImageView iv = new ImageView(getApplicationContext());
		iv.setPadding(5,5,5,5);
		iv.setImageResource(imageid);
		iv.setLayoutParams(new LayoutParams(180, 150));
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		iv.setTag(imageid);
		iv.setOnClickListener(this);
		return iv;
	}
	
	@Override
	public void onClick(View v) {
		int resourceId = (Integer)v.getTag();
		ImageView iv2 = (ImageView)findViewById(R.id.myImageView);
		iv2.setImageResource(resourceId);
		
	}

}
