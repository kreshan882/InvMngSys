/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.item.action;

import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.DBProcesses;
import com.inv.util.LogFileCreator;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.inv.util.Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.item.bean.EditViewItemInputBean;
import com.org.item.bean.ItemBeen;
import com.org.item.servive.EditViewItemService;
import com.org.login.bean.SessionUserBean;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan88
 */
public class EditViewItem extends ActionSupport implements ModelDriven<EditViewItemInputBean> , AccessControlService{
    EditViewItemInputBean inputBean =new EditViewItemInputBean();
    EditViewItemService service = new EditViewItemService();
    HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean)ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    
    public String execute(){
        return SUCCESS;
    }
    
        public String List(){
        System.out.println("ssssssssssssssssssssss");
        List<ItemBeen> dataList = null;
        try {
                int rows = inputBean.getRows();
                int page = inputBean.getPage();
                int to = (rows * page);
                int from = to - rows;
                long records = 0;
                String orderBy = "";    

                if (!inputBean.getSidx().isEmpty()) {
                    orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
                }
                dataList=service.loadData(inputBean, orderBy, rows, from);

                if (!dataList.isEmpty()) {
                    records = dataList.get(0).getFullCount();
                    inputBean.setRecords(records);
                    inputBean.setGridModel(dataList);
                    int total = (int) Math.ceil((double) records / (double) rows);
                    inputBean.setTotal(total);
                } else {
                    inputBean.setRecords(0L);
                    inputBean.setTotal(0);
                }
//            }
        } catch (Exception ex) {
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
        }
        return "list";
     }
        public String Delete() {
        try {
            if(service.deleteData( inputBean)){
                DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.ITEM_MANAGEMENT, Operation.DELETE, SystemMessage.ITEM_DELETE+" Item ID:"+inputBean.getDitemNo(),request.getRemoteAddr());        
                LogFileCreator.writeInfoToLog(SystemMessage.ITEM_DELETE+" Item No:"+inputBean.getDitemNo());
                inputBean.setDsuccess(true);
                inputBean.setDmessage(SystemMessage.ITEM_DELETE);
            }else{
                inputBean.setDsuccess(false);
                inputBean.setDmessage(SystemMessage.ITEM_DELETE_FAIL);
            }
        } catch (Exception ex) {
            inputBean.setDsuccess(false);
            inputBean.setDmessage(SystemMessage.ITEM_DELETE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "delete";
    }

   
    public String Find() {
        try {
            System.out.println("finddddddddddddd"+inputBean.getUpitemNo());
            service.findForUpdate(inputBean);
        } catch (Exception e) {      
            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
        }
        return "find";
    }

  

     
    public String Update() {
         
        try {
            if (doValidation(inputBean)) {
               if(service.updateData(inputBean)){
                addActionMessage(SystemMessage.CUS_UPDATE);
                DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.ITEM_MANAGEMENT, Operation.UPDATE, SystemMessage.ITEM_UPDATE+":"+inputBean.getUpitemNo(),request.getRemoteAddr());     
                LogFileCreator.writeInfoToLog(SystemMessage.ITEM_UPDATE+":"+inputBean.getUpitemNo()); 
              
               } else {    
                  addActionError(SystemMessage.ITEM_UPDATE_FAIL);
               }
              
            }
        } catch (Exception ex) {

            addActionError(SystemMessage.ITEM_UPDATE_FAIL);
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);

        }
        return "update";
    }
    
    private boolean doValidation(EditViewItemInputBean bean) throws Exception {
        boolean ok = false;
        

        try {
            if (bean.getUpname() == null || bean.getUpname().isEmpty()) {
                addActionError(SystemMessage.ITEM_NAME_EMPTY);
                return ok;
            } else if (!Util.validateNAME(bean.getUpname())) {
                addActionError(SystemMessage.ITEM_NAME_INVALID);
                return ok;
            }else if (bean.getUpcolour() == null || bean.getUpcolour().isEmpty()) {
                addActionError(SystemMessage.ITEM_COLOR_EMPTY);
                return ok;
            } else if (!Util.validateNAME(bean.getUpcolour())) {
                addActionError(SystemMessage.ITEM_COLOR_INVALID);
                return ok;
            }else if (bean.getUpunitPrize() == null || bean.getUpunitPrize().isEmpty()) {
                addActionError(SystemMessage.ITEM_UNITPRIZE_EMPTY);
                return ok;
            } else if (!Util.validateNAME(bean.getUpunitPrize())) { //check double
                addActionError(SystemMessage.ITEM_UNITPRIZE_INVALID);
                return ok;
            }else if ("-1".equals(bean.getUpstatus())) {
                addActionError(SystemMessage.ITEM_STATUS_EMPTY);
                return ok;
            }

            else {
                ok = true;
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        return ok;
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
