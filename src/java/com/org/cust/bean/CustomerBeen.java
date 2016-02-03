/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.cust.bean;

import java.util.Date;
import java.util.HashMap;

/**
 * Title       : InstituteSuperUserBeen
 * Description : User Bean for insert ISA Details
 * Company     : Epic Lanka (pvt) Ltd
 * @author sadun
 *
 */
public class CustomerBeen {
    
    private String institute;
    private HashMap<Integer,String> InstituteList = new HashMap<Integer,String>();
    private String userId;
    private String password;
    private String confirmPassword;
    private String email;
    private String telephone;
    private String nic;
    private String location;
    private String company;
    private String gender;
    private Date regDate;

   
    
    public HashMap<Integer, String> getInstituteList() {
        return InstituteList;
    }

    public void setInstituteList(HashMap<Integer, String> InstituteList) {
        this.InstituteList = InstituteList;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
    
    
    
}
