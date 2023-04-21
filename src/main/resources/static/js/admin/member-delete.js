/* delete 버튼 클릭 시 ajax실행*/
$('.modal_test').on('click', function() {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=checkbox]:checked').each(function(i, e){
        console.log(e.innerText);
        var checkedId = e.dataset.id * 1;
        checkedIds.push(checkedId);
    });

    console.log(checkedIds);

    $.ajax({
        url: "/admin/members/delete",
        type: "delete",
        data: {
            "checkedIds": checkedIds
        },
        success: function(){
            load();
            $('#title__checkbox').prop("checked", false);
        }

    });
});