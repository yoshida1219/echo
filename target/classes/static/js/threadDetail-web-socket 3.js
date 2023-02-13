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
    $(".container3").prepend("<div class='TimeLine TimeLine2'>"
    + "<div class='flec flec2'>"
    + "<div class='Frame2 Frame5555'> <div class='comevgj'><p class='vfdi'>" + response.res_count + "</p></div>"
    + "<div class='vvrhsnor vresvn'><button class='clickshow' "
    + "onClick=location.href=\'/mypage?user_id=" + response.user_id + "\'>"
    + "<img src=" + response.icon + " class='rogo2'>"
    + "</button></div>"
    + "<div class='text-user9'><p>" + response.user_name + "</p></div>"
    + "</div>"

    + "<div class='Frame3 balloon1-left Frame4'>"
    + "<button type='button' class='clickshow'"
    + "onClick=\"location.href='/Hiroba/RessDetail/"+ response.url +"?response_id=" + response.response_id + "&user_id=" + response.response_creater + "'\">"
    + "<div class='coment coment2'>"
    + "<div class='user'><p>" + response.response_name + "</p></div></div>"

    + "<div class='Content Content2'><div class='douga douga2'><div class='samneiru samneiru2'>"
    + "<img src='" + response.thumbnail + "' alt=''></div>"
    + "<div class='con23 con232'><div class='content2 content3'>"
    + "<p class='titl'>" + response.movie_name + "</p></div></div></div>"
    + "<div class='content3 content4'><div class='come ine come2 ine2'><p class='iine2'>いいね:</p><span>" + response.like + "</span></div>"
    + "<div class='come kyou come2 kyou2'><p class='iine2'>共有:</p><span>" + response.share + "</span></div>"

    + "<div class='come come2'>"
    + "<a href='/Hiroba/delete_response/ThreadDetail?response_creater=" + response.response_creater 
    + "&response_id=" + response.response_id + "&thread_id=" + response.thread_id + "\'>"
    + `<svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-trash" width="35" height="35" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
        <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
            <path d="M4 7l16 0"></path>
            <path d="M10 11l0 6"></path>
            <path d="M14 11l0 6"></path>
            <path d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12"></path>
            <path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3"></path>
        </svg></a></div></div></button></div></div>`
    );
}

function sendResponse() {
    stompClient.send("/app/response", {}, JSON.stringify({
        'url':$("#url-pc-thread").val(), 
        'response_name':$("#response_name_pc").val(), 
        "thread_id":$("#thread_id").val(),
        "user_id":$("#user_id").val()
    }));
}

