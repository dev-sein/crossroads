// dropdown
const userIcon = document.querySelector("#user-icon");
const dropdown = document.querySelector("#dropdown");

userIcon.addEventListener("click", e => {
    e.preventDefault();
    $(dropdown).slideToggle();
});

if($('.user-img').attr('src') == '/mypage/display?fileName=null/null_null') {
    $('.user-img').attr('src', '/images/mypage/main-logo.png');
}




