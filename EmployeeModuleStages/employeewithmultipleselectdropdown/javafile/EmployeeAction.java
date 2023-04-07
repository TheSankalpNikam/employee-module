package com.web.jxp.employee;

import com.web.jxp.base.Validate;
import com.web.jxp.city.City;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import static com.web.jxp.common.Common.*;
import com.web.jxp.country.Country;
import com.web.jxp.user.UserInfo;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Stack;
import org.apache.struts.upload.FormFile;

public class EmployeeAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EmployeeForm frm = (EmployeeForm) form;
        Employee employee = new Employee();
        Validate validate = new Validate();


        Country country = new Country();
        City city = new City();

        int count = employee.getCount();
        String search = frm.getSearch() != null && !frm.getSearch().equals("") ? frm.getSearch() : "";
        frm.setSearch(search);
        Collection statuses = employee.getStatuses();
        frm.setStatuses(statuses);
        int statusIndex = frm.getStatusIndex();
        frm.setStatusIndex(statusIndex);
        int uId = 0;
        String permission = "N";
        if (request.getSession().getAttribute("LOGININFO") != null) {
            UserInfo uInfo = (UserInfo) request.getSession().getAttribute("LOGININFO");
            if (uInfo != null) {
                permission = uInfo.getPermission();
                uId = uInfo.getUserId();
            }
        }
        int check_user = employee.checkUserSession(request, 5, permission);
        if (check_user == -1) {
            return mapping.findForward("default");
        } else if (check_user == -2) {
            String authmess = employee.getMainPath("user_auth");
            authmess = authmess.replaceAll("__MODULE__", "Employee Module");
            request.setAttribute("AUTHMESSAGE", authmess);
            return mapping.findForward("auth");
        }
        if (frm.getDoAdd() != null && frm.getDoAdd().equals("yes")) {
            print(this, " doAdd block :: ");
            frm.setStatus(1);
            frm.setEmployeeId(-1);

            Collection countries = country.getCountrys();
            frm.setCountries(countries);

            request.getSession().removeAttribute("PROF_IDs");
            saveToken(request);
            return mapping.findForward("add_employee");
        } else if (frm.getDoModify() != null && frm.getDoModify().equals("yes")) {
            System.out.println("Entered getDoModify block");
            int employeeId = frm.getEmployeeId();
            frm.setEmployeeId(employeeId);
            Collection countries = country.getCountrys();
            frm.setCountries(countries);
            EmployeeInfo info = employee.getEmployeeDetailById(employeeId);
            if (info != null) {

                frm.setName(info.getName());
                frm.setLastname(info.getLastname());
                frm.setEmailid(info.getEmailid());
                frm.setNumber(info.getNumber());
                
                if (info.getGender() != null) {
                    frm.setGender(info.getGender());
                }
                
                frm.setCountryId(info.getCountryId());
                frm.setCityId(info.getCityId());
                frm.setCityName(info.getCityName());
                frm.setDescription(info.getDescription());
                
                if (info.getEmpfilename() != null) {
                    frm.setLangfilehidden(info.getEmpfilename());
                }
                request.getSession().setAttribute("FILENAME", info.getEmpfilename());

                frm.setStatus(info.getStatus());
                
                request.getSession().setAttribute("PROF_IDs", info.getProfColumn());

                if (info.getDescription() != null && !info.getDescription().equals("")) {
                    String add_path = employee.getMainPath("add_resumetemplate_file");
                    frm.setDeschidden(info.getDescription());
                    frm.setDescription(employee.readHTMLFile(info.getDescription(), add_path));
                }


            }
            saveToken(request);
            System.out.println("Getting forward to struts-config");
            return mapping.findForward("add_employee");
        } else if (frm.getDoView() != null && frm.getDoView().equals("yes")) {
            int employeeId = frm.getEmployeeId();
            frm.setEmployeeId(employeeId);
            EmployeeInfo info = employee.getEmployeeDetailByIdforDetail(employeeId);
            request.setAttribute("EMPLOYEE_DETAIL", info);
            return mapping.findForward("view_employee");
        } else if (frm.getDoSave() != null && frm.getDoSave().equals("yes")) {
            print(this, "getDoSave block.");
            if (isTokenValid(request)) {
                resetToken(request);
                int employeeId = frm.getEmployeeId();
                String name = validate.replacename(frm.getName());
                String lastname = frm.getLastname();
                String emailid = frm.getEmailid();
                String number = frm.getNumber();

                int countryId = frm.getCountryId();
                int cityId = frm.getCityId();
                String cityName = frm.getCityName();
                System.out.println("City Id: "+ cityId + " of City Name: "+cityName);
                
                String[] profcolumn = frm.getProfColumn();
                String wprofcolumn = validate.replacename(makeCommaDelimString(profcolumn));
                
                String gender = validate.replacename(frm.getGender());
                
                String deschidden = frm.getDeschidden();
                String description = frm.getDescription();

                int status = 1;
                String ipAddrStr = request.getRemoteAddr();
                String iplocal = employee.getLocalIp();
                int ck = employee.checkDuplicacy(employeeId, name, lastname);
                if (ck == 1) {
                    Collection countries = country.getCountrys();
                    frm.setCountries(countries);
                    saveToken(request);
                    request.setAttribute("MESSAGE", "Employee already exists");
                    return mapping.findForward("add_employee");
                }

                String add_path = employee.getMainPath("add_resumetemplate_file");
                java.util.Date now = new java.util.Date();
                String fname = String.valueOf(now.getTime());
                String htmlFolderName = employee.dateFolder();
                if (employeeId > 0) {
                    if (description != null && !description.equals("")) {
                        if (deschidden != null && !deschidden.equals("")) {
                            File f = new File(add_path + deschidden);
                            if (f.exists()) {
                                f.delete();
                            }
                        }
                        description = employee.writeHTMLFile(description, add_path + htmlFolderName, fname + ".html");
                        description = htmlFolderName + "/" + description;
                    }
                } else if (description != null && !description.equals("")) {
                    description = employee.writeHTMLFile(description, add_path + htmlFolderName, fname + ".html");
                    description = htmlFolderName + "/" + description;
                }

                String add_employee_file = employee.getMainPath("add_employee_file");
                String foldername = employee.createFolder(add_employee_file);
                String fn = String.valueOf(now.getTime());

                String fileName1 = "";
                FormFile filename = frm.getLangfile();
                if (filename != null && filename.getFileSize() > 0) {
                    fileName1 = employee.uploadFile(employeeId, frm.getLangfilehidden(), filename, fn + "_1", add_employee_file, foldername);
                }

                request.getSession().removeAttribute("PROF_IDs");
                
                EmployeeInfo info = new EmployeeInfo(employeeId, name, lastname, emailid, number, gender, countryId, cityId, wprofcolumn, description, status, fileName1, uId);
                System.out.println("Employee info details: " + info);
                if (employeeId <= 0) {
                    int cc = employee.createEmployee(info);
                    if (cc > 0) {
                        employee.createHistoryAccess(null, uId, ipAddrStr, iplocal, "Data Added", 5, cc);
                        request.setAttribute("MESSAGE", "Data added successfully.");

                    }
                    request.getSession().removeAttribute("FILENAME");
                    ArrayList employeeList = employee.getEmployeeByName(search, statusIndex, 0, count);
                    int cnt = 0;
                    if (employeeList.size() > 0) {
                        EmployeeInfo einfo = (EmployeeInfo) employeeList.get(employeeList.size() - 1);
                        cnt = einfo.getEmployeeId();
                        employeeList.remove(employeeList.size() - 1);
                    }
                    request.getSession().setAttribute("EMPLOYEE_LIST", employeeList);
                    request.getSession().setAttribute("COUNT_LIST", cnt + "");
                    request.getSession().setAttribute("NEXT", "0");
                    request.getSession().setAttribute("NEXTVALUE", "1");

                    return mapping.findForward("display");
                } else {
                    employee.updateEmployee(info);
                    employee.createHistoryAccess(null, uId, ipAddrStr, iplocal, "Data Updated", 5, employeeId);
                    request.setAttribute("MESSAGE", "Data updated successfully.");
                    int next = 0;
                    if (request.getSession().getAttribute("NEXTVALUE") != null) {
                        next = Integer.parseInt((String) request.getSession().getAttribute("NEXTVALUE"));
                    }
                    next = next - 1;
                    if (next < 0) {
                        next = 0;
                    }
                    ArrayList employeeList = employee.getEmployeeByName(search, statusIndex, next, count);
                    int cnt = 0;
                    if (employeeList.size() > 0) {
                        EmployeeInfo einfo = (EmployeeInfo) employeeList.get(employeeList.size() - 1);
                        cnt = einfo.getEmployeeId();
                        employeeList.remove(employeeList.size() - 1);
                    }
                    request.getSession().setAttribute("EMPLOYEE_LIST", employeeList);
                    request.getSession().setAttribute("COUNT_LIST", cnt + "");
                    request.getSession().setAttribute("NEXT", next + "");
                    request.getSession().setAttribute("NEXTVALUE", (next + 1) + "");

                    return mapping.findForward("display");
                }
            }
        } else if (frm.getDoCancel() != null && frm.getDoCancel().equals("yes")) {
            print(this, "doCancel block");
            int next = 0;
            if (request.getSession().getAttribute("NEXTVALUE") != null) {
                next = Integer.parseInt((String) request.getSession().getAttribute("NEXTVALUE"));
            }
            next = next - 1;
            if (next < 0) {
                next = 0;
            }
            ArrayList employeeList = employee.getEmployeeByName(search, statusIndex, next, count);
            int cnt = 0;
            if (employeeList.size() > 0) {
                EmployeeInfo einfo = (EmployeeInfo) employeeList.get(employeeList.size() - 1);
                cnt = einfo.getEmployeeId();
                employeeList.remove(employeeList.size() - 1);
            }
            request.getSession().setAttribute("EMPLOYEE_LIST", employeeList);
            request.getSession().setAttribute("COUNT_LIST", cnt + "");
            request.getSession().setAttribute("NEXT", next + "");
            request.getSession().setAttribute("NEXTVALUE", (next + 1) + "");
            return mapping.findForward("display");
        } else {
            print(this, "else block.");
            if (frm.getCtp() == 0) {
                Stack<String> blist = new Stack<String>();
                if (request.getSession().getAttribute("BACKURL") != null) {
                    blist = (Stack<String>) request.getSession().getAttribute("BACKURL");
                }
                blist.push(request.getRequestURI());
                request.getSession().setAttribute("BACKURL", blist);
            }

            ArrayList employeeList = employee.getEmployeeByName(search, statusIndex, 0, count);
            int cnt = 0;
            if (employeeList.size() > 0) {
                EmployeeInfo einfo = (EmployeeInfo) employeeList.get(employeeList.size() - 1);
                cnt = einfo.getEmployeeId();
                employeeList.remove(employeeList.size() - 1);
            }
            request.getSession().setAttribute("EMPLOYEE_LIST", employeeList);
            request.getSession().setAttribute("COUNT_LIST", cnt + "");
            request.getSession().setAttribute("NEXT", "0");
            request.getSession().setAttribute("NEXTVALUE", "1");
        }
        return mapping.findForward("display");
    }
}