function sendResponseMobile() {
    console.log($("#response_name").val());

    stompClient.send("/app/response", {}, JSON.stringify({
        'url':$("#video-url").val(), 
        'response_name':$("#response_name_mobile").val(), 
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



// $(function () {
//     $("form").on('submit', function (e) {

//         if(($("#url-pc-thread").val()))

//         $("#url-pc-thread").val("");
//         $("#response_name").val("");
//         $("#video-player").attr("src", "");

//         e.preventDefault();
//         // closeModal();
//     });
//     // $( "#send" ).click(function() { sendResponse(); });
// });

// setTimeout("connect()", 3000);

var flag = true;

function checkDuplicatesThread() {
    let error = document.getElementById('urlError_movie').innerHTML
    var inputValue = document.getElementById('url-pc-thread').value;
    var inflag = false
    console.log(inputValue);
    var regex = /v=([A-Za-z0-9-_]{11})/;
    var regex2 = /be\/([A-Za-z0-9-_]{11})/;
    var regex3 = /shorts\/([A-Za-z0-9-_]{11})/;

    if(inputValue.includes("https://www.youtube.com/watch?v=") && inputValue.match(regex)){
        inflag = true;
    }
    if(inputValue.includes("https://youtu.be/") && inputValue.match(regex2)){
        inflag = true;
    }
    if(inputValue.includes("https://www.youtube.com/shorts/") && inputValue.match(regex3)){
        inflag = true;
    }
    if(inputValue.includes("https://youtube.com/shorts/") && inputValue.match(regex3)){
        inflag = true;
    }

    if (inflag) {
        flag = inflag;
        sendResponse();
        closeModal();
        error = '';
        $(".popupModal1>input:nth-child(1)+label+input:nth-child(3)+label+input:nth-child(5):checked+label+.modalPopup2").css("display", "none");
    } else {
        flag = inflag;
        error = '不正なURLです';
        showErrorPopup();
    }

    return false;
}

function checkDuplicatesShare() {
    var inputValue = document.getElementById('url-pc-share').value;
    var inflag = false
    console.log(inputValue);
    let error = document.getElementById('urlError_movie2').innerHTML
    var regex = /v=([A-Za-z0-9-_]{11})/;
    var regex2 = /be\/([A-Za-z0-9-_]{11})/;
    var regex3 = /shorts\/([A-Za-z0-9-_]{11})/;

    if(inputValue.includes("https://www.youtube.com/watch?v=") && inputValue.match(regex)){
        inflag = true;
    }
    if(inputValue.includes("https://youtu.be/") && inputValue.match(regex2)){
        inflag = true;
    }
    if(inputValue.includes("https://www.youtube.com/shorts/") && inputValue.match(regex3)){
        inflag = true;
    }
    if(inputValue.includes("https://youtube.com/shorts/") && inputValue.match(regex3)){
        inflag = true;
    }

    if (inflag) {
        flag = inflag;
        console.log("正常な動作");
        closeModal();
        error = '';
    } else {
        flag = inflag;
        error = '不正なURLです';
        showErrorPopup();
    }
}

function checkDuplicatesUrl() {
    closeModal();
    
    var inputValue = document.getElementById('video-url').value;
    var inflag = false
    console.log(inputValue);
    let error = document.getElementById('urlError_movie3').innerHTML
    var regex = /v=([A-Za-z0-9-_]{11})/;
    var regex2 = /be\/([A-Za-z0-9-_]{11})/;
    var regex3 = /shorts\/([A-Za-z0-9-_]{11})/;

    if(inputValue.includes("https://www.youtube.com/watch?v=") && inputValue.match(regex)){
        inflag = true;
    }
    if(inputValue.includes("https://youtu.be/") && inputValue.match(regex2)){
        inflag = true;
    }
    if(inputValue.includes("https://www.youtube.com/shorts/") && inputValue.match(regex3)){
        inflag = true;
    }
    if(inputValue.includes("https://youtube.com/shorts/") && inputValue.match(regex3)){
        inflag = true;
    }

    if (inflag) {
        flag = inflag;
        
        sendResponseMobile();
        error = '';
    } else {
        flag = inflag;
        error = '不正なURLです';
        showErrorPopup();
    }
}

function checkDuplicatesUrlPhone() {

    var inputValue = document.getElementById('url-phone').value;
    var inflag = false
    console.log(inputValue);

    let error = document.getElementById('urlError_movie4').innerHTML
    var regex = /v=([A-Za-z0-9-_]{11})/;
    var regex2 = /be\/([A-Za-z0-9-_]{11})/;
    var regex3 = /shorts\/([A-Za-z0-9-_]{11})/;

    if(inputValue.includes("https://www.youtube.com/watch?v=") && inputValue.match(regex)){
        inflag = true;
    }
    if(inputValue.includes("https://youtu.be/") && inputValue.match(regex2)){
        inflag = true;
    }
    if(inputValue.includes("https://www.youtube.com/shorts/") && inputValue.match(regex3)){
        inflag = true;
    }
    if(inputValue.includes("https://youtube.com/shorts/") && inputValue.match(regex3)){
        inflag = true;
    }



    if (inflag) {
        flag = inflag;
        
        closeModal();
        error = '';
    } else {
        flag = inflag;
        error = '不正なURLです';
        showErrorPopup();
    }
}

setTimeout(1000, connect());