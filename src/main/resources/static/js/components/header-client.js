// dropdown
const userIcon = document.querySelector("#user-icon");
const dropdown = document.querySelector("#dropdown");

userIcon.addEventListener("click", e => {
    e.preventDefault();
    $(dropdown).slideToggle();
});





