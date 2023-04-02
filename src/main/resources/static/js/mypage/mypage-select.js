/* 모달 */
const btnOpenPopup = document.querySelector('#modal-back');
const modal = document.querySelector('#modal-background');
const closeBtn = document.querySelector(".modal-close-image-button")
const closegrayBtn = document.querySelector("#btn_modal_close")

/* 모달 열기 */
btnOpenPopup.addEventListener('click', () => {
  modal.style.display = 'block';
  modal.style.zIndex = "1000";
  modal.style.opcity = "1";
});

/* 모달 닫기 */
closeBtn.addEventListener("click", e => {
  modal.style.display = "none"
});

closegrayBtn.addEventListener("click", e => {
  modal.style.display = "none"
});

modal.addEventListener("click", e => {
  const evTarget = e.target
  if(evTarget.classList.contains("modal")) {
      modal.style.display = "none"
  }
});

/* button으로 파일 첨부 */
function upload_image() {
  let myInput = document.querySelector(".img-temp-file");
  myInput.click();
}

/* button으로 파일 삭제 */
$(document).ready(function() {
  $('#remove-button').click(function() {
  $('#fileupload').empty();
  $('#modal_user_img').attr('src',"../../static/images/mypage/main-logo.png");
}) 
});


/*사진 첨부*/
function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('modal_user_img').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('modal_user_img').src = "";
  }
}


/* 수정 버튼 눌렀을 때 프로필 변경 */

$(document).ready(function() {
  $('#modifybutton').click(function() { 
    $('.essential').show(); /* 필수 * 표시 */
    $('.control-wrapper').show(); /* 수정 항목 */
    $('.account-setting-content').hide(); /* 원 화면 정보 */
    $('.blank').show(); /* 공백 */
    $('#base_submit_btn').show(); /* 수정 완료 버튼 */
    $('#modifybutton').hide(); /* 수정 버튼 숨김 */
  })
});



// 저장하기 버튼 필수 입력 공백 확인 
const formSubmit = document.querySelector('#base_submit_btn');
const nameInput = document.querySelector('#full_name'); //이름
const phoneMidldle = document.querySelector('#cell_phone_number_middle'); //휴대폰 중간자리
const phoneEnd = document.querySelector('#cell_phone_number_end'); //휴대폰 뒷자리
const email = document.querySelector('#email'); //이메일
const drivePeriod = document.querySelector('#drive-period'); //기간
const namemsg = document.querySelector('.namemsg'); //이름 에러
const phonemsg = document.querySelector('.phonemsg'); //휴대폰 에러
const emailmsg = document.querySelector('.emailmsg'); //이메일 에러
const periodmsg = document.querySelector('.periodmsg'); //기간 에러

const check = false;


formSubmit.addEventListener("click", function(e){
    var contentInputValue = document.querySelector("#full_name").value;
    if(contentInputValue.length < 1){
		nameInput.style.borderColor = "red";
    	namemsg.style.display = "block";
        namemsg.innerHTML = "이 항목을 채워주십시오.";
        namemsg.style.color = "red";
        e.preventDefault();
    }
});

/* 휴대폰 */
formSubmit.addEventListener("click", function(e){
    var contentInputValue = document.querySelector("#cell_phone_number_middle").value;
    if(contentInputValue.length < 1){
		phoneMidldle.style.borderColor = "red";
    	phonemsg.style.display = "block";
        phonemsg.innerHTML = "이 항목을 채워주십시오.";
        phonemsg.style.color = "red";
        e.preventDefault();
    }
});

formSubmit.addEventListener("click", function(e){
    var contentInputValue = document.querySelector("#cell_phone_number_end").value;
    if(contentInputValue.length < 1){
		phoneEnd.style.borderColor = "red";
    	phonemsg.style.display = "block";
        phonemsg.innerHTML = "이 항목을 채워주십시오.";
        phonemsg.style.color = "red";
        e.preventDefault();
    }
});

/* 이메일  */
formSubmit.addEventListener("click", function(e){
    var contentInputValue = document.querySelector("#email").value;
    var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if(contentInputValue.length < 1){
		email.style.borderColor = "red";
    	emailmsg.style.display = "block";
        emailmsg.innerHTML = "이 항목을 채워주십시오.";
        emailmsg.style.color = "red";
        e.preventDefault();
    }else if((emailPattern.test(contentInputValue))){
        emailmsg.style.display = "none"; 
    } else {
        e.preventDefault();
        emailmsg.style.display = "block";
        emailmsg.innerHTML = "올바른 이메일 주소를 입력해주세요.";
        emailmsg.style.color = "red";
    }
});

