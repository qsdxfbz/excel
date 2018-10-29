package markting;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiReadExcel {
	public static void main(String[] args) {
		
		//引入要解析的excel文件
		File file = new File("e:/杭州1023.xlsx");
//		File file = new File("e:/sfgs01.xlsx");
		try {
			//创建Excel 读取文件 内容
			XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
			//读取默认第一张工作表
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			System.out.println(sheet.toString());
			//
			int firstRowNum = 0;
			//获取sheet 中最后一行
			int lastRowNum = sheet.getLastRowNum();
			
			System.out.println(lastRowNum);
			
			for(int i=firstRowNum; i<lastRowNum; i++) {
				
				XSSFRow row = sheet.getRow(i);
				//获取当前行最后单元格列号
				int lastCellNum = row.getLastCellNum();
				System.out.println(lastCellNum);
				for(int j=0; j<lastCellNum;j++) {
					
					XSSFCell cell = row.getCell(j);
					
//					if (!(cell.getCellType()==XSSFCell.CELL_TYPE_BLANK)) {
//						CellType type = cell.getCellTypeEnum();
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						String value = cell.getStringCellValue();
						
						System.out.print(value + " ");
//					}
					
				}
				System.out.println();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
//		File file1 = new File("e:/test.xls");
//		try {
//			//创建Excel 读取文件 内容
//			HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file1));
//			//读取默认第一张工作表
//			HSSFSheet sheet = workbook.getSheetAt(0);
//			//
//			int firstRowNum = 0;
//			//获取sheet 中最后一行
//			int lastRowNum = sheet.getLastRowNum();
//			for(int i=0; i<lastRowNum ;i++) {
//				
//				HSSFRow row = sheet.getRow(i);
//				//获取当前行最后单元格列号
//				int lastCellNum = row.getLastCellNum();
//				for(int j=0; j<lastCellNum ;j++) {
//					
//					HSSFCell cell = row.getCell(j);
//					String value = cell.getStringCellValue();
//					System.out.println(value + "");
//				}
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}
}
