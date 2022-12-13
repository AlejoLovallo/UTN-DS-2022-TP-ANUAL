let attemptSignIn = 3;

const API_ENDPOINT = "http://127.0.0.1:9000";

const validateSignIn = async () => {
  const username = document.getElementById("NombreUsuarioSignIn").value;
  const password = document.getElementById("PasswordSignIn").value;


console.log(username);
console.log(password);

 await fetch(`${API_ENDPOINT}/menu_login`, {
    method: "POST",

    body: JSON.stringify({
      username,
      password,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then(res => res.json())
    .then(res => {
      console.log(res);
      console.log(res.message);
      console.log(document.cookie);
      if(res.message == 'error de JSON'){
        alert(res.message);
      }
      if(res.message =='error de contra'){
        console.log(res.data);
        alert(res.data);
      }
      if(res.message =='error de usuario'){
        alert(res.message);
      }
      if(res.message =='pantalla organizacion'){
        document.cookie = `idSesion=${res.message}`;
        window.location.replace(`${API_ENDPOINT}/menu_organizacion`);
      }
      if(res.message =='Pantalla Admin'){
        //TODO ver la pantalla del admin
        document.cookie = `idSesion=${res.message}`;
        window.location.replace(`${API_ENDPOINT}/menu_organizacion`);
      }
      if(res.message =='pantalla persona'){
        document.cookie = `idSesion=${res.message}`;
        window.location.replace(`${API_ENDPOINT}/menu_miembro`);
      }
      if(res.message =='pantalla usuario default'){
        document.cookie = `idSesion=${res.message}`;
        alert("usuario default");
      }
     })
    .catch((error) => {
      console.log(error)
    });
};

const singUpOrganizacion = async () => {
  const username = document.getElementById("NombreUsuarioOrg").value;
  const password = document.getElementById("PasswordUserOrg").value;
  const password_copia = document.getElementById("PasswordRepeatUserOrg").value
  const razon_social = document.getElementById("RazonSocial").value;
  const cuit = document.getElementById("Cuit").value;
  const clasificacion = document.getElementById("ClasificacionOrg").value;
  const tipo = document.getElementById("TipoOrg").value;
  const localidad = document.getElementById("LocalidadOrg").value;
  const mail = document.getElementById("EmailUserOrg").value;

  if(password != password_copia){
    alert("Las contraseñas deben ser iguales");
    return;
  }

console.log(username);
console.log(password);

 fetch(`${API_ENDPOINT}/registrar_org`, {
    method: "POST",

    body: JSON.stringify({
      username,
      password,
      mail,
      razon_social,
      cuit,
      clasificacion,
      tipo,
      localidad,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    // .then( (res) => {
    //   console.log(res)
    //   //return res.json()
    // } )
    .then(res => res.json())
    .then(res => {
      console.log(res);
      console.log(res.message);
      if(res.message =='pantalla organizacion'){
        window.location.replace(`${API_ENDPOINT}/menu_organizacion`);
      }
     })
    .catch((error) => {
      console.log(error)
    });
};

const singUpPersona = async () => {
  const username = document.getElementById("UsernameMember").value;
  const password = document.getElementById("PasswordMember").value;
  const password_copia = document.getElementById("PasswordRepeatMember").value
  const nombre = document.getElementById("NombreMember").value;
  const apellido = document.getElementById("ApellidoMember").value;
  const fechaNac = document.getElementById("NacimientoMember").value;
  const sexo = document.getElementById("SexoMember").value;
  const ciudad = document.getElementById("CiudadMember").value;
  const localidad = document.getElementById("LocalidadMember").value;
  const mail = document.getElementById("EmailMember").value;

  if(password != password_copia){
    alert("Las contraseñas deben ser iguales");
    return;
  }

console.log(username);
console.log(password);

 fetch(`${API_ENDPOINT}/registrar_org`, {
    method: "POST",

    body: JSON.stringify({
      username,
      password,
      mail,
      nombre,
      apellido,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    // .then( (res) => {
    //   console.log(res)
    //   //return res.json()
    // } )
    .then(res => res.json())
    .then(res => {
      console.log(res);
      console.log(res.message);
      if(res.message =='pantalla miembro'){
        window.location.replace(`${API_ENDPOINT}/menu_miembro`);
      }
     })
    .catch((error) => {
      console.log(error)
    });
};



const verifyOrgFields = () => {
//TODO: falta documento y tipo de documento
  const socialReason = document.getElementById("RazonSocial").value;
  const cuit = document.getElementById("Cuit").value;
  const clasification = document.getElementById("ClasificacionOrg").value;
  const type = document.getElementById("TipoOrg").value;
  const location = document.getElementById("LocalidadOrg").value;
  const diasSemana = document.getElementById("dias_semana").value

  return {
    socialReason,
    cuit,
    clasification,
    type,
    location,
    diasSemana,
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
  if (org && JSON.stringify(user) != '{}') {
    const res = await fetch(`${API_ENDPOINT}/registrar_org`, {
      method: "POST",

      body: JSON.stringify({
        socialReason: org.socialReason,
        cuit: org.cuit,
        clasification: org.clasification,
        type: org.type,
        location: org.location,
        diasSemana: org.diasSemana,
        user: {
          mail: user.email,
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
      alert("Organizacion creada con exito.");

        /*document.cookies.set({
          name: "username",
          value: Request.Cookies[username]
        });*/
       window.location.replace(`${API_ENDPOINT}/menu_organizacion`);
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
  const tipoDocumento = document.getElementById("TipoDocumento").value;
  const documento = document.getElementById("Documento").value

  if (password != repeatPassword) {
    alert("Las contraseñas no coinciden");
    return;
  }

  await fetch(`${API_ENDPOINT}/registrar_persona`, {
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
      tipoDocumento,
      documento,
    }),

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((res) => {
        alert("Persona registrada con exito");
        window.location.replace(`${API_ENDPOINT}/menu_miembro`);
    })
    .catch((error) => {
      console.log("ERROR");
      console.log(error);
    });
};
