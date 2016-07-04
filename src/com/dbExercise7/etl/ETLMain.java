package com.dbExercise7.etl;

import com.dbExercise7.data.InputSales;
import com.dbExercise7.etl.SalesDataExtraction;
import com.dbExercise7.extractDB2.ExtractDB2;

import java.util.List;
import java.util.ArrayList;

public class ETLMain
{

	public static void main(String[] args)
	{
		// TODO Extract dimensions from DB2 and just load them directly!
		ExtractDB2.extractFromDB();
		
		// TODO Extract sales data from sales.csv
		SalesDataExtraction.readFromFile("sales.csv");
		
		// TODO Load extracted data into data warehouse
	}

}
