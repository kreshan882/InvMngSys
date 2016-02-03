/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.login.bean;

/**
 *
 * @author kreshan
 */
public class SessionUserBean {

   
    private String username;
    private int userid;
    private int instituteid;
    private int appid;
    private String apptype;
    private int currentUserProfileId;
    private String logFilePath;
    private boolean rootCertExpire;

    
    private String currentSessionId;

    //for view history details
    private String historyId;
    private String historyType;

    //home values;
    public boolean isRootCertExpire() {
        return rootCertExpire;
    }

    public void setRootCertExpire(boolean rootCertExpire) {
        this.rootCertExpire = rootCertExpire;
    }
        
    
    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public int getCurrentUserProfileId() {
        return currentUserProfileId;
    }

    public void setCurrentUserProfileId(int currentUserProfileId) {
        this.currentUserProfileId = currentUserProfileId;
    }

    public String getCurrentSessionId() {
        return currentSessionId;
    }

    public void setCurrentSessionId(String currentSessionId) {
        this.currentSessionId = currentSessionId;
    }
    

    public int getInstituteid() {
        return instituteid;
    }

    public void setInstituteid(int instituteid) {
        this.instituteid = instituteid;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getHistoryType() {
        return historyType;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }
    
    
    
}
