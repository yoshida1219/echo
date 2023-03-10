function previewImage() {
    var preview = document.getElementById("preview");
    var file = document.getElementById("fileInput").files[0];
    var reader = new FileReader();
    var file = document.getElementById("fileInput").files[0];
    var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
    var maxFileSize = 2097152; // 2MB

    if (!allowedExtensions.exec(file.name)) {
      alert("PNG、JPEG、またはJPG形式のファイルのみがサポートされています");
      document.getElementById("fileInput").value = "";
      return false;
    }

    if (file.size > maxFileSize) {
        alert("最大ファイルサイズは2MBです");
        document.getElementById("fileInput").value = "";
        return false;
      }
    var hiddenInput = document.getElementById("hidden_icon");
    hiddenInput.value= "t";


    reader.addEventListener("load", function () {
        var image = new Image();
        image.src = reader.result;
        image.onload = function () {
            var canvas = document.createElement("canvas");
            var ctx = canvas.getContext("2d");

            canvas.width = 300;
            canvas.height = 300;
            ctx.drawImage(image, 0, 0, 300, 300);

            preview.src = canvas.toDataURL();
        }
    }, false);

    if (file) {
        reader.readAsDataURL(file);
    }
}

function checkDuplicates() {
    var genre1 = document.getElementById("genre1").value;
    var genre2 = document.getElementById("genre2").value;
    var user_name = document.getElementById('user_name').value;
    var introduction = document.getElementById('introduction').value;

    var name_txt = document.getElementById('name_txt');
    var introduction_txt = document.getElementById('introduction_txt');
    var message = document.getElementById('message');

    

    
    
    var flag = true;;
    if (genre1 === genre2 && genre1 !== "") {
      message.innerHTML = '同じジャンルを選択することはできません';
      flag = false;
    }
    if (user_name.length > 10) {
      name_txt.innerHTML = 'ユーザー名は10文字以内で入力してください';
      flag = false;
    }

    if (user_name.length == 0) {
      name_txt.innerHTML = 'ユーザー名を入力してください';
      flag = false;
    }
    if (introduction.length > 100) {
      introduction_txt.innerHTML = '自己紹介は100文字以内で入力してください';
      flag = false;
    }


    

    

    
    if (flag) {
      return true;
    } else {
      return false;
    }
  
    
}


document.getElementById('genre1').addEventListener('change', checkLists);
document.getElementById('genre2').addEventListener('change', checkLists);

function checkLists() {
  var list1 = document.getElementById('genre1').value;
  var list2 = document.getElementById('genre2').value;
  var message = document.getElementById('message');

  if (!list1 && !list2) {
    message.innerHTML = '値が入っていません';
  } else {
    message.innerHTML = '';
  }
}

window.addEventListener('load', function() {
  var img = document.getElementById("preview");
  var src = img.getAttribute('src');
  
  if(src !== "https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/user.png"){
      var hiddenInput = document.getElementById("hidden_icon");
      hiddenInput.value= "t";
  }
});