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

var listDiv = document.getElementById('listado_soli');
var ul = document.createElement('ul');
ul.appendChild(listDiv);
for(var i = 0; i < data.list.length; ++i) {
  var li = document.createElement('li');
  li.innerHTML = <tr>
  <td id='I'></td>
  <td id="nombre"></td>
  <td id="apellido"></td>
  <td id="tipoDocumento"></td>
  <td id="nroDocumento"></td>
  <td><button type="submit" class="button button-accept" onclick="aceptarMiembro()"> Aceptar </button></td>
  <td><button type="submit" class="button button-deny" onclick="rechazarMiembro()"> Rechazar </button></td>
</tr>;
  li.appendChild(data.list[i].puntata);
  ul.appendChild(li); 
                             
}

function addItem(){

    var li = document.createElement("LI");
    li.innerHTML = "<br>"

    document.getElementById("formulario").appendChild(li);
}

function buscarIngreso() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("unIngreso");
  filter = input.value.toUpperCase();
  table = document.getElementById("tablaIngreso");
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

