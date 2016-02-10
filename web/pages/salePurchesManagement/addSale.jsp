<%-- 
    Document   : addSale
    Created on : Feb 7, 2016, 9:41:10 AM
    Author     : kreshan88
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<html>
    <head>
        
        <jsp:include page="/Styles.jsp" />
        <script type="text/javascript">
//            $.subscribe('resetButton', function(event, data) {
//                $('#divmsg').empty();
//                resetData();
//            });
            
            function resetData(){
                
                $('#custName').val("");
                $('#address').val("");
                $('#email').val("");
                $('#tpMobile').val("");
                $('#tpOffice').val("");
            }
            function loadItemDetail() {
                    var itemCode=$('#itemCode').val();
                    $.ajax({
                        url: '${pageContext.request.contextPath}/loadItemDetailaddSale',
                        data: {itemCode: itemCode},
                        dataType: "json",
                        type: "POST",
                        success: function(data) { 
                            if(data.itemfind){    
                                $('#itemName').attr('readOnly', true);
                                $('#itemName').val(data.itemName);
                                $('#itemQut').val(data.itemQut);
                                $('#itemPrize').val(data.itemPrize);
                            }else{
                                $("#dialogbox").dialog('open');
                                $("#dialogbox").html('<br><b><font size="3" color="red"><center> Barcode:' + data.itemCode + ' not founf ');
                            }
                        },
                        error: function(data) {
                            window.location = "${pageContext.request.contextPath}/logoutCall.action?";
                        }
                    });
                }
                 function addItem() {
                    var invoiceId=$('#invoiceId').val();
                    var storId=$('#storId').val();
                    var custId=$('#custId').val();
                    var itemCode=$('#itemCode').val();
                    var itemName=$('#itemName').val();
                    var itemQut=$('#itemQut').val();
                    var itemPrize=$('#itemPrize').val();

                    $.ajax({
                        url: '${pageContext.request.contextPath}/addItemaddSale',
                        data: {invoiceId: invoiceId,storId:storId,custId:custId,itemCode:itemCode,itemName:itemName,itemQut:itemQut,itemPrize:itemPrize},
                        dataType: "json",
                        type: "POST",
                        success: function(data) { 
                            if(data.itemadd){    
                                $('#itemCode').val("");
                                $('#itemName').val("");
                                $('#itemQut').val("");
                                $('#itemPrize').val("");
                            }else{
                                $("#dialogbox").dialog('open');
                                $("#dialogbox").html('<br><b><font size="3" color="red"><center> Adding Item ' + data.itemCode + ' fail ');
                            }
                            
                            $("#gridtable").jqGrid('setGridParam', {postData: {invoiceId: data.invoiceId}});
                            jQuery("#gridtable").trigger("reloadGrid");
                        },
                        error: function(data) {
                            window.location = "${pageContext.request.contextPath}/logoutCall.action?";
                        }
                    });
                }


            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteInit(&#34;" + rowObject.invId + "&#34;,&#34;" + rowObject.itemNo + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/iconDelete.png'  /></a>";
            }
            function deleteInit(keyval1,keyval2) {
                $("#deletedialog").data('keyval1', keyval1).data('keyval2', keyval2).dialog('open');
                $("#deletedialog").html('<br><b><font size="3" color="red"><center>Please confirm to delete select item : ' + keyval2 + '');
                return false;
            }
            
            function deleteNow(dinvoNo,ditemNo) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteaddSale',
                    data: {dinvoNo: dinvoNo,ditemNo:ditemNo},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        
                        $("#gridtable").jqGrid('setGridParam', {postData: {invoiceId: data.dinvoNo}});
                        jQuery("#gridtable").trigger("reloadGrid");
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/logoutCall.action";
                    }
                });

            }
            
            
            function SubmitInvoice() {
                    var invoiceId=$('#invoiceId').val();
                    alert(invoiceId)
                    $.ajax({
                        url: '${pageContext.request.contextPath}/SubmitInvoiceaddSale',
                        data: {invoiceId: invoiceId},
                        dataType: "json",
                        type: "POST",
                        success: function(data) { 
                            alert("done submit= do print");
                            $('#invoiceId').val(data.invoiceId);
                            $("#assignbut").click();
//                            if(data.itemfind){    
//                                $('#itemName').attr('readOnly', true);
//                                $('#itemName').val(data.itemName);
//                                $('#itemQut').val(data.itemQut);
//                                $('#itemPrize').val(data.itemPrize);
//                            }else{
//                                $("#dialogbox").dialog('open');
//                                $("#dialogbox").html('<br><b><font size="3" color="red"><center> Barcode:' + data.itemCode + ' not founf ');
//                            }
                        },
                        error: function(data) {
                            window.location = "${pageContext.request.contextPath}/logoutCall.action?";
                        }
                    });
                }
            
        </script>
    </head>
    <body style="overflow:hidden">
        <div class="wrapper">

            <jsp:include page="../../header.jsp" /> 

            <div class="body_content" id="includedContent">
                    <div class="watermark"></div>
                    <div class="heading">Add New Sale</div>
                   <div class="AddUser_box "> 
                    
                    <div class="message" >   
                        <s:div id="divmsg">
                            <i style="color: red">  <s:actionerror theme="jquery"/></i>
                            <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                        </s:div>
                    </div>

                            
                    <div class="contentcenter">
                        <s:form action="PrintInvoiceaddSale" theme="simple" >
                                <s:hidden id="invoiceId" name="invoiceId" />
                                <s:submit button="true"  id="assignbut" cssStyle="display: none; visibility: hidden;"  />
                        </s:form>
                        
                        <s:form theme="simple" method="post"  name="addcus" id="addcus">         
                            <table class="form_table" border="0px">
                                
                                <tr>
                                    <td class="content_td formLable">Invoice Number<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="invoiceId" name="invoiceId" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Store</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:select  name="storId" id="storId" headerKey="1" 
                                               list="%{storIdList}" listKey="key" listValue="value"  cssClass="dropdown" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Customer</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:select  name="custId" id="custId" headerKey="1" 
                                               list="%{custIdList}" listKey="key" listValue="value"  cssClass="dropdown" /></td>
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Item Number<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="itemCode" name="itemCode" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">
                                        <s:submit button="true" value="Load" onclick="loadItemDetail()"  targets="divmsg"  cssClass="button_ssave" />
                                    </td>      
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Item Name<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="itemName" name="itemName" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Item Quantity</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="itemQut" name="itemQut" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Unit Prize</td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="itemPrize" name="itemPrize" cssClass="textField" /></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">
                                        <s:submit button="true" value="Add" onclick="addItem()"  targets="divmsg"  cssClass="button_ssave" />
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td class="content_td formLable" colspan="7"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                                
                                <tr>
                                    <td colspan="3">
                                        <s:submit button="true" value="Submit" onclick="SubmitInvoice()"  targets="divmsg"  cssClass="button_ssave" />
                                        <%--<s:url var="addurl" action="PrintInvoiceaddSale"/>--%>
                                        <sj:submit button="true" value="Print Invoice"  targets="divmsg"  cssClass="button_ssave" /> 
                                    <td colspan="4"></td>
                                </tr>
                                </table>

                        </s:form>
                        
                    </div>
                       
                       <div class="viewuser_tbl">
                        <div id="tablediv">
                                <sj:dialog 
                                id="deletedialog" 
                                buttons="{ 
                                'OK':function() { deleteNow($(this).data('keyval1'),$(this).data('keyval2'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Delete confirmation"
                                width="400"
                                height="150"
                                position="center"
                                />
                                
                                <sj:dialog 
                                id="dialogbox" 
                                buttons="{'OK':function() { $( this ).dialog( 'close' );}}"  
                                autoOpen="false" 
                                modal="true" 
                                title="Sale Detail" 
                                width="400"
                                height="150"
                                position="center"
                                />
                                <s:url var="listurl" action="ListaddSale"/>
                                <sjg:grid
                                    id="gridtable"
                                    caption="Selected Item List"
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

                                    <sjg:gridColumn name="invId"  title="invoice id"  hidden="true"/> 
                   
                                    <sjg:gridColumn name="itemNo" index="ITEM_NO" title="Barcode"  align="left" width="10"  sortable="true"/>                        
                                    <sjg:gridColumn name="count" index="COUNT" title="Quantity"  align="left" width="15"  sortable="true"/>
                                    <sjg:gridColumn name="unitPrize" index="UNIT_PRIZE" title="Unit Prize"  align="left" width="15"  sortable="true"/>
                                    <sjg:gridColumn name="totalPrize" index="UNIT_PRIZE" title="Total Prize"  align="left" width="12"  sortable="true"/>

                                    <sjg:gridColumn name="itemNo"  title="Delete" align="center" width="10" align="center"   formatter="deleteformatter" sortable="false" />

                                </sjg:grid> 
                            </div>
                        </div>
                </div>
            </div><!--end of body_content-->





            <jsp:include page="../../footer.jsp" /> 



        </div> 

    </body>
</html>

