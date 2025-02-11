import { useSelector } from "react-redux";
import { useEffect } from 'react';
import { initialState } from "../../../redux/slices/onboardingSlice"
import SendOnBoardingData from "../../services/api/onBoarding/onBoarding"
const OnboardingEnd = () => {
  const onbInfo = useSelector(state => state.onBoarding);
  console.log(onbInfo);

  useEffect(() => { 
            const data = {
              "financialKnowledge": onbInfo.financialKnowledge,
              "riskTolerance": onbInfo.riskTolerance,
              "estimatedIncome": onbInfo.estimatedIncome,
              "estimatedExpenses": onbInfo.estimatedExpenses,
              "savingsCapacity": onbInfo.savingsCapacity,
              "financialGoalIds": onbInfo.financialGoalIds
            }
            initialState !== onbInfo && SendOnBoardingData(data);
            }, [initialState, onbInfo, SendOnBoardingData]); 

    return (
         <div data-aos="flip-left"
         data-aos-duration="500">
      <div className="flex flex-col max-w-sm p-8 text-center rounded-lg shadow-lg backdrop-blur-md bg-white/50">
      <h2 className="mb-4 text-2xl font-bold text-black">¡Muchas gracias! </h2>
               
                <p className="mb-4 text-gray-700">
                Gracias por compartir tu información. Ahora puedes explorar tu dashboard personalizado.                </p>
       
         
            </div>
          </div>
    );
};

export default OnboardingEnd;