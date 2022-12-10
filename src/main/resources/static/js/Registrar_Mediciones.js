
const registrarMediciones = async () => {
    const path = document.getElementById("Path").value;

    await fetch(`${API_ENDPOINT}/cargarMediciones`, {
        method: "POST",
        mode: "no-cors",
    
        body: JSON.stringify({
            path,
        }),
    
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      })
        .then((res) => {
          console.log("OKKKK");
          console.log(res);
          window.location.href = "./Menu_organizacion.html";
        })
        .catch((e) => {
          console.log(e);
        });
}
