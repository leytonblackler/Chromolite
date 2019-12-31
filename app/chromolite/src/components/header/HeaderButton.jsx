import React, { Component } from "react";
import styled from "styled-components";
import { motion } from "framer-motion";

import {
  PANEL_COLOURS,
  TEXT_COLOR,
  HEADER_HEIGHT
} from "../../config/constants";

class HeaderButton extends Component {
  render() {
    const { icon, onClick } = this.props;

    const Icon = icon;

    return (
      <MainContainer
        onClick={onClick}
        whileHover={{ backgroundColor: PANEL_COLOURS[3] }}
      >
        <Icon fill={TEXT_COLOR} width="20px" height="20px" />
      </MainContainer>
    );
  }
}

const MainContainer = styled(motion.div)`
  background-color: ${PANEL_COLOURS[0]};
  -webkit-app-region: no-drag;
  width: ${HEADER_HEIGHT * 0.6}px;
  height: ${HEADER_HEIGHT * 0.6}px;
  margin-right: ${HEADER_HEIGHT * 0.2}px;
  border-radius: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

export default HeaderButton;
