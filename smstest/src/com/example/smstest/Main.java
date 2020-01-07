package com.example.smstest;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.telephony.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.telephony.SmsMessage;
import java.net.*;
import java.io.*;
import android.net.*;
import android.content.*;
import android.os.StrictMode;

public class Main extends Activity implements OnClickListener{
	Button sendSms;
	SmsManager sms;
	BroadcastReceiver receiver;
	BroadcastReceiver deliver;
	BroadcastReceiver smsReceiver;
	
	public boolean isNetworkAvailable()
	{
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
	    {
	        return true;    // yes this device is connected
	    }
		return false;
	}
	
	
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		try {
			URL url = new URL("http://www.CompuScholar.com");
			HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			
			int response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				 // get InputStream and wrap in handy readers
			      InputStream in = httpConn.getInputStream();
			      InputStreamReader isr = new InputStreamReader(in);
			      BufferedReader br = new BufferedReader(isr);

			      String line = "";
			      String file = "";

			      // read lines of text until nothing left on stream
			      do
			      {
			        line = br.readLine();
			        if (line != null)
			           file += line;  // add line to larger string
			      }
			      while (line != null);
			      br.close();     // always close your input stream!

			      // put whatever we received in a multi-line EditText
			      TextView fromlbl = (TextView)findViewById(R.id.textView3);
					TextView messagetxt = (TextView)findViewById(R.id.editText3);
					fromlbl.setText(file);
					messagetxt.setText(line);
			    }
			    httpConn.disconnect();  // always disconnect when finished!
			}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} smsReceiver = new BroadcastReceiver() {
			TextView fromlbl = (TextView)findViewById(R.id.textView3);
			TextView messagetxt = (TextView)findViewById(R.id.editText3);
			
			@Override
			public void onReceive(Context context, Intent intent) {
				Bundle bundle = intent.getExtras();
				if (bundle == null) {
	             return; // error - no Bundle present
				}
				
				
				Object[] pdus = (Object[]) bundle.get("pdus");
				if (pdus == null) {
				   return; // error - no PDU values present
				}
				
				
				for (int i=0; i<pdus.length; i++)  // for each message
				   {
				      // convert from PDU to SmsMessage
				    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);

				      // get from address and message text
				      String from = message.getOriginatingAddress();
				      String text = message.getMessageBody().toString();
				      fromlbl.setText("Message From: " + from);
				      
				   }
				
				
			}
			
		};
		
		deliver = new BroadcastReceiver () {

			public void onReceive(Context context, Intent intent)
		      {
		        if (getResultCode() == Activity.RESULT_OK)
		        {
		          Toast t = Toast.makeText(context, "Msg Delivered!",
		                                   Toast.LENGTH_LONG);
		          t.show();
		        }
		        else
		        {
		          Toast t = Toast.makeText(context,"Msg Error!",
		                                   Toast.LENGTH_LONG);
		          t.show();
		        }
		    }
		};

		receiver = new BroadcastReceiver()     // anonymous inner class
				{
			   public void onReceive(Context context, Intent intent)
			   {
			     if (getResultCode() == Activity.RESULT_OK) // if message sent
			     {
			       Toast t = Toast.makeText(context, "Message Sent!",   
			                                Toast.LENGTH_LONG);
			       t.show();
			     }
			     else  // else some send error occurred
			     {
			        Toast t = Toast.makeText(context,"Message Error!",
			                                 Toast.LENGTH_LONG);
			        t.show();
			     }
			   }
			};
			
		SmsManager sms = SmsManager.getDefault();
		registerReceiver(receiver, new IntentFilter("MY_SMS_SENT"));
		registerReceiver(deliver, new IntentFilter("MY_SMS_DELIVERED"));
		registerReceiver(smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
		
		sendSms = (Button)findViewById(R.id.smsSend);
		sendSms.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void onDestroy()
	{
	   super.onDestroy();  // always call the base class version
	   if (receiver!= null)     // just to be safe
	   {
	      // unregister BroadcastReceiver
	      unregisterReceiver(receiver); 
	      unregisterReceiver(deliver);
	      unregisterReceiver(smsReceiver);
	   }
	}
	
	
	@Override
	public void onClick(View v) {
		if (v == sendSms) {
			EditText addresstext = (EditText)findViewById(R.id.editText1);
			EditText MessageText = (EditText)findViewById(R.id.editText2);
			
			String address = addresstext.getText().toString();
			String message = MessageText.getText().toString();
			
			Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
			smsIntent.putExtra("address", address);
			smsIntent.putExtra("sms_body", message);
			smsIntent.setType("vnd.android-dir/mms-sms");
			
			//startActivity(smsIntent);
			PendingIntent msgSentIntent = PendingIntent.getBroadcast(this, 0,new Intent("MY_SMS_SENT"), 0);
			sms.sendTextMessage(address, null, message, msgSentIntent, null);
			
		}
		
	}

}
