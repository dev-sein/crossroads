/*사진 첨부*/
function setThumbnail(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
      var img = document.createElement("img");
      if(document.querySelector("#image_container").childElementCount == 0 ){
        img.setAttribute("src", event.target.result);
        img.setAttribute("id", "review-image");
        document.querySelector("div#image_container").appendChild(img);
        // document.querySelector("#button-label").style.display = "none";
        // document.querySelector("#photosubmit").style.display = "none";
        // document.querySelector(".ImageFileButton_input").style.display = "none";
      } else {
        var child = document.querySelector("div#image_container img");
        document.querySelector("div#image_container").removeChild(child);
        img.setAttribute("src", event.target.result);
        img.setAttribute("id", "review-image");
        document.querySelector("div#image_container").appendChild(img);
        // document.querySelector("#button-label").style.display = "inline-block !important";
        // document.querySelector("#photosubmit").style.display = "block";
        // document.querySelector(".ImageFileButton_input").style.display = "block";
        // alert("이미지는 1개만 등록 가능합니다.");
        // console.log(document.querySelector("#image").value);
      }
    };

    reader.readAsDataURL(event.target.files[0]);
  }

  document.querySelector("#image_container").addEventListener("click", function(){
      var child = document.querySelector("div#image_container img");  // 만들어진 자식
      document.querySelector("div#image_container").removeChild(child); // 만들어진 이미지 삭제
      document.querySelector("#image").value = null;      // input type:file 에 들어간 이미지 비워주기
      // document.querySelector("#button-label").style.display = "inline-block !important";
      // document.querySelector("#photosubmit").style.display = "block";
      // document.querySelector(".ImageFileButton_input").style.display = "block";

  });