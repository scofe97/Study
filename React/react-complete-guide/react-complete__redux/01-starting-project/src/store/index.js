import {configureStore} from '@reduxjs/toolkit'
import counterReducer from "./counter";
import authSliceReducer from "./auth";

// const counterReducer = (state = {counter: 0, showCounter: true}, action) => {
//
//     const initialState = {counter: 0, showCounter: true}
//
//     if(action.type === INCREMENT){
//         return{
//             counter: state.counter + 1,
//             showCounter: state.showCounter
//         }
//     }
//
//     if(action.type === 'increase'){
//         return{
//             counter: state.counter + action.amount,
//             showCounter: state.showCounter
//         }
//     }
//
//     if (action.type === 'decrement') {
//         return{
//             counter: state.counter - 1,
//             showCounter: state.showCounter
//         }
//     }
//
//     if (action.type === 'toggle') {
//         return{
//             counter: state.counter,
//             showCounter: !state.showCounter
//         }
//     }
//
//     return state;
// }


const store = configureStore({
    reducer: {counter: counterReducer, auth: authSliceReducer}
});

export default store