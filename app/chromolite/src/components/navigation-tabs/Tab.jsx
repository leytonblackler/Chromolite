import React, { Component } from "react";
import styled from "styled-components";
import posed from "react-pose";

import {
  NAVIGATION_TAB_HEIGHT,
  NAVIGATION_TAB_SPACING,
  BASE_FONT_SIZE,
  ACCENT_COLOR,
  TEXT_COLOR,
  PANEL_COLOURS
} from "../../constants";

class Tab extends Component {
  constructor(props) {
    super(props);

    const { icon } = this.props;

    this.Icon = posed(icon)({
      active: { fill: ACCENT_COLOR },
      inactive: { fill: TEXT_COLOR }
    });
  }

  render() {
    const { active, label, onClick } = this.props;

    const Icon = this.Icon;

    return (
      <MainContainer onClick={onClick}>
        <Icon
          pose={active ? "active" : "inactive"}
          width="16px"
          height="16px"
        />
        <Text pose={active ? "active" : "inactive"}>{label}</Text>
      </MainContainer>
    );
  }
}

const MainContainer = posed(styled.div`
  height: ${NAVIGATION_TAB_HEIGHT}px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  margin-top: ${NAVIGATION_TAB_SPACING}px;
  padding-left: 20px;
  padding-right: 20px;
  cursor: pointer;
`)({
  hoverable: true,
  init: {
    backgroundColor: PANEL_COLOURS[1]
  },
  hover: {
    backgroundColor: PANEL_COLOURS[2]
  }
});

const Text = posed(styled.div`
  text-transform: uppercase;
  font-size: ${BASE_FONT_SIZE}pt;
  letter-spacing: 1.4px;
  font-weight: 600;
  margin-left: 10px;
  white-space: nowrap;
`)({ active: { color: ACCENT_COLOR }, inactive: { color: TEXT_COLOR } });

export default Tab;
