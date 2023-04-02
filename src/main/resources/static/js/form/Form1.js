const $nameInput = $("#name-input");
const $nameWarning = $(".name-error");
let nameFlag = false;
// 이름 정규식 이벤트 사용 및 함수
$nameInput.on("blur", function() {
	const $nameInputValue = $nameInput.val();
	const nameInputValue = $nameInput.val();
	var name = nameInputValue.search(/^[가-힣a-zA-Z]{2,20}$/);

	// $nameInput.css("border-color", "#f66");
	// $nameInput.css("border-color", "#dde2e6");
	if ($nameInputValue.length < 1) {
		$nameWarning.text("이름을 입력해주세요.");
		$nameWarning.css("display", "block");
		$nameInput.css("border-color", "#f66");
		nameFlag = false;
	} else if ($nameInputValue.length < 2) {
		$nameWarning.text("최소 2자입니다.");
		$nameWarning.css("display", "block");
		$nameInput.css("border-color", "#f66");
		nameFlag = false;
	} else if (nameInputValue.search(/\s/) != -1) {
		$nameWarning.text("다시 확인해주세요.");
		$nameWarning.css("display", "block");
		$nameInput.css("border-color", "#f66");
		nameFlag = false;
	} else if (name < 0) {
		$nameWarning.text("다시 확인해주세요.");
		$nameWarning.css("display", "block");
		$nameInput.css("border-color", "#f66");
		nameFlag = false;
	} else {
		$nameWarning.css("display", "none");
		$nameInput.css("border-color", "#616161");
		nameFlag = true;
	}
	completeAllCheck();
});

//핸드폰 변수
const $phoneInput = $('#input-phone-number');
//핸드폰 에러 변수
const $phoneWarning = $('.phone-error');
let phoneFlag = false;
// 핸드폰 정규식 이벤트 함수 사용
$phoneInput.on("blur", function() {
	var isPhoneNum = /([01]{2,})([01679]{1,})([0-9]{3,4})([0-9]{4})/;
	var $phoneInputVal = $phoneInput.val();
	var phoneInputVal = $phoneInput.val();


	// $phoneInput.css("border-color", "#f66");
	// $phoneInput.css("border-color", "#dde2e6");
	if ($phoneInputVal.length < 1) {
		$phoneWarning.text("핸드폰 번호를 입력해주세요.");
		$phoneWarning.css("display", "block");
		$phoneInput.css("border-color", "#f66");
		phoneFlag = false;
	} else if (!isPhoneNum.test($phoneInputVal)) {
		$phoneWarning.text("잘못된 형식입니다. 다시 입력해주세요.");
		$phoneWarning.css("display", "block");
		$phoneInput.css("border-color", "#f66");
		phoneFlag = false;
	} else {
		$phoneWarning.css("display", "none");
		$phoneInput.css("border-color", "#616161");
		phoneFlag = true;
	}

	completeAllCheck();
});



const $completeButton = $(".signup-submit-button");
/* 모든 정규식 완료 및 전체 동의 체크 시 버튼 활성화 */
/*$(function(){
	if ($('#acourse').is(":checked")) {
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
})*/


