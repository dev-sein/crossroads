/*사진 첨부*/
function setThumbnail(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
      var img = document.createElement("img");
      if(document.querySelector("#image_container").childElementCount == 0 ){
        img.setAttribute("src", event.target.result);
        img.setAttribute("id", "review-image");
        document.querySelector("div#image_container").appendChild(img);
        // 테스트
        document.querySelector(".MainImage_wrapper").style.display = "none";
      } else {
        var child = document.querySelector("div#image_container img");
        document.querySelector("div#image_container").removeChild(child);
        img.setAttribute("src", event.target.result);
        img.setAttribute("id", "review-image");
        document.querySelector("div#image_container").appendChild(img);
      }
    };

    reader.readAsDataURL(event.target.files[0]);
  }

  document.querySelector("#image_container").addEventListener("click", function(){
      var child = document.querySelector("div#image_container img");  // 만들어진 자식
      document.querySelector("div#image_container").removeChild(child); // 만들어진 이미지 삭제
      document.querySelector("#image").value = null;      // input type:file 에 들어간 이미지 비워주기
      // 테스트
      document.querySelector(".MainImage_wrapper").style.display = "flex";  // 없어진 이미지 넣기 버튼 다시 보여주기
  });


  // 별점
const rating = document.querySelector('.rating');
const score = document.querySelector('.score');

// rating.addEventListener('change', function(e) {
//   score.textContent = `${e.target.value}점`;
// });

// 리뷰 작성 확인 및 제출
$(".registButton_button").on('click', function(){
  let flag1 = true;
  let flag2 = true;
  let flag3 = true;
  console.log("들어옴");
  var $title = $("#input-title");
  var $content = $("#input-content");
  var $star = $("input[name=rating]:checked");

  console.log("star : " + $star.val());
  if($title.val().length < 1){
    // alert("제목을 작성해주세요.");
    $(".modal-wrapper").css('display', 'block');
    $(".modal-message").text("제목을 작성해주세요.");
    $("#input-title").focus();
    flag1 = false;
  } else if($content.val().length < 1){
    // alert("내용을 작성해주세요.");
    $(".modal-wrapper").css('display', 'block');
    $(".modal-message").text("내용을 작성해주세요.");
    $("#input-content").focus();
    flag2 = false;
  } else if($star.val() < 1 || $star.val() == null){
    // alert("만족도를 체크해주세요.");
    $(".modal-wrapper").css('display', 'block');
    $(".modal-message").text("만족도를 체크해주세요.");
    $star.focus();
    flag3 = false;
  }
  
  if(flag1 && flag2 && flag3){
    // alert("완료");
    document.reviewForm.submit();
  }

})

$(".modal-close-btn").on("click", function(){
  $(".modal-wrapper").css('display', 'none');
})



/* 모달 창 띄우기, 끄기 */

