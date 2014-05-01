package com.page;

import com.example.security.R;
import com.example.security.R.layout;



import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class AppelleActivity extends Activity implements View.OnTouchListener, View.OnClickListener
{
	  public void onCreate(Bundle savedInstanceState) 
	  {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_appelle);
	  }

	@Override
	public void onClick(View v) {
		
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		return false;
	}

}
