import logo from "../../assets/icons/logo/iUpi.svg";
const OnboardingStep1 = () => {
  return (
    <div data-aos="flip-left">
      <div className="flex flex-col backdrop-blur-md bg-white/50 rounded-lg shadow-lg p-8 max-w-sm  text-center">
        <h2 className="text-2xl font-bold text-black mb-4">Bienvenido a</h2>
        <div className="flex justify-center mt-3 mb-6">
          <img src={logo} alt="" className="" />
        </div>
        <p className="text-gray-700 mb-4">
          Descubre una nueva manera de gestionar tus ahorros e inversiones.
        </p>
        <p className="text-gray-700 mb-6">
          Completemos juntos unos pasos iniciales para personalizar tu
          experiencia financiera.
        </p>
      </div>
  
    </div>
  );
};

export default OnboardingStep1;
