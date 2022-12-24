const pantallaSolicitudes = async () => {
        window.location.href = "./Aceptar_Miembros.html";
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
