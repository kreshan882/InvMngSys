/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.login.bean;

/**
 *
 * @author kreshan
 */
public class SessionUserBean {

   
    private String username;
    private int userid;
    private int currentUserProfileId;
    private String logFilePath;


    
    private String currentSessionId;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCurrentUserProfileId() {
        return currentUserProfileId;
    }

    public void setCurrentUserProfileId(int currentUserProfileId) {
        this.currentUserProfileId = currentUserProfileId;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public String getCurrentSessionId() {
        return currentSessionId;
    }

    public void setCurrentSessionId(String currentSessionId) {
        this.currentSessionId = currentSessionId;
    }


    
}
