/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.user.action;

import com.inv.init.AppType;
import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.log.LogFileCreator;
import com.epic.login.bean.SessionUserBean;
import com.epic.user.bean.ChangePasswordBean;
import com.epic.user.service.ChangePasswordService;
import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.DBProcesses;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan
 */
public class ChangePassword extends ActionSupport implements ModelDriven<ChangePasswordBean>, AccessControlService{
    ChangePasswordBean changePasswordBean= new ChangePasswordBean();
    ChangePasswordService cps= new ChangePasswordService();
    HttpServletRequest request = ServletActionContext.getRequest();

     
    
    public String execute() {
        return SUCCESS;
    }
       
     public String changePwFunction(){ 
         String msg=null;
             
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            SessionUserBean sub = (SessionUserBean)session.getAttribute("SessionObject");
            changePasswordBean.setUsername(sub.getUsername());
            
            try{
            
             cps.getOldPasswordFromDb(changePasswordBean);
             
           if(!(changePasswordBean.getPasswordOld() == null || changePasswordBean.getPasswordOld().isEmpty())){
            
               if(cps.checkPasswordMatchWithUserPass(changePasswordBean)){
                if(!changePasswordBean.getPasswordOld().equals(changePasswordBean.getPasswordNew1())){
                if(cps.checkNewPasswordsMatch(changePasswordBean)){
                   
                 
                        msg = "Successful";
                    
                 
                    if(msg.equals("Successful")){

                            cps.updatePassword(changePasswordBean);
//                            DBProcesses.insertHistoryRecord(sub.getInstituteid(),
//                            sub.getUserid(),sub.getApptype(),sub.getAppid(),
//                            Module.USER_MANAGEMENT,Operation.UPDATE,SystemMessage.USR_PW_CHG,request.getRemoteAddr());
                            addActionMessage(SystemMessage.USR_PW_UPDATE);

                    }else{
                        addActionError(msg);
                    }
                    
                 }else{
                     addActionError(SystemMessage.USR_PW_NOT_MAT);
                 }
                }else{
                    addActionError(SystemMessage.USR_PW_CNT_EQUAL_NWEPW);
                }
             }else{
                 addActionError(SystemMessage.USR_PW_WORNG);
             }
            }else{
                 addActionError(SystemMessage.USR_PW_WORNG_OLD);
             }
            }catch(Exception ex){
                ex.printStackTrace();
                LogFileCreator.writeErrorToLog(ex);
            }
            
        return "message";
    }

    @Override
    public ChangePasswordBean getModel() {
        return changePasswordBean;
    }
    
    
    @Override
    public boolean checkAccess(int userRole) {
        boolean status = false;
        String page = PageVarList.USER_PASSWDCHANGE;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
}
