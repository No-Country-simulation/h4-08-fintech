import { Outlet } from "react-router-dom"
import { Header } from "../components/Header"
import { useState } from "react";
import { Sidebar } from "./Sidebar";

export const MainLayout = () => {
    const [isOpen, setIsOpen] = useState(false);
    const toggleSidebar = () => setIsOpen(!isOpen);
    
    return (
        <>
            <Sidebar isOpen={isOpen} toggleSidebar={toggleSidebar} />
            <div className="w-screen h-full bg-scroll bg-right bg-no-repeat bg-cover bg-gradient auto font-jakarta">
            <Header toggleSidebar={toggleSidebar}/>
            <main className="p-5">
                <Outlet />    
            </main>
        </div>
        </>
        
    )
}