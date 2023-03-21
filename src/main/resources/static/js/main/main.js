// var btns = document.getElementsByClassName("process-btn");
// var lines = document.getElementsByClassName("process-line");

// function buttonClick(e) {
//   console.log(e.target);

//   console.log(e.target.classList);

//   if (e.target.classList[3] === "active") {
//     e.target.classList.remove("active");
//   } else {
//     for (var i = 0; i < btns.length; i++) {
//       btns[i].classList.remove("active");
//       e.target.classList.add("history");
//     }
//     e.target.classList.add("active");
//   }
// }

// function init() {
//   for (var i = 0; i < btns.length; i++) {
//     btns[i].addEventListener("click", buttonClick);
//   }
// }

// init();


/* ==========================연수신청 진행사항====================== */
HTMLCollection.prototype.forEach = Array.prototype.forEach;
const content = document.querySelector("div.content-list");
const contentDiv = document.querySelectorAll("div.content-list div.process-guide-content");
const $next = $(".next-page");
const $prev = $(".process-paginate");
const buttons = document.querySelectorAll("div.process-btn-group div.process-btn");
let count = 1;
let temp = buttons[0];

// translate 1129 => margin 49까지 포함
HTMLCollection.prototype.forEach = Array.prototype.forEach;
buttons.forEach(button => {
    button.addEventListener("click", () => {
        count = parseInt(button.innerHTML);
        changeButtonStyle();
        content.style.transition = "transform 0.7s";//에니메이션 효과 속도
        content.style.transform = `translate(${-1129 * (count - 1)}px)`;
    });
});

function changeButtonStyle() {
    if(temp){
        console.log("temp: " + temp);
        temp.style.background = "#f7fafc";
        temp.style.borderColor = "#e0e0e0";
        temp.style.color = "black";
    }
    console.log("button: " + count);
    buttons[count - 1].style.background = "#2099bb";
    buttons[count - 1].style.borderColor = "#2099bb";
    buttons[count - 1].style.color = "#fff";
    temp = buttons[count - 1];
}

$next.on('click', function(){
  console.log("next: " + count);
  content.style.transition = "transform 0.7s";
  content.style.transform = `translate(${-1129 * count}px)`;
  count++;
  changeButtonStyle();
})

$prev.on('click', function(){
  console.log("prev :" + count)
  content.style.transition = "transform 0.7s";
  content.style.transform = `translate(${-1129 * (count = count - 2)}px)`;
  count++;
  changeButtonStyle();
})
