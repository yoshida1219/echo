function follow(event) {
    let url = 'insertfollow';
    let user_id = event.value;
    let button = document.getElementById(user_id);
    let hidden = document.getElementById(user_id + 'h');
    let flag = hidden.value;

    alert(user_id);

    $.ajax({
        url: url,
        type: 'POST',
        data: { user_id: user_id },
        success: function (result) {
            console.log(flag);

            if (flag == 1) {
                button.innerText = 'フォロー中';
                hidden.value = 0;
            } else {
                button.innerText = 'フォロー';
                hidden.value = 1;
            }
        }
    });
}