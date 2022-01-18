import React from 'react'
import { useParams ,useNavigate} from 'react-router-dom';
import { useState, useEffect } from "react";
import axios from "axios";
import Home from "./Home";
import { useSelector, useDispatch } from "react-redux";

function Booking() {
  const navigate = useNavigate();
  const {slot} = useParams()
  const {startDate} = useParams()
  const {endDate} = useParams()

    const spotId=slot;
    const state = useSelector((state) => {
        return {
          user: state.userReducer.user,
    
        };
      });
      console.log(state);
      let userId= state.user.id
      if (userId!==undefined){
            axios
              .post(`http://localhost:8080/ticket/${userId}/${spotId}/${startDate}/${endDate}`)
              .then((response) => {
                console.log(response.data);
              })
              .catch((error) => {
                console.log(error);
              });}
              else {
                navigate('/login')
              }
            
    return (
        <div>
            <h1>hhhh</h1>
        { console.log(spotId +""+state.user.id+""+startDate+""+endDate)}
            <button onClick={()=>navigate("/Home")}>back</button>
        </div>
    )
}

export default Booking
