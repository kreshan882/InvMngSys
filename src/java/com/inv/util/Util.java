/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inv.util;

import com.inv.db.DBConnection;
import com.inv.init.InitConfigValue;
import com.inv.init.Status;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tharaka
 */
public class Util {


    public static boolean validateNAME(String text) throws Exception { 
        return text.matches("^[a-zA-Z0-9]+$") &&    text.length() <= 50 ;
    }
    public static boolean validateNUMBER(String numericString) throws Exception { 
        return numericString.matches("^[0-9]*$") && numericString.length() <= 15;
    }
    public static boolean validateEMAIL(String email) throws Exception {  //   VF2
        return email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") &&    email.length() <= 50  ;
    }
    public static boolean validatePHONENO(String numericString) throws Exception { //   VF1
        return numericString.matches("^[0-9]*$") && numericString.length() <= 15;
    }
     public static boolean validateNIC(String nic) {           
        return nic.matches("^[0-9]+[VX]?$")&& nic.length() <= 10;
    }
    public static boolean validateSPECIALCHAR(String specialChars) throws Exception {       // VF5
        return specialChars.matches("[~@#$&!~]+");
    }

    public static boolean validateDESCRIPTION(String text) {                  
        return text.matches("^(.*/)?(?:$|(.+?)(?:(\\.[^.]*$)|$))") &&  text.length() <= 150;
    }


    

    
    public static Date convertStringToDate(String dateString) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = format.parse(dateString);
        return date;
    }

        
    public static  java.sql.Date  convertStringToDBDate(String date)throws Exception{
 
      SimpleDateFormat formtter = new SimpleDateFormat("yyyy-MM-dd");       
      java.util.Date dtt = formtter.parse(date);        
      return  new java.sql.Date(dtt.getTime());
            
    }
   
    public static Map<String, String> getBasicStatus() {
        Map<String, String> basicStatus = new HashMap<String, String>();
        basicStatus.put(Status.ACTIVE, "ACTIVE");
        basicStatus.put(Status.INACTIVE, "INACTIVE");
        return basicStatus;
    }
    

    

    
    
  public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
    List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
     
    Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

        @Override
        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    });


    Map<K,V> sortedMap = new LinkedHashMap<K,V>();

    for(Map.Entry<K,V> entry: entries){
        sortedMap.put(entry.getKey(), entry.getValue());
    }
       
    return sortedMap;
}

    
    
    public static Date getLocalDate(){
               
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date d=new Date();
                java.sql.Date date2 = new java.sql.Date(d.getTime());
                return date2;
       }
    


    
        
    public static Map<Integer, String> getcountrys() throws Exception{
        Map<Integer, String> countrylist = new HashMap<Integer, String>();
         
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT CODE,COUNTRY_CODE FROM E24OCM_COUNTRY  order by COUNTRY_NAME;";
            perSt = con.prepareStatement(sql);
            res = perSt.executeQuery();

            while (res.next()) {
               countrylist.put(res.getInt("CODE"), res.getString("COUNTRY_CODE"));
            }
            con.commit();
        } catch (Exception ex) {
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
        
        return countrylist;
    }
    


        public static String getOSLogPath(String logpath)throws Exception{

        String path = null;
        
        try{
            String linuxPath = logpath+"/";
            String removeFirstSlash = linuxPath.substring(linuxPath.indexOf("/")+1);  
            String removeToSecondSlash = removeFirstSlash.substring(removeFirstSlash.indexOf("/"));       
            String conForwordToBack = removeToSecondSlash.replace("/", "\\");
            
            if(System.getProperty("os.name").startsWith("Windows")){
                path = "C:"+conForwordToBack;
            }else if(System.getProperty("os.name").startsWith("Linux")){
                path = linuxPath;
             }
            
            
            
            
        } catch (Exception ex) {
            throw ex;
        }
        return path;    
    }
        

        
        

    
    public static String generateHash(String password)throws Exception{
    
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            return hashtext;
    }

    public static void setDBxmlPath() throws Exception {
        if (System.getProperty("os.name").startsWith("Windows")) {

            InitConfigValue.SCONFIGPATH = "C:\\inventory\\sconfig\\";
        } else if (System.getProperty("os.name").startsWith("Linux")) {

            InitConfigValue.SCONFIGPATH = "/opt/inventory/sconfig/";
        }

    }
    
    

         
    public static String convertHexToString(String hex){

	  StringBuilder sb = new StringBuilder();
	  StringBuilder temp = new StringBuilder();
          
	  for( int i=0; i<hex.length()-1; i+=2 ){  
	      String output = hex.substring(i, (i + 2));
	      int decimal = Integer.parseInt(output, 16);
	      sb.append((char)decimal);  
	      temp.append(decimal);
	  }
	  return sb.toString();
    }
    

    
}


