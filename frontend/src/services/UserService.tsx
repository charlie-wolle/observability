import axios from "axios";

const API_URL = "http://localhost:8080/api/users/";

const getUserDetails = () => {
    return axios.get(API_URL + "userinfo", {withCredentials: true})}


const UserService = {
    getUserDetails
}

export default UserService;