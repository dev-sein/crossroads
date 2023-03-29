// 이메일
const $emailInput = $(".email .input-text");
const $emailWarning = $(".email .error-text");
const $emailInputvalue = $("#memberEmail");


// 아이디
const $idInput = $('.id .input-text');
let $idWarning = $("#id-error");
const $identificationInput = $("#memberIdentification");

// 비밀번호
const $passwordInput = $("#password-input");
let $passwordWarning = $(".pwd .error-text");
const $pwdEye = $(".pwd i");
let checkPwd = false;

// 비밀번호 확인
const $passwordCheckInput = $("#password-check");
let $passwordCheckWarning = $(".re-pwd .error-text");
const $rePwdEye = $(".re-pwd i");
let checkRePwd = false;

// 이름
const $nameInput = $(".name .input-text");
const $nameWarning = $("#name-error");
let nameFlag = false;

//핸드폰
const $phoneInput = $('.phone .input-text');
const $phoneWarning = $('#phone-error');
let phoneFlag = false;

// 모든 정규식(약관 제외)
let checkAll = [false, false, false, false, false, false, false];

// 이용약관 동의
const $checkbox = $(".crossroads-checkbox");
const $arrow = $(".arrow");
const $icon = $(".checked-icon");
let checkbox = false;

// 회원가입 버튼
const $submitBtn = $("#submit-btn");



// 이메일 정규식 이벤트 사용 및 함수
$emailInput.on("blur", function(){
    // 이메일 정규식
    var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    /* 이메일 중복 확인 */
    /* if (result.check) {
        $emailWarning.text("가입된 계정이 없습니다. 이메일을 다시 확인해주세요.");
        $emailWarning.css("display", "block");
        $emailInput.css("border-color", "#e52929");
        emailFlag = false;
    }  */if (!emailPattern.test($emailInput.val())) {
        $emailWarning.text("이메일을 입력해 주세요.");
        $emailWarning.css("display", "block");
        $emailInput.css("border-color", "#e52929");
        checkAll[0] = false;

    } else {
        $emailWarning.css("display", "none");
        $emailInput.css("border-color", "#e0e0e0");
        checkAll[0] = true;
    }
});

function checkEmail() {
	var email = $('#memberEmail').val(); //email 입력란의 값을 저장
	$.ajax({
		url: './checkEmail', //Controller에서 요청 받을 주소
		type: 'post', //POST 방식으로 전달
		data: {memberEmail: $emailInputvalue.val()},
		success: function (duplicateEmail) { //컨트롤러에서 넘어온 cnt값을 받는다
			if (duplicateEmail == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 이메일
				$('.email_ok').css("display", "inline-block");
				$('.email_already').css("display", "none");
			} else { // cnt가 1일 경우 -> 이미 존재하는 이메일
				$('.email_already').css("display", "inline-block");
				$('.email_ok').css("display", "none");
			}
		},
		error: function () {
			alert("에러입니다");
		}
	});
};

// 아이디 정규식 이벤트 사용 및 함수
	$idInput.on("blur", function () {
		var $idInputValue = $idInput.val();
		var idInputValue = $idInput.val();

		if ($idInputValue.length < 1) {
			$idWarning.text("아이디를 입력해 주세요.");
			$idWarning.css("display", "block");
			$idInput.css("border-color", "#e52929");
			checkAll[1] = false;

		} else if ($idInputValue.length < 6) {
			$idWarning.text("6글자 이상의 영문자, 숫자, 특수기호(_)만 사용 가능합니다.");
			$idWarning.css("display", "block");
			$idInput.css("border-color", "#e52929");
			checkAll[1] = false;

		} else {
			$idWarning.css("display", "none");
			$idInput.css("border-color", "#e0e0e0");
			checkAll[1] = true;
		}
	});


	function checkId() {
		var id = $('#memberIdentification').val(); //id값이 "id"인 입력란의 값을 저장
		$.ajax({
			url: './checkId', //Controller에서 요청 받을 주소
			type: 'post', //POST 방식으로 전달
			data: {memberIdentification: $identificationInput.val()},
			success: function (duplicateId) { //컨트롤러에서 넘어온 check 받는다
				if (duplicateId == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
					$('.id_ok').css("display", "inline-block");
					$('.id_already').css("display", "none");
				} else { // check 1일 경우 -> 이미 존재하는 아이디
					$('.id_already').css("display", "inline-block");
					$('.id_ok').css("display", "none");
				}
			},
			error: function () {
				alert("에러입니다");
			}
		});
	};


