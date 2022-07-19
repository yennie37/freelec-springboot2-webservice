/* 141p.
   ※ var main = {...} 으로 선언한 이유
     : 브라우저의 scope는 공용공간이므로 다른 js 가 추가될시 나중에 로딩된 js의 init, save 가
       먼저 로딩된 js의 function을 덮어쓰게 된다. 해당 문제를 피하기위해 index.js 만의 유효범위(scope)를 만들어 사용.
*/
var main = {
    init : function() {
        var _this = this;

        $('#btn-save').on('click', function() {
            console.log("[저장버튼] 클릭");
            _this.save();
        });

        $('#btn-update').on('click', function() {
            console.log("[수정] 클릭");
            _this.update();
        });

        $('#btn-delete').on('click', function() {
            console.log("[삭제] 클릭");
            _this.delete();
        });
    },

    save : function() {
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)

        }).done(function() {
            console.log("[저장버튼] 완료");
            alert("글이 등록되었습니다 :>");
            window.location.href="/";   /* 성공시 메인으로 이동 */

        }).fail(function(error) {
        console.log("[저장버튼] 실패");
            alert(JSON.stringify(error));
        });
    },

    /* 153p. 수정 펑션 */
       update : function() {
           var data = {
               title : $('#title').val(),
               content : $('#content').val()
           };

          var id = $('#id').val();

           $.ajax({
               /* PostsApiController에 있는 API에서 이미 @PutMapping으로 선언했으므로, REST 규약에 맞추어 PUT 사용.
                   => HTTP Method : 생성 POST, 읽기 GET, 수정 PUT, 삭제 DELETE
               */
               type : 'PUT',
               url : '/api/v1/posts/' + id,
               dataType : 'json',
               contentType : 'application/json; charset=utf-8',
               data : JSON.stringify(data)

           }).done(function() {
               console.log("[수정] 완료");
               alert("글이 수정되었습니다 :D");
               window.location.href="/";   /* 성공시 메인으로 이동 */

           }).fail(function(error) {
               console.log("[수정] 실패");
               alert(JSON.stringify(error));
           });
       },

        /* 158p. 삭제 펑션 */
        delete : function() {
           var id = $('#id').val();

            $.ajax({
                type : 'DELETE',
                url : '/api/v1/posts/' + id,
                dataType : 'json',
                contentType : 'application/json; charset=utf-8'

            }).done(function() {
                console.log("[삭제] 완료");
                alert("글이 삭제되었습니다 :O");
                window.location.href="/";   /* 성공시 메인으로 이동 */

            }).fail(function(error) {
                console.log("[삭제] 실패");
                alert(JSON.stringify(error));
            });
        }
};

main.init();





