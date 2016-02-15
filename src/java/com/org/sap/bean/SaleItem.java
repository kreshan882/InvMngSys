/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.bean;

/**
 *
 * @author kreshan
 */
public class SaleItem {
    private String invId;
    private String itemNo;
    private String count;
    private String unitPrize;
    private String totalPrize;
    
    private long fullCount;

    //temo jasper
    private String pdfItmNo;
    private String pdfItmName;
    private String pdfItmUprize;
    private String pdfItmQty;
    private String pdfItmTprize;

    //edit And View
    private String evinvId;
    private String evstorId;
    private String evcusName;
    private String evstorName;
    private String evtotal;
    private String evsaleDate;
    private String evstatus;

    public String getEvinvId() {
        return evinvId;
    }

    public void setEvinvId(String evinvId) {
        this.evinvId = evinvId;
    }

    public String getEvcusName() {
        return evcusName;
    }

    public String getEvstorId() {
        return evstorId;
    }

    public void setEvstorId(String evstorId) {
        this.evstorId = evstorId;
    }

    public void setEvcusName(String evcusName) {
        this.evcusName = evcusName;
    }

    public String getEvstorName() {
        return evstorName;
    }

    public void setEvstorName(String evstorName) {
        this.evstorName = evstorName;
    }

    public String getEvtotal() {
        return evtotal;
    }

    public void setEvtotal(String evtotal) {
        this.evtotal = evtotal;
    }

    public String getEvsaleDate() {
        return evsaleDate;
    }

    public void setEvsaleDate(String evsaleDate) {
        this.evsaleDate = evsaleDate;
    }

    public String getEvstatus() {
        return evstatus;
    }

    public void setEvstatus(String evstatus) {
        this.evstatus = evstatus;
    }
    
    
    
    public String getPdfItmNo() {
        return pdfItmNo;
    }

    public void setPdfItmNo(String pdfItmNo) {
        this.pdfItmNo = pdfItmNo;
    }

    public String getPdfItmName() {
        return pdfItmName;
    }

    public void setPdfItmName(String pdfItmName) {
        this.pdfItmName = pdfItmName;
    }

    public String getPdfItmUprize() {
        return pdfItmUprize;
    }

    public void setPdfItmUprize(String pdfItmUprize) {
        this.pdfItmUprize = pdfItmUprize;
    }

    public String getPdfItmQty() {
        return pdfItmQty;
    }

    public void setPdfItmQty(String pdfItmQty) {
        this.pdfItmQty = pdfItmQty;
    }

    public String getPdfItmTprize() {
        return pdfItmTprize;
    }

    public void setPdfItmTprize(String pdfItmTprize) {
        this.pdfItmTprize = pdfItmTprize;
    }


    
    
    
    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUnitPrize() {
        return unitPrize;
    }

    public void setUnitPrize(String unitPrize) {
        this.unitPrize = unitPrize;
    }

    public String getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(String totalPrize) {
        this.totalPrize = totalPrize;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }
    
    
    
}
