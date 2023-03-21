const $questions = $(".question");

$questions.on('click', function(){

    if($(this).next().css("display") != 'none'){
        $(this).attr("class", 'question-changed');
        $(this).next().slideUp(500);
    } else {
        $(this).attr("class", 'question');
        $(this).next().slideDown(500);
    }

})