import { useState } from "react";
import ArrowBack from "../../assets/icons/arrow-back.svg";
import OnboardingStep1 from "../../components/Onboarding/OnboardingStep1";
import OnboardingCard from "../../components/Onboarding/OnboardingCard";
import { EnableButton } from "../../components/common/EnableButton";
export const Onboarding = () => {
  const [step, SetStep] = useState(1);

  const handleNext = () => {
    /* aca se pondran mas pasos */
    if (step < 2) {
      SetStep((prevStep) => prevStep + 1);
    }
  };

  const handleBack = () => {
    if (step > 1) {
      SetStep((prevStep) => prevStep - 1);
    }
  };

  return (
    <div className="flex flex-col items-center justify-center bg-gradient bg-center h-screen w-screen relative">
      <button
        onClick={handleBack}
        className="flex items-center justify-center w-[55px] h-[55px] bg-button border-2 border-gradient-stroke rounded-xl absolute top-5 left-5"
      >
        <img src={ArrowBack} className="w-[24px]" alt="flecha hacia atras" />
      </button>
      {step === 1 && <OnboardingStep1 onNext={handleNext} />}
      {step === 2 && <OnboardingCard onNext={handleNext} />}

      <div className="absolute bottom-10 w-full px-8">
        <EnableButton
          content={step === 2 ? "Ir al Dashboard" : "Comenzar"}
          available={true} // cada paso lo active/desactive segun las validaciones
          onClick={handleNext}
        />
      </div>
    </div>
  );
};