// 비밀번호 정규식 이벤트 사용 및 함수
	$passwordInput.on("blur", function () {
		var $passwordInputValue = $passwordInput.val();
		var passwordInputValue = $passwordInput.val();

		var regExp = /^[A-Za-z\d@$!%*#?&]{8,32}$/;


		if ($passwordInputValue.length < 1) {
			$passwordWarning.text("비밀번호를 입력해주세요.");
			$passwordWarning.css("display", "block");
			$passwordInput.css("border-color", "#e52929");
			checkAll[2] = false;

		} else if ($passwordInputValue.length > 31) {
			$passwordWarning.text("글자수를 초과하였습니다.");
			$passwordWarning.css("display", "block");
			$passwordInput.css("border-color", "#e52929");
			checkAll[2] = false;

		} else if (!regExp.test($passwordInputValue)) {
			console.log("들어옴");
			$passwordWarning.text("8글자 이상 입력해 주세요.");
			$passwordWarning.css("display", "block");
			$passwordInput.css("border-color", "#e52929");
			checkAll[2] = false;

		} else {
			$passwordWarning.css("display", "none");
			$passwordInput.css("border-color", "#e0e0e0");
			checkAll[2] = true;

		}
	});

	/* 비밀번호 눈 아이콘 */
	$pwdEye.on('click', function () {
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

// 비밀번호 확인 정규식 이벤트 사용 및 함수
	$passwordCheckInput.on("blur", function () {
		var $passwordInputValue = $passwordInput.val();
		var $passwordCheckInputValue = $passwordCheckInput.val();

		if ($passwordCheckInputValue.length < 1) {
			$passwordCheckWarning.text("비밀번호를 한 번 더 입력해 주세요.");
			$passwordCheckWarning.css("display", "block");
			$passwordCheckInput.css("border-color", "#e52929");
			checkAll[3] = false;

		} else if ($passwordCheckInputValue == $passwordInputValue) {
			$passwordCheckWarning.css("display", "none");
			$passwordCheckInput.css("border-color", "#e0e0e0");
			checkAll[3] = true;

		} else {
			$passwordCheckWarning.text("동일한 비밀번호를 입력해 주세요.");
			$passwordCheckWarning.css("display", "block");
			$passwordCheckInput.css("border-color", "#e52929");
			checkAll[3] = false;
		}
	});


	/* 비밀번호 확인 눈 아이콘 */
	$rePwdEye.on('click', function () {
		var $pwdInput = $('.re-pwd input');
		var $pwdImg = $('.re-pwd img');
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


// 이름 정규식 이벤트 사용 및 함수
	$nameInput.on("blur", function () {
		const $nameInputValue = $nameInput.val();
		const nameInputValue = $nameInput.val();

		if ($nameInputValue.length < 1) {
			$nameWarning.text("이름을 입력해 주세요.");
			$nameWarning.css("display", "block");
			$nameInput.css("border-color", "#e52929");
			checkAll[4] = false;

		} else if ($nameInputValue.length < 2) {
			$nameWarning.text("최소 2자입니다.");
			$nameWarning.css("display", "block");
			$nameInput.css("border-color", "#e52929");
			checkAll[4] = false;

		} else {
			$nameWarning.css("display", "none");
			$nameInput.css("border-color", "#e0e0e0");
			checkAll[4] = true;

		}
	});


// 핸드폰 정규식 이벤트 함수 사용
	$phoneInput.on("blur", function () {
		var isPhoneNum = /([01]{2,})([01679]{1,})([0-9]{3,4})([0-9]{4})/;
		var $phoneInputVal = $phoneInput.val();
		var phoneInputVal = $phoneInput.val();

		if ($phoneInputVal.length < 1) {
			$phoneWarning.text("핸드폰 번호를 입력해 주세요.");
			$phoneWarning.css("display", "block");
			$phoneInput.css("border-color", "#e52929");
			checkAll[5] = false;

		} else if (!isPhoneNum.test($phoneInputVal)) {
			$phoneWarning.text("잘못된 형식입니다. 다시 입력해 주세요.");
			$phoneWarning.css("display", "block");
			$phoneInput.css("border-color", "#e52929");
			checkAll[5] = false;

		} else {
			$phoneWarning.css("display", "none");
			$phoneInput.css("border-color", "#e0e0e0");
			checkAll[5] = true;

		}

	});

	/* file upload */
	$("#file").on('change', function () {
		var fileName = $("#file").val().split('/').pop().split('\\').pop();
		var $fileName = $("#file").val().split('/').pop().split('\\').pop();

		var reg = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;

		if (fileName.match(reg)) {
			$("#upload-name").val(fileName);
			checkAll[6] = true;

		} else {
			$("#upload-name").val("이미지 파일이 아닙니다. 다시 등록해 주세요.");
			checkAll[6] = false;
		}
	});

	/* 이용약관 동의 */
	$checkbox.on("click", function () {
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

// 회원가입 버튼 활성화
	$submitBtn.on("click", function () {
		var flag = false;
		if (checkbox) {
			for (let i = 0; i < checkAll.length; i++) {
				var check = checkAll[i];
				if (!check) {
					flag = true;
					break;
				}
			}
		}

		if (checkbox && !flag) {
			/*비밀번호 암호화*/
			$passwordInput.val(btoa($passwordInput.val()));
			$passwordCheckInput.val(btoa($passwordCheckInput.val()));

			$submitBtn.attr("type", "submit");
		} else {
			$submitBtn.attr("type", "button");
		}
	});

FileList.prototype.forEach = Array.prototype.forEach;
globalThis.arrayFile = new Array();
/*****************************************************/


globalThis.i = 0;
$("input[name='file']").on("change", function(){
	const $files = $("input[name=file]")[0].files;
	let formData = new FormData();
	Array.from($files).forEach(file => globalThis.arrayFile.push(file));
	console.log(globalThis.arrayFile);

	$files.forEach(file => {

		formData.append("file", file)
	});
	$.ajax({
		url: "/files/upload",
		type: "post",
		data: formData,
		contentType: false,
		processData: false,
		success: function(uuids) {
			globalThis.uuids = uuids;
			$files.forEach((file, i) => {
				if(file.type.startsWith("image")){
					$("#thumbnail").append(`<li><a href="/files/download?fileName=${toStringByFormatting(new Date())}/${uuids[i]}_${file.name}"><img src="/files/display?fileName=${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.name}"></a></li>`);
				}else{
					$("#thumbnail").append(`<li><a href="/files/download?fileName=${toStringByFormatting(new Date())}/${uuids[i]}_${file.name}"><img src="/attach.png" width="100"></a></li>`);
				}
			});
			/********************************************************************/
			/*게시글 추가 부분*/
			const dataTransfer = new DataTransfer();
			globalThis.arrayFile.forEach(file => dataTransfer.items.add(file));
			$("input[name='file']")[0].files = dataTransfer.files;
			console.log(dataTransfer.files);
			let text = "";
			$files.forEach(file => {
				text +=
					`
                    <input type="hidden" name="files[${i}].fileName" value="${file.name}">
                    <input type="hidden" name="files[${i}].fileUuid" value="${globalThis.uuids[i]}">
                    <input type="hidden" name="files[${i}].filePath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name="files[${i}].fileSize" value="${file.size}">
                    <input type="hidden" name="files[${i}].fileType" value="${file.type.startsWith("image")}">
                    `
				i++;
			});
			$("form[name='write-form']").append(text);
		}
	});
});



function leftPad(value) {
	if (value >= 10) {
		return value;
	}

	return `0${value}`;
}

function toStringByFormatting(source, delimiter = '/') {
	const year = source.getFullYear();
	const month = leftPad(source.getMonth() + 1);
	const day = leftPad(source.getDate());

	return [year, month, day].join(delimiter);
}
/*****************************************************/