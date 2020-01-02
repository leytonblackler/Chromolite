import React, { Component } from "react";
import styled from "styled-components";
import { Switch, Route, Redirect } from "react-router-dom";
import Button from "../common/Button";
import Spacer from "../common/Spacer";
import { motion, AnimatePresence } from "framer-motion";
import { ReactComponent as GoogleLogo } from "../../icons/google_logo.svg";
import { PANEL_MARGINS } from "../../config/constants";
import ScreenTransitionHandler from "../common/ScreenTransitionHandler";
import { useHistory } from "react-router-dom";

const LoginOptions = () => {
  const history = useHistory();
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
        onClick={() => {
          history.push("/login/account-benefits");
        }}
      />
    </MainContainer>
  );
};

const AccountBenefits = () => {
  const history = useHistory();
  return (
    <MainContainer>
      <Button
        text="Sign in with Email"
        type="solid"
        color="grey"
        size="large"
        onClick={() => {
          history.push("/login/options");
        }}
      />
    </MainContainer>
  );
};

const Login = () => (
  <ScreenTransitionHandler
    basePath="/login"
    screens={[
      {
        path: "options",
        component: LoginOptions
      },
      {
        path: "account-benefits",
        component: AccountBenefits
      }
    ]}
  />
);

const GoogleLogoContainer = styled.div`
  margin-right: 10px;
  display: flex;
`;

const MainContainer = styled(motion.div)`
  // height: calc(100% - (2 * ${PANEL_MARGINS}px));
  display: flex;
  flex-direction: column;
  // background-color: orange;
  margin: ${PANEL_MARGINS}px;
`;

export default Login;

// <Switch>
//     {screens.map(screen => (
//       <Route path={`/login/${screen.path}`} component={screen.component} />
//     ))}
//     <Redirect from="/login/" to="/login/options" />
//   </Switch>
