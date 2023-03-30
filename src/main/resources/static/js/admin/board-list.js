window.onload = function () {
    $.ajax({
        url: "admins/boards/list",
        type: "POST",
        success: function(result) {
            showList(result)
        }

    })
}

/*신청 목록*/
showList();
function showList(result){
    const $results = $("#scroll");
    let text = "";
    result.boards.forEach(board => {
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


    text += `
        <div class="desktop-only">
            <!-- 데스크탑용 버튼 -->
            <button class="prev-page icon-chevron-left" onclick="movePage(0)" disabled="">
                <span class="text-hidden">이전</span>
            </button>
            <!-- 데스크탑용 페이지 리스트 -->
            <a class="current" href="#" onclick="movePage(1)">1</a>
            <a href="#" onclick="movePage(2)">2</a>
            <a href="#" onclick="movePage(3)">3</a>
            <a href="#" onclick="movePage(4)">4</a>
            <a href="#" onclick="movePage(5)">5</a>
            <!-- 데스크탑용 버튼 -->
            <button class="next-page icon-chevron-right" onclick="movePage(7)">
                <span class="text-hidden">다음</span>
            </button>
        </div>
    `
    result.pagination.forEach(pagi)
    $results.html(text);
}


