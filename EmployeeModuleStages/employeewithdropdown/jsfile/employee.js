function resetFilter()
{
    document.forms[0].search.value = "";
    searchFormAjax('s', '-1');
}
function showDetail(id)
{
    document.employeeForm.doView.value = "yes";
    document.employeeForm.doModify.value = "no";
    document.employeeForm.doAdd.value = "no";
    document.employeeForm.employeeId.value = id;
    document.employeeForm.action = "../employee/EmployeeAction.do";
    document.employeeForm.submit();
}

function handleKeySearch(e)
{
    var key = e.keyCode || e.which;
    if (key === 13)
    {
        e.preventDefault();
        searchFormAjax('s', '-1');
    }
}

function checkSearch()
{
    if (trim(document.forms[0].search.value) != "")
    {
        if (validdesc(document.forms[0].search) == false)
        {
            document.forms[0].search.focus();
            return false;
        }
    }
    return true;
}

function searchFormAjax(v, v1)
{
    if (checkSearch())
    {
        var url = "../ajax/employee/getinfo.jsp";
        var httploc = getHTTPObject();
        var getstr = "";
        var next_value = escape(document.employeeForm.nextValue.value);
        var search_value = escape(document.employeeForm.search.value);
        getstr += "nextValue=" + next_value;
        getstr += "&next=" + v;
        getstr += "&search=" + search_value;
        getstr += "&doDirect=" + v1;
        httploc.open("POST", url, true);
        httploc.onreadystatechange = function ()
        {
            if (httploc.readyState == 4)
            {
                if (httploc.status == 200)
                {
                    var response = httploc.responseText;
                    document.getElementById('ajax_cat').innerHTML = '';
                    document.getElementById('ajax_cat').innerHTML = response;
                }
            }
        };
        httploc.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        httploc.setRequestHeader("Content-length", getstr.length);
        httploc.setRequestHeader("Connection", "close");
        httploc.send(getstr);
        document.getElementById('ajax_cat').innerHTML = "<div><img src='../assets/images/loading.gif' align='absmiddle'/>Please wait. Loading...</div>";
    }
}
function goback()
{
    if (document.employeeForm.doView)
        document.employeeForm.doView.value = "no";
    if (document.employeeForm.doCancel)
        document.employeeForm.doCancel.value = "yes";
    if (document.employeeForm.doSave)
        document.employeeForm.doSave.value = "no";
    document.employeeForm.action = "../employee/EmployeeAction.do";
    document.employeeForm.submit();
}

function sortForm(colid, updown)
{
    for (i = 1; i <= 1; i++)
    {
        document.getElementById("img_" + i + "_2").className = "sort_arrow deactive_sort";
        document.getElementById("img_" + i + "_1").className = "sort_arrow deactive_sort";
    }
    if (updown == 2)
    {
        document.getElementById("img_" + colid + "_2").className = "sort_arrow active_sort";

    } else
    {
        document.getElementById("img_" + colid + "_" + updown).className = "sort_arrow active_sort";
    }
    var httploc = getHTTPObject();
    var url_sort = "../ajax/employee/sort.jsp";
    var getstr = "";
    var nextValue = 0;
    if (document.employeeForm.nextValue)
        nextValue = document.employeeForm.nextValue.value;
    getstr += "nextValue=" + nextValue;
    getstr += "&col=" + colid;
    getstr += "&updown=" + updown;
    httploc.open("POST", url_sort, true);
    httploc.onreadystatechange = function ()
    {
        if (httploc.readyState == 4)
        {
            if (httploc.status == 200)
            {
                var response = httploc.responseText;
                document.getElementById('sort_id').innerHTML = '';
                document.getElementById('sort_id').innerHTML = response;
            }
        }
    };
    httploc.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    httploc.setRequestHeader("Content-length", getstr.length);
    httploc.setRequestHeader("Connection", "close");
    httploc.send(getstr);
    document.getElementById('sort_id').innerHTML = "<div><img src='../assets/images/loading.gif' align='absmiddle'/>Please wait. Loading...</div>";
}

function addForm()
{
    document.employeeForm.doModify.value = "no";
    document.employeeForm.doView.value = "no";
    document.employeeForm.doAdd.value = "yes";
    document.employeeForm.action = "../employee/EmployeeAction.do";
    document.employeeForm.submit();
}

function modifyForm(id)
{
    document.employeeForm.doModify.value = "yes";
    document.employeeForm.doView.value = "no";
    document.employeeForm.doAdd.value = "no";
    document.employeeForm.employeeId.value = id;
    document.employeeForm.action = "../employee/EmployeeAction.do";
    document.employeeForm.submit();
}

