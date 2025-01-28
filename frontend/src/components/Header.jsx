import { Link, useLocation } from "react-router-dom"
import { Button } from "./common/Button";
import Menu from "../assets/icons/menu.svg"
import Logo from "../assets/logo.svg"
import LogoLanding from "../assets/logo-v2.svg"
import Notification from "../assets/icons/notification.svg"
import ArrowBack from '../assets/icons/arrow-back.svg';

export const Header = () => {
    const location = useLocation().pathname;

    const needMenu = location == '/dashboard' || location == '/dashboard/inversiones' || location == '/dashboard/comunidad' || location == '/'
    const isHome = location == '/'
    const isAuth = location == '/dashboard' || location == '/dashboard/'
    const titles = [
        {
            path: 'inversiones',
            title: 'Gestión de Inversiones'
        },
        {
            path: 'notificaciones',
            title: 'Notificaciones'
        },
    ]
    
    return (
        <header className={`bg-transparent flex px-4 py-2 ${isAuth? 'justify-between' : ''} items-center`}>
            { needMenu?
                <Button icon={Menu} altText="menu"/>
                :
                <Link to={'/dashboard'}>
                <Button icon={ArrowBack} altText='flecha hacia atras' style={'rm-auto'}/>
                </Link>
            }
            {  
                isHome?
                    <div className="flex items-center justify-center w-full h-full">
                        <img src={Logo} alt="logo" className="w-[38px] mr-4"/>
                    </div>
                    :
                    <></>
            }
            {
                isAuth?
                    <img src={Logo} alt="logo"/>
                :
                <div className="flex items-center justify-center flex-1">
                    <h1 className="text-2xl font-semibold tracking-tight text-blue-900 whitespace-nowrap">
                        {titles.map(title => `/dashboard/${title.path}` == location? title.title : '')}
                    </h1>
                </div>
            }
            {isAuth &&
            <Link to={'/dashboard/notificaciones'}>
                <Button icon={Notification} altText="notificaciones"/>
            </Link>
            }
        </header>
    )
}