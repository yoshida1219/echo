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
    $(".d3-1").prepend(`
    <div class="tuiti">
        <p>` + response.user_name + `</p>
        <p>` + response.comment + `</p>
        <p>` + response.submit_time + `</p>
    </div>
    `)
}

function sendResponse() {
    
    stompClient.send("/app/comment", {}, JSON.stringify({
        'comment':$("#CommentText").val(),
        'response_id':$("#response_id").val(), 
        "response_creater":$("#response_creater").val(),
        "user_id":$("#user_id").val()
    }));
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
        sendResponse();

        $("#CommentText").val("");
    });
});

setTimeout("connect()", 3000);