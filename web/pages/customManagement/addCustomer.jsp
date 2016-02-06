<%-- 
    Document    : addNewUserISA
    Description : Institute user adding panel
    Created on  : Aug 6, 2014, 4:03:57 PM
    Author      : Sadun
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
    <head>
        
        <jsp:include page="/Styles.jsp" />
        <script type="text/javascript">
            $.subscribe('resetButton', function(event, data) {
                $('#divmsg').empty();
                resetData();
            });
            
            function resetData(){
                
                $('#custName').val("");
                $('#address').val("");
                $('#email').val("");
                $('#tpMobile').val("");
                $('#tpOffice').val("");
            }
            
        </script>
    </head>
    <body style="overflow:hidden">
        <div class="wrapper">

            <jsp:include page="../../header.jsp" /> 



            <div class="body_content" id="includedContent">


                
                    <div class="watermark"></div>
                    <div class="heading">Add New Customer</div>
                   <div class="AddUser_box "> 
                    
                    <div class="message" >   
                        <s:div id="divmsg">
                            <i style="color: red">  <s:actionerror theme="jquery"/></i>
                            <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                        </s:div>
                    </div>

                            
                    <div class="contentcenter">
                        
                        <s:form theme="simple" method="post" name="addcus" id="addcus">         
                            <table class="form_table" border="0px">
                                
                                <tr>
                                    <td class="content_td formLable">Customer Name<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="custName" name="custName" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Address</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="address" name="address" cssClass="textField" /></td>
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Email<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="email" name="email" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Tp-Office</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="tpOffice" name="tpOffice" cssClass="textField" /></td>
                                   
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Tp-Mobile</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="tpMobile" name="tpMobile" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable"></td>
                                    <td></td>
                                </tr>

                                
                                <tr>
                                    <td class="content_td formLable" colspan="7"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                                
                                <tr>
                                    <td colspan="3">
                                        <s:url var="addurl" action="CustomerAddaddCus"/>
                                        <sj:submit button="true" value="Save" href="%{addurl}"  targets="divmsg"  cssClass="button_ssave" />
                                        <sj:submit button="true" onClickTopics="resetButton" value="Reset" cssClass="button_reset"/>
                                    <td colspan="4"></td>
                                </tr>
                                </table>

                        </s:form>
                        
                    </div>
                </div>
            </div><!--end of body_content-->





            <jsp:include page="../../footer.jsp" /> 



        </div> 

    </body>
</html>
