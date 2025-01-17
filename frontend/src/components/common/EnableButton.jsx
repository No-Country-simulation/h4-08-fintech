/* eslint-disable react/prop-types */
export const EnableButton = ({ content, available, onClick }) => {
    return (
      <button
        onClick={available ? onClick : null} // Sólo ejecuta onClick si el botón está habilitado
        className={`w-full h-12 my-2 flex items-center justify-center ${
          available ? 'bg-blue-500 text-white' : 'bg-gray-50 text-gray-300'
        } font-semibold px-3 py-2 rounded-2xl`}
      >
        {content}
      </button>
    );
  };
