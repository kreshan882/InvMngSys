/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.item.action;

import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.PageVarList;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.item.bean.EditViewItemInputBean;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan88
 */
public class EditViewItem extends ActionSupport implements ModelDriven<EditViewItemInputBean> , AccessControlService{
    EditViewItemInputBean inputBean =new EditViewItemInputBean();

    public String execute(){
        return SUCCESS;
    }
    
    @Override
    public EditViewItemInputBean getModel() {
        return inputBean;
    }

    @Override
    public boolean checkAccess(int userRole) {
        boolean status = false;
        String page = PageVarList.ITEM_EDIT_VIEW;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
