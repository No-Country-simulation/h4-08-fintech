"use client"
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { checkAuth } from '../../services/api/auth/checkAuth';
import { Loader } from 'lucide-react';

const GoogleCallback = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const check = async () => {
      const user = await checkAuth();
      console.log("checking", user);

      if (user?.hasCompletedOnboarding) {
        navigate('/dashboard');
      } else {
        navigate('/onboarding');
      }
    };

    check();
  }, [navigate]);

  return (
    <div className="flex flex-col items-center justify-center h-screen w-screen bg-white px-5 font-jakarta text-center">
      <h1 className="text-2xl font-bold text-blue-600">iUpi</h1>
      <h2 className="text-lg text-purple-600 mt-4">PAGOS FÁCILES Y AL INSTANTE</h2>
      <p className="text-xl font-semibold mt-2">
        Procesando el inicio de sesión...
      </p>
      <p className="text-gray-600 mt-2">Por favor, espera un momento</p>
      <Loader className="mt-6" />
    </div>
  );
};

export default GoogleCallback;
