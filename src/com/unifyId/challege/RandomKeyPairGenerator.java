package com.unifyId.challege;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

public class RandomKeyPairGenerator {
	
	
	String endpointURL="https://www.random.org/integers/?";
	
	public int getRandNum(Integer num, Integer min, Integer max, Integer col, int base, String format, String rnd) throws IOException{
		endpointURL = endpointURL + "num=" + num + "&min=" + min + "&max=" + max + "&col=" + col + "&base=" + base
				+ "&format=" + format + "&rnd=" + rnd + "/";
		URL myUrl = new URL(endpointURL);
		HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
		InputStream input = conn.getInputStream();
		InputStreamReader is = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(is);

		String inputLine;
		String value = "";
		
		inputLine = reader.readLine();
		value = inputLine;
		return Integer.parseInt(value);
		
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		RandomKeyPairGenerator rgen=new RandomKeyPairGenerator();
		int num=rgen.getRandNum(1,1,10000,1,10,"plain","new");
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(num);
		KeyPair kp = kpg.generateKeyPair();
		
		Key publicKey = kp.getPublic();
		Key privateKey = kp.getPrivate();
		
		
		System.out.println("Public Key:"+publicKey.getFormat());
		System.out.println("Private Key:"+privateKey.getFormat());
	}

}
