package com.dbExercise7.etl;

import com.dbExercise7.data.InputSales;
import com.dbExercise7.etl.SalesDataExtraction;
import java.util.List;
import java.util.ArrayList;

public class ETLMain
{

	public static void main(String[] args)
	{
		// TODO Extract dimensions from DB2
		
		// TODO Extract sales data from sales.csv
		List<InputSales> totalSales = new ArrayList<InputSales>();
		totalSales = SalesDataExtraction.readFromFile("sales.csv");
		System.out.println(totalSales);
		
		// TODO Load extracted data into data warehouse
	}

}
