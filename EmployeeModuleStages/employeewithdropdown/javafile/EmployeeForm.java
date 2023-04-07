package com.web.jxp.employee;
import java.util.Collection;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

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
    
    
    private FormFile langfile;
    private String langfilehidden;
    
    private String description;
    private String deschidden;
    
    private String gender;
    
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

    public void setStatuses(Collection statuses) {
        this.statuses = statuses;
    }

    public String getDoView() {
        return doView;
    }

    public void setDoView(String doView) {
        this.doView = doView;
    }

    public int getStatusIndex() {
        return statusIndex;
    }

    public void setStatusIndex(int statusIndex) {
        this.statusIndex = statusIndex;
    }    

    public Collection getCountries() {
        return countries;
    }

    public void setCountries(Collection countries) {
        this.countries = countries;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getLangfilehidden() {
        return langfilehidden;
    }

    public void setLangfilehidden(String langfilehidden) {
        this.langfilehidden = langfilehidden;
    }

     public FormFile getLangfile() {
        return langfile;
    }

    public void setLangfile(FormFile langfile) {
        this.langfile = langfile;
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
     * @return the deschidden
     */
    public String getDeschidden() {
        return deschidden;
    }

    /**
     * @param deschidden the deschidden to set
     */
    public void setDeschidden(String deschidden) {
        this.deschidden = deschidden;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


}