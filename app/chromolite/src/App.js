import React from "react";
import WindowShadow from "./components/WindowShadow";
import WindowContent from "./components/WindowContent";
import "./App.css";
import "./fonts/fonts.css";

function App() {
  return (
    <WindowShadow>
      <WindowContent />
    </WindowShadow>
  );
}

export default App;
