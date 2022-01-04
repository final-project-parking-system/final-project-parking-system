import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import Login from "./components/Login";
import SignUp from "./components/Register";
import Home from "./components/Home"
import  navbar  from './components/navbar';
import  Spot  from './components/Spot';


function App() {
  return (
    <Router>
    <div className="App">
     

      <div className="auth-wrapper">
        <div className="auth-inner">
          <Routes >
            <Route path="/navbar" element={<navbar/>} />
            <Route exact path='/home' element={<Home/>} />
            <Route path="/login" element={<Login/>} />
            <Route path="/sign-up" element={<SignUp/>} />
            <Route path="/spot/:spoy_id/:startDate/:endDate" element={<Spot/>} />
          </Routes >
        </div>
      </div>
    </div></Router>
  );
}

export default App;
