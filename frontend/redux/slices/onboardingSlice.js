import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  "financialKnowledge": null,
  "riskTolerance": null,
  "estimatedIncome": null,
  "estimatedExpenses": null,
  "savingsCapacity": null,
  "financialGoalIds": null, //[1, 2, 3]
  "fullName": null,
  "phoneNumber": null
}

const onBoardingSlice = createSlice({
  name: 'onBoarding',
  initialState,
  reducers: {
    setFinancialKnowledge: (state, value) => {
      state.financialKnowledge = value;
    },
    setSavingsCapacity: (state, value) => {
      state.savingsCapacity = value;
    },
    setEstimatedExpenses: (state, value) => {
      state.estimatedExpenses = value;
    },
    setEstimatedIncome: (state, value) => {
      state.estimatedIncome = value;
    },
    setFinancialGoals: (state, value) => {
      state.financialGoalIds = value;
    },
    setTolerance: (state, value) => {
      state.riskTolerance = value;
    },
    setPhoneNumber: (state, value) => {
      state.phoneNumber = value;
    },
    setFullName: (state, value) => {
      state.fullName = value
    },
    resetState: () => initialState
  },
});

export const { 
               resetState,
               setFinancialKnowledge,
               setSavingsCapacity,
               setTolerance,
               setFinancialGoals,
               setEstimatedIncome,
               setEstimatedExpenses,
               setPhoneNumber,
               setFullName
              } = onBoardingSlice.actions;

export default onBoardingSlice.reducer;