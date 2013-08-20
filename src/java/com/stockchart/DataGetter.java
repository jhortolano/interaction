package com.stockchart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataGetter {

	private static double lastValue,currentValue = 0.0;
	private static List<Map<String,Double>> charts1;
	
	public static double getLastValue(){
		return lastValue;
	}
	
	public static String getCurrentValue(){
		return Ibex35Getter.getLastQuoteIBEX35();
	}
	
	public static void initializeValuesChart(){
		charts1 = new ArrayList<Map<String,Double>>();
		Map<String,Double> auxMap;
		auxMap = new HashMap<String,Double>();auxMap.put("2002", 112000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2003", 122000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2004", 142000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2005", 110000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2006", 212000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2007", 72000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2008", 112000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2009", 114000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2010", 118000.0);charts1.add(auxMap);
		auxMap = new HashMap<String,Double>();auxMap.put("2011", 82000.0);charts1.add(auxMap);
	}
	
	public static String getChartInfo(){
		if(charts1==null){initializeValuesChart();}
		String returned = "[";
		boolean initialized = false;
		for(Map<String,Double> info:charts1){
			if(initialized==false){
				initialized=true;
			}else{
				returned+=",";
			}
			returned+="[";
			for(String key:info.keySet()){
				returned+=key+","+info.get(key);
			}
			returned+="]";
		}
		returned+="]";
		return returned;
	}
	
	public static String getValuesChart(){
		if(charts1==null){
			return getChartInfo();
		}else{
			int lastValue = Integer.parseInt(charts1.get(charts1.size()-1).keySet().iterator().next());
			lastValue++;
			Map<String,Double> auxMap;
			auxMap = new HashMap<String,Double>();auxMap.put(""+lastValue, 118000.0);charts1.add(auxMap);
			return getChartInfo();
		}
	}

}
