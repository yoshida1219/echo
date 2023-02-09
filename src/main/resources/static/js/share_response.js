function share_response() {

    let share_disp = document.getElementById("tt2");
    let share_str = document.getElementById("check_share");

    let share = document.getElementById("share_disp").innerText;
    let after_share = document.getElementById("share_disp");
    
    let check_share = document.getElementById("check_share").value;
    let paramUrl = "/Hiroba/share_response";
    console.log(share_disp);

    $.ajax({
        type: "GET",
        url: paramUrl,
        data: { response_creater: $("#response_creater").val(), response_id: $("#response_id").val(), check_share: $("#check_share").val(), url: $("#movieID_html").val() },


        
        success: function (result) {

            if(check_share == 0) {
                console.log(share)
                share_disp.innerHTML = '共有解除';
                share_str.value=1;

                
                after_share.textContent=Number(share)+1;
            }else {
                console.log(share)
                share_disp.innerHTML = '共有';
                share_str.value=0;

                after_share.textContent=Number(share)-1;
            }

        }

    });
}

function delete_share_response() {
    let share_disp = document.getElementById("tt2");
    let paramUrl = "/Hiroba/delete_share_response";

    $.ajax({
        type: "GET",
        url: paramUrl,
        data: { response_creater: $("#response_creater").val(), response_id: $("#response_id").val(), url: $("#movieID_html").val() }
    });
}
