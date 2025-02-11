import { FaRegEye, FaRegEyeSlash } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";
import { FaApple } from "react-icons/fa";
import { useState } from "react";

import { ErrorMessage, Field, Form, Formik } from "formik";
import * as yup from "yup";
import { handleAuthGoogleLogin } from "../../services/api/auth/authGoogle";

const Login = () => {

  const [showPassword, setShowPassword] = useState(false);

  const initialValues = {
    email: "",
    password: ""
  }

  const validationSchema = yup.object({
    email: yup.string().required("El email es obligatorio").email("El email no es válido"),
    password: yup.string().required("La contraseña es obligatoria")
  })

  const togglePassword = () => {
    setShowPassword(!showPassword);
  };

  const handleGoogleRegister = () => {
    handleAuthGoogleLogin();
  }

  const handleSubmit = (data) => {
    console.log(data);
    const userTest = {
      email: "prueba@gmail.com",
      password: "12345678",
    };
    if (userTest.email === data.email && userTest.password === data.password) {
      console.log("inicio de sesion correcto");

    } else {
      alert("error al iniciar");
    }
  };

  return (
    <>
      <div className="container flex flex-col items-center justify-center p-6 mx-auto">
        <h2 className="text-[32px] font-bold mb-12" data-aos="zoom-in" data-aos-duration="400">Iniciar Sesión</h2>
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={handleSubmit}
        >
          {({ errors, touched }) => (
            <Form className="w-full" data-aos="zoom-in" data-aos-duration="400">
              <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700 text-start">
                  Email
                </label>
                <Field
                  type="email"
                  name="email"
                  placeholder="email@email.com"
                  className={`mt-1 block w-full px-3 py-2 border ${errors.email && touched.email ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
                />
                <ErrorMessage name="email" component="span" className="mt-1 text-xs text-red-500" />
              </div>
              <div className="relative mb-4">
                <label className="block text-sm font-medium text-gray-700 text-start">
                  Contraseña
                </label>
                <Field
                  placeholder="********"
                  type={showPassword ? "text" : "password"}
                  name="password"
                  className={`block mt-1 w-full px-3 py-2 border ${errors.password && touched.password ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
                />
                <ErrorMessage name="password" component="span" className="text-xs text-red-500 text-start" />

                <button
                  type="button"
                  onClick={togglePassword}
                  className="absolute flex items-center inset-y-11 right-3"
                >
                  {showPassword ? (
                    <FaRegEye className="text-lg" />
                  ) : (
                    <FaRegEyeSlash className="text-lg" />
                  )}
                </button>
              </div>
              <div className="my-8">
                <button
                  type="submit"
                  className="flex justify-center w-full px-3 py-2 font-semibold text-white bg-blue-500 shadow-sm rounded-2xl text-sm/6 hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 "
                >
                  Iniciar Sesion
                </button>
              </div>
            </Form>
          )}
        </Formik>
        <div className="flex items-center justify-center w-full my-4">
          <div className="flex-grow border-t border-gray-300"></div>
          <span className="mx-4 text-gray-500">o</span>
          <div className="flex-grow border-t border-gray-300"></div>
        </div>
        <button className="flex items-center justify-center w-full px-3 py-2 my-2 font-semibold text-blue-600 border border-blue-600 rounded-2xl hover:bg-blue-500 hover:text-white">
          <FcGoogle className="text-2xl me-2"
          onClick={handleGoogleRegister} />
          Continuar con Google
        </button>
        <button className="flex items-center justify-center w-full px-3 py-2 my-2 font-semibold text-blue-600 border border-blue-600 rounded-2xl hover:bg-blue-500 hover:text-white">
          <FaApple className="text-2xl text-black me-2" />
          Continuar con Apple Id
        </button>
      </div>
    </>
  );
};

export default Login;
