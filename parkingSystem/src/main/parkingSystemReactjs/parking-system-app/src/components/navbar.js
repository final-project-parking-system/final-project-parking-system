import React from 'react'
import '../navbar.css'
import logo from "../image/LOGO.png"
// import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

function Navbar() {
    return (
    <div className='grid'>
     <img src={logo} className='logoStyle' height={'120px'} width={'250px'} />
 <ul>
	<li>Home</li>
	<li>About</li>
 </ul>
 <ul>
	<li>Log In</li>
	<li>Log Out</li>
	<li>sgin In</li>
	<li>profile</li>
 </ul>
       </div>
      
    )
    }
export default Navbar
