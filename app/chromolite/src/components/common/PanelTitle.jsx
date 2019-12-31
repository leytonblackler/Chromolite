import React from "react";
import styled from "styled-components";

import { TEXT_COLOR, LETTER_SPACING } from "../../config/constants";

const PanelTitle = ({ children }) => {
  return (
    <MainContainer>
      <Text>{children}</Text>
    </MainContainer>
  );
};

const MainContainer = styled.div``;

const Text = styled.div`
  color: ${TEXT_COLOR};
  font-size: 10pt;
  font-weight: 500;
  letter-spacing: ${LETTER_SPACING}px;
`;

export default PanelTitle;
