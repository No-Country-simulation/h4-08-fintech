import { Outlet } from "react-router-dom"
import { Header } from "../components/Header"

export const MainLayout = () => {
    return (
        <div className="bg-scroll bg-top bg-gradient auto font-jakarta">
            <Header />
            <main className="p-5">
                <Outlet />    
            </main>
        </div>
    )
}