$("#updateListBtn").click(() => {
    $(".updateList").css({
        "display":"block"
    })
    $(".registList").css({
        "display":"none"
    })
})

$("#registListBtn").click(() => {
    $(".updateList").css({
        "display":"none"
    })
    $(".registList").css({
        "display":"block"
    })
})

