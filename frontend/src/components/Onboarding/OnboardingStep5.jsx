import { FaSackDollar } from "react-icons/fa6";
import { GiTakeMyMoney } from "react-icons/gi";
import { OnboardingCard } from "../common/OnboardingCard";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { setEstimatedIncome } from "../../../redux/slices/onboardingSlice";
import { setEstimatedExpenses } from "../../../redux/slices/onboardingSlice";

export const OnboardingStep5 = () => {
  const [ingresos, setIngresos] = useState(0.0);
  const [gastos, setGastos] = useState(0.0);
  const [editingIngreso, setEditingIngreso] = useState(false);
  const [editingGasto, setEditingGasto] = useState(false);
  const dispatch = useDispatch();


  const handleIngresoChange = (e) => {
    const value = parseFloat(e.target.value);
    if (!isNaN(value)) {
      setIngresos(value);
      dispatch(setEstimatedIncome(value));
    }
  };

  const handleGastoChange = (e) => {
    const value = parseFloat(e.target.value);
    if (!isNaN(value)) {
      setGastos(value);
      dispatch(setEstimatedExpenses(value));
    }
  };

  const handleFocus = (e) => {
    if (e.target.value === "0" || e.target.value === "0.00") {
      e.target.value = "";
    }
  };

  const handleBlurIngreso = (e) => {
    setEditingIngreso(false);
    if (e.target.value === "") {
      setIngresos(0.0);
    }
  };

  const handleBlurGasto = (e) => {
    setEditingGasto(false);
    if (e.target.value === "") {
      setGastos(0.0);
    }
  };

  return (
    <OnboardingCard
      title={"¿Proporcionar un estimado de tus ingresos y gastos mensuales?"}
      content={
        "Lorem ipsum dolor sit amet consectetur. Pulvinar augue volutpat commodo lorem pharetra pretium eget varius quam."
      }
    >
      <div className="space-y-10 text-center">
        {/* Ingresos editable */}
        <span
          className="text-4xl font-bold text-gray-900 cursor-pointer"
          onClick={() => setEditingIngreso(true)}
        >
          {editingIngreso ? (
            <input
              type="number"
              defaultValue={ingresos}
              onChange={handleIngresoChange}
              onBlur={handleBlurIngreso}
              onFocus={handleFocus}
              step="0.01"
              className="text-4xl font-bold text-gray-900 bg-transparent border-none outline-none appearance-none no-arrows"
              autoFocus
            />
          ) : (
            `$${ingresos.toFixed(2)}`
          )}
        </span>

        {/* Botón con el ícono de FaSackDollar */}
        <div className="flex justify-center mt-3 mb-10">
          <button
            onClick={() => console.log("FaSackDollar button clicked")}
            className="flex items-center gap-2 p-3 rounded-[14px] border transition-all border-gray-200 bg-white"
          >
            <FaSackDollar
              size={20}
              strokeWidth={1.5}
              className="text-gray-600"
            />
            <span className="text-[15px] font-medium text-gray-800">
              Ingresos mensuales
            </span>
          </button>
        </div>

        {/* Gastos editable */}
        <span
          className="text-4xl font-bold text-gray-900 cursor-pointer"
          onClick={() => setEditingGasto(true)}
        >
          {editingGasto ? (
            <input
              type="number"
              defaultValue={gastos}
              onChange={handleGastoChange}
              onBlur={handleBlurGasto}
              onFocus={handleFocus}
              step="0.01"
              className="text-4xl font-bold text-gray-900 bg-transparent border-none outline-none appearance-none no-arrows"
              autoFocus
            />
          ) : (
            `$${gastos.toFixed(2)}`
          )}
        </span>

        {/* Botón con el ícono de GiTakeMyMoney */}
        <div className="flex justify-center mt-3 mb-10">
          <button
            onClick={() => console.log("GiTakeMyMoney button clicked")}
            className="flex items-center gap-2 p-3 rounded-[14px] border transition-all border-gray-200 bg-white"
          >
            <GiTakeMyMoney
              size={20}
              strokeWidth={1.5}
              className="text-gray-600"
            />
            <span className="text-[15px] font-medium text-gray-800">
              Gastos mensuales
            </span>
          </button>
        </div>
      </div>
    </OnboardingCard>
  );
};

export default OnboardingStep5;
