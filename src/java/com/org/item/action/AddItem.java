/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.item.action;


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
import com.org.item.bean.ItemBeen;
import com.org.item.servive.AddItemService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;


public class AddItem extends ActionSupport implements ModelDriven<ItemBeen> , AccessControlService{
    
    ItemBeen inputBean = new ItemBeen();
    AddItemService service = new AddItemService(); 
    HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    @Override
    public ItemBeen getModel() {
        return inputBean;
    }
    
    
    public String Add() {
        
        try {
  
            if (doValidation(inputBean)) {
                
                
                if (service.addData(inputBean, sub)) {
                    DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.ITEM_MANAGEMENT, Operation.ADD, SystemMessage.CUS_ADD+ " for " + inputBean.getItemNo(),request.getRemoteAddr());
                    
                    addActionMessage(SystemMessage.CUS_ADD);
                    LogFileCreator.writeInfoToLog(SystemMessage.CUS_ADD+inputBean.getItemNo());
                    
                } else {
                   addActionError(SystemMessage.CUS_ADD_FAIL); 
                    
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
    
    private boolean doValidation(ItemBeen bean) throws Exception {
        boolean ok = false;
        try {
            if (bean.getItemNo() == null || bean.getItemNo().isEmpty()) {
                addActionError(SystemMessage.CUS_NAME_EMPTY);
                return ok;
            } else if (!Util.validateNUMBER(bean.getItemNo())) {
                addActionError(SystemMessage.CUS_NAME_INVALID);
                return ok;
            }else if (service.checkCusName(bean.getItemNo())) {
                addActionError(SystemMessage.CUS_NAME_ALREADY);
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
        String page = PageVarList.ITEM_ADD;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
