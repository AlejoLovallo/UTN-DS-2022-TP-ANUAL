const API_ENDPOINT = "http://147.182.167.98:3020";

//TODO: revisar esta funcion
const registrarMediciones = async () => {
  const path = document.getElementById("Path");
  const file_upload = new FormData();
  file_upload.append("file", path.files[0]);

  await fetch(`${API_ENDPOINT}/organizacion/cargar_mediciones`, {
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
};
