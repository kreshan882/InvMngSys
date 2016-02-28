/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.conf.service;

import com.inv.db.DBConnection;
import com.org.conf.bean.StockBean;
import com.org.conf.bean.StockReportInputBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kreshan88
 */
public class StockReportService {

    public List<StockBean> loadData(StockReportInputBean inputBean, String orderBy, int max, int first )throws Exception{
    
        
        List <StockBean> dataList = null;       
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        long totalCount = 0;
        if("0".equals(inputBean.getStoreId())) inputBean.setStoreId("");
        try {            
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sqlCount = "SELECT count(*) AS TOTAL FROM ic_stock WHERE STOR_ID LIKE ?  AND ITEM_NO LIKE ?";
            
            perSt = con.prepareStatement(sqlCount);  
            perSt.setString(1, "%"+inputBean.getStoreId()+"%"); 
            perSt.setString(2, "%"+inputBean.getItemNo()+"%"); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT st.ITEM_NO,itm.NAME,str.NAME AS STRNAME, st.COUNT FROM ic_stock st,ic_items itm,mt_store str "
                    + "WHERE st.ITEM_NO=itm.ITEM_NO AND str.STOR_ID=st.STOR_ID AND st.STOR_ID LIKE ?  AND st.ITEM_NO LIKE ? "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setString(1, "%"+inputBean.getStoreId()+"%"); 
            perSt.setString(2, "%"+inputBean.getItemNo()+"%"); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<StockBean>();
             while (res.next()) {
                 
                 StockBean been = new StockBean();

                 been.setItemCode(res.getString("ITEM_NO"));    
                 been.setItemName(res.getString("NAME"));   
                 been.setStrName(res.getString("STRNAME"));
                 been.setQty(res.getString("COUNT"));                   
                 been.setFullCount(totalCount);                 
                 dataList.add(been);
                      
                }
            con.commit();
         
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally{
            if (perSt != null) {
                perSt.close();
            }
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return dataList;
    }
    
    
}
