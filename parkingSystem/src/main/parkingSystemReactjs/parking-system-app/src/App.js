import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Login from "./components/Login";
import SignUp from "./components/Register";
import Home from "./components/Home"
import Spot  from './components/Spot';
import Booking from './components/Booking';
import Navbar from './components/Navbar';


function App() {
  return (
    <Router>
    <div className="App">

      <div className="auth-wrapper">
        <div className="auth-inner">
          <Routes >
            <Route path="/navbar" element={<Navbar/>} />
            <Route path="/home" element={<Home/>} />
            <Route exact path="/spot/:startDateInParams/:endDateInParams" element={<Spot/>} />
            <Route path="/login" element={<Login/>} />
            <Route path="/sign-up" element={<SignUp/>} />
            <Route exact path="/Booking/:slot/:startDate/:endDate" element={<Booking/>} />
          </Routes >
        </div>
      </div>
    </div></Router>
  );
}

export default App;
