import React, { Component } from "react";
import styled from "styled-components";
import { motion } from "framer-motion";

import {
  NAVIGATION_TAB_INDICATOR_WIDTH,
  NAVIGATION_TAB_INDICATOR_HEIGHT,
  NAVIGATION_TAB_HEIGHT,
  ACCENT_COLOR
} from "../../config/constants";

const calculateMarginTop = activeSectionId =>
  (NAVIGATION_TAB_HEIGHT - NAVIGATION_TAB_INDICATOR_HEIGHT) / 2 +
  (activeSectionId - 1) * NAVIGATION_TAB_HEIGHT;

const ActiveTabIndicator = ({ activeSectionId }) => (
  <Indicator
    initial={false}
    animate={{ marginTop: calculateMarginTop(activeSectionId) }}
    transition={{ duration: 0.25, type: "tween" }}
  />
);

const Indicator = styled(motion.div)`
  position: fixed;
  height: ${NAVIGATION_TAB_INDICATOR_HEIGHT}px;
  width ${NAVIGATION_TAB_INDICATOR_WIDTH}px;
  background-color: ${ACCENT_COLOR};
  border-radius: 0px 20px 20px 0px;
`;

export default ActiveTabIndicator;
