package com.basededonee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class SGBDRandroid {
	/*
	 * Cette Classe sert à faire le lien avec le SGBD
	 * test 1
	 */
SGBDRandroid(){}
public static boolean send(String msg){
		boolean test = false;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://172.20.10.4/Envoie.php");
		if(msg.length()>0)
		{
			try{
				List<NameValuePair> donnees = new ArrayList<NameValuePair>(1);
				donnees.add(new BasicNameValuePair("message",msg));
				post.setEntity(new UrlEncodedFormEntity(donnees));
				client.execute(post);
				test=true; //envoie réussie 
				}
				catch(ClientProtocolException e)
				{
					e.printStackTrace();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			return test;
		}
		else
		{
			test=false; //Ce champ ne peut etre vide
			return test;
		}
	}
public static List  receive()
{
		List  Data = new LinkedList();
		StringBuffer sb =new StringBuffer("");
		BufferedReader br=null;
		try{
			HttpClient client = new DefaultHttpClient();
			HttpGet get= new HttpGet();
			URI uri = new URI("http://172.20.10.4/Resption.php");
			get.setURI(uri);                      
			HttpResponse reponse = client.execute(get);
			InputStream is =reponse.getEntity().getContent();//recuperartion et acces au contenu
			br =new BufferedReader(new InputStreamReader(is));
			String lignelue = br.readLine(); //lire chaque ligne transmise
			while(lignelue != null)
			{
				sb.append(lignelue);
				sb.append("\n");//pour passer a la ligne suivant 
				lignelue= br.readLine();
			}
}
	catch(Exception e)
	{
		Log.e("log_tag", "Error: " + e.toString());
	}
		finally {
			if(br != null){
					try{
					br.close();
					}catch(IOException e){
						Log.e("log_tag", "Error: " + e.toString());
					}
				}
			}
	try{
		JSONArray jArray = new JSONArray(sb.toString());
		 for(int i=0;i<jArray.length();i++)
		 {	 
			 Data.add(jArray.getJSONObject(i).get("message").toString()); 
		 }
	   }
	catch(JSONException e)
	{
		  Log.e("log_tag", "Error: " + e.toString());
	}
		return Data;
	}
}
