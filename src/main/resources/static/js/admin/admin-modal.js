/* 클릭 이벤트 */
function show () {
  document.querySelector(".background").className = "background show";
}

function close () { 
  document.querySelector(".background").className = "background";
}

// document.querySelectorAll(".show").addEventListener('click', show);
$('.show').on('click', show);
document.querySelector("#close").addEventListener('click', close);
document.querySelector("#edit-button1").addEventListener('click', close);
// document.querySelector("#show").addEventListener('click', function() {
//   document.querySelector(".background").className = "background show";
// });
// document.querySelector("#close").addEventListener('click', function(){
//   document.querySelector(".background").className = "background";
// });