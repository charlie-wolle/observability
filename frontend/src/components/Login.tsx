'use client';
import React, {useState} from "react";
import LoginForm from "@/components/LoginForm";
import ForgotPasswordForm from "@/components/ForgotPasswordForm";
import RegisterForm from "@/components/RegisterForm";

const Login = (props) => {

    const [loginForm, setLoginForm] = React.useState("Login");
    const switchForm = (form) => {
        setLoginForm(form);
    }

    if (loginForm === "ForgotPassword") {
        return <ForgotPasswordForm onFormSwitch={switchForm}/>
    } else if (loginForm === "Register") {
        return <RegisterForm onFormSwitch={switchForm}/>
    } else {
        return <LoginForm onFormSwitch={switchForm}/>
    }
}

export default Login;