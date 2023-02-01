var stompClient = null;
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {

        stompClient.subscribe('/comment/posting', function(callback) {
            showResponse(JSON.parse(callback.body));
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}



function showResponse(response) {
    $(".cent-1").prepend();
}

function sendResponse() {
    
    stompClient.send("/app/comment", {}, JSON.stringify({
        'comment':$("#CommentText").val(),
        'response_id':$("#response_id").val(), 
        "response_creater":$("#response_creater").val()
    }));
}


$(function () {
    $("form").on('submit', function (e) {
        $("#CommentText").val("");
        console.log("ok");
        console.log($("#CommentText").val());

        e.preventDefault();
        sendResponse();
    });
});

setTimeout("connect()", 3000);