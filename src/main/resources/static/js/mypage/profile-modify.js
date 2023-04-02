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

/*모달 창 띄우기*/
function showModal() {
    $('.modal').css('visibility', 'visible');
    document.body.style.overflow = 'hidden';    // 모달창 띄웠을 때 스크롤 막기
}

function closeModal() {
    $('.modal').css('visibility', 'hidden');
    document.body.style.removeProperty('overflow'); // 모달창 끄면 스크롤 원상복구
}

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


const btnOpenPopup = document.querySelector('#modal-back');
const modal = document.querySelector('#modal-background');
const closeBtn = modal.querySelector(".modal-close-image-button")
const closegrayBtn = modal.querySelector("#btn_modal_close")

btnOpenPopup.addEventListener('click', () => {
  alert('들어옴');
  modal.style.display = 'block';
  modal.style.zIndex = "1000";
  modal.style.opcity = "1";
});

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

function upload_image() {
  alert('들어옴');
  let myInput = document.querySelector("#img-temp-file");
  myInput.click();
}






/* // focus 됐을 때 테두리 색 변하게 하기
nameInput.addEventListener("focus", function(){
    nameWrapper.style.border = "solid 1px #2099bb";
});

phoneMidldle.addEventListener("blur", function(){
    nameWrapper.style.border = "solid 1px #2099bb";
});

phoneEnd.addEventListener("focus", function(){
    yearWrapper.style.border = "solid 1px #2099bb";
})

email.addEventListener("blur", function(){
    yearWrapper.style.border = "solid 1px #2099bb";
})

drivePeriod.addEventListener("focus", function(){
    monthWrapper.style.border = "solid 1px #2099bb";
})
 */