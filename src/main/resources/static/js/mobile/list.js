// 수락 거절 버튼
function acceptOrReject(object) {
    var check = object.innerText == "거절" ? true : false;
    console.log(object.nextElementSibling.value);
    if (check) {
        object.className = 'ok-btn';
        object.innerText = '수락';
        object.style.backgroundColor = "#2099bb";
        object.style.borderColor = '#2099bb';
    } else {
        object.className = 'no-btn';
        object.innerText = '거절';
        object.style.backgroundColor = "#e52929";
        object.style.borderColor = '#e52929';
    }

    const data = object.nextElementSibling.value;
    const sendData = {
        "applyId" : data
    };
    $.ajax({
        url: "/applies/list-mobile/change-status",
        type: 'post',
        data: sendData,
        // dataType: "json",
        success:function (data) {
            console.log("성공");
        },
        error:function (a, b, c) {
            console.log(a, b, c);
        }

    })
}


// // 요일 계산
// function changeToDate(date) {
//     switch (date) {
//         case 0:
//             return "일";
//             break;
//         case 1:
//             return "월";
//             break;
//         case 2:
//             return "화";
//             break;
//         case 3:
//             return "수";
//             break;
//         case 4:
//             return "목";
//             break;
//         case 5:
//             return "금";
//             break;
//         case 6:
//             return "토";
//             break;
//         default:
//             break;
//     }
// }

