'use client';
import React, {useState} from "react";
import AuthService from "@/services/AuthService";

const RegisterForm = (props) => {
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const switchForm = (form) => {
        props.onFormSwitch(form);
        console.log("Switched to " + form + " form");
    }

    const sendRegisterRequest = () => {
        AuthService.register(username, password, firstname, lastname, email)
        .then(() => {
          switchForm("Login");
        })
            .catch(message => {
                alert("Wrong username or password: " + message);
        });
    }
    const usernameChangeHandler = (event) => {
        setUsername(event.target.value);
    };
    const passwordChangeHandler = (event) => {
        setPassword(event.target.value);
    }

    const firstnameChangeHandler = (event) => {
        setFirstname(event.target.value);
    };
    const lastnameChangeHandler = (event) => {
        setLastname(event.target.value);
    };
    const emailChangeHandler = (event) => {
        setEmail(event.target.value);
    };
    return (
        <form className="bg-white rounded-md shadow-2xl p-5">
            <h1 className="text-black font-bold text-2xl mb-8">Register</h1>
            <div className="flex items-center border-2 mb-8 py-2 px-3 rounded-2xl">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-black" fill="none"
                     viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"/>
                </svg>
                <input id="firstname" className=" pl-2 w-full outline-none border-none" type="text"
                       name="firstname"
                       placeholder="Firstname" onChange={firstnameChangeHandler}/>
            </div>
            <div className="flex items-center border-2 mb-8 py-2 px-3 rounded-2xl">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-black" fill="none"
                     viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"/>
                </svg>
                <input id="lastname" className=" pl-2 w-full outline-none border-none" type="text"
                       name="lastname"
                       placeholder="Lastname" onChange={lastnameChangeHandler}/>
            </div>
            <div className="flex items-center border-2 mb-8 py-2 px-3 rounded-2xl">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-black" fill="none"
                     viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"/>
                </svg>
                <input id="email" className=" pl-2 w-full outline-none border-none" type="email"
                       name="emal"
                       placeholder="Email" onChange={emailChangeHandler}/>
            </div>
            <div className="flex items-center border-2 mb-8 py-2 px-3 rounded-2xl">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-black" fill="none"
                     viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"/>
                </svg>
                <input id="username" className=" pl-2 w-full outline-none border-none" type="text"
                       name="username"
                       placeholder="Username" onChange={usernameChangeHandler}/>
            </div>
            <div className="flex items-center border-2 mb-12 py-2 px-3 rounded-2xl ">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-black"
                     viewBox="0 0 20 20"
                     fill="currentColor">
                    <path fillRule="evenodd"
                          d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z"
                          clipRule="evenodd"/>
                </svg>
                <input className="pl-2 w-full outline-none border-none" type="password" name="password"
                       id="password" placeholder="Password" onChange={passwordChangeHandler}/>

            </div>
            <button type="button" id="submit" onClick={sendRegisterRequest}
                    className="block w-full bg-white mt-5 py-2 rounded-2xl border-2 border-black hover:-translate-y-1 hover:bg-black hover:text-white transition-all duration-500 text-black font-semibold mb-2">Register
            </button>
            <div className="flex justify-between mt-4">
                  <span
                      className="text-sm ml-2 hover:font-bold cursor-pointer hover:-translate-y-1 duration-500 transition-all"
                      onClick={() => switchForm("ForgotPassword")}>Forgot Password ?</span>

                <a href="#"
                   className="text-sm ml-2 hover:font-bold cursor-pointer hover:-translate-y-1 duration-500 transition-all"
                   onClick={() => switchForm("Login")}>Already
                    have an account?</a>
            </div>

        </form>
    );

}

export default RegisterForm;