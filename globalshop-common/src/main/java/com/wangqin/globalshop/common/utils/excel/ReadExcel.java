package com.wangqin.globalshop.common.utils.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/19
 */
public class ReadExcel {
    public ReadExcel() {
    }

    public static List<List<Object>> readExcel(File file) throws IOException {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {
            return read2003Excel(file, 0, 0, 0);
        } else if ("xlsx".equals(extension)) {
            return read2007Excel(file, 0, 0, 0);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }

    public static List<List<Object>> readExcel(File file, int beginRow, int beginRolumn, int endRolumn) throws IOException {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {
            return read2003Excel(file, beginRow, beginRolumn, endRolumn);
        } else if ("xlsx".equals(extension)) {
            return read2007Excel(file, beginRow, beginRolumn, endRolumn);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }

    private static List<List<Object>> read2007Excel(File file, int beginRow, int beginRolumn, int endRolumn) throws IOException {
        List<List<Object>> list = new ArrayList();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = xwb.getSheetAt(0);
        Object value = null;
        XSSFRow row = null;
        XSSFCell cell = null;
        int counter = 0;

        for (int i = beginRow == 0 ? sheet.getFirstRowNum() : beginRow; i < sheet.getPhysicalNumberOfRows(); ++i) {
            int countcell = 0;
            int nullcell = 0;
            row = sheet.getRow(i);
            if (row != null) {
                ++counter;
                List<Object> ll = new ArrayList();

                for (int j = beginRolumn; j <= (endRolumn == 0 ? row.getLastCellNum() - 1 : endRolumn); ++j) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        ll.add("");
                    } else {
                        DecimalFormat df = new DecimalFormat("0");
                        DecimalFormat nf = new DecimalFormat("0.00");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        switch (cell.getCellType()) {
                            case 0:
                                if (cell.getCellStyle().getDataFormatString().indexOf("0.00_") != -1) {
                                    value = nf.format(cell.getNumericCellValue());
                                } else if (cell.getCellStyle().getDataFormatString().toString().indexOf("#,##0.00_") != -1) {
                                    value = nf.format(cell.getNumericCellValue());
                                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                                } else {
                                    value = df.format(cell.getNumericCellValue());
                                }
                                break;
                            case 1:
                                value = cell.getStringCellValue();
                                break;
                            case 2:
                                try {
                                    value = String.valueOf(cell.getNumericCellValue());
                                } catch (IllegalStateException var20) {
                                    value = String.valueOf(cell.getRichStringCellValue());
                                }
                                break;
                            case 3:
                                value = "";
                                break;
                            case 4:
                                value = cell.getBooleanCellValue();
                                break;
                            default:
                                value = cell.toString();
                        }

                        if (value == null || "0.0".equals(value) || "".equals(value)) {
                            ++nullcell;
                        }

                        ll.add(value);
                        ++countcell;
                    }
                }

                if (countcell != nullcell) {
                    list.add(ll);
                }
            }
        }

        file.delete();
        return list;
    }

    private static List<List<Object>> read2003Excel(File file, int beginRow, int beginRolumn, int endRolumn) throws IOException {
        List<List<Object>> list = new ArrayList();
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = hwb.getSheetAt(0);
        Object value = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        int counter = 0;

        for (int i = beginRow == 0 ? sheet.getFirstRowNum() : beginRow; i < sheet.getPhysicalNumberOfRows(); ++i) {
            int countcell = 0;
            int nullcell = 0;
            row = sheet.getRow(i);
            if (row != null) {
                ++counter;
                List<Object> ll = new ArrayList();

                for (int j = beginRolumn; j <= (endRolumn == 0 ? row.getLastCellNum() - 1 : endRolumn); ++j) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        ll.add("");
                    } else {
                        DecimalFormat df = new DecimalFormat("0");
                        DecimalFormat nf = new DecimalFormat("0.00");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        switch (cell.getCellType()) {
                            case 0:
                                if (cell.getCellStyle().getDataFormatString().indexOf("0.00_") != -1) {
                                    value = nf.format(cell.getNumericCellValue());
                                } else if (cell.getCellStyle().getDataFormatString().toString().indexOf("#,##0.00_") != -1) {
                                    value = nf.format(cell.getNumericCellValue());
                                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                                } else {
                                    value = df.format(cell.getNumericCellValue());
                                }
                                break;
                            case 1:
                                value = cell.getStringCellValue();
                                break;
                            case 2:
                                try {
                                    value = String.valueOf(cell.getNumericCellValue());
                                } catch (IllegalStateException var20) {
                                    value = String.valueOf(cell.getRichStringCellValue());
                                }
                                break;
                            case 3:
                                value = "";
                                break;
                            case 4:
                                value = cell.getBooleanCellValue();
                                break;
                            default:
                                value = cell.toString();
                        }

                        if (value == null || "0.0".equals(value) || "".equals(value)) {
                            ++nullcell;
                        }

                        ll.add(value);
                        ++countcell;
                    }
                }

                if (countcell != nullcell) {
                    list.add(ll);
                }
            }
        }

        file.delete();
        return list;
    }
}
