import './App.css'
import AppRouter from './router.jsx'
import { BrowserRouter as Router } from "react-router-dom";
import { Provider } from "react-redux";
import { store } from "../redux/store";

function App() {

  return (
      <Router >
      <Provider store={store}>
        <AppRouter />
      </Router>
  )
}

export default App;
