import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Login from './pages/Auth/Login';
import { Onboarding } from '/src/pages/Onboarding/Onboarding.jsx';
import Register from './pages/Auth/Register';
import Home from './pages/Auth/Home'
export default function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path='/login' element={<Login/>} />
        <Route path='/registro' element={<Register/>} />
        <Route path='/onboarding' element={<Onboarding/>} />
       
      </Routes>
    </Router>
  );
}
