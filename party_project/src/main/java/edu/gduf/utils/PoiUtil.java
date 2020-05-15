package edu.gduf.utils;

import edu.gduf.enums.FileTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-03-28 16:09
 **/
public class PoiUtil {

    /**
     * 通过判断文件的拓展名来创建相应的工作簿对象
     * @param filepath
     * @return
     */
    private static Workbook getWorkbook(String filepath){
        Workbook workbook = null;
        InputStream in;
        try {
            in = new FileInputStream(filepath);
            if (filepath.contains(FileTypeEnum.XLSX.getFileType())){
                workbook = new XSSFWorkbook(in);
            }else if (filepath.contains(FileTypeEnum.XLS.getFileType())){
                workbook = new HSSFWorkbook(in);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
    
    public static List<List<String>> readFile(String filepath){
        Workbook workbook = getWorkbook(filepath);
        Sheet sheet = workbook.getSheetAt(0);
        List<List<String>> lists = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0){
                continue;
            }
            List<String> list = new ArrayList<>();
            for (Cell cell : row) {
                list.add(cell.toString());
            }
            lists.add(list);
        }
        return lists;
    }
    
    
}
