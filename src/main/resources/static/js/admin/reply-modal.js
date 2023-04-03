const $modalButtons = $(".custom-btn");
const modalClose = document.querySelector("#close");
const modalCloseOk = document.querySelector("#edit-button1");
/* 클릭 이벤트 */
function show (e) {
  document.querySelector(".background").className = "background show";

  // var memberName = e.dataset.name;
  // var replyContent = e.dataset.content;

  var reply = {
    "memberName": e.dataset.name,
    "replyContent": e.dataset.content
  }


  console.log(reply);

  showAdminModal(reply);
  // $.ajax({
  //   url: "/admin/replies/detail",
  //   type: "post",
  //   data: {
  //     replyId: replyId
  //   },
  //   success: function(result) {
  //     showAdminModal(result);
  //   },
  //   error: function (error) {
  //     console.log('Error fetching data:', error);
  //   }
  // })

}

function showAdminModal(reply) {
  var $resultAdminModal = $('#modal-main');
  var text = "";
  text += `
      <form>
          <div class="input-wrapper">
              <div class="input-text">작성자</div>
              <input type="text" id="veteran-name" value="${reply.memberName}" class="user-input" readonly>
          </div>
          <div class="input-wrapper">
              <div class="input-text">댓글 내용</div>
              <input type="text" id="reply-content" value="${reply.replyContent}" class="user-input" readonly>
          </div>
      </form>
  `;

  $resultAdminModal.html(text);
}



function close () { 
  document.querySelector(".background").className = "background";
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