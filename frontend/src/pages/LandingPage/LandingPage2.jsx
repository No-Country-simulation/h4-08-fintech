import imgLanding from "../../assets/img-landing.png";
import imgLanding2 from '../../assets/img-landing2.png'

import { HeaderLandingP } from "../../components/common/HeaderLandingP";
export const LandingPage2 = () => {
  const feature = [
    {
      title: "Autenticación de dos pasos",
      desc: "Un nivel extra de seguridad para proteger tu cuenta y transacciones.",
      color: "bg-blue-500",
    },
    {
      title: "Acceso biométrico",
      desc: "Ingresá fácilmente y de forma segura con tu huella o reconocimiento facial.",
      color: "bg-teal-600",
    },
    {
      title: "Detección de fraudes",
      desc: "Sistemas inteligentes identifican y bloquean cualquier intento fraudulento..",
      color: "bg-red-600",
    },
    {
      title: "Encriptación avanzada",
      desc: "Tus datos están siempre codificados para evitar accesos no autorizados.",
      color: "bg-orange-500",
    },
    {
      title: "Notificaciones de actividad",
      desc: "Recibí alertas inmediatas cada vez que haya movimientos en tu cuenta.",
      color: "bg-green-700",
    },
    {
      title: "Soporte y protección 24/7",
      desc: "Sistemas inteligentes identifican y bloquean cualquier intento fraudulento.",
      color: "bg-violet-700",
    },
  ];
  const testimonials  = [
    {
      name: "Juan",
      age: "32",
      url:"https://images.pexels.com/photos/2379005/pexels-photo-2379005.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      
      comment:
        "Invertir nunca fue tan fácil y seguro. Estoy logrando mis objetivos más rápido de lo que imaginaba.",
    },  {
        name: "Sofia",
        age: "32",
        url:"https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg",
        comment:
          "UPi me ayudó a entender mis finanzas y a empezar a ahorrar con confianza",
      },
    {
        name: "Mateo",
        age: "45",
        url:"https://images.pexels.com/photos/7752805/pexels-photo-7752805.jpeg?",

        comment:
          "iUPi me ayudó a entender mis finanzas y a empezar a ahorrar con confianza",
      },
  ];
  return (
    <div className="bg-gradient">
      <img src={imgLanding} alt="" className="mx-auto py-5" />
      <HeaderLandingP
        title="SEGURIDAD"
        subtitle="Protegemos tu dinero en cada paso "
        description="En iUPi, la seguridad
          es nuestra prioridad. Tu dinero está protegido con tecnología avanzada
          que incluye:"
      />
      <div className="bg-white rounded-md mx-5 py-8 border-none">
        {feature.map((item, index) => (
          <div key={index} className="m-5">
            <div
              className={` w-2 h-2 rounded-full ${item.color} mx-auto my-5`}
            ></div>
            <h3 className="font-semibold my-3 text-xl">{item.title}</h3>
            <p className="  text-gray-600">{item.desc}</p>
            {index !== feature.length - 1 && (
              <div className="w-28 h-[1px] bg-gray-400 mx-auto my-7"></div>
            )}
          </div>
        ))}
      </div>
      <HeaderLandingP
        title="TESTIMONIOS"
        subtitle="Confianza construida con opiniones reales"
        description="Lo que dicen nuestros usuarios: Conoce las experiencias de personas como vos que están alcanzando sus metas financieras con iUPi."
      />
{
    testimonials.map((item, index)=>(
      <div className="bg-white rounded-md mx-5 p-8 border-none mt-8 py-8 " key={index}>
        <div>⭐⭐⭐⭐⭐</div>
        <p className="my-5">
         {item.comment}
        </p>{" "}
        <div className="flex items-center gap-4">
          <div className="rounded-full w-12 h-12 bg-black overflow-hidden">
          <img src={item.url}  alt="Descripción de la imagen" className="object-cover h-full w-full"/>
          </div>
          <div className="flex flex-col tracking-wider">
            <label className=" font-semibold text-base">{item.name} </label>
            <label className="text-gray-600 font-normal text-sm">{item.age} años</label>
          </div>
        </div>
      </div>

    ))
}
<div className="mt-8">
<div className="rounded-md mx-5 pb-5" style={{ backgroundImage: `url(${imgLanding2})` }}>
<h2 className="font-semibold text-center text-3xl text-white my-4 p-4">¿Listo para dar el siguiente paso y hacer crecer tu dinero?</h2>
<p className="text-white text-center  px-4">
Comenzá con iUPi hoy mismo. Simple, seguro y pensado para vos. Diseñado para ayudarte a invertir y manejar tus finanzas sin complicaciones. ¿Qué esperás? Tu próxima meta financiera está al alcance de tus manos.
</p>
<p className=" font-semibold mt-4 text-white text-center  px-4">
Regístrate ahora y descubrí cómo podés empezar a ganar con tu dinero.
</p>
<button className="w-80 mx-auto mt-5 bg-blue-500 border-blue-500  flex items-center justify-center border text-white font-semibold p-3 rounded-2xl">
Comenzar</button>
</div>
</div>
    </div>
  );
};
