import { useEffect, useState } from "react";
import {
  ChevronLeft,
  Home,
  Plane,
  PiggyBank,
  TrendingUp,
  Wallet2,
  GraduationCap,
} from "lucide-react";
import { OnboardingCard } from "../common/OnboardingCard";
import { useDispatch, useSelector } from "react-redux";
import { setFinancialGoals } from "../../../redux/slices/onboardingSlice";

export const FinancialGoals = ({ setAvailableToContinue }) => {
  const [selectedGoals, setSelectedGoals] = useState([]);

  const goals = [
    {
      id: "vacaciones",
      title: "Vacaciones",
      icon: Plane,
    },
    {
      id: "hogar",
      title: "Hogar",
      icon: Home,
    },
    {
      id: "ahorrar",
      title: "Ahorrar",
      icon: PiggyBank,
    },
    {
      id: "invertir",
      title: "Invertir",
      icon: TrendingUp,
    },
    {
      id: "deudas",
      title: "Deudas",
      icon: Wallet2,
    },
    {
      id: "educacion",
      title: "Educación",
      icon: GraduationCap,
    },
  ];

  const toggleGoal = (goalId) => {
    if (selectedGoals.includes(goalId)) {
      setSelectedGoals(selectedGoals.filter((id) => id !== goalId));
    } else if (selectedGoals.length < 3) {
      setSelectedGoals([...selectedGoals, goalId]);
    }
  };

  const {financialKnowledge} = useSelector((state) => state.onBoarding)

  console.log(financialKnowledge)

  useEffect(() => {
    if (selectedGoals.length >= 1 && selectedGoals.length <= 3) {
      setAvailableToContinue(true);
    }
  }, [selectedGoals]);

  return (
    <OnboardingCard
      title={"¿Cuáles son tus principales objetivos financieros?"}
      content={
        "Lorem ipsum dolor sit amet consectetur. Pulvinar augue vestibulum commodo lorem pharetra pretium eget velit quam."
      }
    >
      <div className="space-y-4">
        <div className="grid grid-cols-2 gap-3">
          {goals.map((goal) => {
            const Icon = goal.icon;
            const isSelected = selectedGoals.includes(goal.id);

            return (
              <button
                key={goal.id}
                onClick={() => toggleGoal(goal.id)}
                className={`flex items-center gap-2 p-3 rounded-[14px] border transition-all ${
                  isSelected
                    ? "border-[#0051FF] bg-[#F5F9FF] ring-1 ring-[#0051FF]"
                    : "border-gray-200 bg-white"
                }`}
              >
                <div
                  className={`w-5 h-5 ${
                    isSelected ? "text-[#0051FF]" : "text-gray-600"
                  }`}
                >
                  <Icon size={20} strokeWidth={1.5} />
                </div>
                <span
                  className={`text-[15px] font-medium ${
                    isSelected ? "text-[#0051FF]" : "text-gray-800"
                  }`}
                >
                  {goal.title}
                </span>
              </button>
            );
          })}
        </div>

        <div className="text-center text-[13px] text-gray-500 mt-2">
          Selecciona hasta 3 objetivos
        </div>
      </div>
    </OnboardingCard>
  );
};

export default FinancialGoals;
