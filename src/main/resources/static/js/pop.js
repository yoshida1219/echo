

// スマホ投稿
$(".j1").click(() => {
    $(".sure_kyouyu").css({
        "display":"block"
    })
    $(".sure_sesaku").css({
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
$("#url").on("input", () => {
    let input_url = $("#url").val();
    let url = "https://www.youtube.com/embed/"

    if(input_url.length < 11) {
        return
    }

    url += input_url.substr(-11)

    $("#video-player").attr("src", url)  
})