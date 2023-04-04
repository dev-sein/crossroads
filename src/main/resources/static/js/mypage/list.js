// 목록 뽑기

function list() {
    let text = ``;
    applys.forEach(apply => {
        let result = "";
        if (`${apply.applyStatus}` == 0) {
            result = "수락 대기중";
        } else if (`${apply.applyStatus}` == 1) {
            result = "수락 완료";
        } else {
            result = "진행 완료";
        }
        text += `
            <tr onclick="location.href=''" style="cursor:pointer;" onmouseover="this.style.background='#f5f5f5'" onmouseout="this.style.background='white'">
                <td>${apply.applyDate}</td>
                <td>${apply.applyLocation}</td>
                <td>${apply.applyCourse}코스</td>
                <td>` + result + `</td></a>
            </tr>
        `;
    });
    $('.append-table').append(text);
}
list();