<%
    int smarr[] = new int[200];
    if (session.getAttribute("MARR") != null) {
        smarr = (int[]) session.getAttribute("MARR");
    }
%>
<%!
    public boolean cst(int arr[], int ch) {
        boolean b = false;
        if (arr != null) {
            int tm = arr.length;
            for (int m = 0; m < tm; m++) {
                if (arr[m] == ch) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }
%>
<div class="vertical-menu">
    <div data-simplebar class="h-100">		
        <div id="sidebar-menu">
            <ul class="metismenu list-unstyled" id="side-menu">				
                <li class="menu1 <%=(mtp == -1) ? "mm-active active" : ""%>">
                    <a href="/jxp/dashboard/dashboard.jsp" class="waves-effect"><img src="/jxp/assets/images/dashboard.png"/> <strong>Dashboard</strong></a>
                </li>				
                <li class="menu2 <%=(mtp == 2) ? "mm-active active" : ""%>">
                    <a href="javascript:;" class="waves-effect"><img src="/jxp/assets/images/crew-management.png"/> <strong>Crew Management</strong></a>
                    <ul class="sub-menu mm-collapse" aria-expanded="false">
                        <!--                        <li><a href="javascript:;">Enroll Candidate</a></li>-->
                        <% if (cst(smarr, 7)) {%><li class="<%=(submtp == 7 ? "mm-active" : "")%>"><a class="<%=(submtp == 7 ? "active" : "")%>" href="/jxp/candidate/CandidateAction.do">Enroll Candidate</a></li><%}%>
                        <% if (cst(smarr, 46)) {%><li class="<%=(submtp == 46 ? "mm-active" : "")%>"><a class="<%=(submtp == 46 ? "active" : "")%>" href="/jxp/verification/VerificationAction.do">Verification</a></li><%}%>
                        <% if (cst(smarr, 14)) {%><li class="<%=(submtp == 14 ? "mm-active" : "")%>"><a class="<%=(submtp == 14 ? "active" : "")%>" href="/jxp/cassessment/CassessmentAction.do">Assessments</a></li><%}%>
                        <% if (cst(smarr, 4)) {%><li class="<%=(submtp == 4 ? "mm-active" : "")%>"><a class="<%=(submtp == 4 ? "active" : "")%>" href="/jxp/talentpool/TalentpoolAction.do">Talent Pool</a></li><%}%>
                        <% if (cst(smarr, 45)) {%><li class="<%=(submtp == 45 ? "mm-active" : "")%>"><a class="<%=(submtp == 45 ? "active" : "")%>" href="/jxp/jobpost/JobPostAction.do">Job Posting</a></li><%}%>
                        <% if (cst(smarr, 49)) {%><li class="<%=(submtp == 49 ? "mm-active" : "")%>"><a class="<%=(submtp == 49 ? "active" : "")%>" href="/jxp/shortlisting/ShortlistingAction.do">Shortlisting</a></li><%}%>
                        <% if (cst(smarr, 47)) {%><li class="<%=(submtp == 47 ? "mm-active" : "")%>"><a class="<%=(submtp == 47 ? "active" : "")%>" href="/jxp/compliancecheck/CompliancecheckAction.do">Compliance Checks</a></li><%}%>
                        <% if (cst(smarr, 51)) {%><li class="<%=(submtp == 51 ? "mm-active" : "")%>"><a class="<%=(submtp == 51 ? "active" : "")%>" href="/jxp/clientselection/ClientselectionAction.do">Client Selection</a></li><%}%>
                        <li><a href="javascript:;">Crew Rotation</a></li>
                        <li><a href="javascript:;">Mobilization</a></li>
                        <li><a href="javascript:;">Onboarding</a></li>
                    </ul>
                </li>
                <li class="menu3">
                    <a href="javascript: void(0);" class="waves-effect">
                        <img src="/jxp/assets/images/training-and-dev.png"/> <strong>Training & Development</strong>
                    </a>
                    <ul class="sub-menu mm-collapse" aria-expanded="false">
                        <li><a href="javascript:;">Appraisals</a></li>
                        <li><a href="javascript:;">E-Learning Solutions</a></li>
                        <li><a href="javascript:;">Effectiveness Measure</a></li>
                        <li><a href="javascript:;">Manage Content</a></li>
                        <li><a href="javascript:;">Training Matrix</a></li>
                        <li><a href="javascript:;">Training Organizations</a></li>
                        <li><a href="javascript:;">Reports & Dashboards</a></li>
                    </ul>
                </li>
                <li class="menu4">
                    <a href="javascript: void(0);" class="waves-effect">
                        <img src="/jxp/assets/images/competency-mgmt.png"/> <strong>Competency Management</strong>
                    </a>
                    <ul class="sub-menu mm-collapse" aria-expanded="false">
                        <li><a href="javascript:;">Certifications</a></li>
                        <li><a href="javascript:;">Competency Tracker</a></li>
                        <li><a href="javascript:;">Database Management</a></li>
                        <li><a href="javascript:;">Framework Management</a></li>
                        <li><a href="javascript:;">Reports & Dashboards</a></li>
                    </ul>
                </li>
                <li class="menu5">
                    <a href="javascript: void(0);" class="waves-effect">
                        <img src="/jxp/assets/images/wellness-mgmt.png"/> <strong>Wellness Management</strong>
                    </a>
                    <ul class="sub-menu mm-collapse" aria-expanded="false">
                        <li><a href="javascript:;">Crew Insurance</a></li>
                        <li><a href="javascript:;">Diet</a></li>
                        <li><a href="javascript:;">Health & Hygiene</a></li>
                        <li><a href="javascript:;">Medicals</a></li>
                        <li><a href="javascript:;">Rest, Recreation & Nutrition</a></li>
                        <li><a href="javascript:;">Reward & Recognition</a></li>			
                        <li><a href="javascript:;">Safety & Security</a></li> 
                        <li><a href="javascript:;">Suggestions & Feedback</a></li>
                    </ul>
                </li>
                <li class="menu6">
                    <a href="javascript: void(0);" class="waves-effect">
                        <img src="/jxp/assets/images/billing.png"/> <strong>Billing</strong>
                    </a>
                    <ul class="sub-menu mm-collapse" aria-expanded="false">
                        <li><a href="javascript:;">Budget Management</a></li>
                        <li><a href="javascript:;">Client Invoicing</a></li>
                        <li><a href="javascript:;">Financial Forecasting</a></li>
                        <li><a href="javascript:;">Reports & Dashboards</a></li>
                        <li><a href="javascript:;">Timesheet Management</a></li>
                    </ul>
                </li>
                <li class="menu7 conf_menu <%=(mtp == 7) ? "mm-active active" : ""%>">
                    <a href="javascript: void(0);" class="waves-effect">
                        <img src="/jxp/assets/images/settings.png"/> <strong>Configurations</strong>
                    </a>
                    <ul class="sub-menu mm-collapse mm-show menu_scroll" aria-expanded="false">
                        <li><a href="javascript:;"><b>GENERAL</b></a>
                            
                        <% if (cst(smarr, 41)) {%><li class="<%=(submtp == 53 ? "mm-active" : "")%>"><a class="<%=(submtp == 53 ? "active" : "")%>" href="/jxp/airport/AirportAction.do">Airports</a></li><%}%>
                        <% if (cst(smarr, 41)) {%><li class="<%=(submtp == 41 ? "mm-active" : "")%>"><a class="<%=(submtp == 41 ? "active" : "")%>" href="/jxp/assessmentanswertype/AssessmentAnswerTypeAction.do">Assessment Answer Type</a></li><%}%>
                        <% if (cst(smarr, 40)) {%><li class="<%=(submtp == 40 ? "mm-active" : "")%>"><a class="<%=(submtp == 40 ? "active" : "")%>" href="/jxp/assessmentparameter/AssessmentParameterAction.do">Assessment Parameter</a></li><%}%>
                        <% if (cst(smarr, 42)) {%><li class="<%=(submtp == 42 ? "mm-active" : "")%>"><a class="<%=(submtp == 42 ? "active" : "")%>" href="/jxp/assessmentquestion/AssessmentQuestionAction.do">Assessment Question</a></li><%}%>
                        <% if (cst(smarr, 13)) {%><li class="<%=(submtp == 13 ? "mm-active" : "")%>"><a class="<%=(submtp == 13 ? "active" : "")%>" href="/jxp/assessment/AssessmentAction.do">Assessments</a></li><%}%>
                        <% if (cst(smarr, 6)) {%><li class="<%=(submtp == 6 ? "mm-active" : "")%>"><a class="<%=(submtp == 6 ? "active" : "")%>" href="/jxp/assettype/AssettypeAction.do">Asset Type</a></li><%}%>
                        <% if (cst(smarr, 39)) {%><li class="<%=(submtp == 39 ? "mm-active" : "")%>"><a class="<%=(submtp == 39 ? "active" : "")%>" href="/jxp/bankaccounttype/BankAccountTypeAction.do">Bank Account Type</a></li><%}%>
                        <% if (cst(smarr, 33)) {%><li class="<%=(submtp == 33 ? "mm-active" : "")%>"><a class="<%=(submtp == 33 ? "active" : "")%>" href="/jxp/benefitinformationtype/BenefitinformationtypeAction.do">Benefit Information Type</a></li><%}%>
                        <% if (cst(smarr, 43)) {%><li class="<%=(submtp == 43 ? "mm-active" : "")%>"><a class="<%=(submtp == 43 ? "active" : "")%>" href="/jxp/benefit/BenefitAction.do">Benefit(Positions)</a></li><%}%>
                        <% if (cst(smarr, 31)) {%><li class="<%=(submtp == 31 ? "mm-active" : "")%>"><a class="<%=(submtp == 31 ? "active" : "")%>" href="/jxp/bloodpressure/BloodpressureAction.do">Blood Pressure</a></li><%}%>     
                        <% if (cst(smarr, 24)) {%><li class="<%=(submtp == 24 ? "mm-active" : "")%>"><a class="<%=(submtp == 24 ? "active" : "")%>" href="/jxp/city/CityAction.do">Cities</a></li><%}%>
                        <% if (cst(smarr, 2)) {%><li class="<%=(submtp == 2 ? "mm-active" : "")%>"><a class="<%=(submtp == 2 ? "active" : "")%>" href="/jxp/client/ClientAction.do">Clients</a></li><%}%>
                        <% if (cst(smarr, 27)) {%><li class="<%=(submtp == 27 ? "mm-active" : "")%>"><a class="<%=(submtp == 27 ? "active" : "")%>" href="/jxp/companyindustry/CompanyindustryAction.do">Company Industry</a></li><%}%>
                        <% if (cst(smarr, 5)) {%><li class="<%=(submtp == 5 ? "mm-active" : "")%>"><a class="<%=(submtp == 5 ? "active" : "")%>" href="/jxp/country/CountryAction.do">Country</a></li><%}%>
                        <% if (cst(smarr, 36)) {%><li class="<%=(submtp == 36 ? "mm-active" : "")%>"><a class="<%=(submtp == 36 ? "active" : "")%>" href="/jxp/approvedby/ApprovedbyAction.do">Course Approved By</a></li><%}%>
                        <% if (cst(smarr, 35)) {%><li class="<%=(submtp == 35 ? "mm-active" : "")%>"><a class="<%=(submtp == 35 ? "active" : "")%>" href="/jxp/coursetype/CourseTypeAction.do">Course Type</a></li><%}%>
                        <% if (cst(smarr, 28)) {%><li class="<%=(submtp == 28 ? "mm-active" : "")%>"><a class="<%=(submtp == 28 ? "active" : "")%>" href="/jxp/crewtype/CrewtypeAction.do">Crew Type</a></li><%}%>
                        <% if (cst(smarr, 10)) {%><li class="<%=(submtp == 10 ? "mm-active" : "")%>"><a class="<%=(submtp == 10 ? "active" : "")%>" href="/jxp/currency/CurrencyAction.do">Currency</a></li><%}%> 
                        <% if (cst(smarr, 29)) {%><li class="<%=(submtp == 29 ? "mm-active" : "")%>"><a class="<%=(submtp == 29 ? "active" : "")%>" href="/jxp/experiencedept/ExperienceDeptAction.do"> Departments/Functions</a></li><%}%>
                        <% if (cst(smarr, 37)) {%><li class="<%=(submtp == 37 ? "mm-active" : "")%>"><a class="<%=(submtp == 37 ? "active" : "")%>" href="/jxp/documentissuedby/DocumentissuedbyAction.do">Document Issued By</a></li><%}%>
                        <% if (cst(smarr, 34)) {%><li class="<%=(submtp == 34 ? "mm-active" : "")%>"><a class="<%=(submtp == 34 ? "active" : "")%>" href="/jxp/degree/DegreeAction.do">Education Degree</a></li><%}%>   
                        <% if (cst(smarr, 23)) {%><li class="<%=(submtp == 23 ? "mm-active" : "")%>"><a class="<%=(submtp == 23 ? "active" : "")%>" href="/jxp/qualificationtype/QualificationTypeAction.do">Education Kind</a></li><%}%>
                        <% if (cst(smarr, 38)) {%><li class="<%=(submtp == 38 ? "mm-active" : "")%>"><a class="<%=(submtp == 38 ? "active" : "")%>" href="/jxp/enginetype/EnginetypeAction.do">Engine Type</a></li><%}%>
                        <% if (cst(smarr, 20)) {%><li class="<%=(submtp == 20 ? "mm-active" : "")%>"><a class="<%=(submtp == 20 ? "active" : "")%>" href="/jxp/grade/GradeAction.do"> Grades</a></li><%}%>
                        <% if (cst(smarr, 22)) {%><li class="<%=(submtp == 22 ? "mm-active" : "")%>"><a class="<%=(submtp == 22 ? "active" : "")%>" href="/jxp/hoursofwork/HoursOfWorkAction.do"> Hours Of Work</a></li><%}%>
                        <% if (cst(smarr, 8)) {%><li class="<%=(submtp == 8 ? "mm-active" : "")%>"><a class="<%=(submtp == 8 ? "active" : "")%>" href="/jxp/language/LanguageAction.do">Language</a></li><%}%>
                        <% if (cst(smarr, 19)) {%><li class="<%=(submtp == 19 ? "mm-active" : "")%>"><a class="<%=(submtp == 19 ? "active" : "")%>" href="/jxp/maritialstatus/MaritialStatusAction.do">Marital Status</a></li><%}%>
                        <% if (cst(smarr, 54)) {%><li class="<%=(submtp == 54 ? "mm-active" : "")%>"><a class="<%=(submtp == 54 ? "active" : "")%>" href="/jxp/onboardingkit/OnboardingkitAction.do">Onboarding Kit</a></li><%}%>
                        <% if (cst(smarr, 44)) {%><li class="<%=(submtp == 44 ? "mm-active" : "")%>"><a class="<%=(submtp == 44 ? "active" : "")%>" href="/jxp/port/PortAction.do">Port</a></li><%}%> 
                        <% if (cst(smarr, 12)) {%><li class="<%=(submtp == 12 ? "mm-active" : "")%>"><a class="<%=(submtp == 12 ? "active" : "")%>" href="/jxp/position/PositionAction.do">Positions</a></li><%}%> 
                        <% if (cst(smarr, 9)) {%><li class="<%=(submtp == 9 ? "mm-active" : "")%>"><a class="<%=(submtp == 9 ? "active" : "")%>" href="/jxp/proficiency/ProficiencyAction.do">Proficiency</a></li><%}%>
                        <% if (cst(smarr, 52)) {%><li class="<%=(submtp == 52 ? "mm-active" : "")%>"><a class="<%=(submtp == 52 ? "active" : "")%>" href="/jxp/rejectionreason/RejectionreasonAction.do">Rejection Reason</a></li><%}%>
                        <% if (cst(smarr, 18)) {%><li class="<%=(submtp == 18 ? "mm-active" : "")%>"><a class="<%=(submtp == 18 ? "active" : "")%>" href="/jxp/relation/RelationAction.do">Relation</a></li><%}%>
                        <% if (cst(smarr, 11)) {%><li class="<%=(submtp == 11 ? "mm-active" : "")%>"><a class="<%=(submtp == 11 ? "active" : "")%>" href="/jxp/documenttype/DocumentTypeAction.do">Required Document List</a></li><%}%>
                        <% if (cst(smarr, 50)) {%><li class="<%=(submtp == 50 ? "mm-active" : "")%>"><a class="<%=(submtp == 50 ? "active" : "")%>" href="/jxp/resumetemplate/ResumetemplateAction.do">Resume Template</a></li><%}%>
                        <% if (cst(smarr, 21)) {%><li class="<%=(submtp == 21 ? "mm-active" : "")%>"><a class="<%=(submtp == 21 ? "active" : "")%>" href="/jxp/rotation/RotationAction.do"> Rotations</a></li><%}%>
                        <% if (cst(smarr, 32)) {%><li class="<%=(submtp == 32 ? "mm-active" : "")%>"><a class="<%=(submtp == 32 ? "active" : "")%>" href="/jxp/skills/SkillsAction.do">Skills</a></li><%}%>
                        <% if (cst(smarr, 16)) {%><li class="<%=(submtp == 16 ? "mm-active" : "")%>"><a class="<%=(submtp == 16 ? "active" : "")%>" href="/jxp/timezone/TimezoneAction.do">Time Zone</a></li><%}%>
                        <% if (cst(smarr, 1)) {%><li class="<%=(submtp == 1 ? "mm-active" : "")%>"><a class="<%=(submtp == 1 ? "active" : "")%>" href="/jxp/user/UserAction.do">Users</a></li><%}%>
                        <% if (cst(smarr, 25)) {%><li class="<%=(submtp == 25 ? "mm-active" : "")%>"><a class="<%=(submtp == 25 ? "active" : "")%>" href="/jxp/vaccine/VaccineAction.do">Vaccination Name</a></li><%}%>
                        <% if (cst(smarr, 26)) {%><li class="<%=(submtp == 26 ? "mm-active" : "")%>"><a class="<%=(submtp == 26 ? "active" : "")%>" href="/jxp/vaccinetype/VaccinetypeAction.do">Vaccination Type</a></li><%}%>
                        <% if (cst(smarr, 15)) {%><li class="<%=(submtp == 15 ? "mm-active" : "")%>"><a class="<%=(submtp == 15 ? "active" : "")%>" href="/jxp/verificationcheckpoint/VerificationcheckpointAction.do">Verification Checkpoints</a></li><%}%>
                        <% if (cst(smarr, 30)) {%><li class="<%=(submtp == 30 ? "mm-active" : "")%>"><a class="<%=(submtp == 30 ? "active" : "")%>" href="/jxp/experiencewaterdepth/ExperienceWaterDepthAction.do"> Water Depth</a></li><%}%>
                        <% if (cst(smarr, 3)) {%><li class="<%=(submtp == 3 ? "mm-active" : "")%>"><a class="<%=(submtp == 3 ? "active" : "")%>" href="/jxp/access/AccessAction.do">Access Report</a></li><%}%>
                        <% if (cst(smarr, 17)) {%><li class="<%=(submtp == 17 ? "mm-active" : "")%>"><a class="<%=(submtp == 17 ? "active" : "")%>" href="/jxp/maillog/MaillogAction.do">Maillog Report</a></li><%}%>

                        <li><a href="javascript:;"><b>CREW MANAGEMENT</b></a>
                        <% if (cst(smarr, 48)) {%><li class="<%=(submtp == 48 ? "mm-active" : "")%>"><a class="<%=(submtp == 48 ? "active" : "")%>" href="/jxp/checkpoint/CheckPointAction.do">Compliance Checkpoint</a></li><%}%>
                        <% if (cst(smarr, 44)) {%><li class="<%=(submtp == 54 ? "mm-active" : "")%>"><a class="<%=(submtp == 54 ? "active" : "")%>" href="/jxp/employee/EmployeeAction.do">Employee</a></li><%}%> 
                        <li><a href="javascript:;">Enrollment Forms</a></li>
                        <li><a href="javascript:;">Mobilization</a></li>
                        <li><a href="javascript:;">Onboarding</a></li>
                        <li><a href="javascript:;">Talent Pool</a></li>
                        <li><a href="javascript:;"><b>TRAINING & DEVELOPMENT</b></a>
                    </ul>
                </li>
                <li class="menu8 <%=(mtp == 8) ? "mm-active active" : ""%>">
                    <a href="/jxp/user/LogoutAction.do" class="waves-effect">
                        <img src="/jxp/assets/images/log-out.png"/> <strong>Log Out</strong>
                    </a>


                </li>
            </ul>
        </div>
    </div>
</div>