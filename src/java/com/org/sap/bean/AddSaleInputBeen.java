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
 * @author kreshan
 */
public class AddSaleInputBeen {
    private String invoiceId;
    private String storId;
    private Map<String,String>  storIdList= new HashMap<String, String>();
    private String custId;
    private Map<String,String> custIdList= new HashMap<String, String>();

    private String itemCode;
    private String itemName;
    private String itemPrize;
    private String itemQut ;
    private boolean itemfind ;

    
        //grid table
    private List<SaleItem> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;

    public List<SaleItem> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<SaleItem> gridModel) {
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
    
    
    
    
    public boolean isItemfind() {
        return itemfind;
    }

    public void setItemfind(boolean itemfind) {
        this.itemfind = itemfind;
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

    
    
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getStorId() {
        return storId;
    }

    public Map<String, String> getStorIdList() {
        return storIdList;
    }

    public void setStorIdList(Map<String, String> storIdList) {
        this.storIdList = storIdList;
    }

    public Map<String, String> getCustIdList() {
        return custIdList;
    }

    public void setCustIdList(Map<String, String> custIdList) {
        this.custIdList = custIdList;
    }

    public void setStorId(String storId) {
        this.storId = storId;
    }



    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }


    
    
}
