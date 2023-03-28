/* 비밀번호 빈 문자열 체크 */
const submit = document.querySelector('#submit'); /* 제출 버튼  */
const passwordinput = document.querySelector('#password-input'); /* password input */
const error = document.querySelector('#blank'); /* 오류 메세지 */
const length = document.querySelector('#length'); /* inputvalue길이 */


$(document).ready(function() {
  $('#submit').click(function() {
      if (!$('#password-input').val()) {
        passwordinput.style.borderColor = "red";
		length.style.display="none";
        blank.style.display = "block";
        blank.innerHTML = "비밀번호를 입력해주세요.";
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

/* 탈퇴 약관 체크 여부 */
    $('#agreeCheck').on('change', function () {
      if ($(this).is(':checked')) {
        $('.error-text-custom').hide();
      } else {
        $('.error-text-custom').show();
      }
    });

   function submitForm(){
    const targetChecked = $('#agreeCheck').is(':checked');
    if (targetChecked) {
      /* $('form').submit(); */
    }else {
      $('.error-text-custom').show();
    }
  }

