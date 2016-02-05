/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.conf.action;

//import com.inv.init.AppType;
import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.log.LogFileCreator;
import com.org.login.bean.SessionUserBean;
import com.org.conf.bean.ChangePasswordBean;
import com.org.conf.service.ChangePasswordService;
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
    ChangePasswordBean inputBean= new ChangePasswordBean();
    ChangePasswordService service= new ChangePasswordService();
    HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean)ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
     
    
    public String execute() {
        return SUCCESS;
    }
       
     public String changePwFunction(){ 
         String msg=null;
             
            
            
            inputBean.setUsername(sub.getUsername());
            
            try{
            
             service.getOldPasswordFromDb(inputBean);
             
           if(!(inputBean.getPasswordOld() == null || inputBean.getPasswordOld().isEmpty())){
            
               if(service.checkPasswordMatchWithUserPass(inputBean)){
                if(!inputBean.getPasswordOld().equals(inputBean.getPasswordNew1())){
                if(service.checkNewPasswordsMatch(inputBean)){
                   

                            service.updatePassword(inputBean);
                            DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.CONF_MANAGEMENT, Operation.UPDATE, SystemMessage.USR_PW_UPDATE+ " for " + sub.getUsername(),request.getRemoteAddr());                 
                            addActionMessage(SystemMessage.USR_PW_UPDATE);

                    
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
        return inputBean;
    }
    
    
    @Override
    public boolean checkAccess(int userRole) {
        boolean status = false;
        String page = PageVarList.CONF_PASSWDCHANGE;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
}
