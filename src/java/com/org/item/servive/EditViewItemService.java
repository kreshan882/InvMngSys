/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.item.servive;

import com.inv.db.DBConnection;
import com.org.item.bean.EditViewItemInputBean;
import com.org.item.bean.ItemBeen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kreshan88
 */
public class EditViewItemService {
    
    
     public List<ItemBeen> loadData(EditViewItemInputBean inputBean, String orderBy, int max, int first )throws Exception{
    
        
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
       
            quary ="SELECT ITEM_NO,NAME,COLOUR,UNIT_PRIZE,IMG_PATH,STATUS,CAST(REG_DATE AS CHAR) AS REG_DATE,UNIT_TCOST "
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
                 been.setUnitTcost(res.getString("UNIT_TCOST"));
                 
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
     
     
    public boolean deleteData(EditViewItemInputBean bean) throws Exception {

        boolean result=false;
        PreparedStatement prepSt = null;
        Connection con = null;
        String deleteUser = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            deleteUser = "delete from ic_items where ITEM_NO=?";
            prepSt = con.prepareStatement(deleteUser);

            prepSt.setInt(1,  Integer.parseInt(bean.getDitemNo()));
            prepSt.execute();
            con.commit();
            result=true;

        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            result=false;
            throw e;

        } finally {
            if (prepSt != null) {
                prepSt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
//Added on 22nd Aug to find user.

     public void findForUpdate(EditViewItemInputBean bean) throws Exception {
        
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String sql = null;
        try {
            System.out.println(""+bean.getUpitemNo());
            con = DBConnection.getConnection();
            sql = "SELECT ITEM_NO,NAME,COLOUR,UNIT_PRIZE,STATUS  FROM ic_items where ITEM_NO=?";

            prepSt = con.prepareStatement(sql);
            prepSt.setString(1,bean.getUpitemNo());

            res = prepSt.executeQuery();
            if (res.next()) {
                System.out.println(">>>>>>>>>"+res.getString("CUS_ID"));
                bean.setUpitemNo(res.getString("CUS_ID"));
                bean.setUpname(res.getString("NAME"));
                bean.setUpcolour(res.getString("COLOUR"));       
                bean.setUpunitPrize(res.getString("UNIT_PRIZE"));  
                bean.setUpstatus(res.getString("STATUS")); 
            }
            
        } catch (Exception e) {
            con.rollback();
            throw e;

        } finally {
            if (prepSt != null) {
                prepSt.close();
            }
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }

        }

    }

    public boolean updateData(EditViewItemInputBean inputBean) throws Exception {

        
        boolean ok = false;
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String sql = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
           
            sql = "UPDATE ic_items SET NAME=?, COLOUR=?, UNIT_PRIZE=?,STATUS=? WHERE ITEM_NO=?;";
            prepSt = con.prepareStatement(sql);
            prepSt.setString(1, inputBean.getUpname());
            prepSt.setString(2, inputBean.getUpcolour());
            prepSt.setString(3, inputBean.getUpunitPrize());
            prepSt.setString(4, inputBean.getUpstatus());
            prepSt.setString(5, inputBean.getUpitemNo());

            int n= prepSt.executeUpdate();
            if(n>0){
                ok = true;
            }
            con.commit();

        } catch (Exception e) {
            
            con.rollback();
            throw e;

        } finally {
            if (prepSt != null) {
                prepSt.close();
            }
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }

        }
           return ok;
    }
}
