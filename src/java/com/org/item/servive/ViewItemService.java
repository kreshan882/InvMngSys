/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.item.servive;

import com.inv.db.DBConnection;
import com.org.item.bean.ItemBeen;
import com.org.item.bean.ViewItemInputBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kreshan88
 */
public class ViewItemService {

    public List<ItemBeen> loadData(ViewItemInputBean inputBean, String orderBy, int max, int first )throws Exception{
    
        
        List <ItemBeen> dataList = null;       
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        long totalCount = 0;

        try {            
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sqlCount = "SELECT COUNT(*) AS TOTAL FROM ic_items WHERE ITEM_NO LIKE ?";
            
            perSt = con.prepareStatement(sqlCount);  
            perSt.setString(1, "%"+inputBean.getItemNo()+"%"); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT ITEM_NO,NAME,COLOUR,UNIT_PRIZE,IMG_PATH,STATUS,CAST(REG_DATE AS CHAR) AS REG_DATE "
                    + " FROM ic_items WHERE ITEM_NO LIKE ? "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setString(1, "%"+inputBean.getItemNo()+"%"); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<ItemBeen>();
             while (res.next()) {
                 
                 ItemBeen been = new ItemBeen();

                 been.setItemNo(res.getString("ITEM_NO")); 
                 been.setName(res.getString("NAME"));
                 been.setColour(res.getString("COLOUR"));
                 been.setUnitPrize(res.getString("UNIT_PRIZE"));
                 been.setDbfilename(res.getString("IMG_PATH"));
                 been.setStatusCode(res.getString("STATUS"));
                 been.setRegDate(res.getString("REG_DATE"));             
                 
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
