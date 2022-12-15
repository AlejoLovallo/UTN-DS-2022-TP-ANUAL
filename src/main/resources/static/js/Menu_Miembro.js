$(function() {
    $('a.page-scroll').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });
});

// Highlight the top nav as scrolling occurs
$('body').scrollspy({
    target: '.navbar-fixed-top'
})

// Closes the Responsive Menu on Menu Item Click
$('.navbar-collapse ul li a').click(function() {
    $('.navbar-toggle:visible').click();
});

const logout = async () => {

  delete_cookie("idSesion")

  window.location.replace("./")
   // await fetch(`${API_ENDPOINT}/cerrar_sesion`, {
   //    method: "GET",
   //
   //    headers: {
   //      "Content-type": "application/json; charset=UTF-8",
   //    },
   //  })
   //  .catch((error) => {
   //    console.log(error)
   //  });
};

var delete_cookie = function(name) {
  document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};