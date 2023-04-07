<%@page contentType="text/html"%>
<%@page language="java" %>
<%@taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"  %>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"  %>
<%@taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@page import="com.web.jxp.user.UserInfo" %>
<%@page import="com.web.jxp.employee.EmployeeInfo" %>
<%@page import="java.util.ArrayList" %>
<jsp:useBean id="employee" class="com.web.jxp.employee.Employee" scope="page"/>
<!doctype html>
<html lang="en">
    <%
        try {
            int mtp = 7, submtp = 5;
            if (session.getAttribute("LOGININFO") == null) {
    %>
    <jsp:forward page="/index1.jsp"/>
    <%
        }
        String message = "", clsmessage = "red_font";
        if (request.getAttribute("MESSAGE") != null) {
            message = (String) request.getAttribute("MESSAGE");
            request.removeAttribute("MESSAGE");
        }
        if (message != null && (message.toLowerCase()).indexOf("success") != -1) {
            clsmessage = "updated-msg";
        }
    %>
    <head>
        <meta charset="utf-8">
        <title><%= employee.getMainPath("title") != null ? employee.getMainPath("title") : ""%></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- App favicon -->
        <link rel="shortcut icon" href="../assets/images/favicon.png">
        <!-- Bootstrap Css -->
        <link href="../assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css">
        <link href="../assets/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css" />
        <link href="../assets/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="../autofill/jquery-ui.min.css" />  <!-- Autofill-->
        <!-- Icons Css -->
        <link href="../assets/css/icons.min.css" rel="stylesheet" type="text/css">
        <!-- App Css-->
        <link href="../assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css">
        <link href="/jxp/assets/css/minimal.css" rel="stylesheet">
        <link href="../assets/css/style.css" rel="stylesheet" type="text/css">
        <script src="../jsnew/common.js" type="text/javascript"></script>
        <script type="text/javascript" src="../jsnew/validation.js"></script>
        <script type="text/javascript" src="../jsnew/validation.js"></script>
        <script type="text/javascript" src="../jsnew/employee.js"></script>
    </head>
    <body data-sidebar="dark" data-keep-enlarged="true" class="vertical-collpsed">
    <html:form action="/employee/EmployeeAction.do" onsubmit="return false;" styleClass="form-horizontal" enctype="multipart/form-data">
        <html:hidden property="employeeId"/>
        <html:hidden property="doSave"/>
        <html:hidden property="doCancel"/>
        <html:hidden property="search"/>   
        <!-- Begin page -->
        <div id="layout-wrapper">
            <%@include file ="../header.jsp"%>
            <%@include file ="../sidemenu.jsp"%>
            <!-- Start right Content -->
            <div class="main-content">
                <div class="page-content">
                    <div class="row head_title_area">
                        <div class="col-md-12 col-xl-12">
                            <div class="float-start">
                                <a href="javascript:goback();" class="back_arrow">
                                    <img  src="../assets/images/back-arrow.png"/> 
                                    <c:choose>
                                        <c:when test="${employeeForm.employeeId <= 0}">
                                            Add Employee
                                        </c:when>
                                        <c:otherwise>
                                            Edit Employee
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                            <div class="float-end">                                    
                                <div class="toggled-off usefool_tool">
                                    <div class="toggle-title">
                                        <img src="../assets/images/left-arrow.png" class="fa-angle-left"/>
                                        <img src="../assets/images/right-arrow.png" class="fa-angle-right"/>
                                    </div>
                                    <!-- end toggle-title --->
                                    <div class="toggle-content">
                                        <h4>Useful Tools</h4>
                                        <%@include file ="../shortcutmenu_edit.jsp"%>
                                    </div>
                                </div>
                            </div>
                        </div>	
                    </div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12 col-xl-12">
                                <div class="body-background">
                                    <div class="row d-none1">
                                        <div class="col-lg-12">
                                            <% if (!message.equals("")) {%><div class="sbold <%=clsmessage%>">
                                                <%=message%>
                                            </div><% } %>
                                            <div class="main-heading">
                                                <div class="add-btn">
                                                    <h4>&nbsp;</h4>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="row col-lg-12">											
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                                <label class="form_label">Name</label>
                                                <html:text property="name" styleId="name" styleClass="form-control" maxlength="100"/>
                                                <script type="text/javascript">
                                                    document.getElementById("name").setAttribute('placeholder', '');
                                                </script>
                                            </div>
                                            <div class="col-lg-8 col-md-8 col-sm-8 col-12 form_group">
                                                <label class="form_label">Last Name</label>
                                                <html:text property="lastname" styleId="lastname" styleClass="form-control" maxlength="500"/>
                                                <script type="text/javascript">
                                                    document.getElementById("lastname").setAttribute('placeholder', '');
                                                </script>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                                <label class="form_label">Email id</label>
                                                <html:text property="emailid" styleId="emailid" styleClass="form-control" maxlength="100"/>
                                                <script type="text/javascript">
                                                    document.getElementById("emailid").setAttribute('placeholder', '');
                                                </script>
                                            </div> 
                                            <div class="col-lg-8 col-md-8 col-sm-8 col-12 form_group">
                                                <label class="form_label">Number</label>
                                                <html:text property="number" styleId="number" styleClass="form-control" maxlength="10"/>
                                                <script type="text/javascript">
                                                    document.getElementById("number").setAttribute('placeholder', '');
                                                </script>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                                <label class="form_label">Country</label>
                                                <html:select property="countryId" styleId="countryId" styleClass="form-select" onchange="javascript: clearcity();" >
                                                <html:optionsCollection filter="false" property="countries" label="ddlLabel" value="ddlValue">
                                                </html:optionsCollection>
                                                </html:select>
                                            </div>
                                            <div class="col-lg-8 col-md-8 col-sm-8 col-12 form_group">
                                                <label class="form_label">City</label>
                                                <html:hidden property="cityId" />
                                                <html:text property="cityName" styleId="cityName" styleClass="form-control" maxlength="100" onblur="if (this.value == '') {document.forms[0].cityId.value='0';}"/>
                                                <script type="text/javascript">
                                                    document.getElementById("cityName").setAttribute('placeholder', '');
                                                    document.getElementById("cityName").setAttribute('autocomplete', 'off');
                                                </script>
                                            </div>    
                                        </div>									
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-12 form_group" id="submitdiv">
                                            <a href="javascript:submitForm();"class="save_btn"><img src="../assets/images/save.png"> Save</a>
                                            <a href="javascript:goback();" class="cl_btn"><i class="ion ion-md-close"></i> Close</a>
                                        </div>
                                    </div>
                                                
                                </div>
                            </div>
                        </div> 
                    </div>
                    <!-- End Page-content -->
                </div>
                <!-- end main content-->
            </div>
        </div>
        <!-- END layout-wrapper -->
        <%@include file ="../footer.jsp"%>
        <!-- JAVASCRIPT -->
        <script src="../assets/libs/jquery/jquery.min.js"></script>		
        <script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="../assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="../autofill/jquery-ui.min.js" type="text/javascript"></script> <!-- autofill-->
        <script src="../assets/js/app.js"></script>	
        <script src="../assets/js/bootstrap-multiselect.js" type="text/javascript"></script>
        <script src="../assets/js/bootstrap-select.min.js"></script>
        <script src="../assets/js/bootstrap-datepicker.min.js"></script>
        <script src="/jxp/assets/js/sweetalert2.min.js"></script>
        
        <script>
            $(function ()
            {
                $("#cityName").autocomplete({
                    source: function (request, response) {
                        $.ajax({
                            url: "/jxp/ajax/client/autofillcity.jsp",
                            type: 'post',
                            dataType: "json",
                            data: JSON.stringify({"search": request.term, "countryId": $("#countryId").val()}),
                            success: function (data) {
                                response(data);
                            }
                        });
                    },
                    select: function (event, ui) {
                        //console.log('onHover :: '+JSON.stringify(ui,null,2));					
                        $('#cityName').val(ui.item.label); // display the selected text
                        $('input[name="cityId"]').val(ui.item.value);
                        return false;
                    },
                    focus: function (event, ui)
                    {
                        //console.log('onFocus :: '+JSON.stringify(ui,null,2));					
                        $('#cityName').val(ui.item.label); // display the selected text
                        return false;
                    }
                });
            });
            </script>
        
        <script>
                                                    // toggle class show hide text section
                                                    $(document).on('click', '.toggle-title', function () {
                                                        $(this).parent()
                                                                .toggleClass('toggled-on')
                                                                .toggleClass('toggled-off');
                                                    });
        </script>
    </html:form>
</body>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</html>
