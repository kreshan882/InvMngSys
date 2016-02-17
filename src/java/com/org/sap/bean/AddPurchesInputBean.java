/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KRESHAN
 */
public class AddPurchesInputBean {
    //default load
    private String purchaseId;
    private String storId;
    private Map<String,String>  storIdList= new HashMap<String, String>();
    private String suppId;
    private Map<String,String> suppIdList= new HashMap<String, String>();

    //item load
    private String itemCode;
    private String itemName;
    private String itemCost; //manual enter
    private String itemPrize;
    private String itemQut ;
    private boolean itemfind ;

    //add item
    private boolean itemadd;
    private String message;
    
    //Delete selected item
    private String ditemNo;
    private String dpurNo;
    
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
    private String pdfpurchaseId;

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getStorId() {
        return storId;
    }

    public void setStorId(String storId) {
        this.storId = storId;
    }

    public Map<String, String> getStorIdList() {
        return storIdList;
    }

    public void setStorIdList(Map<String, String> storIdList) {
        this.storIdList = storIdList;
    }

    public String getSuppId() {
        return suppId;
    }

    public void setSuppId(String suppId) {
        this.suppId = suppId;
    }

    public Map<String, String> getSuppIdList() {
        return suppIdList;
    }

    public void setSuppIdList(Map<String, String> suppIdList) {
        this.suppIdList = suppIdList;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getItemPrize() {
        return itemPrize;
    }

    public void setItemPrize(String itemPrize) {
        this.itemPrize = itemPrize;
    }

    public String getItemQut() {
        return itemQut;
    }

    public void setItemQut(String itemQut) {
        this.itemQut = itemQut;
    }

    public boolean isItemfind() {
        return itemfind;
    }

    public void setItemfind(boolean itemfind) {
        this.itemfind = itemfind;
    }

    public boolean isItemadd() {
        return itemadd;
    }

    public void setItemadd(boolean itemadd) {
        this.itemadd = itemadd;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDitemNo() {
        return ditemNo;
    }

    public void setDitemNo(String ditemNo) {
        this.ditemNo = ditemNo;
    }

    public String getDpurNo() {
        return dpurNo;
    }

    public void setDpurNo(String dpurNo) {
        this.dpurNo = dpurNo;
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

    public String getPdfpurchaseId() {
        return pdfpurchaseId;
    }

    public void setPdfpurchaseId(String pdfpurchaseId) {
        this.pdfpurchaseId = pdfpurchaseId;
    }

    public String getInvoiceId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
