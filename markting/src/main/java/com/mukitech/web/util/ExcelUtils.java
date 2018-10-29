package com.mukitech.web.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
	public static List<List<String>> readXlsx(String path) throws IOException {
		InputStream input = new FileInputStream(path);
		return readXlsx(input);
	}

	public static List<List<String>> readXls(String path) throws IOException {
		InputStream input = new FileInputStream(path);
		return readXls(input);
	}

	public static List<List<String>> readXlsx(InputStream input) throws IOException {
		List<List<String>> result = new ArrayList<List<String>>();
		XSSFWorkbook workbook = new XSSFWorkbook(input);

		XSSFSheet xssfSheet = workbook.getSheetAt(0);

		if (xssfSheet != null) {
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				
				XSSFRow row = (XSSFRow) xssfSheet.getRow(rowNum);
				int minCellNum = row.getFirstCellNum();
				int maxCellNum = row.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				for (int i = minCellNum; i < maxCellNum; i++) {
					row.getCell(4).setCellType(CellType.STRING);
//					Cell cell = row.getCell(4);
					XSSFCell cell = row.getCell(i);
					if (cell == null) {
						continue;
					}
					rowList.add(cell.toString());
				}
				result.add(rowList);
			}
		}
		return result;
	}

	public static List<List<String>> readXls(InputStream input) throws IOException {
		List<List<String>> result = new ArrayList<List<String>>();
		HSSFWorkbook workbook = new HSSFWorkbook(input);
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet sheet = workbook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				HSSFRow row = sheet.getRow(rowNum);
				int minCellNum = row.getFirstCellNum();
				int maxCellNum = row.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				for (int i = minCellNum; i < maxCellNum; i++) {
					HSSFCell cell = row.getCell(i);
					if (cell == null) {
						continue;
					}
					
					rowList.add(getStringValx(cell));
				}
				result.add(rowList);
			}
		}
		return result;
	}

	private static String getStringVal(HSSFCell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_NUMERIC:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return null;
		}
	}
	
	private static String getStringValx(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_NUMERIC:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return null;
		}
	}

}