import React, { useEffect } from "react";
import styled from "styled-components";
import {
  BrowserRouter,
  HashRouter,
  Switch,
  Route,
  Redirect,
  useHistory
} from "react-router-dom";
import Header from "./header/Header";
import Main from "./main/Main";
import Login from "./login/Login";
import Splash from "./login/screens/Splash";

import {
  WINDOW_RADIUS,
  HEADER_HEIGHT,
  PANEL_COLORS
} from "../config/constants";

// componentDidUpdate(prevProps) {
//   if (this.props.location !== prevProps.location) {
//     this.onRouteChanged();
//   }
// }

// onRouteChanged() {
//   console.log("ROUTE CHANGED");
//   // TODO: Resize window depending on login page vs main pages.
// }

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

const WindowContent = () => (
  <Router>
    <MainContainer>
      <Header />
      <ContentArea>
        <Switch>
          <Route path="/loading" component={Splash} />
          <Route path="/login" component={Login} />
          <Route path="/main" component={Main} />
          <Redirect from="*" to="/login" />
        </Switch>
      </ContentArea>
    </MainContainer>
  </Router>
);

// <Redirect from="/" to="/login" />

const MainContainer = styled.div`
  height: 100%;
  border-radius: ${WINDOW_RADIUS}px;
  overflow: hidden;
  user-select: none;
  -moz-user-select: none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  background-color: ${PANEL_COLORS[0]};
`;

const ContentArea = styled.div`
  height: calc(100% - ${HEADER_HEIGHT}px);
`;

export default WindowContent;
