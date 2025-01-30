import { FaRegEyeSlash, FaRegEye, FaApple } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as yup from "yup";
import { useState } from "react";
import { handleAuthGoogleLogin } from "../../services/api/auth/authGoogle";
import {URL_API} from "../../../vars.js"
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

  const handleSubmit = async (data) => {
    try{
      const response = await fetch (`${URL_API}/auth/register`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
      })
      if (!response.ok) {
        console.log("respuesta ==>", response)
      }
      const result = await response.json();
      window.alert('Registro exitoso');
      setTimeout(() => {
        window.location.href = '/login';
      }, 5000);    }
    catch(error){
      console.error('Error:', error);
    }
  };

  return (
    <>
      <div className="container mx-auto p-6 flex flex-col justify-center items-center">
        <h2 className="text-[32px] font-bold mb-12">Crear cuenta</h2>
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={handleSubmit}>
          {({errors, touched}) => (
            <Form className="w-full">
            <div className="mb-4">
              <label className="block text-start text-sm font-medium text-gray-700">
                Nombre y Apellido
              </label>
              <Field
                type="text"
                placeholder="Juan Paez"
                name="fullName"
                className={`mt-1 block w-full px-3 py-2 border ${errors.fullName && touched.fullName ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="fullName" component="span" className="text-red-500 text-xs mt-1" />
            </div>
            <div className="mb-4">
              <label className="block text-start text-sm font-medium text-gray-700">
                Nombre de Usuario
              </label>
              <Field
                type="text"
                placeholder="juanpaez"
                name="username"
                className={`mt-1 block w-full px-3 py-2 border ${errors.username && touched.username ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="username" component="span" className="text-red-500 text-xs mt-1" />
            </div>
            <div className="mb-4">
              <label className="block text-start text-sm font-medium text-gray-700">
                Email
              </label>
              <Field
                type="email"
                placeholder="email@email.com"
                name="email"
                className={`mt-1 block w-full px-3 py-2 border ${errors.email && touched.email ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="email" component="span" className="text-red-500 text-xs mt-1" />
            </div>
            <div className="relative mb-4">
              <label className="block text-start text-sm font-medium text-gray-700">
                Contraseña
              </label>
              <Field
                placeholder="********"
                type={showPassword ? "text" : "password"}
                name="password"
                className={`mt-1 block w-full px-3 py-2 border ${errors.password && touched.password ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="password" component="span" className="text-red-500 text-xs mt-1" />
              <button
                type="button"
                onClick={togglePassword}
                className="absolute inset-y-10 right-3 flex items-center"
              >
              {showPassword ? (
                <FaRegEye className="text-lg" />
              ) : (
                <FaRegEyeSlash className="text-lg" />
              )}
              </button>
              <p className="mt-2 text-sm text-gray-400 text-left">
                8 caracteres.
              </p>
            </div>
            <div className="relative mb-4">
              <label className="block text-start text-sm font-medium text-gray-700">
                Repetir contraseña
              </label>
              <Field
                placeholder="********"
                type={showRepeatPassword ? "text" : "password"}
                name="repeatPassword" 
                className={`mt-1 block w-full px-3 py-2 border ${errors.repeatPassword && touched.repeatPassword ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
              />
              <ErrorMessage name="repeatPassword" component="span" className="text-red-500 text-xs mt-1" />

              <button
                type="button"
                onClick={toggleRepeatPassword}
                className="absolute inset-y-10 right-3 flex items-center"
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
                className="flex w-full justify-center rounded-2xl bg-blue-500 px-3 py-2 text-sm/6 font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 "
              >
                Crear cuenta
              </button>
            </div>
          </Form>
        )}
        </Formik>
        <div className="flex items-center justify-center my-4 w-full">
          <div className="flex-grow border-t border-gray-300"></div>
          <span className="mx-4 text-gray-500">o</span>
          <div className="flex-grow border-t border-gray-300"></div>
        </div>
        <div></div>{" "}
        <button className="w-full my-2 flex items-center justify-center border border-blue-600 text-blue-600 font-semibold px-3 py-2 rounded-2xl hover:bg-blue-500 hover:text-white">
          <FcGoogle className="text-2xl me-2" 
          onClick={handleGoogleRegister}/>
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

export default Register;
