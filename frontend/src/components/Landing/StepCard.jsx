export const StepCard = ({title, icon, number, description, totalSteps}) => {

    const getBorderClasses = (number, totalSteps) => {
        if (number === 1) {
            return "rounded-t-2xl border-t-2";
        } else if (number === totalSteps) {
            return "rounded-b-2xl border-b-2";
        } else {
            return "border-y-2";
        }
    };
    
    const borderClasses = getBorderClasses(number, totalSteps);
    
    return (
        <div className={`flex flex-col items-center w-11/12 px-5 shadow-lg text-start bg-blur border-gradient-stroke py-10 ${borderClasses}`}
        data-aos="flip-left" data-aos-duration="600"
        >
            <div className="flex w-full">
                <h1 className="mr-3 text-6xl font-bold text-white">0{number}</h1>
                <img src={icon} />
            </div>
            
            <h1 className="w-full mt-2 text-xl font-bold text-[#01081B]">{title}</h1>
            <p className="text-[#4D525F] text-sm mt-1">{description}</p>
        </div>
    )
}