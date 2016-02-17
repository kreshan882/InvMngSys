/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.item.bean;

import com.inv.util.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kreshan88
 */
public class EditViewItemInputBean {
    
     //delete values
    private String ditemNo;
    private boolean dsuccess;
    private String dmessage;
    
    
    //update values
    private String upitemNo; 
    private String upname;
    private String upcolour;
    private String upunitPrize;
    private String upstatus;
    private Map<String,String>  usableStatusList=Util.getBasicStatus();
    // private String imagePath;
    private File   image;  
    private String imageContentType;
    private String imageFileName;
    private String dbfilename;
    
    
    //serch
    private String itemNo="";
    private boolean search;
    
   //**************Export XSL***************
    private ByteArrayInputStream excelStream;
    
      // JQGrid pagination
    private List<ItemBeen> gridModel;
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

    

    public String getDbfilename() {
        return dbfilename;
    }

    public void setDbfilename(String dbfilename) {
        this.dbfilename = dbfilename;
    }
    

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
    
    
    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public boolean isSearch() {
        return search;
    }

    public String getDitemNo() {
        return ditemNo;
    }

    public void setDitemNo(String ditemNo) {
        this.ditemNo = ditemNo;
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

    public String getUpitemNo() {
        return upitemNo;
    }

    public void setUpitemNo(String upitemNo) {
        this.upitemNo = upitemNo;
    }

    public String getUpname() {
        return upname;
    }

    public void setUpname(String upname) {
        this.upname = upname;
    }

    public String getUpcolour() {
        return upcolour;
    }

    public void setUpcolour(String upcolour) {
        this.upcolour = upcolour;
    }

    public String getUpunitPrize() {
        return upunitPrize;
    }

    public void setUpunitPrize(String upunitPrize) {
        this.upunitPrize = upunitPrize;
    }



    public String getUpstatus() {
        return upstatus;
    }

    public void setUpstatus(String upstatus) {
        this.upstatus = upstatus;
    }

    public Map<String, String> getUsableStatusList() {
        return usableStatusList;
    }

    public void setUsableStatusList(Map<String, String> usableStatusList) {
        this.usableStatusList = usableStatusList;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public ByteArrayInputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(ByteArrayInputStream excelStream) {
        this.excelStream = excelStream;
    }

    public List<ItemBeen> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<ItemBeen> gridModel) {
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
    
    
    
}
