export const OnboardingCard = ({title, content, children}) => {
    
    return(
        <div className="font-jakarta w-full flex flex-col items-center bg-blur rounded-2xl shadow-lg text-center border-2 border-gradient-stroke px-2 py-8">
            <h2 className=" w-full text-center font-bold text-2xl">{title}</h2>
            <p className="w-11/12 text-center mt-5 font-light text-gray-800 text-base tracking-tighter leading-tight">{content}</p>
            <div className="w-full mt-12">
                {children}
            </div>
        </div>
    )
} 