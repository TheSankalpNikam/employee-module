package com.web.jxp.employee;
import java.util.Collection;
import org.apache.struts.action.ActionForm;

public class EmployeeForm extends ActionForm
{
    private int employeeId;
    private int status;
    private String search;
    private String doCancel;
    private String doModify;
    private String name;
    private String lastname;
    private String emailid;
    private String number;
    private String doAdd;
    private String doSave;
    private Collection statuses;
    private String doView;
    private int statusIndex;
    private int ctp;

    private String cityName;
    private Collection countries;
    private int countryId;
    private int cityId;
    
    public int getCtp() {
        return ctp;
    }

    public void setCtp(int ctp) {
        this.ctp = ctp;
    }

    /**
     * @return the countryId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return the doCancel
     */
    public String getDoCancel() {
        return doCancel;
    }

    /**
     * @param doCancel the doCancel to set
     */
    public void setDoCancel(String doCancel) {
        this.doCancel = doCancel;
    }

    /**
     * @return the doModify
     */
    public String getDoModify() {
        return doModify;
    }

    /**
     * @param doModify the doModify to set
     */
    public void setDoModify(String doModify) {
        this.doModify = doModify;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nationality
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the code
     */
    public String getEmailid() {
        return emailid;
    }

    /**
     * @param code the code to set
     */
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
    
    public String getNumber(){
        return number;
    }
    
    public void setNumber(String number){
        this.number = number;
    }

    /**
     * @return the doAdd
     */
    public String getDoAdd() {
        return doAdd;
    }

    /**
     * @param doAdd the doAdd to set
     */
    public void setDoAdd(String doAdd) {
        this.doAdd = doAdd;
    }

    /**
     * @return the doSave
     */
    public String getDoSave() {
        return doSave;
    }

    /**
     * @param doSave the doSave to set
     */
    public void setDoSave(String doSave) {
        this.doSave = doSave;
    }

    /**
     * @return the statuses
     */
    public Collection getStatuses() {
        return statuses;
    }

    /**
     * @param statuses the statuses to set
     */
    public void setStatuses(Collection statuses) {
        this.statuses = statuses;
    }

    /**
     * @return the doView
     */
    public String getDoView() {
        return doView;
    }

    /**
     * @param doView the doView to set
     */
    public void setDoView(String doView) {
        this.doView = doView;
    }

    /**
     * @return the statusIndex
     */
    public int getStatusIndex() {
        return statusIndex;
    }

    /**
     * @param statusIndex the statusIndex to set
     */
    public void setStatusIndex(int statusIndex) {
        this.statusIndex = statusIndex;
    }    

    /**
     * @return the countries
     */
    public Collection getCountries() {
        return countries;
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(Collection countries) {
        this.countries = countries;
    }

    /**
     * @return the countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the cityId
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * @param cityId the cityId to set
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }
}