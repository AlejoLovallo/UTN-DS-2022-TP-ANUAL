const API_ENDPOINT = "http://127.0.0.1:9000";

const generarReporte = async =>{

    const mesDesde = document.getElementById("mesDesde").value;
    const añoDesde = document.getElementById("añoDesde").value;
    const mesHasta = document.getElementById("mesHasta").value;
    const añoHasta = document.getElementById("añoHasta").value;
    const tipo = document.getElementById("tipo").value;

    fetch(`${API_ENDPOINT}/generar_reporte`, {
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
            window.location.replace(`${API_ENDPOINT}/reportes`);
           })
          .catch((error) => {
            console.log(error)
          });

}