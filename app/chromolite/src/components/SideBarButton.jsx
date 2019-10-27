import React, { Component } from "react";
import styled from "styled-components";
import {
  SIDE_BAR_BUTTON_HEIGHT,
  SIDE_BAR_BUTTON_SPACING,
  BASE_FONT_SIZE,
  SECONDARY_COLOR
} from "../constants";

class SideBarButton extends Component {
  render() {
    return (
      <MainContainer>
        <Label>Global Control</Label>
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  height: ${SIDE_BAR_BUTTON_HEIGHT}px;
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  margin-top: ${SIDE_BAR_BUTTON_SPACING}px;
  margin-bottom: ${SIDE_BAR_BUTTON_SPACING}px;
`;

const Label = styled.div`
  text-transform: uppercase;
  font-size: ${BASE_FONT_SIZE}pt;
  font-weight: 700;
  letter-spacing: 1.5px;
  color: ${SECONDARY_COLOR};
  margin-left: 20px;
`;

export default SideBarButton;
