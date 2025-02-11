/* eslint-disable react/prop-types */
export const OnboardingCard = ({title, content, children}) => {
    
    return(
        <div className="flex flex-col items-center w-full px-2 text-center border-2 shadow-lg font-jakarta bg-blur rounded-2xl border-gradient-stroke py-7" 
        data-aos="flip-left"
        data-aos-duration="500"

        >
            <h2 className="w-full text-2xl font-bold text-center ">{title}</h2>
            <p className="w-11/12 mt-5 text-base font-light leading-tight tracking-tighter text-center text-gray-800">{content}</p>
            <div className="w-full mt-8">
                {children}
            </div>
        </div>
    )
} 