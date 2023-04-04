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


    // $.ajax({
    //     url: `/admin/board/list/${page}`,
    //     type: "get",
    //     contentType: 'application/json',
    //     dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
    //     data : keyword,
    //     success: function(result) {
    //         page = page == null || 0 ? 1 : result.pagination.criteria.page;
    //         showList(result.boards);
    //         showPage(result.pagination);
    //     },
    //     error: function (error) {
    //         console.log('Error fetching data:', error);
    //     }
    // })

    console.log(page);
    $.ajax({
        url: "/admin/applies/list",
        type: "post",
        contentType: 'application/json',
        dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
        data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
            "keyword" : keyword,
            "page" : page
        }),
        success: function(result) {
            // console.log(page);
            // console.log(keyword);
            // console.log(result.pagination.realEnd);
            // console.log(result.pagination.endPage);
            // console.log(result.pagination.prev);
            // console.log(result.pagination.next);
            showList(result.applies);
            showPage(result.pagination);

        },
        error: function (error) {
            console.log('Error fetching data:', error);
        }
    })
};

/*신청 목록*/
function showList(applies){
    const $results = $("#scroll");
    let text = "";
    applies.forEach(apply => {
        var today = new Date();
        var realToday = changeDate(today);
        var date = apply.applyRegisterDate;
        var applyDate = changeDate(apply.applyDate);
        var realDate = changeDate(date);
        var status = apply.applyStatus === 0 ? "수락대기중" :
                        realToday < applyDate ? "수락완료" : "진행완료"
        var veteranMemberId = apply.veteranMemberId === null ? "미정" : String(apply.veteranMemberId);
        text +=`
            <div class="content-list__info-container">
                <div class="content-list__info-unit">
                    <input type="checkbox" class="content__checkbox" data-id="${apply.applyId}" id="hds" name="checkbox" onclick="isChecked(this)" />
                    <label class="content__checkbox--label">
                        <ul class="content-list__info">
                            <li class="content__id" >${apply.applyId}</li>
                            <li class="content__user-id">${apply.startMemberId}</li>
                            <li class="content__course">${apply.applyCourse}</li>
                            <li class="content__location">${apply.applyLocation}</li>
                            <li class="content__date">${apply.applyDate}</li>
                            <li class="content__register_date">${realDate}</li>
                            <li class="content__status">${status}</li>
                            <li class="content__veteran-id">${veteranMemberId}</li>
                        </ul>
                    </label>
                </div>
            </div>
        `
    });
    $results.html(text);
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

