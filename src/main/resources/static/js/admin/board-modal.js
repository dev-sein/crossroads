const $modalButtons = $(".custom-btn");
const modalClose = document.querySelector("#close");
const modalCloseOk = document.querySelector("#edit-button1");
/* 클릭 이벤트 */
function show (e) {
  document.querySelector(".background").className = "background show";

  var boardId = e.dataset.id;
  boardId *= 1;

  console.log(boardId);
  $.ajax({
    url: "/admin/boards/detail",
    type: "post",
    data: {
      boardId: boardId
    },
    success: function(result) {
      showAdminModal(result);
    },
    error: function (error) {
      console.log('Error fetching data:', error);
    }
  })

}

function showAdminModal(result) {
  var $resultAdminModal = $('#modal-main');
  var board = result.board;
  var files = result.files;
  var text = "";
  text += `
      <form>
          <div class="input-wrapper">
              <div class="input-text">글 제목</div>
              <input type="text" id="text-title" value="${board.boardTitle}" class="user-input" readonly>
          </div>
          <div class="input-wrapper">
              <div class="input-text">글 내용</div>
              <input type="text" id="text-content" value="${board.boardContent}" class="user-input" readonly >
          </div>`
  if(files.length > 0) {
      text +=`
          <div class="input-wrapper" style="margin-top: 30px;">
              <div class="input-text">사진</div>`
  }
  files.forEach(file => {
      text += `<img src="/board/display?fileName=${file.filePath} + '/t_'+${file.fileUuid}+ '_' + ${file.fileOriginalName}" class="board-image">`;
  })

  text += `
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