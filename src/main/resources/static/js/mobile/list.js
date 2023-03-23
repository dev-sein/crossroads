function acceptOrReject(object) {
    var check = object.innerText == "거절" ? true : false;

    if (check) {
        object.innerText = '수락';
        object.style.backgroundColor = "#2099bb";
        object.style.borderColor = '#2099bb';
    } else {
        object.innerText = '거절';
        object.style.backgroundColor = "#e52929";
        object.style.borderColor = '#e52929';
    }
}