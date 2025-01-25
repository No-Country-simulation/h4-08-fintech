import { Button } from "../../components/common/Button"
import ChevronUp from "../../assets/icons/chevron-up.svg"
import Maximize from "../../assets/icons/maximize.svg"
import Thunder from "../../assets/icons/thunder.svg"
import EyeOpen from "../../assets/icons/eye-open.svg"

export const Dashboard = () => {
    const user = {
        name: 'Juan',
        balance: 21560,
        transactions: 6200,
        save: 5000,
        card: '1111111111118912'
    }
    const perfilCompleted = 25

    const formatCardNumber = (card) => {
        const masked = card.slice(0, 12).replace(/\d/g, "*") + card.slice(12);
        return masked.match(/.{1,4}/g).join(" ");
    }

    return (
        <main>
            <h1 className="text-3xl font-semibold text-gray-900">Bienvenido {user.name}!</h1>
            <section className="mt-7 bg-blur rounded-2xl shadow-lg border-2 border-gradient-stroke px-3 py-3 relative">
                <span className="text-xs bg-blue-500 text-white font-medium px-2 py-1 rounded-full">Importante</span>
                <h2 className="text-xl tracking-tight text-gray-900 font-semibold mt-2">Termina de completar tu perfil</h2>
                <p className="text-xs tracking-tigh font-normal text-gray-700">Nos ayudará a mejorar nuestras recomendaciones</p>
                <Button icon={ChevronUp} style={'absolute top-3 right-3'} />

                <div className="flex items-center justify-between mt-2">
                    <div className="relative w-full max-w-md mx-auto">
                    <div
                        className="absolute top-1/2 left-0 w-full h-4 transform -translate-y-1/2 rounded-full bg-blur">
                        <div className="absolute top-1/2 left-[1%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                        <div className="absolute top-1/2 left-[17%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                        <div className="absolute top-1/2 left-[34%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                        <div className="absolute top-1/2 left-[50%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                        <div className="absolute top-1/2 left-[66%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                        <div className="absolute top-1/2 left-[82%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                        <div className="absolute top-1/2 left-[97%] w-2 h-2 bg-white rounded-full transform -translate-y-1/2"></div>
                    </div>
                    <div className={`absolute top-1/2 left-0 h-4 transform -translate-y-1/2 rounded-full bg-blue-500`}
                        style={{ width: `${perfilCompleted}%` }}
                        ></div>
                    </div>
                    <span className="text-blue-900 text-3xl font-bold pl-2">%{perfilCompleted}</span>
                </div>
            </section>

            <section className="mt-7 bg-blur rounded-2xl shadow-lg border-2 border-gradient-stroke px-3 py-3 relative">
                <p className="text-xs tracking-tigh font-normal text-gray-700">Balance (ARS)</p>
                <div className="my-4 flex items-center">
                    <span className="text-gray-900 font-semibold text-4xl">${parseInt(user.balance).toLocaleString("es-AR")}</span>
                    <img src={EyeOpen} alt="ojo" className="ml-4"/>
                </div>
                <div className="flex justify-between items-center">
                    <div className="flex flex-col">
                        <span className="text-xs tracking-tigh font-normal text-gray-700 mb-1">Transacciones</span>
                        <span className="text-gray-900 font-semibold text-lg ">${user.transactions}</span>
                    </div>
                    <div className="flex flex-col">
                        <span className="text-xs tracking-tigh font-normal text-gray-700 mb-1 flex">
                            <img src={Thunder} />
                            Ahorros
                        </span>
                        <span className="text-gray-900 font-semibold text-lg ">${user.save}</span>
                    </div>
                    <div className="flex flex-col">
                        <span className="text-xs tracking-tigh font-normal text-gray-700 mb-1">Caja de ahorros/Tarjeta</span>
                        <span className="text-gray-900 font-semibold text-lg ">{formatCardNumber(user.card)}</span>
                    </div>
                </div>
                <Button icon={Maximize} style={'absolute top-3 right-3'} />
                </section>
        </main>
    )
}