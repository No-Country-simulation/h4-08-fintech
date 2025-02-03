import './App.css'
import AppRouter from './router.jsx'
import { Provider } from "react-redux";
import { store } from "../redux/store";


function App() {

  return (
        <Provider store={store}>
        <AppRouter />
        </Provider>
  )
}

export default App;
