import Thunder from "../assets/icons/thunder.svg"
import Correct from "../assets/icons/correct.svg"
import ManUsingApp from "../assets/images/landing/men-messages.svg"
import PhoneCard from "../assets/icons/landing/phone-card.svg"
import Document from "../assets/icons/landing/document.svg"
import Wallet from "../assets/icons/landing/wallet.svg"
import SecureCard from "../assets/icons/landing/secure-card.svg"
import Sponsor1 from "../assets/images/landing/sponsor-1.svg"
import Sponsor2 from "../assets/images/landing/sponsor-2.svg"
import Sponsor3 from "../assets/images/landing/sponsor-3.svg"
import Sponsor4 from "../assets/images/landing/sponsor-4.svg"

import MailBox from "../assets/icons/landing/mailbox.svg"
import Card from "../assets/icons/landing/card.svg"
import IncomeMoney from "../assets/icons/landing/income-money.svg"

import { Link } from "react-router-dom"
import { BenefitCard } from "../components/Landing/BenefitCard"
import { StepCard } from "../components/Landing/StepCard"
import Marquee from "react-fast-marquee"

export const Home = () => {

    const benefits = [
        {
            title: 'Invertí al instante',
            description: 'Accede a opciones de inversión de forma rápida y fácil, sin papeleo.',
            altText: 'telefono-tarjeta',
            icon: PhoneCard
        },
        {
            title: 'Flexibilidad total',
            description: 'Diseña tu plan de inversión según tus objetivos y preferencias.',
            altText: 'documento',
            icon: Document
        },
        {
            title: 'Transparencia absoluta',
            description: 'Conoce tus ganancias en tiempo real, sin costos ocultos.',
            altText: 'billetera',
            icon: Wallet
        },
        {
            title: 'Seguro y confiable',
            description: 'Tú dinero crece respaldado por tecnología de punta y seguridad.',
            altText: 'tarjeta-segura',
            icon: SecureCard
        }
    ]

    const steps = [
        {
            number: 1,
            title: 'Regístrate en minutos',
            icon: MailBox,
            description: 'Crea tu cuenta con tu email y algunos datos básicos.'
        },
        {
            number: 2,
            title: 'Definí tu perfil financiero',
            icon: Card,
            description: 'Responde preguntas simples sobre tus metas, ingresos y preferencias para obtener un plan adaptado a vos.'
        },
        {
            number: 3,
            title: 'Recibí recomendaciones personalizadas',
            icon: IncomeMoney,
            description: 'Descubrí opciones de inversión diseñadas para que tu dinero crezca según tus objetivos.'
        },
    ]

    const images = [
        Sponsor1, Sponsor2, Sponsor3, Sponsor4
    ];
    
    return(
        <main className="w-full">
            <section className="flex flex-col items-center w-full text-center">
                <h2 className="text-[#932EFA] font-semibold" data-aos="zoom-in" data-aos-duration="600">PAGOS FACILES Y AL INSTANTE</h2>
                <h1 className="mt-3 text-3xl font-bold text-[#000000] leading-none" data-aos="zoom-in" data-aos-duration="600">Empezá a invertir sin complicaciones y hacé crecer tu dinero</h1>
                <p className="text-base text-[#4D525F] mt-3" data-aos="zoom-in" data-aos-duration="600">La forma más simple y segura de manejar tus finanzas, pensada para vos.</p>
                <p className="flex text-base font-bold text-gray-700" data-aos="zoom-in" data-aos-duration="600">Rápido, claro y accesible para todos <img src={Thunder} className="ml-1" /></p>
                <div className="flex justify-between w-9/12 mt-2" data-aos="zoom-in" data-aos-duration="600">
                    <p className="text-sm text-[#4D525F] mt-3 flex"><img src={Correct} className="mr-1" />Sin tarjetas</p>
                    <p className="text-sm text-[#4D525F] mt-3 flex"><img src={Correct} className="mr-1" />Sin complicaciones</p>
                </div>
                <Link to={'/registro'} className="flex justify-center w-full mt-7" data-aos="fade-up" data-aos-duration="600">
                    <button
                        className="flex items-center justify-center w-11/12 px-3 py-3 text-base font-medium text-white bg-blue-500 rounded-2xl hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                    >
                        Comienza a invertir
                    </button>
                </Link>
                <img src={ManUsingApp} alt="hombre usando iUpi" className="mt-7" data-aos="fade-up" data-aos-duration="600"/>
            </section>

            <section className="flex flex-col items-center w-full text-center mt-7">
                <h2 className="text-[#932EFA] font-bold text-sm" data-aos="fade-up" data-aos-duration="300">CARACTERISTICAS</h2>
                <h1 className="mt-3 text-3xl font-bold text-[#000000] leading-none" data-aos="fade-up" data-aos-duration="300">¿Por qué elegir <span className="text-blue-500">iUPi</span> para invertir?</h1>
                {
                    benefits.map((benefit,i) => {
                        return(
                        <BenefitCard key={i} title={benefit.title} icon={benefit.icon} altText={benefit.altText} description={benefit.description} />
                    )})
                }
            </section>

            <section className="flex items-center w-full mt-14">
                {/* <img src={Sponsor1} alt="sponsor-1" className="mt-3 mr-7" />
                <img src={Sponsor2} alt="sponsor-2" />
                <img src={Sponsor3} alt="sponsor-3" className="mt-3 mr-7"/>
                <img src={Sponsor4} alt="sponsor-4" /> */}

                <Marquee gradient={false} speed={50}>
                    {images.map((src, index) => (
                        <img
                        key={index}
                        src={src}
                        alt={`Slide ${index}`}
                        className="h-10 mx-4"
                        />
                    ))}
                </Marquee>
            </section>

            <section className="flex flex-col items-center w-full text-center mt-14">
                <h2 className="text-[#932EFA] font-bold text-sm" data-aos="fade-up" data-aos-duration="300">COMO FUNCIONA</h2>
                <h1 className="mt-3 text-3xl font-bold text-[#000000] leading-none" data-aos="fade-up" data-aos-duration="300">Configura tu perfil de inversión en 3 simples pasos</h1>
                <Link to={'/registro'} className="flex justify-center w-full mt-7 mb-7" data-aos="fade-up" data-aos-duration="300">
                    <button
                        className="flex items-center justify-center w-11/12 px-3 py-3 text-base font-medium text-white bg-blue-500 rounded-2xl hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                    >
                        Obtener perfil
                    </button>
                </Link>
                {
                    steps.map((step,i) => {
                        return(
                        <StepCard key={i} title={step.title} icon={step.icon} number={step.number} description={step.description} totalSteps={steps.length}/>
                    )})
                }
            </section>
        </main>
    )
}