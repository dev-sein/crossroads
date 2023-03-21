// 비밀번호 변수
const $passwordInput = $("#password-input");
// 비밀번호 에러 변수
let $passwordWarning = $(".pwd .error-text");

let completePwd = false;
let completeRePwd = false;

// 비밀번호 정규식 이벤트 사용 및 함수
$passwordInput.on("blur", function() {
	var $passwordInputValue = $passwordInput.val();
	var passwordInputValue = $passwordInput.val();

	var regExp = /^[A-Za-z\d@$!%*#?&]{8,32}$/;


	if ($passwordInputValue.length < 1) {
		$passwordWarning.text("비밀번호를 입력해주세요.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		completePwd = false;
	} else if ($passwordInputValue.length > 31) {
		$passwordWarning.text("글자수를 초과하였습니다.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		completePwd = false;
	} else if (!regExp.test($passwordInputValue)) {
		console.log("들어옴");
		$passwordWarning.text("8글자 이상 입력해 주세요.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		completePwd = false;
	} else {
		$passwordWarning.css("display", "none");
		$passwordInput.css("border-color", "#e0e0e0");
		completePwd = true;
	}
});

// 비밀번호 변수
const $passwordCheckInput = $("#re-password-input");
// 비밀번호 에러 변수
let $passwordCheckWarning = $(".re-pwd .error-text");

// 비밀번호 확인 정규식 이벤트 사용 및 함수
$passwordCheckInput.on("blur", function() {
	var $passwordInputValue = $passwordInput.val();
	var $passwordCheckInputValue = $passwordCheckInput.val();

	if ($passwordCheckInputValue.length < 1) {
		$passwordCheckWarning.text("비밀번호를 한 번 더 입력해 주세요.");
		$passwordCheckWarning.css("display", "block");
		$passwordCheckInput.css("border-color", "#e52929");
        completeRePwd = false;
	} else if ($passwordCheckInputValue == $passwordInputValue) {
		$passwordCheckWarning.css("display", "none");
		$passwordCheckInput.css("border-color", "#e0e0e0");
		completeRePwd = true;
	} else {
		$passwordCheckWarning.text("동일한 비밀번호를 입력해 주세요.");
		$passwordCheckWarning.css("display", "block");
		$passwordCheckInput.css("border-color", "#e52929");
		completeRePwd = false;
	}
});

const $pwdEye = $(".pwd i");
let checkPwd = false;
$pwdEye.on('click', function(){
    var $pwdInput = $('.pwd input');
    var $pwdImg = $('.pwd img');
    if (!checkPwd) {
        $pwdInput.attr('type', 'text');
        $pwdImg.attr('src', '../../static/img/icon_input_slash.png');
        checkPwd = true;
    } else {
        $pwdInput.attr('type', 'password');
        $pwdImg.attr('src', '../../static/img/icon_input_eye.png');
        checkPwd = false;
    }
});

const $rePwdEye = $(".re-pwd i");
let checkRePwd = false;
$rePwdEye.on('click', function(){
    var $pwdInput = $('.re-pwd input');
    var $pwdImg = $('.re-pwd img');
    if (!checkPwd) {
        $pwdInput.attr('type', 'text');
        $pwdImg.attr('src', '../../static/img/icon_input_slash.png');
        checkPwd = true;
    } else {
        $pwdInput.attr('type', 'password');
        $pwdImg.attr('src', '../../static/img/icon_input_eye.png');
        checkPwd = false;
    }
});

/* mypage-check-pwd-mobile 페이지 버튼 클릭 시  */
function checkPassword(object){
	var link = 'mypage-change-pwd-mobile.html';
	if(completePwd) {
		$("object").attr("type", "submit");
		location.replace(link);
	}
};

/* mypage--pwd-mobile 페이지 버튼 클릭 시 */
function changePassword(object){
	var msg = "비밀번호가 변경되었습니다.";
	var link = 'mypage-mobile.html'
	if(completePwd&completeRePwd) {
		$("object").attr("type", "submit");
		alert(msg);
		location.replace(link);
	}
};

/* mypage-check-pwd-mobile 페이지 버튼 클릭 시  */
function checkPassword(object){
	var link = '#';
	if(completePwd) {
		$("object").attr("type", "submit");
		location.replace(link);
	}
};