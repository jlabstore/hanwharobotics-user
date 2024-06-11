const win = $(this); //this = window
const mailList = {
  "product": "rbt_sales@hanwha.com",
  "inquiry": "rbt_distributor@hanwha.com",
  "partner": "rbt_business@hanwha.com",
  "education": "rbt_education@hanwha.com"
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

// $(window).on('resize', function() {
//   const gnbTarget = $(document.body).data('gnb-target');
//
//   if (gnbTarget === 'robot' && win.outerWidth() <= 765) {
//     window.location.reload();
//   }
// });

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
  if (win.outerWidth() <= 1024) {
    headerMinHeight = '70px';
  } else {
    headerMinHeight = '90px';
  }

  if (win.outerWidth() <= 1024) {
    $(document).on('click', 'header .gnb_right .login', function (e) {
      e.stopPropagation();
      $('header .gnb_right .login').toggleClass('active');
      $('header .gnb_right .login .login-tooltip').toggleClass('active');
    });
  } else {
    $(document).off('click', 'header .gnb_right .login');
  }
  ;

  if (win.outerWidth() > 1024) {
    $(document).on('mouseenter', 'header .gnb_right .login', function () {
      $('header .gnb_right .login .login-tooltip').fadeIn(100);
      $('header .gnb_right .login .login-tooltip').addClass('active');
    });

    $(document).on('mouseleave', 'header .gnb_right .login', function () {
      $('header .gnb_right .login .login-tooltip').fadeOut(100);
      $('header .gnb_right .login .login-tooltip').removeClass('active');
    });
  } else {
    $(document).off('mouseenter', 'header .gnb_right .login');
    $(document).off('mouseleave', 'header .gnb_right .login');
    $('header .gnb_right .login').removeAttr('style');
    $('header .gnb_right .login .login-tooltip').removeAttr('style');
  }
  ;

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

    $(document).on('click', '#header .header_inner .gnb_menu', function (e) {
      e.stopPropagation();

      if (e.target.tagName !== 'A') {
        $(this).closest('#header').stop().animate({'height': headerHeight},
            'fast', () => {
              $(this).find('.header__nav__items_sub').addClass('active');
              $(this).closest('#header').addClass('active');
            });
        $(this).closest('.header__nav__items_sub').addClass('active');
      }
    });
  } else {

    $(document).on('mouseover', '#header .gnb_menu', function () {

      $(this).closest('#header').addClass('active');
      $(this).closest('#header').stop().animate({'height': headerHeight}, 100,
          () => {
            $(this).find('.header__nav__items_sub').addClass('active');
          });
    })
    .on('mouseleave', '#header', function () {
      $(this).closest('#header').stop().animate({'height': headerMinHeight},
          100, () => {
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

  if (win.outerWidth() <= 765) {
    $('.products-detail-slider').slick('unslick');
  } else {
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
          breakpoint: 1200,
          settings: {
            draggable: true,
            slidesToShow: 4,
          }
        },
      ]
    })
  }

  if (win.outerWidth() <= 765) {
    $('.robots-section4-slider').slick({
      rows: 3,
      dots: true,
      arrows: true,
      slidesPerRow: 2,
      infinite: false,
      autoplay: false,
      draggable: false,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
    });
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

$(document).ready(async function () {

  // let headerHeight = '332px';
  // $('.inquiry_more').load('/includes/inquiry.html');

  // $('#header').load('/includes/header.html');
  // $('#header').load('/templates/layout/header.html', function() {
  const gnbTarget = $(document.body).data('gnb-target');
  if (gnbTarget === 'main' && win.outerWidth() > 1024) {
    $('.header__nav__link').addClass('active');
    $('.header__nav__link').hover(function () {
      $('.header__nav__link').removeClass('active');
      $(this).addClass('active');
    }, function () {
      $('.header__nav__link').addClass('active');
    });
    return;
  }
  $(`[data-gnb="${gnbTarget}"]`).addClass('active');
  // });
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

  $(document).on('click', 'header .btn_menu button', function (e) {
    e.stopPropagation();

    if (win.outerWidth() <= 1024) {
      const gnb = $('.gnb_menu, .gnb_layer');

      $('header .gnb_layer').addClass('active');
      $(this).addClass('active');
      gnb.addClass('active');
      $('html').addClass('scroll-lock');
    }
  });

  $(document).on('click', 'header .gnb_layer, header .gnb_menu .btn_close',
      function (e) {
        e.stopPropagation();

        if (win.outerWidth() <= 1024) {
          $(this).closest('#header').stop().animate({'height': headerMinHeight},
              'fast');
        }
        $('#header .header__nav__items_sub').removeClass('active');
        $(this).closest('#header').removeClass('active');
        $(this).removeClass('active');
        $('header .btn_menu button').removeClass('active');
        $('.gnb_menu, .gnb_layer').removeClass('active');
        $('html').removeClass('scroll-lock');
      });

  $(document).on('click',
      'header .gnb_menu .header__nav__items .header__nav__items_sub a',
      function (e) {
        e.stopPropagation();

        if (win.outerWidth() <= 1024) {
          $(this).closest('#header').stop().animate({'height': headerMinHeight},
              'fast');
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

  $('#email-domain').change(function () {
    $('#email-domain option:selected').each(function () {
      if ($(this).val() === 'manual') {
        $('#email-domain').hide();
        $('#email-domain-manual').show();
        $('#email-domain-manual').focus();
      } else {
        $('#email-domain-manual').hide();
      }
    })
  })

  /**
   * qna_write.html, qna_edit.html에 동일한 기능 존재하여 주석 처리
   */
  // Array.from({ length: 10 }).forEach((_, i) => {
  //   console.log("이 메소드 타는 것")
  //   let currIndex = i + 1;
  //   $(`input[name=software${currIndex}]`).keyup(function(e) {
  //     if (currIndex === 1 || currIndex === 4 || currIndex === 7) {
  //       $(this).next().next().focus();
  //     } else {
  //       $(this).next().focus();
  //     }
  //   });
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

  $(document).on('click', '.mobile .block .checkbox', function () {
    $(this).closest('.block').toggleClass('on');
  });
});

$('.products-section2-slider').slick({
  rows: 3,
  dots: false,
  arrows: false,
  slidesPerRow: 3,
  infinite: false,
  autoplay: false,
  draggable: false,
  prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
  responsive: [
    {
      breakpoint: 1024,
      settings: {
        rows: 3,
        slidesPerRow: 2,
        draggable: true,
        dots: true,
        arrows: true
      }
    },
    {
      breakpoint: 765,
      settings: {
        dots: true,
        arrows: true,
        rows: 3,
        slidesPerRow: 1,
        draggable: true
      }
    }
  ]
});

$('.robots-section2-slider').slick({
  rows: 2,
  /** 추후 슬라이드 4개 초과하였을 시, 활성화 */
  // dots: true,
  // arrows: true,
  dots: false,
  arrows: false,
  slidesPerRow: 2,
  infinite: false,
  autoplay: false,
  draggable: false,
  prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
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

$('.products-detail-video-slider').slick({
  arrows: true,
  infinite: true,
  autoplay: true,
  autoplaySpeed: 7000,
  slidesToShow: 1,
  slidesToScroll: 1,
  prevArrow: "<div></div>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/products_video_slider_arrow.svg' alt='' /></button>",
  draggable: true,
  responsive: [
    {
      breakpoint: 765,
      settings: {
        swipeToSlide: true
      }
    },
  ]
})

$('#ecosystem-main-slider1').not('.slick-initialized').slick({
  dots: true,
  arrows: true,
  infinite: false,
  autoplay: false,
  slidesPerRow: 4,
  rows: 3,
  prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
  responsive: [
    {
      breakpoint: 1024,
      settings: {
        slidesPerRow: 3,
        rows: 4,
      }
    },
    {
      breakpoint: 765,
      settings: {
        slidesPerRow: 2,
        rows: 3,
      }
    },
  ]
})

$('.ecosystem-section .tab-nav #nav2').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-main-slider2').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 3,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 4,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })
  }
})

$('.ecosystem-section .tab-nav #nav3').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-main-slider3').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 3,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 4,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })
  }
})

$('.ecosystem-section .tab-nav #nav4').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-main-slider4').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 3,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 4,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })
  }
})

