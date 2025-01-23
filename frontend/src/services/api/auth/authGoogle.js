import {URL_API} from "../../../../vars";

export const handleAuthGoogleLogin = () => {

    window.location.href = `${URL_API}/oauth2/authorize/google`

}

