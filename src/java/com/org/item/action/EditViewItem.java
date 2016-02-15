/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.item.action;

import com.inv.util.AccessControlService;
import com.inv.util.Common;
import com.inv.util.LogFileCreator;
import com.inv.util.PageVarList;
import com.inv.util.SystemMessage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.org.item.bean.EditViewItemInputBean;
import com.org.item.bean.ItemBeen;
import com.org.item.servive.EditViewItemService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan88
 */
public class EditViewItem extends ActionSupport implements ModelDriven<EditViewItemInputBean> , AccessControlService{
    EditViewItemInputBean inputBean =new EditViewItemInputBean();
    EditViewItemService service = new EditViewItemService();
    
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
