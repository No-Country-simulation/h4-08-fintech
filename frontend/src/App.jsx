import "./App.css";
import Inicio from "./pages/Inicio";
import Login from "./pages/Login";

function App() {
  return (
    <div>
      <h1 className="bg-black text-cyan-50">Home</h1>
      <Inicio />{" "}
      <Login></Login>
    </div>
  );
}

export default App;
