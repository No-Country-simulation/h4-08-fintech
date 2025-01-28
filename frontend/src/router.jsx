import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Auth/Login";
import Register from "./pages/Auth/Register";
import { Onboarding } from "./pages/Onboarding/Onboarding";
/* import OnboardingStep1 from "./components/Onboarding/OnboardingStep1";
import OnboardingStep2 from "./components/Onboarding/OnboardingStep2";
import OnboardingStep3 from "./components/Onboarding/OnboardingStep3";
import OnboardingStep4 from "./components/Onboarding/OnboardingStep4";
import OnboardingStep5 from "./components/Onboarding/OnboardingStep5"; 
import { OnboardingStep6 } from "./components/Onboarding/OnboardingStep6";*/

import { Dashboard } from "./pages/Dashboard/Dashboard";
import { Investments } from "./pages/Dashboard/Investments";
import { MainLayout } from "./layouts/MainLayout";
import { Error404 } from "./pages/Error404";
import { Notifications } from "./pages/Dashboard/Notifications"
import { Home } from "./pages/Home";
import AuthHome from "./pages/Auth/AuthHome";
import { HomeLayout } from "./layouts/HomeLayout";

import { LandingPage2 } from "./pages/LandingPage/LandingPage2";

export default function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomeLayout/>}>
          <Route index element={<Home />} />
        </Route>
        <Route path="/auth" element={<AuthHome />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Register />} />
        <Route path="/onboarding" element={<Onboarding />} />
        {/* <Route path="/onboarding1" element={<OnboardingStep1 />} />
        <Route path="/onboarding2" element={<OnboardingStep2 />} />
        <Route path="/onboarding3" element={<OnboardingStep3 />} />
        <Route path="/onboarding4" element={<OnboardingStep4 />} />
        <Route path="/onboarding5" element={<OnboardingStep5 />} /> 
        <Route path="/onboarding6" element={<OnboardingStep6 />} />*/}

        <Route path="/dashboard" element={<MainLayout/>}>
          <Route index element={<Dashboard />} />
          <Route path="notificaciones" element={<Notifications />}/>
          <Route path="inversiones" element={<Investments />} /> 
        </Route>
        <Route path="/landing" element={<LandingPage2/>} />
        <Route path="*" element={<Error404 />} />
      </Routes>
    </Router>
  );
}
