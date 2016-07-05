package com.dbExercise7.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ConsoleUtil
{

	public static void printColumnNames(Set<String> columnNames)
	{
		String output = "";
		
		for (String s : columnNames)
		{
			if(s.length() >= 20)
			{
				output += s.substring(20);
			}
			else
			{
				output += String.format("%1$-20s", s);
			}
		}
		
		System.out.println(output);
	}

	public static void printRecord(ResultSet rs, Map<String, Integer> columnDetails)
	{
		String output = "";
		
		try
		{
			for(String s : columnDetails.keySet())
			{
				int temp = columnDetails.get(s);
				
				switch(columnDetails.get(s))
				{
				case(Types.VARCHAR): 
				{
					output += String.format("%1$-19s", rs.getString(s));
					break;
				}
				case(Types.INTEGER):
				{
					output += String.format("%1$19s", rs.getInt(s));
					break;
				}
				case(Types.DOUBLE):
				{
					output += String.format("%1$19s", NumberFormat.getCurrencyInstance().format(rs.getDouble(s)));
					break;
				}
//					case(Types.DATE): output += String.format("%1$-19s", DateFormatSymbols.getInstance().getMonths()[rs.getInt(s)]);
				}
				
				output += " ";
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(output);
	}
	
	public static void displayResults(ResultSet rs)
	{
		Map<String, Integer> columnDetails;
//		ArrayList<String> columnNames = getColumnNames(rs);
		columnDetails = ConsoleUtil.getColumnDetails(rs);
		printColumnNames(columnDetails.keySet());
		
		try
		{
			while(rs.next())
			{
				printRecord(rs, columnDetails);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<String, Integer> getColumnDetails(ResultSet rs)
	{
		ResultSetMetaData meta;
		LinkedHashMap<String, Integer> columnDetails = new LinkedHashMap<String, Integer>();
		
		try
		{
			meta = rs.getMetaData();
			
			for(int i = 1; i <= meta.getColumnCount(); i++)
			{
				columnDetails.put(meta.getColumnName(i), meta.getColumnType(i));
			}
		}
		catch(SQLException e)
		{
			System.err.println("Cannot read metadata from ResultSet, stack trace follows:");
			e.printStackTrace();
		}
		
		return columnDetails;
	}

}
