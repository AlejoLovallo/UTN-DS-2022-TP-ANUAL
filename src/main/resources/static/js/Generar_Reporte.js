const API_ENDPOINT = "http://127.0.0.1:9000";

const generarReporte = async =>{

    const mesDesde = document.getElementById("mesDesde").value;
    const añoDesde = document.getElementById("añoDesde").value;
    const mesHasta = document.getElementById("mesHasta").value;
    const añoHasta = document.getElementById("añoHasta").value;
    const tipo = document.getElementById("tipo").value;

    fetch(`./generar_reporte`, {
     method: "POST",

        body: JSON.stringify({
            mesDesde,
            añoDesde,
            mesHasta,
            añoHasta,
            tipo,
        }),

        headers: {
         "Content-type": "application/json; charset=UTF-8",
        },
    })    .then(res => res.json())
          .then(res => {
            console.log(res);
            console.log(res.message);
            alert("reporte generado");
            window.location.replace(`./reportes`);
           })
          .catch((error) => {
            console.log(error)
          });

}

const logout = async () => {

  delete_cookie("idSesion")

  window.location.replace("./")
   // await fetch(`./cerrar_sesion`, {
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
