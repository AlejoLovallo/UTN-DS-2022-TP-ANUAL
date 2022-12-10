const API_ENDPOINT = "http://127.0.0.1:9000";


const formHC = '<h3>Tu huella de carbono para ese perÍodo es: </h3> <br> <p class="valorHC" id="valorHC">XX</p> <br> <br> <button type="submit" id="myBtn" class="button button-back" onclick="volverAtras()">Volver atrás</button>'


const volverAtras = async () => {
  window.location.href = "./Calcular_HC.html";
};

const calcularHC = async () => {
  const mesDesde = document.getElementById("MesDesde").value;
  const añoDesde = document.getElementById("AñoDesde").value;
  const mesHasta = document.getElementById("MesHasta").value;
  const añoHasta = document.getElementById("AñoHasta").value;

  await fetch(`${API_ENDPOINT}/menu_login`, {
    method: "POST",
    mode: "no-cors",

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

};


