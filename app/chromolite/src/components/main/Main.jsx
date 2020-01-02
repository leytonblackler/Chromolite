import React, { Component } from "react";
import styled from "styled-components";
import NavigationTabs from "./navigation-tabs/NavigationTabs";
import SECTIONS from "../../config/sections";
import GlobalOptions from "./sections/global-options/GlobalOptions";

import { PANEL_MARGINS } from "../../config/constants";

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
        <NavigationTabs
          sections={SECTIONS}
          activeSectionId={activeSectionId}
          onTabClicked={this.onTabClicked}
        />
        <SectionContainer>
          <GlobalOptions />
        </SectionContainer>
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  height: 100%;
  display: flex;
  flex-direction: row;
`;

const SectionContainer = styled.div`
  height: calc(100% - 2 * ${PANEL_MARGINS}px);
  width: calc(100% - 2 * ${PANEL_MARGINS}px);
  overflow: hidden;
  padding: ${PANEL_MARGINS}px;
`;

export default Main;
