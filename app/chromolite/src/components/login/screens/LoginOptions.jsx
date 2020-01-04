import React from "react";
import styled from "styled-components";
import { useHistory } from "react-router-dom";
import { AnimatePresence, motion } from "framer-motion";
import Button from "../../common/Button";
import Spacer from "../../common/Spacer";
import { ReactComponent as GoogleLogo } from "../../../icons/google_logo.svg";
import { PANEL_MARGINS } from "../../../config/constants";

const LoginOptions = () => {
  const history = useHistory();
  return (
    <MainContainer>
      <Button
        text="Login with Email"
        type="solid"
        color="grey"
        size="large"
        onClick={() => {
          history.push("/login/email");
        }}
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
        onClick={() => {
          history.push("/login/google");
        }}
      />
      <Spacer />
      <Button
        text="Create an Account"
        type="solid"
        color="grey"
        size="large"
        onClick={() => {
          history.push("/login/create-account");
        }}
      />
      <Spacer />
      <Button
        text="Continue Without Account"
        type="solid"
        color="grey"
        size="large"
        onClick={() => {
          history.push("/login/account-benefits");
        }}
      />
    </MainContainer>
  );
};

const GoogleLogoContainer = styled.div`
  margin-right: 10px;
  display: flex;
`;

const MainContainer = styled(motion.div)`
  display: flex;
  flex-direction: column;
  margin: ${PANEL_MARGINS}px;
`;

export default LoginOptions;
