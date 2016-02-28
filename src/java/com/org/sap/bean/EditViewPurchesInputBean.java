/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sap.bean;

import java.util.List;
import java.util.Map;

/**
 *
 * @author kreshan88
 */
public class EditViewPurchesInputBean {
        //cancle
    private String storNo="";
    private boolean success;
    private String message;
    //search table
    private String purNo="";
    private boolean search;
    
   //grid table
    private List<PurchesItem> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;

    //jasoer report data
    Map parameterMap = null;
    List<SaleItem> reportdatalist = null;
    private String filename;
    private String pdfpurchId;

    public String getStorNo() {
        return storNo;
    }

    public void setStorNo(String storNo) {
        this.storNo = storNo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPurNo() {
        return purNo;
    }

    public void setPurNo(String purNo) {
        this.purNo = purNo;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public List<PurchesItem> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<PurchesItem> gridModel) {
        this.gridModel = gridModel;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public Map getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map parameterMap) {
        this.parameterMap = parameterMap;
    }

    public List<SaleItem> getReportdatalist() {
        return reportdatalist;
    }

    public void setReportdatalist(List<SaleItem> reportdatalist) {
        this.reportdatalist = reportdatalist;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPdfpurchId() {
        return pdfpurchId;
    }

    public void setPdfpurchId(String pdfpurchId) {
        this.pdfpurchId = pdfpurchId;
    }


    
    
    
}
