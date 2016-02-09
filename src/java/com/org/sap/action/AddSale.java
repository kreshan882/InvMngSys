/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.action;

import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.LogFileCreator;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.inv.util.Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.org.login.bean.SessionUserBean;
import com.org.sap.bean.AddSaleInputBeen;
import com.org.sap.bean.SaleItem;
import com.org.sap.service.AddSaleService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;


/**
 *
 * @author kreshan
 */
public class AddSale extends ActionSupport implements ModelDriven<AddSaleInputBeen> , AccessControlService{
    AddSaleInputBeen inputBean = new AddSaleInputBeen();
    AddSaleService service = new AddSaleService(); 
    HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");

    
    public String execute(){
        return SUCCESS;
    }
    public String loadItemDetail(){
        System.out.println("idddddddddddddddddddddddddd"+inputBean.getItemCode());
        try {
            if(service.getItemDetail(inputBean)){
               inputBean.setItemQut("1"); 
               inputBean.setItemfind(true);
            }else{
                inputBean.setItemfind(false);
            }
            
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            inputBean.setItemfind(false);
            ex.printStackTrace();
        }
        
        return "loadItemDetail";
    }
    
    public String addItem(){
        System.out.println("addItemDetail.............");
        try {
//            if(service.getItemDetail(inputBean)){
//               inputBean.setItemQut("1"); 
//               inputBean.setItemfind(true);
//            }else{
//                inputBean.setItemfind(false);
//            }
            
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            inputBean.setItemfind(false);
            ex.printStackTrace();
        }
        
        return "addItem";
    }
    
    public String List(){
        try {
                List<SaleItem> dataList = null;
                int rows = inputBean.getRows();
                int page = inputBean.getPage();
                int to = (rows * page);
                int from = to - rows;
                long records = 0;
                String orderBy = "";    

                if (!inputBean.getSidx().isEmpty()) {
                    orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
                }
                dataList=service.loadTableData(inputBean, orderBy, rows, from);//invoice details
                System.out.println("jjjj"+dataList.size());
                if (!dataList.isEmpty()) {
                    records = dataList.get(0).getFullCount();
                    inputBean.setRecords(records);
                    inputBean.setGridModel(dataList);
                    int total = (int) Math.ceil((double) records / (double) rows);
                    inputBean.setTotal(total);
                } else {
                    inputBean.setRecords(0L);
                    inputBean.setTotal(0);
                }
//            }
        } catch (Exception ex) {
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
        }
        return "list";
     }
    @Override
    public AddSaleInputBeen getModel() {
        try {
            System.out.println("sssssss");
            service.getInvoiceNumber(inputBean);
            inputBean.getStorIdList().putAll(Util.getStorList());
            inputBean.getCustIdList().putAll(Util.getCustomerList());
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

     @Override
    public boolean checkAccess(int userRole) {
        boolean status = false;
        String page = PageVarList.SALE_ADD;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
