<%-- 
    Document   : addItem
    Created on : Feb 7, 2016, 7:49:43 AM
    Author     : kreshan88
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
                
                $('#itemNo').val("");
                $('#itemType').val("-1");
                $('#name').val("");
                $('#colour').val("");
                $('#unitType').val("-1");
                $('#unitPrize').val("");
                $('#image').val("");
            }
            
        </script>
    </head>
    <body style="overflow:hidden">
        <div class="wrapper">

            <jsp:include page="../../header.jsp" /> 



            <div class="body_content" id="includedContent">


                
                    <div class="watermark"></div>
                    <div class="heading">Add New Items</div>
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
                                    <td class="content_td formLable">Item barcode<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="itemNo" name="itemNo" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Item type</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:select  name="itemType" id="itemType" headerKey="-1"  headerValue="---Select---" 
                                               list="%{itemTypeList}" listKey="key" listValue="value"  cssClass="dropdown" /></td>
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Name<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="name" name="name" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Colour</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="colour" name="colour" cssClass="textField" /></td>
                                   
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Unit type</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:select  name="unitType" id="unitType" headerKey="-1"  headerValue="---Select---" 
                                               list="%{unitTypeList}" listKey="key" listValue="value"  cssClass="dropdown" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Unit prize</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="unitPrize" name="unitPrize" cssClass="textField" /></td>
                                </tr>

                                <tr>
                                    <td class="content_td formLable">Upload Image</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:file  id = "image" name="image" label="File" cssClass="fileField"  /></td>
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
                                        <s:url var="addurl" action="AddaddItem"/>
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
