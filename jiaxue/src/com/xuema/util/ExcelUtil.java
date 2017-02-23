package com.xuema.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExcelUtil {
	public static byte[] generateExcel(String[] head, List<Object[]> data)
			throws IOException, RowsExceededException, WriteException {
		ByteArrayOutputStream os1 = new ByteArrayOutputStream();
		WritableWorkbook excel = Workbook.createWorkbook(os1);
		WritableSheet s = excel.createSheet("yao", 1);
		int c = 0;
		for (String name : head) {
			Label l = new Label(c, 0, name);
			s.addCell(l);
			c++;
		}
		int length = data.size();
		for (int i = 1; i <= length; i++) {
			Object[] os = data.get(i - 1);
			c = 0;
			for (Object v : os) {

				if (v instanceof String) {
					Label l = new Label(c, i, v.toString());
					WritableCellFormat cf = new WritableCellFormat();
					cf.setWrap(true);
					l.setCellFormat(cf);
					s.addCell(l);
				} else if (v instanceof Integer) {
					jxl.write.Number n = new jxl.write.Number(c, i, (Integer) v);
					s.addCell(n);
				} else if (v instanceof BigInteger) {
					jxl.write.Number n = new jxl.write.Number(c, i,
							((BigInteger) v).intValue());
					s.addCell(n);
				} else if (v instanceof Date) {
					jxl.write.DateTime d = new jxl.write.DateTime(c, i,
							(Date) v);
					s.addCell(d);
				} else if (v instanceof Boolean) {
					jxl.write.Boolean b = new jxl.write.Boolean(c, i,
							(Boolean) v);
					s.addCell(b);
				} else if (v instanceof Double) {
					jxl.write.Number b = new jxl.write.Number(c, i, (Double) v);
					s.addCell(b);
				} else if (v instanceof BigDecimal) {
					jxl.write.Number b = new jxl.write.Number(c, i,
							((BigDecimal) v).doubleValue());
					s.addCell(b);
				}
				c++;
			}
		}
		excel.write();
		excel.close();
		return os1.toByteArray();
	}

	public static void main(String[] args) throws Exception {
		tableToCSV();
	}

	public static void tableToCSV() throws SQLException, RowsExceededException,
			WriteException, IOException {
		
	}

	public static void writeRS2File(ResultSet rs, String filePath)
			throws SQLException, RowsExceededException, WriteException,
			IOException {
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();
		String[] header = new String[num];
		for (int i = 1; i <= num; i++) {
			String cName = md.getColumnName(i);
			header[i - 1] = cName;
		}
		List<Object[]> values = new ArrayList<Object[]>();

		while (rs.next()) {
			Object[] value = new Object[num];
			for (int i = 1; i <= num; i++) {
				value[i - 1] = rs.getObject(i);
			}
			values.add(value);
		}
		byte[] result = generateExcel(header, values);

		File f = new File(filePath);
		OutputStream os = new FileOutputStream(f);
		os.write(result);
		os.close();
	}

	public static void writeRS2TXT(ResultSet rs, String filePath,
			String delimiter) throws SQLException, RowsExceededException,
			WriteException, IOException {
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();

		File f = new File(filePath);
		OutputStream os = new FileOutputStream(f);

		String delim = "";
		for (int i = 1; i <= num; i++) {
			String cName = md.getColumnName(i);
			os.write(delim.getBytes());
			os.write(cName.getBytes("utf-8"));
			delim = delimiter;
		}
		os.write("\r\n".getBytes());
		while (rs.next()) {
			delim = "";
			for (int i = 1; i <= num; i++) {
				os.write(delim.getBytes());
				os.write(rs.getObject(i).toString().getBytes("utf-8"));
				delim = delimiter;
			}
			os.write("\r\n".getBytes());
		}
		os.close();
	}

	public static List<String[]> readExcel(String fileName) throws BiffException, IOException {
		List<String[]> results = new ArrayList<String[]>();
		Workbook book = Workbook.getWorkbook(new File(fileName));
		// 获得第一个工作表对象
		// Sheet sheet = book.getSheet( 0 );
		Sheet[] sheets = book.getSheets();

		for (Sheet sheet : sheets) {
			int columnum = sheet.getColumns(); // 得到列数
			int rownum = sheet.getRows(); // 得到行数
			//System.out.println(columnum);
			//System.out.println(rownum);
			for (int i = 0; i < rownum; i++) // 循环进行读写
			{
				String[] columns= new String[columnum];
				for (int j = 0; j < columnum; j++) {
					Cell cell1 = sheet.getCell(j, i);
					String result = cell1.getContents();
					//System.out.print(result);
					//System.out.print(" \t ");
					columns[j] = result;
				}
				results.add(columns);
				//System.out.println();
			}
		}
		book.close();
		return results;
	}
	
	public static List<Object[]> ExcelToStringArray(byte[] content)
			throws InvalidFormatException, IOException {
		InputStream is = new ByteArrayInputStream(content);
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(is);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(createHelper.createDataFormat().getFormat(
				DatetimeUtil.STANDARD_DATETIME_PATTERN));

		List<Object[]> list = new ArrayList<Object[]>();
		Object[] val = null;

		int len = 0;
		if (null != sheet) {
			for (Row row : sheet) {

				if (row.equals(sheet.getRow(0))) {
					len = row.getLastCellNum();
					continue;
				}
				val = new Object[len];

				for (int c = 0; c < len; c++) {
					org.apache.poi.ss.usermodel.Cell cell = row.getCell(c);

					String valStr = "";
					if (null != cell) {
						switch (cell.getCellType()) {// 判断内容类型

						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC: // 数字类型
							if (DateUtil.isCellDateFormatted(cell)) {// 判断是否是日期
								cell.setCellStyle(dateStyle);
								valStr = String
										.valueOf(cell.getDateCellValue());
							} else {
								valStr = String.valueOf(
										cell.getNumericCellValue()).replaceAll(
										"\\.0*$", "");
							}
							break;
						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN: // 布尔类型
							valStr = String.valueOf(cell.getBooleanCellValue());
							break;
						default:
							valStr = cell.getRichStringCellValue().getString();
						}
					}
					val[c] = valStr;
				}

				list.add(val);
			}
		}

		return list;
	}
}
