import imgLanding from "../../assets/img-landing.png";
import imgLanding2 from '../../assets/img-landing2.png'

import { HeaderLandingP } from "../../components/Landing/HeaderLandingP";
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
    <div>
      <img src={imgLanding} alt="" className="py-5 mx-auto" data-aos="fade-up" data-aos-duration="600" />
      <HeaderLandingP
        title="SEGURIDAD"
        subtitle="Protegemos tu dinero en cada paso "
        description="En iUPi, la seguridad
          es nuestra prioridad. Tu dinero está protegido con tecnología avanzada
          que incluye:"
      />
      <div className="py-8 mx-5 bg-white border-none rounded-md" >
        {feature.map((item, index) => (
          <div key={index} className="m-5" data-aos="fade-up" data-aos-duration="400">
            <div
              className={` w-2 h-2 rounded-full ${item.color} mx-auto my-5`}
            ></div>
            <h3 className="my-3 text-xl font-semibold">{item.title}</h3>
            <p className="text-gray-600 ">{item.desc}</p>
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
      <div className="p-8 py-8 mx-5 mt-8 bg-white border-none rounded-md " key={index} data-aos="fade-up"
      data-aos-duration="600">
        <div>⭐⭐⭐⭐⭐</div>
        <p className="my-5">
          {item.comment}
        </p>{" "}
        <div className="flex items-center gap-4">
          <div className="w-12 h-12 overflow-hidden bg-black rounded-full">
          <img src={item.url}  alt="Descripción de la imagen" className="object-cover w-full h-full"/>
          </div>
          <div className="flex flex-col tracking-wider">
            <label className="text-base font-semibold ">{item.name} </label>
            <label className="text-sm font-normal text-gray-600">{item.age} años</label>
          </div>
        </div>
      </div>

    ))
}
<div className="my-8"  data-aos="fade-up"
  data-aos-duration="800">
  <div
    className="mx-5 rounded-lg"
    style={{
      backgroundImage: `url(${imgLanding2})`,
      backgroundRepeat: 'no-repeat',
      backgroundSize: 'cover',
      paddingBottom: '2rem', 
    }}
  >
    <h2 className="pt-5 my-5 text-3xl font-semibold text-center text-white">
      ¿Listo para dar el siguiente paso y hacer crecer tu dinero?
    </h2>
    <p className="px-4 text-center text-white">
      Comenzá con iUPi hoy mismo. Simple, seguro y pensado para vos. Diseñado para ayudarte a invertir y manejar tus finanzas sin complicaciones. ¿Qué esperás? Tu próxima meta financiera está al alcance de tus manos.
    </p>
    <p className="px-4 mt-4 font-semibold text-center text-white">
      Regístrate ahora y descubrí cómo podés empezar a ganar con tu dinero.
    </p>
    <button className="flex items-center justify-center p-3 mx-auto my-5 font-semibold text-white bg-blue-500 border border-blue-500 w-80 rounded-2xl">
      Comenzar
    </button>
  </div>
</div>

    </div>
  );
};
