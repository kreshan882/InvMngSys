/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.cust.service;

import com.inv.db.DBConnection;
import com.inv.util.ExcelCommon;
import com.org.cust.bean.CustomerBeen;
import com.org.cust.bean.ViewCustomerInputBean;
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
 * @author kreshan
 */
public class ExcelReport {
    public static Object generateExcelReport(ViewCustomerInputBean inputBean) throws Exception {
       
        Object returnObject = null;
        
        List <CustomerBeen> dataList = null; 
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        try {
            XSSFWorkbook workbook = ExcelReport.createExcelTopSection();
            dataList = new ArrayList<CustomerBeen>();
           
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            quary ="SELECT CUS_ID,NAME,EMAIL,ADDRESS,TP_OFFICE,TP_MOBILE,STATUS,CAST(REG_DATE AS CHAR) AS REG_DATE FROM ic_customer";
            
            perSt = con.prepareStatement(quary); 
            res = perSt.executeQuery();

            while (res.next()) {

                CustomerBeen databean = new CustomerBeen();
                 databean.setCustId(res.getString("CUS_ID")); 
                 databean.setCustName(res.getString("NAME"));
                 databean.setEmail(res.getString("EMAIL"));
                 databean.setAddress(res.getString("ADDRESS"));
                 databean.setTpOffice(res.getString("TP_OFFICE"));
                 databean.setTpMobile(res.getString("TP_MOBILE"));
                 databean.setRegDate(res.getString("REG_DATE"));  
                
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
        cell.setCellValue("CUS_ID");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        //sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("NAME");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(2);
        cell.setCellValue("EMAIL");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(3);
        cell.setCellValue("ADDRESS");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(4);
        cell.setCellValue("TP_OFFICE");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(5);
        cell.setCellValue("TP_MOBILE");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(6);
        cell.setCellValue("REG_DATE");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        return workbook;
    }

    private static XSSFWorkbook createExcelTableBodySection(XSSFWorkbook workbook, List<CustomerBeen> dataList) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        int excelrow = 1;
        int i = -1;
        while (++i < dataList.size()) {
            Row row = sheet.createRow(excelrow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(dataList.get(i).getCustId());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(1);
            cell.setCellValue(dataList.get(i).getCustName());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(2);
            cell.setCellValue(dataList.get(i).getEmail());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(3);
            cell.setCellValue(dataList.get(i).getAddress());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(4);
            cell.setCellValue(dataList.get(i).getTpOffice());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(5);
            cell.setCellValue(dataList.get(i).getTpMobile());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(6);
            cell.setCellValue(dataList.get(i).getRegDate());
            cell.setCellStyle(rowColumnCell);


        }

        return workbook;
    }
}
