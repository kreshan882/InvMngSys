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
import com.org.item.bean.ItemBeen;
import com.org.item.bean.ViewItemInputBean;
import com.org.item.servive.ExcelReport;
import com.org.item.servive.ViewItemService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan88
 */
public class ViewItem extends ActionSupport implements ModelDriven<ViewItemInputBean> , AccessControlService{

    ViewItemInputBean inputBean=new ViewItemInputBean();
    ViewItemService service =new ViewItemService();
    
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
    
    
     public String XSLcreat(){
         
        try {
            ByteArrayOutputStream outputStream = null;
            Object object =ExcelReport.generateExcelReport(inputBean);
            
            if (object instanceof XSSFWorkbook) {
                XSSFWorkbook workbook = (XSSFWorkbook) object;
                outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "excelreport";
    }
     
    @Override
    public ViewItemInputBean getModel() {
        return inputBean;
    }

    @Override
    public boolean checkAccess(int userRole) {
        boolean status = false;
        String page = PageVarList.ITEM_VIEW;
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(page, userRole, session);
        return status;
    }
    
}
