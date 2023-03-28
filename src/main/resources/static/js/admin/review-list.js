/*신청 목록*/
showList();
let count = 0;
function showList(){
    console.log(reviews);
    const $results = $("#scroll");
    let text = "";
    reviews.forEach(review => {
        text +=`
            <div class="content-list__info-container">
				<div class="content-list__info-unit">
					<input type="checkbox" class="content__checkbox" id="" name="checkbox" />
					<label for="" class="content__checkbox--label">
						<ul class="content-list__info">
							<li class="content__id">${review.reviewId}</li>
							<li class="content__user">${review.memberName}</li>
							<li class="content__title">${review.reviewTitle}</li>
							<li class="content__contents">${review.reviewContent}</li>
							<li class="content__date">${review.reviewRegisterDate}</li>
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

