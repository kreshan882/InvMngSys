<%-- 
    Document   : Home
    Created on : Aug 5, 2014, 4:25:18 PM
    Author     : kreshan
--%>

<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.org.login.bean.SessionUserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Epic CA</title>
        <jsp:include page="../../Styles.jsp" />
        
        
        
    </head>

<body style="overflow:hidden">

        <div class="wrapper">

            <jsp:include page="../../header.jsp" /> 

            

            <div class="body_content" id="includedContent">
                <div class="AddUser_box">
                    <div class="watermark"></div>
                    <div class="contentcenter">
                        <div class="table_center">
                 
                        <table class="form_table" cellspacing="8" align="center">
<!--                            <tr class="home_data">
                                <td class="content_td homeLable" valign="top">Institute</td>
                                <td class="content_td homeLable1">${SessionHomeValues.institute}</td>
                            </tr>
                            <tr class="home_data">
                                <td class="content_td homeLable" valign="top">Application Type</td>
                                <td class="content_td homeLable1">${SessionHomeValues.appTypeName}</td>
                            </tr>
                            <tr class="home_data">
                                <td class="content_td homeLable" valign="top">Application Module</td>
                                <td class="content_td homeLable1">${SessionHomeValues.appIdName}</td>
                            </tr>-->
                            <tr class="home_data">
                                <td class="content_td homeLable" valign="top">Version</td>
                                <td class="content_td homeLable1">ttttt</td>
                            </tr>
   

                        </table>
                        </div>
                            
                            
                            
                            
                            
                </div>
</div>
            </div>



            <jsp:include page="../../footer.jsp" /> 



        </div> <!--end of body_content-->

    </body>
</html>