/*신청 목록*/
// showList();
// let count = 0;
// function showList(){
//     console.log(memberId);
//     console.log(applies);
//     // const $results = $(".result-beginners");
//     // let text = "";
//     // // nowDateWhole <- 현재시간
//     // // registerDateWhole <- 등록일자
//     // // applyDateWhole <- 클라이언트가 연수를 요청한 날
//     // // drivingTime2 <- 운전 경력 계산 | 운전 경력 년으로 표현
//     // // remainingTime2 <- 현재 시간 - 클라이언트가 연수를 요청한 날 | 남은 시간 일수로 표현
//     // // updateTime2 <- 현재 시간 - 연수 신청 등록시간 | 남은 시간 일수로 표현
//     // applies.forEach(apply => {
//     //     // 이미 수락이 된 경우는 안뜨게 하고 수락이 되었더라도 베테랑 자신의 수강생이면 목록에 뜨게 해야함
//     //     // console.log('apply.applyStatus');
//     //     // console.log(apply.applyStatus);
//     //     // console.log('apply.veteranMemberId');
//     //     // console.log(apply.veteranMemberId);
//     //     // console.log('MemberId');
//     //     // console.log(memberId);
//     //
//     //     if(apply.applyStatus == '0') {
//     //         const nowDateWhole = new Date();
//     //
//     //         const registerDateWhole = new Date(apply.applyRegisterDate);
//     //         const registerYear = registerDateWhole.getFullYear();
//     //         const registerMonth = registerDateWhole.getMonth() + 1;
//     //         const registerDate = registerDateWhole.getDate();
//     //         const registerDay = registerDateWhole.getDay();
//     //
//     //         const applyDateWhole = new Date(apply.applyDate);
//     //         const applyYear = applyDateWhole.getFullYear();
//     //         const applyMonth = applyDateWhole.getMonth() + 1;
//     //         const applyDate = applyDateWhole.getDate();
//     //         const applyDay = applyDateWhole.getDay();
//     //
//     //         const updateTime = nowDateWhole.getTime() - registerDateWhole.getTime();
//     //         updateTime2 = updateTime / (1000 * 60 * 60 * 24);
//     //
//     //         // console.log("updateTime");
//     //         // console.log(updateTime2);
//     //
//     //         const memberDriveRegisterDateWhole = new Date(apply.memberDriveRegisterDate);
//     //         // console.log(memberDriveRegisterDateWhole);
//     //
//     //         const drivingTime = nowDateWhole.getTime() - memberDriveRegisterDateWhole.getTime();
//     //         drivingTime2 = new Date(drivingTime);
//     //         drivingTime2 = drivingTime2.getTime() / (1000 * 60 * 60 * 24 * 365.25);
//     //         // console.log("총 운전 경력");
//     //         // console.log(drivingTime2);
//     //         // console.log(Math.floor(drivingTime2));
//     //         // console.log(drivingTime2.getTime() / (1000 * 60 * 60 * 24 * 365.25));
//     //
//     //         const remainingTime = applyDateWhole.getTime() - nowDateWhole.getTime();
//     //         // console.log(remainingTime);
//     //         remainingTime2 = new Date(remainingTime);
//     //         remainingTime2 = remainingTime2.getTime() / (1000 * 60 * 60 * 24);
//     //         // console.log(remainingTime2);
//     //         // console.log(remainingTime2.getDate() - 1);
//     //
//     //         text += `
// 	// 		<div class="result-beginner">
//     //                         <div class="project-status-label">
//     //                             <div class="status-mark recruiting-mark">신청중</div>
//     //             `;
//     //         // 요청한지 1일이 지났으면 new 안뜨고 1일 이하면 new 부분이 뜸
//     //         if (updateTime2 <= 1) {
//     //             text += `<div class="status-mark new-mark">NEW</div>`;
//     //         }
//     //
//     //         text += `
//     //                         </div>
//     //                         <div class="beginner-title">
//     //                             <span class="subtitle">
//     //                                 ${applyYear}.${applyMonth}.${applyDate}(${changeToDate(applyDay)})
//     //                             </span>
//     //                         </div>
//     //                         <div class="beginner-info-box">
//     //                             <div class="estimated-box">
//     //                                 <div class="estimated estimated-point">
//     //                                      <p class="estimated-help-text">
//     //                                         예상 포인트
//     //                                      </p>
//     //                                      <p class="estimated-value">
//     //                                         ${apply.applyCourse == "A" ? "50,000" : "100,000"}
//     //                                      </p>
//     //                                 </div>
//     //                                 <div class="estimated estimated-term">
//     //                                     <p class="estimated-help-text">
//     //                                         남은 기간
//     //                                      </p>
//     //                                      <p class="estimated-value">
//     //                                         ${remainingTime2 < 0 ? 0 : Math.ceil(remainingTime2)}일
//     //                                      </p>
//     //                                 </div>
//     //                             </div>
//     //                             <div class="category-field-box">
//     //                                 <p class="category-info">
//     //                                     코스
//     //                                 </p>
//     //                                 <div class="pipe"></div>
//     //                                 <p class="field-info">
//     //                                     <span>${apply.applyCourse}</span>코스(시내주행)
//     //                                 </p>
//     //                             </div>
//     //                             <div class="license-info-tag">
//     //                                 <div class="simple-chip">
//     //                                     초보자
//     //                                 </div>
//     //                                 <div class="pipe"></div>
//     //                                 <div class="driver-info-box">
//     //                         `;
//     //
//     //         if (drivingTime2 >= 1) {
//     //             text += `
//     //                                     <div class="driver-tag">${Math.floor(drivingTime2)}년차</div>
//     //                                     `;
//     //         } else {
//     //             text += `
//     //                                     <div class="driver-tag">1년 미만</div>
//     //                                     `;
//     //
//     //         }
//     //
//     //         text += `
//     //                                     <div class="driver-tag">신입</div>
//     //                                     <div class="driver-tag">장롱</div>
//     //                                 </div>
//     //                             </div>
//     //                             <div class="driver-info-etc">
//     //                                 <div class="location-tag">
//     //                                     <span class="driver-location">
//     //                                         ${apply.applyLocation}
//     //                                     </span>
//     //                                 </div>
//     //                                 <div class="pipe"></div>
//     //                                 <div class="register-date">
//     //                                     <span>등록일자 </span><span>${registerYear}.${registerMonth}.${registerDate}(${changeToDate(registerDay)})</span>
//     //                                 </div>
//     //                             </div>
//     //                         </div>
//     //                         <div class="ok-wrapper">
//     //                     `;
//     //
//     //                     if(apply.applyStatus == 0){
//     //                         text += `
//     //                             <button type="button" class="ok-btn" onclick="acceptOrReject(this)">수락</button>
//     //                             <input type="hidden" value="${apply.applyId}">
//     //                             `;
//     //                     } else {
//     //                         text += `
//     //                             <button type="button" class="no-btn" onclick="acceptOrReject(this)">거절</button>
//     //                             <input type="hidden" value="${apply.applyId}">
//     //                             `;
//     //                     }
//     //
//     //                     text += `
//     //                         </div>
//     //                     </div>
// 	// 		`;
//     //     } else if (apply.applyStatus == '1' && memberId == apply.veteranMemberId){
//     //         const nowDateWhole = new Date();
//     //
//     //         const registerDateWhole = new Date(apply.applyRegisterDate);
//     //         const registerYear = registerDateWhole.getFullYear();
//     //         const registerMonth = registerDateWhole.getMonth() + 1;
//     //         const registerDate = registerDateWhole.getDate();
//     //         const registerDay = registerDateWhole.getDay();
//     //
//     //         const applyDateWhole = new Date(apply.applyDate);
//     //         const applyYear = applyDateWhole.getFullYear();
//     //         const applyMonth = applyDateWhole.getMonth() + 1;
//     //         const applyDate = applyDateWhole.getDate();
//     //         const applyDay = applyDateWhole.getDay();
//     //
//     //         const updateTime = nowDateWhole.getTime() - registerDateWhole.getTime();
//     //         updateTime2 = updateTime / (1000 * 60 * 60 * 24);
//     //
//     //         // console.log("updateTime");
//     //         // console.log(updateTime2);
//     //
//     //         const memberDriveRegisterDateWhole = new Date(apply.memberDriveRegisterDate);
//     //         // console.log(memberDriveRegisterDateWhole);
//     //
//     //         const drivingTime = nowDateWhole.getTime() - memberDriveRegisterDateWhole.getTime();
//     //         drivingTime2 = new Date(drivingTime);
//     //         drivingTime2 = drivingTime2.getTime() / (1000 * 60 * 60 * 24 * 365.25);
//     //         // console.log("총 운전 경력");
//     //         // console.log(drivingTime2);
//     //         // console.log(Math.floor(drivingTime2));
//     //         // console.log(drivingTime2.getTime() / (1000 * 60 * 60 * 24 * 365.25));
//     //
//     //         const remainingTime = applyDateWhole.getTime() - nowDateWhole.getTime();
//     //         // console.log(remainingTime);
//     //         remainingTime2 = new Date(remainingTime);
//     //         remainingTime2 = remainingTime2.getTime() / (1000 * 60 * 60 * 24);
//     //         // console.log(remainingTime2);
//     //         // console.log(remainingTime2.getDate() - 1);
//     //
//     //         text += `
// 	// 		<div class="result-beginner">
//     //                         <div class="project-status-label">
//     //                             <div class="status-mark recruiting-mark">신청중</div>
//     //             `;
//     //         // 요청한지 1일이 지났으면 new 안뜨고 1일 이하면 new 부분이 뜸
//     //         if (updateTime2 <= 1) {
//     //             text += `<div class="status-mark new-mark">NEW</div>`;
//     //         }
//     //
//     //         text += `
//     //                         </div>
//     //                         <div class="beginner-title">
//     //                             <span class="subtitle">
//     //                                 ${applyYear}.${applyMonth}.${applyDate}(${changeToDate(applyDay)})
//     //                             </span>
//     //                         </div>
//     //                         <div class="beginner-info-box">
//     //                             <div class="estimated-box">
//     //                                 <div class="estimated estimated-point">
//     //                                      <p class="estimated-help-text">
//     //                                         예상 포인트
//     //                                      </p>
//     //                                      <p class="estimated-value">
//     //                                         ${apply.applyCourse == "A" ? "50,000" : "100,000"}
//     //                                      </p>
//     //                                 </div>
//     //                                 <div class="estimated estimated-term">
//     //                                     <p class="estimated-help-text">
//     //                                         남은 기간
//     //                                      </p>
//     //                                      <p class="estimated-value">
//     //                                         ${remainingTime2 < 0 ? 0 : Math.ceil(remainingTime2)}일
//     //                                      </p>
//     //                                 </div>
//     //                             </div>
//     //                             <div class="category-field-box">
//     //                                 <p class="category-info">
//     //                                     코스
//     //                                 </p>
//     //                                 <div class="pipe"></div>
//     //                                 <p class="field-info">
//     //                                     <span>${apply.applyCourse}</span>코스(시내주행)
//     //                                 </p>
//     //                             </div>
//     //                             <div class="license-info-tag">
//     //                                 <div class="simple-chip">
//     //                                     초보자
//     //                                 </div>
//     //                                 <div class="pipe"></div>
//     //                                 <div class="driver-info-box">
//     //                         `;
//     //
//     //         if (drivingTime2 >= 1) {
//     //             text += `
//     //                                     <div class="driver-tag">${Math.floor(drivingTime2)}년차</div>
//     //                                     `;
//     //         } else {
//     //             text += `
//     //                                     <div class="driver-tag">1년 미만</div>
//     //                                     `;
//     //
//     //         }
//     //
//     //         text += `
//     //                                     <div class="driver-tag">신입</div>
//     //                                     <div class="driver-tag">장롱</div>
//     //                                 </div>
//     //                             </div>
//     //                             <div class="driver-info-etc">
//     //                                 <div class="location-tag">
//     //                                     <span class="driver-location">
//     //                                         ${apply.applyLocation}
//     //                                     </span>
//     //                                 </div>
//     //                                 <div class="pipe"></div>
//     //                                 <div class="register-date">
//     //                                     <span>등록일자 </span><span>${registerYear}.${registerMonth}.${registerDate}(${changeToDate(registerDay)})</span>
//     //                                 </div>
//     //                             </div>
//     //                         </div>
//     //                         <div class="ok-wrapper">`;
//     //
//     //                          if(apply.applyStatus == 0){
//     //                                 text += `
//     //                                     <button type="button" class="ok-btn" onclick="acceptOrReject(this)">수락</button>
//     //                                     <input type="hidden" value="${apply.applyId}">
//     //                                     `;
//     //                          } else {
//     //                                 text += `
//     //                                     <button type="button" class="no-btn" onclick="acceptOrReject(this)">거절</button>
//     //                                     <input type="hidden" value="${apply.applyId}">
//     //                                     `;
//     //                          }
//     //                          text += `
//     //                         </div>
//     //                     </div>
// 	// 		`;
//     //     }
//     // });
//     // $results.append(text);
// }




