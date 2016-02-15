<%-- 
    Document   : editViewItem
    Created on : Feb 7, 2016, 7:50:01 AM
    Author     : kreshan88
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>      

<html>
    <head>
        <jsp:include page="/Styles.jsp" />
    <sx:head />
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
             function imageformatter(cellvalue, options, rowObject) {
                    if (cellvalue != null) {
                        return "<a href='#' onClick='imageLoad(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/iconActive.png'  /></a>";
                       // var html = "<img src='${pageContext.request.contextPath}/resources/images/iconActive.png' />";
                    } else {
                        var html = "<img src= '${pageContext.request.contextPath}/resources/images/iconInactive.png' />";
                    }
                    return html;
             }
             
             function imageLoad(image){
                
                $("#imagedialogbox").dialog('open');
                $("#imagedialogbox").html('<br><b><font size="3" color="green"><center> \n\
                <img src= "${pageContext.request.contextPath}/resources/images/test1.jpg" />' + image + ' ');
             }
             
            $.subscribe('onclicksearch', function(event, data) {

                var itemNo      = $('#itemNo').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {itemNo: itemNo, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });
            
            

            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteInit(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/iconDelete.png'  /></a>";
            }

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:editCall(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
            }

            function deleteInit(keyval) {
                $('#message').empty();
                $("#deletedialog").data('keyval', keyval).dialog('open');
                $("#deletedialog").html('<br><b><font size="3" color="red"><center>Please confirm to delete Item : ' + keyval + '');
                return false;
            }
            
            function deleteNow(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteeditViewItem',
                    data: {ditemNo: keyval},
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
                alert(keyval);
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindeditViewItem',
                    data: {upitemNo: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        alert(data.upname);
                        $('#updateForm').show();
                        $('#searchForm').hide();
                        $('#divmsg').empty();

                        $('#upitemNo').val(data.upitemNo);
                        $('#upitemNo').attr('readOnly', true);
                        $('#upname').val(data.upname);
                        $('#upcolour').val(data.upcolour);
                        $('#upunitPrize').val(data.upunitPrize);
                        $('#upstatus').val(data.upstatus);
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });
            }


            function resetUpdateForm() {
                var keyval = $('#upitemNo').val();
                editCall(keyval);
            }
            
            
            function resetData() {
                $('#upitemNo').val("");
                $('#upname').val("");
                $('#upcolour').val("");
                $('#upunitPrize').val("");
                $('#uptpOffice').val("");
                $('#upstatus').val("-1");
                jQuery("#gridtable").trigger("reloadGrid");
            }
            

            function backToMain() {
                $('#updateForm').hide();
                $('#searchForm').show();
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }


        </script>
        <div class="body_content" id="includedContent">
            <div class="AddUser_box "> 
                <div class="watermark"></div>
                <div class="heading">Edit & View Items</div>
                    <div class="message" >   
                        <s:div id="divmsg">
                            <i style="color: red">  <s:actionerror theme="jquery"/></i>
                            <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                        </s:div>
                    </div>


                <div class="contentcenter">

                    <s:form id="searchForm" action="XSLcreatviewItem" theme="simple" method="post">         
                        <table class="form_table">
                            <tr>
                                <td class="content_td formLable">Item Number</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:textfield name="itemNo"  id="itemNo" cssClass="textField" /> </td>
                            </tr>
                           
                            
                            <tr>                                
                                <td colspan="4" align="left">
                                    <sj:a  id="searchbut"   button="true" onClickTopics="onclicksearch"  
                                           cssClass="button_asearch ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"   
                                           role="button" aria-disabled="false" >Search</sj:a>
                                    <s:submit  value="Export XSL"  cssClass="button_sreset" />
                            </tr>

                        </table>

                    </s:form>
                </div>  
                <div class="contentcenter">
                    <s:form id="updateForm"  theme="simple" method="post" cssClass="form_hidden" >

                        <table class="form_table">
                                <tr>
                                    <td class="content_td formLable">Item Number<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="upitemNo" name="upitemNo" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Item Name</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="upname" name="upname" cssClass="textField" /></td>
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Color<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="upcolour" name="upcolour" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Unit Prize</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="upunitPrize" name="upunitPrize" cssClass="textField" /></td>
                                   
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Status</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:select  name="upstatus" id="upstatus" headerKey="-1"  headerValue="---Select---" 
                                               list="%{usableStatusList}" listKey="key" listValue="value"  cssClass="dropdown" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable"></td>
                                    <td></td>
                                </tr>

                                <tr>
                                    <td colspan="3">
                                        <s:url var="updateuserurl" action="UpdateeditViewItem"/>                                   
                                        <sj:submit  button="true"  href="%{updateuserurl}" value="Update" 
                                        targets="divmsg" cssClass="button_sadd" onCompleteTopics="oncompleteform" />
                                        
                                        <sj:a href="#" name="back" button="true" onclick="backToMain()"  cssClass="button_aback" >Back</sj:a> 
                                        
                                        <sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="button_sreset"/>
                                    <td colspan="4"></td>
                                </tr>

                                <tr>
                                    <td colspan="3" align="left" class="buttoncell">  </td>
                                    <td align="left" width="10px" class="buttoncell">   </td>
                                    <td align="right" width="60px" class="buttoncell"> </td>
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
                    <sj:dialog 
                            id="imagedialogbox" 
                            buttons="{ 'OK':function() { $( this ).dialog( 'close' );} }"  
                            autoOpen="false" 
                            modal="true" 
                            title="View Image" 
                            width="400"
                            height="450"
                            position="center"
                            />
                    
                    <s:url var="listurl" action="ListeditViewItem"/>
                    <sjg:grid
                        id="gridtable"
                        caption="View Items"
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
                
                        
                        <sjg:gridColumn name="itemNo" index="ITEM_NO" title="Barcode"  align="left" width="10"  sortable="true"/>                        
                        <sjg:gridColumn name="name" index="NAME" title="Item Name"  align="left" width="15"  sortable="true"/>
                        <sjg:gridColumn name="colour" index="COLOUR" title="Colour"  align="left" width="15"  sortable="true"/>
                        <sjg:gridColumn name="unitTcost" index="UNIT_TCOST" title="Total Unit Cost"  align="left" width="12"  sortable="true"/>
                        <sjg:gridColumn name="unitPrize" index="UNIT_PRIZE" title="Unit Prize"  align="left" width="12"  sortable="true"/>
                        <sjg:gridColumn name="regDate" index="REG_DATE" title="Reg Date"  align="left" width="8"  sortable="true"/>                         
                        <sjg:gridColumn name="statusCode" index="STATUS" title="Status" align="center" width="8" formatter="statusformatter" sortable="true"/>                    
                        <sjg:gridColumn name="dbfilename" index="IMG_PATH" title="Image"  align="center" width="12" formatter="imageformatter" sortable="true"/>
                       
                        <sjg:gridColumn name="itemNo"  title="Edit" align="center" width="10" align="center"  formatter="editformatter" sortable="false" />
                        <sjg:gridColumn name="itemNo"  title="Delete" align="center" width="10" align="center"   formatter="deleteformatter" sortable="false" />
                    </sjg:grid> 

                </div> 
</div>



            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 





</body>
</html>