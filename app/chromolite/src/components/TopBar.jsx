import React, { Component } from "react";
import styled from "styled-components";
import { WINDOW_RADIUS, TOP_BAR_HEIGHT } from "../constants";

const LOGO_PATH = "/images/logo.png";

class TopBar extends Component {
  render() {
    return (
      <MainContainer>
        <Logo src={LOGO_PATH} alt="Logo" />
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  -webkit-app-region: drag
  height: ${TOP_BAR_HEIGHT}px;
  width: 100%;
  background-color: #1C232C;
  border-radius: ${WINDOW_RADIUS}px ${WINDOW_RADIUS}px 0px 0px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
`;

const Logo = styled.img`
  height: calc(${TOP_BAR_HEIGHT}px * 0.4);
  margin-left: calc(${TOP_BAR_HEIGHT}px * 0.4);
`;

export default TopBar;
