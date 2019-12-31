import React, { Component } from "react";
import styled from "styled-components";

import { WINDOW_RADIUS } from "../config/constants";

class ContentArea extends Component {
  render() {
    return <MainContainer>placeholder text</MainContainer>;
  }
}

const MainContainer = styled.div`
  height: 100%;
  width: 100%;
  background-color: #12161b;
  border-radius: 0px 0px ${WINDOW_RADIUS}px 0px;
`;

export default ContentArea;
