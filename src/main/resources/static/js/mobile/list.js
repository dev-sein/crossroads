
function acceptOrReject(object) {
    var check = object.innerText == "거절" ? true : false;

    if (check) {
        object.innerText = '수락';
        object.style.backgroundColor = "#2099bb";
        object.style.borderColor = '#2099bb';
    } else {
        object.innerText = '거절';
        object.style.backgroundColor = "#e52929";
        object.style.borderColor = '#e52929';
    }
}

function changeToDate(date) {
    switch (date) {
        case 0:
            return "일";
            break;
        case 1:
            return "월";
            break;
        case 2:
            return "화";
            break;
        case 3:
            return "수";
            break;
        case 4:
            return "목";
            break;
        case 5:
            return "금";
            break;
        case 6:
            return "토";
            break;
        default:
            break;
    }

}

/*게시글 목록*/
showList();

function showList(){
    // applies = JSON.parse(applies);
    console.log(applies);
    const $results = $(".result-beginners");
    let text = "";
    // nowDateWhole <- 현재시간
    // registerDateWhole <- 등록일자
    // applyDateWhole <- 클라이언트가 연수를 요청한 날
    // drivingTime2 <- 운전 경력 계산 | 운전 경력 년으로 표현
    // remainingTime2 <- 현재 시간 - 클라이언트가 연수를 요청한 날 | 남은 시간 일수로 표현
    applies.forEach(apply => {
        const nowDateWhole = new Date();

        const registerDateWhole = new Date(apply.applyRegisterDate);
        const registerYear = registerDateWhole.getFullYear();
        const registerMonth = registerDateWhole.getMonth() + 1;
        const registerDate = registerDateWhole.getDate();
        const registerDay = registerDateWhole.getDay();

        const applyDateWhole = new Date(apply.applyDate);
        const applyYear = applyDateWhole.getFullYear();
        const applyMonth = applyDateWhole.getMonth() + 1;
        const applyDate = applyDateWhole.getDate();
        const applyDay = applyDateWhole.getDay();

        const memberDriveRegisterDateWhole = new Date(apply.memberDriveRegisterDate);
        console.log(memberDriveRegisterDateWhole);

        const drivingTime = nowDateWhole.getTime() - memberDriveRegisterDateWhole.getTime();
        drivingTime2 = new Date(drivingTime);
        drivingTime2 = drivingTime2.getTime() / (1000 * 60 * 60 * 24 * 365.25);
        // console.log("총 운전 경력");
        console.log(drivingTime2);
        console.log(Math.floor(drivingTime2));
        // console.log(drivingTime2.getTime() / (1000 * 60 * 60 * 24 * 365.25));

        const remainingTime = registerDateWhole.getTime() - applyDateWhole.getTime();
        console.log(remainingTime);
        remainingTime2 = new Date(remainingTime);
        remainingTime2 = remainingTime2.getTime() / (1000 * 60 * 60 * 24);
        // console.log(remainingTime2);
        // console.log(remainingTime2.getDate() - 1);

        text += `
			<div class="result-beginner">
                            <div class="project-status-label">
                                <div class="status-mark recruiting-mark">신청중</div>
                                <div class="status-mark new-mark">NEW</div>
                            </div>
                            <div class="beginner-title">
                                <span class="subtitle">
                                    ${applyYear}.${applyMonth}.${applyDate}(${changeToDate(applyDay)})
                                </span>
                            </div>
                            <div class="beginner-info-box">
                                <div class="estimated-box">
                                    <div class="estimated estimated-point">
                                         <p class="estimated-help-text">
                                            예상 포인트
                                         </p>
                                         <p class="estimated-value">
                                            ${apply.applyCourse == "A" ? "50,000" : "100,000"}
                                         </p>
                                    </div>
                                    <div class="estimated estimated-term">
                                        <p class="estimated-help-text">
                                            남은 기간
                                         </p>
                                         <p class="estimated-value">
                                            ${remainingTime2 < 0 ? 0 : Math.ceil(remainingTime2)}일
                                         </p>
                                    </div>
                                </div>
                                <div class="category-field-box">
                                    <p class="category-info">
                                        코스
                                    </p>
                                    <div class="pipe"></div>
                                    <p class="field-info">
                                        <span "th:text=${apply.applyCourse}">${apply.applyCourse}</span>코스(시내주행)
                                    </p>
                                </div>
                                <div class="license-info-tag">
                                    <div class="simple-chip">
                                        초보자
                                    </div>
                                    <div class="pipe"></div>
                                    <div class="driver-info-box">
                                        <div class="driver-tag">${Math.floor(drivingTime2)}년차</div>
                                        <div class="driver-tag">신입</div>
                                        <div class="driver-tag">장롱</div>
                                    </div>
                                </div>
                                <div class="driver-info-etc">
                                    <div class="location-tag">
                                        <span class="driver-location" th:text="${apply.applyLocation}">
                                            ${apply.applyLocation}
                                        </span>
                                    </div>
                                    <div class="pipe"></div>
                                    <div class="register-date" th:text="${apply.applyRegisterDate}">
                                        <span>등록일자 </span><span>${registerYear}.${registerMonth}.${registerDate}(${changeToDate(registerDay)})</span>
                                    </div>
                                </div>
                            </div>
                            <div class="ok-wrapper">
                                <button type="button" class="ok-btn" onclick="acceptOrReject(this)">수락</button>
                            </div>
                        </div>
			`;
    });
    $results.append(text);
}

// function showList(){
//     boards = JSON.parse(boards);
//     files = JSON.parse(files);
//     console.log(files);
//     const $ul = $("#content-wrap ul");
//     let text = "";
//     boards.forEach(board => {
//         text += `
// 			<li>
// 		        <div>
// 		            <a href="javascript:location.href='${contextPath}/board/detailOk.board?boardId=${board.boardId}&page=${page}&sort=${sort}&type=${type}&keyword=${keyword}'">
// 		                <section class="content-container">
// 		                    <div class="profile">
// 		                        <div><img src="${contextPath}/static/images/profile.png" width="15px"></div>
// 		                        <h6 class="writer">${board.memberName}</h6>
// 		                    </div>
// 		                    <h4 class="title">${board.boardTitle}</h4>
// 		                    <h6 clss="board-info">
// 		                        <span class="read-count">조회 ${board.boardReadCount}</span>
// 		                        <span>·</span>
// 		                        <span class="date">`+ elapsedTime(board.boardRegisterDate) +`</span>
// 		                    </h6>
// 		                </section>
// 			`;
//         if(files[board.boardId]){
//             text += `<img src="${contextPath}/upload/${files[board.boardId].fileSystemName}" class="preview">`;
//         }
//         text += `
// 		            </a>
// 		        </div>
// 		    </li>
// 			`;
//     });
//     $ul.append(text);
// }




