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
    
    //add details
    private String custId;
    private String custName;
    private String email;
    private String address;
    private String tpOffice;
    private String tpMobile;
    
    

    private String statusCode;
    private String regDate;

   private long fullCount;



   
   
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTpMobile() {
        return tpMobile;
    }

    public void setTpMobile(String tpMobile) {
        this.tpMobile = tpMobile;
    }

    public String getTpOffice() {
        return tpOffice;
    }

    public void setTpOffice(String tpOffice) {
        this.tpOffice = tpOffice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
