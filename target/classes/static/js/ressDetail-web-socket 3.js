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
    <div class="tuiti fadein-before">
        <div id="user_left" class="user-ga">
            <button class="clickshow"
                onClick="location.href='/mypage?user_id=` + response.user_id + `'">
                <div id="user_icon">
                    <img src="` + response.icon + `" alt="">
                </div>
                <p>` + response.user_name + `</p>
            </button>
        </div>

        <div id="user">
            <p>` + response.comment + `</p>
            <p id="time">` + response.submit_time + `</p>
        </div>           
    </div>
    `)

    setTimeout(() => {
        $(".fadein-before").addClass("fadein-after");
    }, 10);
}

function sendResponse() {
    
    stompClient.send("/app/comment", {}, JSON.stringify({
        'comment':$("#CommentText").val(),
        'response_id':$("#response_id").val(), 
        "response_creater":$("#response_creater").val(),
        "user_id":$("#user_id").val()
    }));
}


// $(function () {

//     $("#comment_form").on('submit', function (e) {
//         e.preventDefault();
        
//         sendResponse();

//         $("#CommentText").val("");
//     });
// });

// setTimeout("connect()", 3000);