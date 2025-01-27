import { configureStore } from '@reduxjs/toolkit';
import counterReducer from './features/counter/counterSlice';
//recuerden crear una "slice" para cada aspecto de la aplicación que quieran manejar con Redux
//en la slice definiran el estado inicial, las acciones y los reducers para cada slice

export const store = configureStore({
  reducer: {
    counter: counterReducer, // aqui agregan los slices 
  },
});