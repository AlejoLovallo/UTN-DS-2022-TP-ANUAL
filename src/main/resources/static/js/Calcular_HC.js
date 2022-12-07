const API_ENDPOINT = "http://127.0.0.1:9000";

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
      alert(res);
      $v1 = $_POST[res];
      window.location.href = "./HC.html";
    })
    .catch((e) => {
      console.log(e);
    });
};