import React from "react";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { addUser, addToken } from "../reducers/user/actions";
import axios from "axios";
import jwt_decode from "jwt-decode";
import "../Styles/login.css"
import Navbar from "./Navbar";
import Swal from 'sweetalert2'


function Login() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const state = useSelector((state) => {
    return {
      user: state.userReducer.user,
    };
  });

  const handleChangeEmail = (e) => {
      console.log(e.target.value);
    setEmail(e.target.value);
  };
  const handleChangePassword = (e) => {
    console.log(e.target.value);
    setPassword(e.target.value);
  };
  const AddData = (e) => {
    e.preventDefault()
    const data = {
      email,
      password,
     
    };
    console.log(data);

    axios
      .post("http://localhost:8080/login", data)
      .then((response) => {
        console.log(response.data);
        const token = response.data.access_token;
        const decoded = jwt_decode(token);
        console.log(decoded);
        //add to redux
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Your work has been saved',
          showConfirmButton: false,
          timer: 1500
        })
        const user_action = addUser({
          id: decoded.id,
          email: decoded.sub,
        });
        const token_action = addToken(token);

        dispatch(user_action);
        dispatch(token_action);
        if (decoded.roles[0]=="USER") {
           navigate("/Home");
        } else if (decoded.roles[0]=="ADMIN") {
          navigate("/Admin");
        }else{
          console.log("undfinde");
          
        }
       
      })
      .catch((error) => {
       
        console.log(error);
      });
  };

  return (
    <>
    <div className="contener">
      <Navbar/>
    <form className="card">
          <h2 className="h2Style">Log In</h2>
          <hr/>
            <label className="fontStyle">Email address</label>
            <input
              type="email"
              className="inputStyleInLogin"
              placeholder="Enter email"
              onChange={handleChangeEmail}
            />
            <label className="fontStyle">Password</label>
            <input
              type="password"
              className="inputStyleInLogin"
              placeholder="Enter password"
              onChange={handleChangePassword}
            />
          <button type="submit" className="button-login" onClick={AddData}>
            Log in 
          </button>
        </form>   

    </div>
        
     
     </> 
   
  );
}

export default Login;
