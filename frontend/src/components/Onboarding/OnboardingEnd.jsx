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
         <div data-aos="flip-left">
            <div className="flex flex-col backdrop-blur-md bg-white/50 rounded-lg shadow-lg p-8 max-w-sm  text-center">
                <h2 className="text-2xl font-bold text-black mb-4">¡Muchas gracias! </h2>
                <p className="text-gray-700 mb-4">
                Gracias por compartir tu información. Ahora puedes explorar tu dashboard personalizado.                
                </p>
            </div>
          </div>
    );
};

export default OnboardingEnd;