
import {URL_API} from "../../../../vars"

export default async function SendOnBoardingData(data) {
  try {
    const forSending = {
      estimatedExpenses: data.estimatedExpenses.payload,
      estimatedIncome: data.estimatedIncome.payload,
      financialGoalIds: data.financialGoalIds.payload,
      financialKnowledge: data.financialKnowledge.payload,
      fullName: data.fullName,
      phoneNumber: data.phoneNumber,
      riskTolerance: data.riskTolerance.payload,
      savingsCapacity: data.savingsCapacity.payload
    }
    const response = await fetch(`${URL_API}/customer?email=1`, {
      method: 'POST',
      credentials: true,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(forSending),
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




