const API_ENDPOINT = "http://localhost:9000";

const tramoTransportePublico = '<form id="formularioTransportePublico"> <h3>Datos del tramo</h3> <br> <div id="TransportePublico"> <h5>Datos del transporte</h5> <br> <div class="top-row"> <div class="field-wrap"> <label> Tipo transporte público<span class="req">*</span> </label> <br> <br> <select class ="select-css" name="TipoTransportePublico" onChange=changetextbox()> <option value="0">Seleccione el tipo de transporte público</option> <option value="Tren">Tren</option> <option value="Colectivo">Colectivo</option> <option value="Subte">Subte</option> </select> </div> <div class="field-wrap"> <label> Línea<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="Linea"/> </div> </div> </div> <br> <br> <div class="top-row">  <div class="field-wrap"> <h5>Estacion de salida</h5> <label> Nombre<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NombreSalida"/> </div> <div class="field-wrap"> <h5>Estacion de llegada</h5> <label> Nombre<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NombreLlegada"/> </div> </div> <br> <br> <button type="submit" id="GuardarTramo" onclick="guardarTramoTransportePublico()"> Guardar Tramo </button> <button type="button" id="CancelarTramo" onclick="cancelarTramo()"> Cancelar Tramo </button> </form>';

const tramoVehiculoParticular = `<form id="formularioVehiculoParticular"><h3>Datos del tramo</h3><br><h5>Lugar de salida</h5><br><div class="top-row"><div class="field-wrap"><label>Tipo de dirección<span class="req">*</span></label><br><br><select class="select-css" name="TipoDireccionSalida" id="TipoDireccionSalida"><option value="0">Seleccione el tipo de dirección</option><option value="Vivienda">Vivienda</option><option value="Trabajo">Trabajo</option><option value="Otro">Otro</option></select></div></div><div class="top-row"><div class="field-wrap"><label>Calle<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="CalleSalida"/></div><div class="field-wrap"><label>Altura<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="AlturaSalida"/></div></div><div class="top-row"><div class="field-wrap"><label>Localidad<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="LocalidadSalida"/></div><div class="field-wrap"><label>Municipio<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="MunicipioSalida"/></div></div><div class="top-row"><div class="field-wrap"><label>Provincia<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="ProvinciaSalida"/></div><div class="field-wrap"><label>Pais<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="PaisSalida"/></div></div><h5>Lugar de llegada</h5><br><div class="top-row"><div class="field-wrap"><label>Tipo de dirección<span class="req">*</span></label><br><br><select class="select-css" name="TipoDireccionLlegada" id="TipoDireccionLlegada"><option value="0">Seleccione el tipo de dirección</option><option value="Vivienda">Vivienda</option>
<option value="Trabajo">Trabajo</option><option value="Otro">Otro</option></select></div></div><div class="top-row"><div class="field-wrap"><label>Calle<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="CalleLlegada"/></div><div class="field-wrap"><label>Altura<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="AlturaLlegada"/></div></div><div class="top-row"><div class="field-wrap"><label>Localidad<span class="req">*</span></label><br><br><input type="text" required autocomplete="off" name="LocalidadLlegada"/></div><div class="field-wrap"><label>Municipio<span class="req">*</span></label><br><br><input type="text" required autocomplete="off" name="MunicipioLlegada"/></div></div><div class="top-row"><div class="field-wrap"><label>Provincia<span class="req">*</span></label><br><br><input type="text" required autocomplete="off" name="ProvinciaLlegada"/></div><div class="field-wrap"><label>Pais<span class="req">*</span></label><br><br><input type="text" required autocomplete="off" name="PaisLlegada"/></div></div><h5>Datos del vehículo</h5><br><div id="AgregarVehiculoParticular"><br><div class="top-row"><div class="field-wrap"><label>Tipo vehículo<span class="req">*</span></label><br><br><select class="select-css" name="TipoVehiculo" id="TipoVehiculo"><option value="0">Seleccione el tipo de vehículo</option><option value="Moto">Moto</option><option value="Auto">Auto</option><option value="Camioneta">Camioneta</option><option value="Uber">Uber</option><option value="Cabify">Cabify</option><option value="Remis">Remis</option><option value="Taxi">Taxi</option><option value="BiciPie">BiciPie</option></select></div><div class="field-wrap"><label>Tipo combustible<span class="req">*</span></label><br>
<br><select class="select-css" name="TipoCombustible" id="TipoCombustible"><option value="0">Seleccione el tipo de combustible</option><option value="GNC">GNC</option><option value="Nafta">Nafta</option><option value="Electrico">Electrico</option><option value="Gasoil">Gasoil</option><option value="NoConsume">NoConsume</option></select></div></div><div class="top-row"><div class="field-wrap"><label>Consumo por Km<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="ConsumoPorKm"/></div><div class="field-wrap"><label>Cantidad de pasajeros<span class="req">*</span></label><br><br><input type="text"required autocomplete="off"name="CantidadPasajeros"/></div></div><br><br></div><br><br><button type="button"id="GuardarTramo"onclick="guardarTramoVehiculoParticular()">Guardar Tramo</button><button type="button"id="CancelarTramo"onclick="cancelarTramo()">Cancelar Tramo</button></form>`;

const tramos = [];

const validateRegistrarTrayecto = async () => {
  const organizacion = document.getElementById("Organizacion").value;
  const frecuenciaSemanal = document.getElementById("FrecuenciaSemanal").value;
  const fechaInicio = document.getElementById("FechaInicio").value;
  const fechaFin = document.getElementById("FechaFin").value;

  await fetch(`./agregar_trayecto`, {
    method: "POST",

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
      alert("Se registro el trayecto correctamente");
      window.location.href = "/menu_miembro";
    })
    .catch((e) => {
      console.log(e);
    });
};

/*
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
  } */

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

  function guardarTramoVehiculoParticular(){
    const formElement = document.getElementById("formularioVehiculoParticular");
    const formData = new FormData(formElement);
    const tramo = {};
    for(const inputPair of formData.entries()){
      tramo[inputPair[0]] = inputPair[1];
    }
    tramo.tipo = "vehiculoParticular";
    console.log(tramo);
    tramos.push(tramo);
    console.log(tramos);
    $('#modal-container').empty();
    $('#seb').css('display','none');
    $('#agregarTramoVehiculoParticular').prop('disabled',false);
    $('#agregarTramoTransportePublico').prop('disabled',false);

  }

  function guardarTramoTransportePublico(){
    const formElement = document.getElementById("formularioTransportePublico");
    const formData = new FormData(formElement);
    const tramo = {};
    for(const inputPair of formData.entries()){
      tramo[inputPair[0]] = inputPair[1];
    }

    tramo.tipo = "transportePublico";
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

  const logout = async () => {

     await fetch(`./cerrar_sesion`,{
        method:"GET",

        headers: {
          'Content-Type': 'application/json'
        }
     })
     .then(res => res.json())
     .then(res =>{
          console.log(res.message);
          alert(res.message);
          delete_cookie("idSesion");

          window.location.replace("./");
     })
      .catch((error) => {
        console.log(error)
      });
  };

  var delete_cookie = function(name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
  };

  const volverAlInicio = async () => {

    window.location.replace("./")

  };
