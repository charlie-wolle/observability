import React from "react";


const Main = () =>{
    return(
        <div
            className="flex lg:w-2/3 lg:h-auto md:w-full bg-gray-400 lg:bg-swo-background items-center bg-no-repeat bg-cover bg-center bg-fixed">
            <div className="w-full mx-auto px-20 flex-col items-center space-y-6 text-center content-center">
                <h1 className="text-black font-bold text-4xl font-sans">Tribe CCC  Tiger Team - Observability Demo Project</h1>
                <p className="text-black mt-2 text-xl">Check out and try yourself:</p>
                <div className="flex justify-center mt-6">
                    <a href="https://dev.azure.com/swo-appsvc-dach/Tribe.CCC/_git/tigerteam-observability"
                       className="hover:bg-black hover:text-white hover:-translate-y-1 transition-all duration-500 bg-white text-black mt-4 px-4 py-2 rounded-2xl font-bold mb-2">
                        Get Started
                    </a>
                </div>
            </div>
        </div>
    );
}
export default Main;