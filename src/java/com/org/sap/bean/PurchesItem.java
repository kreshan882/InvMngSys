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

    public String getPurId() {
        return purId;
    }

    public void setPurId(String purId) {
        this.purId = purId;
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
