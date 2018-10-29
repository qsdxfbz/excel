package markting;

import java.io.File;
import java.io.FileInputStream;
import java.util.stream.Stream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class jxlReadExcel {
	
	public static void main(String[] args) {
		try {
			Workbook workbook = Workbook.getWorkbook(new FileInputStream("e:/杭州1023.xls"));
			//get the first sheet
			Sheet sheet = workbook.getSheet(0);
			//获取数据
			for(int i=0; i<sheet.getRows();i++){
				for(int j=0 ;j<sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,i);
					System.out.print(cell.getContents()+" ");
				}
				System.out.println();
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
