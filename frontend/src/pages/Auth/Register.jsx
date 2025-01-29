import { FaRegEyeSlash, FaRegEye, FaApple } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as yup from "yup";
import { useState } from "react";
import { handleAuthGoogleLogin } from "../../services/api/auth/authGoogle";

const Register = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [showRepeatPassword, setShowRepeatPassword] = useState(false);

  const initialValues = {
    fullName: "",
    email: "",
    password: "",
    repeatPassword: "",
  }

  const handleGoogleRegister = () => {
    console.log("registrando");
    
    handleAuthGoogleLogin();
  }

  const validationSchema = yup.object({
      fullName: yup.string().required("El nombre  y apellido es obligatorio").min(5, "El nombre debe tener al menos 5 caracteres").max(50, "El nombre no puede tener más de 50 caracteres"),
      username: yup.string().required("El nombre de usuario es obligatorio").min(5, "El nombre de usuario debe tener al menos 5 caracteres").max(20, "El nombre de usuario no puede tener más de 50 caracteres"),
      email: yup.string().required("El email es obligatorio").email("El email no es válido"),
      password: yup.string().required("La contraseña es obligatoria").min(8, "La contraseña debe tener al menos 8 caracteres"),
      repeatPassword: yup.string().required("La contraseña es obligatoria").oneOf([yup.ref('password'), null], 'Las contraseñas deben coincidir')
  })

  const togglePassword = () => {
    setShowPassword(!showPassword);
  };

  const toggleRepeatPassword = () => {
    setShowRepeatPassword(!showRepeatPassword);
  };

  const handleSubmit = (data) => {
    console.log(data);
  };

  return (
    <>
      <div className="container flex flex-col items-center justify-center p-6 mx-auto">
        <h2 className="text-[32px] font-bold mb-12" data-aos="zoom-in" data-aos-duration="400">Crear cuenta</h2>
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          handleSubmit={handleSubmit}>
          {({errors, touched}) => (
            <Form className="w-full" data-aos="zoom-in" data-aos-duration="400">
            <div className="mb-4">
              <label className="block text-sm font-medium text-gray-700 text-start">
                Nombre y Apellido
              </label>
              <Field
                type="text"
                placeholder="Juan Paez"
                name="fullName"
                className={`mt-1 block w-full px-3 py-2 border ${errors.fullName && touched.fullName ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="fullName" component="span" className="mt-1 text-xs text-red-500" />
            </div>
            <div className="mb-4">
              <label className="block text-sm font-medium text-gray-700 text-start">
                Nombre de Usuario
              </label>
              <Field
                type="text"
                placeholder="juanpaez"
                name="username"
                className={`mt-1 block w-full px-3 py-2 border ${errors.username && touched.username ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="username" component="span" className="mt-1 text-xs text-red-500" />
            </div>
            <div className="mb-4">
              <label className="block text-sm font-medium text-gray-700 text-start">
                Email
              </label>
              <Field
                type="email"
                placeholder="email@email.com"
                name="email"
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
                className={`mt-1 block w-full px-3 py-2 border ${errors.password && touched.password ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="password" component="span" className="mt-1 text-xs text-red-500" />
              <button
                type="button"
                onClick={togglePassword}
                className="absolute flex items-center inset-y-10 right-3"
              >
              {showPassword ? (
                <FaRegEye className="text-lg" />
              ) : (
                <FaRegEyeSlash className="text-lg" />
              )}
              </button>
              <p className="mt-2 text-sm text-left text-gray-400">
                8 caracteres.
              </p>
            </div>
            <div className="relative mb-4">
              <label className="block text-sm font-medium text-gray-700 text-start">
                Repetir contraseña
              </label>
              <Field
                placeholder="********"
                type={showRepeatPassword ? "text" : "password"}
                name="repeatPassword" 
                className={`mt-1 block w-full px-3 py-2 border ${errors.repeatPassword && touched.repeatPassword ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="repeatPassword" component="span" className="mt-1 text-xs text-red-500" />

              <button
                type="button"
                onClick={toggleRepeatPassword}
                className="absolute flex items-center inset-y-10 right-3"
              >
              {showRepeatPassword ? (
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
                Crear cuenta
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
        <div></div>{" "}
        <button className="flex items-center justify-center w-full px-3 py-2 my-2 font-semibold text-blue-600 border border-blue-600 rounded-2xl hover:bg-blue-500 hover:text-white">
          <FcGoogle className="text-2xl me-2" 
          onClick={handleGoogleRegister}/>
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

export default Register;
