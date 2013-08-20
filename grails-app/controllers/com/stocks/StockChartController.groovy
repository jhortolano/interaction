package com.stocks

import org.springframework.dao.DataIntegrityViolationException

import com.stockchart.DataGetter;
import com.stockchart.Ibex35Getter;

class StockChartController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
		String currentValue = DataGetter.getCurrentValue()
		boolean isRed = Double.parseDouble(currentValue)<DataGetter.getLastValue()
		String set1 = DataGetter.getValuesChart();
		[stockCurrentValue:currentValue, stockQuoteRed:isRed, stocksSet1:set1, stocksSet2:set1]
    }
	
	def showTime(){
		String set1 = DataGetter.getValuesChart();
		[stocksSet1:set1, stocksSet2:set1]
		render "The time is ${new Date()}"
	}
	
	def getFullData(){
		String set1 = Ibex35Getter.getIbex35Info();
		render set1
	}
	
	def getLastQuote(){
		render Ibex35Getter.getLastQuoteIBEX35();
	}
	
	def index2 (){
		
	}
	
	def ajaxCallTest(){
		double currentValue = DataGetter.getCurrentValue()
		boolean isRed = currentValue<DataGetter.getLastValue()
		String set1 = DataGetter.getValuesChart();	
		[stockCurrentValue:currentValue, stockQuoteRed:isRed, stocksSet1:set1, stocksSet2:set1]
		render(template:"/stockChart/chartmini")
		//redirect(action: "list", params: params)
	}

}
