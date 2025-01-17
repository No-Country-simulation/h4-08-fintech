import { useState } from "react";
import { OnboardingCard } from "../common/OnboardingCard"

export const OnboardingStep5 = ({setAvailableToContinue}) => {
    const min = 100000;
    const max = 1000000;
    const [investmentBalance, setInvestmentValue] = useState(min);
    const [percentage, setPercentage] = useState(0);

    const handleChange = (e) => {
        setAvailableToContinue(true);
        const value = e.target.value;
        getPercentage(value);
        setInvestmentValue(value);
    };

    const getPercentage = (value) => {
        setPercentage((value - min) / (max - min) * 100);
    };

    return(
        <OnboardingCard 
                title={"¿Cuanto de tus ingresos estarías dispuesto a ahorrar o invertir?"}
                content={"Lorem ipsum dolor sit amet consectetur. Pulvinar augue volutpat commodo lorem pharetra pretium eget varius quam."}
                >
                
                <span className=' text-4xl font-bold text-blue-700'>${parseInt(investmentBalance).toLocaleString("es-AR")}</span>
                <div className="relative w-full max-w-md mx-auto mt-7">
                <div
                    className="absolute top-1/2 left-0 w-full h-3 transform -translate-y-1/2 rounded-full bg-blur">
                    <div className="absolute top-1/2 left-[1%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                    <div className="absolute top-1/2 left-[17%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                    <div className="absolute top-1/2 left-[34%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                    <div className="absolute top-1/2 left-[50%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                    <div className="absolute top-1/2 left-[66%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                    <div className="absolute top-1/2 left-[82%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                    <div className="absolute top-1/2 left-[97%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                </div>
                <div className={`absolute top-1/2 left-0 h-3 transform -translate-y-1/2 rounded-full bg-blue-500`}
                    style={{ width: `${percentage}%` }}
                    ></div>
                <input
                    name='investmentBalance'
                    type="range"
                    value={investmentBalance}
                    min={min} 
                    max={max}
                    step={1000}
                    className="relative z-10 w-full h-3 bg-transparent appearance-none rounded-full"
                    onChange={handleChange}
                />
                </div>
                    <div className='flex justify-between w-full mt-1'>
                        <span className='text-xs font-light text-gray-600'>$100.000</span>
                        <span className='text-xs font-light text-gray-600'>+$1.000.000</span>
                    </div>
        </OnboardingCard>
    )
}