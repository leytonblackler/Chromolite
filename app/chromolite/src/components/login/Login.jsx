import React, { Component } from "react";
import styled from "styled-components";
import Button from "../common/Button";
import Spacer from "../common/Spacer";
import { ReactComponent as GoogleLogo } from "../../icons/google_logo.svg";
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
          onClick={() => console.log("Sign in with email button clicked.")}
        />
        <Spacer />
        <Button
          text="Sign in With Google"
          type="solid"
          color="white"
          size="large"
          adornment={
            <GoogleLogoContainer>
              <GoogleLogo />
            </GoogleLogoContainer>
          }
          adornmentPosition="left"
          onClick={() => console.log("Sign in with Google button clicked.")}
        />
        <Spacer />
        <Button
          text="Create an Account"
          type="solid"
          color="grey"
          size="large"
          onClick={() => console.log("Create an account button clicked.")}
        />
        <Spacer />
        <Button
          text="Continue Without Account"
          type="solid"
          color="grey"
          size="large"
          onClick={() =>
            console.log("Continue without account button clicked.")
          }
        />
      </MainContainer>
    );
  }
}

const GoogleLogoContainer = styled.div`
  margin-right: 10px;
  display: flex;
`;

const MainContainer = styled.div`
  // height: calc(100% - (2 * ${PANEL_MARGINS}px));
  display: flex;
  flex-direction: column;
  // background-color: orange;
  margin: ${PANEL_MARGINS}px;
`;

export default Login;
