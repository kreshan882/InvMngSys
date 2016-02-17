/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inv.util;

/**
 *
 * @author tharaka
 */
public class SystemMessage {

   
    /////////////////////////////////////////////////////////////////////////
    //Common messages
    ////////////////////////////////////////////////////////////////////////
    
    public static final String COMMON_ERROR_PROCESS    = "Error occurred while processing";
    public static final String COMMON_ROOTCERT_EXPIRED = "Error root certificate Expired";

    
    /////////////////////////////////////////////////////////////////////////
    //Login managment
    ////////////////////////////////////////////////////////////////////////

    public static final String LOGIN_MSG            = "User login successful ";
    public static final String LOGOUT_MSG           = "User logout successful ";
    public static final String LOGIN_INVALID        = "Invalid user login";

    /////////////////////////////////////////////////////////////////////////
    //Customer management managment
    ////////////////////////////////////////////////////////////////////////
    public static final String CUS_NAME_EMPTY           = "Empty customer name";
    public static final String CUS_NAME_INVALID         = "Invalid customer name";
    public static final String CUS_NAME_ALREADY         = "Customer name already exists"; 
    public static final String CUS_ADDR_EMPTY           = "Empty customer address";
    public static final String CUS_ADDR_INVALID         = "Invalid customer address";
    public static final String CUS_EMAIL_EMPTY          = "Empty customer email";
    public static final String CUS_EAMIL_INVALID        = "Invalid customer email";
    public static final String CUS_TP_OFFI_EMPTY        = "Empty customer office number";
    public static final String CUS_TP_OFFI_INVALID      = "Invalid customer office number";
    public static final String CUS_TP_MOB_EMPTY         = "Empty customer mobile number";
    public static final String CUS_TP_MOB_INVALID       = "Invalid customer mobile number";
    public static final String CUS_STATUS_EMPTY         = "Please select status";

    
    public static final String CUS_ADD                  = "Customer add successful";
    public static final String CUS_ADD_FAIL             = "Customer add failed";
    
    public static final String CUS_UPDATE               = "Customer update successful";
    public static final String CUS_UPDATE_FAIL          = "Customer update failed";
    
    public static final String CUS_DELETE               = "Customer delete successful";
    public static final String CUS_DELETE_FAIL          = "Customer delete failed";
    
    /////////////////////////////////////////////////////////////////////////
    //Supplier management managment
    ////////////////////////////////////////////////////////////////////////
    public static final String SUPP_NAME_EMPTY           = "Empty supplier name";
    public static final String SUPP_NAME_INVALID         = "Invalid supplier name";
    public static final String SUPP_NAME_ALREADY         = "Supplier name already exists"; 
    public static final String SUPP_ADDR_EMPTY           = "Empty supplier address";
    public static final String SUPP_ADDR_INVALID         = "Invalid supplier address";
    public static final String SUPP_EMAIL_EMPTY          = "Empty supplier email";
    public static final String SUPP_EAMIL_INVALID        = "Invalid supplier email";
    public static final String SUPP_TP_OFFI_EMPTY        = "Empty supplier office number";
    public static final String SUPP_TP_OFFI_INVALID      = "Invalid supplier office number";
    public static final String SUPP_TP_MOB_EMPTY         = "Empty supplier mobile number";
    public static final String SUPP_TP_MOB_INVALID       = "Invalid supplier mobile number";
    public static final String SUPP_STATUS_EMPTY         = "Please select status";

    
    public static final String SUPP_ADD                  = "Supplier add successful";
    public static final String SUPP_ADD_FAIL             = "Supplier add failed";
    
    public static final String SUPP_UPDATE               = "Supplier update successful";
    public static final String SUPP_UPDATE_FAIL          = "Supplier update failed";
    
    public static final String SUPP_DELETE               = "Supplier delete successful";
    public static final String SUPP_DELETE_FAIL          = "Supplier delete failed";
    
    
    /////////////////////////////////////////////////////////////////////////
    // Items managment
    ////////////////////////////////////////////////////////////////////////
    public static final String ITEM_NUMBER_EMPTY           = "Empty barcode";
    public static final String ITEM_NUMBER_INVALID         = "Invalid barcode";
    public static final String ITEM_NUMBER_ALREADY         = "Barcode already exists"; 
    public static final String ITEM_ITEMTYPE_SELECT         = "Please select item type";
    
    public static final String ITEM_NAME_EMPTY             = "Empty item name";
    public static final String ITEM_NAME_INVALID           = "Invalid item name";
    
    public static final String ITEM_COLOR_EMPTY           = "Empty item colour";
    public static final String ITEM_COLOR_INVALID         = "Invalid item colour";
    
    public static final String ITEM_UNITPRIZE_EMPTY       = "Empty unit prize";
    public static final String ITEM_UNITPRIZE_INVALID     = "Invalid unit prize";
    public static final String ITEM_IMAGE_EMPTY           = "Empty image do file upload";
    public static final String ITEM_STATUS_EMPTY          = "Please select status";
     
    public static final String ITEM_ADD                  = "Item add successful";
    public static final String ITEM_ADD_FAIL             = "Item add failed";
    
    public static final String ITEM_UPDATE               = "Item update successful";
    public static final String ITEM_UPDATE_FAIL          = "Item update failed";
    
    public static final String ITEM_DELETE               = "Item delete successful";
    public static final String ITEM_DELETE_FAIL          = "Item delete failed";
    
    
     /////////////////////////////////////////////////////////////////////////
    // Sale managment
    ////////////////////////////////////////////////////////////////////////
    public static final String SALE_ITEM_NOTAVAL           = "Item qty not avaliavle";
    public static final String SALE_ITEM_ALREADT_ADD       = "Item already added";
    
    public static final String SALE_ADD                  = "Sale add successful";
    public static final String SALE_ADD_FAIL             = "Sale add failed";
    
    public static final String SALE_CANCLE                  = "Sale cancle successful";
    public static final String SALE_CANCLE_FAIL             = "Sale cancle failed";

     /////////////////////////////////////////////////////////////////////////
    // Sale managment
    ////////////////////////////////////////////////////////////////////////
    public static final String PURCH_ITEM_NOTAVAL           = "Item qty not avaliavle";
    public static final String PURCH_ITEM_ALREADT_ADD       = "Item already added";
    
    public static final String PURCH_ADD                  = "Purchase add successful";
    public static final String PURCH_ADD_FAIL             = "Purchanse add failed";
    
    public static final String PURCH_CANCLE               = "Purchase cancle successful";
    public static final String PURCH_CANCLE_FAIL          = "Purchanse cancle failed";
    
    public static final String USR_PW_CHG        = "User password change successful";
    public static final String USR_PW_UPDATE     = "User password update successful";
    public static final String USR_PW_NOT_MAT    = "User password not matched ";
    public static final String USR_PW_WORNG      = "Old password incorrect";
    public static final String USR_PW_WORNG_OLD  = "Old password Empty";
    public static final String USR_PW_POL_NOT_MAT = "User password does not comply with the password policy ";
    public static final String USR_PW_CNT_EQUAL_NWEPW    = "Can not use the same password ";
    
}
