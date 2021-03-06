<%-- 
    Document   : editViewSale
    Created on : Feb 7, 2016, 9:41:34 AM
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
            function reprintformatter(cellvalue, options, rowObject) {
                if (rowObject.evstatus == '01') {
                    return "<a href='#' onClick='reprintInit(&#34;" + rowObject.evinvId + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/print.png'  /></a>"; 
                } else {
                    var html = "<img src= '${pageContext.request.contextPath}/resources/images/iconInactive.png' />";
                }
                return html;
            }
            
            function rejectformatter(cellvalue, options, rowObject) {
                if (rowObject.evstatus == '01') {
                    return "<a href='#' onClick='cancleInit(&#34;" + rowObject.evinvId + "&#34;,&#34;" + rowObject.evstorId + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/reject.png'  /></a>"; 
                } else {
                    var html = "<img src= '${pageContext.request.contextPath}/resources/images/rejectDisabled.png' />";
                }
                return html;
            }
            function reprintInit(evinvId){
                      $('#pdfinvoiceId').val(evinvId);
                      $("#assignbut").click();
    
            }
            function cancleInit(keyval,keyval2) {
                $("#confirmdialog").data('keyval', keyval).data('keyval2', keyval2).dialog('open');
                $("#confirmdialog").html('<br><b><font size="3" color="red"><center>Please confirm to cancle selected invoice : ' + keyval + '');
                return false;
            }
            
            function cancleNow(invNo,storNo) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/CancleeditViewSale',
                    data: {invNo: invNo,storNo:storNo},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
//                        alert("cancle going pending");
                        if (data.success) {
                            $("#dialogbox").dialog('open');
                            $("#dialogbox").html('<br><b><font size="3" color="green"><center>' + data.message + ' ');
                        } else {
                            $("#dialogbox").dialog('open');
                            $("#dialogbox").html('<br><b><font size="3" color="red"><center>' + data.message + ' ');
                        }
                        
                        jQuery("#gridtable").trigger("reloadGrid");
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });

            }
            $.subscribe('onclicksearch', function(event, data) {

                var invNo = $('#invNo').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {invNo: invNo, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });





        </script>
        <div class="body_content" id="includedContent">
            <div class="AddUser_box "> 
                <div class="watermark"></div>
                <div class="heading">Edit & View Sale</div>



                <div class="contentcenter">
                    <s:div id="message">
                        <i style="color: red">  <s:actionerror theme="jquery"/></i>
                        <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                    </s:div>
                        
                   <s:form action="PrintInvoiceeditViewSale" theme="simple" >
                                <s:hidden id="pdfinvoiceId" name="pdfinvoiceId" />
                                <s:submit button="true"  id="assignbut" cssStyle="display: none; visibility: hidden;"  />
                    </s:form>     
                    <s:form id="searchForm" method="post">         
                        <table class="form_table">
                            <tr>
                                <td class="content_td formLable">Invoice Number</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:textfield name="invNo"  id="invNo" cssClass="textField" /> </td>
                            </tr>


                            <tr>                                
                                <td colspan="4" align="left">
                                    <sj:a  id="searchbut"   button="true" onClickTopics="onclicksearch"  
                                           cssClass="button_asearch ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"   
                                           role="button" aria-disabled="false" >Search</sj:a>

                                </tr>

                            </table>

                    </s:form>
                </div>  

                <div id="divmsg">

                </div>

                <div class="viewuser_tbl">
                    <div id="tablediv">    
                        <sj:dialog 
                            id="dialogbox" 
                            buttons="{ 'OK':function() { $( this ).dialog( 'close' );} }"  
                            autoOpen="false" 
                            modal="true" 
                            title="Delete user" 
                            width="400"
                            height="150"
                            position="center"
                            />
                           <sj:dialog 
                                id="confirmdialog" 
                                buttons="{ 
                                'OK':function() { cancleNow($(this).data('keyval'),$(this).data('keyval2'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Cancle confirmation"
                                width="400"
                                height="150"
                                position="center"
                                />
                        <s:url var="listurl" action="ListeditViewSale"/>
                        <sjg:grid
                            id="gridtable"
                            caption="View Sales"
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

                            <sjg:gridColumn name="evstorId" index="STOR_ID" title="Invoice Id" hidden="true" />  
                            <sjg:gridColumn name="evinvId" index="INV_ID" title="Invoice Id"  align="left" width="10"  sortable="true"/>                        
                            <sjg:gridColumn name="evcusName" index="CNAME" title="Customer Name"  align="left" width="15"  sortable="true"/>
                            <sjg:gridColumn name="evstorName" index="SNAME" title="Store"  align="left" width="15"  sortable="true"/>
                            <sjg:gridColumn name="evtotal" index="TOTAL" title="Total"  align="left" width="12"  sortable="true"/>    
                            <sjg:gridColumn name="evsaleDate" index="SALE_DATE" title="Sale Date"  align="left" width="8"  sortable="true"/>                         
                           
                            <sjg:gridColumn name="evstatus" index="STATUS" title="Status" align="center" width="8" formatter="statusformatter" sortable="true"/>                    
                            <sjg:gridColumn name="evinvId" index="INV_ID" title="Re Print" align="center" width="8" formatter="reprintformatter" sortable="true"/>  
                            <sjg:gridColumn name="evinvId" index="INV_ID" title="Cancle Sale" align="center" width="8" formatter="rejectformatter" sortable="true"/>  


                            
                        </sjg:grid> 

                    </div> 
                </div>



            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 

