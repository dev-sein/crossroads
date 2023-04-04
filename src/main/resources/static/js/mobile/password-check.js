/* 비밀번호 빈 문자열 체크 */
const submit = document.querySelector('#submit-btn'); /* 제출 버튼  */
const passwordInput = document.querySelector('#password-input'); /* password input */
const error = document.querySelector('.error-text'); /* 오류 메세지 */
const length = document.querySelector('#length'); /* inputvalue길이 */

// $(document).ready(function() {
$('#submit-btn').click(function() {
    if (!$('#password-input').val()) {
        passwordInput.style.borderColor = "red";
        error.style.display = "block";
        error.innerHTML = "비밀번호를 입력해주세요.";
        error.style.color = "red";
        return;
    }else if($('#password-input').val().length<8){
        passwordInput.style.borderColor = "red";
        error.style.display = "block";
        error.style.color = "red";
        error.innerHTML = "8글자 이상 입력해 주세요.";
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
                location.href = "/applies/my-mobile-account-cancel";
            } else {
                passwordInput.style.borderColor = "red";
                error.style.display = "block";
                error.style.color = "red";
                error.innerHTML = "다시 입력해주세요.";
            }
        }
    });
});