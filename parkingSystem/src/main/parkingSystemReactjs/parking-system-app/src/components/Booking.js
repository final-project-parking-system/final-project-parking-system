import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import Navbar from "./Navbar";
import "../Styles/booking.css";
import { useSelector, useDispatch } from "react-redux";

function Booking() {
  const navigate = useNavigate();
  const { slot } = useParams();
  const { startDate } = useParams();
  const { endDate } = useParams();
  const [info , setInfo]=useState();
  const [spot ,setSpot]=useState();
  const [ticket ,setTicket]=useState();
  const spotId = slot;
  const state = useSelector((state) => {
    return {
      user: state.userReducer.user,
    };
  });
  console.log(state);
  let userId = state.user.id;
  useEffect(() => {
    if (userId !== undefined) {
      axios
        .post(
          `http://localhost:8080/ticket/${userId}/${spotId}/${startDate}/${endDate}`
        )
        .then((response) => {
          setInfo(response.data.user)
          console.log(response.data.id);
          setSpot(response.data.spot)
          setTicket(response.data)
          console.log(spot);
          console.log(info);
          console.log(ticket);



        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      navigate("/login");
    }
  },[]);
  return (
    <div className="contaner-booking">
      <Navbar />
      <div className="card-booking">
      <h1>Booking details</h1>
      <hr/>
      {ticket ?<>
      <h2>Car wonir</h2>
      <p>{info.fName}</p>
      <h2>Car brand</h2>
      <p>{info.carName}</p>
      <h2>Slot #No</h2>
      <p>{spot.id}</p>
      <h2>Start day</h2>
      <p>{ticket.startTime}</p>
      <h2>End day</h2>
      <p>{ticket.endTime}</p>
      <h2>price</h2>
      <p>{ticket.price} SR</p>
      </>:""}
      </div>
           <button className="button-booking" onClick={() => navigate("/Home")}>
        back
      </button>
     
    </div>
  );
}

export default Booking;
