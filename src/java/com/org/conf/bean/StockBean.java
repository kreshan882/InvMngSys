/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.conf.bean;

/**
 *
 * @author kreshan88
 */
public class StockBean {
    private String itemCode;
    private String itemName;
    private String qty;
    
    private long fullCount;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }
    
    
}
