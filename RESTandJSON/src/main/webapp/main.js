function sampleDataRefresh() {
    var numberOfSamples = document.getElementById('NumberOfData').value;
    var startingIndex = document.getElementById('StartingIndex').value;
    fetch("api/sampleData/" + numberOfSamples + "/" + startingIndex, { method: "get" })
        .then(function (response) {
            return response.json();
        })
        .then(function (json) {
            document.getElementById('table').innerHTML = table(json);
        })
        .catch(function (error) {
            alert("Unable to refresh!");
        });
}

var table = function (array) {
    var content = "<thead><tr>";
    for (item in array[0]) {
        content += "<th>" + item + "</th>"
    }
    content += "</tr></thead>";
    var mapped = array.map(
        function (items) {
            var list = "<tr>";
            for (key in items) {
                list += "<td>" + items[key] + "</td>"
            }
            list += "</tr>";
            return list;
        }
    );
    var joined = mapped.join("");
    return "<table>" + content + joined + "</table>";
}