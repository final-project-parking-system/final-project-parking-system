import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "./Navbar";
import "../home.css"

function Home1() {
  const navigate = useNavigate();
  const today = new Date().toJSON().split("T")[0];
  const [startDateInParams, setStart] = useState(today);
  const [endDateInParams, setEnd] = useState(today);

  const handleChangeStartDate = (e) => {
    setStart(e.target.value);
  };
  const handleChangeEndDate = (e) => {
    setEnd(e.target.value);
  };
 
  const send = () => {
    navigate(`/Spot/${startDateInParams}/${endDateInParams}`);
  };
  

  return (

    <div className="contener">
      <Navbar/>
    <div className='card'>
    <div className="inputStyle">
    <input type="date" className='dateRange' onChange={handleChangeStartDate}  />
    <input type="date" className='dateRange' onChange={handleChangeEndDate} />
    </div>
      <button className="button-46" onClick={send}>SUBMIT</button>
      </div>
    </div>
  );
}

export default Home1;
