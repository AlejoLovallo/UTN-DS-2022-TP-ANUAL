const API_ENDPOINT = "http://127.0.0.1:9000";

//TODO: revisar esta funcion
const registrarMediciones = async () => {
    const path = document.getElementById("Path");
    const file_upload = new FormData();
    file_upload.append("file", path.files[0]);

    await fetch(`./organizacion/cargar_mediciones`, {
        method: "POST",
    
        body: file_upload,

      })
        .then((res) => {
          console.log("OKKKK");
          console.log(res);
          alert("carga exitosa");
          window.location.href = "/menu_organizacion";
        })
        .catch((e) => {
          console.log(e);
        });
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