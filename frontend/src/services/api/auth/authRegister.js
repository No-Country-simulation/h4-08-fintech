import { URL_API } from "../../../../vars";

export const registerUser = (email, username, password) => {
    const registerData = {
        email: email,
        username: username,
        password: password
    };

    fetch(`${URL_API}/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(registerData)
    })
    .then(response => response.json())
    .then(data => {
        window.alert('Registro exitoso');
      setTimeout(() => {
        window.location.href = '/login';
      }, 5000); 
    })
    .catch(error => {
        console.error('Error registrando usuario:', error);
        alert('Error al registrar el usuario');
        return false
    });
}
