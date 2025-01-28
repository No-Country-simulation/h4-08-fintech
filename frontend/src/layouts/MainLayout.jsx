import { Outlet } from "react-router-dom"
import { Header } from "../components/Header"

export const MainLayout = () => {
    return (
        <div className="w-screen h-full bg-scroll bg-no-repeat bg-cover bg-gradient auto font-jakarta">
            <Header />
            <main className="p-5">
                <Outlet />    
            </main>
        </div>
    )
}