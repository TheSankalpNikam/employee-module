<%@page language="java"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.web.jxp.user.UserInfo"%>
<%@page import="com.web.jxp.base.StatsInfo"%>
<%@page import="com.web.jxp.employee.EmployeeInfo"%>
<%@taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<jsp:useBean id="employee" class="com.web.jxp.employee.Employee" scope="page"/>
<!doctype html>
<html lang="en">
<%
    try
    {
        int mtp = 7, submtp = 55;
        String per = "N", addper = "N", editper = "N", deleteper = "N";
        if (session.getAttribute("LOGININFO") == null)
        {
%>
            <jsp:forward page="/index1.jsp"/>
<%
        }
        else
        {
            UserInfo uinfo = (UserInfo) session.getAttribute("LOGININFO");
            if(uinfo != null)
            {
                per = uinfo.getPermission() != null ? uinfo.getPermission() : "N";
                addper = uinfo.getAddper() != null ? uinfo.getAddper() : "N";
                editper = uinfo.getEditper() != null ? uinfo.getEditper() : "N";
                deleteper = uinfo.getDeleteper() != null ? uinfo.getDeleteper() : "N";
            }
        }
        EmployeeInfo info = null;
        if(request.getAttribute("EMPLOYEE_DETAIL") != null)
            info = (EmployeeInfo)request.getAttribute("EMPLOYEE_DETAIL");
            
            String file_path = employee.getMainPath("view_employee_file");
            ArrayList lang_list = new ArrayList();
        if(request.getAttribute("EMPLOYEELIST") != null)
            lang_list = (ArrayList) request.getAttribute("EMPLOYEELIST");
        int lang_list_size = lang_list.size();

        
%>  
<head>
    <meta charset="utf-8">
    <title><%= employee.getMainPath("title") != null ? employee.getMainPath("title") : "" %></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- App favicon -->
    <link rel="shortcut icon" href="../assets/images/favicon.png">
    <!-- Bootstrap Css -->
    <link href="../assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css">
    <link href="../assets/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css" />
    <!-- Icons Css -->
    <link href="../assets/css/icons.min.css" rel="stylesheet" type="text/css">
    <!-- App Css-->
    <link href="../assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css">
    <link href="../assets/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="../jsnew/common.js" type="text/javascript"></script>
    <script type="text/javascript" src="../jsnew/employee.js"></script>
</head>
<body data-sidebar="dark" data-keep-enlarged="true" class="vertical-collpsed">
<html:form action="/employee/EmployeeAction.do" onsubmit="return false;" styleClass="form-horizontal">
<html:hidden property="doCancel"/>
<html:hidden property="search"/>
    <!-- Begin page -->
    <div id="layout-wrapper">
        <%@include file="../header.jsp" %>
        <%@include file="../sidemenu.jsp" %>
        <!-- Start right Content -->
        <div class="main-content">
            <div class="page-content">
                <div class="row head_title_area">
                    <div class="col-md-12 col-xl-12">
                        <div class="float-start"><a href="javascript: goback();" class="back_arrow"><img  src="../assets/images/back-arrow.png"/> View Employee</a></div>
                        <div class="float-end">                            
                            <div class="toggled-off usefool_tool">
                                <div class="toggle-title">
                                    <img src="../assets/images/left-arrow.png" class="fa-angle-left"/>
                                    <img src="../assets/images/right-arrow.png" class="fa-angle-right"/>

                                </div>
                                <!-- end toggle-title --->
                                <div class="toggle-content">
                                    <h4>Useful Tools</h4>
                                    <%@include file ="../shortcutmenu.jsp"%>
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
<%
                                    if(info != null)
                                    {
%>
                                    <div class="row col-lg-12">
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                            <label class="form_label">Name</label>
                                            <span class="form-control"><%= (info.getName() != null && !info.getName().equals("")) ? info.getName() : "&nbsp;" %></span>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                            <label class="form_label">Last Name</label>
                                            <span class="form-control"><%= (info.getLastname()!= null && !info.getLastname().equals("")) ? info.getLastname(): "&nbsp;" %></span>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                            <label class="form_label">Email id</label>
                                            <span class="form-control"><%= (info.getEmailid()!= null && !info.getEmailid().equals("")) ? info.getEmailid(): "&nbsp;" %></span>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                            <label class="form_label">Number</label>
                                            <span class="form-control"><%= (info.getNumber()!= null && !info.getNumber().equals("")) ? info.getNumber(): "&nbsp;" %></span>
                                        </div>
                                        
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                                <label class="form_label">Country</label>
                                                    <span class="form-control"><%= info.getCountryName() != null && !info.getCountryName().equals("") ? info.getCountryName() : "&nbsp;" %></span>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                                <label class="form_label">City</label>
                                                    <span class="form-control"><%= info.getCityName() != null && !info.getCityName().equals("") ? info.getCityName() : "&nbsp;" %></span>
                                            </div>
                                            <div class="row">
                                            <div class="col-md-12 form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-md-2 bold">Share your feedback:</label>
                                                <div class="col-md-10 control-label2">
                                                    <span></span>
                                                    <span><%= info.getDescription() != null && !info.getDescription().equals("") ? info.getDescription() : "NA" %></span>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                        </div>                    
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-12 form_group">
                                            <label class="form_label">Status</label>
                                            <span class="form-control"><%= employee.getStatusById(info.getStatus()) %></span>
                                        </div>
                                        <%
                                                                                if(lang_list_size > 0)
                                                                                {
                                                                                        EmployeeInfo ainfo;
                                                                                    String filename1;
                                                                                    for(int i = 0; i < lang_list_size; i++)
                                                                                    {
                                                                                        ainfo = (EmployeeInfo) lang_list.get(i);
                                                                                        if(ainfo != null)
                                                                                        {
                                                                                            filename1 = ainfo.getEmpfilename() != null ? ainfo.getEmpfilename() : "";

                                        %>
                                        <% if(!ainfo.getEmpfilename().equals("")) {%><a href="javascript:;" class="mr_15" data-bs-toggle="modal" data-bs-target="#view_pdf" onclick="javascript:setIframe('<%=file_path+ainfo.getEmpfilename() %>');"><img src="../assets/images/attachment.png"/> </a><% } else { %><a href='javascript:;'><span style='width: 35px;'>&nbsp;</span></a><% } %>
                                    </div>                                    
<%                                               }
                                            }
                                    }
%>
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
    <%@include file="../footer.jsp" %>
    
<!--    <div id="view_pdf" class="modal fade" tabindex="-1" data-bs-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close close_modal_btn pull-right" data-bs-dismiss="modal" aria-hidden="true"><i class="ion ion-md-close"></i></button>
                            <span class="resume_title"> File</span>
                            <a id='diframe' href="" class="down_btn" download=""><img src="../assets/images/download.png"/></a>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <iframe id='iframe' class="doc" src=""></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>-->
    
    <!-- JAVASCRIPT -->
    <script src="../assets/libs/jquery/jquery.min.js"></script>		
    <script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script> 
    <script src="../assets/libs/metismenu/metisMenu.min.js"></script>
    <script src="../assets/js/app.js"></script>	
    <script src="../assets/js/bootstrap-multiselect.js" type="text/javascript"></script>
    <script>
        // toggle class show hide text section
        $(document).on('click', '.toggle-title', function () {
            $(this).parent()
            .toggleClass('toggled-on')
            .toggleClass('toggled-off');
        });
    </script>
    <script>
                $(function () {
                    $("#upload_link1").on('click', function (e) {
                        e.preventDefault();
                        $("#upload1:hidden").trigger('click');
                    });
                });
            </script>
</html:form>
</body>
<%
}
}
catch(Exception e)
{
    e.printStackTrace();
}
%>
</html>