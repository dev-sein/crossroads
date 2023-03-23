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