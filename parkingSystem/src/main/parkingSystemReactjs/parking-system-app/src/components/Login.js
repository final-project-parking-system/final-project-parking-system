import React from "react";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { addUser, addToken } from "../reducers/user/actions";
import axios from "axios";
import jwt_decode from "jwt-decode";


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
        <form>
          <h3>Sign In</h3>

          <div className="form-group">
            <label>Email address</label>
            <input
              type="email"
              className="form-control"
              placeholder="Enter email"
              onChange={handleChangeEmail}
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              className="form-control"
              placeholder="Enter password"
              onChange={handleChangePassword}
            />
          </div>

          <div className="form-group">
            <div className="custom-control custom-checkbox">
              <input
                type="checkbox"
                className="custom-control-input"
                id="customCheck1"
              />
              <label className="custom-control-label" htmlFor="customCheck1">
                Remember me
              </label>
            </div>
          </div>
          <button type="submit" className="btn btn-primary btn-block" onClick={AddData}>
            Submit
          </button>
        </form>   

     
     </> 
   
  );
}

export default Login;
