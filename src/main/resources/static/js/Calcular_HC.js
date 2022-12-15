const API_ENDPOINT = "http://localhost:9000";


const formHC = '<h3>Tu huella de carbono para ese perÍodo es: </h3> <br> <p class="valorHC" id="valorHC">${XX}</p> <br> <br> <button type="button" id="myBtn" class="button button-back" onclick="volverAtras()">Volver atrás</button>'


const volverAtras = async () => {
     window.location.href = "./";
  };

const volverMenu = async () => {
    window.location.href = "./";
  };

function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}

const calcularHC = async () => {
  const mesDesde = document.getElementById("MesDesde").value;
  const añoDesde = document.getElementById("AñoDesde").value;
  const mesHasta = document.getElementById("MesHasta").value;
  const añoHasta = document.getElementById("AñoHasta").value;

    console.log(mesDesde);
    console.log(mesHasta);
    console.log(añoDesde);
    console.log(añoHasta);
    console.log(document.cookie);

    await fetch(`./pedidoMenuCalcularHC`, {
        method: "GET",
    }).then((response) => response.json())
    .then((response) => {
        console.log(response);
        console.log(response.tipoUsuario);
        if(response.tipoUsuario == "organizacion"){
                  fetch(`$./organizacion/calcularHC`, {
                    method: "POST",

                    body: JSON.stringify({
                        mesDesde,
                        añoDesde,
                        mesHasta,
                        añoHasta
                    }),

                    headers: {
                      "Content-type": "application/json; charset=UTF-8",
                    },
                  }).then((response) => response.json())
                      .then((data) => {
                      //console.log("OKKKK");
                      //console.log(data);
                      //console.log(data.resultado);
                      //console.log(data.text());
                      //console.log(response.message);
                      console.log(data);
                      $('#seb').css('display','flex');
                      $('#modal-container').append(formHC);
                      $('#CalcularHC').prop('disabled',true);
                      $('#valorHC').html(data.resultado);
                    })
                    .catch((e) => {
                      console.log(e);
                    });
        }
        else if(response.tipoUsuario == "persona"){
                        fetch(`./miembro/calcularHC`, {
                        method: "POST",

                        body: JSON.stringify({
                           mesDesde,
                           añoDesde,
                           mesHasta,
                           añoHasta
                        }),

                        headers: {
                           "Content-type": "application/json; charset=UTF-8",
                        },
                        })
                        .then((response) => response.json())
                        .then((data) => {
                            console.log("OKKKK");
                            console.log(data);
                            $('#seb').css('display','flex');
                            $('#modal-container').append(formHC);
                            $('#CalcularHC').prop('disabled',true);
                            $('#valorHC').html(data.resultado);
                        })
                        .catch((e) => {
                            console.log(e);
                        });
        }
    })
/*
  var valuesCookies = document.cookie.split(';');
  for(var i = 0; i < valuesCookies.length; i++){
    var cookieInfo = valuesCookies[i].split('=');
    console.log(cookieInfo[0]);
    if(cookieInfo[0].trim() == 'organizacion')
    {
          await fetch(`./organizacion/calcularHC`, {
            method: "POST",

            body: JSON.stringify({
                mesDesde,
                añoDesde,
                mesHasta,
                añoHasta
            }),

            headers: {
              "Content-type": "application/json; charset=UTF-8",
            },
          }).then((response) => response.json())
              .then((data) => {
              //console.log("OKKKK");
              //console.log(data);
              //console.log(data.resultado);
              //console.log(data.text());
              //console.log(response.message);
              console.log(data);
              $('#seb').css('display','flex');
              $('#modal-container').append(formHC);
              $('#CalcularHC').prop('disabled',true);
              $('#valorHC').html(data.resultado);
            })
            .catch((e) => {
              console.log(e);
            });
    }
    else if(cookieInfo[0].trim() == 'persona')
    {
            await fetch(`./miembro/calcularHC`, {
            method: "POST",

            body: JSON.stringify({
               mesDesde,
               añoDesde,
               mesHasta,
               añoHasta
            }),

            headers: {
               "Content-type": "application/json; charset=UTF-8",
            },
            })
            .then((response) => response.json())
            .then((data) => {
                console.log("OKKKK");
                console.log(data);
                $('#seb').css('display','flex');
                $('#modal-container').append(formHC);
                $('#CalcularHC').prop('disabled',true);
                $('#valorHC').html(data.resultado);
            })
            .catch((e) => {
                console.log(e);
            });
    }*/
  }

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

const volverAlInicio = async () => {

  window.location.replace("./")

};


