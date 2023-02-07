// 2. This code loads the IFrame Player API code asynchronously.
var tag = document.createElement('script');
var count = 0;
var grimheart = 0;
var intervalId;

tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

// 3. This function creates an <iframe> (and YouTube player)
//    after the API code downloads.
var player;

function onYouTubeIframeAPIReady() {
  count = document.getElementById('regacy_fav_count').value;
  player = new YT.Player('player', {
    height: '100%',
    width: '100%',

    // height: '630',
    // width: '1120',

    
    videoId: document.getElementById('movieID_html').value,
    events: {
      'onReady': onPlayerReady,
      'onStateChange': onPlayerStateChange
    }
  });
}
//document.getElementById('movieID_html').value

// 4. The API will call this function when the video player is ready.
function onPlayerReady(event) {
  
}

// 5. The API calls this function when the player's state changes.
//    The function indicates that when playing a video (state=1),
//    the player should play for six seconds and then stop.
var done = false;
function onPlayerStateChange(event) {
  if (event.data == YT.PlayerState.PLAYING && !done) {
    clearInterval(intervalId);
    intervalId = setInterval(countUp,100);
    done = true;
  } else {
    clearInterval(intervalId);
    done = false;
  }
}

function countUp(){
  grimheart++;
  if(grimheart == 10){
      fav_count.textContent = Math.trunc(count++);
      grimheart = 0;
  }
}



//ページを離れるとき　いいね数を取り出す
window.onbeforeunload = function(e) {
  alert("ok");
  let before_like = $("#regacy_fav_count").val();
  let now_like = $("#fav_count").text();
  
  let like = now_like - before_like;
  send_like(like);
}

//いいね数を送信
function send_like(like) {
  let response_id = $("#response_id").val();
  let response_creater = $("#response_creater").val();

  let paramUrl = "/Hiroba/RessDetail/update_like?response_id=" + response_id + "&response_creater=" + response_creater  + "&like=" + like;
  open( "URL","_blank") ;

  $.ajax({
      type : "GET",
      url : paramUrl,
      dataType : "html",

      success : function(data, status, xhr) {
          //console.log(data);
      },
      error : function(XMLHttpRequest, status, errorThrown){}
  })
}
