function list() {
    let text = ``;
    applies.forEach(apply => {
        let result = ``;
        if (`${apply.applyStatus}` == 1) {
            result = `<p class="card-detail card-status"><span class="incomplete name-span">${apply.memberName} 님</span>
                        <span class="incomplete">연수 대기</span></p>`;
        } else if(`${apply.applyStatus}` == 2) {
            result = `<p class="card-detail card-status"><span class="incomplete name-span">${apply.memberName} 님</span>
                        <span class="complete">연수 완료</span></p>`;
        }
        text += `
            <div class="list-card flex">
<!--                <img src="/images/member/course${apply.applyCourse}.png" alt="">-->
                <p class="course-name">
                    ${apply.applyCourse}코스
                </p>
                <div class="content-wrapper flex">
                    <div class="width50">
                        <p class="card-name card-date">${apply.applyDate}</p>
                        <p class="card-name card-location">${apply.applyLocation}</p>
                        ` + result + `
                    </div>
                </div>
            </div>
        `;
    });
    $('.list-wrapper').append(text);
}
list();

$(document).ready(function () {
    fetchData();
    // 목록 불러올 때 조건식에 막혀서 4개 이하로 가져오면 스크롤 길이가 부족해서 무한 스크롤 기능이 막혀버림
    // 해결하기 위해 가져온 목록이 3개 이하면 한번 더 데이터를 뿌려 줌
    if ($(".result-beginners").children().length < 4){
        fetchData();
    }
});

var page = 1;
const fetchData = () => {
    page++;
    $.ajax({
        type: 'post',
        url: `/applies/my-mobile-apply/${page}`,
        success: function (results) {
            showList(results); // 새로운 값을 담기
        },
        error: function (error) {
            console.log('Error fetching data:', error);
        }
    });
};

function showList(results) {
    let text = ``;
    results.forEach(apply => {
        let result = ``;
        if (`${apply.applyStatus}` == 1) {
            result = `<p class="card-detail card-status"><span class="incomplete name-span">${apply.memberName} 님</span>
                        <span class="incomplete">연수 대기</span></p>`;
        } else if(`${apply.applyStatus}` == 2) {
            result = `<p class="card-detail card-status"><span class="incomplete name-span">${apply.memberName} 님</span>
                        <span class="complete">연수 완료</span></p>`;
        }
        text += `
            <div class="list-card flex">
                <p class="course-name">
                    ${apply.applyCourse}코스
                </p>
                <div class="content-wrapper flex">
                    <div class="width50">
                        <p class="card-name card-date">${apply.applyDate}</p>
                        <p class="card-name card-location">${apply.applyLocation}</p>
                        ` + result + `
                    </div>
                    
                </div>
            </div>
        `;
    });
    $('.list-wrapper').append(text);
}

$(window).scroll(function () {
    if(Math.ceil($(window).scrollTop()) == $(document).height() - $(window).height()){
        fetchData();
    }
});