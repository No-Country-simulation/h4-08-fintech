import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import { Onboarding } from './pages/Onboarding/Onboarding';
/* import OnboardingStep1 from './components/Onboarding/OnboardingStep1';
import OnboardingStep2 from './components/Onboarding/OnboardingStep2';
import OnboardingStep3 from "./components/Onboarding/OnboardingStep3" */
export default function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path='/login' element={<Login/>} />
        <Route path='/registro' element={<Home/>} />
        <Route path='/onboarding' element={<Onboarding/>} />
        {/* <Route path='/onboarding1' element={<OnboardingStep1/>} />
        <Route path='/onboarding2' element={<OnboardingStep2/>} />
        <Route path='/onboarding3' element={<OnboardingStep3/>} /> */}
      </Routes>
    </Router>
  );
}
