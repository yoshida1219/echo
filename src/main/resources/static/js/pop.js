

// スマホ投稿
$(".j1").click(() => {
    $(".sure_kyouyu").css({
        "display":"block"
    })
    $(".sure_sesaku").css({
        "display":"none"
    })
    $(".sure_toukou").css({
        "display":"none"
    })
})
$(".j2").click(() => {
    $(".sure_sesaku").css({
        "display":"block"
    })
    $(".sure_kyouyu").css({
        "display":"none"
    })
    $(".sure_toukou").css({
        "display":"none"
    })
})
$(".j6").click(() => {
    $(".sure_sesaku").css({
        "display":"none"
    })
    $(".sure_kyouyu").css({
        "display":"none"
    })
    $(".sure_toukou").css({
        "display":"block"
    })
    
})






// pc投稿
$(".j3").click(() => {
    $(".sure_kyouyu").css({
        "display":"flex"
    })
    $(".sure_sesaku").css({
        "display":"none"
    })
    $(".sure_toukou").css({
        "display":"none"
    })
})
$(".j4").click(() => {
    $(".sure_sesaku").css({
        "display":"flex"
    })
    $(".sure_kyouyu").css({
        "display":"none"
    })
    $(".sure_toukou").css({
        "display":"none"
    })
})

$(".j5").click(() => {
    $(".sure_sesaku").css({
        "display":"none"
    })
    $(".sure_kyouyu").css({
        "display":"none"
    })
    $(".sure_toukou").css({
        "display":"flex"
    })
    
})







//スレッド
$(".sure-btn2").click(() => {
    $("#search_thread").css({
        "display":"block"
    })
    $("#search_form").css({
        "display":"none"
    })
})
// ユーザー
$(".user-btn2").click(() => {
    $("#search_thread").css({
        "display":"none"
    })
    $("#search_form").css({
        "display":"block"
    })
})







/*=============== SHOW MODAL ===============*/

/*
window.onload = function(){
    modalContainer.classList.remove('show-modal')
}
*/

$('.message a').click(function(){
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
 });
 

const showModal = (openButton, modalContent) =>{
    const openBtn = document.getElementById(openButton),
    modalContainer = document.getElementById(modalContent)
    
    if(openBtn && modalContainer){
        openBtn.addEventListener('click', ()=>{
            modalContainer.classList.add('show-modal')
        })
    }
}
showModal('open-modal','modal-container')

showModal('open-modal2','modal-container')




//投稿のポップアップの削除
var closeBtn = document.querySelectorAll('.close-modal')

function closeModal(){
    const modalContainer = document.getElementById('modal-container')
    modalContainer.classList.remove('show-modal')
}
closeBtn.forEach(c => c.addEventListener('click', closeModal))


//動画URLの自動切り出し
$(".url").on("input", (event) => {
    let id = event.target.id;
    let input_url = $("#" + id).val();
    let url = "https://www.youtube.com/embed/";

    

    if(input_url.length < 11) {
        return
    }

    

    url += input_url.substr(-11)

    url += "?enablejsapi=1"

    $(".video-player").attr("src", url)  
})


// function onYouTubeIframeAPIReady() {
//    player = new YT.Player('video-player', {
//       events: {
//          'onError': function (event) {
//             alert("エラーが発生しました");
//          }
//       }
//    });
// }
// onYouTubeIframeAPIReady関数を定義

function checkDuplicates() {
    
    let flag = true;
    var input_url = document.getElementById('url-pc-share').value;

    if (input_url.includes("https://www.youtube.com/@")) {
        alert("error2");
        var urlError = document.getElementById('urlError');
        urlError.innerHTML = 'fuckin';
        flag = false;
    }
    

    
    if (flag) {
      return true;
    } else {
      return false;
    }
    
    
}