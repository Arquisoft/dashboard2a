$( window ).on("load", function() {

    var root = 'http://localhost:8090';

    var voteSuggestionTemplate = "<tr><td>{data.citizen.name} {data.citizen.surname}</td><td>{data.type}</td></tr>";
    let commentTemplate = `<tr id=\"comment{data.id}\">
  <td>{data.id}</td>
  <td>{data.code}</td>
  <td<a href="/dashboard/comment/{data.id}">{data.description}</a></td>
  <td>{data.citizen.name} {data.citizen.surname}</td>
  <td class="positive-vote">{data.votes_positive}</td>
  <td class="negative-vote">{data.votes_negative}</td>
  </tr>`;
    var labelsVotes = ['Si', 'No'];
    var pieChart = null;

    initializeChart();

    var source = new EventSource(root+'/updates');

    source.onopen = function() {
        console.log("Conectado a " + root);
    };

    source.addEventListener('event', function(event){
        let data = JSON.parse(event.data);

        if(data.eventId == "newVoteSuggestion" && data.data.suggestion.id == suggestionId){
            console.log("Recieved event");
            console.log(data.data.suggestion);

            let row = nano(voteSuggestionTemplate, data);
            let theRow = $(row).addClass(data.data.type.toLowerCase() + "-vote");
            $('#voteSuggestionTable tbody').append(theRow);

            paintVote(data.data.type)

            updateSuggestion(data.data.suggestion);
        }

        if(data.eventId == "newComnent"){
            console.log("Recieved event");
            let row = nano(commentTemplate, data);
            let theRow = $(row);
            console.log(row);
            console.log(theRow);
            $('#commentTable tbody').append(theRow);
            updateSuggestion(data.data.suggestion);
        }

        if(data.eventId == "newVoteComment"){
            console.log("Recieved event");
            let suggestionRow = '#comment' + data.data.comment.id;
            if(data.data.type =="POSITIVE"){
                $(suggestionRow+" .positive-vote").html(data.data.comment.votes_positive);
            }else{
                $(suggestionRow+" .negative-vote").html(data.data.comment.votes_negative);
            }
        }


    });

    function updateSuggestion(suggestion) {
        $('#suggestionTitle').html(suggestion.title);
        $('#suggestionText').html(suggestion.description);
    }

    //--Funciones de grafico
    function paintVote(type) {
        if(type=="POSITIVE"){
            votos[0] = votos[0] + 1;
            $('#positive-vote-count').html(votos[0]);
        }else{
            votos[1] = votos[1] + 1;
            $('#negative-vote-count').html(votos[1]);
        }

        //Chart
        pieChart.update();

    }

    function initializeChart() {
        let graphData = {
            labels: labelsVotes,
            series: votos,
        };

        let options = {
            labelInterpolationFnc: function(value) {
                return value
            },
            width: 400,
            height: 400,
        };

        pieChart = new Chartist.Pie('.vote-chart', graphData, options);

    }
});


/* Nano Templates - https://github.com/trix/nano */

function nano(template, data) {
    return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
        var keys = key.split("."), v = data[keys.shift()];
        for (var i = 0, l = keys.length; i < l; i++) v = v[keys[i]];
        return (typeof v !== "undefined" && v !== null) ? v : "";
    });
}
