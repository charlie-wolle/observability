'use client';
import React, {useState} from "react";

const ForgotPasswordForm = (props) => {
    const [email, setEmail] = useState('');

    const switchForm = (form) => {
        props.onFormSwitch(form);
        console.log("Switched to " + form + " form");
    }

    const sendForgotPasswordRequest = () => {
        const requestBody = {
            email: email
        };

        fetch('http://localhost:8080/api/auth/forgot', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody),
        })
            .then((response) => {
                console.log(response.status)
                if (response.status === 200) {
                    console.log("Status 200")
                    return Promise.all([response.json(), response.headers]);
                } else {
                    return Promise.reject(response.status);
                }
            }).catch(message => {
                alert("Wrong username or password: " + message);
        });
    }
    const emailChangeHandler = (event) => {
        setEmail(event.target.value);
    };

    return (
        <form className="bg-white rounded-md shadow-2xl p-5">
            <h1 className="text-black font-bold text-2xl mb-8">Password reset</h1>
            <div className="flex items-center border-2 mb-8 py-2 px-3 rounded-2xl">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-black" fill="none"
                     viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"/>
                </svg>
                <input id="email" className=" pl-2 w-full outline-none border-none" type="email"
                       name="email"
                       placeholder="Email" onChange={emailChangeHandler}/>
            </div>
            <button type="button" id="submit" onClick={sendForgotPasswordRequest}
                    className="block w-full bg-white mt-5 py-2 rounded-2xl border-2 border-black hover:-translate-y-1 hover:bg-black hover:text-white transition-all duration-500 text-black font-semibold mb-2">Submit
            </button>
            <div className="flex justify-between mt-4">
                <a href="#"
                   className="text-sm ml-2 hover:font-bold cursor-pointer hover:-translate-y-1 duration-500 transition-all"
                   onClick={() => switchForm("Login")}>I remembered my password</a>
                <a href="#"
                   className="text-sm ml-2 hover:font-bold cursor-pointer hover:-translate-y-1 duration-500 transition-all"
                   onClick={() => switchForm("Register")}>I want to register new account</a>
            </div>

        </form>
    );

}

export default ForgotPasswordForm;