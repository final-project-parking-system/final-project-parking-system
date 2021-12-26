import {combineReducers, createStore} from "redux"
import userReducer from "../reducers/user/reducer"

const reducers = combineReducers({userReducer})
const store = createStore(reducers)

export default store