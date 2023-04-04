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
function renderStars(reviewGrade) {
    let starString = '';
    for (let i = 1; i <= 5; i++) {
        starString += i <= reviewGrade ?
            '<img src="https://www.wishket.com/static/renewal/img/partner/profile/icon_dashboard_star@2x.png" style="width:20px;">' :
            '<img src="https://www.wishket.com/static/renewal/img/partner/profile/icon_dashboard_star_empty@2x.png" style="width:20px;">';
    }
    return starString;
}

document.addEventListener('DOMContentLoaded', function() {
    let reviewStars = document.querySelectorAll('.review-star');

    for (let starDiv of reviewStars) {
        let reviewGrade = starDiv.getAttribute('data-review-grade');
        starDiv.innerHTML = renderStars(reviewGrade);
    }
});

/*date yy-mm-dd만 가져오기*/
function relativeTime(dateString) {
    let now = new Date();
    let date = new Date(dateString);
    let timeDifference = now - date;

    let seconds = Math.floor(timeDifference / 1000);
    let minutes = Math.floor(seconds / 60);
    let hours = Math.floor(minutes / 60);
    let days = Math.floor(hours / 24);

    if (days > 0) {
        return days + '일 전';
    }
    if (hours > 0) {
        return hours + '시간 전';
    }
    if (minutes > 0) {
        return minutes + '분 전';
    }
    return '방금 전';
}

document.addEventListener('DOMContentLoaded', function() {
    let reviewDates = document.querySelectorAll('.info-date');

    for (let dateSpan of reviewDates) {
        let reviewDate = dateSpan.getAttribute('data-review-date');
        dateSpan.textContent = relativeTime(reviewDate);
    }
});


/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡreview-list에서 현재시간ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



/* 삭제하기 */
// 삭제 버튼을 클릭하면 모달 창을 표시하는 함수
function showDeleteModal(event, reviewId) {
    event.preventDefault();
    $("#delete-modal").css("visibility", "visible");
    $("#delete-modal .delete-btn").attr("data-review-id", reviewId);
}

function deleteReview(reviewId) {
    $.ajax({
        url: `/review-delete/${reviewId}`,
        method: "DELETE",
        success: function (response) {
            location.reload();
        },
        error: function (error) {
            console.error(error);
            alert("삭제 중 오류가 발생했습니다.");
        },
    });
}

$(document).on("click", "#delete-modal .delete-btn", function () {
    let reviewId = $(this).attr("data-review-id");
    deleteReview(reviewId);
    closeModal();
});

function closeDeleteModal() {
    $("#delete-modal").css("visibility", "hidden");
}