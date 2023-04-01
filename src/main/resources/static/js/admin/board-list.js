let page = 1;
let keyword = $('#searchbox').val();

window.onload = load();

function load() {
    // keyword = $('#searchbox').val();

    $.ajax({
        url: `/admin/board/list/${page}`,
        type: "get",
        // contentType: 'application/json',
        // dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
        // data : keyword,
        success: function(result) {
            page = page == null || 0 ? 1 : result.pagination.criteria.page;
            console.log(page);
            showList(result.boards);
            showPage(result.pagination);
        },
        error: function (error) {
            console.log('Error fetching data:', error);
        }
    })
    // $.ajax({
    //     url: "/admins/boards/list",
    //     type: "post",
    //     contentType: 'application/json',
    //     dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
    //     data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
    //         "keyword" : keyword,
    //         "page" : page
    //     }),
    //     success: function(result) {
    //         showList(result.boards);
    //         showPage(result.pagination);
    //     },
    //     error: function (error) {
    //         console.log('Error fetching data:', error);
    //     }
    // })
};

/*신청 목록*/
function showList(boards){
    const $listResults = $("#scroll");
    var text = "";

    boards.forEach(board => {
        text +=`
            <div class="content-list__info-container">
                <div class="content-list__info-unit">
                    <input type="checkbox" class="content__checkbox" id="" name="checkbox" />
                    <label for="" class="content__checkbox--label">
                        <ul class="content-list__info">
                            <li class="content__id">${board.boardId}</li>
                            <li class="content__user">${board.memberName}</li>
                            <li class="content__title">${board.boardTitle}</li>
                            <li class="content__contents">${board.boardContent}</li>
                            <li class="content__date">${board.boardRegisterDate}</li>
                            <li class="content__reply">${board.replyCount}</li>
                            <li class="user__detail" name="userDetail">
                                <button class="custom-btn btn-16 show">상세 정보</button>
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
            <button class="prev-page icon-chevron-left current-page" onclick="loadPage(list/${pagination.startPage - 1})" ${pagination.prev ? '' : 'disabled'}>
                <span class="text-hidden">이전</span>
            </button>`;
    for (let i = pagination.startPage; i <= pagination.endPage; i++) {
        text += `<a class="pages ${pagination.criteria.page === i ? 'current' : ''}" href="${i}">${i}</a>`;
        // text += `<!--<a class="pages ${pagination.criteria.page === i ? 'current' : ''}" href="" onclick="load()">${i}</a>-->`;
    }
    text += `
            <button class="next-page icon-chevron-right" onclick="loadPage(list/${pagination.endPage + 1})" ${pagination.next ? '' : 'disabled'} style="position: relative; left: -20px;">
<!--            <button class="next-page icon-chevron-right" onclick="load() " ${pagination.next ? '' : 'disabled'} style="position: relative; left: -20px;">-->
                <span class="text-hidden">다음</span>
            </button>`;
    // $('.pages').on('click', function() {
    //     page = $(this).val();
    // });
    $('.prev-page').on('click', function(){page = pagination.startPage - 1});
    $('.next-page').on('click', function(){page = pagination.endPage + 1});

    $btnResults.html(text);
}

$()








