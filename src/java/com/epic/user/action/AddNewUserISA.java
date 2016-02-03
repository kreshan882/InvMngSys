/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epic.user.action;

import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.log.LogFileCreator;
import com.epic.login.bean.SessionUserBean;
import com.epic.user.bean.AddISAUserBeen;
import com.epic.user.service.AddISAUserService;
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
public class AddNewUserISA extends ActionSupport implements ModelDriven<AddISAUserBeen> , AccessControlService{
    
    AddISAUserBeen userBean = new AddISAUserBeen();
    AddISAUserService userService = new AddISAUserService(); 
    HttpServletRequest request = ServletActionContext.getRequest();
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    @Override
    public AddISAUserBeen getModel() {
        return userBean;
    }
    
    public String userManagement() {
        try {
            userService.getInstituteList(userBean);
        } catch (Exception ex) {
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        
        return "success";
        
    }
    
    public String UserAdd() {
        boolean ok = false;
        
        try {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            SessionUserBean sessionUserBean = (SessionUserBean) session.getAttribute("SessionObject");
            userService.getInstituteList(userBean);
            
            if (doValidation(userBean)) {
                ok = userService.insertISADetails(userBean, sessionUserBean);
                
                if (ok == false) {
                    addActionError(SystemMessage.USR_ERROR_UNHANDLE);
                } else {
                    DBProcesses.insertHistoryRecord(sessionUserBean.getInstituteid(),
                            sessionUserBean.getUserid(), sessionUserBean.getApptype(), sessionUserBean.getAppid(),
                            Module.USER_MANAGEMENT, Operation.ADD, SystemMessage.USR_ADD + " for " + userBean.getUserId(),request.getRemoteAddr());
                    
                    addActionMessage(SystemMessage.USR_ADD);
                    
                }
            }
            
        } catch (Exception ex) {
            try {
                addActionError(SystemMessage.COMMON_ERROR_PROCESS + " User Add");
                 ex.printStackTrace();
                LogFileCreator.writeErrorToLog(ex);
            } catch (Exception ex1) {
                
            }
        }
        
        return "message";
    }
    
    private boolean doValidation(AddISAUserBeen userBean) throws Exception {
        boolean ok = false;
        try {
            if (userBean.getInstitute().equals("-1")) {
                addActionError(SystemMessage.USR_INVALID_INSTITIUTE);
                return ok;
            } else if (userBean.getUserId() == null || userBean.getUserId().isEmpty()) {
                addActionError(SystemMessage.USR_EMPTY_USERNAME);
                return ok;
            } else if (!Util.validateNAME(userBean.getUserId())) {
                addActionError(SystemMessage.USR_INVALID_USERNAME);
                return ok;
            } else if (userService.checkUserName(userBean)) {
                addActionError(SystemMessage.USR_ERROR_USERNAME);
                return ok;
            } else if (userBean.getPassword().isEmpty() || userBean.getPassword() == null) {
                addActionError(SystemMessage.USR_EMPTY_USERPASSWORD);
                return ok;
            } else if (userBean.getConfirmPassword().isEmpty() || userBean.getConfirmPassword() == null) {
                addActionError(SystemMessage.USR_EMPTY_CONPASSWORD);
                return ok; 
            } else if(!(userBean.getPassword().equals(userBean.getConfirmPassword()))) {   
                addActionError(SystemMessage.USR_PASSWORD_NOT_MATCH);
                return ok;     
            } else if (!userBean.getEmail().isEmpty() && !Util.validateEMAIL(userBean.getEmail())) {
                addActionError(SystemMessage.USR_INVALID_EMAIL);
                return ok;
            } else if ((!userBean.getTelephone().isEmpty()) && !Util.validateNUMBER(userBean.getTelephone())) {
                addActionError(SystemMessage.USR_INVALID_PHONE);
                return ok;
            } else if (( !userBean.getNic().isEmpty()) && !Util.validateNIC(userBean.getNic())) {
                addActionError(SystemMessage.USR_INVALID_NIC);
                return ok;
            } else if ((!userBean.getLocation().isEmpty()) && !Util.validateNAME(userBean.getLocation())) {
                addActionError(SystemMessage.USR_INVALID_LOCATION);
                return ok;
            } else if (userBean.getCompany() == null || userBean.getCompany().isEmpty()) {
                addActionError(SystemMessage.USR_EMPTY_COMPANY);
                return ok;
            } else if (!Util.validateDESCRIPTION(userBean.getCompany())) {
                addActionError(SystemMessage.USR_INVALID_COMPANY);
                return ok;
            } else if (userBean.getGender() == null || userBean.getGender().isEmpty()) {
                addActionError(SystemMessage.USR_EMPTY_GENDER);
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
        String page = PageVarList.USER_ADD_ISA;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
