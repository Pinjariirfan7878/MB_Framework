package com.oms.GenericLib;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class FileLibClass {

	public String readPropertyData(String path, String key) throws Exception {
		FileInputStream fil = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fil);
		return prop.getProperty(key, "Incorrect Key");
	}

	public String readExcelData(String sheet, int row, int cell) throws Exception {
		FileInputStream fis = new FileInputStream(IAutoconstant.CSV_File_SSU);
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		return value;
	}

	public void WriteExcelData(String sheet, int row, int cell, String value) throws Exception, Exception {
		String path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path + "");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheet).getRow(row).createCell(cell).setCellValue(value);
		FileOutputStream fos = new FileOutputStream("");
		wb.write(fos);
	}

	public String readExcelData(int row, int cell, String sheet) throws Exception {
		String path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path + IAutoconstant.EXCEL_URL);
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		return value;
	}

	public String readData_TestData(String sheet, int row, int cell) {
		String value ="";
		try {
		FileInputStream fis = new FileInputStream(IAutoconstant.EXCEL_TestData);
		Workbook wb = WorkbookFactory.create(fis);
		value = wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		}
		catch(Exception e) {
			System.out.println("Exception"+e);
		}
		return value;
	}

	private static Fillo fillo ;
		
	private Fillo getFillo() {
		if(fillo == null) {
			fillo = new Fillo();
		}
		return fillo;
	}
	
	public String getDataFromExcell(String sheetName, String Key, String filePath) {
		String query = "SELECT * FROM " + sheetName + " where Description = '" + Key + "'";
		System.out.println("Quest=" + query);
		String data = "";
		try {
			fillo = getFillo();
			Connection connection = fillo.getConnection(filePath);
			Recordset recordset = connection.executeQuery(query);
			System.out.println("Size of Data=" + recordset.getCount());
			while (recordset.next()) {

				data = recordset.getField("Value"+readPropertyData("./Data/config.properties", "Environment"));

			}
			connection.close();
		} catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Key =>"+Key+", Data is =" + data);

		return data;
	}
	
	public String[] getArrayDataFromExcell(String sheetName, String Key, String filePath) {
		String query = "SELECT * FROM " + sheetName + " where Description = '" + Key + "'";
		System.out.println("Quest=" + query);
		String data = "";
		try {
			fillo = getFillo();
			Connection connection = fillo.getConnection(filePath);
			Recordset recordset = connection.executeQuery(query);
			System.out.println("Size of Data=" + recordset.getCount());
			while (recordset.next()) {

				data = recordset.getField("Value");

			}
			connection.close();
		} catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Key =>"+Key+", Data is =" + data);
		String arr [] = data.split(",");
		return arr ;
	}	
   
	//Start--Method For read data from CSV file
	public HashMap<String, String> getDataAsRowFromCSV(String filePath, String headerName, String dataValue) {
		String csvFile = filePath;
		CSVReader csvReader = null;

		ArrayList<List<String>> dataList = new ArrayList<List<String>>();
		String[] value;
		List<String> listValues;
		List<String> header = new ArrayList<String>();
		HashMap<String, String> rowData = new HashMap<String, String>();

		int rowCount = 0;
		try {
			csvReader = new CSVReader(new FileReader(csvFile));

			while ((value = csvReader.readNext()) != null) {
				System.out.println("Column size = " + value.length);

				if (rowCount != 0) {
					listValues = Arrays.asList(value);
					System.out.println(listValues);
					dataList.add(listValues);
				} else {
					header = Arrays.asList(value);
				}
				rowCount++;
			}
		} catch (Exception e) {
			System.out.println("Exception = " + e);
		}

		// ===================

		System.out.println("Data Size =" + dataList.size());
		System.out.println("Headers = " + header);
		int indexOfHeader = header.indexOf(headerName);
		System.out.println("Index of Headeer =" + indexOfHeader);
		if (indexOfHeader >= 0) {
			for (List<String> dataRow : dataList) {

				if (dataRow.get(indexOfHeader).equals(dataValue)) {
					//System.out.println("Data =>" + dataRow);
					for (int i = 0; i < header.size(); i++) {
						rowData.put(header.get(i), dataRow.get(i));
					}
				}
			}
		} else {
			System.out.println("Wrong " + headerName + " Number pls Check");
		}
		if (rowData.isEmpty()) {
			System.out.println("Wrong Data input for CSV");
		} else {
			//System.out.println("Row data= " + rowData);
		}
		return rowData; 
	}
	
	public String getDataFromCSV(String filePath, String headerName, String dataValue , String keyValue) {
			
		HashMap<String, String> rowData =  getDataAsRowFromCSV(filePath, headerName, dataValue);
		System.out.println("Got data"+rowData);	
		String value = rowData.get(keyValue) ;
		return value;
	}
	//End--Method for read data from CSV file
	
	public List readColumnFromCSVWithCondition(String filePath, String columnName, String conditionHeader,
			String conditionValue) {
		String csvFile = filePath;
		CSVReader csvReader = null;
		List<String> columnData = new ArrayList<String>();

		try {
			csvReader = new CSVReader(new FileReader(csvFile));
			String[] value;
			List<String> csvHeader = new ArrayList<String>();
			int rowCount = 0;

			while ((value = csvReader.readNext()) != null) {

				if (rowCount == 0) {
					for (String s : value) {
						csvHeader.add(s);
					}
				} else {

				}
				rowCount++;
			}
			System.out.println("Headers =" + csvHeader);

			int indexHeader = csvHeader.indexOf(columnName);
			int indexConditionHeader = csvHeader.indexOf(conditionHeader); // 1

			if (indexHeader >= 0) {
				System.out.println("Index of Header=" + indexHeader);
				csvReader = new CSVReader(new FileReader(csvFile));
				rowCount = 0;
				while ((value = csvReader.readNext()) != null) {

					if (rowCount != 0) {
						if (value[indexConditionHeader].equals(conditionValue)) {
							columnData.add(value[indexHeader]);
						}

					}
					rowCount++;
				}

			} else {
				System.out.println("Header is incorrect pls check");
			}
		} catch (Exception e) {
			System.out.println("Exception = " + e);
		}
		return columnData;
	}
	//***********************
	 public List readColumnFromCSV(String filePath, String columnName) {
			String csvFile = filePath;
			CSVReader csvReader = null;
			List<String> columnData = new ArrayList<String>();

			try {
				csvReader = new CSVReader(new FileReader(csvFile));
				String[] value;
				List<String> csvHeader = new ArrayList<String>();
				int rowCount = 0;

				while ((value = csvReader.readNext()) != null) {

					if (rowCount == 0) {
						for (String s : value) {
							csvHeader.add(s);
						}
					} else {

					}
					rowCount++;
				}
				System.out.println("Headers =" + csvHeader);

				int indexHeader = csvHeader.indexOf(columnName);

				if (indexHeader >= 0) {
					System.out.println("Index of Header=" + indexHeader);
					csvReader = new CSVReader(new FileReader(csvFile));
					rowCount = 0;
					while ((value = csvReader.readNext()) != null) {

						if (rowCount != 0) {
							columnData.add(value[indexHeader]);
						}
						rowCount++;
					}

				} else {
					System.out.println("Header is incorrect pls check");
				}
			} catch (Exception e) {
				System.out.println("Exception = " + e);
			}
			return columnData;
		}

	public String getDecimalNumbaer(String number) {
		String num = "";
		char[] ch = number.toCharArray();

		for (char c : ch) {
			if (Character.isDigit(c) || c == '.') {
				num = num + c;
			}
		}
		return num;
	}

	//edit data to CSV
	/**
	 * This method use for changes in CSV file 1) fileToUpdate => Path of CSV file
	 * 2) row => CSV row number where you want to change value 3) coloumnName =>
	 * Column Name where you want to change value 4) replace => new Value which
	 * store in CSV
	 */
	public void editCSV(String fileToUpdate, int row, String columnName, String replace) {

		try {
			File inputFile = new File(fileToUpdate);

			// Read existing file
			CSVReader reader = new CSVReader(new FileReader(inputFile));
			List<String[]> csvBody = reader.readAll();

			// if CSV have 2 Row than last index of row is =>1
			if (row < csvBody.size()) {
				List<String> header = Arrays.asList(csvBody.get(0));
				int indexColoumn = 0;
				if (header.contains(columnName)) {
					indexColoumn = header.indexOf(columnName);

				} else {
					System.out.println("Pls check header is not present in CSV");
				}

				csvBody.get(row)[indexColoumn] = replace;
			} else {
				System.out.println("Pls pass correct Row number");
			}

			reader.close();

			FileWriter outputfile = new FileWriter(fileToUpdate);
			// Write to CSV file which is open
			CSVWriter writer = new CSVWriter(outputfile);
			writer.writeAll(csvBody);
			writer.flush();
			writer.close();

		} catch (Exception e) {
			System.out.println("Exception in Edit CSV method " + e);
		}
	}

	String getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			/** The newest file comes first **/
		//	Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
		return theNewestFile.getName();
	}

		
}
