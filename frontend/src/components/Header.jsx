import { Link, useLocation } from "react-router-dom"
import { Button } from "./common/Button";
import Menu from "../assets/icons/menu.svg"
import Logo from "../assets/logo.svg"
import Notification from "../assets/icons/notification.svg"
import ArrowBack from '../assets/icons/arrow-back.svg';

export const Header = () => {
    const location = useLocation().pathname;

    const needMenu = location == '/dashboard' || location == '/dashboard/inversiones' || location == '/dashboard/comunidad'
    const isHome = location == '/dashboard' || location == '/dashboard/'
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
        <header className={`bg-transparent flex px-4 py-2 ${isHome? 'justify-between' : ''} items-center`}>
            { needMenu?
                <Button icon={Menu} altText="menu"/>
                :
                <Link to={'/dashboard'}>
                <Button icon={ArrowBack} altText='flecha hacia atras' style={'rm-auto'}/>
                </Link>
            }
            {  isHome?
                <img src={Logo} alt="logo"/>
                :
                <div className="flex justify-center items-center flex-1">
                    <h1 className="text-blue-900 font-semibold whitespace-nowrap text-2xl tracking-tight">
                        {titles.map(title => `/dashboard/${title.path}` == location? title.title : '')}
                    </h1>
                </div>
            }
            {isHome &&
            <Link to={'/dashboard/notificaciones'}>
                <Button icon={Notification} altText="notificaciones"/>
            </Link>
            }
        </header>
    )
}