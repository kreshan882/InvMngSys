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
    
    public static final String USR_PW_CHG        = "User password change successful";
    public static final String USR_PW_UPDATE     = "User password update successful";
    public static final String USR_PW_NOT_MAT    = "User password not matched ";
    public static final String USR_PW_WORNG      = "Old password incorrect";
    public static final String USR_PW_WORNG_OLD  = "Old password Empty";
    public static final String USR_PW_POL_NOT_MAT = "User password does not comply with the password policy ";
    public static final String USR_PW_CNT_EQUAL_NWEPW    = "Can not use the same password ";
    /////////////////////////////////////////////////////////////////////////
    //User Profile management
    ////////////////////////////////////////////////////////////////////////

    public static final String USRP_GRP_ADD           = "User group successfully added";
    public static final String USRP_GRP_UPDATED       = "User group successfully updated";
    public static final String USRP_GRP_UPDATED_FAIL  = "User group update failed";
    public static final String USRP_GRP_DELETE_FAIL   = "User group delete failed";
    public static final String USRP_GRP_EMPTY         = "Empty user group name";
    public static final String USRP_GRP_INVALID       = "Invalid user group name";
    public static final String USRP_GRP_ALREADY       = "User group name already exist";
    public static final String USRP_GRP_ADD_ERROR     = "Error adding user group";
    public static final String USRP_GRP_APPTYPE_EMPTY = "Please select application type";
    public static final String USRP_GRP_APPMOD_EMPTY  = "Please select application module";
    public static final String USRP_GRP_DELETE        = "User group successfully deleted";
    
    
    
    
    public static final String USRP_ROLE_ADD           = "User role successfully added";
    public static final String USRP_ROLE_ADD_FAIL      = "User role add failed";
    public static final String USRP_ROLE_UPDATED       = "User role successfully updated";
    public static final String USRP_ROLE_UPDATED_FAIL  = "User role update failed";
    public static final String USRP_ROLE_EMPTY         = "Empty user profile name";
    public static final String USRP_ROLE_INVALID       = "Invalid user profile name";
    public static final String USRP_ROLE_INVALID_DESC  = "Invalid user profile description";
    public static final String USRP_ROLE_NOT_SELECT_PAGES  = "Please select pages";
    public static final String USRP_ROLE_ALREADY       = "User profile name already exist";
    public static final String USRP_ROLE_SEL_APPTYPE   = "Please select application type";
    public static final String USRP_ROLE_SEL_APPMODUL  = "Please select application module";
    public static final String USRP_ROLE_DELETED       = "User role successfully deleted";
    public static final String USRP_ROLE_DELETED_FAIL  = "User role delete failed";
    public static final String USRP_ROLE_INVALID_SEARCH  = "User name invalid";
    public static final String USRP_ROLE_ERROR_UNHANDLE  = "Error unhandled";
    public static final String USRP_ROLE_SEL_PAGES   = "Please select pages";
    
    public static final String INS_EXIT_INSTANCE    = "Instance already inserted";
    public static final String INS_INVALID_INSTANCE = "Invalid instance  name";
    public static final String INS_EMPTY_INSTANCE   = "Empty instance name";
    public static final String INS_EMPTY_APP_TYPE   = "Select app type";
    public static final String INS_EMPTY_MAX_USR    = "Empty max user";
    public static final String INS_INVALDD_MAX_USR  = "Invalid max user";
    public static final String INS_EMPTY_MAX_REQ    = "Empty max Request";
    public static final String INS_INVALDD_MAX_REQ  = "Invalid max Request";
    public static final String INS_EMPTY_MAX_REJ    = "Empty max Rejection";
    public static final String INS_INVALDD_MAX_REJ  = "Invalid max Rejection";
    public static final String INS_EMPTY_MAX_APR    = "Empty max Approval";
    public static final String INS_INVALDD_MAX_APR  = "Invalid max Approval";
    public static final String INS_EMPTY_SEND_EMAIL = "Select email state";
    public static final String INS_EMPTY_STATUS     = "Select status";
    public static final String INS_INVAILD_OPP_HOURS = "Invalid operation hours";
   
    ///////////////////////////////////////////////////////////////////////////
    //Institute management
    ///////////////////////////////////////////////////////////////////////////

    public static final String INS_ADD                  = "Institute successfully registered.";
    public static final String INS_UPDATE               = "Institute successfully updated";
    public static final String INS_ADD_CONFIGPROF       = "Configuration profile successfully added";
    public static final String INS_UPDATED_CONFIGPROF   = "Configuration profile successfully updated";
    public static final String INS_DELETE               = "Institute successfully deleted";
    public static final String INS_ERROR                = "Error unhandled";
    
    public static final String INS_EMPTY_INSTITUTE      = "Empty institute name";
    public static final String INS_EMPTY_ADDRESS        = "Empty address";
    public static final String INS_EMPTY_PHONE          = "Empty telephone number";
    public static final String INS_EMPTY_EMAIL          = "Empty e-mail";
    public static final String INS_EMPTY_CONF_PROFILE   = "Select configuration profile";
    
    public static final String INS_INVALID_INSTITIUTE   = "Invalid institute name";
    public static final String INS_INVALID_ADDRESS      = "Invalid address";
    public static final String INS_INVALID_PHONE        = "Invalid telephone number";
    public static final String INS_INVALID_EMAIL        = "Invalid e-mail";
    public static final String INS_SELECT_ERROR         = "Please select status";
    public static final String INS_SELECT_ERROR_CONF    = "Please select configuration";
    
    public static final String INS_ALREADY_EXIT         = "Institute already inserted";
    
    public static final String INS_DELETE_ERROR         = "Error deleting institute profile";//added by romesh
    public static final String INS_UPDATE_ERROR         = "Error updating institute profile";//added by romesh
    public static final String INS_PRO_DELETED_SUCCESS  = "User successfully deleted ";//added by romesh
    public static final String INS_FIND_ERROR           = "Cannot find institute ";//added by romesh
    
    public static final String INS_CON_PRO_NAME_EMTY    = "Empty institute profile name ";
    public static final String INS_CON_PRO_NAME_INVAILD = "Invalid institute profile name ";
    public static final String INS_CON_ALREADY_EXIT     = "Institute profile name already exists";
    public static final String INS_CON_CA_EMTY          = "No of CA cannot be empty";
    public static final String INS_CON_CA_INVALID       = "Invalid No of CA ";
    public static final String INS_CON_RA_EMTY          = "No of RA cannot be empty";
    public static final String INS_CON_RA_INVALID       = "Invalid No of RA ";
    public static final String INS_CON_OCSP_EMTY        = "No of OCSP cannot be empty";
    public static final String INS_CON_OCSP_INVALID     = "Invalid No of OCSP ";
    public static final String INS_CON_TPU_EMTY         = "No of TPU cannot be empty";
    public static final String INS_CON_TPU_INVALID      = "Invalid No of TPU ";
    public static final String INS_CON_DRM_EMTY         = "No of DRM cannot be empty";
    public static final String INS_CON_DRM_INVALID      = "Invalid No of DRM ";
    
    
