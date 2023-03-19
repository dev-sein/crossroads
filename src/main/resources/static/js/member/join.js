$("#file").on('change', function(){
    var fileName = $("#file").val().split('/').pop().split('\\').pop();
    $("#upload-name").val(fileName);
});