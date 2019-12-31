import React from "react";
import styled from "styled-components";

import { PANEL_MARGINS } from "../../config/constants";

const Spacer = styled.div`
  width: ${PANEL_MARGINS}px;
  height: ${PANEL_MARGINS}px;
  background-color: transparent;
`;

export default () => <Spacer />;
