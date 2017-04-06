$( window ).on("load", function() {

    var root = 'http://localhost:8090';

    var voteSuggestionTemplate = "<tr><td>{data.citizen.name} {data.citizen.surname}</td><td>{data.type}</td></tr>";
    var labelsVotes = ['Si', 'No'];
    var pieChart = null;

    initializeChart();

    var source = new EventSource(root+'/updates');

    source.onopen = function() {
        console.log("Conectado a " + root);
    };

    source.addEventListener('event', function(event){
        let data = JSON.parse(event.data);
        if(data.eventId == "newVoteSuggestion"){
            console.log(data.data.suggestion.id);
        }
        if(data.eventId == "newVoteSuggestion" && data.data.suggestion.id == suggestionId){
            console.log("Recieved event");
            console.log(data.data.suggestion);

            let row = nano(voteSuggestionTemplate, data);
            let theRow = $(row).addClass(data.data.type.toLowerCase() + "-vote");
            $('#voteSuggestionTable tbody').append(theRow);

            paintVote(data.data.type)

            updateSuggestion(data.data.suggestion);
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

