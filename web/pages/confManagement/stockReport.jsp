<%-- 
    Document   : stockReport
    Created on : Feb 28, 2016, 8:16:20 PM
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

            $.subscribe('onclicksearch', function(event, data) {

                var storeId      = $('#storeId').val();
                var itemNo      = $('#itemNo').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {storeId: storeId,itemNo:itemNo, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });
            
            



        </script>
        <div class="body_content" id="includedContent">
            <div class="AddUser_box "> 
                <div class="watermark"></div>
                <div class="heading">Stock Report</div>



                <div class="contentcenter">
                    <s:div id="message">
                        <i style="color: red">  <s:actionerror theme="jquery"/></i>
                        <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                    </s:div>
                    <s:form id="searchForm" action="XSLcreatstockReport" theme="simple" method="post">         
                        <table class="form_table">
                            <tr>
                                <td class="content_td formLable">Store</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:select  name="storeId" id="storeId" headerKey="0"  headerValue="All Store" 
                                               list="%{storeIdList}" listKey="key" listValue="value"  cssClass="dropdown" /> </td>
                            </tr>
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
                
                <div id="divmsg">

                </div>

            <div class="viewuser_tbl">
                <div id="tablediv">                  

                    <s:url var="listurl" action="ListstockReport"/>
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
                 
                        
                        <sjg:gridColumn name="itemCode" index="ITEM_NO" title="Invoice Number"  align="left" width="10"  sortable="true"/>                        
                        <sjg:gridColumn name="itemName" index="NAME" title="Item NAme"  align="left" width="15"  sortable="true"/>
                        <sjg:gridColumn name="strName" index="STRNAME" title="Store Name"  align="left" width="15"  sortable="true"/>
                        <sjg:gridColumn name="qty" index="COUNT" title="Quentity"  align="left" width="15"  sortable="true"/>
                        
                       

                    </sjg:grid> 

                </div> 
</div>



            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 





</body>
</html>
