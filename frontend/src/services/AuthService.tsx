import axios from "axios";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;

const API_URL = "http://localhost:8080/api/";

const register = (username, email, password, firstname?, lastname?) => {
    return axios
        .post(API_URL + "auth/register", {
            username,
            email,
            password,
            firstname,
            lastname,
    })
    .then((response) => {
        if (response.status === 200) {
            return Promise.all([response.data, response.headers]);
        } else {
            return Promise.reject(response.status);
        }
    });
};

const login = (username, password) => {
    return axios
        .post(API_URL + "auth/login", {
            username,
            password,
        }, {withCredentials: true})
        .then((response) => {
            if (response.status === 200) {
                return Promise.all([response.data, response.headers]);
            } else {
                return Promise.reject(response.status);
            }
        });
};

const logout = () => {
    return axios
        .post(API_URL + "auth/logout", null, {withCredentials: true})
        .then((response) => {
            if (response.status === 200) {
                return Promise.all([response.data, response.headers]);
            } else {
                return Promise.reject(response.status);
            }
        });
};

const getCurrentUser = () =>  {
    return fetch('http://localhost:8080/api/users/userinfo', {
        method: 'GET',
        mode: 'cors',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
    })
}

const editUserDetails = (username, firstname, lastname) => {
    return axios.put(API_URL + "users/updateself", {
        username,
        firstname,
        lastname
    } , {withCredentials: true})
        .then((response) => {
            if (response.status === 200) {
                return Promise.all([response.data, response.headers]);
            } else {
                return Promise.reject(response.status);
            }
        });
};

const AuthService = {
    register,
    login,
    logout,
    getCurrentUser,
    editUserDetails
}

export default AuthService;