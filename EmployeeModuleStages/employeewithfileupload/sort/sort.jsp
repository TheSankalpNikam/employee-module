<%@page contentType="text/html"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.web.jxp.user.UserInfo" %>
<%@page import="com.web.jxp.employee.EmployeeInfo" %>
<jsp:useBean id="employee" class="com.web.jxp.employee.Employee" scope="page"/>
<jsp:useBean id="vobj" class="com.web.jxp.base.Validate" scope="page"/>
<%
    try {
        if (session.getAttribute("LOGININFO") != null) {
            UserInfo uinfo = ((UserInfo) request.getSession().getAttribute("LOGININFO"));
            String per = "", editper = "N", deleteper = "N";
            if (uinfo != null) {
                per = uinfo.getPermission() != null ? uinfo.getPermission() : "N";
                editper = uinfo.getEditper() != null ? uinfo.getEditper() : "N";
                deleteper = uinfo.getDeleteper() != null ? uinfo.getDeleteper() : "N";
            }
            if (request.getParameter("col") != null) {
                StringBuilder str = new StringBuilder();
                String colid = request.getParameter("col") != null ? vobj.replaceint(request.getParameter("col")) : "";
                String updowns = request.getParameter("updown") != null && !request.getParameter("updown").equals("") ? vobj.replaceint(request.getParameter("updown")) : "0";
                String nextValue = request.getParameter("nextValue") != null && !request.getParameter("nextValue").equals("") ? vobj.replaceint(request.getParameter("nextValue")) : "0";
                int n = Integer.parseInt(nextValue);
                int updown = Integer.parseInt(updowns);
                int next_value = n;
                n = n - 1;

                session.setAttribute("NEXTVALUE", next_value + "");
                ArrayList employee_list = new ArrayList();
                if (session.getAttribute("EMPLOYEE_LIST") != null) {
                    employee_list = (ArrayList) session.getAttribute("EMPLOYEE_LIST");
                }
                int total = employee_list.size();
                if (total > 0) {
                employee_list = employee.getFinalRecord(employee_list, colid, updown);
                session.setAttribute("EMPLOYEE_LIST", employee_list);
                
                    int status;
                    EmployeeInfo info;
                    for (int i = 0; i < total; i++) {
                        info = (EmployeeInfo) employee_list.get(i);
                        if (info != null) {
                            status = info.getStatus();
                            str.append("<tr class='hand_cursor' href='javascript: void(0);'>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + (info.getName() != null ? info.getName() : "") + "</td>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + (info.getLastname() != null ? info.getLastname() : "") + "</td>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + (info.getEmailid()!= null ? info.getEmailid() : "") + "</td>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + (info.getNumber()!= null ? info.getNumber() : "") + "</td>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + (info.getCountryName()!= null ? info.getCountryName() : "") + "</td>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + (info.getCityName()!= null ? info.getCityName() : "") + "</td>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + (info.getDescription()!= null ? info.getDescription() : "") + "</td>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + (info.getEmpfilename()!= null ? info.getEmpfilename() : "") + "</td>");
                            str.append("<td class='hand_cursor' href='javascript: void(0);' onclick=\"javascript: showDetail('"+ info.getEmployeeId() +"');\">" + employee.getStatusById(info.getStatus()) + "</td>");
                            str.append("<td>");
                            str.append("<span class='switch_bth float-end'>");
                            str.append("<div class='form-check form-switch'>");
                            str.append("<input class='form-check-input' type='checkbox' role='switch' id='flexSwitchCheckDefault_" + (i) + "'");
                            if (!editper.equals("Y")) {
                                str.append("disabled='true'");
                            }
                            if (status == 1) {
                                str.append("checked");
                            }
                            str.append(" onclick=\"javascript: deleteForm('" + info.getEmployeeId() + "', '" + status + "', '" + (i) + "');\" />");
                            str.append("</div>");
                            str.append("</span>");
                            if (editper.equals("Y") && info.getStatus() == 1) {
                                str.append("<a href=\"javascript: modifyForm('" + info.getEmployeeId() + "');\" class='edit_mode float-end mr_15'><img src='../assets/images/pencil.png'/></a>");
                            }
                            str.append("</td>");
                            str.append("</tr>");
                        }
                    }
                }
                String st = str.toString();
                response.getWriter().write(st);
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } else {
            response.getWriter().write("Please check your login session....");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>