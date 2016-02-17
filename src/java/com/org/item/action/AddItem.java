/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.item.action;


import com.inv.init.InitConfigValue;
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
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
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
                String IMAGE_PATH=Util.getOSLogPath(InitConfigValue.GF_ROOT_PATH+"items");
                System.out.println("image path>"+IMAGE_PATH);
                String filenm[] = inputBean.getImageFileName().split("\\.",2);    
                inputBean.setDbfilename(inputBean.getItemNo()+"."+filenm[1]);
                File newFileLocation  = new File(IMAGE_PATH, inputBean.getDbfilename());
                
                FileUtils.copyFile(inputBean.getImage(), newFileLocation);
                
                if (service.addItem(inputBean, sub)) {
                    if(service.addStrockZero(inputBean, sub)){
                        DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.ITEM_MANAGEMENT, Operation.ADD, SystemMessage.CUS_ADD+ " for " + inputBean.getItemNo(),request.getRemoteAddr()); 
                        addActionMessage(SystemMessage.CUS_ADD);
                        LogFileCreator.writeInfoToLog(SystemMessage.CUS_ADD+inputBean.getItemNo());
                    }
                    
                    
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
                addActionError(SystemMessage.ITEM_NUMBER_EMPTY);
                return ok;
            } else if (!Util.validateNUMBER(bean.getItemNo())) {
                addActionError(SystemMessage.ITEM_NUMBER_INVALID);
                return ok;
            }else if (service.checkItemExists(bean.getItemNo().trim())) {
                addActionError(SystemMessage.ITEM_NUMBER_ALREADY);
                return ok;
            }else if ("-1".equals(bean.getItemType())) {
                addActionError(SystemMessage.ITEM_ITEMTYPE_SELECT);
                return ok;
            }
            else if (bean.getName() == null || bean.getName().isEmpty()) {
                addActionError(SystemMessage.ITEM_NAME_EMPTY);
                return ok;
            } else if (!Util.validateNAME(bean.getName())) {
                addActionError(SystemMessage.ITEM_NAME_INVALID);
                return ok;
            }else if (bean.getColour() == null || bean.getColour().isEmpty()) {
                addActionError(SystemMessage.ITEM_COLOR_EMPTY);
                return ok;
            } else if (!Util.validateNAME(bean.getColour())) {
                addActionError(SystemMessage.ITEM_COLOR_INVALID);
                return ok;
            }else if (bean.getUnitPrize() == null || bean.getUnitPrize().isEmpty()) {
                addActionError(SystemMessage.ITEM_UNITPRIZE_EMPTY);
                return ok;
            } else if (!Util.validateAMOUNT(bean.getUnitPrize())) { //check double
                addActionError(SystemMessage.ITEM_UNITPRIZE_INVALID);
                return ok;
            }else if(bean.getImageFileName() == null || bean.getImageFileName().isEmpty()){ 
              addActionError(SystemMessage.ITEM_IMAGE_EMPTY);                
              return ok;
            }
//            else if (bean.getUpfileFileName() != null && !extension.equals(filetypeCheck)) {              
//              addActionError(SystemMessage.REQ_CSR_EXTENTION);                
//              return ok;
//            }
            else {
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
