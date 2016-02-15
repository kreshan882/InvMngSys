<%-- 
    Document   : viewItem
    Created on : Feb 7, 2016, 7:50:13 AM
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
                
                $("#deletedialogbox").dialog('open');
                $("#deletedialogbox").html('<br><b><font size="3" color="green"><center> \n\
         <img src= "${pageContext.request.contextPath}/resources/images/test1.jpg" />' + image + ' ');
             }
            $.subscribe('onclicksearch', function(event, data) {

                var itemNo      = $('#itemNo').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {itemNo: itemNo, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });
            
            



        </script>
        <div class="body_content" id="includedContent">
            <div class="AddUser_box "> 
                <div class="watermark"></div>
                <div class="heading">View Items</div>



                <div class="contentcenter">
                    <s:div id="message">
                        <i style="color: red">  <s:actionerror theme="jquery"/></i>
                        <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                    </s:div>
                    <s:form id="userAAForm" action="XSLcreatviewItem" theme="simple" method="post">         
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
                
                <div id="divmsg">

                </div>

            <div class="viewuser_tbl">
                <div id="tablediv">                  
                
                    
                    <sj:dialog 
                            id="deletedialogbox" 
                            buttons="{ 'OK':function() { $( this ).dialog( 'close' );} }"  
                            autoOpen="false" 
                            modal="true" 
                            title="Delete user" 
                            width="400"
                            height="450"
                            position="center"
                            />
                    
                    <s:url var="listurl" action="ListviewItem"/>
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
                        <sjg:gridColumn name="unitPrize" index="UNIT_PRIZE" title="Unit Prize"  align="left" width="12"  sortable="true"/>
                        <sjg:gridColumn name="regDate" index="REG_DATE" title="Reg Date"  align="left" width="8"  sortable="true"/>                         
                        <sjg:gridColumn name="statusCode" index="STATUS" title="Status" align="center" width="8" formatter="statusformatter" sortable="true"/>                    
                        <sjg:gridColumn name="dbfilename" index="IMG_PATH" title="Image"  align="center" width="12" formatter="imageformatter" sortable="true"/>
                       

                    </sjg:grid> 

                </div> 
</div>



            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 





</body>
</html>