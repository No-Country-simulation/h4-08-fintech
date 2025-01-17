import ArrowBack from '../../assets/icons/arrow-back.svg';
import { EnableButton } from '../../components/common/EnableButton';
import { OnboardingStep5 } from '../../components/Onboarding/OnboardingStep5';
export const Onboarding = () => {

    return(
        <div className="bg-gradient bg-center h-screen w-screen relative pt-32 px-5 flex flex-col items-center font-jakarta">
            <button className='flex items-center justify-center w-[55px] h-[55px] bg-blur border-2 border-gradient-stroke rounded-xl absolute top-5 left-3'><img src={ArrowBack} className='w-[24px]' alt="flecha hacia atras" /></button>
            <OnboardingStep5 />
            <div className='fixed bottom-1 w-full px-5'>
                <EnableButton content='Continuar' available={true}/>
            </div>
        </div>
    )
}