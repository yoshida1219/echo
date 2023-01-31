function previewImage() {
    var preview = document.getElementById("preview");
    var file = document.getElementById("fileInput").files[0];
    var reader = new FileReader();
    var file = document.getElementById("fileInput").files[0];
    var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;

    if (!allowedExtensions.exec(file.name)) {
      alert("PNG、JPEG、またはJPG形式のファイルのみがサポートされています");
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
