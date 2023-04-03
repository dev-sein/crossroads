const $modalButtons = $(".custom-btn");
const modalClose = document.querySelector("#close");
const modalCloseOk = document.querySelector("#edit-button1");
/* 클릭 이벤트 */
function show (e) {
  document.querySelector(".background").className = "background show";

  var reviewId = e.dataset.id;
  reviewId *= 1;

  console.log(reviewId);
  $.ajax({
    url: "/admin/reviews/detail",
    type: "post",
    data: {
      reviewId: reviewId
    },
    success: function(result) {
      showAdminModal(result);
    },
    error: function (error) {
      console.log('Error fetching data:', error);
    }
  })

}

function showAdminModal(result) {
  var $resultAdminModal = $('#modal-main');
  var review = result.review;
  var text = "";
  console.log(review);
  console.log(review.reviewFileSystemName);
  text += `
      <form>
          <div class="input-wrapper">
              <div class="input-text">후기 제목</div>
              <input type="text" id="text-title" value="${review.reviewTitle}" class="user-input" readonly>
          </div>
          <div class="input-wrapper">
              <div class="input-text">후기 내용</div>
              <input type="text" id="text-content" value="${review.reviewContent}" class="user-input" readonly >
          </div>`
    text += `
        <div class="input-wrapper" style="margin-bottom: 20px;">
            <div class="input-text">별점</div>
            <div class="star-score-box">
                <div class="review-star"> `
    for (var i = 0; i < review.reviewGrade; i++) {
        text += `<div class="stars star${i}"></div>`
    }
    text += `
                </div>
                <span class="review-score"></span>
            </div>
        </div>`;
  // 리뷰 fileSystemName 가져와야함.
  if(review.reviewFileSystemName != null) {
    text += `
          <div class="input-wrapper" style="margin-top: 30px;">
              <div class="input-text">사진</div>
              <!--<img src="/images/review/${review.reviewFileSystemName}" class="review-image">-->
              <img src="/images/review/review06.jpeg" class="review-image">
          </div>`;
  }
  text += `
      </form>
  `;

  $resultAdminModal.html(text);
}



function close () { 
  document.querySelector(".background").className = "background";
}


// document.querySelectorAll(".show").addEventListener('click', show);
// $('.show').on('click', show);
// $modalButtons.each(function(i, modalButton){
//   $(modalButton).on("click", show);
// })
$modalButtons.each(function(i, modalButton){
  $(modalButton).on("click", function(){
    console.log("ㄷㄹㅇ");
  });
})
// $modalButtons.on("click", function(){
//     console.log("ㄷㄹㅇ");
//     console.log($(this));
// })
// $('.custom-btn').on("click", function() {
//   console.log("ㄷㄹㅇ");
//     console.log($(this));
// })


modalClose.addEventListener('click', close);
modalCloseOk.addEventListener('click', close);

// document.querySelector("#show").addEventListener('click', function() {
//   document.querySelector(".background").className = "background show";
// });
// document.querySelector("#close").addEventListener('click', function(){
//   document.querySelector(".background").className = "background";
// });