export const Button = ({ icon, altText = "Button Icon", onClick, style}) => {
    return(
        <button
                onClick={onClick}
                className={`flex items-center justify-center w-[44px] h-[44px] bg-blur border-[2px] border-gradient-stroke rounded-xl ${style}`}
            >
                <img src={icon} alt={altText} />
        </button>
    )
}