package com.page;
import java.io.UnsupportedEncodingException;

import com.example.security.R;
import com.security.IBE;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MessageActivity extends Activity implements View.OnTouchListener, View.OnClickListener
{
	 private Button piece = null;
	  private Button envoyer_simple = null;
	  private Button envoyer_chiferre = null;
	  private TextView numero = null;
	  private TextView message = null;
	  
	  private String ch1 = "";
	  
	//  private TextView result = null;	
	
	  
	@Override // Elle permet au compilateur d'optimiser le bytecode
	protected void onCreate(Bundle savedInstanceState)// On déclare ici une nouvelle classe, MainActivity, et on la fait dériver de Activity, puisqu'il s'agit d'une activité.
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_message);//affiche layout
		
	    // On récupère le bouton par son identifiant
		
	    piece = (Button) findViewById(R.id.button1);
	    envoyer_chiferre = (Button) findViewById(R.id.button2);
		envoyer_simple =(Button) findViewById(R.id.button3);
		//On récupère les deux EditText correspondant aux champs pour entrer le numéro et le message
        numero =(EditText)findViewById(R.id.numero);
        message = (EditText)findViewById(R.id.message);
	    piece.setOnClickListener(this);
	    envoyer_chiferre.setOnClickListener(this);
	    envoyer_simple.setOnClickListener(this);
	    

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) //
	{
		getMenuInflater().inflate(R.menu.main, menu);//
		return true;
	}

	@Override
	public void onClick(View arg0)
	
	{

   	//On récupère ce qui a été entré dans les EditText
		String num = numero.getText().toString();
		String msg = message.getText().toString();
		


				
		  switch(arg0.getId()) 
		  {

		    // Si l'identifiant de la vue est celui du premier bouton
		    case R.id.button1:
			
		    break;
		    case R.id.button2://Envoi chifree
				//Si le numéro est supérieur à 4 caractères et que le message n'est pas vide on lance la procédure d'envoi    	
		    	if(num.length()>= 4 && msg.length() > 0 )
				{
		    		//On envoie le SMS a l'aide de la methode sendTextMessage
					IBE t= new IBE();
					try {
						t.crypte(t,msg,num);
					} catch (Exception e) {
						e.printStackTrace();
					}
					SmsManager.getDefault().sendTextMessage(num, null,t.getCrtext().toString(), null, null);
					//On efface les deux EditText
				    numero.setText("");
					message.setText("");
				}
				else
				{
					//On affiche un petit message d'erreur dans un Toast
					Toast.makeText(MessageActivity.this, "Enter le numero et/ou le message", Toast.LENGTH_SHORT).show();
				}
		
			break; 
		    case R.id.button3://Envoie simple

				//Si le numéro est supérieur à 4 caractères et que le message n'est pas vide on lance la procédure d'envoi
				if(num.length()>= 4 && msg.length() > 0 && msg.length()<=160)
				{
					
					//On envoie le SMS a l'aide de la methode sendTextMessage
					SmsManager.getDefault().sendTextMessage(num, null, msg, null, null);
					//On efface les deux EditText
					numero.setText("");
					message.setText("");
				}
				else if (msg.length()>160)
				{
					//On affiche un petit message d'erreur dans un Toast
					Toast.makeText(MessageActivity.this, "le message écrit doit être o maxi moment 160 caractères au lieu de "+msg.length(), Toast.LENGTH_SHORT).show();
				}
				else
				{
					//On affiche un petit message d'erreur dans un Toast
					Toast.makeText(MessageActivity.this, "Enter le numero et/ou le message", Toast.LENGTH_SHORT).show();
				}
			break;

		  }
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
	    // Le toucher est fini, on veut continuer à détecter les touchers d'après
	    return true;
	}


}
