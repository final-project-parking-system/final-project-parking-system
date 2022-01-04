const initialState = {
  spot:[],
};

const spotReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case "ADD_SPOT":
      return {
        spot: payload,
      };
  

    default:
      return state;
  }
};

export default spotReducer;