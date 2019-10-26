import React, { Component } from "react";
import styled from "styled-components";

import { WINDOW_RADIUS } from "../constants";

class SideBar extends Component {
  render() {
    return <MainContainer>placeholder text</MainContainer>;
  }
}

const MainContainer = styled.div`
  height: 100%;
  width: 300px;
  background-color: #191e25;
  border-radius: 0px 0px 0px ${WINDOW_RADIUS}px;
`;

export default SideBar;
