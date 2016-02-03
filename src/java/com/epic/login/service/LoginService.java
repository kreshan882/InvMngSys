/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.login.service;

import com.inv.db.DBConnection;
import com.inv.init.AppType;
import com.inv.init.Status;
import com.epic.login.bean.HomeValues;
import com.epic.login.bean.ModuleBean;
import com.epic.login.bean.PageBean;
import com.epic.login.bean.SessionUserBean;
import com.epic.login.bean.UserLoginBean;
import com.inv.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan
 */
public class LoginService {

//    public static boolean getInstitutesDb( UserLoginBean ub) throws Exception {
//        PreparedStatement perSt = null;
//        ResultSet res = null;
//        Connection con=null;
//        ub.getInstituteList().clear();
//        try {
//            con= DBConnection.getConnection();
//            con.setAutoCommit(false);
//            String sql = "SELECT INSTITUTE_ID,NAME FROM E24OCM_INSTITUTE WHERE (STATUS = ? OR STATUS = ?)";
//            perSt = con.prepareStatement(sql);
//            perSt.setString(1, Status.ACTIVE);
//            perSt.setString(2, Status.INACTIVE);
//            res = perSt.executeQuery();
//
//            while (res.next()) {
//                ub.getInstituteList().put(res.getInt("INSTITUTE_ID")+"",res.getString("NAME"));
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
//        return true;
//
//    }

