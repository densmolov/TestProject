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
            toastr.success("The CD disc has been added successfully.");}
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
    $("#mainDiv").load("pages/showAll.html #secondDiv");
    renderTheTable();
}


function nextPageClick() {
    pageNumber++;
    renderTheTable();
}

function previousPageClick() {
    pageNumber--;
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
            console.log("incomingString = " + incomingString);
            var parser = new DOMParser();
            var xmlDoc = parser.parseFromString(incomingString, "text/xml");
            discsQuantity = xmlDoc.getElementsByTagName("PAGES")[0].childNodes[0].nodeValue;
            updatePaging();
            var allCdNodes = xmlDoc.getElementsByTagName("CD");
            var str = ("<table class=\"table table-condensed\">" +
                "<tr><td>TITLE</td><td>ARTIST</td><td>COUNTRY</td>" +
                "<td>COMPANY</td><td>PRICE</td><td>YEAR</td></tr>");
            for (i = 0; i < allCdNodes.length; i++) {
                str = str + ("<tr><td>");
                str = str + (allCdNodes[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);
                str = str + ("</td><td>");
                str = str + (allCdNodes[i].getElementsByTagName("ARTIST")[0].childNodes[0].nodeValue);
                str = str + ("</td><td>");
                str = str + (allCdNodes[i].getElementsByTagName("COUNTRY")[0].childNodes[0].nodeValue);
                str = str + ("</td><td>");
                str = str + (allCdNodes[i].getElementsByTagName("COMPANY")[0].childNodes[0].nodeValue);
                str = str + ("</td><td>");
                str = str + (allCdNodes[i].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue);
                str = str + ("</td><td>");
                str = str + (allCdNodes[i].getElementsByTagName("YEAR")[0].childNodes[0].nodeValue);
                str = str + ("</td></tr>");
            }
            str = str + "</table>";
            document.getElementById("tableCD").innerHTML = str;
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
    $("#secondDiv tbody").html("");
}