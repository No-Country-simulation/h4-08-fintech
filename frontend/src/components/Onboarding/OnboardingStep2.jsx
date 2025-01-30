import { useEffect, useState } from 'react'
import { OnboardingCard } from '../common/OnboardingCard'
import { useDispatch } from "react-redux"
import { setFinancialKnowledge } from '../../../redux/slices/onboardingSlice'

export default function OnboardingStep2({setAvailableToContinue}) {

  const [selectedLevel, setSelectedLevel] = useState('')

  const levels = [
    {
      id: 'basic',
      title: 'Básico',
      value: "básico",
      description: 'Estoy empezando'
    },
    {
      id: 'intermediate',
      title: 'Intermedio',
      value: "intermedio",
      description: 'Conozco conceptos básicos'
    },
    {
      id: 'advanced',
      title: 'Avanzado',
      value: "avanzado",
      description: 'Tengo experiencia en inversiones / finanzas'
    }
  ]

const dispatch = useDispatch()



  useEffect(() => {
    if(selectedLevel !== ''){
        setAvailableToContinue(true)
    }
  }, [selectedLevel])

  return (
    <OnboardingCard 
      title={"¿Cómo describirías tu nivel de conocimiento financiero?"}
      content={"Lorem ipsum dolor sit amet consectetur. Pulvinar augue vestibulum commodo lorem pharetra pretium eget velit quam."}
      >
        <div className="space-y-3">
          {levels.map((level) => (
            <label
              key={level.id}
              className={`block w-full p-2 rounded-[16px] border transition-all ${
                selectedLevel === level.id
                  ? 'border-[#0051FF] bg-[#F5F9FF] ring-1 ring-[#0051FF]'
                  : 'border-gray-200 bg-white'
              }`}
              onClick={() =>{dispatch(setFinancialKnowledge(level.value))}}
            >
              <div className="flex items-center gap-3">
                <div
                  className={`w-[22px] h-[22px] rounded-full border-2 flex items-center justify-center transition-colors ${
                    selectedLevel === level.id
                      ? 'border-[#0051FF]'
                      : 'border-gray-300'
                  }`}
                >
                  {selectedLevel === level.id && (
                    <div className="w-[12px] h-[12px] rounded-full bg-[#0051FF]" />
                  )}
                </div>
                <div>
                  <div className="font-medium text-[15px] text-gray-800 text-start">{level.title}</div>
                  <div className="w-full text-[13px] text-gray-500 text-start">
                    {level.description}
                  </div>
                </div>
              </div>
              <input
                type="radio"
                name="knowledge-level"
                value={level.id}
                checked={selectedLevel === level.id}
                onChange={(e) => setSelectedLevel(e.target.value)}
                className="sr-only"
              />
            </label>
          ))}
        </div>
        <div className="w-full text-start text-[13px] text-gray-500 mt-3">
          Selecciona 1 opción
        </div>
    </OnboardingCard>
  )
}

