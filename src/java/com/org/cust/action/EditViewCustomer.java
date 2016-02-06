/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.cust.action;

import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.util.LogFileCreator;
import com.org.login.bean.SessionUserBean;
import com.org.cust.bean.EditViewCustomerInputBean;
import com.org.cust.service.EditViewCustomerService;
import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.DBProcesses;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.inv.util.Util;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.cust.bean.CustomerBeen;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author tharaka
 */
public class EditViewCustomer extends ActionSupport implements ModelDriven<EditViewCustomerInputBean> , AccessControlService{

    private EditViewCustomerService  service = new EditViewCustomerService();
    private EditViewCustomerInputBean inputBean = new EditViewCustomerInputBean();
    HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean)ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    
    public String execute(){
        return SUCCESS;
    }
    

    @Override
    public EditViewCustomerInputBean getModel() {
        return inputBean;
    }

     public String List(){
        try {
                List<CustomerBeen> dataList = null;
                int rows = inputBean.getRows();
                int page = inputBean.getPage();
                int to = (rows * page);
                int from = to - rows;
                long records = 0;
                String orderBy = "";    

                if (!inputBean.getSidx().isEmpty()) {
                    orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
                }
                dataList=service.loadTableData(inputBean, orderBy, rows, from);
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
            addActionError(SystemMessage.USR_ERROR_UNHANDLE);
        }
        return "list";
     }
    
    public String Delete() {
        try {
            if(service.deleteData( inputBean)){
                DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.CUST_MANAGEMENT, Operation.DELETE, SystemMessage.CUS_DELETE+" Cus ID:"+inputBean.getDcustId(),request.getRemoteAddr());        
                LogFileCreator.writeInfoToLog(SystemMessage.CUS_DELETE+" Cus ID:"+inputBean.getDcustId());
                inputBean.setDsuccess(true);
                inputBean.setDmessage(SystemMessage.CUS_DELETE);
            }else{
                inputBean.setDsuccess(false);
                inputBean.setDmessage(SystemMessage.CUS_DELETE_FAIL);
            }
        } catch (Exception ex) {
            inputBean.setDsuccess(false);
            inputBean.setDmessage(SystemMessage.CUS_DELETE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "delete";
    }

   
    public String Find() {
        try {
            service.findForUpdate(inputBean);
        } catch (Exception e) {      
            addActionError(SystemMessage.USR_ERROR_UNHANDLE);
        }
        return "find";
    }

  

     
    public String Update() {
         
        try {
            if (doValidation(inputBean)) {
               if(service.updateData(inputBean)){
                addActionMessage(SystemMessage.CUS_UPDATE);
                DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.CUST_MANAGEMENT, Operation.UPDATE, SystemMessage.CUS_UPDATE+":"+inputBean.getUpcustName(),request.getRemoteAddr());     
                LogFileCreator.writeInfoToLog(SystemMessage.CUS_UPDATE+":"+inputBean.getUpcustName()); 
              
               } else {    
                  addActionError(SystemMessage.CUS_UPDATE_FAIL);
               }
              
            }
        } catch (Exception ex) {

            addActionError(SystemMessage.CUS_UPDATE_FAIL);
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);

        }
        return "update";
    }

    
    private boolean  doValidation (EditViewCustomerInputBean cusBean) throws Exception{
        boolean ok = false;
        
        try {
            
            if (cusBean.getUpcompanyName() == null || cusBean.getUpcompanyName().isEmpty()) {
                addActionError(SystemMessage.CUS_COMPANY_EMPTY);
                return ok;
            } else if (!Util.validateDESCRIPTION(cusBean.getUpcompanyName())) {
                addActionError(SystemMessage.CUS_COMPANY_INVALID);
                return ok;
            }else if (cusBean.getUpemail() == null || cusBean.getUpemail().isEmpty()) {
                addActionError(SystemMessage.CUS_EMAIL_EMPTY);
                return ok;
            } else if (!Util.validateEMAIL(cusBean.getUpemail())) {
                addActionError(SystemMessage.CUS_EAMIL_INVALID);
                return ok;
            }else if (cusBean.getUpaddress() == null || cusBean.getUpaddress().isEmpty()) {
                addActionError(SystemMessage.CUS_ADDR_EMPTY);
                return ok;
            } else if (!Util.validateDESCRIPTION(cusBean.getUpaddress())) {
                addActionError(SystemMessage.CUS_ADDR_INVALID);
                return ok;
            }else if (cusBean.getUptpOffice() == null || cusBean.getUptpOffice().isEmpty()) {
                addActionError(SystemMessage.CUS_TP_OFFI_EMPTY);
                return ok;
            } else if (!Util.validatePHONENO(cusBean.getUptpOffice())) {
                addActionError(SystemMessage.CUS_TP_OFFI_INVALID);
                return ok;
            }else if (cusBean.getUptpMobile() == null || cusBean.getUptpMobile().isEmpty()) {
                addActionError(SystemMessage.CUS_TP_MOB_EMPTY);
                return ok;
            } else if (!Util.validatePHONENO(cusBean.getUptpMobile())) {
                addActionError(SystemMessage.CUS_TP_MOB_INVALID);
                return ok;
            }else if ("-1".equals(cusBean.getUpstatus())) {
                addActionError(SystemMessage.CUS_STATUS_EMPTY);
                return ok;
            }else {
                ok = true;
            }
            
        } catch (Exception e) {
           throw e; 
        }
        return ok;
    
    }
    
    
        
    @Override
    public boolean checkAccess(int userRole) {
        boolean status = false;
        String page = PageVarList.CUS_EDIT_VIEW;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
}
