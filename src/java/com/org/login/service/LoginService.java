/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.login.service;

import com.inv.db.DBConnection;
import com.org.login.bean.ModuleBean;
import com.org.login.bean.PageBean;
import com.org.login.bean.UserLoginBean;
import com.inv.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kreshan
 */
public class LoginService {



    public boolean getDbUserPassword(UserLoginBean ulb) throws Exception {
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
       
 
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "SELECT U.PASSWORD,U.STATUS,U.USER_ID,U.USER_PROFILE "
                    + "FROM ic_user AS U  WHERE  U.USERNAME = ? ";
            perSt = con.prepareStatement(sql);
            perSt.setString(1, ulb.getUserName());
            res = perSt.executeQuery();

            while (res.next()) {
                ulb.setDBpassword(res.getString("PASSWORD"));
                ulb.setDBuserStatus(res.getString("STATUS"));
                ulb.setDBuserId(res.getInt("USER_ID"));
                ulb.setDBuserProfile(res.getInt("USER_PROFILE"));
//                ulb.setDBuserlogPath(res.getString("PATH"));              

            }
            con.commit();
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
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
        return true;

    }

    public boolean varifilogin(UserLoginBean userloginBean) throws Exception{

       
        if (Util.generateHash(userloginBean.getPassword()).equals(userloginBean.getDBpassword())) {

            return true;
        } else {
            return false;
        }

    }

//    public boolean getHomeValues(UserLoginBean ulb,HomeValues homeValues) throws Exception {
//        
//      HttpSession session = ServletActionContext.getRequest().getSession(false);
//      SessionUserBean su = (SessionUserBean)session.getAttribute("SessionObject");
//      
//        PreparedStatement perSt = null;
//        ResultSet res = null;
//        Connection con = null;
//
//        try {
//            con = DBConnection.getConnection();
//            con.setAutoCommit(false);
//            String sql = "SELECT * FROM E24OCM_VERSION where INSTITUTE_ID= ? AND APP_TYPE= ? AND APP_ID= ? ";
//            perSt = con.prepareStatement(sql);
//            perSt.setInt(1,Integer.parseInt( "1"));
//            perSt.setString(2, ulb.getDBuserAppType());
//            perSt.setInt(3, ulb.getDBuserAppId());
//            res = perSt.executeQuery();
//
//            while (res.next()) {
//
//                homeValues.setVersionNo(res.getString("VERSION_NUM"));
//                homeValues.setEfiModuleNo(res.getString("MODULE_NUM"));
//                homeValues.setDescription(res.getString("DESCRIPTION"));
//
//            }
//            con.commit();
//        } catch (Exception ex) {
//            con.rollback();
//            throw ex;
//        } finally {
//            if (perSt != null) {
//                perSt.close();
//            }
//            if (res != null) {
//                res.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        
//      
//        return true;
//    }

    //get REC section page according to the user role
    public Map<ModuleBean, List<PageBean>> getModulePageByUser(int userID) throws Exception {
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        Map<ModuleBean, List<PageBean>> modulePageList = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            perSt = con.prepareStatement("SELECT ISC.PAGE_ID,ISC.PAGE_NAME,ISC.PAGE_URL,ISP.CODE,ISP.DESCRIPTION,IP.PROFILE_ID "
                    + "FROM mt_modules ISP,mt_page ISC,ic_user_pf_module IP "
                    + "WHERE ISP.CODE=IP.MODULE_ID AND ISC.PAGE_ID=IP.PAGE_ID AND IP.PROFILE_ID=? "
                    + "ORDER BY IP.MODULE_ID,IP.PAGE_ID");

            perSt.setInt(1, userID);

            res = perSt.executeQuery();
            modulePageList = new HashMap<ModuleBean, List<PageBean>>();
            String currentModule = "";
            List<PageBean> pageList = null;
            ModuleBean module = null;
            while (res.next()) {
                if (!res.getString("ISP.CODE").equals(currentModule)) {
                    currentModule = res.getString("ISP.CODE");
                    if (pageList != null && module != null) {
                        modulePageList.put(module, pageList);
                        pageList = null;
                        module = null;
                    }
                    module = new ModuleBean();
                    module.setMODULE_ID(res.getString("ISP.CODE"));
                    module.setMODULE_NAME(res.getString("ISP.DESCRIPTION"));

                    pageList = new ArrayList<PageBean>();
                    PageBean page = new PageBean();
                    page.setPAGE_ID(res.getString("ISC.PAGE_ID"));
                    page.setPAGE_NAME(res.getString("ISC.PAGE_NAME"));
                    page.setPAGE_URL(res.getString("ISC.PAGE_URL"));

                    pageList.add(page);

                } else {

                    PageBean page = new PageBean();
                    page.setPAGE_ID(res.getString("ISC.PAGE_ID"));
                    page.setPAGE_NAME(res.getString("ISC.PAGE_NAME"));
                    page.setPAGE_URL(res.getString("ISC.PAGE_URL"));

                    pageList.add(page);
                }
            }
            if (pageList != null && module != null) {
                modulePageList.put(module, pageList);
                pageList = null;
                module = null;
            }
            con.commit();
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
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
        return modulePageList;

    }

    public List<String> getUserprofilePageidList(int dBuserProfile) throws SQLException, Exception {
        
        List<String> pageidlist=new ArrayList<String>();
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "SELECT PAGE_ID FROM ic_user_pf_module WHERE PROFILE_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, dBuserProfile);
            res = perSt.executeQuery();

            while (res.next()) {
                pageidlist.add(res.getString("PAGE_ID"));
            }
            con.commit();
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
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
        return pageidlist;
        
    }

    

   

}
