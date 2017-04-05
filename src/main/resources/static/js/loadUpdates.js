$( window ).on("load", function() {

    let root = 'http://localhost:8090';
    let commentTemplate = "<tr><td>{data.id}</td><td>{data.code}</td><td>{data.description}</td><td>{data.citizen.name} {data.citizen.surname}</td></tr>"
    var source = new EventSource(root+'/updates');

    source.onopen = function() {
        console.log("Conectado a " + root);
    };

    source.addEventListener('event', function(event){
        console.log("Recieved event");
        let data = JSON.parse(event.data);

        if(data.eventId = "newComnent"){
            let row = nano(commentTemplate, data);
            let theRow = $(row);
            $('#commentTable tbody').append(theRow);
        }


    });
});

/* Nano Templates - https://github.com/trix/nano */

function nano(template, data) {
    return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
        var keys = key.split("."), v = data[keys.shift()];
        for (var i = 0, l = keys.length; i < l; i++) v = v[keys[i]];
        return (typeof v !== "undefined" && v !== null) ? v : "";
    });
}

