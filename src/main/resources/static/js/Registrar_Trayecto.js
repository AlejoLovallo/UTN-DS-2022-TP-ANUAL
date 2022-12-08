const API_ENDPOINT = "http://127.0.0.1:9000";

const tramoTransportePublico = '<form id="formularioTransportePublico"> <h3>Datos del tramo</h3> <br> <h5>Estacion de salida</h5> <br> <div class="top-row"> <div class="field-wrap"> <label> Numero<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NumeroSalida"/> </div> <div class="field-wrap"> <label> Nombre<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NombreSalida"/> </div> </div> <h5>Estacion de llegada</h5> <div class="top-row"> <div class="field-wrap"> <label> Numero<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NumeroLlegada"/> </div> <div class="field-wrap"> <label> Nombre<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NombreLlegada"/> </div> </div> <br> <br> <div id="TransportePublico"> <h5>Datos del transporte</h5> <br> <div class="top-row"> <div class="field-wrap"> <label> Tipo transporte público<span class="req">*</span> </label> <br> <br> <select class ="select-css" name="TipoTransportePublico" onChange=changetextbox()> <option value="0">Seleccione el tipo de transporte público</option> <option value="1">Tren</option> <option value="2">Colectivo</option> <option value="3">Subte</option> </select> </div> <div class="field-wrap"> <label> Línea<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="Linea"/> </div> </div> </div> <br> <br> <button type="submit" id="GuardarTramo" onclick="guardarTramo()"> Guardar Tramo </button> <button type="button" id="CancelarTramo" onclick="cancelarTramo()"> Cancelar Tramo </button> </form>';

const tramoVehiculoParticular = `<form id="formularioVehiculoParticular"> <h3>Datos del tramo</h3> <br> <h5>Lugar de salida</h5> <br> <div class="top-row"> <div class="field-wrap"> <label> Calle<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="CalleSalida"/> </div> <div class="field-wrap"> <label> Altura<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="AlturaSalida"/> </div> </div> <div class="top-row"> <div class="field-wrap"> <label> Localidad<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="LocalidadSalida"/> </div> <div class="field-wrap"> <label> Municipio<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="MunicipioSalida"/> </div> </div> <div class="top-row"> <div class="field-wrap"> <label> Provincia<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="ProvinciaSalida"/> </div> <div class="field-wrap"> <label> Pais<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="PaisSalida"/> </div> </div> <h5>Lugar de llegada</h5> <br> <div class="top-row"> <div class="field-wrap"> <label> Calle<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="CalleLlegada"/> </div> <div class="field-wrap"> <label> Altura<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="AlturaLlegada"/> </div> </div> <div class="top-row"> 
<div class="field-wrap"> <label> Localidad<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="LocalidadLlegada"/> </div> <div class="field-wrap"> <label> Municipio<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="MunicipioLlegada"/> </div> </div> <div class="top-row"> <div class="field-wrap"> <label> Provincia<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="ProvinciaLlegada"/> </div> <div class="field-wrap"> <label> Pais<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="PaisLlegada"/> </div> </div> <h5>Datos del vehículo</h5> <br> <div id="AgregarVehiculoParticular"> <br> <div class="top-row"> <div class="field-wrap"> <label> Tipo vehículo<span class="req">*</span> </label> <br> <br> <select class ="select-css" name="TipoVehiculo" id="TipoVehiculo"> <option value="0">Seleccione el tipo de vehículo</option> <option value="1">Moto</option> <option value="2">Auto</option> <option value="3">Camioneta</option> <option value="3">Uber</option> <option value="4">Cabify</option> <option value="5">Remis</option> <option value="6">Taxi</option> <option value="7">BiciPie</option> </select> </div> <div class="field-wrap"> <label> Tipo combustible<span class="req">*</span> </label> <br> <br> <select class ="select-css" name="TipoCombustible" id="TipoCombustible"> <option value="0">Seleccione el tipo de combustible</option> <option value="1">GNC</option> <option value="2">Nafta</option> <option value="3">Electrico</option> 
<option value="3">Gasoil</option> <option value="4">NoConsume</option> </select> </div> </div> <div class="top-row"> <div class="field-wrap"> <label> Cantidad de pasajeros<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="CantidadPasajeros"/> </div> </div> <br> <br> </div> <br> <br> <button type="button" id="GuardarTramo" onclick="guardarTramo()"> Guardar Tramo </button> <button type="button" id="CancelarTramo" onclick="cancelarTramo()"> Cancelar Tramo </button> </form>`;

const tramos = [];

const validateRegistrarTrayecto = async () => {
  const organizacion = document.getElementById("Organizacion").value;
  const frecuenciaSemanal = document.getElementById("FrecuenciaSemanal").value;
  const fechaInicio = document.getElementById("FechaInicio").value;
  const fechaFin = document.getElementById("FechaFin").value;

  await fetch(`${API_ENDPOINT}/agregar_trayecto`, {
    method: "POST",
    mode: "no-cors",

    body: JSON.stringify({
      organizacion,
      frecuenciaSemanal,
      fechaInicio,
      fechaFin,
      tramos
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
    $('#seb').css('display','flex');
    $('#modal-container').append(tramoVehiculoParticular);
    $('#agregarTramoVehiculoParticular').prop('disabled',true);
    $('#agregarTramoTransportePublico').prop('disabled',true);
  }
  function AgregarTramoTransportePublico(){
    $('#seb').css('display','flex');
    $('#modal-container').append(tramoTransportePublico);
    $('#agregarTramoTransportePublico').prop('disabled',true);
    $('#agregarTramoVehiculoParticular').prop('disabled',true);
  }

  $('#formularioVehiculoParticular').on('submit',function(event){
    event.preventDefault();
    console.log("algo");
  });

  function guardarTramo(){
    const formElement = document.getElementById("formularioVehiculoParticular");
    const formData = new FormData(formElement);
    const tramo = {};
    for(const inputPair of formData.entries()){
      tramo[inputPair[0]] = inputPair[1];
    }
    console.log(tramo);
    tramos.push(tramo);
    console.log(tramos);
    $('#modal-container').empty();
    $('#seb').css('display','none');
    $('#agregarTramoVehiculoParticular').prop('disabled',false);
    $('#agregarTramoTransportePublico').prop('disabled',false);

  }

  function cancelarTramo(){
    $('#modal-container').empty();
    $('#seb').css('display','none');
    $('#agregarTramoVehiculoParticular').prop('disabled',false);
    $('#agregarTramoTransportePublico').prop('disabled',false);

  }