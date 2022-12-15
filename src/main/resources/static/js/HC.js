const API_ENDPOINT = "http://127.0.0.1:9000";

const volverAtras = async () => {
  var valuesCookies = document.cookie.split(';');
  for(var i = 0; i < valuesCookies.length; i++){
    var cookieInfo = valuesCookies[i].split('=');
    if(cookieInfo[0] == 'organizacion')
    {
          window.location.href = "/organizacion/calcularHC";
    }
    else if(cookieInfo[0] == 'persona')
    {
          window.location.href = "/miembro/calcularHC";
    }
    }
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
