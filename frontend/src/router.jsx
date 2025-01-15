import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import { Onboarding } from './pages/Onboarding/Onboarding';
import OnboardingStep1 from './components/Onboarding/OnboardingStep1';

export default function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path='/login' element={<Login/>} />
        <Route path='/registro' element={<Home/>} />
        <Route path='/onboarding' element={<Onboarding/>} />
        <Route path='/onboarding1' element={<OnboardingStep1/>} />
      </Routes>
    </Router>
  );
}