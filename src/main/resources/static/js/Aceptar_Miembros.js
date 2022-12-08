const API_ENDPOINT = "http://127.0.0.1:9000";

const aceptarMiembro = async () => {
  const id = document.getElementById("ID").value;
  const nombre = document.getElementById("nombre").value;
  const apellido = document.getElementById("apellido").value;
  const tipoDeDocumento = document.getElementById("tipoDeDocumento").value;
  const nroDeDocumento = document.getElementById("nroDeDocumento").value;
  

  await fetch(`${API_ENDPOINT}/aceptar_miembro`, {
    method: "POST",
    mode: "no-cors",

    body: JSON.stringify({
      id,
      nombre,
      apellido,
      tipoDeDocumento,
      nroDeDocumento
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((res) => {
      console.log("OKKKK");
      console.log(res);
      alert(res);
      window.location.href = "./Aceptar_Miembros.html";
    })
    .catch((e) => {
      console.log(e);
    });
};

const rechazarMiembro = async () => {
    const id = document.getElementById("ID").value;
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const tipoDeDocumento = document.getElementById("tipoDeDocumento").value;
    const nroDeDocumento = document.getElementById("nroDeDocumento").value;
  
    await fetch(`${API_ENDPOINT}/aceptar_miembro`, {
      method: "POST",
      mode: "no-cors",
  
      body: JSON.stringify({
        id,
        nombre,
        apellido,
        tipoDeDocumento,
        nroDeDocumento
      }),
  
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    })
      .then((res) => {
        console.log("OKKKK");
        console.log(res);
        alert(res);
        window.location.href = "./Aceptar_Miembros.html";
      })
      .catch((e) => {
        console.log(e);
      });
  };


function buscarSolicitudes() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("unaSolicitud");
  filter = input.value.toUpperCase();
  table = document.getElementById("listadoSolicitudes");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}



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

