
var COMM = {
	ajax: function(ajaxConfig, errorCallback) {
        var defaultConf = {
            type: 'POST',
            dataType: 'json',
			async: true,
            // contentType: 'application/json;charset=UTF-8',
            error : function(jqXHR, textStatus){ 
                // 에러 공통 메세지를 띄우지 않으려면 error자체를 ajaxConfig에 재지정
                // 메세지는 띄우고 추가 작업이 필요하면 errorCallback 전달
                console.log(jqXHR.status, jqXHR );
                
                alert("요청 실행 중 문제가 발생했습니다. 관리자에게 문의하세요.");
                if(typeof errorCallback != "undefined" && errorCallback != null){
                    errorCallback(jqXHR);
                }
            }
        };
        var mergeConf = $.extend(true, {}, defaultConf, ajaxConfig);
        $.ajax(mergeConf);
    }
    ,putAjax: function(ajaxConfig, errorCallback) {
        var defaultConf = {
            type: 'PUT',
            dataType: 'json',
			async: true,
            error : function(jqXHR, textStatus){
                console.log(jqXHR.status, jqXHR );
                
                alert("요청 실행 중 문제가 발생했습니다. 관리자에게 문의하세요.");
                if(typeof errorCallback != "undefined" && errorCallback != null){
                    errorCallback(jqXHR);
                }
            }
        };
        var mergeConf = $.extend(true, {}, defaultConf, ajaxConfig);
        $.ajax(mergeConf);
    }
    
}
