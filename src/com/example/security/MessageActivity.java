package com.example.security;




import java.util.Random;

import uk.ac.ic.doc.jpair.api.Field;
import uk.ac.ic.doc.jpair.api.FieldElement;
import uk.ac.ic.doc.jpair.api.Pairing;
import uk.ac.ic.doc.jpair.pairing.BigInt;
import uk.ac.ic.doc.jpair.pairing.EllipticCurve;
import uk.ac.ic.doc.jpair.pairing.Point;
import uk.ac.ic.doc.jpair.pairing.Predefined;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
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
					SmsManager.getDefault().sendTextMessage(num, null,msg, null, null);
					IBE t= new IBE();
					//using a predefined pairing
					 Pairing e = Predefined.nssTate();
					 
					 //get P, which is a random point in group G1 
					 Point P = e.RandomPointInG1(new Random());
					 
					 //get Q, which is a random point in group G2 
					 Point Q = e.RandomPointInG2(new Random());

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
