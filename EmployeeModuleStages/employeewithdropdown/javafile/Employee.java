 package com.web.jxp.employee;

import com.mysql.jdbc.Statement;
import com.web.jxp.airport.AirportInfo;
import com.web.jxp.base.Base;
import com.web.jxp.common.Common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static com.web.jxp.common.Common.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Employee extends Base 
{
    private static final Logger logger = Logger.getLogger(Class.class.getName());
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public ArrayList getEmployeeByName(String search, int statusIndex, int next, int count)
    {
        ArrayList employees = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("select t_employee.i_employeeid, t_employee.s_name, t_employee.s_lastname, t_employee.s_emailid, t_employee.s_number, t_country.s_name, t_city.s_name, t_employee.s_description, t_employee.i_status, t_employee.s_empfilename from t_employee ");
        sb.append("left join t_country on t_employee.i_countryid = t_country.i_countryid ");
        sb.append("left join t_city on t_employee.i_cityid = t_city.i_cityid ");
        sb.append(" where 0 = 0 ");
        if(search != null && !search.equals(""))
            sb.append("and (t_employee.s_name like ? OR t_employee.s_lastname like ? or t_employee.s_number = ? OR t_country.s_name like ? OR t_city.s_name like ? or t_employee.s_description = ? OR t_employee.s_empfilename = ?) ");
        if(statusIndex > 0)
        {
            sb.append("and t_employee.i_status = ? ");
        }
        sb.append(" order by t_employee.i_status, t_employee.s_name ");
        if(count > 0)
            sb.append(" limit ").append(next*count).append(", ").append(count);
        String query = (sb.toString()).intern();
        sb.setLength(0);
        
        sb.append("select count(1) FROM t_employee ");
        sb.append("left join t_country on t_employee.i_countryid = t_country.i_countryid ");
        sb.append("left join t_city on t_employee.i_cityid = t_city.i_cityid ");
        sb.append("where 0 = 0 ");
        if(search != null && !search.equals(""))
            sb.append("and (t_employee.s_name like ? OR t_employee.s_lastname like ? or t_employee.s_number = ? OR t_country.s_name like ? OR t_city.s_name like ? or t_employee.s_description = ? or t_employee.s_empfilename = ?) ");
        if(statusIndex > 0)
        {
            sb.append("and t_employee.i_status = ? ");
        }
        String empquery = (sb.toString()).intern();
        sb.setLength(0);
        try
        {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int scc = 0;
            if(search != null && !search.equals(""))
            {
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, search);
            }
            if (statusIndex > 0)
                pstmt.setInt(++scc, statusIndex);
            logger.info("getEmployeeByName :: " + pstmt.toString());
            rs = pstmt.executeQuery();
            String name, lastname, emailid, number, gender, countryName, cityName, description, empfilename;
            int employeeId, status;
            while (rs.next())
            {
                employeeId = rs.getInt(1);
                name = rs.getString(2) != null ? rs.getString(2) : "";
                lastname = rs.getString(3);
                emailid = rs.getString(4);
                number = rs.getString(5);
                //gender = rs.getString(6);
                countryName = rs.getString(6) != null ? rs.getString(6) : "";
                cityName = rs.getString(7) != null ? rs.getString(7) : "";
                description = rs.getString(8) != null ? rs.getString(8) : "";
                status = rs.getInt(9);
                empfilename = rs.getString(10);
                employees.add(new EmployeeInfo(employeeId, name, lastname, emailid, number, countryName, cityName, description, status, empfilename,0));
            }
            rs.close();
            
            pstmt = conn.prepareStatement(empquery);
            scc = 0;
            if(search != null && !search.equals(""))
            {
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, search);
            }
            if (statusIndex > 0)
                pstmt.setInt(++scc, statusIndex);
            //print(this,"getCountryByName :: " + pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                employeeId = rs.getInt(1);
                employees.add(new EmployeeInfo(employeeId, "", "", "", "", "", "", "", 0, "", 0));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(conn, pstmt, rs);
        }
        return employees;
    }

    public ArrayList getFinalRecord(ArrayList l, String colId, int tp)
    {
        ArrayList list = null;
        HashMap record = null;
        EmployeeInfo info;
        int total = 0;
        if(l != null)
            total = l.size();
        try
        {
            if(total > 0)
            {
                record = new HashMap();
                int i;
                for (i = 0; i < total; i++)
                {
                    info = (EmployeeInfo) l.get (i);
                    record.put(getInfoValue(info, colId), info);
                }
            }
            Map map = sortByName(record, tp);
            Iterator it = map.keySet().iterator();
            list = new ArrayList();
            EmployeeInfo rInfo;
            while (it.hasNext())
            {
                String key = (String) it.next();
                int i;
                for(i= 0; i < total; i++)
                {
                    rInfo = (EmployeeInfo) l.get(i);
                    String str = getInfoValue(rInfo, colId);
                    if(str.equals(key))
                    {
                        list.add(rInfo);
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    // to sort serach result
    public String getInfoValue(EmployeeInfo info, String i)
    {
        String infoval = "";
        if(i != null && i.equals("1"))
            infoval = info.getName() != null ? info.getName() : "";
        if(i != null && i.equals("2"))
            infoval = info.getLastname() != null ? info.getLastname() : "";
        if(i != null && i.equals("3"))
            infoval = info.getEmailid() != null ? info.getEmailid() : "";
        if(i != null && i.equals("4"))
            infoval = info.getNumber() != null ? info.getNumber() : "";
        if(i != null && i.equals("5"))
            infoval = info.getGender() != null ? info.getGender() : "";
        if(i !=null && i.equals("6"))
            infoval = info.getCountryName()!= null ? info.getCountryName() : "";
        if(i != null && i.equals("7"))
            infoval = info.getCityName()!= null ? info.getCityName(): "";
        if(i !=null && i.equals("8"))
            infoval = info.getDescription()!= null ? info.getDescription() : "";
        if(i !=null && i.equals("9"))
            infoval = info.getEmpfilename()!= null ? info.getEmpfilename() : "";
        return infoval;
    }
    public EmployeeInfo getEmployeeDetailById(int employeeId)
    {
        EmployeeInfo info = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT t_employee.s_name, t_employee.s_lastname, t_employee.s_emailid, t_employee.s_number, t_employee.s_gender, t_employee.i_countryid, t_city.s_name , t_employee.s_description, t_employee.i_status, t_employee.s_empfilename FROM t_employee  ");
        sb.append("left join t_city on (t_employee.i_cityid = t_city.i_cityid) ");
        sb.append("where i_employeeid = ?");
        String query = sb.toString().intern();
        sb.setLength(0);
        try
        {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, employeeId);
            //print(this,"getCountryDetailById :: " + pstmt.toString());
            rs = pstmt.executeQuery();
            String name, lastname, emailid, number, gender, cityName, description , empfilename;
            int status,countryId,cityId;
            while (rs.next())
            {
                name = rs.getString(1);
                lastname = rs.getString(2);
                emailid = rs.getString(3);
                number = rs.getString(4);
                gender = rs.getString(5);
                countryId = rs.getInt(6);
                cityName = rs.getString(7);
                description = rs.getString(8) != null ? rs.getString(8) : "";
                status = rs.getInt(9);
                empfilename = rs.getString(10);
                info = new EmployeeInfo(employeeId, name, lastname, emailid, number, gender, countryId, cityName, description ,status, empfilename,0); 
            }
        }
        catch (Exception exception)
        {
            print(this,"getEmployeeDetailById :: " + exception.getMessage());
        }
        finally
        {
            close(conn, pstmt, rs);
        }
        return info;
    }
    
    public EmployeeInfo getEmployeeDetailByIdforDetail(int employeeId)
    {
        EmployeeInfo info = null;
        StringBuilder sb = new StringBuilder();
        sb.append("select t_employee.s_name, t_employee.s_lastname, t_employee.s_emailid, t_employee.s_number, t_employee.s_gender, t_country.s_name, t_city.s_name, t_employee.s_description, t_employee.i_status, t_employee.s_empfilename from t_employee ");
        sb.append("left join t_country on (t_employee.i_countryid = t_country.i_countryid) ");
        sb.append("left join t_city on (t_employee.i_cityid = t_city.i_cityid) ");
        sb.append("where t_employee.i_employeeid = ?");
        String query = sb.toString().intern();
        sb.setLength(0);
        try
        {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, employeeId);
            print(this,"getCountryDetailByIdforDetail :: " + pstmt.toString());
            rs = pstmt.executeQuery();
            String name, lastname, emailid, number, gender, countryName, cityName, description, empfilename;
            int countryId, cityId;
            int status = 0;
            while (rs.next())
            {
                name = rs.getString(1);
                lastname = rs.getString(2);
                emailid = rs.getString(3);
                number = rs.getString(4);
                gender = rs.getString(5);
                countryName = rs.getString(6);
                cityName = rs.getString(7);
                description = rs.getString(8) != null ? rs.getString(8) : "";
                status = rs.getInt(9);
                empfilename = rs.getString(10);
                System.out.println("cityName :: " + cityName);
                if (description != null && !description.equals("")) {
                    String add_path = getMainPath("add_resumetemplate_file");
                    description = readHTMLFile(description, add_path);
                }
                info = new EmployeeInfo(employeeId, name, lastname, emailid, number, gender, countryName, cityName, description, status, empfilename,0); 
            }
            rs.close();            
        }
        catch (Exception exception)
        {
            print(this,"getEmployeeDetailByIdforDetail :: " + exception.getMessage());
        }
        finally
        {
            close(conn, pstmt, rs);
        }
        return info;
    }
    
    public int createEmployee(EmployeeInfo info)
    {
        int employeeId = 0;
        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append("insert into t_employee ");
            sb.append("(s_name, s_lastname, s_emailid , s_number, s_gender, i_countryid, i_cityid, s_description, i_status, s_empfilename, i_userid, ts_regdate, ts_moddate) ");
            sb.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            String query = (sb.toString()).intern();
            sb.setLength(0);

            conn = getConnection();
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int scc = 0;
            pstmt.setString(++scc, (info.getName ()));
            pstmt.setString(++scc, (info.getLastname()));
            pstmt.setString(++scc, (info.getEmailid()));
            pstmt.setString(++scc, info.getNumber());
            pstmt.setString(++scc, info.getGender());
            pstmt.setInt(++scc, info.getCountryId());
            pstmt.setInt(++scc, info.getCityId());
            pstmt.setString(++scc, info.getDescription());
            pstmt.setInt(++scc, info.getStatus());
            pstmt.setString(++scc, info.getEmpfilename());
            pstmt.setInt(++scc, info.getUserId());
            pstmt.setString(++scc, currDate1());
            pstmt.setString(++scc, currDate1());  
            //print(this,"createCountry :: " + pstmt.toString());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            while (rs.next())
            {
                employeeId = rs.getInt(1);
            }
            createjson(conn);
        }
        catch (Exception exception)
        {
            print(this,"createEmployee :: " + exception.getMessage());
        }
        finally
        {
            close(conn, pstmt, rs);
        }
        return employeeId;
    }
    public int updateEmployee(EmployeeInfo info)
    {
        int cc = 0;
        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append("update t_employee set ");
            sb.append("s_name = ?, ");
            sb.append("s_lastname = ?, ");
            sb.append("s_emailid = ?, ");
            sb.append("s_number = ?, ");
            sb.append("s_gender = ?, ");
            sb.append("i_countryid = ?, ");
            sb.append("i_cityid = ?, ");
            sb.append("s_description = ?, ");
            sb.append("i_status = ?, ");
            sb.append("s_empfilename = ?, ");
            sb.append("i_userid = ?, ");
            sb.append("ts_moddate = ? ");
            sb.append("where i_employeeid = ? ");
            String query = (sb.toString()).intern();
            sb.setLength(0);

            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int scc = 0;
            pstmt.setString(++scc, info.getName());
            pstmt.setString(++scc, info.getLastname());
            pstmt.setString(++scc, info.getEmailid());
            pstmt.setString(++scc, info.getNumber());
            pstmt.setString(++scc, info.getGender());
            pstmt.setInt(++scc, info.getCountryId());
            pstmt.setInt(++scc, info.getCityId());
            pstmt.setString(++scc, info.getDescription());
            pstmt.setInt(++scc, info.getStatus());
            pstmt.setString(++scc, info.getEmpfilename());
            pstmt.setInt(++scc, info.getUserId());
            pstmt.setString(++scc, currDate1());
            pstmt.setInt(++scc, info.getEmployeeId());
            //print(this,"updateCountry :: " + pstmt.toString());
            cc = pstmt.executeUpdate();
            createjson(conn);
        }
        catch (Exception exception)
        {
            print(this,"updateEmployee :: " + exception.getMessage());
        }
        finally
        {
            close(conn, pstmt, rs);
        }
        return cc;
    }
    public int checkDuplicacy(int employeeId, String name, String lastname)
    {
        int ck = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("select i_employeeid FROM t_employee where (s_name = ? OR s_lastname = ?) and i_status in (1, 2)");
        if(employeeId > 0)
            sb.append(" and i_employeeid != ? ");
        String query = (sb.toString()).intern();
        sb.setLength(0);
        try
        {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int scc = 0;           
            pstmt.setString(++scc, name);
            pstmt.setString(++scc, lastname);
            if(employeeId > 0)
                pstmt.setInt(++scc, employeeId);
            //print(this,"checkDuplicacy :: " + pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                ck = 1;
            }
        }
        catch (Exception exception)
        {
            print(this,"checkDuplicacy :: " + exception.getMessage());
        }
        finally
        {
            close(conn, pstmt, rs);
        }
        return ck;
    }
   
    public int deleteEmployee(int employeeId, int userId, int status, String ipAddrStr, String iplocal)
    {
        int cc = 0;
        try
        {
            conn = getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("update t_employee set ");
            sb.append("i_status = ?, ");
            sb.append("i_userid = ?, ");
            sb.append("ts_moddate = ? ");
            sb.append("where i_employeeid = ? ");
            String query = (sb.toString()).intern();
            sb.setLength(0);
            
            pstmt = conn.prepareStatement(query);
            int scc = 0;
            if(status == 1)
                pstmt.setInt(++scc, 2);
            else
                pstmt.setInt(++scc, 1);
            pstmt.setInt(++scc, userId);
            pstmt.setString(++scc, currDate1());
            pstmt.setInt(++scc, employeeId);
            
            cc = pstmt.executeUpdate();
            createjson(conn);
        }
        catch (Exception exception)
        {
            print(this,"deleteEmployee :: " + exception.getMessage());
        }
        finally
        {
            close(conn, pstmt, rs);
        }
        createHistoryAccess(null, userId, ipAddrStr, iplocal, "Data Deleted", 5, employeeId); 
        return cc;
    } 
    
    public Collection getCountry()
    {
        Collection coll = new LinkedList();
        String query = ("SELECT i_countryid, s_name FROM t_country where i_status = 1 order by s_name").intern();
        coll.add(new AirportInfo(-1, "- Select -"));
        try
        {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            int refId;
            String refName;
            while(rs.next())
            {
                refId = rs.getInt(1);
                refName = rs.getString(2);
                coll.add(new EmployeeInfo(refId, refName));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(conn,pstmt,rs);
        }
        return coll;
    }
    
    public Collection getcountry()
    {
        Collection coll = new LinkedList();
        coll.add(new AirportInfo(-1, "Select Employee"));
        String xml_path = getMainPath("json_path");
        String str = readHTMLFile("employee.json", xml_path);
        JSONArray arr = new JSONArray(str);
        if(arr != null)
        {
            int len = arr.length();
            for(int i = 0; i < len; i++)
            {
                JSONObject jobj = arr.optJSONObject(i);
                if(jobj != null)
                {
                    coll.add(new EmployeeInfo(jobj.optInt("employeeId"), jobj.optString("name")));
                }
            }
        }
        return coll;
    }
    
    public ArrayList getListForExcel(String search, int statusIndex) 
    {
        ArrayList list = new ArrayList();
        StringBuilder sb = new StringBuilder();
         sb.append("select s_name, s_lastname, s_emailid, s_number, i_status ");
        sb.append("FROM t_country where 0 = 0 ");
        if(search != null && !search.equals(""))
            sb.append("and (s_name like ? OR s_lastname like ? or s_emailid = ?) ");
        if(statusIndex > 0)
        {
            sb.append("and i_status = ? ");
        }
        sb.append(" order by i_status, s_name "); 
        String query = sb.toString().intern();
        sb.setLength(0);        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            int scc = 0;
           if(search != null && !search.equals(""))
            {
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, "%"+(search)+"%");
                pstmt.setString(++scc, search);
            }
            if (statusIndex > 0)
                pstmt.setInt(++scc, statusIndex);
            Common.print(this, "getListForExcel :: " + pstmt.toString());
            rs = pstmt.executeQuery();
             String name, lastname, emailid, number;
            int status;
            while (rs.next())
            {
                name = rs.getString(1) != null ? rs.getString(1) : "";
                lastname = rs.getString(2);
                emailid = rs.getString(3);
                number = rs.getString(4);
                status = rs.getInt(5);
                list.add(new EmployeeInfo(0, name, lastname, emailid, number, status, 0));
            }
            rs.close();
            pstmt.close();            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return list;
    }
    
      public void createjson(Connection conn)
    {
       String query = ("Select i_employeeid, s_name, s_lastname, s_emailid, s_number, i_countryid, i_cityid, s_description, s_empfilename FROM t_employee where i_status = 1 order by  s_name ");
        try
        {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            int id, countryId, cityId;
            String name, lastname, emailid, number, description, empfilename;
            JSONArray jarr = new JSONArray();
            while (rs.next())
            {
                id = rs.getInt(1);
                name = rs.getString(2);
                lastname = rs.getString(3);
                emailid = rs.getString(4);
                number = rs.getString(5);
                countryId = rs.getInt(6);
                cityId = rs.getInt(7);
                description = rs.getString(8);
                empfilename = rs.getString(9);
                JSONObject jobj = new JSONObject();
                jobj.put("id", id);
                jobj.put("name", name);
                jobj.put("lastname", lastname);
                jobj.put("emailid", emailid);
                jobj.put("number", number);
                jobj.put("country", countryId);
                jobj.put("city", cityId);
                jobj.put("description", description);
                jobj.put("empfilename", empfilename);
                
                jarr.put(jobj);
            }
            rs.close();
            String str = jarr.toString();
            String filePath = getMainPath("json_path");
            writeHTMLFile(str, filePath, "employee.json");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(null, pstmt, rs);
        }
    }
      public Collection getEmployees()
    {
        Collection coll = new LinkedList();
        coll.add(new EmployeeInfo(-1, "- Select -","- Select -","", "- Select -"));
        String xml_path = getMainPath("json_path");
        String str = readHTMLFile("employee.json", xml_path);
        JSONArray arr = new JSONArray(str);
        if(arr != null)
        {
            int len = arr.length();
            for(int i = 0; i < len; i++)
            {
                JSONObject jobj = arr.optJSONObject(i);
                if(jobj != null)
                {
                    coll.add(new EmployeeInfo(jobj.optInt("id"), 
                            
            jobj.optString("name"),
                            jobj.optString("lastname"),
                            jobj.optString("number"), 
                            (jobj.optString("name")+" (+" + jobj.optString("number")+")") ));
                }
            }
        }
        return coll;
    }
      
      public Collection getEmployeesclient()
    {
        Collection coll = new LinkedList();
        String xml_path = getMainPath("json_path");
        String str = readHTMLFile("employee.json", xml_path);
        JSONArray arr = new JSONArray(str);
        if(arr != null)
        {
            int len = arr.length();
            for(int i = 0; i < len; i++)
            {
                JSONObject jobj = arr.optJSONObject(i);
                if(jobj != null)
                {
                    coll.add(new EmployeeInfo(jobj.optInt("id"), 
                            jobj.optString("name"),
                            jobj.optString("lastname"),
                            jobj.optString("number"), 
                            (jobj.optString("name")+" (+" + jobj.optString("number")+")") ));
                }
            }
        }
        return coll;
    }
}
