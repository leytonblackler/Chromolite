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
  BASE_FONT_WEIGHT,
  PANEL_COLORS,
  SMALL_FIELD_HEIGHT,
  LARGE_FIELD_HEIGHT
} from "../../config/constants";

const colors = {
  blue: ACCENT_COLOR,
  grey: PANEL_COLORS[3],
  white: "#FFFFFF"
};

const getHoverColor = (type, color) => {
  if (type === "solid") {
    return Color(colors[color])
      .lighten(0.25)
      .rgb()
      .string();
  } else if (type === "outlined") {
    return Color(colors[color])
      .fade(0.75)
      .string();
  }
};

const getTextColor = (type, color) => {
  if (type === "solid") {
    return color === "blue" ? PANEL_COLORS[0] : TEXT_COLOR;
  } else if (type === "outlined") {
    return colors[color];
  }
};

const Button = ({ onClick, type, color, size, text, icon, iconPosition }) => {
  const hoverColor = getHoverColor(type, color);
  const textColor = getTextColor(type, color);
  return (
    <MainContainer
      onClick={onClick}
      whileHover={{
        backgroundColor: hoverColor,
        borderColor: type === "outlined" ? colors[color] : hoverColor
      }}
      type={type}
      color={color}
      size={size}
    >
      {icon && iconPosition === "left" && (
        <LeftIcon size={0.65} path={icon} color={textColor} />
      )}
      <Text type={type} color={textColor}>
        {text}
      </Text>
      {icon && iconPosition === "right" && (
        <RightIcon size={0.65} path={icon} color={textColor} />
      )}
    </MainContainer>
  );
};

const MainContainer = styled(motion.div)`
  border-style: solid;
  border-width: 1px;
  border-color: ${({ color }) => colors[color]};
  background-color: ${({ type, color }) => {
    if (type === "solid") {
      return colors[color];
    } else if (type === "outlined") {
      return "rgba(0, 0, 0, 0)";
    }
  }};
  height: ${({ size }) =>
    size === "small" ? SMALL_FIELD_HEIGHT : LARGE_FIELD_HEIGHT}px;
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
  color: ${({ color }) => color};
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
  color: PropTypes.oneOf(Object.keys(colors)).isRequired,
  size: PropTypes.oneOf(["small", "large"]).isRequired,
  text: PropTypes.string.isRequired,
  icon: PropTypes.any,
  iconPosition: PropTypes.string
};

export default Button;
