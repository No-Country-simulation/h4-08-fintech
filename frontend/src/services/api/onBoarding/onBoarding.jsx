
const URL_API = " http://localhost:8080"

export default async function SendOnBoardingData(data) {
  try {
    const response = await fetch(`${URL_API}/customer?email=1`, {
      method: 'POST',
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




