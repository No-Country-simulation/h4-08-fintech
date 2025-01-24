export const Button = ({ icon, altText = "Button Icon", onClick, style}) => {
    return(
        <button
                onClick={onClick}
                className={`flex items-center justify-center w-[55px] h-[55px] bg-blur border-[2px] border-gradient-stroke rounded-2xl ${style}`}
            >
                <img src={icon} className="w-[24px]" alt={altText} />
        </button>
    )
}