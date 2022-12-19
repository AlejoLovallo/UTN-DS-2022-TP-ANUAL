function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
};



const logout = async () => {

 await fetch(`./cerrar_sesion`,{
    method:"GET",

    headers: {
      'Content-Type': 'application/json'
    }
 })
 .then(res => res.json())
 .then(res =>{
      console.log(res.message);
      alert(res.message);
      delete_cookie("idSesion");

      window.location.replace("./");
 })
  .catch((error) => {
    console.log(error)
  });
};

var delete_cookie = function(name) {
document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};

const volverAlInicio = async () => {

window.location.replace("./")

};
