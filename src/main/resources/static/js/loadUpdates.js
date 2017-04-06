$( window ).on("load", function() {

    let root = 'http://localhost:8090';
    let commentTemplate = "<tr id=\"comment{data.id}\"><td>{data.id}</td><td>{data.code}</td><td>{data.description}</td><td>{data.citizen.name} {data.citizen.surname}</td><td>0</td><td>0</td></tr>"
    let suggestionTemplate = "<tr id=\"suggestion{data.id}\"><td>{data.id}</td><td>{data.code}</td><td>{data.title}</td><td>{data.citizen.name} {data.citizen.surname}</td><td>0</td><td>0</td></tr>"
    var source = new EventSource(root+'/updates');

    source.onopen = function() {
        console.log("Conectado a " + root);
    };

    source.addEventListener('event', function(event){
        console.log("Recieved event");
        let data = JSON.parse(event.data);
        console.log(event);

        if(data.eventId == "newComnent"){
            let row = nano(commentTemplate, data);
            let theRow = $(row);
            $('#commentTable tbody').append(theRow);
        }
        //
        if(data.eventId == "newSuggestion"){
            let row = nano(suggestionTemplate, data);
            let theRow = $(row);
            $('#suggestionTable tbody').append(theRow);
        }

        if(data.eventId == "newVoteComment"){
            let commentID = data.data.comment.id;
            let col = -1;
            if(data.data.type=="POSITIVE"){
                col = 4;
            }else{
                col = 5;
            }
            let commentRow = $(`#comment${commentID}.td`);
            if(commentRow[col]){
                console.log("EXISTE EL OBJETO");
                console.log(commentRow[col]);
            }
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
