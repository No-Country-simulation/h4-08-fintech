import { Link } from "react-router-dom";
import logo from "../../assets/logo-completo.png";

const AuthHome = () => {
  return (
    <div>
          <div className="flex flex-col items-center justify-center h-screen">
        <img src={logo} alt="" />
        <div className="w-full ">
          <h2 className="text-[32px] text-center font-bold mt-20 mb-12">¡Bienvenido!</h2>
          <div className="mx-3">
          <Link to={'/login'}>
            <button className="w-full px-3 py-2 font-semibold text-white bg-blue-500 shadow-sm rounded-2xl text-sm/6 hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 ">
              Iniciar Sesión
            </button>
            </Link>
            <Link to={'/registro'}>
            <button className="flex items-center justify-center w-full px-3 py-2 my-4 font-semibold text-blue-600 border border-blue-600 rounded-2xl hover:bg-blue-500 hover:text-white">
              Crear Cuenta
            </button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AuthHome;