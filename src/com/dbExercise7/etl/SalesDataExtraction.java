package com.dbExercise7.etl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.dbExercise7.data.InputSales;
import com.dbExercise7.util.DB2ConnectionManager;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SalesDataExtraction {
	
	public static void readFromFile(String pathname)
	{
		ArrayList<InputSales> inputSalesData = new ArrayList<InputSales>();
		ArrayList<Object[]> inputSalesShop = new ArrayList<Object[]>();
		ArrayList<Object[]> inputSalesProduct = new ArrayList<Object[]>();
		
		int i = 0;
		String currentLine = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname), "Cp1252"));
			currentLine = br.readLine();
			while((currentLine = br.readLine()) != null){
				String[] strArr = currentLine.split(";");
				if(strArr.length != 5){
					System.out.println("Incorrect data format:" + currentLine);
					continue;
				}
				if(strArr[0].trim().equals("") || strArr[1].trim().equals("") || strArr[2].trim().equals("") || strArr[3].trim().equals("") || strArr[4].trim().equals("")){
					System.out.println("Incorrect data format:" + currentLine);
					continue;
				}
				try {
					String newDate = strArr[0].substring(6)+"-"+strArr[0].substring(3,5)+"-"+strArr[0].substring(0,2);
					int units = Integer.parseInt(strArr[3]);
					double unitSales = Double.parseDouble(strArr[4].replace(",", "."));
					inputSalesData.add(new InputSales(newDate, strArr[1], strArr[2], units, unitSales));
					inputSalesShop.add(new Object[]{i, strArr[1]});
					inputSalesProduct.add(new Object[]{i, strArr[2]});
					i++;
				} catch (Exception e) {
					System.out.println("Incorrect data format:" + currentLine);
					e.printStackTrace();
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//process the data here
		//CONVERT ALL SHOPNAME TO ID
		while(inputSalesShop.size()>0){
			Object[] initiator = inputSalesShop.get(0);
			String initShopName = (String) initiator[1];
			int initId = getShopIdFromName(initShopName);
			if(initId == -1){
				//Shop doesn't exist
				InputSales initSalesObj = inputSalesData.get((int)initiator[0]);
				System.out.println("Shop not exist."+initSalesObj.getShop()+":"+initSalesObj.getDate()+":"+initSalesObj.getProduct()+":"+initSalesObj.getUnits());
				//Now remove all instances of inexistent shop! - The whole Fking records!
				for (Object[] aSalesShop : inputSalesShop) {
					if(((String) aSalesShop[1]).equals(initShopName)){
						int idOfThatRecord = (int) aSalesShop[0];
						inputSalesData.get(idOfThatRecord).setIgnore(true);
						
						//SMART LOOP
						int productStartIndex = inputSalesShop.indexOf(aSalesShop);
						for (int j = productStartIndex; j < inputSalesProduct.size(); j++) {
							Object[] aSalesProduct = inputSalesProduct.get(j);
							if(((int)aSalesProduct[0]) == idOfThatRecord){
								inputSalesProduct.remove(aSalesProduct);
								break;
							}
						}
						inputSalesShop.remove(aSalesShop);
					}
				}
				continue;
			}
			if(initId == -2){
				System.out.println("So sorry . DB ERROR");
				continue;
			}
			//else initId must be at least 0;
			for (int j = 0; j < inputSalesShop.size(); j++) {
				Object[] aSalesShop = inputSalesShop.get(j);
				if(((String) aSalesShop[1]).equals(initShopName)){
					int idOfThatRecord = (int) aSalesShop[0];
					inputSalesData.get(idOfThatRecord).setShopId(initId);
					inputSalesShop.remove(aSalesShop);
					j--;
				}
			}
		}
		System.out.println("CONVERTED ALL SHOP NAME TO ID");
		while(inputSalesProduct.size()>0){
			Object[] initiator = inputSalesProduct.get(0);
			String initProductName = (String) initiator[1];
			int initId = getProductIdFromName(initProductName);
			if(initId == -1){
				//Product doesn't exist
				InputSales initSalesObj = inputSalesData.get((int)initiator[0]);
				System.out.println("Product not exist."+initSalesObj.getShop()+":"+initSalesObj.getDate()+":"+initSalesObj.getProduct()+":"+initSalesObj.getUnits());
				//Now remove all instances of inexistent Product! - The whole Fking records!
				for (int j = 0; j < inputSalesProduct.size(); j++) {
					Object[] aSalesProduct = inputSalesProduct.get(j);
					if(((String)aSalesProduct[1]).equals(initProductName)){
						int idOfThatRecord = (int) aSalesProduct[0];
						inputSalesData.get(idOfThatRecord).setIgnore(true);
						inputSalesProduct.remove(aSalesProduct);
						j--;
					}
				}
				continue;
			}
			if(initId == -2){
				System.out.println("So sorry . DB ERROR");
				continue;
			}
			//else initId must be at least 0;
			for (int j = 0; j < inputSalesProduct.size(); j++) {
				Object[] aSalesProduct = inputSalesProduct.get(j);
				if(((String) aSalesProduct[1]).equals(initProductName)){
					int idOfThatRecord = (int) aSalesProduct[0];
					inputSalesData.get(idOfThatRecord).setArticleId(initId);
					inputSalesProduct.remove(aSalesProduct);
					j--;
				}
			}
		}
		System.out.println("CONVERTED ALL ARTICLE NAME TO ID");
		
		// Get connection
		try {
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);		
			String insertSQL = "INSERT INTO VSISP51.SALES VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(insertSQL);
			for (int j = 0; j < inputSalesData.size(); j++) {
				InputSales aSale = inputSalesData.get(j);
				if(aSale.getIgnore()){
					//j--;
					continue;
				}
				//else
				aSale.save(pstmt);
			}
			System.out.println("This part could take 1 minute at most. Be patient!");
			int[] count = pstmt.executeBatch();
			System.out.println("Batch Queries executed");
			//Explicitly commit statements to apply changes
			con.commit();
			System.out.println("Committed!");
			DB2ConnectionManager.getInstance().closeConnection();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		System.out.println("Filtered out, converted the Sales Data and pushed to DB.");
		
	}

	public static int getShopIdFromName (String shopName){
		int shopId = -1;
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT SHOPID FROM VSISP51.SHOPID WHERE NAME = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, shopName);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				shopId = rs.getInt("SHOPID");
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return shopId;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		}
		
	}
	
	public static int getProductIdFromName (String productName){
		int productId = -1;
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT ARTICLEID FROM VSISP51.ARTICLEID WHERE NAME = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, productName);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				productId = rs.getInt("ARTICLEID");
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return productId;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		}
		
	}
}
