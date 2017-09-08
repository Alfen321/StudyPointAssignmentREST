PersonsRefresh();

document.getElementById("PersonsRefresh").addEventListener("click", function() {
    PersonsRefresh();
});

document.getElementById("PersonAdd").addEventListener("click", function() {
    PersonAdd();
    document.getElementById("TablePersonsBody").innerHTML += "<tr><td>New</td><td>" + document.getElementById("PersonFirstName").value + "</td><td>" + document.getElementById("PersonLastName").value + "</td><td>" + document.getElementById("PersonPhoneNumber").value + "</td></tr>";
});

document.getElementById("PersonEdit").addEventListener("click", function() {
    PersonEdit();
    PersonsRefresh();
});

document.getElementById("PersonDelete").addEventListener("click", function() {
    PersonDelete();
    PersonsRefresh();
});

var persons;

function PersonsRefresh() {
    fetch("api/person/all", { method: "get" })
        .then(function(response) {
            return response.json();
        })
        .then(function(json) {
            document.getElementById("TablePersonsBody").innerHTML = "";
            persons = json;
            var rows = "";

            for (var i in json) {
                rows += '<tr>';
                rows += '<td>' + json[i].id + '</td>';
                rows += '<td>' + json[i].firstName + '</td>';
                rows += '<td>' + json[i].lastName + '</td>';
                rows += '<td>' + json[i].phoneNumber + '</td>';
                /*rows += '<td><input type="button" onclick="PersonDeleteID(' + json[i].id + ')" value="Delete" /></td>';*/
                rows += '<td><input type="button" onclick="PersonEditID(' + json[i].id + ')" value="Edit" /></td>';
                rows += '</tr>';
            }

            document.getElementById("TablePersonsBody").innerHTML = rows;
        })
        .catch(function(error) {
            alert("Unable to refresh!");
        });
}

function PersonAdd() {
    var person = {
        firstName: document.getElementById("PersonFirstName").value,
        lastName: document.getElementById("PersonLastName").value,
        phoneNumber: Number(document.getElementById("PersonPhoneNumber").value)
    };

    fetch("api/person", {
            method: "post",
            body: JSON.stringify(person),
            headers: new Headers({ 'content-type': 'application/json' })
        })
        .then(function(response) {
            return response.json();
        })
        .then(function(json) {
            alert("Person added!");
        })
        .catch(function(error) {
            alert("Person not added!");
        });
}

function PersonDeleteID(id) {
    var url = "api/person/";
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", url + '/' + id, true);
    xhr.onload = function() {
        var person = JSON.parse(xhr.responseText);
        if (xhr.readyState == 4 && xhr.status == "200") {
            console.table(person);
            PersonsRefresh();
        } else {
            console.error(person);
        }
    }
    xhr.send(null);
}

function PersonDelete() {
    var id = document.getElementById("PersonID").value;

    var url = "api/person/";
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", url + '/' + id, true);
    xhr.onload = function() {
        var person = JSON.parse(xhr.responseText);
        if (xhr.readyState == 4 && xhr.status == "200") {
            console.table(person);
            PersonsRefresh();
        } else {
            alert("Person not deleted!");
            console.error(person);
        }
    }
    xhr.send(null);
}

function PersonEditID(id) {
    var person;
    persons.forEach(function(p) {
        if (p.id == id) {
            person = p;
        }
    }, this);
    document.getElementById("PersonID").value = person.id;
    document.getElementById("PersonFirstName").value = person.firstName;
    document.getElementById("PersonLastName").value = person.lastName;
    document.getElementById("PersonPhoneNumber").value = person.phoneNumber;
}

function PersonEdit() {
    var person = {
        id: document.getElementById("PersonID").value,
        firstName: document.getElementById("PersonFirstName").value,
        lastName: document.getElementById("PersonLastName").value,
        phoneNumber: Number(document.getElementById("PersonPhoneNumber").value)
    };

    fetch("api/person", {
            method: "put",
            body: JSON.stringify(person),
            headers: new Headers({ 'content-type': 'application/json' })
        })
        .then(function(response) {
            return response.json();
        })
        .then(function(json) {
            alert("Person edited!");
        })
        .catch(function(error) {
            alert("Person not edited!");
        });
}