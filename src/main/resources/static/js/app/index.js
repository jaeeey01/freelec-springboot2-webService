var main = {    // 나중에 로딩된 js init,save가 먼저 로딩된 js의 functiond을 덮어쓰기 때문에 구분하기위해서 index.js만의 유효범위를 만듦
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("글이 수정되었습니다");
            window.location.href = '/';
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    delete: function (){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url:'/api/v1/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=UTF-8',
        }).done(function (){
            alert("삭제되었습니다");
            window.location.href='/';
        }).fail(function (err){
            alert(JSON.stringify(err));
        });
    }
};
main.init();