//    //////////////////////////////////////////////////////////////////////////////
//    //Request management
//    /////////////////////////////////////////////////////////////////////////////
//
    public static final String REQ_CSRUPLOAD_SUCCESS        = "CSR uploaded successfully";
    public static final String REQ_CSRUPLOAD_FAIL           = "CSR uploaded fail";
    
    public static final String REQ_CSR_CREATE_FAIL          = "CSR creating fail";
    public static final String REQ_CSR_CREATE_SUCCESS       = "CSR created successfully";

    public static final String REQ_DOWNLOADEDCRL            = "CRL downloaded";
    public static final String REQ_DOWNLOADEDCERT           = "Certificate downloaded";
    public static final String REQ_CSR_EXTENTION            = "Choose an CSR file";
    public static final String REQ_CSR_EMTY                 = "Empty CSR file";
    public static final String REQ_CSR_DATA_EMTY            = "Empty CSR data";
    public static final String REQ_INVAILD_DESCRIPION       = "Invalid description";
    
    public static final String REQ_VRE_RA_STATUS_EMPTY        = "Please select status";
    public static final String REQ_VRE_RA_INVALID_SERIAL      = "Please insert valid serial number or part of serial Number";
    public static final String REQ_VRE_RA_LOAD_ERROR          = "Error loading data";
    public static final String REQ_VRE_RA_LOAD_HISTORY_ERROR  = "Error loading history data";
    
    

    public static final String REQUEST_RE_RA_APPROVED           = "Successfully approved";
    public static final String REQUEST_RE_RA_REJECT             = "Successfully rejected";
    public static final String REQUEST_RE_RA_REMOVE             = "Successfully removed";
    public static final String REQUEST_RE_RA_APPROVED_ERROR     = "Error in approving";
    public static final String REQUEST_RE_RA_REJECT_ERROR       = "Error in rejecting";
    public static final String REQUEST_RE_RA_REMOVE_ERROR       = "Error in removing";
    

    public static String REQUEST_TPU_FILE_DOWNLADED            = "TPU details downloaded successfully";
    public static final String REQ_VRE_TPU_STATUS_EMPTY        = "Please select status";
    public static final String REQ_VRE_TPU_INVALID_SERIAL      = "Please insert valid serial number or part of serial number";
    public static final String REQ_VRE_TPU_LOAD_ERROR          = "Error loading data";
    
    public static final String REQUEST_RE_TPU_APPROVED           = "Approved Successfully";
    public static final String REQUEST_RE_TPU_REJECT             = "Rejected Successfully";
    public static final String REQUEST_RE_TPU_REMOVE             = "Removed Successfully";
    public static final String REQUEST_RE_TPU_APPROVED_ERROR     = "Error in approving";
    public static final String REQUEST_RE_TPU_REJECT_ERROR       = "Error in rejecting";
    public static final String REQUEST_RE_TPU_REMOVE_ERROR       = "Error in removing";
    
    public static final String REQ_VRE_CA_APPTYPE_EMPTY        ="Please select Application Type";
    public static final String REQ_VRE_CA_APPMODULE_EMPTY      ="Please insert Application Module";
    public static final String REQ_VRE_CA_DATERANG_EMPTY       ="Please select Date Range";
    public static final String REQ_VRE_CA_STATUS_EMPTY         ="Please select Status";
    public static final String REQ_VRE_CA_INVALID_SERIAL       ="Invalid serial Number";
    
    public static final String REQUEST_RE_CA_APPROVED           = "Approved By CA Successfully";
    public static final String REQUEST_RE_CA_REJECT             = "Rejected By CA Successfully";
    public static final String REQUEST_RE_CA_REVOKE             = "Revoked By CA Successfully";
    public static final String REQUEST_RE_CA_APPROVED_ERROR     = "Error in Approved By CA ";
    public static final String REQUEST_RE_CA_REJECT_ERROR       = "Error in Rejected By CA ";
    public static final String REQUEST_RE_CA_REVOKE_ERROR       = "Error in Revoked By CA";
    
    
    public static final String REQUEST_RE_DRM_APPROVED           = "Approved Successfully";
    public static final String REQUEST_RE_DRM_REJECT             = "Rejected Successfully";
    public static final String REQUEST_RE_DRM_REMOVE             = "Removed Successfully";
    public static final String REQUEST_RE_DRM_APPROVED_ERROR     = "Error in approving";
    public static final String REQUEST_RE_DRM_REJECT_ERROR       = "Error in rejecting";
    public static final String REQUEST_RE_DRM_REMOVE_ERROR       = "Error in removing";
    
    public static final String REQUEST_CRL_DOWNLOADED           = "CRL file downloaded";
    public static final String REQUEST_FILE_NOT_FOUND           = "File not found to download";
    
    public static final String REQ_VRE_OCSP_STATUS_EMPTY        ="Please select Status";
    public static final String REQ_VRE_OCSP_INVALID_SERIAL      ="Please insert valid Serial Number or part of Serial Number";
  
    public static final String REQUEST_OCSP_EMPTY_SRILNO        = "Empty Serial Number";
    public static final String REQUEST_OCSP_INVALID_SRILNO      = "Invalid Serial Number";
    public static final String REQUEST_OCSP_RESPONDER_SUCESS    = "OCSP Response successful";
    public static final String REQUEST_OCSP_WRONG_SRILNO        = "CRL not exists";
    ///////////////////////////////////////////////////////////////////////////////
    //Log file management
    ///////////////////////////////////////////////////////////////////////////////

    public static final String LOG_INFORUPDATE       = "Log file information updated successfully.";
    public static final String LOG_DELETED_SUCCESS   = "Log file deleted successfully";
    public static final String LOG_DOWNLOADED        = "Log file downloaded";

    /////////////////////////////////////////////////////////////////////////////////
    //Configuration management
    /////////////////////////////////////////////////////////////////////////////////

    public static final String CONF_PASSWDPOLICYUPDATED  = "Password policy updated successfully ";
    public static final String CONF_SYSBACKUPSTARTED     = "System backup started";
    public static final String CONF_SYSRESTORESTARTED    = "System restore started";
    public static final String CONF_HSMTOKENPINUPDATED   = "HSM token PIN updated successfully";
    
    
    public static final String CONF_HSM_PIN_EMTY   = "Empty PIN";
    public static final String CONF_HSM_PIN_ADD    = "PIN added successfully";
    public static final String CONF_PIN_INVALID    = "Invalid PIN";
    
    public static final String CONF_PRO_NAME_EMTY        = "Empty configuration profile name";
    public static final String CONF_PRO_NAME_INVALID     = "Invalid configuration profile name";
    public static final String CONF_LOG_PATH_EMTY        = "Empty log path";
    public static final String CONF_LOG_PATH_INVALID     = "Invalid log path";
    public static final String CONF_LOG_LEVEL_EMTY       = "Please select log level";
    public static final String CONF_LOG_ADDED            = "Log configuration added successfully";
    public static final String CONF_PRO_NAME_ALREADY_EXIT= "Configuration profile name already exits";
    public static final String CONF_ERROR                = "Error unhandled";
    
    public static final String CONF_UPDATE_SUCCESS      = "Configuration profile updated successfully";
    public static final String CONF_UPDATE_ERROR        = "Error in update Configuration profile";
    public static final String CONF_EMPTY_MAX_USR       = "Empty max user";
    public static final String CONF_INVALDD_MAX_USR     = "Invalid max user";
    public static final String CONF_EMPTY_MAX_REQ       = "Empty max Request";
    public static final String CONF_INVALDD_MAX_REQ     = "Invalid max Request";
    public static final String CONF_EMPTY_MAX_REJ       = "Empty max Rejection";
    public static final String CONF_INVALDD_MAX_REJ     = "Invalid max Rejection";
    public static final String CONF_EMPTY_MAX_APR       = "Empty max Approval";
    public static final String CONF_INVALDD_MAX_APR     = "Invalid max Approval";
    public static final String CONF_EMPTY_SEND_EMAIL    = "Select email state";
    public static final String CONF_EMPTY_STATUS        = "Select status";
        //sub catagory Password Policy
    
    //Edit & View Ocsp conf
    
    public static final String CONF_EV_OCSP_LOAD_ERROR          = "Error Loading Details";
    public static final String CONF_EV_OCSP_UPDATE_SUCCESS      = "Configuration profile updated successfully";
    public static final String CONF_EV_OCSP_UPDATE_ERROR        = "Error in update Configuration profile";
    public static final String CONF_EV_OCSP_INVALID_NAME        = "Configuration Profile Name invalid";
    public static final String CONF_EV_OCSP_ALREADY_NAME        = "Configuration Profile Name already exists in the institute";
    public static final String CONF_EV_OCSP_INVALID_MAX_USR     = "Invalid max users";
    public static final String CONF_EV_OCSP_INVALID_MAX_REQ     = "Invalid max Request";
    public static final String CONF_EV_OCSP_INVALID_MAX_REJ     = "Invalid max Rejection";
    public static final String CONF_EV_OCSP_INVALDD_MAX_APR     = "Invalid max Approval";
    
    /////////////////////////////////////////////////////////////////////////////////
    //ISA USER MANAGEMENT
    /////////////////////////////////////////////////////////////////////////////////

    public static final String USR_INVALID_INSTITIUTE      = "Select institute";
    public static final String USR_INVALID_USERNAME        = "Invalid user name";
    public static final String USR_INVALID_USERPASSWORD    = "Invalid user password";
    public static final String USR_INVALID_EMAIL           = "Invalid email";
    public static final String USR_INVALID_PHONE           = "Invalid contact number ";
    public static final String USR_INVALID_NIC             = "Invalid NIC  ";
    public static final String USR_INVALID_LOCATION        = "Invaid location ";
    public static final String USR_INVALID_COMPANY         = "Invalid company ";
    public static final String USR_INVALID_CATAGORY        = "Select user category ";
    public static final String USR_INVALID_PROFILE         = "Select user profile ";
    public static final String USR_INVALID_APPTYPE         = "Select application type ";
    public static final String USR_INVALID_MODULE          = "Select application module";
    public static final String USR_EMPTY_STATUS            = "Select status";
    public static final String USR_ERROR_USERNAME          = "User already exits ";
    
    
    public static final String USR_EMPTY_USERNAME          = "Empty user name";
    public static final String USR_EMPTY_USERPASSWORD      = "Empty user password";
    public static final String USR_EMPTY_CONPASSWORD       = "Empty confirm user password";
    public static final String USR_PASSWORD_NOT_MATCH      = "Password not matched";
    public static final String USR_EMPTY_EMAIL             = "Empty e-mail";
    public static final String USR_EMPTY_PHONE             = "Empty contact number ";
    public static final String USR_EMPTY_COMPANY           = "Empty company ";
    public static final String USR_EMPTY_GENDER            = "Select gender ";
    //Added by supun
    public static final String USR_ERROR_UNHANDLE          = "Exception occurred ";
  
    
     /////////////////////////////////////////////////////////////////////////////////
    //Password Policy
    /////////////////////////////////////////////////////////////////////////////////
    
    public static final String PPOLICY_ADD                    = "Password policy inserted successfully ";
    public static final String PPOLICY_UPDATE                 = "Password policy updated successfully";
    
    public static final String PPOLICY_INVALID_MODULE         = "Invalid module";
    public static final String PPOLICY_INVALID_MINLENGTH      = "Invalid min-length";
    public static final String PPOLICY_INVALID_MAXLENGTH      = "Invalid max-length";
    public static final String PPOLICY_INVALID_SCARACTERS     = "Invalid special-characters";
    public static final String PPOLICY_INVALID_MINSCARACTERS  = "Invalid min-special characters";
    public static final String PPOLICY_INVALID_MAXSCARACTERS  = "Invalid max-special characters";
    public static final String PPOLICY_INVALID_MINUPPERCASE   = "Invalid min-uppercase characters";
    public static final String PPOLICY_INVALID_MINNUMERICS    = "Invalid min-numeric characters";
     
    
    
    public static final String PPOLICY__EMPTY_MINLENGTH       = "Empty min-length";
    public static final String PPOLICY__EMPTY_MAXLENGTH       = "Empty max-length";
    public static final String PPOLICY__EMPTY_SCARACTERS      = "Empty special-characters";
    public static final String PPOLICY__EMPTY_MINSCARACTERS   = "Empty min-special characters";
    public static final String PPOLICY__EMPTY_MAXSCARACTERS   = "Empty max-special-characters";
    public static final String PPOLICY__EMPTY_MINUPPERCASE    = "Empty min-uppercase characters";
    public static final String PPOLICY__EMPTY_MINNUMERICS     = "Empty min-numeric characters";
    
    public static final String PPOLICY_ERROR_UNHANDLE         = "Exception occurred";
    public static final String PPOLICY_ERROR_EXIT             = "Policy already exits ";
    
    public static final String PPOLICY_ERROR_NOTEXIT          = "Create password policy first";
    
    
    public static final String CONF_EMTY_BACKUP          = "Empty Backup File Name" ;
    public static final String CONF_EMTY_RESTORE_PATH    = "Empty Restore Path";
    public static final String CONF_INVAILD_BACKUP       = "Invalid Backup File Name" ;
    public static final String CONF_INVAILD_RESTORE_PATH = "Invalid Restore Path" ;
    public static final String CONF_RESTORE_SUCCESS       = "Restore successful" ;
    public static final String CONF_RESTORE_ERROR         = "Error in restoring" ;
    
    /////////////////////////////////////////////////////////////////////////////////
    //Instance MANAGEMENT
    /////////////////////////////////////////////////////////////////////////////////

   ///INSTANCE_OCSP ADD
    public static final String INSTANCE_OCSP_ADD                      = " Successfully registered ";
    public static final String INSTANCE_OCSP_UPDATE                   = " Successfully updated ";
    public static final String INSTANCE_OCSP_DELETED                  = " OCSP deleted successfully  ";
    
    public static final String INSTANCE_OCSP_EMPTY_NAME               = "Empty name";
    public static final String INSTANCE_OCSP_INVALID_NAME             = "Invalid name";
    public static final String INSTANCE_OCSP_INVALID_DESCRIPTION      = "Invalid description";
    public static final String INSTANCE_OCSP_EMPTY_PORT               = "Empty port";
    public static final String INSTANCE_OCSP_INVALID_PORT             = "Invalid port";
    public static final String INSTANCE_OCSP_EMPTY_URL                = "Empty URL";
    public static final String INSTANCE_OCSP_INVALID_URL              = "Invalid port";
    public static final String INSTANCE_OCSP_INVALID_CONFID           = "Invalid configuration profile ";
    public static final String INSTANCE_OCSP_INVALID_TARGETCA         = "Invalid target CA";
    public static final String INSTANCE_OCSP_ERROR_NAME               = "OCSP already exits ";
    public static final String INSTANCE_OCSP_MAX_OCSP_LIMIT           = "Not allowed to create more OCSP instances";
    
    
    ///RA ADD
    public static final String INSTANCE_RA_EMPTY_NAME               = "Empty name";
    public static final String INSTANCE_RA_INVALID_NAME             = "Invalid  name";
    public static final String INSTANCE_RA_INVALID_DESCRIPTION      = "Invalid description";
    public static final String INSTANCE_RA_EMPTY_COM_NAME           = "Empty company name";
    public static final String INSTANCE_RA_INVALID_COM_NAME         = "Invalid company name";
    public static final String INSTANCE_RA_INVALID_EMAIL            = "Invalid email name";
    public static final String INSTANCE_RA_INVALID_MOB_NO           = "Invalid mobile number";
    public static final String INSTANCE_RA_INVALID_DEPT             = "Invalid department name";
    public static final String INSTANCE_RA_EMPTY_PORT               = "Empty port";
    public static final String INSTANCE_RA_INVALID_PORT             = "Invalid port";
    public static final String INSTANCE_RA_EMPTY_COUNTRY            = "Empty country";
    public static final String INSTANCE_RA_EMPTY_CON_PRO            = "Empty configuration profile";
    public static final String INSTANCE_RA_EMPTY_TARGET_CA          = "Empty target CA";
    public static final String INSTANCE_RA_ADD_NEW                  = "Added successfully";
    public static final String INSTANCE_RA_ALREADY_EXIT             = "Name already inserted";
    public static final String INSTANCE_RA_MAX_RA_LIMIT             = "Not allowed to create more RA instances";
    
    
 
    //DRM Add
    public static final String INSTANCE_DRM_EMPTY_NAME               = "Empty name";
    public static final String INSTANCE_DRM_INVALID_NAME             = "Invalid name";
    public static final String INSTANCE_DRM_ALREADY_EXIT             = "Name already inserted";
    public static final String INSTANCE_DRM_INVALID_DESCRIPTION      = "Invalid description";
    public static final String INSTANCE_DRM_EMPTY_CON_PRO            = "Empty configuration profile";
    public static final String INSTANCE_DRM_ADD                      = "Successfully registered ";
    public static final String INSTANCE_DRM_INVALID_STATUS           = "Invalid status";
    public static final String INSTANCE_DRM_MAX_DRM_LIMIT            = "Not allowed to create more DRM instances";
    
    
    
    //TPU Add
    
    public static final String INSTANCE_TPU_ADD                      = " Successfully registered ";
    
    public static final String INSTANCE_TPU_EMPTY_NAME               = "Empty name ";
    public static final String INSTANCE_TPU_INVALID_NAME             = "Invalid name";
    public static final String INSTANCE_TPU_INVALID_DESCRIPTION      = "Invalid description";
    public static final String INSTANCE_TPU_INVALID_CONFID           = "Invalid configuration profile ";
    public static final String INSTANCE_TPU_INVALID_TARGETCA         = "Invalid target CA";
    public static final String INSTANCE_TPU_ERROR_NAME               = "TPU already exits ";
    public static final String INSTANCE_TPU_MAX_TPU_LIMIT            = "Not allowed to create more TPU instances";
    

     //CA ADD
    public static final String INSTANCE_CA_ADD_SUCESS               = "CA added Successfully";
    public static final String INSTANCE_CA_ADD_FAIL                 = "Adding failed";
    public static final String INSTANCE_CA_CERT_LOAD_FAIL           = "Certificate load failed";
    public static final String INSTANCE_CA_EMPTY_NAME               = "Empty name";
    public static final String INSTANCE_CA_INVALID_NAME             = "Invalid  name";
    public static final String INSTANCE_CA_ALRADY_NAME              = "CA name already exists";
    public static final String INSTANCE_CA_INVALID_DESCRIPTION      = "Invalid description";
    public static final String INSTANCE_CA_EMPTY_COMPANY_NAME       = "Empty company name";
    public static final String INSTANCE_CA_INVALID_COMPANY_NAME     = "Invalid company name";
    public static final String INSTANCE_CA_INVALID_EMAIL            = "Invalid email";
    public static final String INSTANCE_CA_INVALID_MOBILE_NO        = "Invalid phone number";
    public static final String INSTANCE_CA_EMPTY_COMMON_NAME        = "Empty common name";
    public static final String INSTANCE_CA_INVALID_COMMON_NAME      = "Invalid common name";
    public static final String INSTANCE_CA_EMPTY_PK_PASSWD          = "Empty private key password";
    public static final String INSTANCE_CA_INVALID_PK_PASSWD        = "Invalid private key password";
    public static final String INSTANCE_CA_EMPTY_ORG_UNIT           = "Empty organization unit";
    public static final String INSTANCE_CA_INVALID_ORG_UNIT         = "Invalid organization unit";
    public static final String INSTANCE_CA_EMPTY_VALID_DATE         = "Empty date";
    public static final String INSTANCE_CA_INVALID_VALID_DATE       = "Invalid date";
    public static final String INSTANCE_CA_SELECT_COUNTRY           = "Select country";
    public static final String INSTANCE_CA_SELECT_KEYSIZE           = "Select key size";
    public static final String INSTANCE_CA_SELECT_HASHALGO          = "Select hash algorithm";
    public static final String INSTANCE_CA_SELECT_APPCONF           = "Select application configuration";
    public static final String INSTANCE_CA_MAX_CA_LIMIT             = "Not allowed to create more CA instances";
    
    //CONFIGURATION 
    public static final String CONFIG_DEL_ERROR            = "Error in deleting";
    
    //CA Edit And View
    public static final String INSTANCE_CA_EV_LOAD_FAIL             = "Data load failed";
    public static final String INSTANCE_CA_EV_UPDATED               = "CA updated successfully";
    public static final String INSTANCE_CA_EV_UPDATED_ERROR         = "CA update Error";
    public static final String INSTANCE_CA_EV_DELETE                = "CA deleted successfully";
    public static final String INSTANCE_CA_EV_DELETE_ERROR          = "CA delete Error";  
    public static final String INSTANCE_CA_EV_EMPTY_NAME            = "CA Name is Empty";
    public static final String INSTANCE_CA_EV_INVALID_NAME          = "CA Name Incorrect";
    public static final String INSTANCE_CA_EV_ALRADY_NAME           = "CA Name already exist in this Institute";
    public static final String INSTANCE_CA_EV_INVALID_DESCRIPTION   = "CA Description Incorrect";
    
    //DRM Edit And View
    public static final String INSTANCE_DRM_EV_LOAD_FAIL                 = "Data load failed";
    public static final String INSTANCE_DRM_EV_UPDATED                   = "DRM updated successfully";
    public static final String INSTANCE_DRM_EV_UPDATED_ERROR             = "DRM updated error";
    public static final String INSTANCE_DRM_EV_DELETE                    = "DRM deleted successfully";
    public static final String INSTANCE_DRM_EV_DELETE_ERROR              = "DRM delete Error";

     //TPU Edit And View
    public static final String INSTANCE_TPU_EV_LOAD_FAIL                 = "Data load failed";
    public static final String INSTANCE_TPU_EV_UPDATED                   = "TPU updated successfully";
    public static final String INSTANCE_TPU_EV_UPDATED_ERROR             = "TPU update Error";
    public static final String INSTANCE_TPU_EV_DELETE                    = "TPU deleted successfully";
    public static final String INSTANCE_TPU_EV_DELETE_ERROR              = "TPU delete Error";

     //RA Edit And View
    public static final String INSTANCE_RA_EV_LOAD_FAIL                 = "Data load failed";
    public static final String INSTANCE_RA_EV_UPDATED                   = "RA updated successfully";
    public static final String INSTANCE_RA_EV_UPDATED_ERROR             = "RA update Error";
    public static final String INSTANCE_RA_EV_DELETE                    = "RA deleted successfully";
    public static final String INSTANCE_RA_EV_DELETE_ERROR              = "RA delete Error";
    public static final String INSTANCE_RA_EV_EMTY_STATUS               = "Empty status";
   
    
    
    /////////////////////////////////////////////////////////////////////////
    //CA Management
    ////////////////////////////////////////////////////////////////////////

    public static final String CRL_ADD                   = " Successfully created CRL  ";
    public static final String CRL_ERROR_CREATE          = "Create CRL failed ";
    public static final String CRL_EMPTY_FILENAME        = "Empty file name";
    public static final String CRL_INVALID_FILENAME      = "Invalid file name";
    public static final String CRL_INVALID_DESCRIPTION   = "Invalid file description";
    public static final String CRL_EMPTY_FROMDATE        = "Empty from date";
    public static final String CRL_EMPTY_TODATE          = "Empty to date";
    public static final String CRL_ERROR_FILENAME        = "File already exits ";
    
    public static final String CRL_UPDATE                = " Successfully updated ";

     /////////////////////////////////////////////////////////////////////////
    //dash Board
    ////////////////////////////////////////////////////////////////////////
    
    public static final String DASH_APPROVED_BY_CA                   = " Approved by CA  ";
    public static final String DASH_REJECT_BY_CA                     = " Rejected by CA ";
    public static final String DASH_APPROVED_BY_RA                   = " Approved by RA  ";
    public static final String DASH_REJECT_BY_RA                     = " Rejected by RA ";
    public static final String DASH_APPROVED_BY_TPU                  = " Approved by TPU  ";
    public static final String DASH_REJECT_BY_TPU                    = " Rejected by TPU ";
    public static final String DASH_OCSP_STATUS_GOOD                 = " OCSP Status Good ";
    public static final String DASH_OCSP_STATUS_BAD                  = " OCSP Status Bad  ";
    public static final String DASH_OCSP_STATUS_UNKNOWN              = " OCSP Status Unknown ";
    
     /////////////////////////////////////////////////////////////////////////
    //Token Prosesing Unit
    ////////////////////////////////////////////////////////////////////////
    
    public static final String TPU_EMPTY_TOKEN_SERIAL_NUMBER        = "Empty token serial number";
    public static final String TPU_INVALID_TOKEN_SERIAL_NUMBER      = "Invalid token serial number";
    public static final String TPU_EMPTY_NAME                       = "Empty name";
    public static final String TPU_INVALID_NAME                     = "Invalid  name";
    public static final String TPU_NAME_EXIT                        = "Name already inserted";
    public static final String TPU_INVALID_EMAIL                    = "Invalid email";
    public static final String TPU_EMPTY_COMPANY_NAME               = "Empty company name";
    public static final String TPU_INVALID_COMPANY_NAME             = "Invalid company name";
    public static final String TPU_EMPTY_COMMON_NAME                = "Empty common name";
    public static final String TPU_INVALID_COMMON_NAME              = "Invalid common name";
    public static final String TPU_INVALID_DESCRIPTION              = "Invalid description";
    public static final String TPU_EMPTY_ORG_UNIT                   = "Empty organization unit";
    public static final String TPU_INVALID_ORG_UNIT                 = "Invalid organization unit";
    public static final String TPU_EMPTY_VALID_DATE                 = "Empty date";
    public static final String TPU_SELECT_COUNTRY                   = "Select country";
    public static final String TPU_SELECT_KEYSIZE                   = "Select key size";
    public static final String TPU_SELECT_HASHALGO                  = "Select hash algorithm";
    public static final String TPU_SELECT_CRTTYPE                  = "Select certificate type";
    
    public static final String TPU_GENERATE_SUCESS                  = "TPU generated successfully";
    public static final String TPU_GENERATE_ERROR                   = "Error occurred";

   
}
