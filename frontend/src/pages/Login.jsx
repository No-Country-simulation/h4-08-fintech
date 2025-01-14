import { FaRegEyeSlash } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";
import { FaApple } from "react-icons/fa";
import { useForm } from "react-hook-form";

const Login = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data) => {
    console.log(data); 
  };

  return (
    <>
      <div className="container mx-auto p-6 flex flex-col justify-center items-center">
        <h2 className="text-[32px] font-bold mt-20 mb-12">Iniciar Sesión</h2>
        <form onSubmit={handleSubmit(onSubmit)} className="w-full">
          {" "}
          <div className="mb-4">
            <label className="block text-start text-sm font-medium text-gray-700">
              Email
            </label>
            <input
              type="email"
              placeholder="email@email.com"
              {...register("email", {
                required: "El email es obligatorio",
                pattern: {
                  value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i,
                  message: "El email no es válido",
                },
              })}
              className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            />
            {errors.email && (
              <p className="text-red-500 text-xs mt-1">
                {errors.email.message}
              </p>
            )}
          </div>
          <div className="relative mb-4">
            <label className="block text-start text-sm font-medium text-gray-700">
              Contraseña
            </label>
            <input
              placeholder="********"
              type="password"
              {...register("password", {
                required: "La contraseña es obligatoria",
              })}
              className="block mt-1 w-full px-3 py-2 border border-gray-300 rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            />
            {errors.password && (
              <p className="text-red-500 text-xs mt-1">
                {errors.password.message}
              </p>
            )}

            <button
              type="button"
              className="absolute inset-y-10 right-3 flex items-center"
            >
              <FaRegEyeSlash className="text-lg" />
            </button>
          </div>
          <div className="my-8">
            <button
              type="submit"
              className="flex w-full justify-center rounded-2xl bg-blue-500 px-3 py-2 text-sm/6 font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 "
            >
              Iniciar Sesion
            </button>
          </div>
        </form>
        <div className="flex items-center justify-center my-4 w-full">
          <div className="flex-grow border-t border-gray-300"></div>
          <span className="mx-4 text-gray-500">o</span>
          <div className="flex-grow border-t border-gray-300"></div>
        </div>
        <div></div>{" "}
        <button className="w-full my-2 flex items-center justify-center border border-blue-600 text-blue-600 font-semibold px-3 py-2 rounded-2xl hover:bg-blue-500 hover:text-white">
          <FcGoogle className="text-2xl me-2" />
          Continuar con Google
        </button>
        <button className="w-full my-2 flex items-center justify-center border border-blue-600 text-blue-600 font-semibold px-3 py-2 rounded-2xl hover:bg-blue-500 hover:text-white">
          <FaApple className="text-2xl me-2 text-black" />
          Continuar con Apple Id
        </button>
      </div>
    </>
  );
};

export default Login;
