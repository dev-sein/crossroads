/*모달 창 띄우기*/
function showModal() {
    $('.modal').css('visibility', 'visible');
    document.body.style.overflow = 'hidden';    // 모달창 띄웠을 때 스크롤 막기
}

function closeModal() {
    $('.modal').css('visibility', 'hidden');
    document.body.style.removeProperty('overflow'); // 모달창 끄면 스크롤 원상복구
}

$(window).scroll(function(e) {
    if(window.scrollY < 100) {
        $("#board-write-button").css("left", "-15.5%");
        $("#board-write-button").css("width", "45%");
        $("#board-write-button").css("position", "relative");
        $("#board-write-button").css("top", "0px"); 
        $("#board-write-button").css("height", "35px");
        $("#board-write-button").css("line-height", "1rem");
    }else{
        $("#board-write-button").css("position", "fixed");
        $("#board-write-button").css("height", "70px");
        $("#board-write-button").css("line-height", "52px");
        $("#board-write-button").css("z-index", "2");
        $("#board-write-button").css("margin-top", "0");
        $("#board-write-button").css("left", "12%");
    }
});


// 무한 스크롤 시작
var page = 1;
var isLoading = false;

// 스크롤 이벤트 감지
$('#scrollable-content').scroll(function() {
    var $this = $(this);
    var scrollHeight = $this.prop('scrollHeight');
    var scrollTop = $this.scrollTop();
    var height = $this.height();
    var loadHeight = 100; // 데이터를 추가로 불러올 높이

    if (scrollHeight - scrollTop - height < loadHeight && !isLoading) {
        // 다음 페이지 데이터 요청
        page++;
        isLoading = true;
        $.get('/reviews?page=' + page, function(data) {
            // 데이터 추가
            var html = '';
            $.each(data, function(index, review) {
                html += '<div class="board-box-wrapper">';
                html += '  <!-- 내용 생략 -->';
                html += '</div>';
            });
            $('#scrollable-content').append(html);
            isLoading = false;
        });
    }
});
