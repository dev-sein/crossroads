/* delete 버튼 클릭 시 ajax실행*/
$('.modal_test').on('click', function() {
    console.log("delete버튼 들어옴");
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=checkbox]:checked').each(function(i, e){
        console.log(e.innerText);
        var checkedId = e.dataset.id * 1;
        checkedIds.push(checkedId);
    });

    console.log(checkedIds);
        // ?page=${$page}
    $.ajax({
        url: `/admin/boards/delete`,
        type: "delete",
        data: {
            "checkedIds": checkedIds
        },
        success: function(){
            // location.href= result;
            load();
            $('#title__checkbox').prop("checked", false);
        }

    });
});
