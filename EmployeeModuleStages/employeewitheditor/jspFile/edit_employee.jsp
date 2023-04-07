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
            int mtp = 7, submtp = 55;
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
        if (message != null && (message.toLowerCase()).indexOf("success") != -1) 
        {
            clsmessage = "updated-msg";           
        }
            String filename = "";
            if (session.getAttribute("FILENAME") != null)
            {
                filename = (String)session.getAttribute("FILENAME");
                if(filename != null && !filename.equals(""))
                    filename = employee.getMainPath("view_employee_file")+filename;
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
        <script type="text/javascript" src="../js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
        <script>
            tinyMCE.init({
                // General options
                remove_script_host: false,
                relative_urls: false,
                forced_root_block: false,
                force_br_newlines: true,
                force_p_newlines: false,

                mode: "exact",
                elements: "summernote_1",
                theme: "advanced",
                plugins: "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",
                theme_advanced_buttons1: "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
                theme_advanced_buttons2: "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
                theme_advanced_buttons3: "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
                //theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft",
                theme_advanced_toolbar_location: "top",
                theme_advanced_toolbar_align: "left",
                theme_advanced_statusbar_location: "bottom",
                theme_advanced_resizing: true,
                content_css: "css/content.css",
                template_external_list_url: "lists/template_list.js",
                external_link_list_url: "lists/link_list.js",
                external_image_list_url: "lists/image_list.js",
                media_external_list_url: "lists/media_list.js",

                // Style formats
                style_formats: [
                    {title: 'Bold text', inline: 'b'},
                    {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
                    {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
                    {title: 'Example 1', inline: 'span', classes: 'example1'},
                    {title: 'Example 2', inline: 'span', classes: 'example2'},
                    {title: 'Table styles'},
                    {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
                ],

                // Replace values for the template plugin
                template_replace_values: {
                    username: "Some User",
                    staffid: "991234"
                }
            });
        </script>
    </head>
    <body data-sidebar="dark" data-keep-enlarged="true" class="vertical-collpsed">
    <html:form action="/employee/EmployeeAction.do" onsubmit="return false;" styleClass="form-horizontal" enctype="multipart/form-data">
        <html:hidden property="employeeId"/>
        <html:hidden property="doSave"/>
        <html:hidden property="doCancel"/>
        <html:hidden property="search"/>
        <html:hidden property="deschidden"/>
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
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-4 form_group">
                                                    <label class="form_label">Upload a file(if any) (.pdf/.jpeg/.png)</label>                                                   
                                                    <html:file property="langfile" styleId="upload1" onchange="javascript: setClass('1');"/>
                                                    <a href="javascript:;" id="upload_link_1" class="attache_btn uploaded_img1"><i class="fas fa-paperclip"></i> Attach</a>
                                                    <% if(!filename.equals("")) {%><div class="down_prev"  id='preview_1'><a href="javascript:;" data-bs-toggle="modal" data-bs-target="#view_pdf" onclick="javascript:setIframeedit('<%=filename%>');">Preview</a></div><% } %>
                                            </div> 
                                            <div class="form-group form-horizontal">
                                                <label class="control-label col-md-3">Share your feedback <span class="required" aria-required="true"></span></label>
                                                <div class="col-md-6 control-label3"><html:textarea property="description" styleId="summernote_1" style=" width:1000px; height: 400px;" /></div>
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
                function setIframeedit(uval)
                {
                    var url_v = "", classname = "";
                    if (uval.includes(".doc") || uval.includes(".docx") || uval.includes("wordprocessingml.document")) 
                    {
                        url_v = "https://docs.google.com/gview?url=" + uval + "&embedded=true";
                        classname = "doc_mode";
                    }
                    else if (uval.includes(".pdf"))
                    {
                        url_v = uval;
                        classname = "pdf_mode";
                    }
                    else
                    {
                        url_v = uval;
                        classname = "img_mode";
                    }
                    window.top.$('#iframe').attr('src', 'about:blank');
                    setTimeout(function () {
                        window.top.$('#iframe').attr('src', url_v);
                         document.getElementById("iframe").className=classname;
                         document.getElementById("diframe").href =uval;
                    }, 1000);

                    $("#iframe").on("load", function() {
                            let head = $("#iframe").contents().find("head");
                            let css = '<style>img{margin: 0px auto;}</style>';
                            $(head).append(css);
                        });
                }
            </script>
        
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
        
        <script>
                $(function () {
                    $("#upload_link_1").on('click', function (e) {
                        e.preventDefault();
                        $("#upload1:hidden").trigger('click');
                    });
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
