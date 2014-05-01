package com.example.security;







import com.page.AppelleActivity;
import com.page.MessageActivity;
import com.page.ProfileActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity  extends TabActivity 
{
	// TabSpec Names
		private static final String INBOX = "Inbox";
		private static final String APPELLE = "Appelle";
		private static final String PROFILE = "Profile";
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        TabHost tabHost = getTabHost();
	        
	        // Inbox Tab
	        TabSpec inboxSpec = tabHost.newTabSpec(INBOX);
	        // Tab Icon
	        inboxSpec.setIndicator(INBOX, getResources().getDrawable(R.drawable.icon_inbox));
	        Intent inboxIntent = new Intent(this, MessageActivity.class);
	        // Tab Content
	        inboxSpec.setContent(inboxIntent);
	        
	        // Outbox Tab
	        TabSpec outboxSpec = tabHost.newTabSpec(APPELLE);
	        outboxSpec.setIndicator(APPELLE, getResources().getDrawable(R.drawable.icon_outbox));
	        Intent outboxIntent = new Intent(this, AppelleActivity.class);
	        outboxSpec.setContent(outboxIntent);
	        
	        // Profile Tab
	        TabSpec profileSpec = tabHost.newTabSpec(PROFILE);
	        profileSpec.setIndicator(PROFILE, getResources().getDrawable(R.drawable.icon_profile));
	        Intent profileIntent = new Intent(this, ProfileActivity.class);
	        profileSpec.setContent(profileIntent);
	        
	        // Adding all TabSpec to TabHost
	        tabHost.addTab(inboxSpec); // Adding Inbox tab
	        tabHost.addTab(outboxSpec); // Adding Outbox tab
	        tabHost.addTab(profileSpec); // Adding Profile tab
	    }
}	    
