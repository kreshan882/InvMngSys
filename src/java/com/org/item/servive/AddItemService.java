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
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class AddItemService {

    public boolean addItem(ItemBeen userBean ,  SessionUserBean sessionUserBean) throws Exception{
        Connection con = null;
        String query;
        PreparedStatement preStat=null;
        boolean ok=false;
        
        
        try {
            FileInputStream inputStream = new FileInputStream(userBean.getImage());
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            query="INSERT INTO ic_items(ITEM_NO,NAME,ITEM_TYPE,COLOUR,UNIT_PRIZE,"
                    + "IMG_PATH,STATUS,REG_DATE) "
                    + "VALUES(?,?,?,?,?,  ?,?,?)";
            
            preStat = con.prepareStatement(query);

            preStat.setString(1, userBean.getItemNo());
            preStat.setString(2, userBean.getName());
            preStat.setString(3, userBean.getItemType());
            preStat.setString(4, userBean.getColour());
            preStat.setDouble(5, Double.parseDouble(userBean.getUnitPrize()));
           
            preStat.setString(6, userBean.getDbfilename());
            preStat.setString(7, Status.ACTIVE);
            preStat.setDate(8, (Date) Util.getLocalDate());
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
    


    
    
     public boolean checkItemExists(String itno) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        boolean ok=false;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT * FROM ic_items where ITEM_NO=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, itno);
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

    public boolean addStrockZero(ItemBeen inputBean, SessionUserBean sub) throws Exception{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        boolean ok=false;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sql = "SELECT STOR_ID FROM mt_store";
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();

            while (res.next()) {
                ps = null;
                System.out.println(">>"+res.getInt("STOR_ID"));
                String insStock = "insert into ic_stock(STOR_ID,ITEM_NO,COUNT) values(?,?,?)";
                ps = con.prepareStatement(insStock);
                ps.setInt(1, res.getInt("STOR_ID"));
                ps.setString(2, inputBean.getItemNo());
                ps.setDouble(3, 0);
                     int n= ps.executeUpdate();
                     if(n >= 0){
                        ok=true;
                      }
                  
                con.commit();
            }
           
        }
        catch (Exception ex) {
            throw ex;
	}finally {
            if (ps != null) {
                ps.close();
            }
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return  ok;
    }
  
    
    
  
    
}
