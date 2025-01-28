export const Notifications = () => {
  return (
    <div>
      <h3 className="text-blue-900 text-2xl font-semibold">Hoy</h3>
      <div className="p-2 px-4 my-5 bg-blur border-2 border-blue-800 rounded-lg  ">
        <div className="flex items-center ">
          <div className="bg-white rounded-full shrink-0 grow-0 p-5 text-2xl">
            💡
          </div>
          <p className="my-1 ms-3 text-blue-700 font-semibold">
            Conocer tu situación financiera es clave. Completa tu radiografía y
            obtén consejos personalizados.
          </p>
        </div>

        <p className="text-end text-xs text-gray-500 ">Justo ahora</p>
      </div>

      <h3 className="text-blue-900 text-2xl font-semibold">
        Notificaciones previas
      </h3>
      <div className=" p-4 my-5 bg-blur border-2 border-white rounded-lg  ">
        <div className="flex items-center ">
          <div className="bg-white rounded-full shrink-0 grow-0 p-5 text-2xl">
            🚀
          </div>
          <p className="my-1 ms-3 font-semibold text-gray-900">
            Alcanzaste el 50% de tu objetivo Vacaciones ¡Increíble, sigue así!
          </p>
        </div>
        <p className="text-end text-xs text-gray-500 ">Ayer </p>
        <div className="border-b border-white my-4"></div>
        <div className="flex items-center ">
          <div className="bg-white rounded-full shrink-0 grow-0 p-5 text-2xl">
            🎯
          </div>
          <p className="my-1 ms-3 font-semibold  text-gray-900">
          ¡Felicitaciones! Has creado tu primer objetivo. Ahora trabajemos juntos para lograrlo.          </p>
        </div>
        <p className="text-end text-xs text-gray-500 ">Justo ahora </p>
        <div className="border-b border-white my-4"></div>
        <div className="flex items-center ">
          <div className="bg-white rounded-full shrink-0 grow-0 p-5 text-2xl">
            🎯
          </div>
          <p className="my-1 ms-3 font-semibold  text-gray-900">
          Define tu primer objetivo financiero y empieza a ahorrar. </p>        </div>
        <p className="text-end text-xs text-gray-500 ">Justo ahora </p>
      </div>
    </div>
  );
};
