const $passwordEye = $('#passwordeye');
const $passwordEyeConfirm = $('#passwordeye-confirm');
const $submit = $('#submit'); /* 제출 버튼  */
const $passwordInput = $('#password-input');
const $passwordInputConfirm = $('#password-input-confirm');
const $error = $('#blank');
const $error2 = $('#blank2');
const $length = $('#length'); /* inputvalue길이 */

// 모든 정규식(약관 제외)
let checkAll = [false, false, false];

/* 비밀번호 input type 변경  */
$(function(){
    $passwordEye.on("click", function(){
        $passwordInput.toggleClass('active');
        if ($passwordInput.hasClass('active')){
            $passwordInput.prop('type',"text");
            $(this).attr("src","https://account.wishket.com/static/renewal/img/news/icon_input_slash.png");
        }
        else{
            $passwordInput.prop('type',"password");
            $(this).attr("src","https://account.wishket.com/static/renewal/img/news/icon_input_eye.png");
        }
    });
});

/* 비밀번호 확인 input type 변경 */
$(function(){
    $passwordEyeConfirm.on("click", function(){
        $passwordInputConfirm.toggleClass('active');
        if ($passwordInputConfirm.hasClass('active')){
            $passwordInputConfirm.prop('type',"text");
            $(this).attr("src","https://account.wishket.com/static/renewal/img/news/icon_input_slash.png");
        }
        else{
            $passwordInputConfirm.prop('type',"password");
            $(this).attr("src","https://account.wishket.com/static/renewal/img/news/icon_input_eye.png");
        }
    });

});


/* 비밀번호 빈 문자열 체크 */1``


/*비밀번호 에러메세지 출력*/
$(document).ready(function() {
    $submit.click(function() {
        if (!$passwordInput.val()) {
            $passwordInput.css("border-color", "red");
            $error.css("display", "block");
            $error.css("color", "red");
            $error.text("새 비밀번호를 입력해주세요.");
           checkAll[0] = false;
        }else if($passwordInput.val().length<8){
            $passwordInput.css("border-color", "red");
            $error.css("display", "none");
            $length.css("display", "block");
            $length.css("color", "red");
            $length.text("8글자 이상 입력해 주세요.");
            checkAll[0] = false;
        } else {
            $passwordInput.css("border-color", "#e0e0e0");
            $length.css("display", "none");
            checkAll[0] = true;
        }
    })
});


$(document).ready(function() {
    $submit.click(function() {
        if (!$passwordInputConfirm.val()) {
            $passwordInputConfirm.css("border-color", "red");
            $error2.css("display", "block");
            $error2.css("color", "red");
            $error2.text("새 비밀번호를 한번 더 입력해주세요.");
            checkAll[1] = false;
        }else if($passwordInputConfirm.val().length<8){
            $passwordInputConfirm.css("border-color", "red");
            $error2.css("display", "none");
            $length.css("display", "block");
            $length.css("color", "red");
            $length.text("8글자 이상 입력해 주세요.");
            checkAll[1] = false;
        } else {
            $passwordInputConfirm.css("border-color", "#e0e0e0");
            $length.css("display", "none");
            checkAll[1] = true;
        }
    })
});

// 비밀번호 확인 정규식 이벤트 사용 및 함수
// $passwordCheckInput.on("blur", function () {
//     var $passwordInputValue = $passwordInput.val();
//     var $passwordCheckInputValue = $passwordCheckInput.val();
//
//     if ($passwordCheckInputValue.length < 1) {
//         $passwordCheckWarning.text("비밀번호를 한 번 더 입력해 주세요.");
//         $passwordCheckWarning.css("display", "block");
//         $passwordCheckInput.css("border-color", "#e52929");
//         checkAll[2] = false;
//
//     } else if ($passwordCheckInputValue == $passwordInputValue) {
//         $passwordCheckWarning.css("display", "none");
//         $passwordCheckInput.css("border-color", "#e0e0e0");
//         checkAll[2] = true;
//
//     } else {
//         $passwordCheckWarning.text("동일한 비밀번호를 입력해 주세요.");
//         $passwordCheckWarning.css("display", "block");
//         $passwordCheckInput.css("border-color", "#e52929");
//         checkAll[2] = false;
//     }
// });


const btnOpenPopup = document.querySelector('#modal-back');
const modal = document.querySelector('#modal-background');
const closeBtn = modal.querySelector(".finish")
const closegrayBtn = modal.querySelector("#btn_modal_close")


/* 비밀번호 동일 확인 */
function same() {
    var p1 = document.getElementById('password-input').value;
    var p2 = document.getElementById('password-input-confirm').value;
    if( p1 != p2 ) {
        $passwordInputConfirm.css("border-color", "red");
        $error2.css("display", "block");
        $error2.css("color", "red");
        $error2.text("동일한 비밀번호를 입력해주세요.");
        checkAll[2] = false;
    } else{
        // test=(btoa(p1));
        // $passwordInputConfirm.css("border-color", "red");
        // $error2.text(test);
        // $error2.css("display", "block");
        modal.css("display", "block");
        modal.css("opcity", "1");
        modal.css("zIndex", "1000");

        checkAll[2] = true;
    }
}

btnOpenPopup.addEventListener('click', () => {
    alert('들어옴');
    modal.style.display = 'block';
    modal.style.zIndex = "1000";
    modal.style.opcity = "1";
});

closeBtn.addEventListener("click", e => {
    modal.style.display = "none"
});

closegrayBtn.addEventListener("click", e => {
    modal.style.display = "none"
});

modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal")) {
        modal.style.display = "none"
    }
});

// 비밀번호 변경 활성화

$submit.on("click", function () {
    var flag = false;
        for (let i = 0; i < checkAll.length; i++) {
            var check = checkAll[i];
            if (!check) {
                flag = true;
                break;
        }
    }

    if (flag) {
        /*비밀번호 암호화*/
        $passwordInput.val(btoa($passwordInput.val()));
        $passwordCheckInput.val(btoa($passwordCheckInput.val()));
        document.passwordForm.submit();
    } else {
        $submit.attr("type", "button");
    }
});