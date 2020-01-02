import React from "react";
import styled from "styled-components";
import Header from "./header/Header";
import Main from "./main/Main";
import Login from "./login/Login";

import {
  WINDOW_RADIUS,
  HEADER_HEIGHT,
  PANEL_COLORS
} from "../config/constants";

const WindowContent = () => (
  <MainContainer>
    <Header />
    <ContentArea>
      <Login />
    </ContentArea>
  </MainContainer>
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
