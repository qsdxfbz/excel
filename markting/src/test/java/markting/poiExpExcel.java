package markting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class poiExpExcel 
{
	public static void main(String[] args) 
	{

		// 工作空间
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 页
		HSSFSheet sheet = workbook.createSheet();
		// 第一行
		HSSFRow row = sheet.createRow(0);

		// 单元格子
		HSSFCell cell = null;
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("杭州苏宁易购便利店有限公司东方丽都花苑店");

		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("郁义龙");

		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("其他日用品零售");

		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("浙江省杭州市江干区东方丽都花苑1幢商铺8号");

		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("0571-26890000");

		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("2018/10/23");

		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("20181023001");
		
		File file = new File("e:/test.xlsx");
		
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public String serialNum() 
	{
		Long time = System.currentTimeMillis();
		String str = time.toString();
		return str;
	}

}
