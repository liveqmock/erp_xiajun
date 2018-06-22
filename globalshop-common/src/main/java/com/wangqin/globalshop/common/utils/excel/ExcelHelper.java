package com.wangqin.globalshop.common.utils.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

/**
 * Operate Excel helper class
 * Title: ExcelHelper.java
 * Description: 
 *
 * @author jc
 * Apr 1, 2017
 *
 */
@Service("excelHelper")
public class ExcelHelper {

	private Workbook wbRead;
	private Workbook wbWrite;
	
	public ExcelHelper() {	
	}
	
	public boolean openRead(String filepath) throws Exception {

		if(null == filepath){
            return false;  
        }
		
        String ext = filepath.substring(filepath.lastIndexOf("."));
        
        if ((".xls".equals(ext)) || (".xlsx".equals(ext))) {
            try {
                InputStream is = new FileInputStream(filepath);  
                if(".xls".equals(ext)){
                    wbRead = new HSSFWorkbook(is);  
                } else {  
                    wbRead = new XSSFWorkbook(is);  
                }
            } catch (FileNotFoundException e) {
            	throw e;
            } catch (IOException e) {  
            	throw e;
            }
		} else {
            wbRead=null;
            
            throw new Exception("Unrecognized file");
		}

        return true;
	}

	public void close() {
		if (null != wbRead) {
			wbRead = null;
		}
		
		if (null != wbWrite) {
			wbWrite = null;
		}
	}
	
	/**
	 *  Get excel file summary information
	 * @return
	 * @throws Exception
	 */
    public ExcelSummary[] getExcelSummaries() throws Exception{
    	if(null == wbRead){  
            throw new NullPointerException("Workbook");
        }
        
        int sheetNumber = wbRead.getNumberOfSheets();
        if (0 == sheetNumber) {
			return null;
		}
        
        List<ExcelSummary> listExcelSummaries = new ArrayList<>();
        
        Sheet sheet;
        Row row;
        ExcelSummary excelSummary;
        String[] columnTitles;
        int rowNumber = 0;
        for (int i = 0; i < sheetNumber; i++) {
			sheet = wbRead.getSheetAt(i);
			if (null == sheet) {
				continue;
			}
			
			rowNumber = sheet.getLastRowNum();
			if (rowNumber > 0) {
				excelSummary = new ExcelSummary();
				//getLastRowNum: Last row index
				excelSummary.setRowNumber(sheet.getLastRowNum() + 1);
				
				row = sheet.getRow(0);
				excelSummary.setColumnNumber(row.getPhysicalNumberOfCells());
				
				columnTitles = new String[excelSummary.getColumnNumber()];
				for (int j = 0; j < columnTitles.length; j++) {
					columnTitles[j] = row.getCell(j).getStringCellValue();
				}
				excelSummary.setColumnTitles(columnTitles);

				listExcelSummaries.add(excelSummary);
			}
		}
        
        ExcelSummary[] excelSummaries = new ExcelSummary[listExcelSummaries.size()];
        return listExcelSummaries.toArray(excelSummaries); 
    }  
  
    /**
     * Read excel content
     * @param sheetIndex
     * @param startRowIndex
     * @param length
     * @return
     * @throws Exception
     */
    public Map<Integer, Map<Integer,Object>> readSheetContent(ExcelSummary excelSummary, int startRowIndex, int length) throws Exception{  
        if(null == wbRead){  
            throw new NullPointerException("Workbook");
        }
        
        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();  
          
        Sheet sheet = wbRead.getSheetAt(excelSummary.getSheetIndex());
        if (null == sheet) {
			return content;
		}
        
        if (excelSummary.getRowNumber() <= startRowIndex + length) {
			return content;
		}
        
        Row row;
        //First row is title, 
        for (int i = startRowIndex; i < startRowIndex + length; i++) {  
            row = sheet.getRow(i); 
            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();  
            int columnNumber = excelSummary.getColumnNumber();
            for (int j = 0; j < columnNumber; j++) {
				cellValue.put(j, getCellFormatValue(row.getCell(j)));
			}
            
            content.put(i, cellValue);  
        }  
        
        return content;  
    }  
  
    /**
     * Set cell data by type
     * @param cell
     * @return
     */
    private Object getCellFormatValue(Cell cell) {  
        Object cellvalue = "";
        
        if (cell == null) { 
        	return cellvalue;
        }
        
        switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC:
            	//Numeric
            case Cell.CELL_TYPE_FORMULA: { 
                if (DateUtil.isCellDateFormatted(cell)) {  
                	//Date: 2013-7-10 11:00:00
                    Date date = cell.getDateCellValue();  
                    cellvalue = date;  
                } else {
                	// Numeric
                    cellvalue = String.valueOf(cell.getNumericCellValue());  
                }  
                break;  
            }  
            case Cell.CELL_TYPE_STRING:
            	// String 
                cellvalue = cell.getRichStringCellValue().getString();  
                break;  
            default:
            	// default cell
                cellvalue = "";  
        }
        
