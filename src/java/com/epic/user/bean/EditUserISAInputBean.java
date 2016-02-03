/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epic.user.bean;

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
public class EditUserISAInputBean {
    
    private Map<Integer,String>  instituteList=new HashMap<Integer,String>();
    //private List<UserISADataBean> gridModel=new ArrayList<UserISADataBean>();
    
    private Map<String,String>  usableStatusList=Util.getBasicStatus();
    
    private List<String> usernameList=new ArrayList<String>();
    
    private String institute;

    private String username;
    private String delsuccess;
    
    private String company;
    private String status;
    private String statusCode;

    
    private String upstatus;    
    private int  upinstituteID;    
    private String upusername;
    private String upcompany;
    private String upusername2;
    private String message;
     
     
         /*------------------------list data table  ------------------------------*/
    private List<EditViewUserISADataBean> gridModel=new ArrayList<EditViewUserISADataBean>();
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
     
     private int instituteID;
    
     private boolean search;

     
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     
    public String getUpusername2() {
        return upusername2;
    }

    public void setUpusername2(String upusername2) {
        this.upusername2 = upusername2;
    }
    
    public String getUpstatus() {
        return upstatus;
    }

    public void setUpstatus(String upstatus) {
        this.upstatus = upstatus;
    }
            

    
    /**
     * @return the gridModel
     */
    public List<EditViewUserISADataBean> getGridModel() {
        return gridModel;
    }

    /**
     * @param gridModel the gridModel to set
     */
    public void setGridModel(List<EditViewUserISADataBean> gridModel) {
        this.gridModel = gridModel;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }



    public Map<Integer, String> getInstituteList() {
        return instituteList;
    }

    public void setInstituteList(Map<Integer, String> instituteList) {
        this.instituteList = instituteList;
    }


    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the delsuccess
     */
    public String getDelsuccess() {
        return delsuccess;
    }

    /**
     * @param delsuccess the delsuccess to set
     */
    public void setDelsuccess(String delsuccess) {
        this.delsuccess = delsuccess;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
   
    /**
     * @return the search
     */
    public boolean isSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(boolean search) {
        this.search = search;
    }

    /**
     * @return the instituteID
     */
 
    /**
     * @return the usableStatusList
     */
    public Map<String,String> getUsableStatusList() {
        return usableStatusList;
    }

    /**
     * @param usableStatusList the usableStatusList to set
     */
    public void setUsableStatusList(Map<String,String> usableStatusList) {
        this.usableStatusList = usableStatusList;
    }

    /**
     * @return the instituteID
     */
    public int getInstituteID() {
        return instituteID;
    }

    /**
     * @param instituteID the instituteID to set
     */
    public void setInstituteID(int instituteID) {
        this.instituteID = instituteID;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the upinstituteID
     */
    public int getUpinstituteID() {
        return upinstituteID;
    }

    /**
     * @param upinstituteID the upinstituteID to set
     */
    public void setUpinstituteID(int upinstituteID) {
        this.upinstituteID = upinstituteID;
    }

    /**
     * @return the upUsername
     */
   
    /**
     * @return the upCompany
     */
 

    /**
     * @return the upusername
     */
     

    /**
     * @return the upcompany
     */
    public String getUpcompany() {
        return upcompany;
    }

    /**
     * @param upcompany the upcompany to set
     */
    public void setUpcompany(String upcompany) {
        this.upcompany = upcompany;
    }

    /**
     * @return the upusername
     */
    public String getUpusername() {
        return upusername;
    }

    /**
     * @param upusername the upusername to set
     */
    public void setUpusername(String upusername) {
        this.upusername = upusername;
    }

    /**
     * @return the rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(Integer rows) {
       
        this.rows = rows;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the records
     */
    public Long getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(Long records) {
        this.records = records;
    }

    /**
     * @return the sord
     */
    public String getSord() {
        return sord;
    }

    /**
     * @param sord the sord to set
     */
    public void setSord(String sord) {
        this.sord = sord;
    }

    /**
     * @return the sidx
     */
    public String getSidx() {
        return sidx;
    }

    /**
     * @param sidx the sidx to set
     */
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    /**
     * @return the searchField
     */
    public String getSearchField() {
        return searchField;
    }

    /**
     * @param searchField the searchField to set
     */
    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    /**
     * @return the searchString
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * @param searchString the searchString to set
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    /**
     * @return the searchOper
     */
    public String getSearchOper() {
        return searchOper;
    }

    /**
     * @param searchOper the searchOper to set
     */
    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }
    
    /**
     * @return the usernameList
     */
    public List<String> getUsernameList() {
        return usernameList;
    }

    /**
     * @param usernameList the usernameList to set
     */
    public void setUsernameList(List<String> usernameList) {
        this.usernameList = usernameList;
    }
    
    
    
}
