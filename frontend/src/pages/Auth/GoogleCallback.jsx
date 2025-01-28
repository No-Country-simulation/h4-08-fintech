import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { checkAuth } from '../../services/api/auth/checkAuth';

const GoogleCallback = () => {
  const navigate = useNavigate();

  useEffect(() => {

    const check = async () => {
      await checkAuth();
      console.log("checking");
      

      setTimeout(() => navigate('/onboarding'),2000)
    }

    check();

  }, []);

  return <div>Procesando el inicio de sesión...</div>;
};

export default GoogleCallback;
