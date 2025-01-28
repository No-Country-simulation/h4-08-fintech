import { Outlet } from "react-router-dom"
import { Header } from "../components/Header"
import { Footer } from "../components/Footer"

export const HomeLayout = () => {
    return (
        <div className="w-screen bg-scroll bg-right bg-repeat bg-cover bg-gradient auto font-jakarta">
            <Header />
            <main className="p-5">
                <Outlet />    
            </main>
            <Footer />
        </div>
    )
}