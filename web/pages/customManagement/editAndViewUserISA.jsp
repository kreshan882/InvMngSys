

<%-- 
    Document   : editAndViewUserISA
    Created on : Aug 6, 2014, 4:05:53 PM
    Author     : kreshan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>      

<html>
    <head>
        <jsp:include page="/Styles.jsp" />
    <head />
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
                return "<a href='#' onClick='deleteUserInit(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/iconDelete.png'  /></a>";
            }

            function editformatter(cellvalue, options, rowObject) {

                return "<a href='#' onClick='javascript:editUser(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
            }

            function deleteUser(keyval) {
                var instituteID = $('#institute').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteeditViewUserISA',
                    data: {username: keyval, instituteID: instituteID},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        $('#message').empty();
                        if (data.delsuccess == 1) {
                            $("#deletedialogbox").dialog('open');
                            $("#deletedialogbox").html('<br><b><font size="3" color="green"><center>' + data.message + ' ');
                        } else {
                            $("#deletedialogbox").dialog('open');
                            $("#deletedialogbox").html('<br><b><font size="3" color="red"><center>' + data.message + ' ');
                        }
                        $('#message').val('Success');
                        resetData();
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });

            }


            function loadUserList(keyval) {

                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadUserseditViewUserISA',
                    data: {instituteID: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {

                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });


            }

            function deleteUserInit(keyval) {
                $('#message').empty();
                $("#deletedialog").data('keyval', keyval).dialog('open');
                $("#deletedialog").html('<br><b><font size="3" color="red"><center>Please confirm to delete user : ' + keyval + '');
                return false;
            }

            function editUser(keyval) {
                $('#userEditForm').show();
                $('#userISAForm').hide();
                $('#divmsg').empty();
                var instituteID = $('#institute').val();

                $.ajax({
                    url: '${pageContext.request.contextPath}/FindeditViewUserISA',
                    data: {username: keyval, instituteID: instituteID},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {

                        $('#userEditForm').show();
                        $('#userISAForm').hide();


                        $('#upusername2').attr('readOnly', true);
                        $('#upstatus').val(data.statusCode);
                        $('#upcompany').val(data.company);
                        $('#upusername2').val(data.username);
                        $('#upinstituteID').val(data.instituteID);


                    },
                    error: function(data) {

                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });
            }

            function resetData() {
                jQuery("#gridtable").trigger("reloadGrid");
            }
            
            function resetSearchData() {
                $("#gridtable").jqGrid('clearGridData', true);
            }

            function backToMain() {
                $('#userEditForm').hide();
                $('#userISAForm').show();
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }


            function resetUpdateForm() {

                var instituteID = $('#institute').val();
                var keyval = $('#upusername2').val();

                $.ajax({
                    url: '${pageContext.request.contextPath}/FindeditViewUserISA',
                    data: {username: keyval, instituteID: instituteID},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {

                        $('#userEditForm').show();
                        $('#userISAForm').hide();


                        $('#upusername2').attr('readOnly', true);
                        $('#upstatus').val(data.statusCode);
                        $('#upcompany').val(data.company);
                        $('#upusername2').val(data.username);
                        $('#upinstituteID').val(data.instituteID);


                    },
                    error: function(data) {

                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });
                
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }

            $.subscribe('onclicksearch', function(event, data) {

                var username = $('#username').val();
                var instituteID = $('#institute').val();

                $("#gridtable").jqGrid('setGridParam', {postData: {username: username, instituteID: instituteID, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });

            $.subscribe('oncompleteform', function(event, data) {
                $('#upstatus').val("");
                $('#upcompany').val("");
                $('#upusername2').val("");
                jQuery("#gridtable").trigger("reloadGrid");

            });


        </script>
        <div class="body_content" id="includedContent">
             
                <div class="watermark"></div>
                <div class="heading">Edit & View User</div>
<div class="AddUser_box ">
                <div class="message">
                            <div id="divmsg">             
                        <s:div id="message">
                            <i style="color: red">  <s:actionerror theme="jquery"/></i>
                            <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                        </s:div>
                        </div>
                    </div>
                

                <div class="contentcenter">
                    
                    

                    <s:form id="userISAForm" action="VieweditViewUserISA" theme="simple">         
                        <table class="form_table">
                            <tr>
                                <td class="content_td formLable">Institute</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:select  name="institute" headerKey="-1" 
                                           headerValue="---Select---" listKey="key" listValue="value"
                                           list="%{instituteList}" id="institute" onchange="loadUserList(this.value)" cssClass="dropdown" /></td>
                            </tr>
                            <s:hidden name="loadingStatus" id="loadingStatus" value="1"/>
                            <tr>
                                <td class="content_td formLable">User Name</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:textfield name="username"  id="username" cssClass="textField" /> </td>
                            </tr>

                            <tr>                                
                                <td colspan="4"><sj:a 
                                        id="searchbut" 
                                        button="true"                                        
                                        onClickTopics="onclicksearch"  cssClass="button_asearch"   role="button" aria-disabled="false"                                    
                                        >Search</sj:a>
                                        <s:reset  value="Reset" onClick="resetSearchData()" type="button" cssClass="button_reset"/></td>

                            </tr>

                        </table>

                    </s:form>

                </div>
                <div class="contentcenter">
                    <s:form action="UpdateeditViewUserISA" theme="simple" method="post" id="userEditForm" cssClass="form_hidden" >

                        <table class="form_table">

                            <tr>
                                <td class="content_td formLable" colspan="2">User Name</td>
                                <td class="content_td formLable" width="5px">:</td>
                                <td colspan="3"><s:textfield name="upusername2" id="upusername2" cssClass="textField" /></td>

                            </tr>
                            <tr>
                                <td colspan="2" class="content_td formLable">Status</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="3"><s:select  name="upstatus" headerKey="-1" 
                                           headerValue="---Select---"  list="%{usableStatusList}" listKey="key" listValue="value"
                                           id="upstatus" cssClass="dropdown" />
                                </td>


                            </tr>

                            <s:hidden name="upinstituteID" id="upinstituteID" value="1"/>
                            <tr>
                                <td colspan="2" class="content_td formLable">Company</td>
                                <td class="content_td formLable">:</td>
                                <td align="left" colspan="3"><s:textfield name="upcompany" id="upcompany" cssClass="textField"  />  </td>

                            </tr>

                            <tr>
<!--                                <td></td>-->
                                <td colspan="3" align="right" class="buttoncell"><s:url var="updateuserurl" action="UpdateeditViewUserISA"/>                                   
                                    <sj:submit 
                                        button="true"
                                        href="%{updateuserurl}"
                                        value="Update" 
                                        targets="divmsg"  
                                        cssClass="button_sadd"
                                        onCompleteTopics="oncompleteform"
                                        />
                                </td>
                                <td align="left" width="60px" class="buttoncell">
                                    <sj:a href="#" name="back" button="true" onclick="backToMain()"  cssClass="button_aback" >Back</sj:a> 
                                    </td>
                                    <td align="right" width="60px" class="buttoncell">
                                    <sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="button_sreset"/>
                                </td>

                            </tr>
<!--                            <tr>
                                <td colspan="6">  
                                    <div>

                                    </div>
                                </td>   
                            </tr>-->

                        </table>

                    </s:form>  
                </div>


                <div class="viewuser_tbl">
                    <div id="tablediv">

                        <sj:dialog 
                            id="deletedialog" 
                            buttons="{ 
                            'OK':function() { deleteUser($(this).data('keyval'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Delete user confirmation"
                            width="400"
                            height="150"
                            position="center"
                            />

                        <sj:dialog 
                            id="deletedialogbox" 
                            buttons="{
                            'OK':function() { $( this ).dialog( 'close' );}
                            }"  
                            autoOpen="false" 
                            modal="true" 
                            title="Delete user" 
                            width="400"
                            height="150"
                            position="center"
                            />
                        <!-- End delete dialog box -->
                        
                        <s:url var="listurl" action="ListeditViewUserISA"/>
                        <sjg:grid
                            id="gridtable"
                            caption="Edit View User"
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

                            <sjg:gridColumn name="username" index="username" title="User Name" align="left" width="20" sortable="true"/>                    
                            <sjg:gridColumn name="company" index="company" title="Company"  align="left" width="20"  sortable="true"/>
                            <sjg:gridColumn name="regDate" index="regDate" title="Reg Date" align="center"  width="18"  sortable="true"/>
                            <sjg:gridColumn name="statusCode" index="statusCode" title="Status" align="center" width="10" formatter="statusformatter" sortable="true"/>                    
                            <sjg:gridColumn name="username" index="username" title="Edit" align="center" width="10" align="center"  formatter="editformatter" sortable="false" />
                            <sjg:gridColumn name="username" index="username" title="Delete" align="center" width="10" align="center"   formatter="deleteformatter" sortable="false" />

                        </sjg:grid> 

                    </div> 



                </div>
            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 





</body>
</html>
