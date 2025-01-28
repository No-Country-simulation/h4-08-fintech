import { URL_API } from "../../../../vars";

export const loginUser = (email, password) => {
    const loginData = {
        email: email,
        password: password
    };

    fetch(`${URL_API}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData)
    })
    .then(response => response.json())
    .then(data => {

        if (data && data.email && data.username) {
            console.log('Usuario logueado:', data);
            alert('Inicio de sesión exitoso');

        } else {
            alert('Error al iniciar sesión');
        }
    })
    .catch(error => {
        console.error('Error al iniciar sesión:', error);
        alert('Error al iniciar sesión');
    });
}
