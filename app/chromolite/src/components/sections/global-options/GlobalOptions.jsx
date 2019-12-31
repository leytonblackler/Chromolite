import React from "react";
import styled from "styled-components";
import EffectConfiguration from "./panels/EffectConfiguration";
import Devices from "./panels/Devices";
import Spacer from "../../common/Spacer";

const GlobalOptions = () => {
  //
  return (
    <MainContainer>
      <PanelsContainer>
        <EffectConfiguration width={200} />
        <Spacer />
        <Devices />
      </PanelsContainer>
    </MainContainer>
  );
};

const MainContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
`;

const PanelsContainer = styled.div`
  // background-color: orange;
  flex-grow: 1;
  display: flex;
  flex-direction: row;
`;

export default GlobalOptions;
