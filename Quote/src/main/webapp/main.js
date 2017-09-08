QuotesRefresh();

var quotes;

document.getElementById("QuotesRefresh").addEventListener("click", function () {
    QuotesRefresh();
});

document.getElementById("randomQuotesRefresh").addEventListener("click", function () {
    randomQuote();
});

document.getElementById("QuoteAdd").addEventListener("click", function () {
    QuoteAdd();
    document.getElementById("TableQuotesBody").innerHTML += "<tr><td>New</td><td>" + document.getElementById("QuoteFirstName").value + "</td><td>" + document.getElementById("QuoteLastName").value + "</td><td>" + document.getElementById("QuotePhoneNumber").value + "</td></tr>";
});

document.getElementById("QuoteEdit").addEventListener("click", function () {
    QuoteEdit();
    QuotesRefresh();
});

function QuotesRefresh() {
    fetch("api/quote/all", { method: "get" })
        .then(function (response) {
            return response.json();
        })
        .then(function (json) {
            quotes = json;

            document.getElementById("TableQuotesBody").innerHTML = "";

            var rows = "";

            console.log(json);

            for (var i in quotes) {
                rows += '<tr>';
                rows += '<td>' + json[i].id + '</td>';
                rows += '<td>' + str + '</td>';
                rows += '<td><input type="button" onclick="QuoteEditID(' + i + ')" value="Edit" /></td>';
                rows += '</tr>';
            }

            document.getElementById("TableQuotesBody").innerHTML = rows;
        })
        .catch(function (error) {
            alert("Unable to refresh!");
        });
}

function randomQuote() {
    fetch("api/quote/random", { method: "get" })
        .then(function (response) {
            return response.json();
        })
        .then(function (json) {
            alert(json);
            document.getElementById('QuoteOfTheDay').innerHTML = json;
        })
        .catch(function (error) {
            alert("Unable to refresh!");
        });
}

function QuoteAdd() {
    var Quote = {
        firstName: document.getElementById("QuoteFirstName").value,
        lastName: document.getElementById("QuoteLastName").value,
        phoneNumber: Number(document.getElementById("QuotePhoneNumber").value)
    };

    fetch("api/Quote", {
        method: "post",
        body: JSON.stringify(Quote),
        headers: new Headers({ 'content-type': 'application/json' })
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (json) {
            alert("Quote added!");
        })
        .catch(function (error) {
            alert("Quote not added!");
        });
}

function QuoteDeleteID(id) {
    var url = "api/quote/";
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", url + '/' + id, true);
    xhr.onload = function () {
        QuotesRefresh();
    }
    xhr.send(null);
}

function QuoteEditID(id) {
    var Quote;


    alert('Edit quote: ' + Quote);

    document.getElementById("QuoteID").value;
    document.getElementById("Quote").value = Quote;
}