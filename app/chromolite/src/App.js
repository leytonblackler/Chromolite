import React from "react";
import WindowShadow from "./components/WindowShadow";
import Main from "./components/Main";
import "./App.css";
import "./fonts/fonts.css";

function App() {
  return (
    <WindowShadow>
      <Main />
    </WindowShadow>
  );
}

export default App;
