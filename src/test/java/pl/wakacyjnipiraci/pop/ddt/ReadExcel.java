package pl.wakacyjnipiraci.pop.ddt;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ReadExcel {
    FileInputStream file;
    XSSFWorkbook wb;
    File src;

    public ReadExcel()  {
        try {
            src = new File("src/rsc/loginData.xlsx");
            file = new FileInputStream(src);
            wb = new XSSFWorkbook(file);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getData(String sheetName, int rowNumber, int cellNumber){
        XSSFSheet sheet = wb.getSheet(sheetName);
        String data = String.valueOf(sheet.getRow(rowNumber).getCell(cellNumber));
        return data;
    }


}
