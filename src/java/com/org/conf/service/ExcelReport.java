/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.conf.service;

import com.inv.db.DBConnection;
import com.inv.util.ExcelCommon;
import com.org.conf.bean.StockBean;
import com.org.conf.bean.StockReportInputBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author kreshan88
 */
public class ExcelReport {
    public static Object generateExcelReport(StockReportInputBean inputBean) throws Exception {
       
        Object returnObject = null;
        
        List <StockBean> dataList = null; 
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        try {
            XSSFWorkbook workbook = ExcelReport.createExcelTopSection();
            dataList = new ArrayList<StockBean>();
            if("0".equals(inputBean.getStoreId())) inputBean.setStoreId("");
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            quary ="SELECT st.ITEM_NO,itm.NAME,str.NAME AS STRNAME, st.COUNT FROM ic_stock st,ic_items itm,mt_store str "
                    + "WHERE st.ITEM_NO=itm.ITEM_NO AND str.STOR_ID=st.STOR_ID AND st.STOR_ID LIKE ? ORDER BY st.ITEM_NO ";
            
            perSt = con.prepareStatement(quary); 
            perSt.setString(1, "%"+inputBean.getStoreId()+"%"); 
            res = perSt.executeQuery();

            while (res.next()) {

                StockBean databean = new StockBean();
                 databean.setItemCode(res.getString("ITEM_NO")); 
                 databean.setItemName(res.getString("NAME"));
                 databean.setStrName(res.getString("STRNAME"));
                 databean.setQty(res.getString("COUNT")); 
                
                 dataList.add(databean);

            }

            workbook = ExcelReport.createExcelTableBodySection(workbook, dataList);

            returnObject = workbook;

        } catch (Exception e) {
            throw e;
        } finally {
            
        }
        return returnObject;
    }

    private static XSSFWorkbook createExcelTopSection() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Non Function Terminal");
        XSSFCellStyle fontBoldedUnderlinedCell = ExcelCommon.getColumnHeadeCell(workbook);

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("Item Code");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        //sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("Irem Name");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(2);
        cell.setCellValue("Shop");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(3);
        cell.setCellValue("Quentity");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        return workbook;
    }

    private static XSSFWorkbook createExcelTableBodySection(XSSFWorkbook workbook, List<StockBean> dataList) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        int excelrow = 1;
        int i = -1;
        while (++i < dataList.size()) {
            Row row = sheet.createRow(excelrow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(dataList.get(i).getItemCode());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(1);
            cell.setCellValue(dataList.get(i).getItemName());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(2);
            cell.setCellValue(dataList.get(i).getStrName());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(3);
            cell.setCellValue(dataList.get(i).getQty());
            cell.setCellStyle(rowColumnCell);



        }

        return workbook;
    }
}
