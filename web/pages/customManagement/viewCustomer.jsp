

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

            $.subscribe('onclicksearch', function(event, data) {

                var custName      = $('#custName').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {custName: custName, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });
            
            



        </script>
        <div class="body_content" id="includedContent">
            <div class="AddUser_box "> 
                <div class="watermark"></div>
                <div class="heading">View Customer</div>



                <div class="contentcenter">
                    <s:div id="message">
                        <i style="color: red">  <s:actionerror theme="jquery"/></i>
                        <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                    </s:div>
                    <s:form id="userAAForm" action="XSLcreatviewCus" theme="simple" method="post">         
                        <table class="form_table">
                            <tr>
                                <td class="content_td formLable">Customer Name</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:textfield name="custName"  id="custName" cssClass="textField" /> </td>
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
                
                <div id="divmsg">

                </div>

            <div class="viewuser_tbl">
                <div id="tablediv">                  

                    <s:url var="listurl" action="ListviewCus"/>
                    <sjg:grid
                        id="gridtable"
                        caption="View Customer"
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

                        <sjg:gridColumn name="custId" index="CUS_ID" title="User Name"  hidden="true"/>                    
                        
                        <sjg:gridColumn name="custName" index="NAME" title="Customer Name"  align="left" width="10"  sortable="true"/>                        
                        <sjg:gridColumn name="email" index="EMAIL" title="Email"  align="left" width="15"  sortable="true"/>
                        <sjg:gridColumn name="address" index="ADDRESS" title="Address"  align="left" width="15"  sortable="true"/>
                        <sjg:gridColumn name="tpOffice" index="TP_OFFICE" title="Office No"  align="left" width="12"  sortable="true"/>
                        <sjg:gridColumn name="tpMobile" index="TP_MOBILE" title="Mobile No"  align="left" width="12"  sortable="true"/>
                        
                        <sjg:gridColumn name="regDate" index="REG_DATE" title="Reg Date"  align="left" width="8"  sortable="true"/>                         
                        <sjg:gridColumn name="statusCode" index="STATUS" title="Status" align="center" width="8" formatter="statusformatter" sortable="true"/>                    

                       

                    </sjg:grid> 

                </div> 
</div>



            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 





</body>
</html>