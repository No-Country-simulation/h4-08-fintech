import { useState } from 'react'
import { ChevronLeft, Home, Plane, PiggyBank, TrendingUp, Wallet2, GraduationCap } from 'lucide-react'

export default function FinancialGoals() {
  const [selectedGoals, setSelectedGoals] = useState([])

  const goals = [
    {
      id: 'vacaciones',
      title: 'Vacaciones',
      icon: Plane
    },
    {
      id: 'hogar',
      title: 'Hogar',
      icon: Home
    },
    {
      id: 'ahorrar',
      title: 'Ahorrar',
      icon: PiggyBank
    },
    {
      id: 'invertir',
      title: 'Invertir',
      icon: TrendingUp
    },
    {
      id: 'deudas',
      title: 'Deudas',
      icon: Wallet2
    },
    {
      id: 'educacion',
      title: 'Educación',
      icon: GraduationCap
    }
  ]

  const toggleGoal = (goalId) => {
    if (selectedGoals.includes(goalId)) {
      setSelectedGoals(selectedGoals.filter(id => id !== goalId))
    } else if (selectedGoals.length < 3) {
      setSelectedGoals([...selectedGoals, goalId])
    }
  }

  return (
    <div className="min-h-screen bg-gradient-to-b from-[#E8F3FF] via-[#E3FFE7] to-[#DAFFDF] p-4">
      {/* Back button */}
      <button className="w-10 h-10 rounded-full bg-white/90 shadow-[0_2px_8px_rgba(0,0,0,0.05)] flex items-center justify-center">
        <ChevronLeft className="w-5 h-5 text-gray-600" strokeWidth={2} />
      </button>

      <div className="mt-8 space-y-4">
        <h1 className="text-[22px] font-semibold text-center px-8 text-gray-800">
          ¿Cuáles son tus principales objetivos financieros?
        </h1>
        
        <p className="text-center text-gray-500 text-[15px] px-8 leading-[1.4]">
          Lorem ipsum dolor sit amet consectetur. Pulvinar augue vestibulum commodo lorem pharetra pretium eget velit quam.
        </p>

        <div className="grid grid-cols-2 gap-3 mt-6">
          {goals.map((goal) => {
            const Icon = goal.icon
            const isSelected = selectedGoals.includes(goal.id)
            
            return (
              <button
                key={goal.id}
                onClick={() => toggleGoal(goal.id)}
                className={`flex items-center gap-2 p-3 rounded-[14px] border transition-all ${
                  isSelected
                    ? 'border-[#0051FF] bg-[#F5F9FF] ring-1 ring-[#0051FF]'
                    : 'border-gray-200 bg-white'
                }`}
              >
                <div className={`w-5 h-5 ${isSelected ? 'text-[#0051FF]' : 'text-gray-600'}`}>
                  <Icon size={20} strokeWidth={1.5} />
                </div>
                <span className={`text-[15px] font-medium ${
                  isSelected ? 'text-[#0051FF]' : 'text-gray-800'
                }`}>
                  {goal.title}
                </span>
              </button>
            )
          })}
        </div>

        <div className="text-center text-[13px] text-gray-500 mt-2">
          Selecciona hasta 3 objetivos
        </div>

        <div className="fixed bottom-0 left-0 right-0 p-4 bg-transparent">
          <div className="flex justify-center gap-[6px] mb-4">
            <div className="w-8 h-1.5 rounded-full bg-[#0051FF]" />
            <div className="w-8 h-1.5 rounded-full bg-gray-200" />
            <div className="w-8 h-1.5 rounded-full bg-gray-200" />
            <div className="w-8 h-1.5 rounded-full bg-gray-200" />
            <div className="w-8 h-1.5 rounded-full bg-gray-200" />
          </div>
          
          <button
            className={`w-full h-[52px] rounded-[14px] font-medium text-[15px] transition-colors ${
              selectedGoals.length > 0
                ? 'bg-[#0051FF] text-white'
                : 'bg-gray-100 text-gray-400'
            }`}
            disabled={selectedGoals.length === 0}
          >
            Continuar
          </button>
        </div>
      </div>
    </div>
  )
}
