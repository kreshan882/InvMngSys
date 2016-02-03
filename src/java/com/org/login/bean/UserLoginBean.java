/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.login.bean;

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


    private String  DBpassword;
    private int     DBuserId;
    private int     DBuserProfile;
    private String  DBuserStatus;
    private String  DBuserEmail;
    private String  DBuserTpNum;
    private String  DBuserAddress;
    private String  DBuserlogPath;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDBpassword() {
        return DBpassword;
    }

    public void setDBpassword(String DBpassword) {
        this.DBpassword = DBpassword;
    }

    public int getDBuserId() {
        return DBuserId;
    }

    public void setDBuserId(int DBuserId) {
        this.DBuserId = DBuserId;
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

    public String getDBuserEmail() {
        return DBuserEmail;
    }

    public void setDBuserEmail(String DBuserEmail) {
        this.DBuserEmail = DBuserEmail;
    }

    public String getDBuserTpNum() {
        return DBuserTpNum;
    }

    public void setDBuserTpNum(String DBuserTpNum) {
        this.DBuserTpNum = DBuserTpNum;
    }

    public String getDBuserAddress() {
        return DBuserAddress;
    }

    public void setDBuserAddress(String DBuserAddress) {
        this.DBuserAddress = DBuserAddress;
    }

    public String getDBuserlogPath() {
        return DBuserlogPath;
    }

    public void setDBuserlogPath(String DBuserlogPath) {
        this.DBuserlogPath = DBuserlogPath;
    }



}
