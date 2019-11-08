import React from "react";
import styled from "styled-components";
import Tab from "./Tab";
import ActiveTabIndicator from "./ActiveTabIndicator";

import { WINDOW_RADIUS } from "../../constants";

const NavigationTabs = props => {
  const { sections, activeSectionId } = props;
  return (
    <MainContainer>
      <ActiveTabIndicator
        sections={sections}
        activeSectionId={activeSectionId}
      />
      {sections.map(section => (
        <Tab
          key={section.id}
          icon={section.icon}
          label={section.name}
          active={section.id === activeSectionId}
          onClick={() => props.onTabClicked(section.id)}
        />
      ))}
    </MainContainer>
  );
};

const MainContainer = styled.div`
  height: 100%;
  background-color: #191e25;
  border-radius: 0px 0px 0px ${WINDOW_RADIUS}px;
`;

export default NavigationTabs;
