/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.login.action;
import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.init.Status;
import com.inv.util.LogFileCreator;
import com.inv.util.DBProcesses;
import com.org.login.bean.HomeValues;
import com.org.login.bean.ModuleBean;
import com.org.login.bean.PageBean;
import com.org.login.bean.SessionUserBean;
import com.org.login.bean.UserLoginBean;
import com.org.login.service.LoginService;
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
    SessionUserBean sub = new SessionUserBean();
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
                            
                            System.out.println("pass ok, status active...............");
                            
                            sub.setUsername(userLoginBean.getUserName());
                            sub.setUserid(userLoginBean.getDBuserId());
                            sub.setCurrentUserProfileId(userLoginBean.getDBuserProfile());
                            sub.setLogFilePath("/opt/inventory/logs/");
                            
                            
  
                            HttpSession sessionPrevious = ServletActionContext.getRequest().getSession(false);
                            if (sessionPrevious != null) {
                                sessionPrevious.invalidate();
                            }

                            HttpSession session = ServletActionContext.getRequest().getSession(true);
                            session.setAttribute("SessionObject", sub);
                            
                            sub.setCurrentSessionId(session.getId());
                            
                            DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.LOGIN_MANAGEMENT, Operation.LOGIN, SystemMessage.LOGIN_MSG+ ":" + userLoginBean.getUserName(),request.getRemoteAddr());
                            LogFileCreator.writeInfoToLog(SystemMessage.LOGIN_MSG+":"+userLoginBean.getUserName());
                            
                            //load home page values
//                            service.getHomeValues(userLoginBean,homeValues);
//                            session.setAttribute("SessionHomeValues", homeValues);
                            
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
        return userLoginBean;
    }

    public String logoutFunction() {
        try {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
           
           

           if (session != null) {
                SessionUserBean su = (SessionUserBean) session.getAttribute("SessionObject");
                if(su!=null){
                    DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.LOGIN_MANAGEMENT, Operation.LOGOUT, SystemMessage.LOGOUT_MSG+ ":" + su.getUsername(),request.getRemoteAddr());              
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
