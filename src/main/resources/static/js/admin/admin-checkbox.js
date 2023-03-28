// 전체 선택 체크박스
const checkBoxAll = document.getElementsByName("checkbox-all");
const checkBox = document.querySelectorAll('input[name = "checkbox"]');
const checkBoxChecked = document.querySelectorAll(
  'input[name = "checkbox"]:checked'
);




const selectAll = function () {
  if (checkBoxAll[0].checked) {
    checkBox.forEach((e) => {
      e.checked = true;
      // console.log(e.target);
      // checkedIds.push(e.target.value);
    });
  }
  if (!checkBoxAll[0].checked) {
    checkBox.forEach((e) => (e.checked = false));
  }
};

/* 개수 모두 전체 선택인지 */
const checkSelectAll = function () {

  var checkedBox = document.querySelectorAll(
      'input[name = "checkbox"]:checked'
  );
  if (checkBox.length != checkedBox.length) {
    checkBoxAll[0].checked = false;
  } else {
    checkBoxAll[0].checked = true;
  }
};

checkBoxAll[0].addEventListener("click", selectAll);

checkBox.forEach((e) => {
  e.addEventListener("click", checkSelectAll);
});


/* delete 버튼 클릭 시 form태그 전송*/
$('.modal_test').on('click', function() {
  var checkedIds = new Array();
  // 체크 박스 체크된 값
  // $('input:checkbox[name=checkbox]:checked').next('.content__id').each(function(){
  $('input:checkbox[name=checkbox]:checked').next().find('.content__id').each(function(){
    // console.log(this);
    // console.log(this.innerText);
    // console.log(typeof this.innerText);
    checkedIds.push(this.innerText);
  });

  // console.log(checkedIds);
  $('#check-hidden').val(checkedIds);
  // console.log($('#check-hidden').val());
  // console.log(typeof $('#check-hidden').val());
  document.checkedForm.submit();
});


