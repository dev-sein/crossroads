const $modalButtons = $(".custom-btn");
const modalClose = document.querySelector("#close");
const modalCloseOk = document.querySelector("#edit-button1");
/* 클릭 이벤트 */
function show (e) {
  document.querySelector(".background").className = "background show";

  var memberId = e.dataset.id;
  memberId *= 1;

  console.log(memberId);
  $.ajax({
    url: "/admin/members/detail",
    type: "post",
    data: {
      memberId: memberId
    },
    success: function(result) {
      console.log(result);
      showAdminModal(result);
    },
    error: function (error) {
      console.log('Error fetching data:', error);
    }
  })

}
/* 핸드폰 자동 하이픈 */
function autoHyphen(number) {
  const regex = /^01(0|1|[6-9])\-?\d{3,4}\-?\d{4}$/;
  if (regex.test(number)) {
    return number.replace(/^01(0|1|[6-9])/, "01$1-").replace(/(\d{3,4})(\d{4})/, "$1-$2");
  }
  return "Invalid number";
}

function showAdminModal(member) {
  var $resultAdminModal = $('#modal-main');
  // var member = memberVO;
  var text = "";
  var memberPhone = autoHyphen(member.memberPhone);
  var memberType = member.memberType === 0 ? "초보자" : "베테랑";
  console.log(member);

  text += `
      <form>
          <div class="input-wrapper">
              <div class="input-text">회원 타입</div>
              <input type="text" id="user-type" value="${memberType}" class="user-input user-input2" readonly>
          </div>
          <div class="input-wrapper">
              <div class="input-text">이름</div>
              <input type="text" id="user-name" value="${member.memberName}" class="user-input" readonly>
          </div>
          <div class="input-wrapper">
              <div class="input-text">아이디</div>
              <input type="text" id="user-name" value="${member.memberIdentification}" class="user-input" readonly>
          </div>
          <div class="input-wrapper">
              <div class="input-text">이메일</div>
              <input type="text" id="user-email" value="${member.memberEmail}" class="user-input" readonly>
          </div>
          <div class="input-wrapper">
              <div class="input-text">핸드폰번호</div>
              <input type="text" id="user-phone-number" value="${memberPhone}" class="user-input" readonly>
          </div>
          <div class="input-wrapper">
              <div class="input-text">면허 취득일자</div>
              <input type="text" id="driver-license" value="${member.memberDriveRegisterDate}" class="user-input" readonly>
          </div>`;
  if(member.memberFileOriginalName != null) {
    text += `
          <div class="input-wrapper" style="margin-top: 30px;">
              <div class="input-text">사진</div>
              <img src="/upload/${member.memberFileOriginalName}" class="member-image">
          </div>`;
  }
  text += `</form>`;

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