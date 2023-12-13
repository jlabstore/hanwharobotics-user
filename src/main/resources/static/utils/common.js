
var COMM = {
	ajax: function(ajaxConfig, errorCallback) {
        var lang = document.cookie.replace(/(?:(?:^|.*;\s*)lang\s*=\s*([^;]*).*$)|^.*$/, '$1');
        var params = {
            lang: lang !== undefined && lang !== null && lang !== '' ? lang.trim() : 'ko'
        }
        ajaxConfig.data =  $.extend(true, {}, ajaxConfig.data, params);

        var defaultConf = {
            type: 'POST',
            dataType: 'json',
			async: true,
            // contentType: 'application/json;charset=UTF-8',
            error : function(jqXHR, textStatus){
                // 에러 공통 메세지를 띄우지 않으려면 error자체를 ajaxConfig에 재지정
                // 에러 공통 메세지는 띄우고 추가 작업이 필요하면 errorCallback 전달
                console.log(jqXHR.status, jqXHR );

                alert("[[#{alert.error}]]");
                if(typeof errorCallback != "undefined" && errorCallback != null){
                    errorCallback(jqXHR);
                }
            }
        };
        var mergeConf = $.extend(true, {}, defaultConf, ajaxConfig);
        $.ajax(mergeConf);
    }
     // 페이징 리스트 AJAX
     ,ajaxPagingList : function(ajaxConfig, dataCallback){
        var params = {};

        //Search Data
        // var keyword = $('#keyword').val(); //검색어
        // var type = $('#type').val();       //구분
        // if(keyword != undefined) params['keyword'] = keyword;
        // if(type != undefined) params['type'] = type;

        //Paging Data
        var size = $('#size').val();
        var page = $('#page').val();
        params['size'] = size != undefined && size > 0 ? size : 10;
        params['page'] = page != undefined && page > 0 ? page : 1;

        //검색 및 페이징 데이터 추가
        ajaxConfig.data =  $.extend(true, {}, ajaxConfig.data, params);

        //paging ajax succes 공통
        if(ajaxConfig.success == undefined){
            ajaxConfig.success = function(pageRes){
                // console.log(pageRes)
                if(pageRes.code==200){
                    $(pageRes.data.contents).map(function(i, e){
                        e['no'] = pageRes.data.totalElements - ((pageRes.data.page-1)*pageRes.data.size+i);
                        e['createDt'] = e.createDt ? e.createDt.replaceAll('-','.') : e.createDt;
                    });

                    if(typeof dataCallback != "undefined" && dataCallback != null){
                        dataCallback(pageRes.data);
                    }
                }
            }
        }
        ajaxConfig.complete = function(){
            //새로고침시 검색데이터 유지되도록
            setQueryStringURI(this.data);
        }


        this.ajax(ajaxConfig, null);
    }
    ,putAjax: function(ajaxConfig, errorCallback) {
        var defaultConf = {
            type: 'PUT',
            dataType: 'json',
			async: true,
            error : function(jqXHR, textStatus){
                console.log(jqXHR.status, jqXHR );
                alert("[[#{alert.error}]]");
                if(typeof errorCallback != "undefined" && errorCallback != null){
                    errorCallback(jqXHR);
                }
            }
        };
        var mergeConf = $.extend(true, {}, defaultConf, ajaxConfig);
        $.ajax(mergeConf);
    }
}


//검색 파라미터 -> URI 파라미터
var setQueryStringURI = function(data){
    var queryString = new URLSearchParams(data).toString();
    var redirectUri = location.pathname + '?' + queryString;
    history.replaceState({},'',redirectUri);
}


//URI 파라미터 -> 검색 파라미터
var setQueryStringParams = function(formId) {
	if ( !location.search ) {
		return false;
	}
    formId = (typeof formId == "undefined" || formId == null)? 'form' : formId;
	var form = document.getElementById(formId);
	new URLSearchParams(location.search).forEach((value, key) => {
		if (form[key]) {
			form[key].value = value;
		}
	});
}

var preventClick = function(e){
	e.preventDefault()
}


var setPagination = function(data, elemnt){
    var prevTmpl = $("#paginate-prev-tmpl").html();
    var prevPage = data.page > 1 ?   data.page-1 : 1;
    var prev = Mustache.render(prevTmpl, {'class' : data.page > 1 ? 'enabled' : 'disabled', 'page': prevPage});


    var nextTmpl = $("#paginate-next-tmpl").html();
    var nextPage = data.page +1 > data.totalPages ?   data.totalPages : data.page +1;
    var next = Mustache.render(nextTmpl,  {'class' : data.page < data.totalPages? 'enabled' : 'disabled', 'page': nextPage});

    var paginateTmpl = $("#paginate-tmpl").html();
    var list = []
    for(var i=0; i<data.totalPages ; i++){
        var page = i+1;
        list.push({'class':page == data.page ? 'on':'', 'page':page})
    }
    var paginate = Mustache.render(paginateTmpl, {'list' : list});

    elemnt.html(prev + paginate + next)

}

var layerInquiryBtn = function(data){
    $('body').addClass('scroll-lock');
    var type  = data == 1 ? '대리점 문의' : '제품문의';

    //init
    $('#inquiryType').val(type);
    $('#name').val('');
    $('#tel').val('');
    $('#email').val('');
    $('#company').val('');
    $('#inquiry').val('');
    $('#position').val('');

    //show
    $('.layer.inquiry').show('fast');;
    $('.layer_bg').show();
    $('#loading-prograss').hide();
}

var closeInquiryLayer = function(){
    $('body').removeClass('scroll-lock');
    $('.layer.inquiry').hide('fast');
    $('.layer_bg').hide();
}

// 쿠키 가져오기 
var getCookie = (key) => {
    var cookies = document.cookie.split(`; `).map((el) => el.split('='));

    for (var i = 0; i < cookies.length; i++) {
        if (cookies[i][0] === key) {
            return cookies[i][1];
        }
    }
    return null;
};

document.addEventListener('DOMContentLoaded', function () {
    var lang = document.cookie.replace(/(?:(?:^|.*;\s*)lang\s*=\s*([^;]*).*$)|^.*$/, '$1');
    if (lang === 'en') {
        document.querySelector('html').classList.add('en');
    }
});
