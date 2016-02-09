/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.item.servive;


import com.inv.db.DBConnection;
//import com.inv.init.AppType;
import com.inv.init.Status;
import com.org.login.bean.SessionUserBean;
import com.inv.util.Util;
import com.org.item.bean.ItemBeen;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class AddItemService {

    public boolean addData(ItemBeen userBean ,  SessionUserBean sessionUserBean) throws Exception{
        Connection con = null;
        String query;
        PreparedStatement preStat=null;
        boolean ok=false;
        
        
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            query="INSERT INTO ic_items(ITEM_NO,NAME,ITEM_TYPE,COLOUR,UNIT_PRIZE,"
                    + "UNIT_TYPE,IMG_PATH,STATUS,REG_DATE) "
                    + "VALUES(?,?,?,?,?,  ?,?,?,?)";
            
            preStat = con.prepareStatement(query);

            preStat.setString(1, userBean.getItemNo());
            preStat.setString(2, userBean.getName());
            preStat.setString(3, userBean.getItemType());
            preStat.setString(4, userBean.getColour());
            preStat.setDouble(5, Double.parseDouble(userBean.getUnitPrize()));
            
            preStat.setString(6, userBean.getUnitType());
            preStat.setString(7, userBean.getImageFileName());
            preStat.setString(8, Status.ACTIVE);
            preStat.setDate(9, (Date) Util.getLocalDate());

           int n= preStat.executeUpdate();
           if(n >= 0){
               ok=true;
           }
           con.commit();

        } catch (Exception e) {
            throw  e;
        }finally{
            if(preStat != null){
                preStat.close();
            }
            if(con != null){
                DBConnection.dbConnectionClose(con);
            }
        }
        return ok;
      
    }
    


    
    
     public boolean checkCusName(String cusname) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        boolean ok=false;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT * FROM ic_customer WHERE NAME=? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cusname);
            result = ps.executeQuery();

            if (result.next()) {
                ok = true;
            }
           
        }
        catch (Exception ex) {
            throw ex;
	}finally {
            if (ps != null) {
                ps.close();
            }
            if (result != null) {
                result.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return  ok;
        
        }
  
    
    
  
    
}
