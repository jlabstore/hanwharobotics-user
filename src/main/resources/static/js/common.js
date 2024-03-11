const win = $(this); //this = window
const mailList = {
  "product" : "rbt_sales@hanwha.com",
  "inquiry" : "rbt_distributor@hanwha.com",
  "partner" : "rbt_business@hanwha.com",
  "education" : "rbt_education@hanwha.com"
}

let headerMinHeight = '90px';

// function showLayer(layerName, target, headerMinHeight) {

//   const offset = $(window).scrollTop()+50;
//   if(layerName == 'cookie'){
//     $(`.layer.${layerName}`).show('fast');
//   }else{
//     $(`.layer.${layerName}`).css('top', offset+'px').show('fast');
//   }

//   $('.layer_bg').show();
//   target.removeClass('active');
//   $('html').removeClass('scroll-lock');

//   $('header .btn_menu button').removeClass('active');
//   $('.gnb_menu, .gnb_layer').removeClass('active');

//   $('.header__nav__items_sub').removeClass('active');
//   target.closest('#header').stop().animate({'height': headerMinHeight}, 'fast');
//   $('#header').removeClass('active');
// }

// pc, table, mobile 여부
$(window).on("load resize", function (e) {
  $('#header .header__nav__items_sub').removeClass('active');
  $(this).closest('#header').removeClass('active');
  $(this).removeClass('active');
  $('header .btn_menu button').removeClass('active');
  $('.gnb_menu, .gnb_layer').removeClass('active');
  $('html').removeClass('scroll-lock');

  let top = 0;

  if (win.outerWidth() <= 950) {
    top = 130;
  } else if (win.outerWidth() > 950 && win.outerWidth() <= 1190) {
    top = 167;
  } else {
    top = 207;
  }

  let headerHeight = '332px';
  if(win.outerWidth() <= 1024) {
    headerMinHeight = '70px';
  } else {
    headerMinHeight = '90px';
  }

  // if(win.width() <= 1550) {
  //   headerHeight = '450px';
  // }

  // if (win.outerWidth() <= 1024) {
  //   $(document).find('#header .gnb_menu').off('mouseover mouseleave');
  //   $(document).find('#header .header_inner .gnb_menu').off('click');

  //   $(document).on('click', '#header .btn_menu button', function(e) {
  //     e.stopPropagation();

  //     $(this).closest('#header').addClass('active');
  //     $(this).closest('#header').stop().animate({'height': headerHeight}, 100, () => {
  //       $(this).closest('#header').find('.gnb_menu').addClass('active');
  //       $(this).closest('#header').find('.header__nav__items_sub').addClass('active');
  //     });
  //   })

  // } else if (win.outerWidth() <= 850) {
  if (win.outerWidth() <= 1024) {
    $(document).off('mouseover mouseleave');
    $(document).find('#header .header_inner .gnb_menu').off('click');

    $('header').removeClass('active');
    $('header .gnb_layer').removeClass('active');
    $('header .gnb_menu').removeClass('active');


    $(document).on('click', '#header .header_inner .gnb_menu', function(e) {
      e.stopPropagation();

      if(e.target.tagName !== 'A') {
        $(this).closest('#header').stop().animate({'height': headerHeight}, 'fast', () => {
          $(this).find('.header__nav__items_sub').addClass('active');
          $(this).closest('#header').addClass('active');
        });
        $(this).closest('.header__nav__items_sub').addClass('active');
      }
    });
  } else {

    $(document).on('mouseover', '#header .gnb_menu', function() {

      $(this).closest('#header').addClass('active');
      $(this).closest('#header').stop().animate({'height': headerHeight}, 100, () => {
        $(this).find('.header__nav__items_sub').addClass('active');
      });
    })
    .on('mouseleave', '#header', function() {
      $(this).closest('#header').stop().animate({'height': headerMinHeight}, 100, () => {
        $(this).find('.header__nav__items_sub').removeClass('active');
        $(this).closest('#header').removeClass('active');
      });
    });

  }

  $('#header').css('height', headerMinHeight);

  if (win.outerWidth() <= 1024) {
    $("body").attr("class", "mobile");
  } else if (win.outerWidth() <= 1550) {
    $("body").attr("class", "tablet");
  } else {
    $("body").attr("class", "pc");
  }
  $("header").css('visibility', 'visible');

  if (win.outerWidth() <= 765) {
    $('.management-policy-certification').removeAttr('open');
  }

  if (win.outerWidth() > 765) {
    $('.management-policy-certification').attr('open', 'true');
  }

  // setTimeout(function() {
  //   more_btn 요소를 선택합니다.
  //   const moreBtn = $('#aside .more_btn');
  //   const scrollPosition = window.pageYOffset || document.documentElement.scrollTop;
  //   Convert the current top style to an integer and add the scroll position
  //   const currentTop = parseInt(moreBtn.css('top'), 10);
  //   moreBtn.css('top', top + scrollPosition + 'px');
  // }, 300);
});

