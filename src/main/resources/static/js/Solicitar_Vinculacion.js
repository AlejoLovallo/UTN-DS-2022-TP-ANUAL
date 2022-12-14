const API_ENDPOINT = "http://127.0.0.1:3020";

const solicitar_vinculacion = async () => {
  const organizacion = document.getElementById("Organizacion").value;
  const sector = document.getElementById("Sector").value;

  fetch(`${API_ENDPOINT}/enviar_solicitud`, {
    method: "POST",

    body: JSON.stringify({
      organizacion,
      sector
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