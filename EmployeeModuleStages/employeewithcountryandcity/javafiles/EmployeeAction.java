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
import java.util.Collection;
import java.util.Stack;

public class EmployeeAction extends Action 
{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EmployeeForm frm = (EmployeeForm) form;
        Employee employee = new Employee();
        Validate vobj = new Validate();
        
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
            if (uInfo != null) 
            {
                permission = uInfo.getPermission();
                uId = uInfo.getUserId();
            }
        }
        int check_user = employee.checkUserSession(request, 5, permission);
        if (check_user == -1)
            return mapping.findForward("default");
        else if (check_user == -2) 
        {
            String authmess = employee.getMainPath("user_auth");
            authmess = authmess.replaceAll("__MODULE__", "Country Module");
            request.setAttribute("AUTHMESSAGE", authmess);
            return mapping.findForward("auth");
        }
        if (frm.getDoAdd() != null && frm.getDoAdd().equals("yes"))
        {
            print(this, " doAdd block :: ");
            frm.setStatus(1);
            frm.setEmployeeId(-1);
            
            Collection countries = country.getCountrys();
            frm.setCountries(countries);
            
            saveToken(request);
            return mapping.findForward("add_employee");
        }
        else if(frm.getDoModify() != null && frm.getDoModify().equals("yes"))
        {
            int employeeId = frm.getEmployeeId();
            frm.setEmployeeId(employeeId);
            Collection countries = country.getCountrys();
            frm.setCountries(countries);
            EmployeeInfo info = employee.getEmployeeDetailById(employeeId);
            if(info != null)
            {    
                
                
                frm.setName(info.getName());
                frm.setLastname(info.getLastname());
                frm.setEmailid(info.getEmailid());
                frm.setNumber(info.getNumber());
                frm.setCountryId(info.getCountryId());
                //frm.setCityId(info.getCityId());
                frm.setCityName(info.getCity());
                frm.setStatus(info.getStatus());
            }
            saveToken(request);
            return mapping.findForward("add_employee");
        }
        else if(frm.getDoView() != null && frm.getDoView().equals("yes"))
        {            
            int employeeId = frm.getEmployeeId();
            frm.setEmployeeId(employeeId);
            EmployeeInfo info = employee.getEmployeeDetailByIdforDetail(employeeId);
            request.setAttribute("EMPLOYEE_DETAIL", info);
            return mapping.findForward("view_employee");
        }        
        else if(frm.getDoSave() != null && frm.getDoSave().equals("yes"))
        {
            print(this,"getDoSave block.");
            if(isTokenValid(request))
            {
                resetToken(request);
                int employeeId = frm.getEmployeeId();
                String name = vobj.replacename(frm.getName());
                String lastname = frm.getLastname();
                String emailid = frm.getEmailid();
                String number = frm.getNumber();
                
                int countryId = frm.getCountryId();
                int cityId = frm.getCityId();
                
                int status = 1;
                String ipAddrStr = request.getRemoteAddr();
                String iplocal = employee.getLocalIp();
                int ck = employee.checkDuplicacy(employeeId, name, lastname);
                if(ck == 1)
                {
                    Collection countries = country.getCountrys();
                    frm.setCountries(countries);
                    saveToken(request);
                    request.setAttribute("MESSAGE", "Employee already exists");
                    return mapping.findForward("add_employee");
                }
                EmployeeInfo info = new EmployeeInfo(employeeId, name, lastname, emailid, number, countryId, cityId, status, uId);
                if(employeeId <= 0)
                {
                    int cc = employee.createEmployee(info);
                    if(cc > 0)
                    {
                       employee.createHistoryAccess(null, uId, ipAddrStr, iplocal, "Data Added", 5, cc);
                       request.setAttribute("MESSAGE", "Data added successfully.");
                       
                    }
                    ArrayList employeeList = employee.getEmployeeByName(search, statusIndex, 0, count);
                    int cnt = 0;
                    if(employeeList.size() > 0)
                    {
                        EmployeeInfo einfo = (EmployeeInfo) employeeList.get(employeeList.size() - 1);
                        cnt = einfo.getEmployeeId();
                        employeeList.remove(employeeList.size() - 1);
                    }
                    request.getSession().setAttribute("EMPLOYEE_LIST", employeeList);
                    request.getSession().setAttribute("COUNT_LIST", cnt+"");
                    request.getSession().setAttribute("NEXT", "0");
                    request.getSession().setAttribute("NEXTVALUE", "1");
                    
                    return mapping.findForward("display");
                }
                else
                {
                    employee.updateEmployee(info);
                    employee.createHistoryAccess(null, uId, ipAddrStr, iplocal, "Data Updated", 5, employeeId);
                    request.setAttribute("MESSAGE", "Data updated successfully.");
                    int next = 0;
                    if(request.getSession().getAttribute("NEXTVALUE") != null)
                    {
                        next = Integer.parseInt((String) request.getSession().getAttribute("NEXTVALUE"));
                    }
                    next = next - 1;
                    if(next < 0)
                        next = 0;
                    ArrayList employeeList = employee.getEmployeeByName(search, statusIndex, next, count);
                    int cnt = 0;
                    if(employeeList.size() > 0)
                    {
                        EmployeeInfo einfo = (EmployeeInfo)employeeList.get(employeeList.size() - 1);
                        cnt = einfo.getEmployeeId();
                        employeeList.remove(employeeList.size() - 1);
                    }
                    request.getSession().setAttribute("EMPLOYEE_LIST", employeeList);
                    request.getSession().setAttribute("COUNT_LIST", cnt+"");
                    request.getSession().setAttribute("NEXT", next+"");
                    request.getSession().setAttribute("NEXTVALUE", (next+1)+"");
                    
                    return mapping.findForward("display");
                }                
            }
        }
        else if(frm.getDoCancel() != null && frm.getDoCancel().equals("yes"))
        {
            print(this,"doCancel block");
            int next = 0;
            if(request.getSession().getAttribute("NEXTVALUE") != null)
            {
                next = Integer.parseInt((String) request.getSession().getAttribute("NEXTVALUE"));
            }
            next = next - 1;
            if(next < 0)
                next = 0;
            ArrayList employeeList = employee.getEmployeeByName(search, statusIndex, next, count);
            int cnt = 0;
            if(employeeList.size() > 0)
            {
                EmployeeInfo einfo = (EmployeeInfo) employeeList.get(employeeList.size() - 1);
                cnt = einfo.getEmployeeId();
                employeeList.remove(employeeList.size() - 1);
            }
            request.getSession().setAttribute("EMPLOYEE_LIST", employeeList);
            request.getSession().setAttribute("COUNT_LIST", cnt+"");
            request.getSession().setAttribute("NEXT", next + "");
            request.getSession().setAttribute("NEXTVALUE", (next+1) +"");
            return mapping.findForward("display");
        }
        else
        {
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
            if(employeeList.size() > 0)
            {
                EmployeeInfo einfo = (EmployeeInfo) employeeList.get(employeeList.size() - 1);
                cnt = einfo.getEmployeeId();
                employeeList.remove(employeeList.size() - 1);
            }
            request.getSession().setAttribute("EMPLOYEE_LIST", employeeList);
            request.getSession().setAttribute("COUNT_LIST", cnt+"");
            request.getSession().setAttribute("NEXT", "0");
            request.getSession().setAttribute("NEXTVALUE", "1");
        }
        return mapping.findForward("display");
    }    
}