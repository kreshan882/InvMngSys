package com.inv.util;

//package com.epic.util;
//
//import com.epic.log.LogFileCreator;
//import com.epic.configuration.bean.AddPasswordPolicyBean;
//import com.epic.configuration.service.AddPasswordPolicyService;
//import com.epic.init.AppType;
//import com.epic.login.bean.SessionUserBean;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
///**
// *
// * @author dushantha
// */
//public class PasswordValidator{
//
//    public synchronized String validatePassword(String password ) {
//
//
//        char characters[] = password.toCharArray();
//        
//        
//        
////        AddPasswordPolicyBean passPolcyBean = getPasswordPolicys();
//        
//       
//        if (null != passPolcyBean) {
//            
//
//            if (Integer.parseInt(passPolcyBean.getMinLegnth()) <= characters.length && Integer.parseInt(passPolcyBean.getMaxLegnth()) >= characters.length) {
//
//                if (isValidSpecialChars(characters, passPolcyBean)) {
//
//                    if (Integer.parseInt(passPolcyBean.getMinUpperCase()) <= numOfUpperCases(characters)) {
//
//                            if (Integer.parseInt(passPolcyBean.getMinNumerics().trim()) <= numOfNumerics(characters)) {
//                                
//                                return "Successful";
//                                
//                            } else {
//                                return "Invalid password : insert minimum " + passPolcyBean.getMinNumerics() + " mumeric values";
//                            }
//                        
//                    } else {
//                        return "Invalid password : insert minimum " + passPolcyBean.getMinUpperCase() + " uppercase values";
//                    }
//
//                } else {
//                    return "Invalid password : insert special character count between ( " + passPolcyBean.getMinSpecialCharacters() + " , " + passPolcyBean.getMaxSpecialCharacters() + " ) : Allowed characters ( " + passPolcyBean.getAllowSpecialChars() + " ) ";
//                }
//
//            } else {
//                return "Invalid password legnth. Please Insert " + passPolcyBean.getMinLegnth() + " to " + passPolcyBean.getMaxLegnth() + " values.";
//            }
//        } else {
//            return "Error please create password policy first";
//        }
//    }
//
////    public synchronized String validatePassword(String password , SessionUserBean sub) {
////
////
////        char characters[] = password.toCharArray();
////        
////        
////        
////        AddPasswordPolicyBean passPolcyBean = getPasswordPolicys();
////        
////       if(!AppType.IAM.equals(sub.getApptype())){
////
////           if (null != passPolcyBean) {
////            
////
////            if (Integer.parseInt(passPolcyBean.getMinLegnth()) <= characters.length && Integer.parseInt(passPolcyBean.getMaxLegnth()) >= characters.length) {
////
////                if (isValidSpecialChars(characters, passPolcyBean)) {
////
////                    if (Integer.parseInt(passPolcyBean.getMinUpperCase()) <= numOfUpperCases(characters)) {
////
////                        
////
////                            if (Integer.parseInt(passPolcyBean.getMinNumerics().trim()) <= numOfNumerics(characters)) {
////                                
////                                return "Successful";
////                                
////                            } else {
////                                return "Invalid password : insert minimum " + passPolcyBean.getMinNumerics() + " mumeric values";
////                            }
////                        
////                    } else {
////                        return "Invalid password : insert minimum " + passPolcyBean.getMinUpperCase() + " uppercase values";
////                    }
////
////                } else {
////                    return "Invalid password : insert special character count between ( " + passPolcyBean.getMinSpecialCharacters() + " , " + passPolcyBean.getMaxSpecialCharacters() + " ) : Allowed characters ( " + passPolcyBean.getAllowSpecialChars() + " ) ";
////                }
////
////            } else {
////                return "Invalid password legnth. Please Insert " + passPolcyBean.getMinLegnth() + " to " + passPolcyBean.getMaxLegnth() + " values.";
////            }
////        } else {
////            return "Error please create password policy first";
////        }
////       
////       }else{
////             return "Successful";
////       }
////        
////        
////    }
//    
////    private AddPasswordPolicyBean getPasswordPolicys() {
////        AddPasswordPolicyBean pass = null;
////        try {
////
////           pass = new AddPasswordPolicyService().getPasswordPolicyData();
////
////        } catch (Exception e) {
////
////            try {
////                LogFileCreator.writeErrorToLog(e);
////            } catch (Exception ex) {
////                Logger.getLogger(PasswordValidator.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////        return pass;
////    }
//
//    private int numOfUpperCases(char[] charArray) {
//        int numbers = 0;
//        for (int i = 0; i < charArray.length; i++) {
//            if (Character.isUpperCase(charArray[i])) {
//                numbers++;
//            }
//        }
//        return numbers;
//    }
//
//    private int numOflowerCases(char[] charArray) {
//        int numbers = 0;
//        for (int i = 0; i < charArray.length; i++) {
//            if (Character.isLowerCase(charArray[i])) {
//                numbers++;
//            }
//        }
//        return numbers;
//    }
//
//    private int numOfNumerics(char[] charArray) {
//        int numbers = 0;
//        for (int i = 0; i < charArray.length; i++) {
//            if (String.valueOf(charArray[i]).matches("[0-9]")) {
//                numbers++;
//            }
//        }
//        return numbers;
//    }
//
////    private boolean isValidSpecialChars(char[] charArray, AddPasswordPolicyBean passpolBean) {
////
////        int numbers = 0, numOfDoler = 0, counter = 0;
////        String validVal = "", secondValidator = "$";
////        char chars[] = passpolBean.getAllowSpecialChars().toCharArray();
////        numOfDoler = passpolBean.getAllowSpecialChars().compareTo("$");
////
////        for (int i = 0; i < chars.length; i++) {
////            if (!"$".equals(String.valueOf(chars[i]))) {
////
////                if ((counter + 1) != (chars.length - numOfDoler)) {
////                    validVal += String.valueOf(chars[i]) + "|";
////
////                } else {
////                    validVal += String.valueOf(chars[i]);
////
////                }
////                counter++;
////
////            }
////
////        }
////
////
////        for (int i = 0; i < charArray.length; i++) {
////            if (String.valueOf(charArray[i]).matches(validVal)) {
////                numbers++;
////            } else if (String.valueOf(charArray[i]).matches("[" + secondValidator + "]")) {
////                numbers++;
////            }
////        }
////
////        if (numbers >= Integer.parseInt(passpolBean.getMinSpecialCharacters()) && numbers <=Integer.parseInt(passpolBean.getMaxSpecialCharacters())) {
////            return true;
////        } else {
////            return false;
////        }
////    }
//
//
// 
//}
