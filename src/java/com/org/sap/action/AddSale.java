/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.action;

import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.DBProcesses;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        System.out.println("addItem.............");
        try {
            if(service.checkItemAvaliable(inputBean)){
                if(service.checkInvoiceId(inputBean.getInvoiceId())){   //outher Rows
                service.addInvoiceDetail(inputBean);
                inputBean.setItemadd(true);
                }else{   //add first row
                    service.addInvoice(inputBean);
                    service.addInvoiceDetail(inputBean);
                    inputBean.setItemadd(true);
                }
            }else{
                inputBean.setItemadd(false);
                inputBean.setMessage(SystemMessage.SALE_ITEM_NOTAVAL);
            }
            
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            inputBean.setItemadd(false);
            inputBean.setMessage(SystemMessage.COMMON_ERROR_PROCESS);
            ex.printStackTrace();
        }
        
        return "addItem";
    }
    
    public String List(){
        try {
            System.out.println("inv id:"+inputBean.getInvoiceId());
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
    
    public String firstload(){
        try {
            service.getNextInvoiceNumber(inputBean);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return SUCCESS;
    }
    
    public String Delete(){
        try {
            service.deleteSelectItem(inputBean);
            
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }
        
        return "delete";
    }
    
    public String SubmitInvoice(){ //uudate invoice/stock table
        try {
            System.out.println("SubmitInvoice............"+inputBean.getInvoiceId());
             if(service.submitInvoice(inputBean)){

                 
                 inputBean.setItemadd(true);
                 inputBean.setMessage(SystemMessage.SALE_ADD);
                 DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.SALE_PURCH_MANAGEMENT, Operation.ADD, SystemMessage.SALE_ADD+ " Invoice No " + inputBean.getInvoiceId(),request.getRemoteAddr());
                 LogFileCreator.writeInfoToLog(SystemMessage.SALE_ADD+ " Invoice No " + inputBean.getInvoiceId());
                    
             }else{
                 inputBean.setItemadd(false);
                 inputBean.setMessage(SystemMessage.SALE_ADD_FAIL);
             }
        } catch (Exception ex) {
           LogFileCreator.writeErrorToLog(ex);
            inputBean.setItemadd(false);
            inputBean.setMessage(SystemMessage.SALE_ADD_FAIL);
            ex.printStackTrace();
        }
        
        return "submitInvoice";
    }
    
    public String PrintInvoice(){
        try {
            System.out.println("endddddddddddddddddddd"+inputBean.getInvoiceId());
             Map reportParameters = new HashMap();
                reportParameters.put("from_d", "2012");
                reportParameters.put("to_d", "2015");
                reportParameters.put("total_d", "20");
                inputBean.setParameterMap(reportParameters);

                
                    List datalist = new ArrayList();
                    SaleItem data1 = new SaleItem();
                    data1.setAA("1111");
                    data1.setBB("bbbbb1");
                    data1.setCC("ccccc1");
                    datalist.add(data1);
                    
                    SaleItem data2 = new SaleItem();
                    data2.setAA("2222");
                    data2.setBB("bbbbb2");
                    data2.setCC("ccccc2");
                    datalist.add(data2);
                    
                inputBean.setReportdatalist(datalist);
                inputBean.setFilename("INVOICE-"+inputBean.getInvoiceId()+".pdf");
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }
        
        return "jasperreport";
    }
    
    @Override
    public AddSaleInputBeen getModel() {
        try {
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
