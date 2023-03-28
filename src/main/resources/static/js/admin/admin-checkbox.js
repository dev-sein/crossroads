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