/*모달 창 띄우기*/
function showModal() {
    $('.modal').css('visibility', 'visible');
    document.body.style.overflow = 'hidden';    // 모달창 띄웠을 때 스크롤 막기
}

function closeModal() {
    $('.modal').css('visibility', 'hidden');
    document.body.style.removeProperty('overflow'); // 모달창 끄면 스크롤 원상복구
}

$(window).scroll(function (e) {
    if (window.scrollY < 100) {
        $("#board-write-button").css("left", "-15.5%");
        $("#board-write-button").css("width", "45%");
        $("#board-write-button").css("position", "relative");
        $("#board-write-button").css("top", "0px");
        $("#board-write-button").css("height", "35px");
        $("#board-write-button").css("line-height", "1rem");
    } else {
        $("#board-write-button").css("position", "fixed");
        $("#board-write-button").css("height", "70px");
        $("#board-write-button").css("line-height", "52px");
        $("#board-write-button").css("z-index", "2");
        $("#board-write-button").css("margin-top", "0");
        $("#board-write-button").css("left", "12%");
    }
});

/*모달 클릭시 이미지 뽑기*/
$('.review-image').on('click', function () {
    $('.modal-image').attr('src', $(this).attr('src'));
});

/*별점*/
$(document).ready(function () {
    $('.review-star').each(function () {
        const reviewGrade = parseInt($(this).attr('data-review-grade'));
        let starString = '';

        for (let i = 1; i <= 5; i++) {
            starString += i <= reviewGrade ? '★' : '☆';
        }

        $(this).html(starString);
    });
});

// 무한스크롤
$(window).scroll(function () {
    if ($(window).scrollTop() + $(window).height() > $(document).height() - 100) {
        loadMoreReviews();
    }
});

function loadMoreReviews() {
    var page = parseInt($("#page").val());
    $.post("/review-list/more", { start: (page - 1) * 10 + 1, end: page * 10 }, function (data) {
        if (data && data.length > 0) {
            // 게시물 추가 코드 작성 (HTML 구조에 따라 수정해야 할 수 있습니다.)
            // ...
            $("#page").val(page + 1);
        }
    }, "json");
}




