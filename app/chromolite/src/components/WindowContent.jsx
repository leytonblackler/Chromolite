import React from "react";
import styled from "styled-components";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect
} from "react-router-dom";
import Header from "./header/Header";
import Main from "./main/Main";
import Login from "./login/Login";

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

const WindowContent = () => (
  <Router>
    <MainContainer>
      <Header />
      <ContentArea>
        <Switch>
          <Route path="/login" component={Login} />
          <Route path="/main" component={Main} />
          <Redirect from="/" to="/error" />
        </Switch>
      </ContentArea>
    </MainContainer>
  </Router>
);

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
