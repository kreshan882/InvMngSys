/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.login.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kreshan
 */
public class UserLoginBean {

    private String userName;
    private String password;
    private String institute;
    private Map<String,String> instituteList = new HashMap<String,String>();

    private String  DBpassword;
    private int     DBuserId;
    private int     DBuserCategory;
    private int     DBuserProfile;
    private int     DBuserInstituteId;
    private String  DBuserStatus;
    private String  DBuserAppType;
    private int     DBuserAppId;
    private String  DBuserEmail;
    private String  DBuserTpNum;
    private String  DBuserAddress;
    private String  DBuserlogPath;
    
    private String  DBrootCertExpireStatus;
    private String  DBrootCertExpiredate;
    
//    //Home Page
//    private String appTypeName ;
//    private String appIdName;        
//    private String  versionNo;
//    private String  efiModuleNo;
//    private String  description;

    public String getDBuserlogPath() {
        return DBuserlogPath;
    }

    public void setDBuserlogPath(String DBuserlogPath) {
        this.DBuserlogPath = DBuserlogPath;
    }

    public String getDBrootCertExpiredate() {
        return DBrootCertExpiredate;
    }

    public void setDBrootCertExpiredate(String DBrootCertExpiredate) {
        this.DBrootCertExpiredate = DBrootCertExpiredate;
    }

    public String getDBrootCertExpireStatus() {
        return DBrootCertExpireStatus;
    }

    public void setDBrootCertExpireStatus(String DBrootCertExpireStatus) {
        this.DBrootCertExpireStatus = DBrootCertExpireStatus;
    }

    public Map<String, String> getInstituteList() {
        return instituteList;
    }

    public void setInstituteList(Map<String, String> instituteList) {
        this.instituteList = instituteList;
    }


    
      
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getEfiModuleNo() {
//        return efiModuleNo;
//    }
//
//    public void setEfiModuleNo(String efiModuleNo) {
//        this.efiModuleNo = efiModuleNo;
//    }
//
//    public String getVersionNo() {
//        return versionNo;
//    }
//
//    public void setVersionNo(String versionNo) {
//        this.versionNo = versionNo;
//    }
    public int getDBuserId() {
        return DBuserId;
    }

    public void setDBuserId(int DBuserId) {
        this.DBuserId = DBuserId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDBpassword() {
        return DBpassword;
    }

    public void setDBpassword(String DBpassword) {
        this.DBpassword = DBpassword;
    }
    
    
    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

 

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
    
    public String getDBuserAddress() {
        return DBuserAddress;
    }

    public void setDBuserAddress(String DBuserAddress) {
        this.DBuserAddress = DBuserAddress;
    }

    public int getDBuserAppId() {
        return DBuserAppId;
    }

    public void setDBuserAppId(int DBuserAppId) {
        this.DBuserAppId = DBuserAppId;
    }

    public String getDBuserAppType() {
        return DBuserAppType;
    }

    public void setDBuserAppType(String DBuserAppType) {
        this.DBuserAppType = DBuserAppType;
    }

    public int getDBuserCategory() {
        return DBuserCategory;
    }

    public void setDBuserCategory(int DBuserCategory) {
        this.DBuserCategory = DBuserCategory;
    }

    public String getDBuserEmail() {
        return DBuserEmail;
    }

    public void setDBuserEmail(String DBuserEmail) {
        this.DBuserEmail = DBuserEmail;
    }

    public int getDBuserInstituteId() {
        return DBuserInstituteId;
    }

    public void setDBuserInstituteId(int DBuserInstituteId) {
        this.DBuserInstituteId = DBuserInstituteId;
    }

    public int getDBuserProfile() {
        return DBuserProfile;
    }

    public void setDBuserProfile(int DBuserProfile) {
        this.DBuserProfile = DBuserProfile;
    }

    public String getDBuserStatus() {
        return DBuserStatus;
    }

    public void setDBuserStatus(String DBuserStatus) {
        this.DBuserStatus = DBuserStatus;
    }

    public String getDBuserTpNum() {
        return DBuserTpNum;
    }

    public void setDBuserTpNum(String DBuserTpNum) {
        this.DBuserTpNum = DBuserTpNum;
    }

//    public String getAppTypeName() {
//        return appTypeName;
//    }
//
//    public void setAppTypeName(String appTypeName) {
//        this.appTypeName = appTypeName;
//    }
//
//    public String getAppIdName() {
//        return appIdName;
//    }
//
//    public void setAppIdName(String appIdName) {
//        this.appIdName = appIdName;
//    }
    

}
