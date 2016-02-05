/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.cust.action;

import com.inv.init.Module;
import com.inv.init.Operation;
import com.inv.log.LogFileCreator;
import com.org.login.bean.SessionUserBean;
import com.org.cust.bean.EditViewCustomerInputBean;
import com.org.cust.service.EditViewCustomerService;
import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.DBProcesses;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.inv.util.Util;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.cust.bean.CustomerBeen;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author tharaka
 */
public class EditViewCustomer extends ActionSupport implements ModelDriven<EditViewCustomerInputBean> , AccessControlService{

    private EditViewCustomerService  service = new EditViewCustomerService();
    private EditViewCustomerInputBean inputBean = new EditViewCustomerInputBean();
    HttpServletRequest request = ServletActionContext.getRequest();

    
    public String execute(){
        return SUCCESS;
    }
    

    @Override
    public EditViewCustomerInputBean getModel() {
        return inputBean;
    }

     public String List(){
         System.out.println("liiiiiiiiiiiiii");
        try {
                List<CustomerBeen> dataList = null;
                int rows = inputBean.getRows();
                int page = inputBean.getPage();
                int to = (rows * page);
                int from = to - rows;
                long records = 0;
                String orderBy = "";    

                if (!inputBean.getSidx().isEmpty()) {
                    orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
                }
                dataList=service.loadUsersFromInstiute(inputBean, orderBy, rows, from);
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
            addActionError(SystemMessage.USR_ERROR_UNHANDLE);
        }
        return "list";
     }
    
    public String Delete() {
        try {
            LogFileCreator.writeInfoToLog("Deleting  user from page EditAndViewUserISA");
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            SessionUserBean sub = (SessionUserBean)session.getAttribute("SessionObject");
//            DBProcesses.insertHistoryRecord(sub.getInstituteid(),
//                                sub.getUserid(),sub.getApptype(),sub.getAppid(),
//                                Module.USER_MANAGEMENT,Operation.DELETE,SystemMessage.USR_DELETED,request.getRemoteAddr());
//           
            service.deleteUserISA( inputBean);
//            addActionMessage(SystemMessage.USR_DELETED); 
//            inputBean.setMessage(SystemMessage.USR_DELETED);
            LogFileCreator.writeInfoToLog("Deleting  user from page EditAndViewUserISA succesfully completed");
            inputBean.setDelsuccess("1");
            
            
        } catch (Exception ex) {
            addActionError(SystemMessage.USR_ERROR_UNHANDLE);
            inputBean.setMessage(SystemMessage.USR_ERROR_UNHANDLE);
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "delete";
    }

   
    public String Find() {
        
        try {

            service.findUser(inputBean);

        } catch (Exception e) {
            
            addActionError(SystemMessage.USR_ERROR_UNHANDLE);

        }

        return "find";
    }

  

     
    public String Update() {
         
        try {
            boolean ok = false;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            SessionUserBean sub = (SessionUserBean)session.getAttribute("SessionObject");
            service.loadinstituteList(inputBean);

            LogFileCreator.writeInfoToLog("Updating  user from page EditAndViewUserISA");
            if (doValidation(inputBean)) {
              
               ok= service.updateUserISA(inputBean);
                System.out.println(ok);
               if(ok==true){
//                addActionMessage(SystemMessage.USR_UPDATED);
//                DBProcesses.insertHistoryRecord(sub.getInstituteid(),
//                                sub.getUserid(),sub.getApptype(),sub.getAppid(),
//                                Module.USER_MANAGEMENT,Operation.UPDATE,SystemMessage.USR_UPDATED,request.getRemoteAddr());
              
                LogFileCreator.writeInfoToLog("Updating  user from page EditAndViewUserISA succesfull."); 
              
               } else {    
//                  addActionError(SystemMessage.USR_UPDATED_ERROR);
               }
              
            }
        } catch (Exception ex) {

            addActionError(SystemMessage.USR_ERROR_UNHANDLE);
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);

        }
        return "update";
    }

    
    public String LoadUsers(){
        try{
        LogFileCreator.writeInfoToLog("Loading  user on for each institute from page EditAndViewUserISA");    
        service.loadUserListFromInstitute(inputBean);
        }catch(Exception ex){
        ex.printStackTrace();
        LogFileCreator.writeErrorToLog(ex);
        }
        return "loadusers";
    }
    
    private boolean  doValidation (EditViewCustomerInputBean bean) throws Exception{
        boolean ok = false;
        
        try {
            
            if(!Util.validateDESCRIPTION(bean.getUpcompany())){
               addActionError(SystemMessage.USR_INVALID_COMPANY);
                return ok;
           }else if (("-1".equals(bean.getUpstatus()))) {
                addActionError(SystemMessage.USR_EMPTY_STATUS);
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
        String page = PageVarList.CUS_EDIT_VIEW;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
}