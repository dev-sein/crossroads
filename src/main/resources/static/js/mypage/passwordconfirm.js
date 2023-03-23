
/* 비밀번호 input type 변경  */
$(function(){
	$("#passwordeye").on("click", function(){
	$("#password-input").toggleClass('active');
	if ($("#password-input").hasClass('active')){
	$('#password-input').prop('type',"text");
	$(this).attr("src","https://account.wishket.com/static/renewal/img/news/icon_input_slash.png");
	}
	else{
	$('#password-input').prop('type',"password");
  $(this).attr("src","https://account.wishket.com/static/renewal/img/news/icon_input_eye.png");
}
});
});

/* 비밀번호 확인 input type 변경 */

$(function(){
	$("#passwordeye-confirm").on("click", function(){
	$("#password-input-confirm").toggleClass('active');
	if ($("#password-input-confirm").hasClass('active')){
	$('#password-input-confirm').prop('type',"text");
	$(this).attr("src","https://account.wishket.com/static/renewal/img/news/icon_input_slash.png");
	}
	else{
	$('#password-input-confirm').prop('type',"password");
  $(this).attr("src","https://account.wishket.com/static/renewal/img/news/icon_input_eye.png");
}
});

});

/* 비밀번호 빈 문자열 체크 */

const submit = document.querySelector('#submit');
const passwordinput = document.querySelector('#password-input');
const passwordinputconfirm = document.querySelector('#password-input-confirm');
const error = document.querySelector('#blank');
const length = document.querySelector('#length'); /* inputvalue길이 */

$(document).ready(function() {
  $('#submit').click(function() {
      if (!$('#password-input').val()) {
        passwordinput.style.borderColor = "red";
        blank.style.display = "block";
        blank.innerHTML = "새 비밀번호를 입력해주세요.";
        blank.style.color = "red";
      }else if($('#password-input').val().length<8){
        passwordinput.style.borderColor = "red";
        blank.style.display="none";
        length.style.display = "block";
        length.style.color = "red";
        length.innerHTML = "8글자 이상 입력해 주세요.";
        }
    
      })
    });
    

$(document).ready(function() {
    $('#submit').click(function() {
        if (!$('#password-input-confirm').val()) {
          passwordinputconfirm.style.borderColor = "red";
          blank2.style.display = "block";
          blank2.innerHTML = "새 비밀번호를 한번 더 입력해주세요.";
          blank2.style.color = "red";
        }else if($('#password-input-confirm').val().length<8){
          passwordinputconfirm.style.borderColor = "red";
          blank2.style.display="none";
          length.style.display = "block";
          length.style.color = "red";
          length.innerHTML = "8글자 이상 입력해 주세요.";
          }
      
        })
      });
      
      
const btnOpenPopup = document.querySelector('#modal-back');
const modal = document.querySelector('#modal-background');
const closeBtn = modal.querySelector(".finish")
const closegrayBtn = modal.querySelector("#btn_modal_close")


/* 비밀번호 동일 확인 */
  function same() {
    var p1 = document.getElementById('password-input').value;
    var p2 = document.getElementById('password-input-confirm').value;
    if( p1 != p2 ) {
      passwordinputconfirm.style.borderColor = "red";
      blank2.style.display = "block";
      blank2.innerHTML = "동일한 비밀번호를 입력해주세요.";
      blank2.style.color = "red";
    } else{
      test=(btoa(p1));
     /*  blank2.style.display = "block";
      blank2.innerHTML=test; */ /* 암호화 확인 */
      modal.style.display = 'block';
      modal.style.zIndex = "1000";
      modal.style.opcity = "1";
      return true;
    }
  }

  
btnOpenPopup.addEventListener('click', () => {
  alert('들어옴');
  modal.style.display = 'block';
  modal.style.zIndex = "1000";
  modal.style.opcity = "1";
});

closeBtn.addEventListener("click", e => {
  modal.style.display = "none"
});

closegrayBtn.addEventListener("click", e => {
  modal.style.display = "none"
});

modal.addEventListener("click", e => {
  const evTarget = e.target
  if(evTarget.classList.contains("modal")) {
      modal.style.display = "none"
  }
});
