package com.dbExercise7.etl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Date;
import java.util.List;

import com.dbExercise7.data.InputSales;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalesDataExtraction {
	
	public static List<InputSales> readFromFile(String pathname)
	{
		InputSales sales;
		List<InputSales> totalSale = new ArrayList<InputSales>();
		boolean isFirstLine = true;
		
		String currentLine = "";
		try {
			FileReader fr = new FileReader(new File(pathname));
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				if(!isFirstLine)
				{
					String[] strArr = currentLine.split(",");
					
					DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
					Date salesDate = df.parse(strArr[0]);
					int units = Integer.parseInt(strArr[3]);
					double unitSales = Double.parseDouble(strArr[4]);
					sales = new InputSales(salesDate, strArr[1], strArr[2], units, unitSales);
					totalSale.add(sales);
				}
				else
				{
					isFirstLine = false;
				}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return totalSale;
	}

}
