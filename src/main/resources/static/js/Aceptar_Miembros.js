const API_ENDPOINT = "http://127.0.0.1:9000";

const aceptarMiembro = async () => {
  const id = document.getElementById("ID").value;
  const nombre = document.getElementById("nombre").value;
  const apellido = document.getElementById("apellido").value;
  const tipoDeDocumento = document.getElementById("tipoDeDocumento").value;
  const nroDeDocumento = document.getElementById("nroDeDocumento").value;
  

  await fetch(`${API_ENDPOINT}/aceptar_miembro`, {
    method: "POST",
    mode: "no-cors",

    body: JSON.stringify({
      id,
      nombre,
      apellido,
      tipoDeDocumento,
      nroDeDocumento
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((res) => {
      console.log("OKKKK");
      console.log(res);
      alert(res);
      window.location.href = "./Aceptar_Miembros.html";
    })
    .catch((e) => {
      console.log(e);
    });
};

const rechazarMiembro = async () => {
    const id = document.getElementById("ID").value;
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const tipoDeDocumento = document.getElementById("tipoDeDocumento").value;
    const nroDeDocumento = document.getElementById("nroDeDocumento").value;
  
    await fetch(`${API_ENDPOINT}/aceptar_miembro`, {
      method: "POST",
      mode: "no-cors",
  
      body: JSON.stringify({
        id,
        nombre,
        apellido,
        tipoDeDocumento,
        nroDeDocumento
      }),
  
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    })
      .then((res) => {
        console.log("OKKKK");
        console.log(res);
        alert(res);
        window.location.href = "./Aceptar_Miembros.html";
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
            <td id="ID">${miembro.ID}</td>
            <td id="nombre">${miembro.nombre}</td>
            <td id="apellido">${miembro.apellido}</td>
            <td id="tipoDeDocumento">${miembro.tipoDeDocumento}</td>
            <td id="nroDeDocumento">${miembro.nroDeDocumento}</td>
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