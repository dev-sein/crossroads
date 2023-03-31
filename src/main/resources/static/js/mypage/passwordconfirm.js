const $passwordEye = $('#passwordeye');
const $passwordEyeConfirm = $('#passwordeye-confirm');
const $submitBtn = $('#submit-button'); /* 제출 버튼  */
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


/*비밀번호 에러메세지 출력*/
$(document).ready(function() {
    $submitBtn.click(function() {
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
    $submitBtn.click(function() {
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


// const btnOpenPopup = document.querySelector('#modal-back');
const modal = document.querySelector('#modal-background');
const closeBtn = modal.querySelector(".finish")
// const closegrayBtn = modal.querySelector("#btn_modal_close");

let check = false;

/* 비밀번호 동일 확인 */
function checkPasswords() {
    var p1 = document.getElementById('password-input').value;
    var p2 = document.getElementById('password-input-confirm').value;
    if( p1 != p2 ) {
        $passwordInputConfirm.css("border-color", "red");
        $passwordInput.css("border-color", "red");
        $error2.css("display", "block");
        $error2.css("color", "red");
        $error2.text("동일한 비밀번호를 입력해주세요.");
        // checkAll[2] = false;
        check = false;
    } else if(p1.length < 8){
        $error.css("display", "block");
        $error.css("color", "red");
        $error.text("8자 이상 32자 이하로 입력해 주세요.");
        $error2.text("8자 이상 32자 이하로 입력해 주세요.");
        $passwordInput.css("border-color", "red");
    }
    else {
        $error.css("display", "none");
        $error2.css("color", "green");
        $error2.text("비밀번호가 일치합니다.");
        $passwordInputConfirm.css("border-color", "green");
        $passwordInput.css("border-color", "green");
        // test=(btoa(p1));
        // $passwordInputConfirm.css("border-color", "red");
        // $error2.text(test);
        // $error2.css("display", "block");
        // 모달 창 띄워주는 부분
        // $(modal).css("display", "block");
        // $(modal).css("opcity", "1");
        // $(modal).css("zIndex", "1000");
        // checkAll[2] = true;
        check = true;
    }
    if (check){
        // $submitBtn.attr('type', 'submit');
        $('#submit-button').attr("class", "btn btn-01 btn-wishket submit-button-ok");
    } else {
        $submitBtn.attr('type', 'button');
        $('#submit-button').attr("class", "btn btn-01 btn-wishket");
    }
}
// function changePassword(){
//     $passwordInput.val(btoa($passwordInput.val()));
//     $passwordInputConfirm.val(btoa($passwordInputConfirm.val()));
// }
$('#submit-button').on('click', function () {
    if($('#submit-button').attr("class").includes("submit-button-ok")){
        console.log($('#password-input').val());
        $('#password-input').val(btoa($('#password-input').val()));
        console.log($('#password-input').val());
        $("#password-input-confirm").val(btoa($('#password-input-confirm').val()));
        document.passwordCheckForm.submit();
    }
});

// 비밀번호 변경 활성화
// $submit.on("click", function () {
//     // same();
//     if ($passwordInput.val()) {
//         console.log()
//         // document.passwordCheckForm.submit();
//         $("#passwordForm").submit();
//     } else {
//         alert('제출안됨')
//         // $submit.attr("type", "button");
//     }
// });

// btnOpenPopup.addEventListener('click', () => {
//     alert('들어옴');
//     modal.style.display = 'block';
//     modal.style.zIndex = "1000";
//     modal.style.opcity = "1";
// });

closeBtn.addEventListener("click", e => {
    modal.style.display = "none"
});

// closegrayBtn.addEventListener("click", e => {
//     modal.style.display = "none"
// });

modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal")) {
        modal.style.display = "none"
    }
});


// $submit.on("click", function () {
//     var flag = false;
//         for (let i = 0; i < checkAll.length; i++) {
//             var check = checkAll[i];
//             if (!check) {
//                 flag = true;
//                 break;
//         }
//     }
//
//     if (flag) {
//         /*비밀번호 암호화*/
//         $passwordInput.val(btoa($passwordInput.val()));
//         $passwordCheckInput.val(btoa($passwordCheckInput.val()));
//         document.passwordForm.submit();
//     } else {
//         $submit.attr("type", "button");
//     }
// });