const API_ENDPOINT = "http://127.0.0.1:9000";


const formHC = '<h3>Tu huella de carbono para ese perÍodo es: </h3> <br> <p class="valorHC" id="valorHC">${XX}</p> <br> <br> <button type="button" id="myBtn" class="button button-back" onclick="volverAtras()">Volver atrás</button>'


const volverAtras = async () => {
  var valuesCookies = document.cookie.split(';');
  for(var i = 0; i < valuesCookies.length; i++){
    var cookieInfo = valuesCookies[i].split('=');
    if(cookieInfo[0] == 'organizacion')
    {
          window.location.href = "/calcularHTMLorg";
    }
    else if(cookieInfo[0] == 'persona')
    {
          window.location.href = "/calcularHTMLmiembro";
    }
    }
  };


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

  var valuesCookies = document.cookie.split(';');
  for(var i = 0; i < valuesCookies.length; i++){
    var cookieInfo = valuesCookies[i].split('=');
    if(cookieInfo[0] == 'organizacion')
    {
          await fetch(`${API_ENDPOINT}/organizacion/calcularHC`, {
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
    else if(cookieInfo[0] == 'persona')
    {
            await fetch(`${API_ENDPOINT}/miembro/calcularHC`, {
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
            .then((res) => {
                console.log("OKKKK");
                console.log(res);
                $('#seb').css('display','flex');
                $('#modal-container').append(formHC);
                $('#CalcularHC').prop('disabled',true);
                $('#valorHC').html(res.text());
            })
            .catch((e) => {
                console.log(e);
            });
    }
  }

};


