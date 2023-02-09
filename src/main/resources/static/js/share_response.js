function share_response() {
    let paramUrl = "/Hiroba/share_response";
    
    $.ajax({
        type: "GET",
        url : paramUrl,
        data: {response_creater : $("#response_creater").val(), response_id : $("#response_id").val(), url : $("#movieID_html").val()}
    })
}

function delete_share_response() {
    let paramUrl = "/Hiroba/delete_share_response";

    $.ajax({
        type:"GET",
        url: paramUrl,
        data:{response_creater : $("#response_creater").val(), response_id : $("#response_id").val()}
    })
}