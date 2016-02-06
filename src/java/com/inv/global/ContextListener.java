/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inv.global;

import com.inv.db.DBConnection;
import com.inv.util.Util;
import com.inv.util.ReaderDbInit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author kreshan
 */
public class ContextListener implements ServletContextListener{

      
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            
//~~~~~~~~~~start set globle configuration data~~~~~~~~~~~~~~
            Util.setDBxmlPath();
            ReaderDbInit.readConfigValues();
            DBConnection.createDbPool();
           
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, "glash fish loggggggggggggggggggggggggggg kkkkk");
            System.out.println("hsm end@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//~~~~~~~~~~end set globle configuration data~~~~~~~~~~~~~~~~
            
          
            
            
            
            System.out.println("Global Variable Initialized.");
        } catch (Exception e) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.out.println("Global Variable Destroyed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//////////////////
//dont remove/////
//////////////////


//C:\Program Files\Java\jdk1.7.0_25\jre\lib\security\java.secutity
//security.provider.11=org.bouncycastle.jce.provider.BouncyCastleProvider

//C:\Program Files\Java\jre7\lib\ext
//past this :bcprov-jdk16-143.jar
