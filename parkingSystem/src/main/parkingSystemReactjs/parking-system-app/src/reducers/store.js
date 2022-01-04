import {combineReducers, createStore} from "redux"
import userReducer from "../reducers/user/reducer"
import spotReducer from "../reducers/allSpot/reducer"


const reducers = combineReducers({userReducer,spotReducer})
const store = createStore(reducers)

export default store