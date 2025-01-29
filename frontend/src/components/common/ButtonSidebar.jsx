import { useState } from "react";
import { Link, useLocation } from "react-router-dom"

export const ButtonSidebar = ({icon, title, iconSelected, selected, path, toggleSidebar}) => {
    const [isHovered, setIsHovered] = useState(false);
    const location = useLocation().pathname;
    
    return (
        <Link to={path} className="h-[44px] w-[281px] mt-3 cursor-pointer" 
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
            onClick={toggleSidebar}
        >
            <button className={`w-full rounded-2xl flex px-4 py-[10px] text-base font-bold 
            transition-all duration-200 
            ${isHovered || location==path ? "bg-blue-50 text-blue-600" : "bg-white text-blue-900"}`}
            >
                <img src={isHovered || location==path? iconSelected : icon} className="mr-2" />
                {title}
            </button>
        </Link>
    )
}