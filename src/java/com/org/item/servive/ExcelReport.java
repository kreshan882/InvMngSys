/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.item.servive;

import com.inv.db.DBConnection;
import com.inv.util.ExcelCommon;
import com.org.item.bean.ItemBeen;
import com.org.item.bean.ViewItemInputBean;
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
    public static Object generateExcelReport(ViewItemInputBean inputBean) throws Exception {
       
        Object returnObject = null;
        
        List <ItemBeen> dataList = null; 
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        try {
            XSSFWorkbook workbook = ExcelReport.createExcelTopSection();
            dataList = new ArrayList<ItemBeen>();
           
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            quary ="SELECT ITEM_NO,NAME,COLOUR,UNIT_TCOST,UNIT_PRIZE,STATUS,CAST(REG_DATE AS CHAR) AS REG_DATE FROM ic_items";
            
            perSt = con.prepareStatement(quary); 
            res = perSt.executeQuery();

            while (res.next()) {

                ItemBeen databean = new ItemBeen();
                 databean.setItemNo(res.getString("ITEM_NO")); 
                 databean.setName(res.getString("NAME"));
                 databean.setColour(res.getString("COLOUR"));
                 databean.setUnitTcost(res.getString("UNIT_TCOST"));
                 databean.setUnitPrize(res.getString("UNIT_PRIZE"));
                 databean.setStatusCode(res.getString("STATUS"));
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
        cell.setCellValue("ITEM_NO");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        //sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("NAME");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(2);
        cell.setCellValue("COLOUR");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(3);
        cell.setCellValue("UNIT_TCOST");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(4);
        cell.setCellValue("UNIT_PRIZE");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(5);
        cell.setCellValue("STATUS");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(6);
        cell.setCellValue("REG_DATE");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        return workbook;
    }

    private static XSSFWorkbook createExcelTableBodySection(XSSFWorkbook workbook, List<ItemBeen> dataList) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        int excelrow = 1;
        int i = -1;
        while (++i < dataList.size()) {
            Row row = sheet.createRow(excelrow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(dataList.get(i).getItemNo());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(1);
            cell.setCellValue(dataList.get(i).getName());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(2);
            cell.setCellValue(dataList.get(i).getColour());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(3);
            cell.setCellValue(dataList.get(i).getUnitTcost());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(4);
            cell.setCellValue(dataList.get(i).getUnitPrize());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(5);
            if("01".equals(dataList.get(i).getStatusCode()))  cell.setCellValue("Active");
            else cell.setCellValue("Inactive");
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(6);
            cell.setCellValue(dataList.get(i).getRegDate());
            cell.setCellStyle(rowColumnCell);


        }

        return workbook;
    }
}
