$(".v").click(() => {
    $(".updateList").css({
        "display":"block"
    })
    $(".registList").css({
        "display":"none"
    })

    $(".v").css({
        "background-color":"coral"
    })
    $(".n").css({
        "background-color":"#6c6c6c"
    })
})

$(".n").click(() => {
    $(".updateList").css({
        "display":"none"
    })
    $(".registList").css({
        "display":"block"
    })

    $(".v").css({
        "background-color":"#6c6c6c"
    })
    $(".n").css({
        "background-color":"coral"
    })
})

