/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.login.action;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.init.Status;
import com.inv.log.LogFileCreator;
import com.epic.login.bean.HomeValues;
import com.epic.login.bean.ModuleBean;
import com.epic.login.bean.PageBean;
import com.epic.login.bean.SessionUserBean;
import com.epic.login.bean.UserLoginBean;
import com.epic.login.service.LoginService;
import com.inv.util.DBProcesses;
import com.inv.util.SystemMessage;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan
 */
public class UserLogin extends ActionSupport implements Action, ModelDriven<UserLoginBean> {
    
    UserLoginBean userLoginBean = new UserLoginBean();
    HomeValues homeValues=new HomeValues();
    LoginService service = new LoginService();
    SessionUserBean sessionUserBean = new SessionUserBean();
    HttpServletRequest request = ServletActionContext.getRequest();
    Map<ModuleBean, List<PageBean>> modulePageList = null;
    List<String> profilePageidList =  new ArrayList<String>();
    public String execute() {
    
        return "success";
        
    }
    
    public String getInstitutes() {
         
        try {
//            service.getInstitutesDb(userLoginBean);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "success";
    }
    
    public String loginCheck() {
        try {
           
           
                if (service.getDbUserPassword(userLoginBean)) {
                    
                    if (service.varifilogin(userLoginBean)) {
                        if (userLoginBean.getDBuserStatus().equals(Status.ACTIVE)) {

                            
                            sessionUserBean.setUsername(userLoginBean.getUserName());
                            sessionUserBean.setUserid(userLoginBean.getDBuserId());
                            sessionUserBean.setInstituteid(userLoginBean.getDBuserInstituteId());
                            sessionUserBean.setCurrentUserProfileId(userLoginBean.getDBuserProfile());
                            sessionUserBean.setApptype(userLoginBean.getDBuserAppType());
                            sessionUserBean.setAppid(userLoginBean.getDBuserAppId());
                            sessionUserBean.setLogFilePath(userLoginBean.getDBuserlogPath());
                            
                            
                            userLoginBean=service.getCertficateStatus(userLoginBean);
                            
  
                            HttpSession sessionPrevious = ServletActionContext.getRequest().getSession(false);
                            if (sessionPrevious != null) {
                                sessionPrevious.invalidate();
                            }

                            HttpSession session = ServletActionContext.getRequest().getSession(true);
                            session.setAttribute("SessionObject", sessionUserBean);
                            
                            sessionUserBean.setCurrentSessionId(session.getId());
                            
                            DBProcesses.insertHistoryRecord(sessionUserBean.getInstituteid(),
                                    sessionUserBean.getUserid(), sessionUserBean.getApptype(), sessionUserBean.getAppid(),
                                    Module.LOGIN_MANAGEMENT, Operation.LOGIN, SystemMessage.LOGIN_MSG, request.getRemoteAddr());
                            
                            LogFileCreator.writeInfoToLog(SystemMessage.LOGIN_MSG);
                            //load home page values
                            service.getHomeValues(userLoginBean,homeValues);
                            session.setAttribute("SessionHomeValues", homeValues);
                            
                            profilePageidList = service.getUserprofilePageidList(userLoginBean.getDBuserProfile());
                            session.setAttribute("profilePageidList", profilePageidList);
                            
                            modulePageList = this.getModulePageByUser();
                            session.setAttribute("modulePageList", modulePageList);
                            return "success";
                        } else {
                            addActionError("User status not active");
                            return "login";
                        }
                        
                    } else {
                         
                        addActionError(SystemMessage.LOGIN_INVALID);
                        // addActionMessage("correct");
                        return "login";
                    }
                } else {
                     
                    addActionError(SystemMessage.LOGIN_INVALID);
                    return "login";
                }                
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            addActionError("Error contact administrator");
            LogFileCreator.writeErrorToLog(ex);
        }
        return null;
        
    }
    
    public String homeFunction() {
        return "success";
        
    }
            
    @Override
    public UserLoginBean getModel() {
        try {
//            service.getInstitutesDb(userLoginBean);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userLoginBean;
    }

    public String logoutFunction() {
        try {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
           
           

           if (session != null) {
                SessionUserBean su = (SessionUserBean) session.getAttribute("SessionObject");
                if(su!=null){
                    DBProcesses.insertHistoryRecord(su.getInstituteid(), su.getUserid(), su.getApptype(), su.getAppid(),
                    Module.LOGIN_MANAGEMENT, Operation.LOGOUT, SystemMessage.LOGOUT_MSG, request.getRemoteAddr());
                }else{
                    addActionError("Session timeout.");
                }
                
                session.removeAttribute("SessionObject");
                session.removeAttribute("SessionHomeValues");
                session.removeAttribute("profilePageidList");
                session.removeAttribute("modulePageList");
                session.invalidate();
                session = null;
            }else{
               addActionError("Session timeout");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return LOGIN;
    }
    
    public Map<ModuleBean, List<PageBean>> getModulePageByUser() throws Exception {
        
        try {
            
            modulePageList = service.getModulePageByUser(userLoginBean.getDBuserProfile());
            
        } catch (Exception e) {
            throw e;
        }
        
        return modulePageList;
    }
}
