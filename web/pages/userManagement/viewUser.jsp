

<%-- 
    Document   : ViewUser
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
                            $("#deletesuccdialog").dialog('open');
                            $("#deletesuccdialog").html(data.message);
                        } else {
                            $("#deleteerrordialog").dialog('open');
                            $("#deleteerrordialog").html(data.message);
                        }
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
                $("#deletedialog").html('Are you sure you want to delete user ' + keyval + ' ?');
                return false;
            }

            function editUser(keyval) {
                $('#userEditForm').show();
                $('#userISAForm').hide();
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

            function backToMain() {
                $('#userEditForm').hide();
                $('#userISAForm').show();
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



                jQuery("#gridtable").trigger("reloadGrid");

            }

            $.subscribe('onclicksearch', function(event, data) {

                var userProfileID = $('#userProfileID').val();
                var userCategory  = $('#userCategory').val();
                var username      = $('#username').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {username: username,userProfileID:userProfileID,userCategory:userCategory, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });
            
            
            function resetSearchData() {
                $("#gridtable").jqGrid('clearGridData', true);
            }


        </script>
        <div class="body_content" id="includedContent">
            <div class="AddUser_box "> 
                <div class="watermark"></div>
                <div class="heading">View User</div>



                <div class="contentcenter">
                    <s:div id="message">
                        <i style="color: red">  <s:actionerror theme="jquery"/></i>
                        <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                    </s:div>
                    <s:form id="userAAForm" action="ViewviewUser" theme="simple">         
                        <table class="form_table">
                            <tr>
                                <td class="content_td formLable">User Name</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:textfield name="username"  id="username" cssClass="textField" /> </td>
                            </tr>
                            <tr>
                                <td class="content_td formLable">User Profile</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:select  name="userProfileID" headerKey="-1" 
                                           headerValue="---Select---" listKey="key" listValue="value"
                                           list="%{userProfileList}" id="userProfileID" cssClass="textField" /></td>
                            </tr>
                            <tr>
                                <td class="content_td formLable">User Category</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:select  name="userCategory" headerKey="-1" 
                                           headerValue="---Select---" listKey="key" listValue="value"
                                           list="%{userCategoryList}" id="userCategory" cssClass="textField" /></td>
                            </tr>
                            <s:hidden name="loadingStatus" id="loadingStatus" value="1"/>
                            <tr>                                
                                <td colspan="4" align="left"><sj:a 
                                        id="searchbut" 
                                        button="true" 
                                        onClickTopics="onclicksearch"  cssClass="button_asearch ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"   role="button" aria-disabled="false"                                    
                                        >Search</sj:a>
                                        <s:reset  value="Reset" onClick="resetSearchData()" type="button" cssClass="button_reset"/></td>
                            </tr>

                        </table>

                    </s:form>
                </div>  
                
                <div id="divmsg">

                </div>

<div class="viewuser_tbl">
                <div id="tablediv">                  

                    <s:url var="listurl" action="ListviewUser"/>
                    <sjg:grid
                        id="gridtable"
                        caption="View User "
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

                        <sjg:gridColumn name="username" index="USERNAME" title="User Name" align="left" width="20" sortable="true"/>                    
                        <sjg:gridColumn name="email" index="EMAIL" title="Email"  align="left" width="20"  sortable="true"/>                        
                        <sjg:gridColumn name="telephonenumber" index="TELEPHONE" title="Telephone"  align="left" width="20"  sortable="true"/>
                        <sjg:gridColumn name="nic" index="nic" title="NIC"  align="left" width="20"  sortable="true"/>
                        <sjg:gridColumn name="location" index="LOCATION" title="Location"  align="left" width="20"  sortable="true"/>
                        <sjg:gridColumn name="company" index="COMPANY" title="Company"  align="left" width="20"  sortable="true"/>
                        <sjg:gridColumn name="category" index="CATEGORY" title="Category"  align="left" width="20"  sortable="true"/>
                        <sjg:gridColumn name="profile" index="PROFILE" title="Profile"  align="left" width="20"  sortable="true"/>                        
                        <sjg:gridColumn name="appType" index="APP_TYPE" title="App Type"  align="left" width="20"  sortable="true"/>  
                        <sjg:gridColumn name="registrationDate" index="REG_DATE" title="Registration Date"  align="left" width="15"  sortable="true"/>   
                        <%--<sjg:gridColumn name="appModule" index="appModule" title="App Module"  align="left" width="20"  sortable="true"/>--%>                        
                        <sjg:gridColumn name="statusCode" index="STATUSCODE" title="Status" align="center" width="10" formatter="statusformatter" sortable="true"/>                    

                       

                    </sjg:grid> 

                </div> 
</div>



            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 





</body>
</html>