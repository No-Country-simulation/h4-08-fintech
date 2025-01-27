import { Button } from "../../components/common/Button"
import ChevronUp from "../../assets/icons/chevron-up.svg"
import Maximize from "../../assets/icons/maximize.svg"
import Thunder from "../../assets/icons/thunder.svg"
import Clock from "../../assets/icons/clock.svg"
import EyeOpen from "../../assets/icons/eye-open.svg"
import Info from "../../assets/icons/info.svg"
import Plain from "../../assets/emojis/plain.svg"

import Goals from "../../assets/icons/directAccess/goals.svg"
import Historial from "../../assets/icons/directAccess/historial.svg"
import Investments from "../../assets/icons/directAccess/investments.svg"
import Summary from "../../assets/icons/directAccess/summary.svg"
import { ButtonAccess } from "../../components/common/ButtonAccess"
import { Doughnut } from "react-chartjs-2"
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";


export const Dashboard = () => {
    
    ChartJS.register(ArcElement, Tooltip, Legend);
    const user = {
        name: 'Juan',
        balance: 21560,
        transactions: 6200,
        save: 5000,
        card: '1111111111118912',
        incomes: 500000,
        expensives: 250000,
        objetives: [
            {
                name: "Vacaciones",
                dueDate: '2025-02-07',
                savePerMonth: 0,
                goal: 0,
                balance: 0
            }
        ]
    }

    const financialRadiograph = [
        {name: "Ingresos", value: user.incomes, color: '#0058CC'},
        {name: "Gastos", value: user.expensives, color: '#EE5E37'},
        {name: "Ahorros", value: user.save, color: '#C6FF66'}
    ]

    const data = {
        labels: financialRadiograph.map(data => data.name),
        datasets: [
        {
            data: financialRadiograph.map(data => data.value),
            backgroundColor: financialRadiograph.map(data => data.color),
            borderWidth: 0,
           //hoverBackgroundColor: ["#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0"],
        },
        ],
    };

    const options = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                display: false
            }
        },
        tooltip: {
            enabled: true,
        },
        cutout: '60%',
        animations: {
            tension: {
            duration: 2000,
            easing: "easeInOutBounce", // Animación personalizada
            },
        },
    };
    
    const perfilCompleted = 25

    const formatCardNumber = (card) => {
        const masked = card.slice(0, 12).replace(/\d/g, "*") + card.slice(12);
        return masked.match(/.{1,4}/g).join(" ");
    }

    const daysUntil = (targetDate) => {
        const today = new Date();
        const target = new Date(targetDate);
        const differenceInMilliseconds = target - today;

        const days = Math.ceil(differenceInMilliseconds / (1000 * 60 * 60 * 24));
        return days;
    }

    return (
        <main>
            <h1 className="text-3xl font-semibold text-gray-900">Bienvenido {user.name}!</h1>
            <section className="relative px-3 py-3 border-2 shadow-lg mt-7 bg-blur rounded-2xl border-gradient-stroke">
                <span className="px-2 py-1 text-xs font-medium text-white bg-blue-500 rounded-full">Importante</span>
                <h2 className="mt-2 text-xl font-semibold tracking-tight text-gray-900">Termina de completar tu perfil</h2>
                <p className="text-xs font-normal text-gray-700 tracking-tigh">Nos ayudará a mejorar nuestras recomendaciones</p>
                <Button icon={ChevronUp} style={'absolute top-3 right-3'} />

                <div className="flex items-center justify-between mt-2">
                    <div className="relative w-full max-w-md mx-auto">
                    <div
                        className="absolute left-0 w-full h-4 transform -translate-y-1/2 rounded-full top-1/2 bg-blur">
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
                    <span className="pl-2 text-3xl font-bold text-blue-900">%{perfilCompleted}</span>
                </div>
            </section>

            <section className="relative px-3 py-3 border-2 shadow-lg mt-7 bg-blur rounded-2xl border-gradient-stroke">
                <p className="text-xs font-normal text-gray-700 tracking-tigh">Balance (ARS)</p>
                <div className="flex items-center my-4">
                    <span className="text-4xl font-semibold text-gray-900">${parseInt(user.balance).toLocaleString("es-AR")}</span>
                    <img src={EyeOpen} alt="ojo" className="ml-4"/>
                </div>
                <div className="flex items-center justify-between">
                    <div className="flex flex-col">
                        <span className="mb-1 text-xs font-normal text-gray-700 tracking-tigh">Transacciones</span>
                        <span className="text-lg font-semibold text-gray-900 ">${user.transactions}</span>
                    </div>
                    <div className="flex flex-col">
                        <span className="flex mb-1 text-xs font-normal text-gray-700 tracking-tigh">
                            <img src={Thunder} />
                            Ahorros
                        </span>
                        <span className="text-lg font-semibold text-gray-900 ">${user.save}</span>
                    </div>
                    <div className="flex flex-col">
                        <span className="mb-1 text-xs font-normal text-gray-700 tracking-tigh">Caja de ahorros/Tarjeta</span>
                        <span className="text-lg font-semibold text-gray-900 ">{formatCardNumber(user.card)}</span>
                    </div>
                </div>
                <Button icon={Maximize} style={'absolute top-3 right-3'} />
            </section>

            <section className="relative px-3 py-3 border-2 shadow-lg mt-7 bg-blur rounded-2xl border-gradient-stroke">
                <p className="text-2xl font-bold text-black tracking-tigh">Accesos directos</p>
                <div className="flex items-center justify-between mt-3">
                    <div className="flex flex-col items-center justify-center">
                        <span className="mb-1 text-xs font-medium text-gray-700 tracking-tigh">Crear objetivo</span>
                        <ButtonAccess path={""} icon={Goals} color={"green"} textAlt={"objetivo"}/>
                    </div>
                    <div className="flex flex-col items-center justify-center">
                        <span className="flex mb-1 text-xs font-medium text-gray-700 tracking-tigh">Historial</span>
                        <ButtonAccess path={""} icon={Historial} color={"blue"} textAlt={"historial"}/>
                    </div>
                    <div className="flex flex-col items-center justify-center">
                        <span className="mb-1 text-xs font-medium text-gray-700 tracking-tigh">Resumen</span>
                        <ButtonAccess path={""} icon={Summary} color={"purple"} textAlt={"resumen"}/>
                    </div>
                    <div className="flex flex-col items-center justify-center">
                        <span className="mb-1 text-xs font-medium text-gray-700 tracking-tigh">Invertir</span>
                        <ButtonAccess path={""} icon={Investments} color={"yellow"} textAlt={"inversiones"}/>
                    </div>
                </div>
                <div className="flex justify-end mt-4">
                    <span className="w-full text-xs font-semibold text-right text-blue-600">Ver todos</span>
                </div>
            </section>

            <h2 className="mt-5 text-2xl font-bold text-black">Radiografía financiera</h2>
            <section className="relative flex items-center justify-between w-full px-3 py-3 mt-4 border-2 shadow-lg bg-blur rounded-2xl border-gradient-stroke">
                <div className="w-1/2 h-auto">
                    <Doughnut data={data} options={options} />
                </div>
                <div>
                    { financialRadiograph.map((data, i) => {
                        return(
                            <div key={i} className="flex items-start mb-2">
                                <span className="w-3 h-3 mt-1 mr-1 rounded-full" style={{backgroundColor: data.color }}></span>
                                <div className="flex flex-col justify-start">
                                    <span className="text-xs font-light text-gray-600" >{data.name}</span>
                                    <span className="text-xs font-light text-gray-600">{parseInt(data.value).toLocaleString("es-AR")} ARS</span>
                                </div>
                            </div>
                        )
                    })
                    }
                </div>
            </section>

            <div className="flex items-center mt-7">
                <h2 className="text-2xl font-bold text-black">Objetivos de ahorro</h2>
                <span className="w-6 h-6 ml-2"><img src={Info} alt="informacion" /></span>
            </div>
            {user.objetives.length > 0? 
                user.objetives.map((objetive, i) => {
                return (
                    <section key={i} className="relative flex items-center justify-between w-full px-3 py-3 mt-4 border-2 shadow-lg bg-blur rounded-2xl border-gradient-stroke" >
                        <div className="flex">
                            <span className="w-[48px] h-[48px] bg-white rounded-full flex justify-center items-center"><img src={Plain}/></span>
                            <div className="ml-2">
                                <h3 className="mb-1 text-2xl font-bold text-blue-900">{objetive.name}</h3>
                                <div className="flex items-center w-full">
                                    <span className="flex mr-5 text-xs font-normal text-gray-700 tracking-tigh">
                                        <img src={Clock} />
                                        Faltan {daysUntil(objetive.dueDate)} dias
                                    </span>
                                    <span className="flex text-xs font-normal text-gray-700 tracking-tigh">
                                        <img src={Thunder} />
                                        {objetive.savePerMonth}  ARS/Mes
                                    </span>
                                </div>
                            </div>
                        </div>
                        
                    </section>
                )})
                :
                <h2>No hay objetivos</h2>
            }
            <div className="flex justify-end w-full mt-4">
                <span className="w-full text-base font-semibold text-right text-blue-600">Ver todos</span>
            </div>
                
            <h2 className="mt-5 text-2xl font-bold text-black">Inversiones recomendadas</h2>
            <section className="relative flex items-center justify-between w-full px-3 py-3 mt-4 border-2 shadow-lg bg-blur rounded-2xl border-gradient-stroke">
                <div className="flex justify-end w-full mt-4">
                    <span className="w-full text-base font-semibold text-right text-blue-600">Ver todas</span>
                </div>
            </section>
        </main>
    )
}