$( window ).on("load", function() {

    let root = 'http://localhost:8090';
    let commentTemplate = `<tr id=\"comment{data.id}\">
  <td>{data.id}</td>
  <td>{data.code}</td>
  <td<a href="/dashboard/comment/{data.id}">{data.description}</a></td>
  <td>{data.citizen.name} {data.citizen.surname}</td>
  <td class="positive-vote">{data.votes_positive}</td>
  <td class="negative-vote">{data.votes_negative}</td>
  </tr>`;
    let suggestionTemplate = `<tr id=\"suggestion{data.id}\">
  <td>{data.id}</td>
  <td>{data.code}</td>
  <td><a href="/dashboard/suggestion/{data.id}">{data.title}</a></td>
  <td>{data.citizen.name} {data.citizen.surname}</td>
  <td class="positive-vote">{data.votes_positive}</td>
  <td class="negative-vote">{data.votes_negative}</td>
  </tr>`;
    var source = new EventSource(root+'/updates');

    source.onopen = function() {
        console.log("Conectado a " + root);
    };

    source.addEventListener('event', function(event){
        console.log("Recieved event");
        let data = JSON.parse(event.data);

        if(data.eventId == "newComnent"){
            let row = nano(commentTemplate, data);
            let theRow = $(row);
            console.log(row);
            console.log(theRow);
            $('#commentTable tbody').append(theRow);
        }
        //
        if(data.eventId == "newSuggestion"){
            let row = nano(suggestionTemplate, data);
            let theRow = $(row);
            $('#suggestionTable tbody').append(theRow);
        }

        if(data.eventId == "newVoteSuggestion"){
            let suggestionRow = '#suggestion' + data.data.suggestion.id;

            if(data.data.type =="POSITIVE"){
                $(suggestionRow+" .positive-vote").html(data.data.suggestion.votes_positive);
            }else{
                $(suggestionRow+" .negative-vote").html(data.data.suggestion.votes_negative);
            }
        }

        if(data.eventId == "newVoteComment"){
            let suggestionRow = '#comment' + data.data.comment.id;
            if(data.data.type =="POSITIVE"){
                $(suggestionRow+" .positive-vote").html(data.data.comment.votes_positive);
            }else{
                $(suggestionRow+" .negative-vote").html(data.data.comment.votes_negative);
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
