import ArrowBack from '../../assets/icons/arrow-back.svg';
import OnboardingStep1 from '../../components/Onboarding/OnboardingStep1';
export const Onboarding = () => {

    return(
        <div className="flex flex-col items-center justify-center bg-gradient bg-center h-screen w-screen relative">
            <button className='flex items-center justify-center w-[55px] h-[55px] bg-button border-2 border-gradient-stroke rounded-xl absolute top-5 left-5'><img src={ArrowBack} className='w-[24px]' alt="flecha hacia atras" /></button>
         <OnboardingStep1/>
        </div>
    )
}