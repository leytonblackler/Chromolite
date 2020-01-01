import React, { Component } from "react";
import styled from "styled-components";
import { motion } from "framer-motion";

import {
  NAVIGATION_TAB_HEIGHT,
  NAVIGATION_TAB_SPACING,
  LETTER_SPACING,
  BASE_FONT_SIZE,
  BASE_FONT_WEIGHT,
  ACCENT_COLOR,
  TEXT_COLOR,
  PANEL_COLOURS
} from "../../config/constants";

class Tab extends Component {
  render() {
    const { active, icon, label, onClick } = this.props;

    const Icon = icon;

    return (
      <MainContainer
        whileHover={{ backgroundColor: PANEL_COLOURS[2] }}
        onClick={onClick}
      >
        <Icon
          fill={active ? ACCENT_COLOR : TEXT_COLOR}
          width="16px"
          height="16px"
        />
        <Text
          initial={false}
          animate={{ color: active ? ACCENT_COLOR : TEXT_COLOR }}
        >
          {label}
        </Text>
      </MainContainer>
    );
  }
}

const MainContainer = styled(motion.div)`
  background-color: ${PANEL_COLOURS[1]};
  height: ${NAVIGATION_TAB_HEIGHT}px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  margin-top: ${NAVIGATION_TAB_SPACING}px;
  padding-left: 20px;
  padding-right: 20px;
  cursor: pointer;
`;

const Text = styled(motion.div)`
  text-transform: uppercase;
  font-size: ${BASE_FONT_SIZE}pt;
  letter-spacing: ${LETTER_SPACING}px;
  font-weight: ${BASE_FONT_WEIGHT};
  margin-left: 10px;
  white-space: nowrap;
`;

export default Tab;
