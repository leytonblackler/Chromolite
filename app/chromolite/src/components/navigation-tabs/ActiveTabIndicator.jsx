import React, { Component } from "react";
import styled from "styled-components";
import posed from "react-pose";

import {
  NAVIGATION_TAB_INDICATOR_WIDTH,
  NAVIGATION_TAB_INDICATOR_HEIGHT,
  NAVIGATION_TAB_HEIGHT,
  ACCENT_COLOR
} from "../../constants";

class ActiveTabIndicator extends Component {
  constructor(props) {
    super(props);
    const { sections } = props;
    this.buildPoses(sections);
  }

  buildPoses = sections => {
    const poses = sections.reduce(
      (accumulator, currentSection, currentIndex) => {
        return {
          ...accumulator,
          [currentSection.id]: {
            marginTop:
              (NAVIGATION_TAB_HEIGHT - NAVIGATION_TAB_INDICATOR_HEIGHT) / 2 +
              currentIndex * NAVIGATION_TAB_HEIGHT
          }
        };
      },
      {}
    );

    this.indicator = posed(styled.div`
    position: fixed;
    height: ${NAVIGATION_TAB_INDICATOR_HEIGHT}px;
    width ${NAVIGATION_TAB_INDICATOR_WIDTH}px;
    background-color: ${ACCENT_COLOR};
    border-radius: 0px 20px 20px 0px;
    `)(poses);
  };

  render() {
    const { activeSectionId } = this.props;
    const Indicator = this.indicator;
    return <Indicator pose={activeSectionId} />;
  }
}

export default ActiveTabIndicator;
