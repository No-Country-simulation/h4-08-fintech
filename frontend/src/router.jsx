import { Routes, Route, useLocation } from "react-router-dom";
import Login from "./pages/Auth/Login";
import Register from "./pages/Auth/Register";
import { Onboarding } from "./pages/Onboarding/Onboarding";
import { Dashboard } from "./pages/Dashboard/Dashboard";
import { Investments } from "./pages/Dashboard/Investments";
import { MainLayout } from "./layouts/MainLayout";
import { Error404 } from "./pages/Error404";
import { Notifications } from "./pages/Dashboard/Notifications"
import { Home } from "./pages/Home";
import AuthHome from "./pages/Auth/AuthHome";
import { HomeLayout } from "./layouts/HomeLayout";
import GoogleCallback from "./pages/Auth/GoogleCallback";
import { Sidebar } from "./layouts/Sidebar";
import { CSSTransition, TransitionGroup } from "react-transition-group";


export default function AppRouter() {
  const location = useLocation();

  return (
      <TransitionGroup>
      <CSSTransition key={location.key} classNames="fade" timeout={300}>
        <Routes location={location}>
          <Route path="/" element={<HomeLayout/>}>
            <Route index element={<Home />} />
          </Route>
          <Route path="/sidebar" element={<Sidebar />}/>
          <Route path="/auth" element={<AuthHome />} />
          <Route path="/login" element={<Login />} />
          <Route path="/registro" element={<Register />} />
          <Route path="/onboarding" element={<Onboarding />} />
          <Route path="/oauth/success" element={<GoogleCallback/>}/>

          <Route path="/dashboard" element={<MainLayout/>}>
            <Route index element={<Dashboard />} />
            <Route path="notificaciones" element={<Notifications />}/>
            <Route path="inversiones" element={<Investments />} /> 
          </Route>
          <Route path="*" element={<Error404 />} />
        </Routes>
        </CSSTransition>
        </TransitionGroup>
  );
}
