//スクロールをするとハンバーガーメニューに変化するための設定を関数でまとめる
// function FixedAnime() {
//   //ヘッダーの高さを取得
//   var headerH = $('#header').outerHeight(true);
//   var scroll = $(window).scrollTop();
//   if (scroll >= headerH) {//ヘッダーの高さ以上までスクロールしたら
//     $('.openbtn1').addClass('fadeDown');//.openbtnにfadeDownというクラス名を付与して
//     $('#header').addClass('dnone');//#headerにdnoneというクラス名を付与
//     $('.openbtn1').addClass('fix');

//   } else {//それ以外は
//     $('.openbtn1').removeClass('fadeDown');//fadeDownというクラス名を除き
//     $('#header').removeClass('dnone');//dnoneというクラス名を除く
//     $('.openbtn1').removeClass('fix');
//   }
// }

// 画面をスクロールをしたら動かしたい場合の記述
// $(window).scroll(function () {
//   FixedAnime();//スクロールをするとハンバーガーメニューに変化するための関数を呼ぶ
// });

// // ページが読み込まれたらすぐに動かしたい場合の記述
// $(window).on('load', function () {
//   FixedAnime();//スクロールをするとハンバーガーメニューに変化するための関数を呼ぶ
// });


//ボタンをクリックした際のアニメーションの設定
$(".openbtn1").click(function () {//ボタンがクリックされたら
  $(this).toggleClass('active');//ボタン自身に activeクラスを付与し
  $("#header").toggleClass('panelactive');//ヘッダーにpanelactiveクラスを付与
});
$("#g-navi li a").click(function () {//ナビゲーションのリンクがクリックされたら
  $(".openbtn1").removeClass('active');//ボタンの activeクラスを除去し
  $("#header").removeClass('panelactive');//ヘッダーのpanelactiveクラスも除去
});


$(".openbtn").click(function () {//ボタンがクリックされたら
  $(this).toggleClass('active');//ボタン自身に activeクラスを付与し
  $("#g-nav2").toggleClass('panelactive');//ナビゲーションにpanelactiveクラスを付与
});

$("#g-nav2 a").click(function () {//ナビゲーションのリンクがクリックされたら
  $(".openbtn").removeClass('active');//ボタンの activeクラスを除去し
  $("#g-nav2").removeClass('panelactive');//ナビゲーションのpanelactiveクラスも除去
});

$(".rogo1").click(function () {//ボタンがクリックされたら
  $(this).toggleClass('active');//ボタン自身に activeクラスを付与し
  $("#g-nav2").toggleClass('panelactive');//ナビゲーションにpanelactiveクラスを付与
});


$("#schbtn11").click(function () {//ボタンがクリックされたら
  $(this).toggleClass('active');//ボタン自身に activeクラスを付与し
  $(".side").toggleClass('rig');//ナビゲーションにpanelactiveクラスを付与
});




//リンク先のidまでスムーススクロール
//※ページ内リンクを行わない場合は不必要なので削除してください
$('#g-navi li a').click(function () {
  var elmHash = $(this).attr('href');
  var pos = $(elmHash).offset().top - 0;
  $('body,html').animate({ scrollTop: pos }, 1000);
  return false;
});

// 動きのきっかけとなるアニメーションの名前を定義
function fadeAnime() {

  // ふわっ
  $('.fadeUpTrigger').each(function () { //fadeUpTriggerというクラス名が
    var elemPos = $(this).offset().top - 50;//要素より、50px上の
    var scroll = $(window).scrollTop();
    var windowHeight = $(window).height();
    if (scroll >= elemPos - windowHeight) {
      $(this).addClass('fadeUp');// 画面内に入ったらfadeUpというクラス名を追記
    } else {
      $(this).removeClass('fadeUp');// 画面外に出たらfadeUpというクラス名を外す
    }
  });
}

// 画面をスクロールをしたら動かしたい場合の記述
$(window).scroll(function () {
  fadeAnime();/* アニメーション用の関数を呼ぶ*/
});// ここまで画面をスクロールをしたら動かしたい場合の記述

// 画面が読み込まれたらすぐに動かしたい場合の記述
$(window).on('load', function () {
  fadeAnime();/* アニメーション用の関数を呼ぶ*/
});// ここまで画面が読み込まれたらすぐに動かしたい場合の記述





// ハンバーメニューの制御
$(".m-c2").click(function() {
  $(this).addClass('active');
  $("#g-nav2").addClass('panelactive');
})

$(".hum-list-2-hed1").click(function() {
  $(this).removeClass('active');
  $("#g-nav2").removeClass('panelactive');
})
