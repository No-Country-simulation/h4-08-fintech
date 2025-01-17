import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import { Onboarding } from './pages/Onboarding/Onboarding';
import {Registro} from './pages/Registro'
export default function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro/>} />
        <Route path='/onboarding' element={<Onboarding/>} />
      </Routes>
    </Router>
  );
}
