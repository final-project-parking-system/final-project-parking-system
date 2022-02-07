import React from 'react'
import '../Styles/navbar.css'
import logo from "../image/LOGO.png"
import { useSelector, useDispatch } from "react-redux";
import {removeUser} from "../reducers/user/actions";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
function Navbar() {
    const dispatch = useDispatch();
    const state = useSelector((state) => {
        return {
          user: state.userReducer.user,
        };
      });
    return (
        <>
    <div className='grid'>
     <img src={logo} className='logoStyle' height={'120px'} width={'250px'} />
 <ul>
	<li>Home</li>
	<li>About</li>
 </ul>
 <ul>
     {!state.user.id?
     <>
     <Link to = "/Login"><li>Log In</li></Link>

     <Link to = "/sign-up"> <li>Register</li></Link>
     </>
     :""}
	    

    {state.user.id?
        <Link to="/profile">
            <li>profile</li>
        </Link>
    :" "}
    
 {state.user.id?
  <Link to="/Home" className="nav-links"  onClick={() => { 
// const action = removeUser();
console.log("remove user")
  dispatch(removeUser()); }}>
  <li> Log Out</li></Link> :" "}

 </ul>
       </div>
       </> 
    )
    }
export default Navbar
