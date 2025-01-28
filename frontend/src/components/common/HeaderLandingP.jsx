/* eslint-disable react/prop-types */

export const HeaderLandingP = ({ title, subtitle, description }) => {
  return (
    <div className="my-6">
      <p className="font-semibold text-violet-700 text-center">{title}</p>
      <h3 className="font-semibold text-center text-3xl m-5">{subtitle}</h3>
      <p className="text-gray-600 text-center mx-5">
        {/\biUPi\b/i.test(description) ? (
          <>
            {description.split(/\biUPi\b/i).map((part, index, array) => (
              <span key={index}>
                {index > 0 && (
                  <span className="font-bold text-gray-600">iUPi</span>
                )}
                {part}
                {index < array.length - 1 && " "}
              </span>
            ))}
          </>
        ) : (
          description
        )}
      </p>
    </div>
  );
};
