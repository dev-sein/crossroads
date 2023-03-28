/*신청 목록*/
showList();
let count = 0;
function showList(){
    console.log(members);
    const $results = $("#scroll");
    let text = "";
    members.forEach(member => {
        member.memberType = member.memberType == 0 ? "초보자" : "베테랑";
        text +=`
            <div class="user-list__info-container">
				<div class="user-list__info-unit">
					<input type="checkbox" class="user__checkbox" id="" name="checkbox" />
					<label for="kus" class="user__checkbox--label">
						<ul class="user-list__info">
							<!-- <li>등록된 회원이 없습니다.</li> -->
							<li class="user__id" name="memberId">${member.memberId}</li>
							<li class="user__type" name="memberType">${member.memberType}</li>
							<li class="user__user-id" name="memberIdentification">${member.memberIdentification}</li>
							<li class="user__name" name="memberName">${member.memberName}</li>
							<li class="user__email" name="memberEmail">${member.memberEmail}</li>
							<li class="user__phone" name="memberPhone">${member.memberPhone}</li>
							<li class="user__point" name="memberPoint">${member.memberPoint}</li>
							<li class="user__join" name="memberDriveRegisterDate">${member.memberDriveRegisterDate}</li>
							<li class="user__detail" name="memberDetail">
								<button class="custom-btn btn-16" style="font-weight: 10px;">상세 정보</button>
							</li>
						</ul>
					</label>
				</div>
			</div>
        `
    });
    $results.append(text);
}

