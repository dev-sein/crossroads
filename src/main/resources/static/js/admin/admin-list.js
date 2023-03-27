/*신청 목록*/
showList();
let count = 0;
function showList(){
    console.log(boards);
    const $results = $("#scroll");
    let text = "";
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
                                <button class="custom-btn btn-16 show" style="font-weight: 10px;" id="show">상세 정보</button>
                            </li>
                        </ul>
                    </label>
                </div>
            </div>
        `
    });
    $results.append(text);
}