import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { Switch, Route, Redirect, useLocation } from "react-router-dom";
import Header from "./header/Header";
import Main from "./main/Main";
import Login from "./login/Login";
import Loading from "./common/Loading";
import { useSelector } from "react-redux";

import {
  WINDOW_RADIUS,
  HEADER_HEIGHT,
  PANEL_COLORS,
  LOGIN_WINDOW_WIDTH,
  LOGIN_WINDOW_HEIGHT,
  MAIN_WINDOW_WIDTH,
  MAIN_WINDOW_HEIGHT
} from "../config/constants";

const resizeWindow = (width, height) => {
  // Only resize the window if running in an electron instance.
  const userAgent = navigator.userAgent.toLowerCase();
  if (userAgent.indexOf(" electron/") > -1) {
    const { ipcRenderer } = window.require("electron");
    ipcRenderer.send("resize-window", { width: width, height: height });
  } else {
    console.warn(
      "An electron instance was not detected, window resize request ignored."
    );
  }
};

const ProtectedRoute = ({
  component: Component,
  isAuthenticated,
  isVerifying,
  ...rest
}) => {
  const location = useLocation();
  return (
    <Route
      {...rest}
      render={props =>
        isVerifying ? (
          <Loading />
        ) : isAuthenticated ? (
          <Component {...props} />
        ) : (
          <Redirect to="/login" from={location.pathname} />
        )
      }
    />
  );
};

const WindowContent = () => {
  const [currentWindowSize, setCurrentWindowSize] = useState("small");
  const isAuthenticated = useSelector(state => state.auth.isAuthenticated);
  const isVerifying = useSelector(state => state.auth.isVerifying);

  useEffect(() => {
    if (isAuthenticated) {
      if (currentWindowSize === "small") {
        setCurrentWindowSize("large");
        resizeWindow(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
      }
    } else {
      if (currentWindowSize === "large") {
        setCurrentWindowSize("small");
        resizeWindow(LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
      }
    }
  }, [isAuthenticated, currentWindowSize]);

  return (
    <MainContainer>
      <Header />
      <ContentArea>
        <Switch>
          <ProtectedRoute
            path="/"
            exact
            isAuthenticated={isAuthenticated}
            isVerifying={isVerifying}
            component={Main}
          />
          <Route path="/login" component={Login} />
          <Redirect from="*" to="/" />
        </Switch>
      </ContentArea>
    </MainContainer>
  );
};

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
