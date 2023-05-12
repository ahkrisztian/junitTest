import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ApachePOI {
	
	public ArrayList<Object[]> getExcelData(String filePath) throws IOException{
		
		ArrayList<Object[]> output = new ArrayList<Object[]>();
		
		FileInputStream is = new FileInputStream(filePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		
		XSSFSheet worksheet = workbook.getSheet("Testform3");
		
		for(Row row : worksheet) {
			if (row.getRowNum() >= 1) {

				Object[] values = new Object[row.getPhysicalNumberOfCells()];

				Iterator<Cell> cellIerator = row.cellIterator();

				int coloumn = 0;
				while (cellIerator.hasNext()) {
					Cell cellValue = cellIerator.next();
					if (cellValue.getCellType() == CellType.STRING) {
						values[coloumn] = cellValue.getStringCellValue();
					} else {
						values[coloumn] = NumberToTextConverter.toText(cellValue.getNumericCellValue());
					}
					coloumn++;
				}
				output.add(values);
			}
		}
		
		return output;
	}
	

}
