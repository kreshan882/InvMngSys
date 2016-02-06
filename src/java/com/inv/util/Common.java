/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inv.util;

import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kreshan
 */
public class Common {
    
      //checks the accees to the method name passed
    public boolean checkMethodAccess( String page, int userRole, HttpSession sessionk){
        boolean access = false;
        try{
            HttpSession session = sessionk;
            List<String> profilePageidList = (List<String>) session.getAttribute("profilePageidList");

                if(profilePageidList.contains(page)){
                    access=true;
                }else{
                    access=false;
                }
        }catch(Exception e){
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }      
        return access;
    }

}
