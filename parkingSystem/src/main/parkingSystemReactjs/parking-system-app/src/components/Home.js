import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, Navigate, useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { addSpot } from "../reducers/allSpot/actions";
import Spot from "./Spot";
import slotImg from  "../image/avarbile.png"

function Home() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const today = new Date().toJSON().split("T")[0];
  const [startDate, setStart] = useState(today);
  const [endDate, setEnd] = useState(today);
  const [spot, setSpot] = useState([]);

  const state = useSelector((state) => {
    return {
      spot: state.spotReducer.spot,
    };
  });

  const handleChangeStartDate = (e) => {
    setStart(e.target.value);
    console.log(e.target.value);
  };
  const handleChangeEndDate = (e) => {
    setEnd(e.target.value);
    console.log(e.target.value);

  };
  const send = () => {
    axios
      .get(`http://localhost:8080/spot/${startDate}/${endDate}/`)
      .then((response) => {
        setSpot(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const getValuo = (Spot_id)=>{
    console.log(Spot_id);
   <Spot Spot_id={Spot_id}/>
   navigate(`/spot/${Spot_id}/${startDate}/${endDate}`);

  }

  useEffect(() => {
    axios
      .get(`http://localhost:8080/spot/${startDate}/${endDate}/`)
      .then((response) => {
        setSpot(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <input type="date" onChange={handleChangeStartDate} />
      <input type="date" onChange={handleChangeEndDate} />
      <button onClick={send}>SUBMIT</button>
      <div id='backgrondParking'>
    { spot.map((element ,index )=>
   { if(element.available===false){
        
    return (
        <div  id ='grid' kay={element.id}>
        <img src={slotImg} alt={element.id}  onClick={()=>getValuo(element.id)}/>

        </div>)
    }else {
        return (<div id ='grid'>
        <img src={slotImg}alt={element.id}  onClick={()=>getValuo(element.id)}/>
        </div>)
    }}
   
      )}
      </div>
      </div>
  );
}

export default Home;
