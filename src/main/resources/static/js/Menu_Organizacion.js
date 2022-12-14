
const API_ENDPOINT = "http://localhost:9000";


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

const pantallaSolicitudes() = async () => {
        window.location.href = "./Aceptar_Miembros.html";
  };

const logout() = async () => {
   await fetch(`${API_ENDPOINT}/cerrar_sesion`, {
      method: "POST",

      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    })
    .catch((error) => {
      console.log(error)
    });
};
