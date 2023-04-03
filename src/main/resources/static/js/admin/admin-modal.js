const $modalButtons = $(".custom-btn");
const modalClose = document.querySelector("#close");
const modalCloseOk = document.querySelector("#edit-button1");
/* 클릭 이벤트 */
function show (e) {
  document.querySelector(".background").className = "background show";
  console.log(e);
}

function close () { 
  document.querySelector(".background").className = "background";
}

function test(e) {
  console.log(e);
}

// document.querySelectorAll(".show").addEventListener('click', show);
// $('.show').on('click', show);
// $modalButtons.each(function(i, modalButton){
//   $(modalButton).on("click", show);
// })
$modalButtons.each(function(i, modalButton){
  $(modalButton).on("click", function(){
    console.log("ㄷㄹㅇ");
  });
})
// $modalButtons.on("click", function(){
//     console.log("ㄷㄹㅇ");
//     console.log($(this));
// })
// $('.custom-btn').on("click", function() {
//   console.log("ㄷㄹㅇ");
//     console.log($(this));
// })


modalClose.addEventListener('click', close);
modalCloseOk.addEventListener('click', close);

// document.querySelector("#show").addEventListener('click', function() {
//   document.querySelector(".background").className = "background show";
// });
// document.querySelector("#close").addEventListener('click', function(){
//   document.querySelector(".background").className = "background";
// });