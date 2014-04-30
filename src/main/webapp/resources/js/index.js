var discsQuantity = 0;
var totalPages = 0;
var pageNumber = 1;

function submitForm() {
    $.ajax({
        type: "POST",
        url: "/createDisc",
        data: $("#createDiscForm").serialize(),
        success: function () {
            if (formValidation()) {
                $("#createDiscForm").each(function () {
                    this.reset();
                });
                toastr.success("The CD disc has been added successfully.");
            }
        },
        error: function (e) {
            toastr.error("Error: " + e);
        }
    });
    return false;
}

function formValidation() {
    if ($('#createDiscForm').validationEngine('validate')) {
        return true;
    }
}

function downloadCatalog() {
    window.location = '/downloadCatalog';
}

function redirectToDownloadFilePage() {
    $("#mainDiv").load("pages/downloadXMLfile.html #secondDiv");
}

function redirectToCreateDiscPage() {
    $("#mainDiv").load("pages/index.html #secondDiv");
}

function redirectToShowAllPage() {
    $("#mainDiv").load("pages/showAll.html #secondDiv", function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success") {
            ajaxCallTable();
        }
    });
}

function nextPageClick() {
    pageNumber++;
    renderTheTable();
}

function previousPageClick() {
    if (pageNumber > 1) {
        pageNumber--;
    }
    renderTheTable();
}

function firstPageClick() {
    pageNumber = 1;
    renderTheTable();
}

function lastPageClick() {
    pageNumber = totalPages;
    renderTheTable();
}

function ajaxCallTable() {
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            makeTheTable(xmlhttp.responseText);
        }
    };
    xmlhttp.open("GET", "/showData" + '?' + $.param({pageNumber: this.pageNumber}), false);
    xmlhttp.send();
}

function makeTheTable(incomingString) {
    console.log("incomingString = " + incomingString);
    var parser = new DOMParser();
    var xmlDoc = parser.parseFromString(incomingString, "text/xml");
    discsQuantity = xmlDoc.getElementsByTagName("PAGES")[0].childNodes[0].nodeValue;
    updatePaging();
    var allCdNodes = xmlDoc.getElementsByTagName("CD");
    var str = ("<table class=\"table table-condensed\">" +
        "<thead><tr><td>TITLE</td><td>ARTIST</td><td>COUNTRY</td>" +
        "<td>COMPANY</td><td>PRICE</td><td>YEAR</td></tr></thead><tbody>");
    for (var i = 0; i < allCdNodes.length; i++) {
        str += ("<tr><td>");
        str += (allCdNodes[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);
        str += ("</td><td>");
        str += (allCdNodes[i].getElementsByTagName("ARTIST")[0].childNodes[0].nodeValue);
        str += ("</td><td>");
        str += (allCdNodes[i].getElementsByTagName("COUNTRY")[0].childNodes[0].nodeValue);
        str += ("</td><td>");
        str += (allCdNodes[i].getElementsByTagName("COMPANY")[0].childNodes[0].nodeValue);
        str += ("</td><td>");
        str += (allCdNodes[i].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue);
        str += ("</td><td>");
        str += (allCdNodes[i].getElementsByTagName("YEAR")[0].childNodes[0].nodeValue);
        str += ("</td></tr>");
    }
    str += "</tbody></table>";
    $("#tableCdDiv").html(str);
}

function renderTheTable() {
    $.ajax({
        type: "GET",
        url: "/showData" + '?' + $.param({pageNumber: this.pageNumber}),
        async: false,
        headers: {
            Accept: "text/plain; charset=UTF-8",
            "Content-Type": "text/plain; charset=UTF-8"
        },
        success: function (incomingString) {
            makeTheTable(incomingString);
        }
    })
}

function updatePaging() {
    console.log("discsQuantity = " + discsQuantity);
    totalPages = Math.ceil(discsQuantity / 5);
    console.log("totalPages = " + totalPages);
    $("#previous").attr("disabled", false);
    $("#next").attr("disabled", false);
    $("#first").attr("disabled", false);
    $("#last").attr("disabled", false);
    if (pageNumber === 1) {
        $("#previous").attr("disabled", true);
        $("#first").attr("disabled", true);
    }
    if (pageNumber === totalPages) {
        $("#next").attr("disabled", true);
        $("#last").attr("disabled", true);
    }
    if (totalPages === 1) {
        $("#first").attr("disabled", true);
        $("#last").attr("disabled", true);
    }
    if (totalPages === 0) {
        $("#previous").attr("disabled", true);
        $("#next").attr("disabled", true);
        $("#first").attr("disabled", true);
        $("#last").attr("disabled", true);
    }
    $("#secondDiv").find("tbody").html("");
}