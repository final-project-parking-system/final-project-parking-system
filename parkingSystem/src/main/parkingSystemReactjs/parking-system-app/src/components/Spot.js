import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, Navigate, useParams } from "react-router-dom";
import car from "../image/car.png";
import Navbar from "./Navbar";
import "../Styles/spot.css";
import Swal from 'sweetalert2'

function Spot() {
  const navigate = useNavigate();
  const today = new Date().toJSON().split("T")[0];
  const { startDateInParams } = useParams();
  const { endDateInParams } = useParams();
  const [startDate, setStart] = useState(startDateInParams);
  const [endDate, setEnd] = useState(endDateInParams);
  const [spot, setSpot] = useState([]);
  const [slot, setslot] = useState(0);

  const handleChangeStartDate = (e) => {
    setStart(e.target.value);
  };
  const handleChangeEndDate = (e) => {
    setEnd(e.target.value);
  };
  const errorAlart = () =>{
    Swal.fire({
      title: 'Error!',
      text: 'Do you want to continue',
      icon: 'error',
      confirmButtonText: 'Cool'
    })
  }
  const send = () => {
    axios
      .get(`http://localhost:8080/spot/${startDate}/${endDate}/`)
      .then((response) => {
        setSpot(response.data);
      })
      .catch((error) => {});
  };
  const getValue = (Spot_id) => {
    //  <Booking Spot_id={Spot_id}/>
    //  navigate(`/Booking/${Spot_id}/${startDate}/${endDate}`);
    setslot(Spot_id);
  };
  const go = () => {
    navigate(`/Booking/${slot}/${startDate}/${endDate}`);
  };

  useEffect(() => {
    axios
      .get(`http://localhost:8080/spot/${startDate}/${endDate}/`)
      .then((response) => {
        setSpot(response.data);
      })
      .catch((error) => {});
  }, []);

  return (
    <div className="contener">
      <Navbar />
      <div className="inputStyleInSpot">
        <input
          type="date"
          value={startDate}
          className="dateRange"
          onChange={handleChangeStartDate}
        />
        <input
          type="date"
          value={endDate}
          className="dateRange"
          onChange={handleChangeEndDate}
        />
        <button className="button-45" onClick={send}>
          SUBMIT
        </button>
      </div>

      <div className="wrapper-space">
        <div className="wrapper">
          {spot.map((element, index) => {
            if (element.available === true && slot != element.id) {
              // className="available"
              return (
                <div
                  kay={element.id}
                  className="available"
                  onClick={() => getValue(element.id)}
                >
                  {/* <img src={slotImg} height={"110px"} width={"90px"} /> */}
                  {/* <p className="stylyP">Slot:{element.id}</p> */}
                </div>
              );
            } else {
              return (
                <div className="Notavailable">
                  <img
                    src={car}
                    className="carSlot"
                    height={"110px"}
                    width={"90px"}
                    onClick={errorAlart}
                  />
       
                </div>
              );
            }
          })}
        </div>
      </div>
      {slot?
      <button className="button-go" onClick={go}>
        booking
      </button>:
      ""}
      <div></div>
    </div>
  );
}

export default Spot;
