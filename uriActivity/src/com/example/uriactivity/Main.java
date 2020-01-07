package com.example.uriactivity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.net.*;
import android.content.*;
import android.annotation.SuppressLint;
import android.app.*;


@SuppressLint("NewApi")
public class Main extends Activity implements OnClickListener{
	Button webButton;
	Button dialButton;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Intent intent = getIntent();
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_MAIN)) {
			Toast testToast = Toast.makeText(this, "Ur mom", Toast.LENGTH_LONG);
			testToast.setGravity(android.view.Gravity.CENTER, 0, 0);
			testToast.show();
			
			Intent implicitIntent = new Intent();
			PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, implicitIntent, 0);
			
			
			Notification.Builder builder = new Notification.Builder(getApplicationContext());
					builder.setSmallIcon(R.drawable.ic_launcher) ; 
					builder.setTicker("Notify!");
					builder.setContentTitle("You are Notified!");
					builder.setContentText("Ur mom");
					builder.setContentIntent(pendingIntent);
					builder.setAutoCancel(true);

					Notification notify = builder.build();
					
					NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
					nm.notify(0,notify);     

			webButton = (Button)findViewById(R.id.button1);
			
			webButton.setOnClickListener(this);
			dialButton = (Button)findViewById(R.id.button2);
			dialButton.setOnClickListener(this);
		}
		
		else if (action.equals(Intent.ACTION_VIEW)){
		   Uri uri = intent.getData();
		   Bundle bun = intent.getExtras();
		}
		
		else if (action.equals(Intent.ACTION_EDIT)){
			Uri uri    = intent.getData();  
			Bundle bun = intent.getExtras();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.actionbar, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem menu) {
		if(item.getItemId() == R.id.actiondelete) {
			
		}
		else if (item.getItemId() == R.id.actionEdit) {
			
		}
		return super.onOptionsItemSelected(item);
		
	}

	public void onClick(View v) {
		if (v == webButton) {
			Uri uri = Uri.parse("https://www.amazon.com");
			Intent viewIntent = new Intent(Intent.ACTION_VIEW,uri);
			startActivity(viewIntent);
			
		}
		if (v == dialButton) {
			Uri uri = Uri.parse("tel:1-435-850-8544");
			try {
			Intent dialIntent = new Intent(Intent.ACTION_CALL,uri);
			startActivity(dialIntent);
			}
			catch(Exception e) {
				String errorMessage = e.getMessage();
			}
		}
		
	}

		
}



