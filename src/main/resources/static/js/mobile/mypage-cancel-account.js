// 이용약관 동의
const $checkbox = $(".crossroads-checkbox");
const $arrow = $(".arrow");
const $icon = $(".checked-icon");
let checkbox = false;

const $errorMsg = $(".error-text-custom");

/* 이용약관 동의 */
$checkbox.on("click", function(){
    if (!checkbox) {
        $arrow.css("background", "#3ba3c7");
        $arrow.css("border-color", "#3ba3c7");
        $icon.css("stroke-dashoffset", "1");
        checkbox = true;
        $errorMsg.css("display", "none");

    } else {
        $arrow.css("background", "#fff");
        $arrow.css("border-color", "#e0e0e0");
        $icon.css("stroke-dashoffset", "29.7833385");
        checkbox = false;
        $errorMsg.css("display", "block");
    }
});

// 버튼 클릭
function cancelCheck(e) {
    if(!checkbox) {
        $errorMsg.css("display", "block");
        $("e").attr("type", "button");
    } else {
        $errorMsg.css("display", "none");
        $("e").attr("type", "submit");
        location.replace('mypage-complete-cancel-mobile.html');
    }
}


