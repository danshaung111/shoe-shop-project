
$(document).ready(function(){
    
    //Check to see if the window is top if not then display button
    $(window).scroll(function(){
        if ($(this).scrollTop() > 100) {
            $('.scroll-to-top').fadeIn();
        } else {
            $('.scroll-to-top').fadeOut();
        }
    });
    
    //Click event to scroll to top
    $('.scroll-to-top').click(function(){
        $('html, body').animate({scrollTop : 0},200);
        return false;
    });
    
});

$(document).ready(function (argument) {
    var owl = $('.owl-carousel').owlCarousel({
        loop:true,
        margin:20,
        autoplay:true,
        autoplayTimeout:1500,
        autoplayHoverPause:true,
        autoHeight:true,
        responsiveClass:true,
      responsive:{
        0:{
            items:1,
        },
        600:{
          items:3,
          nav:false
        }
      }
    });
    $('.play').on('click',function(){
      owl.trigger('play.owl.autoplay',[3000])
    })
    $('.stop').on('click',function(){
      owl.trigger('stop.owl.autoplay')
    })
  })