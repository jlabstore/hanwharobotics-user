
let resizeTimeout = null;
let lastScrollPos = 0;
const $header = $("#header");

function hideHeader() {
  let lastScrollPos = 0;

  const $header = $("#header");

  // ScrollTrigger
  headerScrollTrigger = ScrollTrigger.create({
    trigger: "#header",
    start: "top top",
    end: "+=100%",
    onRefreshInit: function(self) {
      lastScrollPos = self.scroll();
    },
    onUpdate: function(self) {
      const currentScrollPos = self.scroll();

      if (currentScrollPos > lastScrollPos) {
        $header.css('opacity', 0);
      } else {
        $header.css('opacity', 1);
      }
      lastScrollPos = currentScrollPos;
    }
  });
}

function pcBoxPositionUp() {
  const $missionBox = $('.section2 .second_box');

  $missionBox.css('top', '140px')
  .on('mouseenter', function() {
    $missionBox.stop().animate({'top': '0'});
  });

  $missionBox
  .on('mouseleave', function() {
    $missionBox.stop().animate({'top': '140px'});
  });
}

$(window).on("resize", function () {
  // backgroundHeader();
  // const $missionBox = $('.section2 .second_box');
  // hideHeader();

  // if ($(this).width() >= 1190) {
  //   pcBoxPositionUp();
  // } else {

  //   $missionBox.css('top', '0')
  //   .off('mouseenter mouseleave');
  // }
});

$(window).on("load", function (e) {
  // backgroundHeader();
  // const $missionBox = $('.section2 .second_box');
  // hideHeader();

  // if ($(this).width() >= 1190) {
  //   pcBoxPositionUp();
  // } else {
  //   $missionBox.css('top', '0')
  //   .off('mouseenter mouseleave');
  // }

  // document.querySelectorAll('.main .section1 .contents').forEach((section, index, array) => {

  //   // Pin the section
  //   gsap.to(section, {
  //     scrollTrigger: {
  //       trigger: section,
  //       start: "top top",
  //       end: "bottom top",
  //       pin: true,
  //       scrub: true
  //     }
  //   });

  //   // Only proceed if there is a next section
  //   if (index < array.length - 1) {
  //     const nextSection = array[index + 1]; // Get the next section

  //     const textElements = section.querySelectorAll('.title, .txt, .move_to_intro');
  //     const nextTextElements = nextSection.querySelectorAll('.title, .txt, .move_to_intro');


  //     // Create a timeline for the current section's text elements
  //     const tl = gsap.timeline({
  //       scrollTrigger: {
  //         trigger: nextSection, // Use the next section as the trigger
  //         start: "top bottom", // Start when the top of the next section hits the bottom of the viewport
  //         end: "top top", // End when the top of the next section hits the top of the viewport
  //         scrub: true,
  //         onEnter: () => {
  //           // When the next section's .title and .txt start coming in, fade out the current section's .title and .txt
  //           textElements.forEach(el => {
  //             gsap.to(el, { opacity: 0, duration: 3 });
  //           });

  //         },
  //         onLeaveBack: () => {
  //           // When scrolling back up, reset the opacity if the previous .title and .txt are in view again
  //           textElements.forEach(el => {
  //             gsap.to(el, { opacity: 1, duration: 3 });
  //           });
  //         }
  //       }
  //     });

  //     // Fade in the next section's text elements gradually
  //     nextTextElements.forEach(el => {
  //       tl.fromTo(el, { opacity: 0 }, { opacity: 1, duration: 3, ease: "none" });
  //     });
  //   }
  // });
});

$(window).scroll(function (e) {
  if(window.scrollY < 1) {
    $header.css('backgroundColor', 'transparent').find('.header_wrapper').css('backgroundColor', 'transparent');
    $header.removeClass('scroll');
    $('.inquiry_more .bg').css({
      'opacity': '0.6',
      'background': '#000'
    });
    $('header .logo .txt span').css('color', '#f60');
    $('header .logo .txt span strong').css('background', 'url(../images/pc/ic_main_more.svg) no-repeat 0 0')

  } else { 

    $header.css('backgroundColor', '#f60').find('.header_wrapper').css('backgroundColor', '#f60');
    $header.addClass('scroll');
    $('.inquiry_more .bg').css({
      'opacity': '1',
      'background': '#666'
    });
    $('header .logo .txt span, header .logo .txt span strong').css('color', '#fff');
    $('header .logo .txt span strong').css('background', 'url(../images/pc/ic_sub_more.svg) no-repeat 0 0')
  }
});