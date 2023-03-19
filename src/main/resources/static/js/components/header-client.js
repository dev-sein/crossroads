// dropdown
const userIcon = document.querySelector("#user-icon");
const dropdown = document.querySelector("#dropdown");

userIcon.addEventListener("click", e => {
if(dropdown.style.display == 'none'){
    alert('들어옴');
    dropdown.style.display = 'block';
}else{
    dropdown.style.display = 'none';
}
});





