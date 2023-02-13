

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


// function checkDuplicates() {
    
//     let flag = true;
//     var input_url = document.getElementById('url-pc-share').value;

//     if (input_url.includes("https://www.youtube.com/@")) {
//         alert("error2");
//         var urlError = document.getElementById('urlError');
        
//         flag = false;
//     }
    

    
//     if (flag) {
//       return true;
//     } else {
//       return false;
//     }
    
    
// }

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
        sendResponse();
        closeModal();
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

    

function removeFeatureShare(input) {
    var inputValue = input.value;
    var dflag = false
    if (inputValue.includes("?feature=share")) {
      input.value = inputValue.replace("?feature=share", "");
    }

    if (inputValue.includes("&list=")) {
      input.value = inputValue.substring(0, inputValue.indexOf("&list="));
    }

    // if(inputValue.includes("https://www.youtube.com/watch?v=")){
    //     flag = true;
    // }
    // if(inputValue.includes("https://youtu.be/")){
    //     flag = true;
    // }
    // if(inputValue.includes("https://www.youtube.com/shorts/")){
    //     flag = true;
    // }
    // if(inputValue.includes("https://youtube.com/shorts/")){
    //     flag = true;
    // }

    // if(flag){
    //     error = '';
    // }else{
    //     error = 'このURLは入力できません';
    // }

    // console.log(error);

  }

  function showErrorPopup() {
    $("#error-popup").show();
    $("#error-popup2").show();
    $("#error-popup3").show();
    $("#error-popup4").show();
}

$(document).ready(function () {
$("#back-button").click(function () {
$("#error-popup").hide();
$("#error-popup2").hide();
$("#error-popup3").hide();
$("#error-popup4").hide();

});
$("#back-button2").click(function () {
$("#error-popup").hide();
$("#error-popup2").hide();
$("#error-popup3").hide();
$("#error-popup4").hide();

});
$("#back-button3").click(function () {
$("#error-popup").hide();
$("#error-popup2").hide();
$("#error-popup3").hide();
$("#error-popup4").hide();

});
$("#back-button4").click(function () {
$("#error-popup").hide();
$("#error-popup2").hide();
$("#error-popup3").hide();
$("#error-popup4").hide();

});
});