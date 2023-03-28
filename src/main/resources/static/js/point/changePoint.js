//계좌번호
const $accountInput = $('#account-number');
//계좌번호 에러 변수
const $accountWarning = $('.account-error');
let accountFlag = false;
// 계좌번호 정규식 이벤트 함수 사용
$accountInput.on("keyup", function() {
	var isaccountNum = /^(\d{1,})(-(\d{1,})){1,}/;
	var $accountInputVal = $accountInput.val();

	if ($accountInputVal.length < 1) {
		$accountWarning.text("계좌번호를 입력해주세요.");
		$accountWarning.css("display", "block");
		$accountInput.css("border-color", "#f66");
		accountFlag = false;
	} else if (!isaccountNum.test($accountInputVal)) {
		$accountWarning.text("잘못된 형식입니다. '-'를 사용하여 다시 입력해주세요.");
		$accountWarning.css("display", "block");
		$accountInput.css("border-color", "#f66");
		accountFlag = false;
	} else if($accountInputVal.length < 12){
		$accountWarning.text("계좌번호를 입력해주세요.");
		$accountWarning.css("display", "block");
		$accountInput.css("border-color", "#f66");
		accountFlag = false;
	} else {
		$accountWarning.css("display", "none");
		$accountInput.css("border-color", "#616161");
		accountFlag = true;
	}

	 completeAllCheck();
});


const $completeButton = $(".signup-submit-button");
/* 모든 정규식 완료 및 전체 동의 체크 시 버튼 활성화 */
function completeAllCheck() {

	// let identificationFlag = false; // 아이디
	// let passwordFlag = false; // 비밀번호
	// let passwordCheckFlag = false; // 비밀번호 확인
	// let nicknameFlag = false; // 닉네임
	// let emailFlag = false; // 이메일
	// let nameFlag = false; // 이름
	// let phoneCheck = false; // 핸드폰

	if (accountFlag) {
		console.log("완료");
		$completeButton.css("pointer-events", "auto");
		$completeButton.css("cursor", "pointer");
		// $completeButton.css("border-color", "#00c4c4");
		// $completeButton.css("background-color", "#00c4c4");
		$completeButton.css("opacity", "0.8");  // 활성화 opacity: 0.8;
		$completeButton.css("color", "#fff");
	} else {
		console.log("하나라도 실패 시 들어옴.")
		$completeButton.css("pointer-events", "none");
		$completeButton.css("cursor", "default");
		// $completeButton.css("border-color", "#00c4c4");
		// $completeButton.css("background-color", "#00c4c4");
		$completeButton.css("opacity", "0.45");  //  비활성화 opacity: 0.45;
		$completeButton.css("color", "#fff");
	}
}
let check = false;

$completeButton.on("click", function(){
	console.log($("#hidden-point").val());
	if(Number($("#hidden-point").val()) < 10000){
		check = false;
		// alert("10000point 부터 환전이 가능합니다.");
		$("#modal-wrapper2").show();
	}else {
		check = true;
		$("#account").text($('#account-number').val());
		$("#modal-wrapper1").show();
	}
});

$("#close-modal-button").on("click", function(){
	$("#modal-wrapper2").hide();
	// location.href='/applies/point/change-point-mobile'; /* 포인트 내역으로 */
});

$("#no-button").on('click', function(){
	$("#modal-wrapper1").hide();
});

$("#ok-button").on('click', function(){
	document.changepointForm.submit();
});
