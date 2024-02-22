var COMM = {
	ajax: function(ajaxConfig, errorCallback) {
        var defaultConf = {
            type: 'POST',
            dataType: 'json',
			async: true,
            // contentType: 'application/json;charset=UTF-8',
            error : function(jqXHR, textStatus){
                // 에러 공통 메세지를 띄우지 않으려면 error자체를 ajaxConfig에 재지정
                // 에러 공통 메세지는 띄우고 추가 작업이 필요하면 errorCallback 전달
                console.log(jqXHR.status, jqXHR );

                // alert("[[#{alert.error}]]");
                var lang = getCookieLang();
                if(lang == 'en'){
                    alert("There has been an error processing your request. Please contact Admin.")
                }else{
                    alert("요청 실행 중 문제가 발생했습니다. 관리자에게 문의하세요.");
                }

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
        var keyword = $('#keyword').val(); //검색어
        var type = $('#type').val();       //구분
        if(keyword != undefined) params['keyword'] = keyword;
        if(type != undefined) params['type'] = type;

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

function showLayer(layerName, target, headerMinHeight, bgColor = '#fff', opacity = 0.1) {

    const offset = $(window).scrollTop()+50;
    if(layerName == 'cookie'){
      $(`.layer.${layerName}`).show('fast');
    }else{
      $(`.layer.${layerName}`).css('top', offset+'px').show('fast');
    }

    // $('.layer_bg').show();
    $('.layer_bg').css('background-color', bgColor).css('opacity', opacity).show();
    target.removeClass('active');
    $('html').removeClass('scroll-lock');

    $('header .btn_menu button').removeClass('active');
    $('.gnb_menu, .gnb_layer').removeClass('active');

    $('.header__nav__items_sub').removeClass('active');
    target.closest('#header').stop().animate({'height': headerMinHeight}, 'fast');
    $('#header').removeClass('active');
  }

//이벤트 막기
var preventClick = function(e){
	e.preventDefault()
}

// 페이징 UI 셋팅
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

// 문의하기 팝업 레이어 열기
var layerInquiryBtn = function(data){

    var lang = getCookieLang()
    if (lang == 'en') {
        alert('Coming Soon');
    } else {
        $('body').addClass('scroll-lock');
        // var type  = data == 1 ? '대리점 문의' : '제품 문의';
        var type = "";
        if(data == 1) {
            type = '대리점 문의';
        } else if (data == 2) {
            type = '제품 문의';
        } else {
            type = "";
        }

        //init
        $('#inquiryType').val(type);
        // $('#inquiryType').val('');
        $('#name').val('');
        $('#tel').val('');
        $('#email').val('');
        $('#company').val('');
        $('#inquiry').val('');
        $('#position').val('');

        // $('#inquiryType').val(type);

        //show
        // $('.layer.inquiry').show('fast');
        // $('.layer_bg').show();
        // $('#loading-prograss').hide();

        // e.stopPropagation();
        showLayer('inquiry', $('.layer.inquiry'), headerMinHeight);
    }
}

// 푸터 팝업 열기
var layerCompanyOpenBtn = function() {
    // $('.layer.company').show('fast');
    // $('.layer_bg').show();
    // $('#loading-prograss').hide();

    // e.stopPropagation();
    showLayer('company', $('.layer.company'), headerMinHeight);
}
var layerPrivacyOpenBtn = function() {
    // $('.layer.privacy').show('fast');
    // $('.layer_bg').show();
    // $('#loading-prograss').hide();

    // e.stopPropagation();
    showLayer('privacy', $('.layer.privacy'), headerMinHeight);
}
var layerCopyrightOpenBtn = function() {
    // $('.layer.copyright').show('fast');
    // $('.layer_bg').show();
    // $('#loading-prograss').hide();

    // e.stopPropagation();
    showLayer('copyright', $('.layer.copyright'), headerMinHeight);
}
var layerEmailOpenBtn = function() {
    // $('.layer.email').show('fast');
    // $('.layer_bg').show();
    // $('#loading-prograss').hide();

    // e.stopPropagation();
    showLayer('email', $('.layer.email'), headerMinHeight);
}
var layerFamilyOpenBtn = function() {
    // $('.layer.family').show('fast');
    // $('.layer_bg').show();
    // $('#loading-prograss').hide();

    // e.stopPropagation();
    showLayer('family', $('.layer.family'), headerMinHeight);
}


var layerQnaOpenBtn = function () {
    showLayer('qna', $(this), headerMinHeight, '#000', 0.2);

}



// $(document).on('click', 'button.inquiry.qna', function(e) {
//     e.stopPropagation();
//     showLayer('qna', $(this), headerMinHeight, '#000', 0.2);
// });





var checkCookie = function () {
    console.log('cookie popup')

    if(localStorage.chk1Yn != undefined && localStorage.chk1Yn != null && localStorage.chk1Yn) {
        document.getElementById('chk2').checked = localStorage.chk2Yn === 'true'
        document.getElementById('chk3').checked = localStorage.chk3Yn  === 'true'
    }

    // 모두 거부 상태
    if(localStorage.chk1Yn != undefined && localStorage.chk1Yn != null && !localStorage.chk1Yn) {
        document.getElementById('chk1').checked = true
        document.getElementById('chk2').checked = false
        document.getElementById('chk2').checked = false
    }
}

var layerCookieOpenBtn = function() {
    // e.stopPropagation();

    checkCookie();
    showLayer('cookie', $(".layer.cookie"));
    // console.log('cookie Popup',$('.layer.cookie').css('display'))
    // const layerCookie = $('.layer.cookie');
    // if(layerCookie.css('display') === 'none') {
    //   $('.layer.cookie').show('fast');
    //   $('html').addClass('scroll-lock');
    // } else {
    //   $('.layer.cookie').hide('fast');
    //   $('html').removeClass('scroll-lock');
    // }

}

// 문의하기 팝업 레이어 닫기
var closeInquiryLayer = function(id){
    $('body').removeClass('scroll-lock');
    // $('.layer.inquiry').hide('fast');
    if(id){
        $('#'+ id).hide('fast');

    }else{
        $('.layer').hide('fast');
    }
    $('.layer_bg').hide();
}

// 쿠키값 가져오기
var getCookie = (key) => {
    var cookies = document.cookie.split(`; `).map((el) => el.split('='));

    for (var i = 0; i < cookies.length; i++) {
        if (cookies[i][0] === key) {
            return cookies[i][1];
        }
    }
    return null;
};

// 쿠키 다국어 값 가져오기
var getCookieLang = ()=>{
    var langType = ['ko', 'en'];
    var lang = langType.includes(getCookie('lang')) ? getCookie('lang') : 'ko';
    return lang;
}

// 영문 css를 위한 클래스 수정(en 추가)
document.addEventListener('DOMContentLoaded', function () {
    var lang = getCookieLang();
    if (lang === 'en') {
        document.querySelector('html').classList.add('en');
    }
});


// function saveCookie(data) {
//     var chk1Yn = document.getElementById("chk1").checked
//     var chk2Yn = document.getElementById("chk2").checked
//     var chk3Yn = document.getElementById("chk3").checked

//     localStorage.setItem("chk1Yn", chk1Yn);
//     localStorage.setItem("chk2Yn", chk2Yn);
//     localStorage.setItem("chk3Yn", chk3Yn);

//     if(data == 1){
//         closeInquiryLayer()
//     } else if (data == 2) {
//         location.reload();
//     }
// }
// function denyCookie(data) {
//     var chk1Yn = document.getElementById("chk1").checked
//     var chk2Yn = document.getElementById("chk2").checked
//     var chk3Yn = document.getElementById("chk3").checked

//     chk1Yn = false;
//     chk2Yn = false;
//     chk3Yn = false;

//     localStorage.setItem("chk1Yn", chk1Yn);
//     localStorage.setItem("chk2Yn", chk2Yn);
//     localStorage.setItem("chk3Yn", chk3Yn);

//     if(data == 1){
//         closeInquiryLayer()
//     } else if (data == 2) {
//         location.reload();
//     }
// }
// function saveCookieAll(data) {
//     var chk1Yn = document.getElementById("chk1").checked
//     var chk2Yn = document.getElementById("chk2").checked
//     var chk3Yn = document.getElementById("chk3").checked

//     chk1Yn = true;
//     chk2Yn = true;
//     chk3Yn = true;

//     localStorage.setItem("chk1Yn", chk1Yn);
//     localStorage.setItem("chk2Yn", chk2Yn);
//     localStorage.setItem("chk3Yn", chk3Yn);

//     if(data == 1){
//         closeInquiryLayer()
//     } else if (data == 2) {
//         location.reload();
//     }
// }
function updateCookieValues(chk1Yn, chk2Yn, chk3Yn) {
    localStorage.setItem("chk1Yn", chk1Yn);
    localStorage.setItem("chk2Yn", chk2Yn);
    localStorage.setItem("chk3Yn", chk3Yn);
}

function handleCookieAction(data, chk1Yn, chk2Yn, chk3Yn) {
    updateCookieValues(chk1Yn, chk2Yn, chk3Yn);

    if (data === 1) {
        closeInquiryLayer();
    } else if (data === 2) {
        location.reload();
    }
}

function saveCookie(data) {
    var chk1Yn = document.getElementById("chk1").checked;
    var chk2Yn = document.getElementById("chk2").checked;
    var chk3Yn = document.getElementById("chk3").checked;

    handleCookieAction(data, chk1Yn, chk2Yn, chk3Yn);
}

function denyCookie(data) {
    handleCookieAction(data, false, false, false);
}

function saveCookieAll(data) {
    handleCookieAction(data, true, true, true);
}