$('.ecosystem-section .tab-nav #nav5').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-main-slider5').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 3,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 4,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })
  }
})

$('#ecosystem-template-01-slider1').not('.slick-initialized').slick({
  dots: true,
  arrows: true,
  infinite: false,
  autoplay: false,
  slidesPerRow: 4,
  rows: 2,
  prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
  responsive: [
    {
      breakpoint: 1024,
      settings: {
        slidesPerRow: 3,
        rows: 2,
      }
    },
    {
      breakpoint: 765,
      settings: {
        slidesPerRow: 2,
        rows: 3,
      }
    },
  ]
})

$('.ecosystem-template-01-section .tab-nav #nav1').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-template-01-slider2').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 2,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 2,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })
  }

  $('#ecosystem-template-01-slider1').slick('slickGoTo', 0);
})

$('.ecosystem-template-01-section .tab-nav #nav2').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-template-01-slider2').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 2,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 2,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })
  }

  $('#ecosystem-template-01-slider2').slick('slickGoTo', 0);
})

$('.ecosystem-template-01-section .tab-nav #nav3').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-template-01-slider3').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 2,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 2,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })
  }
})

$('.ecosystem-template-01-section .tab-nav #nav4').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-template-01-slider4').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 2,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 2,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })

    $('#ecosystem-template-01-slider4').slick('slickGoTo', 0);
  }
})

