/** @type {import('tailwindcss').Config} */
export default {
  content: [".index.html", "./src/**/*.{js,ts,jsx,tsx,html}"],
  theme: {
    extend: {
      fontFamily: {
        jakarta: ["Plus Jakarta Sans", 'ui-sans-serif'],
      },
      backgroundImage: {
        'gradient': 'url(/assets/bg-gradient.svg)',
        'gradient-radial': 'radial-gradient(#CDDDFF 0%, #AECBF0 100%)',
        'gradient-stroke': 'linear-gradient(#FFFFFF/40 0%, #FFFFFF/20 100%)',
        'blur': 'linear-gradient(135deg, rgba(255,255,255,0.5) 0%, rgba(255,255,255,0.25) 100%)',
        'gradient-green': 'linear-gradient(135deg, rgba(234, 248, 227, 1) 0%, rgba(47, 224, 132, 1) 100%)',
        'gradient-blue': 'linear-gradient(135deg, rgba(246, 254, 254, 1) 0%, rgba(19, 194, 198, 1) 100%)', 
        'gradient-purple': 'linear-gradient(135deg, rgba(242, 240, 253, 1) 0%, rgba(136, 129, 170, 1) 100%)', 
        'gradient-yellow': 'linear-gradient(135deg, rgba(255, 244, 184, 1) 0%, rgba(248, 224, 54, 1) 100%)',  
      },
      colors: {
        blue: {
          500: '#0058CC',
          600: '#004AAD',
          700: '#003E91',
          900: '#002556'
        },
        gray: {
          600: '#5E6165',
          700: '#494C4F',
          900: '#2B2D2F'
        },
        black: '#212121'
      },
    },
  },
  plugins: [],
}

