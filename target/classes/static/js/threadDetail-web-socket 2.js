var stompClient = null;
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/response/posting', function(callback) {
            console.log("subscribe");
            console.log(callback);

            showResponse(JSON.parse(callback.body));
        });
    });
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}



function showResponse(response) {
    $(".container3").prepend(`
    <div class="TimeLine TimeLine2">
        <div class="flec flec2">
            
            <div class="Frame2 Frame5555">
                <div class="comevgj"><p class="vfdi">new</p></div>
                
                <!-- ユーザのアイコン画像 -->
                <div class="vvrhsnor vresvn"><button class="clickshow"
                        onClick="location.href='/mypage?user_id=` + response.user_id + `'">
                        <img src="` + response.icon + `" class="rogo2">
                    </button></div>
                <div class="text-user9">
                    <p>` + response.user_name + `</p>
                </div>
            </div>

            <div class="Frame3 balloon1-left Frame4">
                <button type="button" class="clickshow" onclick="location.href='/Hiroba/RessDetail/` + response.url + `?response_id=` + response.response_id + `&user_id=` + response.user_id + `'">
                    <div class="coment coment2">
                        <div class="user">
                            <p>` + response.response_name + `</p>
                        </div>
                    </div>

                    <div class="Content Content2">
                        <div class="douga douga2">

                            <div class="samneiru samneiru2">
                                <!-- 動画のサムネ画像 -->
                                <img src="` + response.thumbnail + `" alt="">
                            </div>

                            <div class="con23 con232">
                                <div class="content2 content3">
                                    <p class="titl">` + response.movie_name + `</p>
                                </div>
                            </div>

                        </div>
                        <div class="content3 content4">
                            <div class="come ine come2 ine2">
                                <p class="iine">いいね</p>
                                <span>` + response.like + `</span>
                            </div>

                            <div class="come kyou  come2 kyou2">
                                <p class="iine2">共有</p>
                                <span>` + response.share + `</span>
                            </div>

                            <div class="come come2">
                                <a
                                    href="/Hiroba/delete_response/ThreadDetail?response_creater=` + response.response_creater + `&response_id=` + response.response_id + `&thread_id=` + response.thread_id + `">
                                    <span class="jaf">削除する</span>
                                </a>
                            </div>
                        </div>
                    </button>
            </div>
        </div>
    </div>
    `);
}

function sendResponse() {
    stompClient.send("/app/response", {}, JSON.stringify({
        'url':$("#url-pc-thread").val(), 
        'response_name':$("#response_name").val(), 
        "thread_id":$("#thread_id").val(),
        "user_id":$("#user_id").val()
    }));
}

//投稿のポップアップの削除
var closeBtn = document.querySelectorAll('.close-modal')

function closeModal(){
    const modalContainer = document.getElementById('modal-container')
    modalContainer.classList.remove('show-modal')
}
closeBtn.forEach(c => c.addEventListener('click', closeModal))



$(function () {
    $("#form-thread").on('submit', function (e) {

        if(($("#url-pc-thread").val()))

        $("#url-pc-thread").val("");
        $("#response_name").val("");
        $("#video-player").attr("src", "");

        e.preventDefault();
        closeModal();
    });
    $( "#send" ).click(function() { sendResponse(); });
});

setTimeout("connect()", 3000);