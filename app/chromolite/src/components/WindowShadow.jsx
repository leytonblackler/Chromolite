import React, { Component } from "react";
import styled from "styled-components";

import { WINDOW_RADIUS, WINDOW_SHADOW_SIZE } from "../config/constants";

class WindowShadow extends Component {
  render() {
    return (
      <WindowContainer>
        <Window>{this.props.children}</Window>
      </WindowContainer>
    );
  }
}

const WindowContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: ${WINDOW_RADIUS}px;
  background-color: transparent;
`;

const Window = styled.div`
  width: calc(100% - ${WINDOW_SHADOW_SIZE}px * 2);
  height: calc(100% - ${WINDOW_SHADOW_SIZE}px * 2);
  border-radius: ${WINDOW_RADIUS}px;
  box-shadow: 0 0px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
`;

export default WindowShadow;
