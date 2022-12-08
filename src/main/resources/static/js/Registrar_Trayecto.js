const API_ENDPOINT = "http://127.0.0.1:9000";


const validateRegistrarTrayecto = async () => {
  const organizacion = document.getElementById("Organizacion").value;
  const frecuenciaSemanal = document.getElementById("FrecuenciaSemanal").value;
  const fechaInicio = document.getElementById("FechaInicio").value;
  const fechaFin = document.getElementById("FechaFin").value;
  const calleSalida = document.getElementById("CalleSalida").value;
  const alturaSalida = document.getElementById("AlturaSalida").value;
  const localidadSalida = document.getElementById("LocalidadSalida").value;
  const municipioSalida = document.getElementById("MunicipioSalida").value;
  const provinciaSalida = document.getElementById("ProvinciaSalida").value;
  const paisSalida = document.getElementById("PaisSalida").value;
  const calleLlegada = document.getElementById("CalleLlegada").value;
  const alturaLlegada = document.getElementById("AlturaLlegada").value;
  const localidadLlegada = document.getElementById("LocalidadLlegada").value;
  const municipioLlegada = document.getElementById("MunicipioLlegada").value;
  const provinciaLlegada = document.getElementById("ProvinciaLlegada").value;
  const paisLlegada = document.getElementById("PaisLlegada").value;
  const tipoVehiculo = document.getElementById("TipoVehiculo").value;
  const tipoCombustible = document.getElementById("TipoCombustible").value;
  const cantidadPasajeros = document.getElementById("CantidadPasajeros").value;
  const tipoTransportePublico = document.getElementById("TipoTransportePublico").value;
  const linea = document.getElementById("Linea").value;

  await fetch(`${API_ENDPOINT}/agregar_trayecto`, {
    method: "POST",
    mode: "no-cors",

    body: JSON.stringify({
      organizacion,
      frecuenciaSemanal,
      fechaInicio,
      fechaFin,
      
      calleSalida,
      alturaSalida,
      localidadSalida,
      municipioSalida,
      provinciaSalida,
      paisSalida,
    
      calleLlegada,
      alturaLlegada,
      localidadLlegada,
      municipioLlegada,
      provinciaLlegada,
      paisLlegada,

      tipoVehiculo,
      tipoCombustible,
      cantidadPasajeros,
      tipoTransportePublico,
      linea
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((res) => {
      console.log("OKKKK");
      console.log(res);
      alert(res);
      window.location.href = "./Menu_miembro.html";
    })
    .catch((e) => {
      console.log(e);
    });
};


$('.form').find('input, textarea').on('keyup blur focus', function (e) {

    var $this = $(this),
      label = $this.prev('label');
  
    if (e.type === 'keyup') {
      if ($this.val() === '') {
        label.removeClass('active highlight');
      } else {
        label.addClass('active highlight');
      }
    } else if (e.type === 'blur') {
      if ($this.val() === '') {
        label.removeClass('active highlight');
      } else {
        label.removeClass('highlight');
      }
    } else if (e.type === 'focus') {
  
      if ($this.val() === '') {
        label.removeClass('highlight');
      }
      else if ($this.val() !== '') {
        label.addClass('highlight');
      }
    }
  
  });
  
  $('.tab a').on('click', function (e) {
  
    e.preventDefault();
  
    $(this).parent().addClass('active');
    $(this).parent().siblings().removeClass('active');
  
    target = $(this).attr('href');
  
    $('.tab-content > div').not(target).hide();
  
    $(target).fadeIn(600);
  
  });
  
  $(function () {
    $('a.page-scroll').bind('click', function (event) {
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
  $('.navbar-collapse ul li a').click(function () {
    $('.navbar-toggle:visible').click();
  });
  
  var modal = document.getElementById("myModal");
  
  // Get the button that opens the modal
  var btn = document.getElementById("myBtn");
  
  // Get the <span> element that closes the modal
  var span = document.getElementsByClassName("close")[0];
  
  // When the user clicks the button, open the modal 
  btn.onclick = function () {
    modal.style.display = "block";
  }
  
  // When the user clicks on <span> (x), close the modal
  span.onclick = function () {
    modal.style.display = "none";
    window.location.href = '/confirmar_asociacion_prod_cate'
  }
  
  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
    }
  } 

  function AgregarTramoVehiculoParticular(){
    //var bloque = '<div id="vehiculo"> <form class="" style=""> <h5>Datos del vehículo</h5> <br> <div class="top-row"> <div class="field-wrap"> <label> Tipo vehículo<span class="req">*</span></label><br><br><select class ="select-css" name="seleccionDeTipoVehiculo" id="TipoVehiculo" onChange=changetextbox()><option value="0">Seleccione el tipo de vehículo</option><option value="1">Moto</option><option value="2">Auto</option><option value="3">Camioneta</option><option value="3">Uber</option><option value="4">Cabify</option><option value="5">Remis</option><option value="6">Taxi</option><option value="7">BiciPie</option></select></div><div class="field-wrap"><label>Tipo combustible<span class="req">*</span></label><br><br><select class ="select-css" name="seleccionDeTipoCombustible" id="TipoCombustible" onChange=changetextbox()><option value="0">Seleccione el tipo de combustible</option><option value="1">GNC</option><option value="2">Nafta</option><option value="3">Electrico</option><option value="3">Gasoil</option><option value="4">NoConsume</option></select></div></div><div class="top-row"><div class="field-wrap"><label>Cantidad de pasajeros<span class="req">*</span></label><br><br></br><input type="text" required autocomplete="off" id="CantidadPasajeros"/></div></div></form> </div>';
    var bloque = document.getElementById('#TramoVehiculoParticular')
    $('#formulario').append(bloque);
  }
  function AgregarTramoTransportePublico(){
    var bloque = '<div id="text"> hola </div>';
    $('#append').append(bloque);
  }


  function AgregarTramo()
  {
  
      //clone the first row
      var newTramo =  $('#Tramo').clone();
  
      $('#Tramo').after(newRow);
  
      return false;
  }