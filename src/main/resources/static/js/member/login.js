// 아이디 변수
const $idInput = $('.id .input-text');
// 아이디 에러 변수
let $idWarning = $("#id-error");
$idInput.on("blur", function() {
    var $idInputValue = $idInput.val();
	var idInputValue = $idInput.val();

    if ($idInputValue.length < 1) {
		$idWarning.text("아이디를 입력해 주세요.");
		$idWarning.css("display", "block");
		$idInput.css("border-color", "#e52929");


	} else if ($idInputValue.length < 6) {
		$idWarning.text("6글자 이상의 영문자, 숫자, 특수기호(_)만 사용 가능합니다.");
		$idWarning.css("display", "block");
		$idInput.css("border-color", "#e52929");

	} else {
		$idWarning.css("display", "none");
		$idInput.css("border-color", "#e0e0e0");
	}
})


/*		$('form').submit();*/


// 비밀번호 변수
const $passwordInput = $("#password-input");
// 비밀번호 에러 변수
let $passwordWarning = $(".pwd .error-text");


// 비밀번호 정규식 이벤트 사용 및 함수
$passwordInput.on("blur", function() {
	var $passwordInputValue = $passwordInput.val();
	var passwordInputValue = $passwordInput.val();

	var regExp = /^[A-Za-z\d@$!%*#?&]{8,32}$/;


	if ($passwordInputValue.length < 1) {
		$passwordWarning.text("비밀번호를 입력해주세요.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		passwordFlag = false;
	} else if ($passwordInputValue.length > 31) {
		$passwordWarning.text("글자수를 초과하였습니다.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		passwordFlag = false;
	} else if (!regExp.test($passwordInputValue)) {
		console.log("들어옴");
		$passwordWarning.text("8글자 이상 입력해 주세요.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		passwordFlag = false;
	} else {
		$passwordWarning.css("display", "none");
		$passwordInput.css("border-color", "#e0e0e0");
		passwordFlag = true;
	}
});

const $pwdEye = $(".pwd i");
let checkPwd = false;
$pwdEye.on('click', function(){
    var $pwdInput = $('.pwd input');
    var $pwdImg = $('.pwd img');
    if (!checkPwd) {
        $pwdInput.attr('type', 'text');
        $pwdImg.attr('src', '/img/icon_input_slash.png');
        checkPwd = true;
    } else {
        $pwdInput.attr('type', 'password');
        $pwdImg.attr('src', '/img/icon_input_eye.png');
        checkPwd = false;
    }
});


/* 로그인 상태 유지 */
let checkbox = false;
const $checkbox = $(".crossroads-checkbox");
const $arrow = $(".arrow");
const $icon = $(".checked-icon");

$checkbox.on("click", function(){
    if (!checkbox) {
        $arrow.css("background", "#3ba3c7");
        $arrow.css("border-color", "#3ba3c7");
        $icon.css("stroke-dashoffset", "1");
        checkbox = true;
    } else {
        $arrow.css("background", "#fff");
        $arrow.css("border-color", "#e0e0e0");
        $icon.css("stroke-dashoffset", "29.7833385");
        checkbox = false;
    }
});


