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

            canvas.width = 100;
            canvas.height = 100;
            ctx.drawImage(image, 0, 0, 100, 100);

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
    if (genre1 === genre2 && genre1 !== "") {
      alert("同じジャンルは選択できません");
      return false;
    }
    return true;
  }

