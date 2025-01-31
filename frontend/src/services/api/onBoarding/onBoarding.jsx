
import {URL_API} from "../../../../vars"

export default async function SendOnBoardingData(data) {
  try {
    const response = await fetch(`${URL_API}/customer?email=1`, {
      method: 'POST',
      credentials: true,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });

    if (!response.ok) {
      throw new Error(`Error: ${response.status} - ${response.statusText}`);
    }
    const result = await response.json();
    return result; 
  } catch (error) {
    
    console.error('Error en SendOnBoardingData:', error.message);
    throw error; 
  }
}




