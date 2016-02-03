 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inv.interceptor;

import com.inv.log.LogFileCreator;
import com.org.login.action.UserLogin;
import com.org.login.bean.SessionUserBean;
import com.inv.util.AccessControlService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
/**
 *
 * @author kreshan
 */
public class AccessControlInterceptor  implements Interceptor{

    @Override
    public void destroy() {
//         System.out.println("Interceptor : Destroyed");
    }

    @Override
    public void init() {
//        System.out.println("Interceptor : Initialized");
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        String result = "";
        String INTERCEPT_LOGOUT = "noaccessPage";
        try {
            String className = ai.getAction().getClass().getName();
           if(ai.getAction() instanceof UserLogin ){
             result = ai.invoke();   
           }
           else {
       
                    HttpServletRequest request = ServletActionContext.getRequest();
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                         SessionUserBean sessionUser = (SessionUserBean)session.getAttribute("SessionObject");
                        if (sessionUser != null) {
                            //check user logged in another mechine
//                            ServletContext sc = ServletActionContext.getServletContext();
//                            HashMap<String, String> userMap = (HashMap<String, String>) sc.getAttribute(SessionVarlist.USERMAP);
//                            String sessionId = userMap.get(user.getUsername());
//                            //
//                            if (sessionId.equals(session.getId())) {
                                //check user access
                                //boolean status = false;
                                Object action = ai.getAction();
                                if (action instanceof AccessControlService) {
                                    if (((AccessControlService) action).checkAccess( sessionUser.getCurrentUserProfileId())) {
                                        result = ai.invoke();
                                    } else {
                                        result = INTERCEPT_LOGOUT;
                                    }
                                    
                                } else {/*-------------allowed everyone have to be deletede after implemented------------*/
//                                    result = ai.invoke();
                                }
//                            } else {
//                                result = "multiaccess";
//                            }
                        } else {
                            result = INTERCEPT_LOGOUT; //session user null
                        }
                    } else {
                        result = INTERCEPT_LOGOUT; //session null
                    }
                
            }
           
         } catch (Exception ex) {
                    ex.printStackTrace();
                    LogFileCreator.writeErrorToLog(ex);
                    result = INTERCEPT_LOGOUT;
         }
//        System.out.println("innnnnn:"+result);
        return result;
    }
    
}
