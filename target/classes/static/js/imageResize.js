$( "#imageContainer" ).draggable();
$( "#resizeHandle" ).resizable({
  aspectRatio: true,
  handles: "se",
  stop: function(event, ui) {
    var width = ui.size.width;
    var height = ui.size.height;
    var x = ui.position.left;
    var y = ui.position.top;
    // Your code to process the user-defined crop area
  }
});