$(document).ready(async function() {

  // let headerHeight = '332px';
  // $('.inquiry_more').load('/includes/inquiry.html');

  // $('#header').load('/includes/header.html');
  // $('#aside').load('/includes/aside.html');

  // $('#footer').load('/includes/footer.html', function() {
    // changeUrl();
  // });

  // $('.layer_wrap').load('/includes/layer.html', function() {
  //   $('.layer.inquiry').load('/includes/layer_inquiry.html');
    // $('.layer.company').load('/includes/layer_company.html');
    // $('.layer.email').load('/includes/layer_email.html');
    // $('.layer.privacy').load('/includes/layer_privacy.html');
    // $('.layer.copyright').load('/includes/layer_copyright.html');
    // $('.layer.family').load('/includes/layer_family.html');
    // $('.layer.cookie').load('./includes/layer_cookie.html');
  // });

  $(document).on('click', 'header .btn_menu button', function(e) {
    e.stopPropagation();

    if(win.outerWidth() <= 1024) {
      const gnb = $('.gnb_menu, .gnb_layer');

      $('header .gnb_layer').addClass('active');
      $(this).addClass('active');
      gnb.addClass('active');
      $('html').addClass('scroll-lock');
    }
  });

  $(document).on('click', 'header .gnb_layer, header .gnb_menu .btn_close', function(e) {
    e.stopPropagation();

    if(win.outerWidth() <= 1024) {
      $(this).closest('#header').stop().animate({'height': headerMinHeight}, 'fast');
    }
    $('#header .header__nav__items_sub').removeClass('active');
    $(this).closest('#header').removeClass('active');
    $(this).removeClass('active');
    $('header .btn_menu button').removeClass('active');
    $('.gnb_menu, .gnb_layer').removeClass('active');
    $('html').removeClass('scroll-lock');
  });

  $(document).on('click', 'header .gnb_menu .header__nav__items .header__nav__items_sub a', function(e) {
    e.stopPropagation();

    if(win.outerWidth() <= 1024) {
      $(this).closest('#header').stop().animate({'height': headerMinHeight}, 'fast');
    }
    $('#header .header__nav__items_sub').removeClass('active');
    $(this).closest('#header').removeClass('active');
    $(this).removeClass('active');
    $('header .btn_menu button').removeClass('active');
    $('.gnb_menu, .gnb_layer').removeClass('active');
    $('html').removeClass('scroll-lock');
  });

  /**
   * 문의하기 버튼(header.html와 aside.html의 inquiry) 클릭 시 문의하기 팝업 호출
   * utils/common.js layerInquiryBtn()로 대체
   */
  // $(document).on('click', '#header button.inquiry, #aside button.inquiry', function(e) {
  //   e.stopPropagation();

  //   // $('body').addClass('scroll-lock');
  //   $('.layer.inquiry').show('fast');
  //   $('.layer_bg').show();
  //   $(this).removeClass('active');
  //   $('html').removeClass('scroll-lock');

  //   $('header .btn_menu button').removeClass('active');
  //   $('.gnb_menu, .gnb_layer').removeClass('active');

  //   $('.header__nav__items_sub').removeClass('active');
  //   $(this).closest('#header').stop().animate({'height': headerMinHeight}, 'fast');
  //   $('#header').removeClass('active');
  // });

  /**
   * 푸터 팝업 버튼 utils/common.js layerCompanyOpenBtn()로 대체
   */
  // $(document).on('click', 'button.sitemap_company', function(e) {
  //   e.stopPropagation();

  //   showLayer('company', $(this), headerMinHeight);
  // });

  // $(document).on('click', 'button.sitemap_email', function(e) {
  //   e.stopPropagation();

  //   showLayer('email', $(this), headerMinHeight);
  // });


  // $(document).on('click', 'button.sitemap_copyright', function(e) {
  //   e.stopPropagation();

  //   showLayer('copyright', $(this), headerMinHeight);
  // });

  // $(document).on('click', 'button.sitemap_privacy', function(e) {
  //   e.stopPropagation();

  //   showLayer('privacy', $(this), headerMinHeight);
  // });

  // $(document).on('click', '.link .select_wrap.popup', function(e) {
  //   e.stopPropagation();

  //   showLayer('family', $(this), headerMinHeight);
  // });

  /**
   * utils/common.js 에 동일한 기능 존재하여 주석 처리
   */
  // $(document).on('click', 'button.sitemap_cookie', function(e) {
  //   e.stopPropagation();

  //   showLayer('cookie', $(this), headerMinHeight);
  // });

  /**
   * utils/common.js 에 동일한 기능 존재하여 주석 처리
   */
  // $(document).on('click', '.layer_bg, .layer .btn_close', function() {
  //   $('body').removeClass('scroll-lock');
  //  // $('.layer.inquiry').hide('fast');
  //   $('.layer').hide('fast');
  //   $('.layer_bg').hide();
  // });


  $(document).on('click', '.mobile .block .checkbox', function() {
    $(this).closest('.block').toggleClass('on');
  });
});






