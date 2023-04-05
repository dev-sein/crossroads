/* selectbox  */
const $selectInput = $('#sido1');
const $selectWarning = $('.select-error');
let selectFlag = false;

    $selectInput.on("change", function (){
        var value = $(this).find("option:selected").val();

        if ( value == "시/도 선택"){
            $selectWarning.text("지역을 선택해주세요.")
            $selectWarning.css("display", "block");
            selectFlag = false;
        } else {
            $selectWarning.css("display", "none");
		    selectFlag = true;
        }
         completeAllCheck();
    })

	/* 달력 박스  */
const $dateInput = $('#startDate');
const $dateWarning = $('.date-error');
let dateFlag = false;

    $dateInput.on("change", function (){
		var $dateInputVal = $dateInput.val();
		var isDateNum = /^\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;
		if ( $dateInputVal.length < 1){
            $dateWarning.text("날짜를 선택해주세요.")
            $dateWarning.css("display", "block");
            dateFlag = false;
        } else if (!isDateNum.test($dateInputVal)) {
			$dateWarning.text("잘못된 형식입니다. 다시 입력해주세요.");
			$dateWarning.css("display", "block");
			$dateInput.css("border-color", "#f66");
			dateFlag = false;
		}else {
            $dateWarning.css("display", "none");
		    dateFlag = true;
        }
        completeAllCheck(); 
    })


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
function completeAllCheck() {

	// let identificationFlag = false; // 아이디
	// let passwordFlag = false; // 비밀번호
	// let passwordCheckFlag = false; // 비밀번호 확인
	// let nicknameFlag = false; // 닉네임
	// let emailFlag = false; // 이메일
	// let nameFlag = false; // 이름
	// let phoneCheck = false; // 핸드폰

	if (selectFlag && dateFlag) {
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


const $submitBtn = $("#nextbtn");

$submitBtn.on('click', function () {
	document.join.submit();
});