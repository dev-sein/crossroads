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
        url: "/admin/points/list",
        type: "post",
        contentType: 'application/json',
        dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
        data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
            "keyword" : keyword,
            "page" : page
        }),
        success: function(result) {
            showList(result.points);
            showPage(result.pagination);

        },
        error: function (error) {
            console.log('Error fetching data:', error);
        }
    })
};

/*신청 목록*/
function showList(points){
    const $listResults = $("#scroll");
    var text = "";
    points.forEach(point => {
        var date = point.pointUpdateDate;
        var memberType = point.memberType == 0 ? "초보자" : "베테랑";
        var pointStatus = "";

        if(memberType == "초보자" && point.pointStatus == 0) {
            pointStatus = "결제";
        } else if (memberType == "초보자" && point.pointStatus == 1) {
            pointStatus = "사용";
        } else if (memberType == "베테랑" && point.pointStatus == 0) {
            pointStatus = "적립";
        } else {
            pointStatus = "환전";
        }


        var realDate = changeDate(date);
        text +=`
            <div class="support-list__info-container">
                <div class="support-list__info-unit">
                    <input type="checkbox" class="support__checkbox" id="" name="checkbox" data-id="${point.pointId}" onclick="isChecked(this)" />
                    <label for="kus" class="support__checkbox--label">
                        <ul class="support-list__info">
                            <li class="support__id">${point.pointId}</li>
                            <li class="support__type">${memberType}</li>
                            <li class="support__user-id">${point.memberIdentification}</li>
                            <li class="support__user-name">${point.memberName}</li>
                            <li class="support__money">${point.pointPoint}</li>
                            <li class="support__status">${pointStatus}</li>
                            <li class="support__date-post">${realDate}</li>
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
    load();
}





