import Cross from "../assets/icons/cross.svg"
import Logo from "../assets/logo.svg"

import Bars from "../assets/icons/sidebar/bars.svg"
import BarsSelected from "../assets/icons/sidebar/bars-selected.svg"
import Pulse from "../assets/icons/sidebar/pulse.svg"
import PulseSelected from "../assets/icons/sidebar/pulse-selected.svg"
import People from "../assets/icons/sidebar/people.svg"
import PeopleSelected from "../assets/icons/sidebar/people-selected.svg"

import Config from "../assets/icons/sidebar/config.svg"
import ConfigSelected from "../assets/icons/sidebar/config-selected.svg"
import Help from "../assets/icons/sidebar/help.svg"
import HelpSelected from "../assets/icons/sidebar/help-selected.svg"

import Profile from "../assets/images/profile.svg"

import { ButtonSidebar } from "../components/common/ButtonSidebar"

export const Sidebar = ({ isOpen, toggleSidebar }) => {
    const user = {
        name: "Nicolás Lozano",
        email: "nicolaslozano@email.com"
    }
    
    return (
        <>
        {isOpen && <div className="fixed inset-0 z-40 bg-black bg-opacity-50" onClick={toggleSidebar}></div>}
            <section className={`fixed top-0 left-0 bg-[#FFFFFF] font-jakarta w-[313px] h-screen rounded-r-2xl py-7 px-4 z-50 
            transform ${isOpen ? "translate-x-0" : "-translate-x-full"} 
            transition-transform duration-300 ease-in-out`}>
                <div className="flex justify-between">
                    <img src={Logo} alt="logo" className="ml-3"/>
                    <img src={Cross} alt="cerrar" onClick={toggleSidebar} />
                </div>
                <div className="flex flex-col justify-between h-full">
                    <div className="flex flex-col py-3 mt-7">
                        <ButtonSidebar icon={Bars} title={"Dashboard"} path={'/dashboard'} iconSelected={BarsSelected} selected={true} toggleSidebar={toggleSidebar}/>
                        <ButtonSidebar icon={Pulse} title={"Gestión de inversiones"} path={'/dashboard/inversiones'} iconSelected={PulseSelected} selected={true} toggleSidebar={toggleSidebar}/>
                        <ButtonSidebar icon={People} title={"Comunidad"} path={'/dashboard/comunidad'} iconSelected={PeopleSelected} selected={true} toggleSidebar={toggleSidebar}/>
                    </div>
                    <div className="flex-col">
                        <div className="flex flex-col py-3 mt-10">
                            <ButtonSidebar icon={Help} title={"Ayuda"} path={'/ayuda'} iconSelected={HelpSelected} selected={true} toggleSidebar={toggleSidebar}/> 
                            <ButtonSidebar icon={Config} title={"Configuración"} path={'/dashboard/configuracion'} iconSelected={ConfigSelected} selected={true} toggleSidebar={toggleSidebar}/>
                        </div>
                        <hr className="h-1 text-gray-200"/>
                        <div className="flex px-3 my-7">
                            <img src={Profile} alt="perfil" className="border-[2px] border-blue-500 rounded-full mr-2" />
                            <div className="flex flex-col">
                                <h3 className="text-base font-bold text-blue-900">{user.name}</h3>
                                <p className="text-sm text-gray-500">{user.email}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}