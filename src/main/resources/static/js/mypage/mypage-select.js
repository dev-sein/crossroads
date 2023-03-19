
const btnOpenPopup = document.querySelector('#modal-back');
const modal = document.querySelector('#modal-background');

console.log(modal);

btnOpenPopup.addEventListener('click', () => {
  alert('들어옴');
  modal.style.display = 'block';
  modal.style.zIndex = "1000";
  modal.style.opcity = "1";
});





/* 
var $profile_modal;
var cropper;
var blob_file;

$(document).ready(function () {
  $profile_modal = $('#profile_image_edit_modal');
  $(document).click(function (e) {
    if ($(e.target).hasClass('in') && $('body').hasClass('modal-open')) {
      close_modal();
    }
  });
  $('#btn_profile_img_modal_close').on('click', function(){
    close_modal();
  });
});

function show_img_edit_modal() {
  $('.user-profile-img').removeClass('active');
  $('#modal_user_img').attr('src', $('.user-img').attr('src'));
  $('#modal_user_img').addClass('active');
  $profile_modal.modal('show');
}

function upload_image() {
  $('.img-temp-file').click();
}

function remove_image() {
  $('.user-profile-img').removeClass('active');
  $('.user-img-default').addClass('active');
  $('#profile_remove_image_file').prop('checked', true);
  $('.img-temp-file').val('').clone(true);
  blob_file = '';
}

function isValidImage(file) {
  const acceptFileTypes = ['apng', 'avif', 'gif', 'jpg', 'jpeg', 'jfif', 'pjpeg', 'pjp', 'png', 'webp',];
  const fileType = file.name.split('.').pop().toLowerCase();
  return acceptFileTypes.indexOf(fileType) !== -1;
}

function close_modal() {
  blob_file = '';
  $profile_modal.modal('hide');
}

function submit_form(url){
  const $targetForm = $('#profile_image_edit_form');
  const formData = new FormData($targetForm[0]);
  if (blob_file) {
    formData.append('cropped_image', blob_file);
  }
  $.ajax({
    method: 'POST',
    url: url,
    data: formData,
    contentType: false,
    processData: false,
    success: function (data) {
      close_modal();
      $('.user-img').attr('src', data['profile_image_url']);
      $('#modal_user_img').attr('src', data['profile_image_url']);
    },
    error: function () {},
  });
}

function start_cropper() {
  return new Promise(function (resolve, reject) {
    const image = document.getElementsByClassName('cropper-img')[0];
    if (cropper) {
      cropper.destroy();
    }
    cropper = new Cropper(image, {
      minCropBoxWidth: 300,
      minCropBoxHeight: 300,
      viewMode: 1,
      aspectRatio: 1,
    });
    resolve();
  });
}

$(document).on('change', '.img-temp-file', function () {
  const file = this.files[0];
  const $cropper_img = $('.cropper-img');
  const $img = $('.temp-user-img');
  const MAX_WIDTH = 300;
  const MAX_HEIGHT = 300;
  if (file) {
    const isAcceptFileType = isValidImage(file);
    if (isAcceptFileType) {
      $('.user-profile-img').removeClass('active');
      $('.loading-circle').addClass('active');

      const reader = new FileReader();
      reader.onload = function(e){
        $cropper_img.attr('src', e.target.result);
      };
      reader.readAsDataURL(file);
      setTimeout(function () {
        start_cropper().then(function () {
          setTimeout(function(){
            let crop_img = cropper.getCroppedCanvas({
            width: MAX_WIDTH,
            height: MAX_HEIGHT,
          });
            crop_img.toBlob(function(blob){
              blob_file = blob;
              const reader = new FileReader();
              reader.onload = function() {
                $img.attr('src', reader.result);
              };
              reader.readAsDataURL(blob);
              $img.addClass('active');
              $('.loading-circle').removeClass('active');
            });
          },200);
        });
      }, 100);
    } else {
      alert('이미지 파일만 등록 가능합니다.');
    }
  }
}); */