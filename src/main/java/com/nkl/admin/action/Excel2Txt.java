package com.nkl.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel2Txt {

	//读取Excel转化成Txt保存
	public static String parseExcel(File excelFile,String excelFileName, String saveFile){
		String tip = null;
		FileInputStream is = null;
		Workbook wbs = null;
		try {
			List<List<String>> datas = new ArrayList<List<String>>();
			//读取导入Excel信息
			is = new FileInputStream(excelFile.getAbsolutePath());
			if (excelFileName.endsWith(".xlsx")) {
				wbs = new XSSFWorkbook(is);
			}else{
				wbs = new HSSFWorkbook(is);
			}
			Sheet childSheet = wbs.getSheetAt(0);
			// 前4行是标题，跳过标题 i=4
			for (int i = 4; i <= childSheet.getLastRowNum(); i++) {
				List<String> data = new ArrayList<String>();
				Row row = childSheet.getRow(i);
				if (null != row) {
					//前3列是学生信息
					for (int j = 3; j < row.getLastCellNum(); j++) {
						Cell cell = row.getCell(j);
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						String val = cell.getStringCellValue();
						data.add(val);
					}
					datas.add(data);
				}
			}
			
			File file = new File(saveFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter =new FileWriter(file);
			for (int i = 0; i < datas.size(); i++) {
				List<String> data = datas.get(i);
				for (int j = 0; j < data.size(); j++) {
					fileWriter.write(data.get(j));
					if (j!=data.size()-1) {
						fileWriter.write(" ");
					}
				}
				if (i!=datas.size()-1) {
					fileWriter.write("\r\n");
				}
			}
			fileWriter.flush();
            fileWriter.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tip = "目前后台服务器繁忙，请稍后再试吧";
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return tip;
	}
}
