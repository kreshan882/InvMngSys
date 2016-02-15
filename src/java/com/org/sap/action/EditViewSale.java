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
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.login.bean.SessionUserBean;
import com.org.sap.bean.EditViewSaleInputBean;
import com.org.sap.bean.SaleItem;
import com.org.sap.service.EditViewSaleService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author KRESHAN
 */
public class EditViewSale extends ActionSupport implements ModelDriven<EditViewSaleInputBean> , AccessControlService{
    EditViewSaleInputBean inputBean =new EditViewSaleInputBean();
    EditViewSaleService service =new EditViewSaleService();
     HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean)ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    
    public String execute(){
        return SUCCESS;
    }
    
     public String List(){
        List<SaleItem> dataList = null;
        try {
                int rows = inputBean.getRows();
                int page = inputBean.getPage();
                int to = (rows * page);
                int from = to - rows;
                long records = 0;
                String orderBy = "";    

                if (!inputBean.getSidx().isEmpty()) {
                    orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
                }
                dataList=service.loadUsers(inputBean, orderBy, rows, from);
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
    public String PrintInvoice(){
        try {
             service.setPdfParameters(inputBean);
             service.setPdfDataList(inputBean);
             inputBean.setFilename("INVOICE-"+inputBean.getPdfinvoiceId()+".pdf");
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }
        
        return "jasperreport";
    }
    public String Cancle(){
        try {
             System.out.println("cancleeeing"+inputBean.getInvNo()+inputBean.getStorNo());
             if(service.rollbackStock(inputBean)){
                DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.SALE_PURCH_MANAGEMENT, Operation.DELETE, SystemMessage.SALE_CANCLE+" nivoice num:"+inputBean.getInvNo(),request.getRemoteAddr());        
                LogFileCreator.writeInfoToLog(SystemMessage.SALE_CANCLE+" Inv NO:"+inputBean.getInvNo());
                inputBean.setSuccess(true);
                inputBean.setMessage(SystemMessage.SALE_CANCLE);
            }else{
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.SALE_CANCLE_FAIL);
            }
        } catch (Exception ex) {
            inputBean.setSuccess(false);
            inputBean.setMessage(SystemMessage.SALE_CANCLE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        
        return "cancle";
    }
    @Override
    public EditViewSaleInputBean getModel() {
        return inputBean;
    }

     @Override
    public boolean checkAccess(int userRole) {
            boolean status = false;
            String page = PageVarList.SALE_EDIT_VIEW;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
