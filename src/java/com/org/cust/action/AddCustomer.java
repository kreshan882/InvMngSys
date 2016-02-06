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
import com.org.cust.bean.CustomerBeen;
import com.org.cust.service.AddCustomerService;
import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.DBProcesses;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.inv.util.Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 * Title : InstituteSuperUserManagement Description : Institute super user
 * management action class Company : Epic Lanka (pvt) Ltd
 *
 * @author sadun
 *
 */
public class AddCustomer extends ActionSupport implements ModelDriven<CustomerBeen> , AccessControlService{
    
    CustomerBeen customerBean = new CustomerBeen();
    AddCustomerService service = new AddCustomerService(); 
    HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    @Override
    public CustomerBeen getModel() {
        return customerBean;
    }
    
    
    public String CustomerAdd() {
        
        try {
  
            if (doValidation(customerBean)) {
                
                
                if (service.addData(customerBean, sub)) {
                    DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.CUST_MANAGEMENT, Operation.ADD, SystemMessage.CUS_ADD+ " for " + customerBean.getCustName(),request.getRemoteAddr());
                    
                    addActionMessage(SystemMessage.CUS_ADD);
                    LogFileCreator.writeInfoToLog(SystemMessage.CUS_ADD+customerBean.getCustName());
                    
                } else {
                   addActionError(SystemMessage.USR_ERROR_UNHANDLE); 
                    
                }
            }
            
        } catch (Exception ex) {
            try {
                addActionError(SystemMessage.CUS_ADD_FAIL);
                 ex.printStackTrace();
                LogFileCreator.writeErrorToLog(ex);
            } catch (Exception ex1) {
                
            }
        }
        
        return "message";
    }
    
    private boolean doValidation(CustomerBeen cusBean) throws Exception {
        boolean ok = false;
        try {
            if (cusBean.getCustName() == null || cusBean.getCustName().isEmpty()) {
                addActionError(SystemMessage.CUS_NAME_EMPTY);
                return ok;
            } else if (!Util.validateDESCRIPTION(cusBean.getCustName())) {
                addActionError(SystemMessage.CUS_NAME_INVALID);
                return ok;
            }else if (service.checkCusName(cusBean.getCustName())) {
                addActionError(SystemMessage.CUS_NAME_ALREADY);
                return ok;
            }else if (cusBean.getAddress() == null || cusBean.getAddress().isEmpty()) {
                addActionError(SystemMessage.CUS_ADDR_EMPTY);
                return ok;
            } else if (!Util.validateDESCRIPTION(cusBean.getAddress())) {
                addActionError(SystemMessage.CUS_ADDR_INVALID);
                return ok;
            }else if (cusBean.getEmail() == null || cusBean.getEmail().isEmpty()) {
                addActionError(SystemMessage.CUS_EMAIL_EMPTY);
                return ok;
            } else if (!Util.validateEMAIL(cusBean.getEmail())) {
                addActionError(SystemMessage.CUS_EAMIL_INVALID);
                return ok;
            }else if (cusBean.getTpOffice() == null || cusBean.getTpOffice().isEmpty()) {
                addActionError(SystemMessage.CUS_TP_OFFI_EMPTY);
                return ok;
            } else if (!Util.validatePHONENO(cusBean.getTpOffice())) {
                addActionError(SystemMessage.CUS_TP_OFFI_INVALID);
                return ok;
            }else if (cusBean.getTpMobile() == null || cusBean.getTpMobile().isEmpty()) {
                addActionError(SystemMessage.CUS_TP_MOB_EMPTY);
                return ok;
            } else if (!Util.validatePHONENO(cusBean.getTpMobile())) {
                addActionError(SystemMessage.CUS_TP_MOB_INVALID);
                return ok;
            } else {
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
        String page = PageVarList.CUS_ADD;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
