import React, { Component } from "react";
import styled from "styled-components";
import {
  SIDE_BAR_INDICATOR_HEIGHT,
  SIDE_BAR_BUTTON_HEIGHT,
  SIDE_BAR_BUTTON_SPACING,
  PRIMARY_COLOR,
  TOP_BAR_HEIGHT
} from "../constants";

class SideBarIndicator extends Component {
  render() {
    return <Indicator />;
  }
}

const indicatorYPosition = activeIndex =>
  TOP_BAR_HEIGHT +
  SIDE_BAR_BUTTON_SPACING +
  (SIDE_BAR_BUTTON_HEIGHT - SIDE_BAR_INDICATOR_HEIGHT) / 2 +
  activeIndex * (SIDE_BAR_BUTTON_HEIGHT + SIDE_BAR_BUTTON_SPACING);

// const indicatorYPosition = activeIndex =>
//   TOP_BAR_HEIGHT +
//   SIDE_BAR_BUTTON_HEIGHT / 2 +
//   activeIndex * (SIDE_BAR_BUTTON_HEIGHT + SIDE_BAR_INDICATOR_HEIGHT / 2);

const Indicator = styled.div`
  width: 4px;
  height: ${SIDE_BAR_INDICATOR_HEIGHT}px;
  position: fixed;
  top: ${indicatorYPosition(1)}px;
  left: 0;
  background-color: ${PRIMARY_COLOR};
  border-radius: 0px 10px 10px 0px;
`;

export default SideBarIndicator;
