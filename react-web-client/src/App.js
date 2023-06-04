import React from "react";
import { BrowserRouter } from "react-router-dom";
import './app.css'
import AppRouter from "./component/AppRouter";
import NavBar from "./component/NavBar";
// import Generate from "./pages/Generate";
// import LongPulling from "./pages/Generate";

function App() {
  return (
      <BrowserRouter>
        <NavBar/>
        <AppRouter/> 
      </BrowserRouter>
  );
}; 

export default App;
