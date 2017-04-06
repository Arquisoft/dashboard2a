$( window ).on("load", function() {

    var root = 'http://localhost:8090';

    var voteCommentTemplate = "<tr><td>{data.citizen.name} {data.citizen.surname}</td><td>{data.type}</td></tr>";
    var labelsVotes = ['Si', 'No'];
    var pieChart = null;

    initializeChart();

    var source = new EventSource(root+'/updates');

    source.onopen = function() {
        console.log("Conectado a " + root);
    };

    source.addEventListener('event', function(event){
        let data = JSON.parse(event.data);

        if(data.eventId == "newVoteComment" && data.data.comment.id == commentId){
            console.log("Recieved event");
            console.log(data.data);

            let row = nano(voteCommentTemplate, data);
            let theRow = $(row).addClass(data.data.type.toLowerCase() + "-vote");
            $('#voteCommentTable tbody').append(theRow);

            paintVote(data.data.type)

            updateComment(data.data.comment);
        }


    });

    function updateComment(comment) {
        $('#commentText').html(comment.description);
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
