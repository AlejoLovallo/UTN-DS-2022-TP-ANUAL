const API_ENDPOINT = "http://127.0.0.1:9000";

const volverAtras = async () => {
        window.location.href = "./Calcular_HC.html";
  };

fetch(`${API_ENDPOINT}/menu_login`, {
method: "GET",
mode: "no-cors",

});

fetch(`${API_ENDPOINT}/menu_login` + new URLSearchParams({
    mesDesde: 'value',
    añoDesde: 'value',
    mesHasta: 'value',
    añoHasta: 'value',
}))
