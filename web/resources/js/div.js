function loadXMLDoc(id)
            {
                var path = "";
                var ele = document.getElementById("pheader");
                switch (id) {
//                    case 1 :
//                        path = "userManagementaddNewUserISA.action";
//                        ele.innerHTML = "User Management > Add New User 1";
//                        break;
//                    case 2 :
//                        path="/EpicCA/pages/userManagement/addNewUserIA.jsp";
//                        ele.innerHTML = "User Management > Add New User";
//                        break;
//                    case 3 :
//                        path="/EpicCA/pages/userManagement/addNewUserAA.jsp";
//                        ele.innerHTML = "User Management > Add New User";
//                        break;
//                    case 4 :
//                        path="/EpicCA/pages/userManagement/editViewUserISA.action";
//                        ele.innerHTML = "User Management > Edit And View User";
//                        break;
//                    case 5 :
//                        path="/EpicCA/pages/userManagement/editAndViewUserIA.jsp";
//                        ele.innerHTML = "User Management > Edit And View User";
//                        break;
//                    case 6 :
//                        path="/EpicCA/pages/userManagement/editAndViewUserAA.jsp";
//                        ele.innerHTML = "User Management > Edit And View User";
//                        break;
//                    case 7 :
//                        path="/EpicCA/pages/userManagement/viewUser.jsp";
//                        ele.innerHTML = "User Management > Edit And View User";
//                        break;
////                    case 8 :
////                        path="changePassword.action";
////                        ele.innerHTML = "User Management > Change Password";
////                        break;
////                    case 9 :
////                        path="/EpicCA/pages/userProfileManagement/addNewUserGroupIA.jsp";
////                        ele.innerHTML = "User Profile Management > Add New User Group";
////                        break;
////                    case 10 :
////                        path="/EpicCA/pages/userProfileManagement/addNewUserGroupAA.jsp";
////                        ele.innerHTML = "User Profile Management > Add New User Group";
////                        break;
//                    case 11 :
//                        path="/EpicCA/pages/userProfileManagement/editAndViewUserGroupIA.jsp";
//                        ele.innerHTML = "User Profile Management > Edit and View User Group";
//                        break;
//                    case 12 :
//                        path="/EpicCA/pages/userProfileManagement/editAndViewUserRoleAA.jsp";
//                        ele.innerHTML = "User Profile Management > Edit and View User Role";
//                        break;
//                    case 13 :
//                        path="/EpicCA/pages/userProfileManagement/viewUserGroup.jsp";
//                        ele.innerHTML = "User Profile Management > View User Group";
//                        break;
//                    case 14 :
//                        path="/EpicCA/pages/userProfileManagement/addNewUserRoleIA.jsp";
//                        ele.innerHTML = "User Profile Management > Add New User Role";
//                        break;
//                    case 15 :
//                        path="/EpicCA/pages/userProfileManagement/addNewUserRoleAA.jsp";
//                        ele.innerHTML = "User Profile Management > Add New User Role";
//                        break;
//                    case 16 :
//                        path="/EpicCA/pages/userProfileManagement/editAndViewUserRoleIA.jsp";
//                        ele.innerHTML = "User Profile Management > Edit and View User Role";
//                        break;
//                    case 17 :
//                        path="/EpicCA/pages/userProfileManagement/editAndViewUserRoleAA.jsp";
//                        ele.innerHTML = "User Profile Management > Edit and View User Role";
//                        break;
//                    case 18 :
//                        path="/EpicCA/pages/userProfileManagement/viewUserRole.jsp";
//                        ele.innerHTML = "User Profile Management > View User Role";
//                        break;
//                    case 19 :
//                        path="/EpicCA/pages/instanceManagement/addNewInstance.jsp";
//                        ele.innerHTML = "Instance Management > Add New Instance";
//                        break;
//                    case 20 :
//                        path="/EpicCA/pages/instanceManagement/editAndViewInstance.jsp";
//                        ele.innerHTML = "Instance Management > Edit and View Instance";
//                        break;
//                    case 21 :
//                        path="/EpicCA/pages/instanceManagement/addNewConfigurationProfile.jsp";
//                        ele.innerHTML = "Instance Management > Add New Configuration Profile";
//                        break;
//                    case 22 :
//                        path="/EpicCA/pages/instanceManagement/editAndViewInstance.jsp";
//                        ele.innerHTML = "Instance Management > Edit and View Instance";
//                        break;
//                    case 23 :
//                        path="/EpicCA/pages/instanceManagement/viewConfiguration.jsp";
//                        ele.innerHTML = "Instance Management > View Configuration";
//                        break;
//                    case 24 :
//                        path="/EpicCA/pages/requestManagement/viewRevokingCertificates.jsp";
//                        ele.innerHTML = "Request Management > View Revocking Certificates";
//                        break;
//                    case 25 :
//                        path="/EpicCA/pages/requestManagement/viewRevokingCertificatesCA.jsp";
//                        ele.innerHTML = "Request Management > View Revocking Certificates";
//                        break;
//                    case 26 :
//                        path="/EpicCA/pages/requestManagement/uploadUserCSR.jsp";
//                        ele.innerHTML = "Request Management > Upload User CSR";
//                        break;
//                    case 27 :
//                        path="/EpicCA/pages/requestManagement/downloadPublishingCRL.jsp";
//                        ele.innerHTML = "Request Management > Download Publishing CRL";
//                        break;
//                    case 28 :
//                        path="/EpicCA/pages/requestManagement/downloadCertificate.jsp";
//                        ele.innerHTML = "Request Management > Download Certificate";
//                        break; 
//                    case 29 :
//                        path="/EpicCA/pages/requestManagement/requestsExploreCA.jsp";
//                        ele.innerHTML = "Request Management > Request Explore";
//                        break;
//                    case 30 :
//                        path="/EpicCA/pages/requestManagement/requestsExploreRA.jsp";
//                        ele.innerHTML = "Request Management > Request Explore";
//                        break;
//                    case 31 :
//                        path="/EpicCA/pages/requestManagement/requestsExploreTPU.jsp";
//                        ele.innerHTML = "Request Management > Request Explore";
//                        break;
//                    case 32 :
//                        path="/EpicCA/pages/requestManagement/requestsExploreDRM.jsp";
//                        ele.innerHTML = "Request Management > Request Explore";
//                        break;
//                    case 33 :
//                        path="/EpicCA/pages/requestManagement/requestsExploreOCSP.jsp";
//                        ele.innerHTML = "Request Management > Request Explore";
//                        break;
//                    case 34 :
//                        path="/EpicCA/pages/requestManagement/viewRequestsExploreCA.jsp";
//                        ele.innerHTML = "Request Management > View Request Explore";
//                        break; 
//                    case 35 :
//                        path="/EpicCA/pages/requestManagement/viewRequestsExploreRA.jsp";
//                        ele.innerHTML = "Request Management > View Request Explore";
//                        break;
//                    case 36 :
//                        path="/EpicCA/pages/requestManagement/viewRequestsExploreTPU.jsp";
//                        ele.innerHTML = "Request Management > View Request Explore";
//                        break;
//                    case 37 :
//                        path="/EpicCA/pages/requestManagement/viewRequestsExploreDRM.jsp";
//                        ele.innerHTML = "Request Management > View Request Explore";
//                        break;
//                    case 38 :
//                        path="/EpicCA/pages/requestManagement/viewRequestsExploreOCSP.jsp";
//                        ele.innerHTML = "Request Management > View Request Explore";
//                        break;
//                    case 39 :
//                        path="/EpicCA/pages/certAuthManagement/viewInformation.jsp";
//                        ele.innerHTML = "Certificate Authority Management > View Information";
//                        break;
//                    case 40 :
//                        path="/EpicCA/pages/certAuthManagement/createNewCRL.jsp";
//                        ele.innerHTML = "Certificate Authority Management > Create New CRL";
//                        break;
//                    case 41 :
//                        path="/EpicCA/pages/certAuthManagement/editAndViewCRL.jsp";
//                        ele.innerHTML = "Certificate Authority Management > Edit and View CRLs";
//                        break;
//                    case 42 :
//                        path="/EpicCA/pages/certAuthManagement/viewCRL.jsp";
//                        ele.innerHTML = "Certificate Authority Management > View CRLs";
//                        break;
//                    case 43 :
//                        path="/EpicCA/pages/ocspManagement/viewInformation.jsp";
//                        ele.innerHTML = "OSCP Request Management > View Information";
//                        break;
//                    case 44 :
//                        path="/EpicCA/pages/ocspManagement/addNewCRL.jsp";
//                        ele.innerHTML = "OSCP Request Management > Add New CRL";
//                        break;
//                    case 45 :
//                        path="/EpicCA/pages/ocspManagement/viewCRL.jsp";
//                        ele.innerHTML = "OSCP Request Management > View CRL";
//                        break;
//                    case 46 :
//                        path="/EpicCA/pages/ocspManagement/viewTargetCertificationAuthority.jsp";
//                        ele.innerHTML = "OSCP Request Management > View Target Certification Authority";
//                        break; 
//                    case 47 :
//                        path="/EpicCA/pages/tokenManagement/viewInformation.jsp";
//                        ele.innerHTML = "Token/Smart Card Certificate Management > View Information";
//                        break;
//                    case 48 :
//                        path="/EpicCA/pages/tokenManagement/viewTargetCertificationAuthority.jsp";
//                        ele.innerHTML = "Token/Smart Card Certificate Management > View Target Certificate Authority";
//                        break;
//                    case 49 :
//                        path="/EpicCA/pages/drmManagement/viewInformation.jsp";
//                        ele.innerHTML = "DRM Management > View Information";
//                        break;
//                    case 50 :
//                        path="/EpicCA/pages/instituteManagement/addNewInstitute.jsp";
//                        ele.innerHTML = "Institute Management > Add New Institute";
//                        break;
//                    case 51 :
//                        path="/EpicCA/pages/instituteManagement/editAndViewInstitute.jsp";
//                        ele.innerHTML = "Institute Management > Edit and View Institute";
//                        break;
//                    case 52 :
//                        path="/EpicCA/pages/instituteManagement/addNewConfigurationProfile.jsp";
//                        ele.innerHTML = "Institute Management > Add New Configuration Profile";
//                        break;
//                    case 53 :
//                        path="/EpicCA/pages/instituteManagement/editAndViewConfiguration.jsp";
//                        ele.innerHTML = "Institute Management > Edit and View Configuration Profile";
//                        break; 
//                    case 54 :
//                        path="/EpicCA/pages/instituteManagement/viewConfiguration.jsp";
//                        ele.innerHTML = "Institute Management > View Configuration";
//                        break;
//                    case 55 :
//                        path="/EpicCA/pages/systemHistory/auditSystemHistoryIA.jsp";
//                        ele.innerHTML = "System History > Audit System History";
//                        break;
//                    case 56 :
//                        path="/EpicCA/pages/systemHistory/auditSystemHistoryAA.jsp";
//                        ele.innerHTML = "System History > Audit System History";
//                        break;
//                    case 57 :
//                        path="/EpicCA/pages/systemHistory/dashBoardMonitor.jsp";
//                        ele.innerHTML = "System History > Dash Board Monitoring";
//                        break;
//                    case 58 :
//                        path="/EpicCA/pages/logsManagement/downloadLogsFiles.jsp";
//                        ele.innerHTML = "Log File Management > Download Log Files";
//                        break;
//                    case 59 :
//                        path="/EpicCA/pages/configurationManagement/passwordPolicy.jsp";
//                        ele.innerHTML = "Configuration Management > Password Policy";
//                        break; 
//                    case 60 :
//                        path="/EpicCA/pages/configurationManagement/viewPasswordPolicy.jsp";
//                        ele.innerHTML = "Configuration Management > View Password Policy";
//                        break;
////                    case 61 :
////                        path="viewLogLevels.action";
////                        ele.innerHTML = "Configuration Management > Logs Configurations";
////                        break;
//                    case 62 :
//                        path="/EpicCA/pages/configurationManagement/viewLogConfiguration.jsp";
//                        ele.innerHTML = "Configuration Management > View Log Configurations";
//                        break;
//                    case 63 :
//                        path="/EpicCA/pages/configurationManagement/viewInstituteConfiguration.jsp";
//                        ele.innerHTML = "Configuration Management > View Institute Configuration";
//                        break;
//                    case 64 :
//                        path="/EpicCA/pages/configurationManagement/systemBackupAndRestore.jsp";
//                        ele.innerHTML = "Configuration Management > System Backup and Restore";
//                        break;
//                    case 65 :
//                        path="/EpicCA/pages/configurationManagement/HSMConfiguration.jsp";
//                        ele.innerHTML = "Configuration Management > HSM Configuration";
//                        break;
//                    case 66 :
//                        path="/EpicCA/pages/configurationManagement/configurationDRM.jsp";
//                        ele.innerHTML = "Configuration Management > Configuration";
//                        break;
//                    case 67 :
//                        path="/EpicCA/pages/configurationManagement/configurationOSCP.jsp";
//                        ele.innerHTML = "Configuration Management > Configuration";
//                        break; 
//                    case 68 :
//                        path="/EpicCA/pages/configurationManagement/configurationCA.jsp";
//                        ele.innerHTML = "Configuration Management > Configuration";
//                        break; 
//                    case 69 :
//                        path="/EpicCA/pages/configurationManagement/configurationRA.jsp";
//                        ele.innerHTML = "Configuration Management > Configuration";
//                        break; 
//                    case 70 :
//                        path="/EpicCA/pages/configurationManagement/configurationTPU.jsp";
//                        ele.innerHTML = "Configuration Management > Configuration";
//                        break;
//                    case 71 :
//                        path="/EpicCA/pages/configurationManagement/viewConfigurationDRM.jsp";
//                        ele.innerHTML = "Configuration Management > Configuration";
//                        break;
//                    case 72 :
//                        path="/EpicCA/pages/configurationManagement/viewConfigurationOSCP.jsp";
//                        ele.innerHTML = "Configuration Management > View Configuration";
//                        break;
//                    case 73 :
//                        path="/EpicCA/pages/configurationManagement/viewConfigurationCA.jsp";
//                        ele.innerHTML = "Configuration Management > View Configuration";
//                        break;
//                    case 74 :
//                        path="/EpicCA/pages/configurationManagement/viewConfigurationRA.jsp";
//                        ele.innerHTML = "Configuration Management > View Configuration";
//                        break;
//                    case 75 :
//                        path="/EpicCA/pages/configurationManagement/viewConfigurationTPU.jsp";
//                        ele.innerHTML = "Configuration Management > View Configuration";
//                        break; 
                                          
                    default:
                }
                var xmlhttp;
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp = new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        document.getElementById("includedContent").innerHTML = xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET", path, true);
                xmlhttp.send();
            }
            
        
        
function searchUsersISA() {
    var searchinstitute = $("#searchinstitute").val();
    var searchuserid = $("#searchuserid").val();
    alert(searchinstitute + "//////" + searchuserid);
}