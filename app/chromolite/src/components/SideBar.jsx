import React, { Component } from "react";
import styled from "styled-components";
import SideBarButton from "./SideBarButton";
import SideBarIndicator from "./SideBarIndicator";

import { WINDOW_RADIUS } from "../constants";

class SideBar extends Component {
  render() {
    return (
      <MainContainer>
        <SideBarIndicator />
        <SideBarButton />
        <SideBarButton />
        <SideBarButton />
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  height: 100%;
  width: 250px;
  background-color: #191e25;
  border-radius: 0px 0px 0px ${WINDOW_RADIUS}px;
`;

export default SideBar;
