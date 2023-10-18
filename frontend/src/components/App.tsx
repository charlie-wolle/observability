'use client';
import React, {useEffect, useState} from "react";
import Main from "@/components/Main";
import Login from "@/components/Login";

import AuthService from "@/services/AuthService";
import EventBus from "@/common/EventBus";
import { useCookies } from "react-cookie";
import UserDetails from "@/components/UserDetails";

const App = () => {
    const [showUserBoard, setShowUserBoard] = useState(false);
    const [showAdminBoard, setShowAdminBoard] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);

    const [cookies, setCookie] = useCookies();

    useEffect(() => {
        if (cookies.jwtToken) {
            AuthService.getCurrentUser()
                .then((response) => {
                    if (response.status == 200)
                        return response.json();
                })
                .then((user) => {
                    if (user) {
                        setCurrentUser(user);
                        setShowUserBoard(user.roles.includes('ROLE_USER'));
                        setShowAdminBoard(user.roles.includes('ROLE_ADMIN'));
                    }
                })
            .catch((error) => {console.log(error)});
        }

        EventBus.on("logout", () => {
            logOut();
        });

        return () => {
            EventBus.remove("logout", () => {
                console.log("removed logout event")
            });
        };
    }, [cookies.jwtToken]);

    const logOut = () => {
        AuthService.logout();
        setShowUserBoard(false);
        setShowAdminBoard(false);
        setCurrentUser(undefined);
        window.location.reload();
    };

    return (
    <div className="min-h-screen max-h-screen lg:flex">
        <Main/>
            <div className="flex lg:w-1/3 md:w-full justify-center items-center bg-white lg:space-y-8 mt-4">
                <div className="w-full px-8 md:px-32 lg:px-24">
                    {currentUser ? (
                        <UserDetails user={currentUser}/>
                    ) : (
                        <Login />
                    )}
                </div>
            </div>

    </div>
);
}
export default App;