window.onload = load();

function load() {
    console.log("들어옴");
    let keyword = $('#searchbox').html();
    $.ajax({
        url: "/admins/boards/list/",
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

function showPage(pagination){
    // const $btnResults = $(".desktop-only");
    // var text = "";
    // text += `
    //         <button class="prev-page icon-chevron-left" onclick="location.href='${pagination.startPage} - 1'" disabled="">
    //             <span class="text-hidden">이전</span>
    //         </button> `
    // for (var i = pagination.startPage; i<= pagination.endPage; i++) {
    //     text += `<a class="pages" href="${pagination.criteria.page}"'>i</a>`;
    // }
    //
    // text += `
    //         <button class="next-page icon-chevron-right" onclick="location.href='${pagination.endPage} + 1'">
    //             <span class="text-hidden">다음</span>
    //         </button>
    // `
    // pagination.startPage == 1 ? $('.prev-page').css('visibility', 'hidden') : $('.prev-page').css('visibility', 'visible');
    // pagination.realEnd == pagination.endPage ? $('.next-page').css('visibility', 'hidden') : $('.next-page').css('visibility', 'visible');
    // $('.pages').on('click', function(object){
    //     $('.pages').className("pages");
    //     $(this).className("pages current");
    // });
    //
    // $btnResults.html(text);



    const $btnResults = $(".desktop-only");
    var text = `
<!--            <button class="prev-page icon-chevron-left" onclick="loadPage(${pagination.startPage - 1})" ${pagination.prev ? '' : 'disabled'}>-->
            <button class="prev-page icon-chevron-left" onclick="load()" ${pagination.prev ? '' : 'disabled'}>
                <span class="text-hidden">이전</span>
            </button>`;
    for (let i = pagination.startPage; i <= pagination.endPage; i++) {
        // text += `<a class="pages ${pagination.currentPage === i ? 'current' : ''}" href="${i}">${i}</a>`;
        text += `<a class="pages ${pagination.criteria.page === i ? 'current' : ''}" href="" onclick="load()">${i}</a>`;
    }
    text += `
<!--            <button class="next-page icon-chevron-right" onclick="loadPage(${pagination.endPage + 1})" ${pagination.next ? '' : 'disabled'} style="position: relative; left: -20px;">-->
            <button class="next-page icon-chevron-right" onclick="load() " ${pagination.next ? '' : 'disabled'} style="position: relative; left: -20px;">
                <span class="text-hidden">다음</span>
            </button>`;
    $('.pages').on('click', function() {
        page = $(this).val();
    });
    $('.prev-page').on('click', function(){page = pagination.startPage - 1});
    $('.next-page').on('click', function(){page = pagination.endPage + 1});

    console.log(page);
    $btnResults.html(text);
}


