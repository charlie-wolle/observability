import AuthService from "@/services/AuthService";
import React, {useState} from "react";

const UserDetails = (props) => {
    const [readOnly, setReadOnly] = useState(true)

    const [firstname, setFirstname] = useState(`${props.user.firstname}`);
    const [lastname, setLastname] = useState(`${props.user.lastname}`);
    const [username, setUsername] = useState(`${props.user.username}`);

    const buttonLabel = readOnly ? "Edit Data" : "Back"

    const logOut = () => {
        AuthService.logout();
        window.location.reload();
    };
    const unlockInput = ()  => {
        setReadOnly(prevState => !prevState)
    }

    const editUserDetails = () => {
        AuthService.editUserDetails(username, firstname, lastname);
        window.location.reload();
    };
    const firstnameChangeHandler = (event) => {
        setFirstname(event.target.value);
    };
    const lastnameChangeHandler = (event) => {
        setLastname(event.target.value);
    };


    return (
        <div>
            <button type="button" id="submit" onClick={logOut}
                    className="block w-full bg-white mt-5 py-2 rounded-2xl border-2 border-black hover:-translate-y-1 hover:bg-black hover:text-white transition-all duration-500 text-black font-semibold mb-2">Logout
            </button>
            <form className="bg-white rounded-md shadow-2xl p-5">
                <h1 className="mb-2 py-2 px-3 font-bold">USER DETAILS:</h1>
                <p className="mb-2 py-1 px-3">Firstname:</p>
                <div className="flex items-center border-2 mb-8 py-2 px-3 rounded-2xl">
                    <input id="firstname" className=" pl-2 w-full outline-none border-none" type="text" readOnly={readOnly}
                           name="firstname" defaultValue={firstname} onChange={firstnameChangeHandler}/>
                </div>
                <p className="mb-2 py-1 px-3">Lastname:</p>
                <div className="flex items-center border-2 mb-8 py-2 px-3 rounded-2xl">
                    <input id="lastname" className=" pl-2 w-full outline-none border-none" type="text" readOnly={readOnly}
                           name="lastname" defaultValue={lastname} onChange={lastnameChangeHandler}/>
                </div>

                <div className="flex justify-between mt-4">
                    <a href="#"
                       className="text-sm ml-2 hover:font-bold cursor-pointer hover:-translate-y-1 duration-500 transition-all"
                       onClick={unlockInput}>{buttonLabel}</a>
                    { !readOnly && (
                        <a href="#" className="text-sm ml-2 hover:font-bold cursor-pointer hover:-translate-y-1 duration-500 transition-all"
                        onClick={editUserDetails}>
                            Save
                        </a>
                    )}
                </div>
            </form>
        </div>
    );
}

export default UserDetails;