package utility;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Exceldata {

	
	@DataProvider(name = "testdata")
	public Object[][] ExcelDatas() throws BiffException, IOException, Exception {

		String filepath = (System.getProperty("user.dir") +"\\test resources\\excel\\testdata.xlsx");

		// Read Excel Data
		FileInputStream Excelfile = new FileInputStream(filepath);

		// Read Workbook
		Workbook Exbook = Workbook.getWorkbook(Excelfile);

		// Read Sheet
		Sheet Exsheet = Exbook.getSheet("Login Data");

		// Read rows and columns in a sheet
		int Rows = Exsheet.getRows();
		int Columns = Exsheet.getColumns();

		// Save both Rows and columns in String array
		String Testdata[][] = new String[Rows - 1][Columns];

		int count = 0;

		// For loop to get the cell data
		for (int i = 1; i < Rows; i++) {
			for (int j = 0; j < Columns; j++) {
				Cell Excell = Exsheet.getCell(j, i);
				// get contents from the string array
				Testdata[count][j] = Excell.getContents();

			}
			count++;

		}

		Excelfile.close();
		return Testdata;
	}

}
