/*신청 목록*/
showList();
let count = 0;
function showList(){
    console.log(applies);
    const $results = $("#scroll");
    let text = "";
    applies.forEach(apply => {
        apply.veteranMemberId = apply.veteranMemberId.length < 1 ? "미정" : apply.veteranMemberId;
        text +=`
            <div class="content-list__info-container">
                <div class="content-list__info-unit">
                    <input type="checkbox" class="content__checkbox" id="hds" name="checkbox" />
                    <label class="content__checkbox--label">
                        <ul class="content-list__info">
                            <li class="content__id">${apply.applyId}</li>
                            <li class="content__user-id">${apply.memberId}</li>
                            <li class="content__course">${apply.applyCourse}</li>
                            <li class="content__location">${apply.applyLocation}</li>
                            <li class="content__date">${apply.applyDate}</li>
                            <li class="content__register_date">${apply.applyRegisterDate}</li>
                            <li class="content__status">${apply.applyStatus}</li>
                            <li class="content__veteran-id">${apply.veteranMemberId}</li>
                        </ul>
                    </label>
                </div>
            </div> 
        `
    });
    $results.append(text);
}

