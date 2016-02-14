/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.item.bean;


import com.inv.util.Util;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title       : InstituteSuperUserBeen
 * Description : User Bean for insert ISA Details
 * Company     : Epic Lanka (pvt) Ltd
 * @author sadun
 *
 */
public class ItemBeen {
    
    //add details
    private String itemNo;
    private String itemType; //from ITEM_TYPE table default 1
    private Map<String,String>  itemTypeList=Util.getItemType();
    private String name;
    private String colour;
    private String unitType;  //from UNIT_TYPE table default 1
    private Map<String,String>  unitTypeList=Util.getUnitType();
    private String unitPrize;  //double
    
   // private String imagePath;
    private File   image;  
    private String imageContentType;
    private String imageFileName;
    private String dbfilename;
    
    private String statusCode;
    private String regDate;

   private long fullCount;

    public String getDbfilename() {
        return dbfilename;
    }

    public void setDbfilename(String dbfilename) {
        this.dbfilename = dbfilename;
    }

   
   
    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
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

    public Map<String, String> getItemTypeList() {
        return itemTypeList;
    }

    public void setItemTypeList(Map<String, String> itemTypeList) {
        this.itemTypeList = itemTypeList;
    }

    public Map<String, String> getUnitTypeList() {
        return unitTypeList;
    }

    public String getUnitPrize() {
        return unitPrize;
    }

    public void setUnitPrize(String unitPrize) {
        this.unitPrize = unitPrize;
    }

    public void setUnitTypeList(Map<String, String> unitTypeList) {
        this.unitTypeList = unitTypeList;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }



//    public String getImagePath() {
//        return imagePath;
//    }
//
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }



    
}
