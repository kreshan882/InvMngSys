/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.cust.action;


import com.inv.log.LogFileCreator;
import com.org.cust.bean.ViewUserAADataBean;
import com.org.cust.bean.ViewUserAAInputBean;
import com.org.cust.service.ViewUserAAService;
import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author tharaka
 */
public class ViewUserAA extends ActionSupport implements ModelDriven<ViewUserAAInputBean> , AccessControlService {

    
     private ViewUserAAInputBean inputBean = new ViewUserAAInputBean();
     private ViewUserAAService   service = new ViewUserAAService();
     
     public String View(){    
         try{      
            
//            service.loaduserCategoryList(inputBean);
//            service.loaduserProfileList(inputBean);
        
        }catch(Exception e){
        
                        
        }      
        
         return Action.SUCCESS;
     }
     
     public String List(){
        
      List<ViewUserAADataBean> dataList = null;
          
        try {
             
           
            if (inputBean.isSearch()) {
                LogFileCreator.writeInfoToLog("Searching  user from page ViewUserAA");
                int rows = inputBean.getRows();
                int page = inputBean.getPage();
                int to = (rows * page);
                int from = to - rows;
                long records = 0;
                String orderBy = "";    
                               
                
                
                if (!inputBean.getSidx().isEmpty()) {
                    orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
                }
 
                dataList=service.loadUsers(inputBean, orderBy, rows, from);
                
                
                
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

            }

        } catch (Exception ex) {
             ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
            addActionError(SystemMessage.USR_ERROR_UNHANDLE);
        }
          
         
     
         return "list";
     }
     
     
     @Override
    public ViewUserAAInputBean getModel() {
        return inputBean;
    }
    
         
    @Override
    public boolean checkAccess(int userRole) {
        boolean status = false;
        String page = PageVarList.USER_VIEWUSER;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
}
