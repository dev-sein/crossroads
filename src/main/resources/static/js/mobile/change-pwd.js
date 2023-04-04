/* 비밀번호 빈 문자열 체크 */
const submit = document.querySelector('#submit-btn'); /* 제출 버튼  */
const passwordInput = document.querySelector('#password-input'); /* password input */
const error = document.querySelector('#blank'); /* 오류 메세지 */
const length = document.querySelector('#length'); /* inputvalue길이 */

// $(document).ready(function() {
$('#submit-btn').click(function() {
	if (!$('#password-input').val()) {
		passwordInput.style.borderColor = "red";
		length.style.display="none";
		error.style.display = "block";
		error.innerHTML = "비밀번호를 입력해주세요.";
		error.style.color = "red";
		return;
	}else if($('#password-input').val().length<8){
		passwordInput.style.borderColor = "red";
		error.style.display="none";
		length.style.display = "block";
		length.style.color = "red";
		length.innerHTML = "8글자 이상 입력해 주세요.";
		return;
	}
	$('#password-input').val(btoa($('#password-input').val()));
	let $password = $('#password-input').val();

	$.ajax({
		url: "/mypage/my-password-check-out",
		type: "post",
		data: { memberPassword : $password },
		success: function(result) {
			if(result) {
				location.href = "/applies/my-mobile-password-change";
			} else {
				passwordInput.style.borderColor = "red";
				error.style.display="none";
				length.style.display = "block";
				length.style.color = "red";
				length.innerHTML = "다시 입력해주세요.";
			}
		}
	});
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
//
//
// // 비밀번호 변수
// const $passwordInput = $("#password-input");
// // 비밀번호 에러 변수
// let $passwordWarning = $(".pwd .error-text");
//
// let completePwd = false;
// let completeRePwd = false;
// const $submitBtn = $("#submit-btn");
//
// // 비밀번호 정규식 이벤트 사용 및 함수
// $passwordInput.on("blur", function() {
// 	var $passwordInputValue = $passwordInput.val();
// 	var passwordInputValue = $passwordInput.val();
//
// 	var regExp = /^[A-Za-z\d@$!%*#?&]{8,32}$/;
//
//
// 	if ($passwordInputValue.length < 1) {
// 		$passwordWarning.text("비밀번호를 입력해주세요.");
// 		$passwordWarning.css("display", "block");
// 		$passwordInput.css("border-color", "#e52929");
// 		completePwd = false;
// 	} else if ($passwordInputValue.length > 31) {
// 		$passwordWarning.text("글자수를 초과하였습니다.");
// 		$passwordWarning.css("display", "block");
// 		$passwordInput.css("border-color", "#e52929");
// 		completePwd = false;
// 	} else if (!regExp.test($passwordInputValue)) {
// 		console.log("들어옴");
// 		$passwordWarning.text("8글자 이상 입력해 주세요.");
// 		$passwordWarning.css("display", "block");
// 		$passwordInput.css("border-color", "#e52929");
// 		completePwd = false;
// 	} else {
// 		$passwordWarning.css("display", "none");
// 		$passwordInput.css("border-color", "#e0e0e0");
// 		completePwd = true;
// 	}
// });
//
// // 비밀번호 변수
// const $passwordCheckInput = $("#re-password-input");
// // 비밀번호 에러 변수
// let $passwordCheckWarning = $(".re-pwd .error-text");
//
// // 비밀번호 확인 정규식 이벤트 사용 및 함수
// $passwordCheckInput.on("blur", function() {
// 	var $passwordInputValue = $passwordInput.val();
// 	var $passwordCheckInputValue = $passwordCheckInput.val();
//
// 	if ($passwordCheckInputValue.length < 1) {
// 		$passwordCheckWarning.text("비밀번호를 한 번 더 입력해 주세요.");
// 		$passwordCheckWarning.css("display", "block");
// 		$passwordCheckInput.css("border-color", "#e52929");
//         completeRePwd = false;
// 	} else if ($passwordCheckInputValue == $passwordInputValue) {
// 		$passwordCheckWarning.css("display", "none");
// 		$passwordCheckInput.css("border-color", "#e0e0e0");
// 		completeRePwd = true;
// 	} else {
// 		$passwordCheckWarning.text("동일한 비밀번호를 입력해 주세요.");
// 		$passwordCheckWarning.css("display", "block");
// 		$passwordCheckInput.css("border-color", "#e52929");
// 		completeRePwd = false;
// 	}
// });
//
// const $pwdEye = $(".pwd i");
// let checkPwd = false;
// $pwdEye.on('click', function(){
//     var $pwdInput = $('.pwd input');
//     var $pwdImg = $('.pwd img');
//     if (!checkPwd) {
//         $pwdInput.attr('type', 'text');
//         $pwdImg.attr('src', '/images/member/icon_input_slash.png');
//         checkPwd = true;
//     } else {
//         $pwdInput.attr('type', 'password');
//         $pwdImg.attr('src', '/images/member/icon_input_eye.png');
//         checkPwd = false;
//     }
// });
//
// const $rePwdEye = $(".re-pwd i");
// let checkRePwd = false;
// $rePwdEye.on('click', function(){
//     var $pwdInput = $('.re-pwd input');
//     var $pwdImg = $('.re-pwd img');
//     if (!checkPwd) {
//         $pwdInput.attr('type', 'text');
//         $pwdImg.attr('src', '/images/member/icon_input_slash.png');
//         checkPwd = true;
//     } else {
//         $pwdInput.attr('type', 'password');
//         $pwdImg.attr('src', '/images/member/icon_input_eye.png');
//         checkPwd = false;
//     }
// });
//
// /* mypage-check-pwd-mobile 페이지 버튼 클릭 시  */
// // function checkPassword(object){
// // 	var link = 'my-mobile-password-change.html';
// // 	if(completePwd) {
// // 		$("object").attr("type", "submit");
// // 		location.replace(link);
// // 	}
// // };
//
// /* mypage--pwd-mobile 페이지 버튼 클릭 시 */
// // function changePassword(object){
// // 	var msg = "비밀번호가 변경되었습니다.";
// // 	var link = 'my-mobile.html'
// // 	if(completePwd&completeRePwd) {
// // 		$("object").attr("type", "submit");
// // 		alert(msg);
// // 		location.replace(link);
// // 	}
// // };
//
// /* my-mobile-password-check 페이지 버튼 클릭 시  */
// // function checkAccount(object){
// // 	var link = 'my-mobile-account-cancel.html';
// // 	if(completePwd) {
// // 		$("object").attr("type", "submit");
// // 		location.replace(link);
// // 	}
// // };
//
// // 비밀번호 확인
// // 비밀번호 입력 - 입력 값 암호화 - 비교 - 같으면 인증완료 틀리면 다시 입력해주세요. 메세지 출력
// $('#submit-btn').on('click', function(){
// 	if($('#password-input').val()){
// 		$('#password-input').val(btoa($('#password-input').val()));
// 		document.passwordForm.submit();
// 		alert("들어옴")
// 	}
// })
//
// $submitBtn.on("click", function () {
// 	var $pwdInput = $('.pwd input');
// 	if ($pwdInput.val()) {
// 		$passwordInput.val(btoa($passwordInput.val()));
// 		$passwordCheckInput.val(btoa($passwordCheckInput.val()));
// 		$submitBtn.attr("type", "submit");
// 	} else {
// 		$submitBtn.attr("type", "button");
// 	}
// });
