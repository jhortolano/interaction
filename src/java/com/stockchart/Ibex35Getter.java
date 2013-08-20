package com.stockchart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Ibex35Getter {

	public static String urlIBEX35="http://chartapi.finance.yahoo.com/instrument/1.0/%5EIBEX/chartdata;type=quote;range=1d/csv";
	private static long TIME_MIN_TO_REFRESH = 15000;//15 seconds
	private static String currentData=null;
	private static String currentLast = "0";
	private static long lastRefresh=-1;

	public static void main(String[] args) {		
		String p = getIbex35Info();
		String s ="s";
	}

	public static String getIbex35Info(){
		String allData = readURL(urlIBEX35);
		List<List<String>> dataProcessed = readData(allData, 0, 1, "volume:");
		List<String> minMax = calculateMaxMin(dataProcessed);
		dataProcessed.add(0, minMax);
		return getChartInfo(dataProcessed);
	}
	
	public static String getLastQuoteIBEX35(){
		if(currentData==null){
			return "0";
		}
		return currentLast;
	}

	public static synchronized String readURL(String url){
		if(lastRefresh==-1 || currentData==null || Calendar.getInstance().getTimeInMillis()>lastRefresh+TIME_MIN_TO_REFRESH){
			try {
				URL oracle = new URL(url);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(oracle.openStream()));

				String inputLine="";
				String auxIn;
				while ((auxIn = in.readLine()) != null){
					inputLine += "\n"+auxIn;
				}
				in.close();
				currentData = inputLine;
			} catch (IOException e) {
				e.printStackTrace();
			}
			lastRefresh = Calendar.getInstance().getTimeInMillis();
			return currentData;
		}
		return currentData;
	}

	public static List<List<String>> readData(String allInfo, int columnA, int columnB, String lastWordNotRead){
		String[] allLines = allInfo.split("\\n");
		int firstIndex = -1;
		for(int i=0;firstIndex==-1 && i<allLines.length;i++){
			if(allLines[i].toLowerCase().contains(lastWordNotRead.toLowerCase())){
				firstIndex=i+1;
			}
		}
		List<List<String>> returnedList = new ArrayList<List<String>>();
		for(int i=firstIndex;i<allLines.length;i++){
			List<String> auxList = new ArrayList<String>();
			String [] line = allLines[i].split(",");
			//auxList.add(line[columnA]);
			auxList.add(""+i);
			auxList.add(line[columnB]);
			returnedList.add(auxList);
		}
		
		currentLast = returnedList.get(returnedList.size()-1).get(columnB);

		return returnedList;
	}

	public static String getChartInfo(List<List<String>> charts){
		String returned = "[";
		boolean initialized = false;
		for(List<String> info:charts){
			if(initialized==false){
				initialized=true;
			}else{
				returned+=",";
			}
			returned+="["+info.get(0)+","+info.get(1)+"]";
		}
		returned+="]";
		return returned;
	}

	public static List<String> calculateMaxMin(List<List<String>> data){
		double max =0;
		double min =0;
		for(List<String>coupleValues:data){
			if(Double.parseDouble(coupleValues.get(1))>max){
				max=Double.parseDouble(coupleValues.get(1));
			}else if(min==0 || Double.parseDouble(coupleValues.get(1))<min){
				min=Double.parseDouble(coupleValues.get(1));
			}
		}
		List<String> returned = new ArrayList<String>();
		returned.add(""+min);
		returned.add(""+max);
		return returned;
	}

}
