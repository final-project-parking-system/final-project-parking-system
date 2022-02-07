import React from "react";
import { useState, useEffect } from "react";
import { useNavigate, Navigate, useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { addUser, addToken } from "../reducers/user/actions";
import axios from "axios";
import jwt_decode from "jwt-decode";
import Navbar from "./Navbar";
import Swal from 'sweetalert2'

function Register() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();

  const state = useSelector((state) => {
    return {
      user: state.userReducer.user,
    };
  });
  const [fname, setFName] = useState("");
  const [lName, setLName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [carName, setCarName] = useState("");
  const [carModel, setCarModel] = useState("");
  const [phone, setPhone] = useState("");
  const [plat, setPlat] = useState("");

  const handleChangePassword = (e) => {
    console.log(e.target.value);
    setPassword(e.target.value);
  };
  const handleChangeEmail = (e) => {
    console.log(e.target.value);
    setEmail(e.target.value);
  };
  const handleChangeFname = (e) => {
    console.log(e.target.value);
    setFName(e.target.value);
  };
  const handleChangeLName = (e) => {
    console.log(e.target.value);
    setLName(e.target.value);
  };
  const handleChangecarModel = (e) => {
    console.log(e.target.value);
    setCarModel(e.target.value);
  };
  const handleChangeCarName = (e) => {
    console.log(e.target.value);
    setCarName(e.target.value);
  };
  const handleChangePhone = (e) => {
    console.log(e.target.value);
    setPhone(e.target.value);
  };
  const handleChangePlat = (e) => {
    console.log(e.target.value);
    setPlat(e.target.value);
  };

  const sginIn =(e)=>{
    e.preventDefault()
    console.log("hi");
    axios
    .post("http://localhost:8080/users/register-By-User", {
      user:{
      fName :fname,
      lName:lName,
      carName:carName,
      carModel:carModel,
      platNumber:plat,
      email:email,
      password:password,
      phone:phone
           },
           role_id:1,
    })
    .then((res) => {
      if (res.data === null) {
        console.log("Sorry, the phone number is taken");
      }
    })
    .catch((err) => {
      console.log("Sorry, the phone number is taken");

      console.log(err);

    });

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
}
  
 return (
   <div className="contener">
     <Navbar />
    <form className="card">
      <h3>Sign Up</h3>

      <div className="form-group">
        <label>First name</label>
        <input
          type="text"
          onChange={handleChangeFname}
          className="form-control"
          placeholder="First name"
        />
      </div>

      <div className="form-group">
        <label>Last name</label>
        <input
          type="text"
          onChange={handleChangeLName}
          className="form-control"
          placeholder="Last name"
        />
      </div>
      <div className="form-group">
        <label>Phone number </label>
        <input
          type="text"
          onChange={handleChangePhone}
          className="form-control"
          placeholder="5xxxxxxxx"
        />
      </div>

      <div className="form-group">
        <label>Email address</label>
        <input
          type="email"
          onChange={handleChangeEmail}
          className="form-control"
          placeholder="Enter email"
        />
      </div>

      <div className="form-group">
        <label>Password</label>
        <input
          type="password"
          onChange={handleChangePassword}
          className="form-control"
          placeholder="Enter password"
        />
      </div>
      <div className="form-group">
        <label>Car brand</label>
        <input
          type="text"
          onChange={handleChangeCarName}
          className="form-control"
          placeholder="Enter Car brand"
        />
      </div>
      <div className="form-group">
        <label>Car Model</label>
        <input
          type="number"
          onChange={handleChangecarModel}
          className="form-control"
          placeholder="Enter Car Model ex 2020 .."
        />
      </div>
      <div className="form-group">
        <label>Car plat Number</label>
        <input
          type="text"
          onChange={handleChangePlat}
          className="form-control"
          placeholder="Enter Car plat number  ex 111 sss .."
        />
      </div>

      <button type="submit" onClick={sginIn} className="btn btn-primary btn-block">
        Sign Up
      </button>
     
    </form>
    </div>
  );
  }

export default Register;