$('.products-section2-slider').slick({
  rows: 3,
  dots: true,
  arrows: true,
  slidesPerRow: 3,
  infinite: false,
  autoplay: false,
  draggable: false,
  prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/btn_paging_arrow_enabled.svg' alt='' /></button>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/btn_paging_arrow_enabled.svg' alt='' /></button>",
  responsive: [
    {
      breakpoint: 1024,
      settings: {
        rows: 3,
        slidesPerRow: 2,
        draggable: true
      }
    },
    {
      breakpoint: 765,
      settings: {
        rows: 3,
        slidesPerRow: 1,
        draggable: true
      }
    }
  ]
});





$('.robots-section2-slider').slick({
  rows: 2,
  dots: true,
  arrows: true,
  slidesPerRow: 2,
  infinite: false,
  autoplay: false,
  draggable: false,
  prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/btn_paging_arrow_enabled.svg' alt='' /></button>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/btn_paging_arrow_enabled.svg' alt='' /></button>",
  responsive: [
    {
      breakpoint: 1024,
      settings: {
        draggable: true
      }
    },
    {
      breakpoint: 765,
      settings: {
        rows: 4,
        slidesPerRow: 1,
        draggable: true
      }
    }
  ]
});




$('.products-detail-slider').slick({
  arrows: true,
  infinite: false,
  autoplay: false,
  slidesToShow: 6,
  slidesToScroll: 1,
  draggable: false,
  prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/products_detail_slider_prev.svg' alt='' /></button>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/products_detail_slider_next.svg' alt='' /></button>",
  responsive: [
    {
      breakpoint: 1024,
      settings: {
        draggable: true,
        slidesToShow: 4,
      }
    },
    {
      breakpoint: 765,
      settings: {
        arrows: false,
        draggable: true,
        slidesToShow: 3,
        slidesToScroll: 3,
        swipeToSlide: true
      }
    },
  ]
})


$('.robots-detail-slider').slick({
  arrows: true,
  infinite: true,
  autoplay: false,
  slidesToShow: 2,
  slidesToScroll: 1,
  draggable: false,
  variableWidth: true,
  prevArrow: "<div></div>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/ic_robots_detail_arrow.svg' alt='' /></button>",
  responsive: [
    {
      breakpoint: 1024,
      settings: {
        arrows: true,
        draggable: true,
        slidesToShow: 2,
      }
    },
    {
      breakpoint: 765,
      settings: {
        arrows: true,
        draggable: true,
        variableWidth: true,
        slidesToShow: 1,
        swipeToSlide: true
      }
    },
  ]
})

if (win.outerWidth() <= 765) {
  $('.robots-section4-slider').slick({
    rows: 3,
    dots: true,
    arrows: true,
    slidesPerRow: 2,
    infinite: false,
    autoplay: false,
    draggable: false,
    prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/btn_paging_arrow_enabled.svg' alt='' /></button>",
    nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/btn_paging_arrow_enabled.svg' alt='' /></button>",
  });
}


if (win.outerWidth() > 765) {
  $('#nav1-1-content').show();
} else {
  $('#nav1-1-content').hide();
}

$("input[name='nav1-1']").change(function() {
  let value = $("input[name='nav1-1']:checked").val();

  if (value === 'nav1-1') {
    $('#nav1-1-content').show();
    $('#nav1-2-content').hide();
    $('#nav1-3-content').hide();
    $('#nav1-4-content').hide();
  }

  if (value === 'nav1-2') {
    $('#nav1-1-content').hide();
    $('#nav1-2-content').show();
    $('#nav1-3-content').hide();
    $('#nav1-4-content').hide();
  }

  if (value === 'nav1-3') {
    $('#nav1-1-content').hide();
    $('#nav1-2-content').hide();
    $('#nav1-3-content').show();
    $('#nav1-4-content').hide();
  }

  if (value === 'nav1-4') {
    $('#nav1-1-content').hide();
    $('#nav1-2-content').hide();
    $('#nav1-3-content').hide();
    $('#nav1-4-content').show();
  }
})

if (win.outerWidth() <= 765) {
  $('.nav1-content-mobile-title').first().toggleClass('on').next('.nav1-content-mobile-content').slideToggle(300);
}

$(".nav1-content-mobile-title").click(function() {
  $(this).next(".nav1-content-mobile-content").stop().slideToggle(300);
  $(this).toggleClass('on').siblings().removeClass('on');
  $(this).next(".nav1-content-mobile-content").siblings(".nav1-content-mobile-content").slideUp(300); // 1개씩 펼치기
});








// 스크롤 이벤트 리스너를 추가합니다.
// window.addEventListener('scroll', throttle(function() {
//   requestAnimationFrame(() => {
//     let top = 0;

//     if (win.width() <= 1024) {
//       top = 130;
//     } else if (win.width() > 1024 && win.width() <= 1190) {
//       top = 167;
//     } else {
//       top = 207;
//     }

//     // more_btn 요소를 선택합니다.
//     const moreBtn = $('#aside .more_btn');

//     // 브라우저 창 상단으로부터의 현재 스크롤 위치를 얻습니다.
//     const scrollPosition = window.pageYOffset || document.documentElement.scrollTop;
//
//     // Convert the current top style to an integer and add the scroll position
//     // const currentTop = parseInt(moreBtn.css('top'), 10);
//     moreBtn.css('top', top + scrollPosition + 'px');
//   });
// });