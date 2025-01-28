import { Link } from "react-router-dom"

const styles = {
    green: 'linear-gradient(135deg, rgba(234, 248, 227, 1) 0%, rgba(47, 224, 132, 1) 100%)',
    blue: 'linear-gradient(135deg, rgba(246, 254, 254, 1) 0%, rgba(19, 194, 198, 1) 100%)',
    purple: 'linear-gradient(135deg, rgba(242, 240, 253, 1) 0%, rgba(136, 129, 170, 1) 100%)',
    yellow: 'linear-gradient(135deg, rgba(255, 244, 184, 1) 0%, rgba(248, 224, 54, 1) 100%)',
};

export const ButtonAccess = ({path, icon, color, textAlt}) => {
    return(
        <Link to={path}>
            <button className="p-3 border-2 border-blur rounded-xl" style={{ background: styles[color] }}>
                <img src={icon} alt={textAlt}/>
            </button>
        </Link>
    )
}