$('.ecosystem-template-01-section .tab-nav #nav5').change(function () {
  if ($(this).is(':checked')) {
    $('#ecosystem-template-01-slider5').not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 2,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesPerRow: 3,
            rows: 2,
          }
        },
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })

    $('#ecosystem-template-01-slider5').slick('slickGoTo', 0);
  }
})

$('.ecosystem-template-01-info-slider').slick({
  arrows: true,
  infinite: true,
  autoplay: false,
  slidesToShow: 1,
  prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/ecosystem_template_slider_left.svg' alt='' /></button>",
  nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/ecosystem_template_slider_right.svg' alt='' /></button>",
})

if (win.outerWidth() <= 765) {
  $('.tab-nav-mobile').change(function () {
    let value = $('.tab-nav-mobile option:selected').val();
    $('.nav-content').hide();
    $(`#${value}-content`).show();

    $(`#${value}-content .ecosystem-main-slider1`).not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 3,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })

    $(`#${value}-content .ecosystem-main-slider1`).slick('slickGoTo', 0);

    $(`#${value}-content .ecosystem-template-01-slider1`).not('.slick-initialized').slick({
      dots: true,
      arrows: true,
      infinite: false,
      autoplay: false,
      slidesPerRow: 4,
      rows: 2,
      prevArrow: "<button type='button' class='slick-prev'><img src='../images/pc/robots_slider_arrow_left.svg' alt='' /></button>",
      nextArrow: "<button type='button' class='slick-next'><img src='../images/pc/robots_slider_arrow_right.svg' alt='' /></button>",
      responsive: [
        {
          breakpoint: 765,
          settings: {
            slidesPerRow: 2,
            rows: 3,
          }
        },
      ]
    })

    $(`#${value}-content .ecosystem-template-01-slider1`).slick('slickGoTo', 0);
  });
}

// $(document).on('click', '.ecosystem-template-01-section input[type=radio]', function(e) {
//   $('.ecosystem-template-01-slider1').slick('slickGoTo', 0);
// });

// $(document).on('click', '.ecosystem-section input[type=radio]', function(e) {
//   $('.ecosystem-main-slider1').slick('slickGoTo', 0);
// });

if (win.outerWidth() > 765) {
  $('#nav1-1-content').show();
} else {
  $('#nav1-1-content').hide();
}

$("input[name='nav1-1']").change(function () {
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
  $('.nav1-content-mobile-title').first().toggleClass('on').next(
      '.nav1-content-mobile-content').slideToggle(300);
}

if (win.outerWidth() <= 765) {
  $('.robots-detail-spec-mobile-scroll').click(function () {
    $(this).hide();
    $(this).parent().css('overflow-x', 'scroll');
  })
}

$('.ecosystem-template-01-info-arrow').click(function () {
  $('.ecosystem-template-01-info-content p').animate({scrollTop: '+=100px'},
      300);
})

$(".nav1-content-mobile-title").click(function () {
  $(this).next(".nav1-content-mobile-content").stop().slideToggle(300);
  $(this).toggleClass('on').siblings().removeClass('on');
  $(this).next(".nav1-content-mobile-content").siblings(
      ".nav1-content-mobile-content").slideUp(300); // 1개씩 펼치기
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