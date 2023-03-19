// 전체 선택 체크박스
const checkBoxAll = document.getElementsByName("checkbox-all");
const checkBox = document.querySelectorAll('input[name = "checkbox"]');
const checkBoxChecked = document.querySelectorAll(
  'input[name = "checkbox"]:checked'
);

const selectAll = function () {
  if (checkBoxAll[0].checked) {
    checkBox.forEach((e) => (e.checked = true));
  }
  if (!checkBoxAll[0].checked) {
    checkBox.forEach((e) => (e.checked = false));
  }
};

const checkSelectAll = function () {
  if (checkBox.length === checkBoxChecked.length) {
    checkBoxAll[0].checked = true;
  } else if (checkBox.length != checkBoxChecked.length) {
    checkBoxAll[0].checked = false;
  }
  console.log(checkBox.length);
};

checkBoxAll[0].addEventListener("click", selectAll);
checkBox.forEach((e) => {
  e.addEventListener("click", checkSelectAll);
});

console.log(checkBoxChecked);