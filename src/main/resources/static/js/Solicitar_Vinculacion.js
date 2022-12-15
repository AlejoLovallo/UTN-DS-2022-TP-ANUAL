const API_ENDPOINT = "http://127.0.0.1:9000";

src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"

const solicitar_vinculacion = async () => {
  const organizacion = document.getElementById("Organizacion").value;
  const sector = document.getElementById("Sector").value;
  const idSesion = getCookie("idSesion");

    console.log(idSesion);

  fetch(`${API_ENDPOINT}/enviar_solicitud`, {
    method: "POST",

    body: JSON.stringify({
      organizacion,
      sector,
      idSesion,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((res) => {
      console.log("OKKKK");
      console.log(res);
      alert("Solicitud enviada");
      window.location.href = "/menu_miembro";
    })
    .catch((e) => {
      console.log(e);
    });
};


function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
};

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