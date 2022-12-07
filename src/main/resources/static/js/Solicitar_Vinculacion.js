const API_ENDPOINT = "http://127.0.0.1:9000";

const solicitar_vinculacion = async () => {
  const organizacion = document.getElementById("Organizacion").value;
  const sector = document.getElementById("Sector").value;

  await fetch(`${API_ENDPOINT}/menuEnviarSolicitud`, {
    method: "POST",
    mode: "no-cors",

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
      alert(res);
      window.location.href = "./Menu_miembro.html";
    })
    .catch((e) => {
      console.log(e);
    });
};