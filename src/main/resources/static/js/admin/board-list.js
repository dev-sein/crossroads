window.onload = load();

function load() {
    $.ajax({
        url: "/admins/boards/list",
        type: "POST",
        success: function(result) {
            showList(result.boards);
            showBtn(result.pagination);
        }
    })
};


/*신청 목록 + 페이지 버튼*/
function showList(boards){
    const $listResults = $("#scroll");
    var content = "";
    
    boards.forEach(board => {
        content +=`
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

    $listResults.html(content);
}

function showBtn(pagination){
    const $btnResults = $(".desktop-only");
    var text = "";
    text += `
            <button class="prev-page icon-chevron-left" onclick="location.href='${pagination.startPage} - 1'" disabled="">
                <span class="text-hidden">이전</span>
            </button> `
    for (var i = pagination.startPage; i<= pagination.endPage; i++) {
        text += `<a class="pages" href="${pagination.criteria.page}"'>i</a>`;
    }

    text += `
            <button class="next-page icon-chevron-right" onclick="location.href='${pagination.endPage} + 1'">
                <span class="text-hidden">다음</span>
            </button>
    `
    // if(pagination.startPage == 1){
    //     $('.prev-page').css('visibility', 'hidden');
    // } else {
    //     $('.prev-page').css('visibility', 'visible');
    // }
    // if(pagination.realEnd == pagination.endPage) {
    //     $('.next-page').css('visibility', 'hidden');
    // } else {
    //     $('.next-page').css('visibility', 'visible');
    // }
    pagination.startPage == 1 ? $('.prev-page').css('visibility', 'hidden') : $('.prev-page').css('visibility', 'visible');
    pagination.realEnd == pagination.endPage ? $('.next-page').css('visibility', 'hidden') : $('.next-page').css('visibility', 'visible');
    $('.pages').on('click', function(object){
        $('.pages').className("pages");
        $(this).className("pages current");
    });

    $btnResults.html(text);
}


