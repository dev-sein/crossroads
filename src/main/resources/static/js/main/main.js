var btns = document.getElementsByClassName("process-btn");
var lines = document.getElementsByClassName("process-line");

function buttonClick(e) {
  console.log(e.target);

  console.log(e.target.classList);

  if (e.target.classList[3] === "active") {
    e.target.classList.remove("active");
  } else {
    for (var i = 0; i < btns.length; i++) {
      btns[i].classList.remove("active");
      e.target.classList.add("history");
    }
    e.target.classList.add("active");
  }
}

function init() {
  for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", buttonClick);
  }
}

init();


