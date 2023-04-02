/* 비밀번호 빈 문자열 체크 */
const submit = document.querySelector('#submit-btn'); /* 제출 버튼  */
const passwordInput = document.querySelector('#password-input'); /* password input */
const error = document.querySelector('#blank'); /* 오류 메세지 */
const length = document.querySelector('#length'); /* inputvalue길이 */

$(document).ready(function() {
    $('#submit-btn').click(function() {
        if (!$('#password-input').val()) {
            passwordInput.style.borderColor = "red";
            length.style.display="none";
            error.style.display = "block";
            error.innerHTML = "비밀번호를 입력해주세요.";
            error.style.color = "red";
        }else if($('#password-input').val().length<8){
            passwordInput.style.borderColor = "red";
            error.style.display="none";
            length.style.display = "block";
            length.style.color = "red";
            length.innerHTML = "8글자 이상 입력해 주세요.";
        }

    })
});


// 비밀번호 확인
// 비밀번호 입력 - 입력 값 암호화 - 비교 - 같으면 인증완료 틀리면 다시 입력해주세요. 메세지 출력
$('#submit-btn').on('click', function(){
    if($('#password-input').val()){
        $('#password-input').val(btoa($('#password-input').val()));
        document.passwordForm.submit();
    }
})

// // 비밀번호 확인 정규식 이벤트 사용 및 함수
// $passwordCheckInput.on("blur", function () {
//     var $passwordInputValue = $passwordInput.val();
//     var $passwordCheckInputValue = $passwordCheckInput.val();
//
//     if ($passwordCheckInputValue.length < 1) {
//         $passwordCheckWarning.text("비밀번호를 한 번 더 입력해 주세요.");
//         $passwordCheckWarning.css("display", "block");
//         $passwordCheckInput.css("border-color", "#e52929");
//         checkAll[3] = false;
//
//     } else if ($passwordCheckInputValue == $passwordInputValue) {
//         $passwordCheckWarning.css("display", "none");
//         $passwordCheckInput.css("border-color", "#e0e0e0");
//         checkAll[3] = true;
//
//     } else {
//         $passwordCheckWarning.text("동일한 비밀번호를 입력해 주세요.");
//         $passwordCheckWarning.css("display", "block");
//         $passwordCheckInput.css("border-color", "#e52929");
//         checkAll[3] = false;
//     }
// });



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
