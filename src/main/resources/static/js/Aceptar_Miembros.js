const API_ENDPOINT = "http://127.0.0.1:3020";

const aceptarMiembro = async () => {
  const name = document.getElementById("nombre").innerText;
  const surname = document.getElementById("apellido").innerText;
  const typeDoc = document.getElementById("tipoDeDocumento").innerText;
  const dni = document.getElementById("dni").innerText;
  const sector = document.getElementById("nombreSector").innerText;
  const id_sector = document.getElementById("idSector").innerText;


  await fetch(`${API_ENDPOINT}/aceptar_miembro`, {
    method: "POST",

    body: JSON.stringify({
      name,
      surname,
      typeDoc,
      dni,
      sector,
      id_sector,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((res) => {
      console.log("OKKKK");
      console.log(res);
      alert("Miembro aceptado");
      window.location.href = "/sol_miembros";
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

    await fetch(`${API_ENDPOINT}/rechazar_miembro`, {
      method: "POST",

      body: JSON.stringify({
        nombre,
        apellido,
        tipoDeDocumento,
        dni,
        sector,
        id_sector
      }),
  
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    })
      .then((res) => {
        console.log("OKKKK");
        console.log(res);
        alert(res);
        window.location.href = "/sol_miembros";
      })
      .catch((e) => {
        console.log(e);
      });
};

const buscarListaMiembros = async () => {

  return fetch(`${API_ENDPOINT}/solicitudes_miembro`, {
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


buscarListaMiembros();
