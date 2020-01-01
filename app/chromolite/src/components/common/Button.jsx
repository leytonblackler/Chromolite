import React from "react";
import PropTypes from "prop-types";
import styled from "styled-components";
import { motion } from "framer-motion";
import Color from "color";
import Icon from "@mdi/react";

import {
  ACCENT_COLOR,
  TEXT_COLOR,
  LETTER_SPACING,
  BASE_FONT_SIZE,
  BASE_FONT_WEIGHT
} from "../../config/constants";

const Button = ({ onClick, type, text, icon, iconPosition }) => {
  return (
    <MainContainer
      onClick={onClick}
      whileHover={{
        backgroundColor: Color(ACCENT_COLOR)
          .fade(0.75)
          .string()
      }}
    >
      {icon && iconPosition === "left" && (
        <LeftIcon size={0.65} path={icon} color={ACCENT_COLOR} />
      )}
      <Text>{text}</Text>
      {icon && iconPosition === "right" && (
        <RightIcon size={0.65} path={icon} color={ACCENT_COLOR} />
      )}
    </MainContainer>
  );
};

const MainContainer = styled(motion.div)`
  border-style: solid;
  border-width: 1px;
  border-color: ${ACCENT_COLOR};
  background-color: rgba(0, 0, 0, 0);
  padding: 4px;
  padding-left: 10px;
  padding-right: 10px;
  border-radius: 4px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
`;

const LeftIcon = styled(Icon)`
  padding-right: 2px;
  margin-left: -2px;
`;

const RightIcon = styled(Icon)`
  padding-left: 2px;
  margin-right: -2px;
`;

const Text = styled(motion.div)`
  text-transform: uppercase;
  white-space: nowrap;
  color: ${ACCENT_COLOR};
  font-size: ${BASE_FONT_SIZE}pt;
  font-weight: ${BASE_FONT_WEIGHT};
  letter-spacing: ${LETTER_SPACING}px;
`;

Button.defaultProps = {
  icon: null,
  iconPosition: "left"
};

Button.propTypes = {
  onClick: PropTypes.func.isRequired,
  type: PropTypes.oneOf(["solid", "outlined"]).isRequired,
  text: PropTypes.string.isRequired,
  icon: PropTypes.any,
  iconPosition: PropTypes.string
};

export default Button;
