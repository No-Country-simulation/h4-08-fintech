import Thunder from "../assets/icons/thunder.svg"
import Correct from "../assets/icons/correct.svg"
import ManUsingApp from "../assets/images/landing/men-messages.svg"
import { Link } from "react-router-dom"

export const Home = () => {
    return(
        <main className="w-full">
            <section className="flex flex-col items-center w-full text-center">
                <h2 className="text-[#932EFA] font-semibold">PAGOS FACILES Y AL INSTANTE</h2>
                <h1 className="mt-3 text-3xl font-bold text-[#000000]">Empezá a invertir sin complicaciones y hacé crecer tu dinero</h1>
                <p className="text-base text-[#4D525F] mt-3">La forma más simple y segura de manejar tus finanzas, pensada para vos.</p>
                <p className="flex text-base font-bold text-gray-700">Rápido, claro y accesible para todos <img src={Thunder} className="ml-1" /></p>
                <div className="flex justify-between w-9/12 mt-2">
                    <p className="text-sm text-[#4D525F] mt-3 flex"><img src={Correct} className="mr-1" />Sin tarjetas</p>
                    <p className="text-sm text-[#4D525F] mt-3 flex"><img src={Correct} className="mr-1" />Sin complicaciones</p>
                </div>
                <Link to={'/registro'} className="w-full mt-7">
                    <button
                        className="flex items-center justify-center w-full px-3 py-3 text-base font-medium text-white bg-blue-500 rounded-2xl hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                    >
                        Comienza a invertir
                    </button>
                </Link>
                <img src={ManUsingApp} alt="hombre usando iUpi" className="mt-7"/>
            </section>

            <section className="flex flex-col items-center w-full text-center mt-7">
                <h2 className="text-[#932EFA] font-bold text-sm">CARACTERISTICAS</h2>
                <h1 className="mt-3 text-3xl font-bold text-[#000000]">¿Por qué elegir iUPi para invertir?</h1>
                
            </section>
        </main>
    )
}