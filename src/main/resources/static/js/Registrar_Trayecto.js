const API_ENDPOINT = "http://localhost:9000";

const tramoTransportePublico = '<form id="formularioTransportePublico"> <h3>Datos del tramo</h3> <br> <h5>Estacion de salida</h5> <br> <div class="top-row"> <div class="field-wrap"> <label> Numero<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NumeroSalida"/> </div> <div class="field-wrap"> <label> Nombre<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NombreSalida"/> </div> </div> <h5>Estacion de llegada</h5> <div class="top-row"> <div class="field-wrap"> <label> Numero<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NumeroLlegada"/> </div> <div class="field-wrap"> <label> Nombre<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="NombreLlegada"/> </div> </div> <br> <br> <div id="TransportePublico"> <h5>Datos del transporte</h5> <br> <div class="top-row"> <div class="field-wrap"> <label> Tipo transporte público<span class="req">*</span> </label> <br> <br> <select class ="select-css" name="TipoTransportePublico" onChange=changetextbox()> <option value="0">Seleccione el tipo de transporte público</option> <option value="1">Tren</option> <option value="2">Colectivo</option> <option value="3">Subte</option> </select> </div> <div class="field-wrap"> <label> Línea<span class="req">*</span> </label> <br> <br> <input type="text" required autocomplete="off" name="Linea"/> </div> </div> </div> <br> <br> <button type="submit" id="GuardarTramo" onclick="guardarTramoTransportePublico()"> Guardar Tramo </button> <button type="button" id="CancelarTramo" onclick="cancelarTramo()"> Cancelar Tramo </button> </form>';

const tramoVehiculoParticular = `<formid="formularioVehiculoParticular"><h3>Datosdeltramo</h3><br><h5>Lugardesalida</h5><br><divclass="top-row"><divclass="field-wrap"><label>Tipodedirección<spanclass="req">*</span></label><br><br><selectclass="select-css"name="TipoVehiculo"id="TipoVehiculo"><optionvalue="0">Seleccioneeltipodedirección</option><optionvalue="Vivienda">Vivienda</option><optionvalue="Trabajo">Trabajo</option><optionvalue="Otro">Otro</option></select></div></div><divclass="top-row"><divclass="field-wrap"><label>Calle<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="CalleSalida"/></div><divclass="field-wrap"><label>Altura<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="AlturaSalida"/></div></div><divclass="top-row"><divclass="field-wrap"><label>Localidad<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="LocalidadSalida"/></div><divclass="field-wrap"><label>Municipio<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="MunicipioSalida"/></div></div><divclass="top-row"><divclass="field-wrap"><label>Provincia<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="ProvinciaSalida"/></div><divclass="field-wrap"><label>Pais<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="PaisSalida"/></div></div><h5>Lugardellegada</h5><br><divclass="top-row"><divclass="field-wrap"><label>Tipodedirección<spanclass="req">*</span></label><br><br><selectclass="select-css"name="TipoVehiculo"id="TipoVehiculo"><optionvalue="0">Seleccioneeltipodedirección</option><optionvalue="Vivienda">Vivienda</option>
<optionvalue="Trabajo">Trabajo</option><optionvalue="Otro">Otro</option></select></div></div><divclass="top-row"><divclass="field-wrap"><label>Calle<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="CalleLlegada"/></div><divclass="field-wrap"><label>Altura<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="AlturaLlegada"/></div></div><divclass="top-row"><divclass="field-wrap"><label>Localidad<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="LocalidadLlegada"/></div><divclass="field-wrap"><label>Municipio<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="MunicipioLlegada"/></div></div><divclass="top-row"><divclass="field-wrap"><label>Provincia<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="ProvinciaLlegada"/></div><divclass="field-wrap"><label>Pais<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="PaisLlegada"/></div></div><h5>Datosdelvehículo</h5><br><divid="AgregarVehiculoParticular"><br><divclass="top-row"><divclass="field-wrap"><label>Tipovehículo<spanclass="req">*</span></label><br><br><selectclass="select-css"name="TipoVehiculo"id="TipoVehiculo"><optionvalue="0">Seleccioneeltipodevehículo</option><optionvalue="Moto">Moto</option><optionvalue="Auto">Auto</option><optionvalue="Camioneta">Camioneta</option><optionvalue="Uber">Uber</option><optionvalue="Cabify">Cabify</option><optionvalue="Remis">Remis</option><optionvalue="Taxi">Taxi</option><optionvalue="BiciPie">BiciPie</option></select></div><divclass="field-wrap"><label>Tipocombustible<spanclass="req">*</span></label><br>
<br><selectclass="select-css"name="TipoCombustible"id="TipoCombustible"><optionvalue="0">Seleccioneeltipodecombustible</option><optionvalue="GNC">GNC</option><optionvalue="Nafta">Nafta</option><optionvalue="Electrico">Electrico</option><optionvalue="Gasoil">Gasoil</option><optionvalue="NoConsume">NoConsume</option></select></div></div><divclass="top-row"><divclass="field-wrap"><label>ConsumoporKm<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="ConsumoPorKm"/></div><divclass="field-wrap"><label>Cantidaddepasajeros<spanclass="req">*</span></label><br><br><inputtype="text"requiredautocomplete="off"name="CantidadPasajeros"/></div></div><br><br></div><br><br><buttontype="button"id="GuardarTramo"onclick="guardarTramoVehiculoParticular()">GuardarTramo</button><buttontype="button"id="CancelarTramo"onclick="cancelarTramo()">CancelarTramo</button></form>`

const tramos = [];

const validateRegistrarTrayecto = async () => {
  const organizacion = document.getElementById("Organizacion").value;
  const frecuenciaSemanal = document.getElementById("FrecuenciaSemanal").value;
  const fechaInicio = document.getElementById("FechaInicio").value;
  const fechaFin = document.getElementById("FechaFin").value;

  await fetch(`${API_ENDPOINT}/agregar_trayecto`, {
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
      alert(res);
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