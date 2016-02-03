/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epic.user.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tharaka
 */
public class ViewUserAAInputBean {
    private Map<Integer,String> userCategoryList=new HashMap<Integer,String>();
    private Map<Integer,String> userProfileList=new HashMap<Integer,String>();
    
    
    private String username;
    private boolean search;
    
    private int userProfileID;
    private int userCategory;
    
      // JQGrid pagination
    private List<ViewUserAADataBean> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
    private boolean loadonce = false;  

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }    
    
    public Map<Integer, String> getUserCategoryList() {
        return userCategoryList;
    }

    public void setUserCategoryList(Map<Integer, String> userCategoryList) {
        this.userCategoryList = userCategoryList;
    }

    public Map<Integer, String> getUserProfileList() {
        return userProfileList;
    }

    public void setUserProfileList(Map<Integer, String> userProfileList) {
        this.userProfileList = userProfileList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ViewUserAADataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<ViewUserAADataBean> gridModel) {
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

    public boolean isLoadonce() {
        return loadonce;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public int getUserProfileID() {
        return userProfileID;
    }

    public void setUserProfileID(int userProfileID) {
        this.userProfileID = userProfileID;
    }

    public int getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(int userCategory) {
        this.userCategory = userCategory;
    }
    
    
    
    
    
}
