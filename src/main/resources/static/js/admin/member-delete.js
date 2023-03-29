/* delete 버튼 클릭 시 ajax실행*/
$('.modal_test').on('click', function() {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=checkbox]:checked').next().find('.user__id').each(function(){
        console.log(this.innerText);
        checkedIds.push(this.innerText);
    });

    console.log(checkedIds);

    $.ajax({
        url: "/admins/member/delete",
        type: "delete",
        data: {"checkedIds": checkedIds},
        success: function(){
            location.reload();
        }

    });
});