        return cellvalue;  
    }
    
    //Write    
    public boolean setToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas) {
		
    	boolean bIsSuccess = true;
    	
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	Row row = sheet.createRow(0);
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        	
        	//String[] propertyNames = new String[]{"id","outerOrderId","outerDetailOrderId","targetNo","orderNo","erpNo","status","stockStatus","upc","skuId","skuCode","itemId","itemName","color","scale","freight","salePrice","quantity","shippingOrderId","gmtCreate","gmtModify","userCreate","userModify","receiver","addressDetail","telephone","postcode","remark","warehouseName","receiverState","receiverCity","receiverDistrict","idCard","purchasePrice"};
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		List<Object> rowData = rowDatas.get(i);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(i);
        			cell.setCellValue(columnTitles[i]);
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {			
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    
    /**
     * 定制导出采购单excel 
     * @param sheetName		sheet名称
     * @param columnTitles	首行标题
     * @param rowDatas		单元格数据
     * @param columnWidth	单元宽度
     * @return
     */
    public boolean setTaskDailyToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	
        	
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(100);
        		List<Object> rowData = rowDatas.get(i-1);
        		int rowDataSize = rowData.size();
        		for (int j = 0; j < rowDataSize; j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j >= 7 && j<= 8) {
        					cell.setCellValue(Double.valueOf(rowData.get(j).toString()));
        				} else {
        					cell.setCellValue(rowData.get(j).toString());
        				}
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    
    /**
     * 定制导出采购单excel 
     * @param sheetName		sheet名称
     * @param columnTitles	首行标题
     * @param rowDatas		单元格数据
     * @param columnWidth	单元宽度
     * @return
     */
    public boolean setNoCompleteTaskDailyToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	
        	
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(100);
        		List<Object> rowData = rowDatas.get(i-1);
        		int rowDataSize = rowData.size();
        		for (int j = 0; j < rowDataSize; j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j == 7 ) {
        					cell.setCellValue(Integer.valueOf(rowData.get(j).toString()));
        				} else {
        					cell.setCellValue(rowData.get(j).toString());
        				}
        				
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    
    /**
     * 定制导出入库单excel 
     * @param sheetName		sheet名称
     * @param columnTitles	首行标题
     * @param rowDatas		单元格数据
     * @param columnWidth	单元宽度
     * @return
     */
    public boolean setPurchaseStorageToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	
        	
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(100);
        		List<Object> rowData = rowDatas.get(i-1);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j>=7 && j<=9){
        					cell.setCellValue(Double.parseDouble(rowData.get(j).toString()));
        				}else{
        					cell.setCellValue(rowData.get(j).toString());
        				}        					        				
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    
    /**
     * 定制导出发货单excel 
     * @param sheetName		sheet名称
     * @param columnTitles	首行标题
     * @param rowDatas		单元格数据
     * @param columnWidth	单元宽度
     * @return
     */
    public boolean setShippingOrderToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	
        	
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(90);
        		List<Object> rowData = rowDatas.get(i-1);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j==7){
        					cell.setCellValue(Double.parseDouble(rowData.get(j).toString()));
        				}else{
        					cell.setCellValue(rowData.get(j).toString());
        				}        				
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    
    public boolean setShippingOrderPackageToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	
        	
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(45);
        		List<Object> rowData = rowDatas.get(i-1);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				/*if(j==7){
        					cell.setCellValue(Double.parseDouble(rowData.get(j).toString()));
        				}else{*/
        					cell.setCellValue(rowData.get(j).toString());
        				//}        				
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    /**
     * 定制导出主订单excel 
     * @param sheetName		sheet名称
     * @param columnTitles	首行标题
     * @param rowDatas		单元格数据
     * @param columnWidth	单元宽度
     * @return
     */
    public boolean setOuterOrderToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(30);
        		List<Object> rowData = rowDatas.get(i-1);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j==2) {
        					cell.setCellValue(Double.parseDouble(rowData.get(j).toString()));
        				} else {
        					cell.setCellValue(rowData.get(j).toString());
        				}
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    
    /**
     * 定制导出子订单excel 
     * @param sheetName		sheet名称
     * @param columnTitles	首行标题
     * @param rowDatas		单元格数据
     * @param columnWidth	单元宽度
     * @return
     */
    public boolean setErpOrderToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	cellStyle.setWrapText(true);
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(60);
        		List<Object> rowData = rowDatas.get(i-1);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j>=8 && j<=10) {
        					cell.setCellValue(Double.parseDouble(rowData.get(j).toString()));
        				} else {
        					cell.setCellValue(rowData.get(j).toString());
        				}        				
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    
    public boolean setItemToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	cellStyle.setWrapText(true);
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(60);
        		List<Object> rowData = rowDatas.get(i-1);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j==5) {
        					cell.setCellValue(Double.parseDouble(rowData.get(j).toString()));
        				} else {
        					cell.setCellValue(rowData.get(j).toString());
        				}        				
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
   
    /**
     * 定制导出退货订单excel 
     * @param sheetName		sheet名称
     * @param columnTitles	首行标题
     * @param rowDatas		单元格数据
     * @param columnWidth	单元宽度
     * @return
     */
    public boolean setErpReturnOrderToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	cellStyle.setWrapText(true);
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(60);
        		List<Object> rowData = rowDatas.get(i-1);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j>=9 && j<=10) {
        					cell.setCellValue(Double.parseDouble(rowData.get(j).toString()));
        				} else if(j>=11 && j<=12) {
        					if("1".equals(rowData.get(j).toString())) {
        						cell.setCellValue(rowData.get(j).toString().replace("1", "是"));
        					} else {
        						cell.setCellValue(rowData.get(j).toString().replace("0", "否"));
        					}      					
        					
        				} else {
        					cell.setCellValue(rowData.get(j).toString());        					
        				}        				
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    /**
     * 定制库存excel 
     * @param sheetName		sheet名称
     * @param columnTitles	首行标题
     * @param rowDatas		单元格数据
     * @param columnWidth	单元宽度
     * @return
     */
    public boolean setInventoryAreaToSheet(String sheetName, String[] columnTitles, List<List<Object>> rowDatas, Integer[] columnWidth) {
    	boolean bIsSuccess = true;
    	if (null == wbWrite) {
    		wbWrite = new XSSFWorkbook();
		}
    	try {
        	Sheet sheet = wbWrite.createSheet(sheetName);
        	
        	//设置单元格样式
        	CellStyle cellStyle = wbWrite.createCellStyle();
        	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        	cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        	cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        	cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        	cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        	cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        	cellStyle.setWrapText(true);
        	
        	//第一行
        	Row row = sheet.createRow(0);
        	row.setHeightInPoints(30);
        	for (int i = 0; i < columnTitles.length; i++) {
    			Cell cell = row.createCell(i);
    			cell.setCellValue(columnTitles[i]);
    			cell.setCellStyle(cellStyle);
    		}
        	//图片相关准备
        	CreationHelper helper = wbWrite.getCreationHelper();
        	Drawing drawing = sheet.createDrawingPatriarch();
        	
        	//第二行开始
        	for (int i = 1; i <= rowDatas.size(); i++) {
        		row = sheet.createRow(i);
        		//设置高度
        		row.setHeightInPoints(60);
        		List<Object> rowData = rowDatas.get(i-1);
        		for (int j = 0; j < rowData.size(); j++) {
        			Cell cell = row.createCell(j);
        			//设置宽度
        			sheet.setColumnWidth(j, columnWidth[j]*256);
        			
        			if(rowData.get(j) == null) {
        				cell.setCellValue("");
        			} else if(rowData.get(j) instanceof Date) {	//日期
        				//cell.setCellValue(com.wangqin.util.DateUtil.formatDate((Date)rowData.get(j)));
        			} else if(rowData.get(j) instanceof byte[]) {	//图片
        				int pictureIdx = wbWrite.addPicture((byte[])rowData.get(j), Workbook.PICTURE_TYPE_PNG);
        				ClientAnchor anchor = helper.createClientAnchor();
        				anchor.setCol1(j);
        				anchor.setRow1(i);
        				anchor.setCol2(j+1);
        				anchor.setRow2(i+1);
        				Picture pict = drawing.createPicture(anchor, pictureIdx);  
        				//pict.resize(0.5); 设置了这个，图片就不适应单元格了
        			} else {
        				if(j>=9 && j<=14 && StringUtil.isNotBlank(rowData.get(j).toString())) {
        					cell.setCellValue(Double.parseDouble(rowData.get(j).toString()));
        				} else {
        					cell.setCellValue(rowData.get(j).toString());
        				}        				
        			}
        			cell.setCellStyle(cellStyle);
    			}
    		}
		} catch (Exception e) {
			bIsSuccess = false;
			e.printStackTrace();
		}
    	
		return bIsSuccess;
	}
    
    public Boolean writeToFile(String filePath) {
    	
    	try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wbWrite.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            return false;
        }
    	
    	return true;
	}
    
    public ByteArrayOutputStream writeToByteArrayOutputStream() {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	try {
			wbWrite.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    	
    	return out;
    }
}
