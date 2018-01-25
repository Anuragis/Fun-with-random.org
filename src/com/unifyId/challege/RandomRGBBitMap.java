package com.unifyId.challege;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;



public class RandomRGBBitMap {

	
	String endpointURL="https://www.random.org/integers/?";
	
	public ArrayList<Integer> randList=new ArrayList<Integer>();;
	
	public void fillList(Integer num, Integer min, Integer max, Integer col, int base, String format, String rnd) throws IOException{
		endpointURL = endpointURL + "num=" + num + "&min=" + min + "&max=" + max + "&col=" + col + "&base=" + base
				+ "&format=" + format + "&rnd=" + rnd + "/";
		URL myUrl = new URL(endpointURL);
		HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
		InputStream input = conn.getInputStream();
		InputStreamReader is = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(is);

		String inputLine;
		String value = "";
		
		while ((inputLine = reader.readLine()) != null) {
			value = inputLine;
			randList.add(Integer.parseInt(value));
		}
	}
	public ArrayList<Integer> getRandList() {
		return randList;
	}
	public void setRandList(ArrayList<Integer> randList) {
		this.randList = randList;
	}
	public static void main(String[] args) throws IOException {
		
		
		RandomRGBBitMap rand=new RandomRGBBitMap();
		rand.fillList(10000, 0, 16384, 1, 10, "plain", "new");
		rand.fillList(6384, 0, 16384, 1, 10, "plain", "new");
	
		Iterator<Integer> it = rand.getRandList().iterator();
		for(Integer i=0;i < 128;i++)
		{
			for(Integer j=0;j < 128;j++)
			{
				int num =  it.next();
				
				int blue =  num & 255;
				int green = (num >> 8) & 255;
				int red = (num >> 16) & 255;
				
				String hex = String.format("#%02x%02x%02x", red, green, blue);
				
				System.out.print(hex);
			}
			System.out.println();
		}
		
		
	}

}