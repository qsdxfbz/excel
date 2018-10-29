package markting;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class JxlExpExcel {
	
	public static void main(String[] args) throws Exception, WriteException {
		
		String[] title = {"id","name","num"};
		File file = new File("e:/jxl_test.xls");
		try {
			file.createNewFile();
			
			//创建工作簿
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			//创建sheet 页面
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			Label label = null;
			
			//第一行 设置列名
			for(int i=0; i<title.length;i++) {
				label = new Label(i, 0, title[i]);
			
				sheet.addCell(label);
				
			}
			
			//追加数据
			for(int i=1; i<10; i++) {
				label = new Label(0, i,"a"+1);
				sheet.addCell(label);
				label = new Label(1, i,"user"+i);
				sheet.addCell(label);
				label = new Label(2, i,"meal"+1);
				sheet.addCell(label);
			}
			workbook.write();
			workbook.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
