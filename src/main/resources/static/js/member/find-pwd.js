// 이메일 변수
const $emailInput = $(".input-text");
// 이메일 에러 변수
const $emailWarning = $(".error-text");
var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
const $emailInputvalue = $("#memberEmail");

//비밀번호 전송 버튼
const $submitBtn = $("#submit-btn");

$emailInput.on("blur", function(){
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
    } else {
        $emailWarning.css("display", "none");
        $emailInput.css("border-color", "#e0e0e0");
    }
});


//이메일 중복체크 확인 후 중복인 경우 버튼 타입을 submit으로 넘기기

    function checkEmail() {
        alert('checkEmail들어옴');
    var email = $('#memberEmail').val(); //email 입력란의 값을 저장
    $.ajax({
        url: './checkEmail', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {memberEmail: $emailInputvalue.val()},
        success: function (duplicateEmail) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (duplicateEmail == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 이메일
                $('.error_text').css("display", "inline-block");
                alert('존재하지 않는 이메일입니다');
            } else { // cnt가 1일 경우 -> 이미 존재하는 이메일
                alert('존재하는 이메일입니다');
            }
        },
    });
};

    $submitBtn.on('clcik', function(){
       if($emailInputvalue){
           document.passwordForm.submit();
       }
    });