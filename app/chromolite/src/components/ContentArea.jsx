import React, { Component } from "react";
import styled from "styled-components";
import GlobalOptions from "./sections/global-options/GlobalOptions";

import { WINDOW_RADIUS, PANEL_MARGINS } from "../config/constants";

class ContentArea extends Component {
  render() {
    return (
      <MainContainer>
        <GlobalOptions />
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  height: calc(100% - 2 * ${PANEL_MARGINS}px);
  width: calc(100% - 2 * ${PANEL_MARGINS}px);
  background-color: #12161b;
  border-radius: 0px 0px ${WINDOW_RADIUS}px 0px;
  overflow: hidden;
  padding: ${PANEL_MARGINS}px;
`;

export default ContentArea;
