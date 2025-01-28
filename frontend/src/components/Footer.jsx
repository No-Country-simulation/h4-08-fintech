import { Link } from "react-router-dom"

export const Footer = () => {
    return (
        <footer className="bg-[#FFFFFF] p-6">
            <h1 className="text-2xl font-bold">IUPI</h1>
            <p className="text-[#4D525F] text-sm">IUPI offers secure, seamless, and fee-free payments for effortless global transactions.</p>
            <div className="flex justify-between mt-4">
                <div className="flex flex-col gap-4">
                    <h2 className="gap-5 text-lg font-bold">Short Links</h2>
                    <Link to={""} className="text-[#4D525F] text-sm">Features</Link>
                    <Link to={""} className="text-[#4D525F] text-sm">How it works</Link>
                    <Link to={""} className="text-[#4D525F] text-sm">Security</Link>
                    <Link to={""} className="text-[#4D525F] text-sm">Testimonial</Link>
                </div>
                <div className="flex flex-col gap-4">
                    <h2 className="text-lg font-bold">Other pages</h2>
                    <Link to={""} className="text-[#4D525F] text-sm">Privacy policy</Link>
                    <Link to={""} className="text-[#4D525F] text-sm">Terms & conditions</Link>
                    <Link to={""} className="text-[#4D525F] text-sm">404</Link>
                </div>
            </div>
            <hr className="my-3 text-[#E7E7E8] h-2"/>
            <div className="flex flex-col items-center gap-3 mb-5">
            <p className="text-[#4D525F] text-sm font-light">iupi.com</p>
            <p className="text-[#4D525F] text-sm font-light">{Date.now()} © hackathon. All rights reserved.</p>
            </div>
        </footer>
    )
}