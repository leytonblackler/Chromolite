import React from "react";
import { Provider as ReduxProvider } from "react-redux";
import WindowShadow from "./components/WindowShadow";
import WindowContent from "./components/WindowContent";
import "./App.css";
import "./fonts/fonts.css";
import { BrowserRouter, HashRouter } from "react-router-dom";
import configureStore from "./redux/configureStore";

const store = configureStore();

// Use a HashRouter if running in an electron instance, otherwise use BrowserRouter.
const Router = ({ children }) => {
  const userAgent = navigator.userAgent.toLowerCase();
  if (userAgent.indexOf(" electron/") > -1) {
    console.log("Using HashRouter.");
    return <HashRouter>{children}</HashRouter>;
  } else {
    console.log("Using BrowserRouter.");
    return <BrowserRouter>{children}</BrowserRouter>;
  }
};

function App() {
  return (
    <ReduxProvider store={store}>
      <Router>
        <WindowShadow>
          <WindowContent />
        </WindowShadow>
      </Router>
    </ReduxProvider>
  );
}

export default App;
