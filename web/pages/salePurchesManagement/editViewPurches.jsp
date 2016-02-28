<%-- 
    Document   : editViewPurches
    Created on : Feb 7, 2016, 9:40:22 AM
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
                    return "<a href='#' onClick='reprintInit(&#34;" + rowObject.evpurId + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/print.png'  /></a>"; 
                } else {
                    var html = "<img src= '${pageContext.request.contextPath}/resources/images/iconInactive.png' />";
                }
                return html;
            }
            
            function rejectformatter(cellvalue, options, rowObject) {
                if (rowObject.evstatus == '01') {
                    return "<a href='#' onClick='cancleInit(&#34;" + rowObject.evpurId + "&#34;,&#34;" + rowObject.evstorId + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/reject.png'  /></a>"; 
                } else {
                    var html = "<img src= '${pageContext.request.contextPath}/resources/images/rejectDisabled.png' />";
                }
                return html;
            }
            function reprintInit(evpurId){
                      $('#pdfpurchId').val(evpurId);
                      $("#assignbut").click();
    
            }
            function cancleInit(keyval,keyval2) {
                $("#confirmdialog").data('keyval', keyval).data('keyval2', keyval2).dialog('open');
                $("#confirmdialog").html('<br><b><font size="3" color="red"><center>Please confirm to cancle selected invoice : ' + keyval + '');
                return false;
            }
            
            function cancleNow(purNo,storNo) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/CancleeditViewPurches',
                    data: {purNo: purNo,storNo:storNo},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
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

                var purNo = $('#purNo').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {purNo: purNo, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });





        </script>
        <div class="body_content" id="includedContent">
            <div class="AddUser_box "> 
                <div class="watermark"></div>
                <div class="heading">Edit & View Purchase</div>



                <div class="contentcenter">
                    <s:div id="message">
                        <i style="color: red">  <s:actionerror theme="jquery"/></i>
                        <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                    </s:div>
                        
                   <s:form action="PrintInvoiceeditViewPurches" theme="simple" >
                                <s:hidden id="pdfpurchId" name="pdfpurchId" />
                                <s:submit button="true"  id="assignbut" cssStyle="display: none; visibility: hidden;"  />
                    </s:form>     
                    <s:form id="searchForm" method="post">         
                        <table class="form_table">
                            <tr>
                                <td class="content_td formLable">Purchase Number</td>
                                <td class="content_td formLable">:</td>
                                <td colspan="2"><s:textfield name="purNo"  id="purNo" cssClass="textField" /> </td>
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
                            title="cancle purchease" 
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
                        <s:url var="listurl" action="ListeditViewPurches"/>
                        <sjg:grid
                            id="gridtable"
                            caption="View Purchase"
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

                            <sjg:gridColumn name="evstorId" index="STOR_ID" title="Purchase Id" hidden="true" />  
                            <sjg:gridColumn name="evpurId" index="PUR_ID" title="Purchase Id"  align="left" width="10"  sortable="true"/>                        
                            <sjg:gridColumn name="evsupName" index="SUPNAME" title="Supplier Name"  align="left" width="15"  sortable="true"/>
                            <sjg:gridColumn name="evstorName" index="STRNAME" title="Store"  align="left" width="15"  sortable="true"/>
                            <sjg:gridColumn name="evtotal" index="TOTAL" title="Total"  align="left" width="12"  sortable="true"/>    
                            <sjg:gridColumn name="evpurDate" index="PURCH_DATE" title="Purchase Date"  align="left" width="8"  sortable="true"/>                         
                           
                            <sjg:gridColumn name="evstatus" index="STATUS" title="Status" align="center" width="8" formatter="statusformatter" sortable="true"/>                    
                            <sjg:gridColumn name="evpurId" index="PUR_ID" title="Re Print" align="center" width="8" formatter="reprintformatter" sortable="true"/>  
                            <sjg:gridColumn name="evpurId" index="PUR_ID" title="Cancle Purchase" align="center" width="8" formatter="rejectformatter" sortable="true"/>  


                            
                        </sjg:grid> 

                    </div> 
                </div>



            </div>              

        </div>
    </div>

    <jsp:include page="../../footer.jsp" /> 

