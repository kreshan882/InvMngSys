/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.cust.bean;

import com.inv.init.Status;
import com.inv.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author epic_kapila
 */
public class EditViewCustomerInputBean {
    

    //delete values
    private String dcustId;
    private boolean dsuccess;
    private String dmessage;
    
    
    //update values
    private String upcustId; 
    private String upcustName;
    private String upemail;
    private String upaddress;
    private String uptpOffice;
    private String uptpMobile;
    private String upstatus;
    private Map<String,String>  usableStatusList=Util.getBasicStatus();
     
    //search table
    private String custName="";
    private boolean search;
    
    
    //grid table
    private List<CustomerBeen> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;

    public String getCustName() {
        return custName;
    }

    public String getUpstatus() {
        return upstatus;
    }

    public String getDcustId() {
        return dcustId;
    }

    public void setDcustId(String dcustId) {
        this.dcustId = dcustId;
    }

    public boolean isDsuccess() {
        return dsuccess;
    }

    public void setDsuccess(boolean dsuccess) {
        this.dsuccess = dsuccess;
    }

    public String getDmessage() {
        return dmessage;
    }

    public void setDmessage(String dmessage) {
        this.dmessage = dmessage;
    }

    public void setUpstatus(String upstatus) {
        this.upstatus = upstatus;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public boolean isSearch() {
        return search;
    }

    public String getUpcustId() {
        return upcustId;
    }

    public void setUpcustId(String upcustId) {
        this.upcustId = upcustId;
    }

    public String getUpcustName() {
        return upcustName;
    }

    public void setUpcustName(String upcustName) {
        this.upcustName = upcustName;
    }


    public String getUpemail() {
        return upemail;
    }

    public void setUpemail(String upemail) {
        this.upemail = upemail;
    }

    public String getUpaddress() {
        return upaddress;
    }

    public void setUpaddress(String upaddress) {
        this.upaddress = upaddress;
    }

    public String getUptpOffice() {
        return uptpOffice;
    }

    public void setUptpOffice(String uptpOffice) {
        this.uptpOffice = uptpOffice;
    }

    public String getUptpMobile() {
        return uptpMobile;
    }

    public void setUptpMobile(String uptpMobile) {
        this.uptpMobile = uptpMobile;
    }

    
    public void setSearch(boolean search) {
        this.search = search;
    }

    public Map<String, String> getUsableStatusList() {
        return usableStatusList;
    }

    public void setUsableStatusList(Map<String, String> usableStatusList) {
        this.usableStatusList = usableStatusList;
    }

    public List<CustomerBeen> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<CustomerBeen> gridModel) {
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
     

    
    

  
    
    
}
