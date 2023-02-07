$("#search_form").on('submit', function (e) {
    e.preventDefault();
    let paramUrl = $(this).attr("action") + "?search_word=" + $("#search_word").val();

    $.ajax({
        type : "GET",
        url : paramUrl,
        dataType : "html",
        
        success : function(data, status, xhr) {
            $("#result").html(data);
        },
        error : function(XMLHttpRequest, status, errorThrown){}
    })
})