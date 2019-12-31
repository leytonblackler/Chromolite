import React from "react";
import PropTypes from "prop-types";
import styled from "styled-components";
import PanelTitle from "./PanelTitle";

import {
  PANEL_MARGINS,
  PANEL_RADIUS,
  PANEL_COLOURS
} from "../../config/constants";

const SectionPanel = ({ title, titleContent, children, width }) => {
  //
  return (
    <MainContainer width={width}>
      <TitleArea>
        <PanelTitle>{title}</PanelTitle>
      </TitleArea>
      <ContentContainer>{children}</ContentContainer>
    </MainContainer>
  );
};

// {titleContent}

const MainContainer = styled.div`
  width: ${({ width }) => (width ? width : "default")}px;
  flex-grow: ${({ width }) => (width ? 0 : 1)};
  display: flex;
  flex-direction: column;
`;

const ContentContainer = styled.div`
  background-color: ${PANEL_COLOURS[1]};
  flex-grow: 1;
  border-radius: ${PANEL_RADIUS}px;
`;

const TitleArea = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding-bottom: ${PANEL_MARGINS}px;
`;

SectionPanel.defaultProps = {
  titleContent: null,
  width: null
};

SectionPanel.propTypes = {
  title: PropTypes.string.isRequired,
  titleContent: PropTypes.node,
  children: PropTypes.node.isRequired,
  width: PropTypes.number.isRequired
};

export default SectionPanel;
