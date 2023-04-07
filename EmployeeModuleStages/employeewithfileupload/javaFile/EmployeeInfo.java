package com.web.jxp.employee;

import java.util.Collection;

public class EmployeeInfo
{
    private int employeeId;
    private String name;
    private String lastname;
    private String emailid;
    private String number;
    private int status;
    private int userId;  
    private int ddlValue;
    private String ddlLabel;
    private String ddlLabel1;
    private String ddlLabel2;
    private String ddlLabel3;
    
    private String countryName;
    
    private String city;
    
    private int countryId;
    private int cityId;
    private String cityName;
    
    private String description;
    
    private String empfilename;
    
    private int genderId;
    private Collection gendertypes;
    private String gendertypeName;
    
    //for ddl
    public EmployeeInfo(int ddlValue, String ddlLabel,String ddlLabel1,String ddlLabel2,String ddlLabel3)
    {
        this.ddlValue = ddlValue;
        this.ddlLabel = ddlLabel;
        this.ddlLabel1 = ddlLabel1;
        this.ddlLabel2 = ddlLabel2;
        this.ddlLabel3 = ddlLabel3;
        
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, int status, int userId)
    {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.status = status;
        this.userId = userId;
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, String countryName, String cityName, int status, int userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryName = countryName;
        this.cityName = cityName;
        this.status = status;
        this.userId = userId;
    }
    
    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, String countryName, String cityName, int status) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryName = countryName;
        this.cityName = cityName;
        this.status = status;
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, int countryId, int cityId, int status, int userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryId = countryId;
        this.cityId = cityId;
        this.status = status;
        this.userId = userId;
    }

    public EmployeeInfo(int ddlValue, String ddlLabel)
    {
        this.ddlValue = ddlValue;
        this.ddlLabel = ddlLabel;
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, String countryName, int cityId, int status, int userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryName = countryName;
        this.cityId = cityId;
        this.status = status;
        this.userId = userId;
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, String countryName, String cityName, int status, String empfilename, int i) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryName = countryName;
        this.cityName = cityName;
        this.status = status;
        this.empfilename = empfilename;
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, int countryId, int cityId, int status, String fileName1, int userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryId = countryId;
        this.cityId = cityId;
        this.status = status;
        this.userId = userId;
        this.empfilename = fileName1;
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, int countryId, int cityId, String description, int status, String empfilename, int userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryId = countryId;
        this.cityId = cityId;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.empfilename = empfilename;
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, String countryName, String cityName, String description, int status, String empfilename, int i) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryName = countryName;
        this.cityName = cityName;
        this.description = description;
        this.status = status;
        this.empfilename = empfilename;
        this.userId = i;
    }

    public EmployeeInfo(int employeeId, String name, String lastname, String emailid, String number, int countryId, String cityName, String description, int status, String empfilename, int userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastname = lastname;
        this.emailid = emailid;
        this.number = number;
        this.countryId = countryId;
        this.cityName = cityName;
        this.description = description;
        this.status = status;
        this.empfilename = empfilename;
        this.userId = userId;
    }

    public String getDdlLabel3() {
        return ddlLabel3;
    }

    public String getDdlLabel1() {
        return ddlLabel1;
    }

    public String getDdlLabel2() {
        return ddlLabel2;
    }
    /**
     * @return the countryId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the nationality
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @return the code
     */
    public String getEmailid() {
        return emailid;
    }
    
    public String getNumber(){
        return number;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @return the ddlValue
     */
    public int getDdlValue() {
        return ddlValue;
    }

    /**
     * @return the ddlLabel
     */
    public String getDdlLabel() {
        return ddlLabel;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

  

    /**
     * @return the countryId
     */
    public int getCountryId() {
        return countryId;
    }

 
     public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the cityId
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the empfilename
     */
    public String getEmpfilename() {
        return empfilename;
    }

    /**
     * @param empfilename the empfilename to set
     */
    public void setEmpfilename(String empfilename) {
        this.empfilename = empfilename;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the genderId
     */
    public int getGenderId() {
        return genderId;
    }

    /**
     * @param genderId the genderId to set
     */
    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    /**
     * @return the gendertypes
     */
    public Collection getGendertypes() {
        return gendertypes;
    }

    /**
     * @param gendertypes the gendertypes to set
     */
    public void setGendertypes(Collection gendertypes) {
        this.gendertypes = gendertypes;
    }

    /**
     * @return the gendertypeName
     */
    public String getGendertypeName() {
        return gendertypeName;
    }

    /**
     * @param gendertypeName the gendertypeName to set
     */
    public void setGendertypeName(String gendertypeName) {
        this.gendertypeName = gendertypeName;
    }



    
}
