export const EnableButton = ({ content, available}) => {
    return (
        <button className={`w-full h-12 my-2 flex items-center justify-center ${available? 'bg-blue-500 text-white' : 'bg-gray-50 text-gray-300'} font-semibold px-3 py-2 rounded-xl`} >
            {content}
        </button>
    )
}