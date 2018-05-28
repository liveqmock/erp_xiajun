package com.wangqin.globalshop.common.utils.excel;

public class ExcelSummary {
	private int SheetIndex;
	private int RowNumber;
	private int ColumnNumber;
	private String[] ColumnTitles;
	
	public int getSheetIndex() {
		return SheetIndex;
	}
	public void setSheetIndex(int sheetIndex) {
		SheetIndex = sheetIndex;
	}
	public int getRowNumber() {
		return RowNumber;
	}
	public void setRowNumber(int rowNumber) {
		RowNumber = rowNumber;
	}
	public int getColumnNumber() {
		return ColumnNumber;
	}
	public void setColumnNumber(int columnNumber) {
		ColumnNumber = columnNumber;
	}
	public String[] getColumnTitles() {
		return ColumnTitles;
	}
	public void setColumnTitles(String[] columnTitles) {
		ColumnTitles = columnTitles;
	}
}
