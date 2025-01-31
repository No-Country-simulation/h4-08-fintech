import { FaRegEye, FaRegEyeSlash } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";
import { FaApple } from "react-icons/fa";
import { useState } from "react";
import { loginUser } from "../../services/api/auth/authLogin";
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
    const {email, password} = data;
    loginUser(email, password)
  };

  return (
    <>
      <div className="container mx-auto p-6 flex flex-col justify-center items-center">
        <h2 className="text-[32px] font-bold mb-12">Iniciar Sesión</h2>
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={handleSubmit}
        >
          {({ errors, touched }) => (
            <Form className="w-full">
              <div className="mb-4">
                <label className="block text-start text-sm font-medium text-gray-700">
                  Email
                </label>
                <Field
                  type="email"
                  name="email"
                  placeholder="email@email.com"
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
                  className={`block mt-1 w-full px-3 py-2 border ${errors.password && touched.password ? 'border-red-500' : 'border-gray-300'} rounded-xl shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm`}
                />
                <ErrorMessage name="password" component="span" className="text-start text-red-500 text-xs" />

                <button
                  type="button"
                  onClick={togglePassword}
                  className="absolute inset-y-11 right-3 flex items-center"
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
                  className="flex w-full justify-center rounded-2xl bg-blue-500 px-3 py-2 text-sm/6 font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 "
                >
                  Iniciar Sesion
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
        <button className="w-full my-2 flex items-center justify-center border border-blue-600 text-blue-600 font-semibold px-3 py-2 rounded-2xl hover:bg-blue-500 hover:text-white">
          <FcGoogle className="text-2xl me-2"
          onClick={handleGoogleRegister} />
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
