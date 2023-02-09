function follow(event) {
    let url = '/insertfollow';
    let user_id = event.value;
    let hidden = document.getElementById(user_id + 'h');
    console.log(hidden);
    let flag = hidden.value;
    let button = document.getElementById(event.id);
    console.log(flag);
    console.log(button);
    $.ajax({
        url: url,
        type: 'POST',
        data: {
            user_id: user_id,
            check_follow: flag
        },
        success: function (result) {
            if (flag == 0) {
                // $(btnClass).css({
                //     "background-color": "black"
                // })
                button.innerHTML = "フォロー解除";
                hidden.value = 1;
            } else {
                // $(btnClass).css({
                //     "background-color": "red"
                // })
                console.log("success");
                button.innerHTML = "フォロー";
                hidden.value = 0;
            }
        }
    });
}