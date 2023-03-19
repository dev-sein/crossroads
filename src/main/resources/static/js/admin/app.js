let app = {
  service: (function () {
    function load(datas, callback) {
      datas = JSON.parse(datas);
      var text = '';

      datas.forEach((data, i) => {
        text += callback(data, i);
      });

      $('#scroll').html(text);
    }

    function ajax(url, data, callback) {
      $.ajax({
        url: url, //request 보낼 서버의 경로
        type: 'post', // 메소드(get, post, put 등)
        data: data,
        success: function (result) {
          callback(result, loadData);
        },
        error: function (a, b, c) {
          console.log(a, b, c);
        },
      });
    }

    return { load: load, ajax: ajax };
  })(),
};
