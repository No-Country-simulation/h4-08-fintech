import { Link } from "react-router-dom";
import logo from "../assets/logo-completo.png";

const Home = () => {
  return (
    <div>
          <div className="h-screen flex flex-col justify-center items-center">
        <img src={logo} alt="" />
        <div className="w-full ">
          <h2 className="text-[32px] text-center font-bold mt-20 mb-12">¡Bienvenido!</h2>
          <div className="mx-3">
          <Link to={'/login'}>
            <button className="w-full rounded-2xl bg-blue-500 px-3 py-2 text-sm/6 font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 ">
              Iniciar Sesión
            </button>
            </Link>
            <Link to={'/registro'}>
            <button className="w-full my-4 flex items-center justify-center border border-blue-600 text-blue-600 font-semibold px-3 py-2 rounded-2xl hover:bg-blue-500 hover:text-white">
              Crear Cuenta
            </button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;