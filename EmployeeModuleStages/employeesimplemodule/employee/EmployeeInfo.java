package com.web.jxp.employee;

public class EmployeeInfo
{
    private int employeeId;
    private String name;
    private String fathername;
    private String emailid;
    private String number;
    private int status;
    private int userId;  
    private int ddlValue;
    private String ddlLabel;
    private String ddlLabel1;
    private String ddlLabel2;
    private String ddlLabel3;
    
    //for ddl
    public EmployeeInfo(int ddlValue, String ddlLabel,String ddlLabel1,String ddlLabel2,String ddlLabel3)
    {
        this.ddlValue = ddlValue;
        this.ddlLabel = ddlLabel;
        this.ddlLabel1 = ddlLabel1;
        this.ddlLabel2 = ddlLabel2;
        this.ddlLabel3 = ddlLabel3;
        
    }

    public EmployeeInfo(int employeeId, String name, String fathername, String emailid, String number, int status, int userId)
    {
        this.employeeId = employeeId;
        this.name = name;
        this.fathername = fathername;
        this.emailid = emailid;
        this.number = number;
        this.status = status;
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
    public String getFathername() {
        return fathername;
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
    
}
