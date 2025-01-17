
import { useState } from 'react'

export default function OnboardingStep2() {
  const [selectedLevel, setSelectedLevel] = useState('')

  const levels = [
    {
      id: 'basic',
      title: 'Básico',
      description: 'Estoy empezando'
    },
    {
      id: 'intermediate',
      title: 'Intermedio',
      description: 'Conozco conceptos básicos'
    },
    {
      id: 'advanced',
      title: 'Avanzado',
      description: 'Tengo experiencia en inversiones/finanzas'
    }
  ]

  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-200 via-blue-100 to-green-200 p-4">
      <button className="w-10 h-10 rounded-full bg-white/90 shadow-[0_2px_8px_rgba(0,0,0,0.05)] flex items-center justify-center">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          strokeWidth={2}
          stroke="currentColor"
          className="w-5 h-5 text-gray-600"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M15.75 19.5L8.25 12l7.5-7.5"
          />
        </svg>
      </button>

      <div className="mt-8 space-y-4">
        <h1 className="text-[22px] font-semibold text-center px-8 text-gray-800">
          ¿Cómo describirías tu nivel de conocimiento financiero?
        </h1>
        
        <p className="text-center text-gray-500 text-[15px] px-8 leading-[1.4]">
          Lorem ipsum dolor sit amet consectetur. Pulvinar augue vestibulum commodo lorem pharetra pretium eget velit quam.
        </p>

        <div className="space-y-3 mt-6">
          {levels.map((level) => (
            <label
              key={level.id}
              className={`block w-full p-4 rounded-[16px] border transition-all ${
                selectedLevel === level.id
                  ? 'border-[#0051FF] bg-[#F5F9FF] ring-1 ring-[#0051FF]'
                  : 'border-gray-200 bg-white'
              }`}
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
                  <div className="font-medium text-[15px] text-gray-800">{level.title}</div>
                  <div className="text-[13px] text-gray-500">
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
              selectedLevel
                ? 'bg-[#0051FF] text-white'
                : 'bg-gray-100 text-gray-400'
            }`}
            disabled={!selectedLevel}
          >
            Continuar
          </button>
        </div>

        <div className="text-center text-[13px] text-gray-500 mt-4">
          Selecciona 1 opción
        </div>
      </div>
    </div>
  )
}

