/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.supp.action;

import com.org.cust.action.*;
import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.util.LogFileCreator;
import com.org.login.bean.SessionUserBean;
import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.DBProcesses;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.inv.util.Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.supp.bean.SupplierBeen;
import com.org.supp.service.AddSupplierService;
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
public class AddSupplier extends ActionSupport implements ModelDriven<SupplierBeen> , AccessControlService{
    
    SupplierBeen supplierBean = new SupplierBeen();
    AddSupplierService service = new AddSupplierService(); 
    
    HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    @Override
    public SupplierBeen getModel() {
        return supplierBean;
    }
    
    
    public String Add() {
        
        try {
  
            if (doValidation(supplierBean)) {
                
                
                if (service.addData(supplierBean, sub)) {
                    DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.SUPPLIER_MANAGEMENT, Operation.ADD, SystemMessage.SUPP_ADD+ " for " + supplierBean.getSuppName(),request.getRemoteAddr());
                    
                    addActionMessage(SystemMessage.SUPP_ADD);
                    LogFileCreator.writeInfoToLog(SystemMessage.SUPP_ADD+supplierBean.getSuppName());
                    
                } else {
                   addActionError(SystemMessage.SUPP_ADD_FAIL); 
                    
                }
            }
            
        } catch (Exception ex) {
            try {
                addActionError(SystemMessage.SUPP_ADD_FAIL);
                 ex.printStackTrace();
                LogFileCreator.writeErrorToLog(ex);
            } catch (Exception ex1) {
                
            }
        }
        
        return "message";
    }
    
    private boolean doValidation(SupplierBeen cusBean) throws Exception {
        boolean ok = false;
        try {
            if (cusBean.getSuppName() == null || cusBean.getSuppName().isEmpty()) {
                addActionError(SystemMessage.SUPP_NAME_EMPTY);
                return ok;
            } else if (!Util.validateDESCRIPTION(cusBean.getSuppName())) {
                addActionError(SystemMessage.SUPP_NAME_INVALID);
                return ok;
            }else if (service.checkSuppName(cusBean.getSuppName())) {
                addActionError(SystemMessage.SUPP_NAME_ALREADY);
                return ok;
            }else if (cusBean.getAddress() == null || cusBean.getAddress().isEmpty()) {
                addActionError(SystemMessage.SUPP_ADDR_EMPTY);
                return ok;
            } else if (!Util.validateDESCRIPTION(cusBean.getAddress())) {
                addActionError(SystemMessage.SUPP_ADDR_INVALID);
                return ok;
            }else if (cusBean.getEmail() == null || cusBean.getEmail().isEmpty()) {
                addActionError(SystemMessage.SUPP_EMAIL_EMPTY);
                return ok;
            } else if (!Util.validateEMAIL(cusBean.getEmail())) {
                addActionError(SystemMessage.SUPP_EAMIL_INVALID);
                return ok;
            }else if (cusBean.getTpOffice() == null || cusBean.getTpOffice().isEmpty()) {
                addActionError(SystemMessage.SUPP_TP_OFFI_EMPTY);
                return ok;
            } else if (!Util.validatePHONENO(cusBean.getTpOffice())) {
                addActionError(SystemMessage.SUPP_TP_OFFI_INVALID);
                return ok;
            }else if (cusBean.getTpMobile() == null || cusBean.getTpMobile().isEmpty()) {
                addActionError(SystemMessage.SUPP_TP_MOB_EMPTY);
                return ok;
            } else if (!Util.validatePHONENO(cusBean.getTpMobile())) {
                addActionError(SystemMessage.SUPP_TP_MOB_INVALID);
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
        String page = PageVarList.SUPP_ADD;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
