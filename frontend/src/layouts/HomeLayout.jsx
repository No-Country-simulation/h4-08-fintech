import { Outlet } from "react-router-dom"
import { Header } from "../components/Header"

export const HomeLayout = () => {
    return (
        <div className="w-screen h-[1963px] bg-scroll bg-cover bg-gradient auto font-jakarta">
            <Header />
            <main className="p-5">
                <Outlet />    
            </main>
        </div>
    )
}