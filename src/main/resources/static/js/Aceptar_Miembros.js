const API_ENDPOINT = "http://localhost:9000";


function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}

const aceptarMiembro = async () => {
  const name = document.getElementById("nombre").innerText;
  const surname = document.getElementById("apellido").innerText;
  const typeDoc = document.getElementById("tipoDeDocumento").innerText;
  const dni = document.getElementById("dni").innerText;
  const sector = document.getElementById("nombreSector").innerText;
  const id_sector = document.getElementById("idSector").innerText;
  const idSesion = getCookie("idSesion");


  await fetch(`./aceptar_miembro`, {
    method: "POST",

    body: JSON.stringify({
      name,
      surname,
      typeDoc,
      dni,
      sector,
      id_sector,
      idSesion,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((res) => {
      console.log("OKKKK");
      console.log(res);
      alert("Miembro aceptado");
      window.location.href = "./sol_miembros";
    })
    .catch((e) => {
      console.log(e);
    });
};

const rechazarMiembro = async () => {
    const nombre = document.getElementById("nombre").innerText;
    const apellido = document.getElementById("apellido").innerText;
    const tipoDeDocumento = document.getElementById("tipoDeDocumento").innerText;
    const dni = document.getElementById("dni").innerText;
    const sector = document.getElementById("nombreSector").innerText;
    const id_sector = document.getElementById("idSector").innerText;
      const idSesion = getCookie("idSesion");

    await fetch(`./rechazar_miembro`, {
      method: "POST",

      body: JSON.stringify({
        nombre,
        apellido,
        tipoDeDocumento,
        dni,
        sector,
        id_sector,
        idSesion
      }),

      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    })
      .then((res) => {
        console.log("OKKKK");
        console.log(res);
        alert("miembro rechazado");
        window.location.href = "./";
      })
      .catch((e) => {
        console.log(e);
      });
};

const buscarListaMiembros = async () => {

  return fetch(`./solicitudes_miembro`, {
    method: "GET",
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      for(let i = 0;i < data.length;i++){
        const miembro = data[i];
        const item = `<tr>
          <td id="nombre">${miembro.persona.nombre}</td>
          <td id="apellido">${miembro.persona.apellido}</td>
          <td id="tipoDeDocumento">${miembro.persona.tipoDeDocumento}</td>
          <td id="dni">${miembro.persona.dni}</td>
          <td id="idSector">${miembro.sector.id}</td>
          <td id="nombreSector">${miembro.sector.nombreSector}</td>
          <td><button type="submit" class="button button-accept" onclick="aceptarMiembro()"> Aceptar </button></td>
          <td><button type="submit" class="button button-deny" onclick="rechazarMiembro()"> Rechazar </button></td>
          </tr>`;
          $('#ListaMiembrosBody').append(item);
      }
      console.log("OKKKK");
    })
    .catch((e) => {
      console.log(e);
    });
};

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

buscarListaMiembros();
