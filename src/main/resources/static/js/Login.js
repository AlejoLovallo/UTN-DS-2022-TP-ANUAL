let attemptSignIn = 3;

const API_ENDPOINT = "http://127.0.0.1:9000";

const validateSignIn = async () => {
  const username = document.getElementById("NombreUsuarioSignIn").value;
  const password = document.getElementById("PasswordSignIn").value;

 fetch(`${API_ENDPOINT}/menu_login`, {
    method: "POST",
    mode: "no-cors",

    body: JSON.stringify({
      username,
      password,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    //.then(response => response.json())
    .then((response) => {
      console.log("OKKKK");
      console.log(JSON.stringify(response));
      console.log(response);
      alert(JSON.stringify(response));
      //TODO no redirigir a esta pagina siempre
      //window.location.href = "./Menu_miembro.html";
    })
    .catch((error) => {
      console.log(error)
    });
};

const verifyOrgFields = () => {
  const socialReason = document.getElementById("RazonSocial").value;
  const cuit = document.getElementById("Cuit").value;
  const clasification = document.getElementById("ClasificacionOrg").value;
  const type = document.getElementById("TipoOrg").value;
  const location = document.getElementById("LocalidadOrg").value;

  return {
    socialReason,
    cuit,
    clasification,
    type,
    location,
  };

  return true;
};

const verifyUserOrgFields = () => {
  const email = document.getElementById("EmailUserOrg").value;
  const name = document.getElementById("NombreUsuarioOrg").value;
  const password = document.getElementById("PasswordUserOrg").value;
  const repeatPassword = document.getElementById("PasswordRepeatUserOrg").value;

  if (password != repeatPassword) {
    alert("Las contraseñas no coinciden");
    return {};
  }

  return {
    name,
    email,
    password,
  };
};

const validateOrganizationCreation = async () => {
  const org = verifyOrgFields();
  const user = verifyUserOrgFields();
  if (org && user != {}) {
    const res = await fetch(`${API_ENDPOINT}/organizacion`, {
      method: "POST",

      body: JSON.stringify({
        socialReason: org.socialReason,
        cuit: org.cuit,
        clasification: org.clasification,
        type: org.type,
        location: org.location,
        user: {
          mail: user.mail,
          name: user.name,
          password: user.password,
        },
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
      alert(
        "Organizacion creada con exito. Un administrador dará de alta su pedido a la brevedad."
      );
    }
  }
};

const validateRegisterMember = async () => {
  const name = document.getElementById("NombreMember").value;
  const surname = document.getElementById("ApellidoMember").value;
  const birthDate = document.getElementById("NacimientoMember").value;
  const sex = document.getElementById("SexoMember").value;
  const city = document.getElementById("CiudadMember").value;
  const location = document.getElementById("LocalidadMember").value;
  const email = document.getElementById("EmailMember").value;
  const username = document.getElementById("UsernameMember").value;
  const password = document.getElementById("PasswordMember").value;
  const repeatPassword = document.getElementById("PasswordRepeatMember").value;

  if (password != repeatPassword) {
    alert("Las contraseñas no coinciden");
    return;
  }

  await fetch(`${API_ENDPOINT}/miembro`, {
    method: "POST",

    body: JSON.stringify({
      name,
      surname,
      birthDate,
      sex,
      city,
      location,
      email,
      username,
      password,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((res) => {})
    .catch((error) => {
      console.log("ERROR");
      console.loog(error);
    });
};
