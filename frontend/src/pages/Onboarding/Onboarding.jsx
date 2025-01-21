import { useEffect, useState } from 'react';
import ArrowBack from '../../assets/icons/arrow-back.svg';
import { EnableButton } from '../../components/common/EnableButton';
import OnboardingStep1 from '../../components/Onboarding/OnboardingStep1';
import OnboardingStep2 from '../../components/Onboarding/OnboardingStep2';

import { OnboardingStep5 } from '../../components/Onboarding/OnboardingStep5';
import {FinancialGoals as OnboardingStep3} from '../../components/Onboarding/OnboardingStep3';
export const Onboarding = () => {
const [step, SetStep] = useState(1);
const [availableToContinue, setAvailableToContinue] = useState(true);

const handleNext = () => {
    /* aca se pondran mas pasos */   
    if (step < 5) {
    SetStep((prevStep) => prevStep + 1);
    setAvailableToContinue(false);
    }
};

const handleBack = () => {
    if (step > 1) {
    SetStep((prevStep) => prevStep - 1);
    }
};

useEffect(() => {
    if (step === 1) {
    setAvailableToContinue(true);
    }
},[step]);

    return (
    <div className="flex flex-col items-center justify-center bg-gradient bg-center h-screen w-screen relative px-5 font-jakarta">
    {
        step > 1 ?
        <button
            onClick={handleBack}
            className="flex items-center justify-center w-[55px] h-[55px] bg-blur border-2 border-gradient-stroke rounded-xl absolute top-5 left-5"
        >
            <img src={ArrowBack} className="w-[24px]" alt="flecha hacia atras" />
        </button> : null
        } 
        {step === 1 && <OnboardingStep1 onNext={handleNext} />}
        {step === 2 && <OnboardingStep2 onNext={handleNext} setAvailableToContinue={setAvailableToContinue} />}
        {step === 3 && <OnboardingStep3 onNext={handleNext} setAvailableToContinue={setAvailableToContinue}/>}
        {step === 4 && <OnboardingStep3 onNext={handleNext} setAvailableToContinue={setAvailableToContinue}/>}
        {step === 5 && <OnboardingStep5 onNext={handleNext} setAvailableToContinue={setAvailableToContinue}/>} 

        <div className="fixed bottom-1 w-full px-5">
            { step > 1 &&
            <div className="flex justify-center gap-[6px] mb-4">
            {Array.from({ length: 5 }).map((_, index) => (
                <div
                    key={index}
                    className={`w-8 h-1.5 rounded-full ${step === index + 1 ? 'bg-blue-500' : 'bg-white'}`}
                />
            ))}
            </div>
            }
            <EnableButton
            content={step > 1 ? "Continuar" : "Comenzar"}
            available={availableToContinue}
            onClick={handleNext}
            />
        </div>
        </div>
    );
};
