/** @type {import('tailwindcss').Config} */
export default {
  content: [".index.html", "./src/**/*.{js,jsx}"],
  theme: {
    extend: {
      fontFamily: {
        sans: ['Nunito', 'ui-sans-serif', 'system-ui'],
      },
      backgroundImage: {
        'gradient': 'url(./src/assets/bg-gradient.svg)',
        'gradient-radial': 'radial-gradient(#CDDDFF 0%, #AECBF0 100%)',
        'gradient-stroke': 'linear-gradient(#FFFFFF/40 0%, #FFFFFF/20 100%)',
        'button': 'linear-gradient(135deg, rgba(255,255,255,0.5) 0%, rgba(255,255,255,0.25) 100%)',
      }
    },
  },
  plugins: [],
}

