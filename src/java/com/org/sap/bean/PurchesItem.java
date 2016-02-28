/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.bean;

/**
 *
 * @author KRESHAN
 */
public class PurchesItem {
    private String purId;
    private String itemNo;
    private String count;
    private String unitCost;
    private String unitPrize;
    private String totalPrize;
    
    private long fullCount;

    //temo jasper
    private String pdfItmNo;
    private String pdfItmName;
    private String pdfItmQty;
    private String pdfItmUcost;
    private String pdfItmUprize;
    private String pdfItmTprize;

    
        //edit And View
    private String evpurId;
    private String evstorId;
    private String evsupName;
    private String evstorName;
    private String evtotal;
    private String evpurDate;
    private String evstatus;
    
    public String getPurId() {
        return purId;
    }

    public void setPurId(String purId) {
        this.purId = purId;
    }

    public String getEvpurId() {
        return evpurId;
    }

    public void setEvpurId(String evpurId) {
        this.evpurId = evpurId;
    }

    public String getEvstorId() {
        return evstorId;
    }

    public void setEvstorId(String evstorId) {
        this.evstorId = evstorId;
    }

    public String getEvsupName() {
        return evsupName;
    }

    public void setEvsupName(String evsupName) {
        this.evsupName = evsupName;
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

    public String getEvpurDate() {
        return evpurDate;
    }

    public void setEvpurDate(String evpurDate) {
        this.evpurDate = evpurDate;
    }

    public String getEvstatus() {
        return evstatus;
    }

    public void setEvstatus(String evstatus) {
        this.evstatus = evstatus;
    }

    public String getPdfItmUcost() {
        return pdfItmUcost;
    }

    public void setPdfItmUcost(String pdfItmUcost) {
        this.pdfItmUcost = pdfItmUcost;
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

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
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
    
    
}
