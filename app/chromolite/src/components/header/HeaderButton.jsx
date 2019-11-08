import React, { Component } from "react";
import styled from "styled-components";
import posed from "react-pose";

import { PANEL_COLOURS, TEXT_COLOR, HEADER_HEIGHT } from "../../constants";

class HeaderButton extends Component {
  render() {
    const { icon, onClick } = this.props;

    const Icon = icon;

    return (
      <MainContainer onClick={onClick}>
        <Icon fill={TEXT_COLOR} width="20px" height="20px" />
      </MainContainer>
    );
  }
}

const MainContainer = posed(styled.div`
  -webkit-app-region: no-drag;
  width: ${HEADER_HEIGHT * 0.6}px;
  height: ${HEADER_HEIGHT * 0.6}px;
  margin-right: ${HEADER_HEIGHT * 0.2}px;
  border-radius: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`)({
  hoverable: true,
  init: {
    backgroundColor: PANEL_COLOURS[0]
  },
  hover: {
    backgroundColor: PANEL_COLOURS[3]
  }
});

export default HeaderButton;
