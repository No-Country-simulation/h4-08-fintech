import logo from "../../assets/icons/logo/iUpi.svg";
const OnboardingStep1 = () => {
  return (
    <div data-aos="flip-left"
    data-aos-duration="500">
      <div className="flex flex-col max-w-sm p-8 text-center rounded-lg shadow-lg backdrop-blur-md bg-white/50">
        <h2 className="mb-4 text-2xl font-bold text-black">Bienvenido a</h2>
        <div className="flex justify-center mt-3 mb-6">
          <img src={logo} alt="" className="" />
        </div>
        <p className="mb-4 text-gray-700">
          Descubre una nueva manera de gestionar tus ahorros e inversiones.
        </p>
        <p className="mb-6 text-gray-700">
          Completemos juntos unos pasos iniciales para personalizar tu
          experiencia financiera.
        </p>
      </div>
  
    </div>
  );
};

export default OnboardingStep1;
