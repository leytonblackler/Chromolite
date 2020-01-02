import React, { Component } from "react";
import styled from "styled-components";
import Button from "../common/Button";
import Spacer from "../common/Spacer";
import { PANEL_MARGINS } from "../../config/constants";

class Login extends Component {
  render() {
    return (
      <MainContainer>
        <Button
          text="Sign in with Email"
          type="solid"
          color="grey"
          size="large"
        />
        <Spacer />
        <Button
          text="Sign in With Google"
          type="solid"
          color="grey"
          size="large"
        />
        <Spacer />
        <Button
          text="Create an Account"
          type="solid"
          color="grey"
          size="large"
        />
        <Spacer />
        <Button
          text="Continue Without Account"
          type="solid"
          color="grey"
          size="large"
        />
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  // height: calc(100% - (2 * ${PANEL_MARGINS}px));
  display: flex;
  flex-direction: column;
  // background-color: orange;
  margin: ${PANEL_MARGINS}px;
`;

export default Login;
