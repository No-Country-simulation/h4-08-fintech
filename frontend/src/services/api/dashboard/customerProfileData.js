import { URL_API } from "../../../../vars";

export const getCustomerData = async () => {

    try {   

       const response = await fetch(`${URL_API}/financial-profiles/dashboard?limit=5&page=0&sortBy=id&ascending=true`, {
           method: 'GET',
           headers: {
               'Content-Type': 'application/json',
           },
           credentials:"include",
       })
       const data = await response.json();
        console.log('Datos Dash:', data);
        return data;

    } catch (error) {
        console.error(error);
    }

}