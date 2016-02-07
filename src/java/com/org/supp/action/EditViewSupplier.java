/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.supp.action;

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
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.supp.bean.EditViewSupplierInputBean;
import com.org.supp.bean.SupplierBeen;
import com.org.supp.service.EditViewSupplierService;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author tharaka
 */
public class EditViewSupplier extends ActionSupport implements ModelDriven<EditViewSupplierInputBean> , AccessControlService{

    private EditViewSupplierService  service = new EditViewSupplierService();
    private EditViewSupplierInputBean inputBean = new EditViewSupplierInputBean();
    HttpServletRequest request = ServletActionContext.getRequest();
    SessionUserBean sub = (SessionUserBean)ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    
    public String execute(){
        return SUCCESS;
    }
    

    @Override
    public EditViewSupplierInputBean getModel() {
        return inputBean;
    }

     public String List(){
        try {
                List<SupplierBeen> dataList = null;
                int rows = inputBean.getRows();
                int page = inputBean.getPage();
                int to = (rows * page);
                int from = to - rows;
                long records = 0;
                String orderBy = "";    

                if (!inputBean.getSidx().isEmpty()) {
                    orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
                }
                dataList=service.loadTableData(inputBean, orderBy, rows, from);
                System.out.println("jjjj"+dataList.size());
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
                DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.SUPPLIER_MANAGEMENT, Operation.DELETE, SystemMessage.SUPP_DELETE+" Cus ID:"+inputBean.getDsuppId(),request.getRemoteAddr());        
                LogFileCreator.writeInfoToLog(SystemMessage.SUPP_DELETE+" Sup ID:"+inputBean.getDsuppId());
                inputBean.setDsuccess(true);
                inputBean.setDmessage(SystemMessage.SUPP_DELETE);
            }else{
                inputBean.setDsuccess(false);
                inputBean.setDmessage(SystemMessage.SUPP_DELETE_FAIL);
            }
        } catch (Exception ex) {
            inputBean.setDsuccess(false);
            inputBean.setDmessage(SystemMessage.SUPP_DELETE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "delete";
    }

   
    public String Find() {
        try {
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
                DBProcesses.insertHistoryRecord(sub.getUserid(),  Module.SUPPLIER_MANAGEMENT, Operation.UPDATE, SystemMessage.SUPP_UPDATE+":"+inputBean.getUpsuppName(),request.getRemoteAddr());     
                LogFileCreator.writeInfoToLog(SystemMessage.CUS_UPDATE+":"+inputBean.getUpsuppName()); 
              
               } else {    
                  addActionError(SystemMessage.SUPP_UPDATE_FAIL);
               }
              
            }
        } catch (Exception ex) {

            addActionError(SystemMessage.SUPP_UPDATE_FAIL);
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);

        }
        return "update";
    }

    
    private boolean  doValidation (EditViewSupplierInputBean supBean) throws Exception{
        boolean ok = false;
        
        try {
            
            if (supBean.getUpaddress() == null || supBean.getUpaddress().isEmpty()) {
                addActionError(SystemMessage.SUPP_ADDR_EMPTY);
                return ok;
            } else if (!Util.validateDESCRIPTION(supBean.getUpaddress())) {
                addActionError(SystemMessage.SUPP_ADDR_INVALID);
                return ok;
            }else if (supBean.getUpemail() == null || supBean.getUpemail().isEmpty()) {
                addActionError(SystemMessage.SUPP_EMAIL_EMPTY);
                return ok;
            } else if (!Util.validateEMAIL(supBean.getUpemail())) {
                addActionError(SystemMessage.SUPP_EAMIL_INVALID);
                return ok;
            }else  if (supBean.getUptpOffice() == null || supBean.getUptpOffice().isEmpty()) {
                addActionError(SystemMessage.SUPP_TP_OFFI_EMPTY);
                return ok;
            } else if (!Util.validatePHONENO(supBean.getUptpOffice())) {
                addActionError(SystemMessage.SUPP_TP_OFFI_INVALID);
                return ok;
            }else if (supBean.getUptpMobile() == null || supBean.getUptpMobile().isEmpty()) {
                addActionError(SystemMessage.SUPP_TP_MOB_EMPTY);
                return ok;
            } else if (!Util.validatePHONENO(supBean.getUptpMobile())) {
                addActionError(SystemMessage.SUPP_TP_MOB_INVALID);
                return ok;
            }else if ("-1".equals(supBean.getUpstatus())) {
                addActionError(SystemMessage.SUPP_STATUS_EMPTY);
                return ok;
            }else {
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
        String page = PageVarList.SUPP_EDIT_VIEW;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
}
