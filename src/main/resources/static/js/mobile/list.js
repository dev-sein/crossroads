// 수락 거절 버튼
function acceptOrReject(object) {
    var check = object.innerText == "거절" ? true : false;
    console.log(object.nextElementSibling.value);
    if (check) {
        object.className = 'ok-btn';
        object.innerText = '수락';
        object.style.backgroundColor = "#2099bb";
        object.style.borderColor = '#2099bb';
    } else {
        object.className = 'no-btn';
        object.innerText = '거절';
        object.style.backgroundColor = "#e52929";
        object.style.borderColor = '#e52929';
    }

    const data = object.nextElementSibling.value;
    const sendData = {
        "applyId" : data
    };
    $.ajax({
        url: "/applies/list-mobile/change-status",
        type: 'post',
        data: sendData,
        // dataType: "json",
        success:function (data) {
            console.log("성공");
        },
        error:function (a, b, c) {
            console.log(a, b, c);
        }

    })
}


// 맨위로 가기 버튼
const backToTopButton = document.querySelector(".back-to-top");

window.addEventListener("scroll", () => {
    // 페이지 스크롤 위치가 500 이상이면 버튼을 보여줌
    if (window.pageYOffset > 500) {
        backToTopButton.style.display = "block";
    } else {
        backToTopButton.style.display = "none";
    }
});

backToTopButton.addEventListener("click", () => {
    // 버튼을 클릭하면 다시 클릭할 수 없도록 설정
    backToTopButton.disabled = true;

    // 맨 위로 스크롤
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });

    // 스크롤이 끝나면 버튼을 다시 활성화
    setTimeout(() => {
        backToTopButton.disabled = false;
    }, 1000);
});

$('.modal-clear-btn').on('click',function () {
    location.href = "/applies/list-mobile?reset=o";
})

window.addEventListener('load', () => {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('reset') === 'o') {
        $('.modal-search-modal').show();
        document.body.style.overflow = 'hidden';    // 모달창 띄웠을 때 스크롤 막기
    }
});