    public boolean getDbUserPassword(UserLoginBean ulb) throws Exception {
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
       
 
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "SELECT U.PASSWORD,U.STATUS,U.USER_ID,U.APP_TYPE,U.APP_ID,U.INSTITUTE_ID,U.USER_CATEGORY,U.USER_PROFILE,LG.PATH "
                    + "FROM E24OCM_USER AS U,E24OCM_INSTITUTE AS I,E24OCM_INSTITUTE_CONFIG AS IC,E24OCM_LOG_CONFIG AS LG "
                    + "WHERE U.INSTITUTE_ID=I.INSTITUTE_ID AND I.CONF_PROFILE=IC.ID AND IC.LOG_GONFIG_ID=LG.ID AND U.USERNAME = ? AND I.INSTITUTE_ID = ?;";
            perSt = con.prepareStatement(sql);
            perSt.setString(1, ulb.getUserName());
            perSt.setString(2, "1");
            res = perSt.executeQuery();

            while (res.next()) {
                ulb.setDBpassword(res.getString("PASSWORD"));
                ulb.setDBuserStatus(res.getString("STATUS"));
                ulb.setDBuserId(res.getInt("USER_ID"));
                ulb.setDBuserAppType(res.getString("APP_TYPE"));
                ulb.setDBuserAppId(res.getInt("APP_ID"));
                ulb.setDBuserInstituteId(res.getInt("INSTITUTE_ID"));
                ulb.setDBuserCategory(res.getInt("USER_CATEGORY"));
                ulb.setDBuserProfile(res.getInt("USER_PROFILE"));
                ulb.setDBuserlogPath(res.getString("PATH"));              

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

    public boolean getHomeValues(UserLoginBean ulb,HomeValues homeValues) throws Exception {
        
      HttpSession session = ServletActionContext.getRequest().getSession(false);
      SessionUserBean su = (SessionUserBean)session.getAttribute("SessionObject");
      
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "SELECT * FROM E24OCM_VERSION where INSTITUTE_ID= ? AND APP_TYPE= ? AND APP_ID= ? ";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1,Integer.parseInt( "1"));
            perSt.setString(2, ulb.getDBuserAppType());
            perSt.setInt(3, ulb.getDBuserAppId());
            res = perSt.executeQuery();

            while (res.next()) {

                homeValues.setVersionNo(res.getString("VERSION_NUM"));
                homeValues.setEfiModuleNo(res.getString("MODULE_NUM"));
                homeValues.setDescription(res.getString("DESCRIPTION"));

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
        
         try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
          if(AppType.CA.equals(su.getApptype())) { 
            String sql = "SELECT CA.NAME,AT.DESCRIPTION "
                    + "FROM E24OCM_CA CA,E24OCM_APP_TYPE AT,E24OCM_USER U,E24OCM_INSTITUTE I "
                    + "WHERE CA.CA_ID=U.APP_ID AND AT.CODE=U.APP_TYPE AND  U.USERNAME =? AND I.INSTITUTE_ID =?;";
            perSt = con.prepareStatement(sql);

          }else if(AppType.RA.equals(su.getApptype())){
             String sql = "SELECT RA.NAME,AT.DESCRIPTION "
                    + "FROM E24OCM_RA RA,E24OCM_APP_TYPE AT,E24OCM_USER U,E24OCM_INSTITUTE I "
                    + "WHERE RA.RA_ID=U.APP_ID AND AT.CODE=U.APP_TYPE AND  U.USERNAME =? AND I.INSTITUTE_ID =?;";
            perSt = con.prepareStatement(sql);

          }else if(AppType.OCSP.equals(su.getApptype())){
             String sql = "SELECT OCSP.NAME,AT.DESCRIPTION "
                    + "FROM E24OCM_OCSP OCSP,E24OCM_APP_TYPE AT,E24OCM_USER U,E24OCM_INSTITUTE I "
                    + "WHERE OCSP.OCSP_ID=U.APP_ID AND AT.CODE=U.APP_TYPE AND  U.USERNAME =? AND I.INSTITUTE_ID =?;";
            perSt = con.prepareStatement(sql);

          }else if(AppType.TPU.equals(su.getApptype())){
             String sql = "SELECT TPU.NAME,AT.DESCRIPTION "
                    + "FROM E24OCM_TPU TPU,E24OCM_APP_TYPE AT,E24OCM_USER U,E24OCM_INSTITUTE I "
                    + "WHERE TPU.TPU_ID=U.APP_ID AND AT.CODE=U.APP_TYPE AND  U.USERNAME =? AND I.INSTITUTE_ID =?;";
            perSt = con.prepareStatement(sql);

          }else if(AppType.DRM.equals(su.getApptype())){
             String sql = "SELECT DRM.NAME,AT.DESCRIPTION "
                    + "FROM E24OCM_DRM DRM,E24OCM_APP_TYPE AT,E24OCM_USER U,E24OCM_INSTITUTE I "
                    + "WHERE DRM.DRM_ID=U.APP_ID AND AT.CODE=U.APP_TYPE AND  U.USERNAME =? AND I.INSTITUTE_ID =?;";
            perSt = con.prepareStatement(sql);

          }else if(AppType.EIM.equals(su.getApptype())){
             String sql = "SELECT AT.DESCRIPTION "
                    + "FROM E24OCM_APP_TYPE AT,E24OCM_USER U,E24OCM_INSTITUTE I "
                    + "WHERE AT.CODE=U.APP_TYPE AND  U.USERNAME =? AND I.INSTITUTE_ID =?;";
            perSt = con.prepareStatement(sql);
          }else if(AppType.IAM.equals(su.getApptype())){
             String sql = "SELECT AT.DESCRIPTION "
                    + "FROM E24OCM_APP_TYPE AT,E24OCM_USER U,E24OCM_INSTITUTE I "
                    + "WHERE AT.CODE=U.APP_TYPE AND  U.USERNAME =? AND I.INSTITUTE_ID =?;";
            perSt = con.prepareStatement(sql);
          }
            
            perSt.setString(1, ulb.getUserName());
            perSt.setString(2, ulb.getInstitute());
            
            res = perSt.executeQuery();

            while (res.next()) {
               if(AppType.CA.equals(su.getApptype())) {   
                  homeValues.setAppIdName(res.getString("CA.NAME"));
                  homeValues.setAppTypeName(res.getString("AT.DESCRIPTION"));
               }else if(AppType.RA.equals(su.getApptype())){
                   homeValues.setAppIdName(res.getString("RA.NAME"));
                   homeValues.setAppTypeName(res.getString("AT.DESCRIPTION"));
               } else if(AppType.OCSP.equals(su.getApptype())){
                   homeValues.setAppIdName(res.getString("OCSP.NAME"));
                   homeValues.setAppTypeName(res.getString("AT.DESCRIPTION"));
               } else if(AppType.DRM.equals(su.getApptype())){
                   homeValues.setAppIdName(res.getString("DRM.NAME"));
                   homeValues.setAppTypeName(res.getString("AT.DESCRIPTION"));
               } else if(AppType.TPU.equals(su.getApptype())){
                   homeValues.setAppIdName(res.getString("TPU.NAME"));
                   homeValues.setAppTypeName(res.getString("AT.DESCRIPTION"));
               } else if(AppType.EIM.equals(su.getApptype())){
                  homeValues.setAppIdName("Epic");
                  homeValues.setAppTypeName(res.getString("AT.DESCRIPTION"));
               }else if(AppType.IAM.equals(su.getApptype())){
                  homeValues.setAppIdName("Institute");
                  homeValues.setAppTypeName(res.getString("AT.DESCRIPTION"));
               }        
              

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
        homeValues.setInstitute(ulb.getInstituteList().get(ulb.getInstitute()));
        return true;
    }

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
                    + " FROM E24OCM_MODULE ISP,E24OCM_PAGE ISC,E24OCM_USER_PF_MODULE IP"
                    + " WHERE ISP.CODE=IP.MODULE_ID AND ISC.PAGE_ID=IP.PAGE_ID AND IP.PROFILE_ID=?"
                    + "	ORDER BY IP.MODULE_ID,IP.PAGE_ID");

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
            String sql = "SELECT PAGE_ID FROM E24OCM_USER_PF_MODULE where PROFILE_ID=?";
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

    public UserLoginBean  getCertficateStatus(UserLoginBean userLoginBean) throws Exception {
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String sql ="";
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
                   if(AppType.CA.equals(userLoginBean.getDBuserAppType())) {   
                       sql = "SELECT EXPIRE_STATUS,EXPIRE_DATE FROM E24OCM_CA where CA_ID=?";
                   }else if(AppType.RA.equals(userLoginBean.getDBuserAppType())){
                       sql = "SELECT CA.EXPIRE_STATUS,CA.EXPIRE_DATE FROM E24OCM_RA RA ,E24OCM_CA CA where RA.CA_ID=CA.CA_ID and RA.RA_ID=?";
                   } else if(AppType.OCSP.equals(userLoginBean.getDBuserAppType())){
                       sql = "SELECT CA.EXPIRE_STATUS,CA.EXPIRE_DATE FROM E24OCM_OCSP OCSP ,E24OCM_CA CA where OCSP.CA_ID=CA.CA_ID and OCSP.OCSP_ID=?";
                   } else if(AppType.DRM.equals(userLoginBean.getDBuserAppType())){

                   } else if(AppType.TPU.equals(userLoginBean.getDBuserAppType())){
                       sql ="SELECT CA.EXPIRE_STATUS,CA.EXPIRE_DATE FROM E24OCM_TPU TPU ,E24OCM_CA CA where TPU.CA_ID=CA.CA_ID and TPU.TPU_ID=?";
                   }
           
            perSt = con.prepareStatement(sql);
            if (AppType.CA.equals(userLoginBean.getDBuserAppType()) ||AppType.RA.equals(userLoginBean.getDBuserAppType()) ||AppType.OCSP.equals(userLoginBean.getDBuserAppType()) 
                    ||AppType.DRM.equals(userLoginBean.getDBuserAppType())||AppType.TPU.equals(userLoginBean.getDBuserAppType())){
                
            
            perSt.setInt(1, userLoginBean.getDBuserAppId());
            res = perSt.executeQuery();

            while (res.next()) {
                userLoginBean.setDBrootCertExpireStatus(res.getString("EXPIRE_STATUS"));
                userLoginBean.setDBrootCertExpiredate(res.getString("EXPIRE_DATE"));
            }
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
        return userLoginBean;

    }

    public void UpdateExpireStatus(UserLoginBean userLoginBean) throws Exception{
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = null;
        String deleteUserRole ;
       
        boolean ok = false;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            
            deleteUserRole = "UPDATE E24OCM_CA SET EXPIRE_STATUS =?  WHERE CA_ID=?";
            ps = con.prepareStatement(deleteUserRole);

            ps.setString(1,"1"); 
            ps.setInt(2,userLoginBean.getDBuserAppId());
            ok = ps.execute();
            
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
            
        }    
    }

}