function submitForm()
{
    if (document.forms[0].getElementsByTagName("input"))
    {
        var inputElements = document.forms[0].getElementsByTagName("input");
        for (i = 0; i < inputElements.length; i++)
        {
            if (inputElements[i].type == "text")
            {
                inputElements[i].value = trim(inputElements[i].value);
            }
        }
    }
    if (checkEmployee())
    {
        document.getElementById('submitdiv').innerHTML = "<img src='../assets/images/loading.gif' align='absmiddle' />";
        document.employeeForm.doSave.value = "yes";
        document.employeeForm.doCancel.value = "no";
        document.employeeForm.action = "../employee/EmployeeAction.do";
        document.employeeForm.submit();
    }
}
function checkEmployee()
{
    if (trim(document.employeeForm.name.value) == "")
    {
        Swal.fire({
            title: "Please enter name.",
            didClose:() => {
                document.employeeForm.name.focus();
            }
        })
        return false;
    }
    if (validnamewithand(document.employeeForm.name) == false)
    {
        return false;
    }
    if (trim(document.employeeForm.lastname.value) == "")
    {
        Swal.fire({
            title: "Please enter Last Name.",
            didClose: () => {
                document.employeeForm.lastname.focus();
            }
        })
        return false;
    }
    if (validnamewithand(document.employeeForm.lastname) == false)
    {
        return false;
    }
    //end 
    if (trim(document.employeeForm.emailid.value) == "")
    {
        Swal.fire({
            title: "Please enter ISD emailid.",
            didClose: () => {
                document.employeeForm.emailid.focus();
            }
        })
        return false;
    }
    if (checkEmailAddress(document.employeeForm.emailid) == false)
    {
        return false;
    }
    if (trim(document.employeeForm.number.value) == "")
    {
        Swal.fire({
            title: "Please enter Number",
            didClose: () => {
                document.employeeForm.number.focus();
            }
        })
        return false;
    }
    if (validnum(document.employeeForm.number) == false)
    {
        return false;
    }
    
//     if (document.employeeForm.gender.value == "Gender")
//        {
//        Swal.fire({
//        title: "Please select gender.",
//                didClose:() => {
//        document.candidateForm.gender.focus();
//        }
//        })
//                return false;
//        }
    
//    if (document.employeeForm.countryId.value == "-1")
//    {
//        Swal.fire({
//            title: "Please select the country",
//            didClose: () => {
//                document.employeeForm.countryId.focus();
//            }
//        })
//        return false;
//    }
//    if (validnamewithand(document.employeeForm.countryId) == false)
//    {
//        return false;
//    }
//    
//    if (document.employeeForm.cityName.value == "-1")
//    {
//        Swal.fire({
//            title: "Please enter the city",
//            didClose: () => {
//                document.employeeForm.cityName.focus();
//            }
//        })
//        return false;
//    }
//    if (validnum(document.employeeForm.cityName) == false)
//    {
//        return false;
//    }

    var content = tinyMCE.editors["summernote_1"].getContent();
    if (content == "")
    {
        Swal.fire({
            title: "Please enter description.",
            didClose: () => {
                tinyMCE.get("summernote_1").focus();
            }
        })
        return false;
    }
    
    if (document.employeeForm.langfile.value != "")
        {
            if (document.employeeForm.employeeId.value < 0 || document.employeeForm.langfilehidden.value == "")
            {
                if (document.employeeForm.langfile.value == "")
                {
                    Swal.fire({
                    title: "please upload certificate.",
                    didClose:() => {
                    document.employeeForm.langfile.focus();
                    }
                    }) 
                    return false;
                }
            }
        }
        if (document.employeeForm.langfile.value != "")
        {
            if (!(document.employeeForm.langfile.value).match(/(\.(png)|(jpg)|(jpeg)|(pdf))$/i))
            {
                Swal.fire({
                title: "Only .jpg, .jpeg, .png, .pdf are allowed.",
                didClose:() => {
                document.employeeForm.langfile.focus();
                }
                }) 
                return false;
            }
            var input = document.employeeForm.langfile;
                    if (input.files)
            {
                var file = input.files[0];
                        if (file.size > 1024 * 1024 * 5)
                {
                    Swal.fire({
                        title: "File size should not exceed 5 MB.",
                        didClose:() => {
                        document.employeeForm.langfile.focus();
                        }
                        }) 
                        return false;
                }
            }
        }
    
    
    return true;
}
function resetForm()
{
    document.employeeForm.reset();
}

function deleteForm(userId, status, id)
{
    var s = "";
    if (eval(status) == 1)
        s = "<span>The selected item will be <b>deactivated.</b></span>";
    else
        s = "<span>The selected item will be <b>activated.</b></span>";
    Swal.fire({
        title: s + 'Are you sure?',
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirm',
        showCloseButton: true,
        allowOutsideClick: false,
        allowEscapeKey: false
    }).then((result) => {
        if (result.isConfirmed)
        {
            var url = "../ajax/employee/getinfo.jsp";
            var getstr = "";
            var httploc = getHTTPObject();
            var next_value = escape(document.employeeForm.nextValue.value);
            var next_del = "-1";
            if (document.employeeForm.nextDel)
                next_del = escape(document.employeeForm.nextDel.value);
            var search_value = escape(document.employeeForm.search.value);
            getstr += "nextValue=" + next_value;
            getstr += "&nextDel=" + next_del;
            getstr += "&search=" + search_value;
            getstr += "&status=" + status;
            getstr += "&deleteVal=" + userId;
            httploc.open("POST", url, true);
            httploc.onreadystatechange = function ()
            {
                if (httploc.readyState == 4)
                {
                    if (httploc.status == 200)
                    {
                        var response = httploc.responseText;
                        var arr = new Array();
                        arr = response.split('##');
                        var v1 = arr[0];
                        var v2 = trim(arr[1]);
                        document.getElementById('ajax_cat').innerHTML = '';
                        document.getElementById('ajax_cat').innerHTML = v1;
                        if (trim(v2) != "")
                        {
                            Swal.fire(
                                    v2
                                    )
                        }
                    }
                }
            };
            httploc.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            httploc.setRequestHeader("Content-length", getstr.length);
            httploc.setRequestHeader("Connection", "close");
            httploc.send(getstr);
        } else
        {
            if (document.getElementById("flexSwitchCheckDefault_" + id).checked == true)
                document.getElementById("flexSwitchCheckDefault_" + id).checked = false;
            else
                document.getElementById("flexSwitchCheckDefault_" + id).checked = true;
        }
    })
}

function exporttoexcel()
{
    document.employeeForm.action = "../country/CountryExportAction.do";
    document.employeeForm.submit();
}