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
  
  var flag = true;
  alert("a");
  if (genre1 === genre2 && genre1 !== "") {
    alert("同じジャンルは選択できません");
    flag = false;
  }
  if (user_name.length > 20) {
    alert("ユーザー名は 20 文字以内で入力してください");
    flag = false;
  }
  if (introduction.length > 100) {
    alert("自己紹介文は 100 文字以内で入力してください");
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
