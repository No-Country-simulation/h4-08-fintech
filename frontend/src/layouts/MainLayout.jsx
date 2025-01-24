import { Outlet } from "react-router-dom"
import { Header } from "../components/Header"

export const MainLayout = () => {
    return (
        <div className="bg-gradient h-screen w-screen font-jakarta">
            <Header />
            <main>
                <Outlet />    
            </main>
        </div>
    )
}