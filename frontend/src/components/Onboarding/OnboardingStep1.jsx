/* eslint-disable react/prop-types */
import logo from "../../assets/icons/logo/iUpi.svg";
const OnboardingStep1 = ({ onNext }) => {
  return (
    <div>
      <div className="flex flex-col backdrop-blur-md mx-3 bg-white/50 rounded-lg shadow-lg p-8 max-w-sm  text-center">
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
      <div className="m-3">
        <button
          onClick={onNext}
          className="flex w-full justify-center rounded-2xl bg-blue-500 px-3 py-2 text-sm/6 font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 "
        >
          Comenzar
        </button>
      </div>
    </div>
  );
};

export default OnboardingStep1;
