export const BenefitCard = ({title, icon, altText, description }) => {
    return (
        <div className="flex flex-col items-center w-9/12 px-4 mt-5 text-center border-2 shadow-lg bg-blur rounded-2xl border-gradient-stroke py-7" 
        data-aos="flip-left"
        data-aos-duration="300"
        >
            <img src={icon} alt={altText} />
            <h1 className="w-full mt-5 text-xl font-bold text-center text-[#01081B]">{title}</h1>
            <p className="text-[#4D525F] text-sm ">{description}</p>
        </div>
    )
}