$(document).ready(function() {
    // 검색창에서 키보드를 눌렀을 때
    $('.search__searchbox__form').on('keydown', function(e) {
        if (e.keyCode == 13) { // Enter 키를 눌렀을 때
            e.preventDefault(); // 기본 이벤트 막기
        }
    });
});


let page = 1;
let keyword;
load();

function load() {
    console.log(page);
    $.ajax({
        url: "/admin/reviews/list",
        type: "post",
        contentType: 'application/json',
        dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
        data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
            "keyword" : keyword,
            "page" : page
        }),
        success: function(result) {
            showList(result.boards);
            showPage(result.pagination);

        },
        error: function (error) {
            console.log('Error fetching data:', error);
        }
    })
};


/*신청 목록*/
function showList(reviews){
    const $listResults = $("#scroll");
    var text = "";
    reviews.forEach(review => {
        var date = review.reviewRegisterDate;
        var realDate = changeDate(date);
        text +=`
            <div class="content-list__info-container">
                <div class="content-list__info-unit">
                    <input type="checkbox" class="content__checkbox" id="" name="checkbox" data-id="${review.reviewId}" onclick="isChecked(this)"/>
                    <label for="" class="content__checkbox--label">
                        <ul class="content-list__info">
                            <li class="content__id">${review.reviewId}</li>
                            <li class="content__user">${review.memberName}</li>
                            <li class="content__title">${review.reviewTitle}</li>
                            <li class="content__contents">${review.reviewContent}</li>
                            <li class="content__date">${realDate}</li>
                            <li class="user__detail" name="userDetail">
                                <button class="custom-btn btn-16 show" data-id="${review.reviewId}" onclick="show(this)">상세 정보</button>
                            </li>
                        </ul>
                    </label>
                </div>
            </div>
        `
    });

    $listResults.html(text);
}


/*페이지 버튼*/
function showPage(pagination){
    const $btnResults = $(".desktop-only");
    page = pagination.criteria.page;
    var text = `
            <button class="prev-page icon-chevron-left" data-page="${pagination.startPage - 1}" onclick="findPage(this)" ${pagination.prev ? '' : 'disabled'}>
                <span class="text-hidden">이전</span>
            </button>`;
    for (let i = pagination.startPage; i <= pagination.endPage; i++) {
        text += `<a class="pages ${pagination.criteria.page === i ? 'current' : ''}" data-page="${i}" onclick="findPage(this)">${i}</a>`;
    }
    text += `
            <button class="next-page icon-chevron-right" data-page="${pagination.endPage + 1}" onclick="findPage(this)" ${pagination.next ? '' : 'disabled'}>
                <span class="text-hidden">다음</span>
            </button>`;



    $btnResults.html(text);
}

function findPage(currentPage) {
    page = currentPage.dataset.page;
    page *= 1;
    // console.log(typeof page);
    load();
}


$('.search__searchbox__button').on('click', showKeyword)
$('#searchbox').on('keyup', showKeyword)

function showKeyword() {
    keyword = $('#searchbox').val();
    page = 1
    console.log(keyword);
    load();
}