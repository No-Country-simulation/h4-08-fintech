/* eslint-disable react/prop-types */

export const HeaderLandingP = ({ title, subtitle, description }) => {
  return (
    <div className="my-6">
      <p className="font-semibold text-center text-[#932EFA]"  data-aos="fade-up" data-aos-duration="300">{title}</p>
      <h3 className="text-center m-3 text-3xl font-bold text-[#000000] leading-none"  data-aos="fade-up" data-aos-duration="300">{subtitle}</h3>
      <p className="mx-5 text-center text-[#4D525F]"  data-aos="fade-up" data-aos-duration="300">
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
