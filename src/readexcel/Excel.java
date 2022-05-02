package readexcel;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excel.HibernateClient;

public class Excel {
	static Object Data[][];
	HibernateClient dm;
	public void process() throws Exception{
		dm = new HibernateClient();
		String path="D:\\WorkSpace2\\hibernateexcel\\Detailed Results.xlsx";
		File file=new File(path);
		//System.out.println(file.exists());
		
		FileInputStream fis=new FileInputStream(file);
		
		XSSFWorkbook workBook=new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheetAt(0); //XSSFSheet sheet = workbook.getSheet("Sheet1");
		//System.out.println("No of Rows present :"+ sheet.getLastRowNum());
		//System.out.println("No of columns present :"+ sheet.getRow(0).getLastCellNum());
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		Data = new Object[rows+1][cols];
		
		for(int r =0;r<=rows;r++) {
			XSSFRow row =sheet.getRow(r);
			for(int c =0;c<cols;c++) {
				XSSFCell cell = row.getCell(c);
				
				switch(cell.getCellType()) {
				case STRING:System.out.print(cell.getStringCellValue());Data[r][c] = (cell.getStringCellValue());break;
				case NUMERIC:System.out.print(cell.getNumericCellValue());Data[r][c] = (cell.getNumericCellValue());break;
				case BOOLEAN:System.out.print(cell.getBooleanCellValue());Data[r][c] = (cell.getBooleanCellValue());break;
				case FORMULA:System.out.println(cell.getNumericCellValue());Data[r][c] = (cell.getNumericCellValue());break;
				default:
					break;
				}
				System.out.print(" | ");
				
			}
			System.out.println();
		}
		
		for(int r =0;r<rows;r++) {
			if(r!=0) {

				dm.setToSql((int)(double)Data[r][0], (int)(double)Data[r][1],(String)Data[r][2], (String)Data[r][3],
						(String)Data[r][4],(int)(double)Data[r][5],(String)Data[r][6],(String)Data[r][7],(int)(double)Data[r][8],
						(int)(double)Data[r][9],(int)(double)Data[r][10],(int)(double)Data[r][11],(int)(double)Data[r][12],(double)Data[r][13]);
			}
		
	    }
	}
	public static void main(String[] args) throws Exception {
		Excel read = new Excel();
		long startTime=System.nanoTime();
		read.process();
		long nTime=System.nanoTime();
		long totalTime=nTime-startTime;
		System.out.println(totalTime);
	}
}
