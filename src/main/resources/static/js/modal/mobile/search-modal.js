$.datepicker.setDefaults({
    dateFormat: 'yy년 m월 d일',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
});

$('#datepicker').datepicker();

$("#datepicker").change(function(){
    $('.date-value').text($('#datepicker').val());
});

var area0 = ["시/도","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"];
var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
var area3 = ["대덕구","동구","서구","유성구","중구"];
var area4 = ["광산구","남구","동구","북구","서구"];
var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
var area6 = ["남구","동구","북구","중구","울주군"];
var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
var area11 = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
var area15 = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
var area16 = ["서귀포시","제주시","남제주군","북제주군"];
// 시/도 선택 박스 초기화
$("select[name^=sido]").each(function() {
    $selsido = $(this);
    $.each(eval(area0), function() {
        $selsido.append("<option value='"+this+"'>"+this+"</option>");
    });
    $selsido.next().append("<option value=''>구/군</option>");
});

// 시/도 선택시 구/군 설정
$("select[name^=sido]").change(function() {
    var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
    var $gugun = $(this).next(); // 선택영역 군구 객체
    $("option",$gugun).remove(); // 구군 초기화

    if(area == "area0")
        $gugun.append("<option value=''>구/군</option>");
    else {
        $.each(eval(area), function() {
            $gugun.append("<option value='"+this+"'>"+this+"</option>");
        });
    }
});

const $searchButton = $(".search-button");  // 검색 버튼
const $closeButton = $(".modal-close-btn"); // 검색창 닫기 버튼
const $searchHeader = $(".serach-header");  // 검색 헤더

const $date = $("#datepicker");             // 날짜 검색 input
const $location = $("#locationpicker");     // 지역 검색 input
const $modalSearchButton = $(".modal-search-btn");  // 모달창 안에 검색 버튼

$searchButton.on('click', function(){
    $('.modal-search-modal').show();
    document.body.style.overflow = 'hidden';    // 모달창 띄웠을 때 스크롤 막기
    // $('.modal-search-modal').css('display', 'flex');
});

$searchHeader.on('click', function(){
    $('.modal-search-modal').show();
    document.body.style.overflow = 'hidden';    // 모달창 띄웠을 때 스크롤 막기
    // $('.modal-search-modal').css('display', 'flex');
});

$closeButton.on('click', function(){
    $('.modal-search-modal').hide();
    document.body.style.removeProperty('overflow'); // 모달창 끄면 스크롤 원상복구
});

$modalSearchButton.on('click', function () {
    // var regex = /[^0-9]/g;				// 숫자가 아닌 문자열을 선택하는 정규식
    // $date.val($date.val().replace(regex, ""));	// 원래 문자열에서 숫자가 아닌 모든 문자열을 빈 문자로 변경
    // console.log($date.val());
    // 날짜에서 2023월 3월 1일이면 202331 로 나오는데 월이나 일이 2글자가 아닌 1글자이면 앞에 0을 붙여주는 정규식
    var changedDate = $date.val().replace(/(\d{4})년 (\d{1,2})월 (\d{1,2})일/, function(match, p1, p2, p3) {
        if (p2.length === 1) {
            p2 = "0" + p2;
        }
        if (p3.length === 1) {
            p3 = "0" + p3;
        }
        return p1 + p2 + p3;
    });
    $date.val(changedDate);
    // console.log($date.val());

    console.log($("#sido1").val());     // 시도
    console.log($("#gugun1").val());    // 구군
    $location.val($("#sido1").val() + " " + $("#gugun1").val());    // 지역 값 = 시도 + 구군
    if($("#sido1").val() == "시/도"){     // 지역의 들어온 값이 없을 경우
        console.log("들어옴");
        $("#sido").val('');
        $location.val('');
    }
    // console.log($location.val());

    document.searchForm.submit();
})


