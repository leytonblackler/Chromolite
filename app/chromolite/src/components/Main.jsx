import React, { Component } from "react";
import styled from "styled-components";
import TopBar from "./TopBar";
import SideBar from "./SideBar";
import ContentArea from "./ContentArea";

import { WINDOW_RADIUS, TOP_BAR_HEIGHT } from "../constants";

class Main extends Component {
  render() {
    return (
      <MainContainer>
        <TopBar />
        <LowerContainer>
          <SideBar />
          <ContentArea />
        </LowerContainer>
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  height: 100%;
  border-radius: ${WINDOW_RADIUS}px;
`;

const LowerContainer = styled.div`
  height: calc(100% - ${TOP_BAR_HEIGHT}px);
  display: flex;
  flex-direction: row;
`;

export default Main;
