import { URL_API } from "../../../../vars";

export const checkAuth = async () => {

  //para hacer un recheck de la atenticacion puede ser cada sierto tiempo hago un lastcheck
  const lastCheck = localStorage.getItem("lastAuthCheck") || "";
  const now = Date.now();
  const SECONDS = 5000;

  if (lastCheck && now - parseInt(lastCheck) < SECONDS) {
    return JSON.parse(localStorage.getItem("userdata") || "false");
  }

  try {
    console.log("Iniciando solicitud a", `${URL_API}/auth/check?clearCookie=false`);
    
    const response = await fetch('http://localhost:8080/auth/check?clearCookie=false', {
      method: 'GET',
      credentials: 'include',
      headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
      },
  });
  

    console.log("Respuesta recibida:", response);

    if (response.ok) {
      const userData = await response.json();
      console.log("Datos del usuario:", userData);
      // Guarda los datos y la marca de tiempo
      localStorage.setItem("userdata", JSON.stringify(userData));
      localStorage.setItem("lastAuthCheck", now.toString());
      return true;
    } else {
      console.error("Error en la respuesta:", response.status, response.statusText);
      localStorage.removeItem("lastAuthCheck");
      return false;
    }
  } catch (error) {
    console.error("Error verificando autenticación:", error);
    return false;
  }

}

export const logOut = async () => {
  try {
    const response = await fetch(`${URL_API}/auth/check?clear=true`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      credentials: 'include',
    });

    if (response.ok) {
      console.log('Usuario desloagueado');
      return true;
    } else {
      console.log('No autenticado ' + response);
      return false;
    }
  } catch (error) {
    console.error('Error verificando autenticación:', error);
    return false;
  }
}
