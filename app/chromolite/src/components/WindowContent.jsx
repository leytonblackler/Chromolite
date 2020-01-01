import React from "react";
import styled from "styled-components";
import Header from "./header/Header";
import Main from "./Main";
import Login from "./Login";

import { WINDOW_RADIUS, HEADER_HEIGHT } from "../config/constants";

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
`;

const ContentArea = styled.div`
  height: calc(100% - ${HEADER_HEIGHT}px);
`;

export default WindowContent;
