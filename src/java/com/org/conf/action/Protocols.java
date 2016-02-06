/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.conf.action;

import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.PageVarList;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.conf.bean.ChangePasswordBean;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan88
 */
public class Protocols extends ActionSupport implements ModelDriven<ChangePasswordBean>, AccessControlService{
    ChangePasswordBean pp=new ChangePasswordBean();
    
    public String execute() {
        return SUCCESS;
    }
        
    @Override
    public ChangePasswordBean getModel() {
        return pp;
    }

    @Override
    public boolean checkAccess(int userRole) {
         boolean status = false;
         status=true;
//        String page = PageVarList.CONF_PASSWDCHANGE;
//            HttpSession session = ServletActionContext.getRequest().getSession(false);
//            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
