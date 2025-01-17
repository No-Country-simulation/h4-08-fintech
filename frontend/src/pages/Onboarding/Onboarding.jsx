import ArrowBack from '../../assets/icons/arrow-back.svg';
import { EnableButton } from '../../components/common/EnableButton';
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
    <div className="flex flex-col items-center justify-center bg-gradient bg-center h-screen w-screen relative pt-32 px-5 font-jakarta">
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
      {step === 2 && <OnboardingCard onNext={handleNext} />}

      <div className="fixed bottom-1 w-full px-5">
        <EnableButton
          content={step === 2 ? "Ir al Dashboard" : "Comenzar"}
          available={true} // cada paso lo active/desactive segun las validaciones
          onClick={handleNext}
        />
      </div>
    </div>
  );
};