/* 운전 경력 */
formSubmit.addEventListener("click", function(e){
    var contentInputValue = document.querySelector("#drive-period").value;
    if(contentInputValue.length < 1){
		drivePeriod.style.borderColor = "red";
    	periodmsg.style.display = "block";
        periodmsg.innerHTML = "이 항목을 채워주십시오.";
        periodmsg.style.color = "red";
        e.preventDefault();
    }
});


// 공백 -> 문자열 입력 시 인풋 속성 변경
/* 이름 */
nameInput.addEventListener("blur", function(){
    const nameInputValue = nameInput.value;
    if(nameInputValue.length > 1){
		nameInput.style.borderColor = "#dadada";
    	namemsg.style.display = "none";
    }
});

/* 휴대폰 */
phoneMidldle.addEventListener("blur", function(){
    const phoneMiddleValue = phoneMidldle.value;
    if(phoneMiddleValue.length > 1){
		phoneMidldle.style.borderColor = "#dadada";
    	phonemsg.style.display = "none";
    }
});

phoneEnd.addEventListener("blur", function(){
    const phoneEndValue = phoneEnd.value;
    if(phoneEndValue.length > 1){
		phoneEnd.style.borderColor = "#dadada";
    	phonemsg.style.display = "none";
    }
});

/* 이메일 */
email.addEventListener("blur", function(){
    const emailValue = email.value;
    if(emailValue.length > 1){
		email.style.borderColor = "#dadada";
    	emailmsg.style.display = "none";
    }
});

/* 경력 */
drivePeriod.addEventListener("blur", function(){
    const drivePeriodValue = drivePeriod.value;
    if(drivePeriodValue.length > 1){
		drivePeriod.style.borderColor = "#dadada";
    	periodmsg.style.display = "none";
    }
});

/* 프로필 기본사진으로 변경 */
globalThis.arrayFile2 = new Array();
globalThis.j = 0;
const dataTransfer = new DataTransfer();
$("input[id='remove_image_file']").on("change", function() {
    const $files2 = $("input[id=remove_image_file]")[0].files[0];
    console.log($files2)
//    파일 객체에 접근함
    let formData = new FormData();
    globalThis.arrayFile2.push($files2);
    // 파일 Array의 file들을 하나씩 담아줌
    console.log(globalThis.arrayFile2)
    formData.append("file", $files2)
    $.ajax({
        url: "/board-files/uploadM",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuid) {
            globalThis.uuid = uuid;
            console.log(globalThis.uuid)
            $("span.cover-txt").hide();
            $("div.cover-img").hide();
            $(".img").append(`<img src="/board-files/display?fileName=${toStringByFormatting(new Date())}/m_${uuid}_${$files2.name}" class="mainPhoto">`);
            $(".img img").css("width", "50%");
            $(".img img").css("height", "100%");


            $("input[id='cover-file']")[0].files = dataTransfer.files;
            let text2 = "";
            text2 =
                `
                    <input type="hidden" name="fileMainName" value="${$files2.name}">
                    <input type="hidden" name="fileMainUuid" value="${globalThis.uuid}">
                    <input type="hidden" name="fileMainPath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name="fileMainSize" value="${$files2.size}">
                    `
            $("form[name='board']").append(text2);

            let boardFileVO1 = new Object();
            boardFileVO1.fileOriginalName = $files2.name;
            boardFileVO1.filePath = toStringByFormatting(new Date());
            boardFileVO1.fileSize = $files2.size;
            boardFileVO1.fileUuid = globalThis.uuid;

            files.push(boardFileVO1);

            console.log(boardFileVO1.fileSize)
            console.log(boardFileVO1.filePath)
            console.log(boardFileVO1.fileUuid)
            console.log(boardFileVO1.fileOriginalName)
        }
    });
});

/* 프로필 사진 변경 */
globalThis.arrayFile2 = new Array();
globalThis.j = 0;
const dataTransfer = new DataTransfer();
$("input[id='fileupload']").on("change", function() {
    const $files2 = $("input[id=fileupload]")[0].files[0];
    console.log($files2)
//    파일 객체에 접근함
    let formData = new FormData();
    globalThis.arrayFile2.push($files2);
    // 파일 Array의 file들을 하나씩 담아줌
    console.log(globalThis.arrayFile2)
    formData.append("file", $files2)
    $.ajax({
        url: "/members/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuid) {
            globalThis.uuid = uuid;
            console.log(globalThis.uuid)

            $("input[id='profile']")[0].files = dataTransfer.files;
            let text2 = "";
            text2 =
                `
                    <input type="hidden" name="memberProfileOriginalName" value="${$files2.name}">
                    <input type="hidden" name="memberProfileUuid" value="${globalThis.uuid}">
                    <input type="hidden" name="memberProfilePath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name="memberProfileSize" value="${$files2.size}">
                    `
            $("form[name='write-form']").append(text2);
            console.log(text2);
        }
    });
});