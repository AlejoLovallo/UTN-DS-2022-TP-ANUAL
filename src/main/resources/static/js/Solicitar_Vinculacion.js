const API_ENDPOINT = "http://127.0.0.1:9000";

const solicitar_vinculacion = async () => {
  const organizacion = document.getElementById("Organizacion").value;
  const sector = document.getElementById("Sector").value;
  const idSesion = getCookie("idSesion");

    console.log(idSesion);

  fetch(`${API_ENDPOINT}/enviar_solicitud`, {
    method: "POST",

    body: JSON.stringify({
      organizacion,
      sector,
      idSesion,
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


function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}

-------------------------------------------------------
const sliderOrganizacion = new Vue({
        el: "#appVue",
        data: {
            username: "",
            mascotaSeleccionada: "",
            mascotas: [],
        },
        methods: {
            login: function () {
                //Armar objeto request
                var request = {
                    username: this.username,
                    password: 123,
                    mascotaSeleccionada: this.mascotaSeleccionada
                }
                console.log(request)
                if (request.mascotaSeleccionada == "") {
                    alert("Debes seleccionar una mascota")
                    return;
                }
                //POST al API REST
                //luego puede ser una redirección o mostrar un mensaje
            }
        },
        created() {
            fetch('./organizacionesGet')
                .then(response => response.json())
                .then(response => {
                    this.mascotas = response.data
                })
        }
    })
