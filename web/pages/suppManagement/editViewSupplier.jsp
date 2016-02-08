<%-- 
    Document   : editViewSupplier
    Created on : Feb 6, 2016, 5:18:25 PM
    Author     : kreshan88
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>      

<html>
    <head> <jsp:include page="/Styles.jsp" /><head />
</head>

<body style="overflow:hidden">

    <div class="wrapper">
        <jsp:include page="../../header.jsp" />            
        <script type="text/javascript">


            function statusformatter(cellvalue, options, rowObject) {
                if (cellvalue == '01') {
                    var html = "<img src='${pageContext.request.contextPath}/resources/images/iconActive.png' />";
                } else {
                    var html = "<img src= '${pageContext.request.contextPath}/resources/images/iconInactive.png' />";
                }
                return html;
            }

            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteInit(&#34;" + rowObject.suppId + "&#34;,&#34;" + rowObject.suppName + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/iconDelete.png'  /></a>";
            }

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:editCall(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
            }

            function deleteInit(keyval,desc) {
                $('#message').empty();
                $("#deletedialog").data('keyval', keyval).dialog('open');
                $("#deletedialog").html('<br><b><font size="3" color="red"><center>Please confirm to delete supplier : ' + desc + '');
                return false;
            }
            
            function deleteNow(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteeditViewSupplier',
                    data: {dsuppId: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        
                        if (data.dsuccess) {
                            $("#deletedialogbox").dialog('open');
                            $("#deletedialogbox").html('<br><b><font size="3" color="green"><center>' + data.dmessage + ' ');
                        } else {
                            $("#deletedialogbox").dialog('open');
                            $("#deletedialogbox").html('<br><b><font size="3" color="red"><center>' + data.dmessage + ' ');
                        }
                        
                        jQuery("#gridtable").trigger("reloadGrid");
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });

            }


            function editCall(keyval) {

                $.ajax({
                    url: '${pageContext.request.contextPath}/FindeditViewSupplier',
                    data: {upsuppId: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {

                        $('#updateForm').show();
                        $('#searchForm').hide();
                        $('#divmsg').empty();

                        $('#upsuppId').val(data.upsuppId);
                        $('#upsuppName').attr('readOnly', true);
                        $('#upsuppName').val(data.upsuppName);
                        $('#upemail').val(data.upemail);
                        $('#upaddress').val(data.upaddress);
                        
                        $('#uptpOffice').val(data.uptpOffice);
                        $('#uptpMobile').val(data.uptpMobile);
                        $('#upstatus').val(data.upstatus);
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });
            }


            function resetUpdateForm() {
                var keyval = $('#upsuppId').val();
                editCall(keyval);
            }
            
            
            function resetData() {
                $('#upsuppName').val("");
                $('#upaddress').val("");
                $('#upemail').val("");
                $('#uptpMobile').val("");
                $('#uptpOffice').val("");
                $('#upstatus').val("-1");
                jQuery("#gridtable").trigger("reloadGrid");
            }
            
            function generateXSL() {
                alert("dddd gen xl");

            }

            function backToMain() {
                $('#updateForm').hide();
                $('#searchForm').show();
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }

            $.subscribe('onclicksearch', function(event, data) {

                var suppName = $('#suppName').val();

                $("#gridtable").jqGrid('setGridParam', {postData: {suppName: suppName, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });



        </script>
        <div class="body_content" id="includedContent">
             
                <div class="watermark"></div>
                <div class="heading">Edit & View Supplier</div>
                <div class="AddUser_box ">
                    <div class="message" >   
                        <s:div id="divmsg">
                            <i style="color: red">  <s:actionerror theme="jquery"/></i>
                            <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                        </s:div>
                    </div>
                

                <div class="contentcenter">
                    
                    

                    <s:form id="searchForm"  action="XSLcreatviewSupplier" theme="simple" method="post">         
                        <table class="form_table">
                            
                            <tr>
                                <td class="content_td formLable">Supplier Name</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:textfield name="suppName"  id="suppName" cssClass="textField" /> </td>
                            </tr>

                            <tr>                                
                                <td colspan="4">
                                    <sj:a   id="searchbut"  button="true"    onClickTopics="onclicksearch"  cssClass="button_asearch" 
                                            role="button" aria-disabled="false" >Search</sj:a>
                                    <s:submit  value="Export XSL"  cssClass="button_sreset" />
                               </td>

                            </tr>

                        </table>

                    </s:form>

                </div>
                <div class="contentcenter">
                    <s:form id="updateForm"  theme="simple" method="post" cssClass="form_hidden" >

                        <table class="form_table">
                                    <s:hidden name="upsuppId" id="upsuppId" cssClass="textField" />
                            <tr>
                                    <td class="content_td formLable">Supplier Name<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="upsuppName" name="upsuppName" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Address</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="upaddress" name="upaddress" cssClass="textField" /></td>
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Email<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="upemail" name="upemail" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Tp-Office</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="uptpOffice" name="uptpOffice" cssClass="textField" /></td>
                                   
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Tp-Mobile</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="uptpMobile" name="uptpMobile" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Status</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:select  name="upstatus" id="upstatus" headerKey="-1"  headerValue="---Select---" 
                                               list="%{usableStatusList}" listKey="key" listValue="value"  cssClass="dropdown" /></td>
                                </tr>

                                <tr>
                                    <td colspan="3">
                                        <s:url var="updateuserurl" action="UpdateeditViewSupplier"/>                                   
                                        <sj:submit  button="true"  href="%{updateuserurl}" value="Update" 
                                        targets="divmsg" cssClass="button_sadd" onCompleteTopics="oncompleteform" />
                                        
                                        <sj:a href="#" name="back" button="true" onclick="backToMain()"  cssClass="button_aback" >Back</sj:a> 
                                        
                                        <sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="button_sreset"/>
                                    <td colspan="4"></td>
                                </tr>
                            

                            <tr>
<!--                                <td></td>-->
                                <td colspan="3" align="left" class="buttoncell">
                                    
                                </td>
                                <td align="left" width="10px" class="buttoncell">
                                    
                                </td>
                                <td align="right" width="60px" class="buttoncell">
                                    
                                </td>

                            </tr>

                        </table>

                    </s:form>  
                </div>


                <div class="viewuser_tbl">
                    <div id="tablediv">

                        <sj:dialog 
                            id="deletedialog" 
                            buttons="{ 
                            'OK':function() { deleteNow($(this).data('keyval'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Delete confirmation"
                            width="400"
                            height="150"
                            position="center"
                            />

                        <sj:dialog 
                            id="deletedialogbox" 
                            buttons="{ 'OK':function() { $( this ).dialog( 'close' );} }"  
                            autoOpen="false" 
                            modal="true" 
                            title="Delete user" 
                            width="400"
                            height="150"
                            position="center"
                            />
                        <!-- End delete dialog box -->
                        
                        <s:url var="listurl" action="ListeditViewSupplier"/>
                        <sjg:grid
                            id="gridtable"
                            caption="Edit & View Custumer"
                            dataType="json"
                            href="%{listurl}"
                            pager="true"
                            gridModel="gridModel"
                            rowList="10,15,20"
                            rowNum="10"
                            autowidth="true"
                            rownumbers="true"
                            onCompleteTopics="completetopics"
                            rowTotal="false"
                            viewrecords="true"
                            >

                             <sjg:gridColumn name="suppId" index="SUP_ID" title="Supplier id"  hidden="true"/>                    
                        
                            <sjg:gridColumn name="suppName" index="NAME" title="Supplier Name"  align="left" width="10"  sortable="true"/>                        
                            <sjg:gridColumn name="email" index="EMAIL" title="Email"  align="left" width="15"  sortable="true"/>
                            <sjg:gridColumn name="address" index="ADDRESS" title="Address"  align="left" width="15"  sortable="true"/>
                            <sjg:gridColumn name="tpOffice" index="TP_OFFICE" title="Office No"  align="left" width="12"  sortable="true"/>
                            <sjg:gridColumn name="tpMobile" index="TP_MOBILE" title="Mobile No"  align="left" width="12"  sortable="true"/>
                            <sjg:gridColumn name="regDate" index="REG_DATE" title="Reg Date"  align="left" width="8"  sortable="true"/>                         
                            <sjg:gridColumn name="statusCode" index="STATUS" title="Status" align="center" width="8" formatter="statusformatter" sortable="true"/>                    

                                         
                            <sjg:gridColumn name="suppId"  title="Edit" align="center" width="10" align="center"  formatter="editformatter" sortable="false" />
                            <sjg:gridColumn name="suppId"  title="Delete" align="center" width="10" align="center"   formatter="deleteformatter" sortable="false" />

                        </sjg:grid> 

                    </div> 



                </div>
            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 





</body>
</html>
