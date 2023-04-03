/*개별 체크 박스 선택 시*/
function isChecked(checkbox) {
    var $checkAllBox = $('#title__checkbox');
    var $checkBoxes = $('.content__checkbox');
    var j = 0;
    checkbox.checked ? checkbox.checked = true : checkbox.checked = false;

    $checkBoxes.each(function(i, checkbox) {
        if($(checkbox).is(":checked")){
          j++;
        }
    })
    console.log("j는? " + j);
    j === $checkBoxes.length ? $checkAllBox.prop("checked", true) : $checkAllBox.prop("checked", false);

}

/*전체 체크 박스 선택 시*/
function isAllChecked() {

    var $checkAllBox = $('#title__checkbox');
    var $checkBoxes = $('.content__checkbox');

    if($checkAllBox.is(":checked")){
        $(this).prop("checked", true);
        $checkBoxes.each(function(i, checkbox) {
            $(checkbox).prop("checked", true);
        })
    } else {
        $(this).prop("checked", false);
        $checkBoxes.each(function(i, checkbox) {
            $(checkbox).prop("checked", false);
        })
    }
}




// // 전체 선택 체크박스
// const checkBoxAll = document.getElementsByName("checkbox-all");
// const checkBox = document.querySelectorAll('input[name = "checkbox"]');
// const checkBoxChecked = document.querySelectorAll(
//     'input[name = "checkbox"]:checked'
// );
//
//
// function makeName(e) {
//   console.log(e);
// }
//
//
//
//
// const selectAll = function () {
//   if (checkBoxAll[0].checked) {
//     checkBox.forEach((e) => {
//       e.checked = true;
//       // console.log(e.target);
//       // checkedIds.push(e.target.value);
//     });
//   }
//   if (!checkBoxAll[0].checked) {
//     checkBox.forEach((e) => (e.checked = false));
//   }
// };
//
// /* 개수 모두 전체 선택인지 */
// const checkSelectAll = function () {
//
//   var checkedBox = document.querySelectorAll(
//       'input[name = "checkbox"]:checked'
//   );
//   if (checkBox.length != checkedBox.length) {
//     checkBoxAll[0].checked = false;
//   } else {
//     checkBoxAll[0].checked = true;
//   }
// };
//
// checkBoxAll[0].addEventListener("click", selectAll);
//
// checkBox.forEach((e) => {
//   e.addEventListener("click", checkSelectAll);
// });