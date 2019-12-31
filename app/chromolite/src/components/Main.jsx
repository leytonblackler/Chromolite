import React, { Component } from "react";
import styled from "styled-components";
import Header from "./header/Header";
import NavigationTabs from "./navigation-tabs/NavigationTabs";
import ContentArea from "./ContentArea";

import SECTIONS from "../config/sections";

import { WINDOW_RADIUS, HEADER_HEIGHT } from "../config/constants";

class Main extends Component {
  constructor(props) {
    super(props);
    this.state = { activeSectionId: SECTIONS[0].id };
  }

  onTabClicked = sectionId => {
    this.setState({ activeSectionId: sectionId });
  };

  render() {
    const { activeSectionId } = this.state;
    return (
      <MainContainer>
        <Header />
        <LowerContainer>
          <NavigationTabs
            sections={SECTIONS}
            activeSectionId={activeSectionId}
            onTabClicked={this.onTabClicked}
          />
          <ContentArea />
        </LowerContainer>
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  height: 100%;
  border-radius: ${WINDOW_RADIUS}px;
`;

const LowerContainer = styled.div`
  height: calc(100% - ${HEADER_HEIGHT}px);
  display: flex;
  flex-direction: row;
`;

export default Main;
