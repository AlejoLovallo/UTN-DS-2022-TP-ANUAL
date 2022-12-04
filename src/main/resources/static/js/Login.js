let attemptSignIn = 3;

const validateSignIn = async () => {
  let username = document.getElementById("NombreUsuarioSignIn").value;
  let password = document.getElementById("PasswordSignIn").value;

  const res = await fetch("http://127.0.0.1:9000/menu_login", {
    method: "POST",

    body: JSON.stringify({
      username,
      password,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  });

  const data = await res.json();

  if (!data) {
    alert("Ha ocurrido un error. Vuelve a intentarlo");
    return false;
  } else {
    window.location.href = "/menu_logueado";
    return true;
  }

  return false;
};

const validateMemberCreation = async () => {};

const validateOrganizationCreation = async () => {};
