package com.security;

import uk.ac.ic.doc.jpair.api.Field;
import uk.ac.ic.doc.jpair.api.FieldElement;
import uk.ac.ic.doc.jpair.api.Pairing;
import uk.ac.ic.doc.jpair.ibe.BFCipher;
import uk.ac.ic.doc.jpair.ibe.BFCtext;
import uk.ac.ic.doc.jpair.ibe.key.BFUserPrivateKey;
import uk.ac.ic.doc.jpair.ibe.key.BFUserPublicKey;
import uk.ac.ic.doc.jpair.pairing.*;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class IBE {
BFUserPublicKey PuClee;
BFUserPrivateKey PrClee;
BFCtext Crtext;
public  IBE(){}
public  BFUserPublicKey getPuClee(){
	return PuClee;
}
public  BFCtext getCrtext(){
	return Crtext;
}
public  BFUserPrivateKey getPrClee(){
	return PrClee;
}
public  Pairing Generation() throws Exception{
	//using a predefined pairing
	 Pairing e = Predefined.nssTate();
	 
	 //get P, which is a random point in group G1 
	 Point P = e.RandomPointInG1(new Random());
	 
	 //get Q, which is a random point in group G2 
	 Point Q = e.RandomPointInG2(new Random());
	
	 
	//compute e(P,Q)
	 FieldElement epq =e.compute(P,Q);
	 
	//the curve on which G1 is defined 
	 EllipticCurve g1 = e.getCurve();
	//a is a 160-bit random integer
	BigInt a = new BigInt(160,new Random()); 
	//Point aP is computed over G1
	Point aP = g1.multiply(P, a);
	
	 
	//The curve on which G2 is defined
	 EllipticCurve g2 = e.getCurve2();
	//b is a 160-bit random integer
	BigInt b = new BigInt(160,new Random());
	//bQ is computed over G2
	Point bQ = g2.multiply(Q, b);
	 
	//compute e(aP,bQ)
	 FieldElement res = e.compute(aP,bQ);
	 
	 //compute e(P,Q)^ab, this is done in group Gt
	 BigInt ab = a.multiply(b);
	 //get the field on which Gt is defined
	 Field gt = e.getGt();
	 FieldElement res2 = gt.pow(epq,ab);

	 if(!res.equals(res2)){
		 throw new Exception("Something is wrong! e(aP,BQ) != e(P,Q)^ab");
	 }

	 return e;
}
public  void CreeCle(String IdCle,Pairing generation)
{
	java.security.KeyPair masterKey=BFCipher.setup(generation ,new Random());
	java.security.KeyPair pk=BFCipher.extract( masterKey,IdCle,new Random());
	 PuClee= (BFUserPublicKey) pk.getPublic(); //cle publique
	 PrClee= (BFUserPrivateKey) pk.getPrivate(); //cle prive
}
public  byte[] CovTB(String Text){
	byte msg[]= new byte[Text.length()];
    msg = Text.getBytes();     
	return msg;
}
public  void crypte(IBE tx,String text,String PuClee) throws Exception{
	tx.CreeCle(PuClee,tx.Generation());
	Crtext=BFCipher.encrypt( tx.getPuClee(), tx.CovTB(text), new Random()) ;
}
public  String decrypte(IBE tx){
	 byte msgre[]=new byte[BFCipher.decrypt(tx.Crtext, tx.getPrClee()).length];
 	 msgre=BFCipher.decrypt(tx.Crtext, tx.getPrClee());
 	 String str="";
	try {
		str = new String(msgre, "UTF-8");
	}
	catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	} 
 	 return str;
}  

}
