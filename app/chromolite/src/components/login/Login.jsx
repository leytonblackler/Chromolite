import React from "react";
import { useSelector } from "react-redux";
import { Redirect, useLocation } from "react-router-dom";
import ScreenTransitionHandler from "../common/ScreenTransitionHandler";
import LoginOptions from "./screens/LoginOptions";
import CreateAccount from "./screens/CreateAccount";
import LoginWithEmail from "./screens/LoginWithEmail";
import AccountBenefits from "./screens/AccountBenefits";
import Loading from "../common/Loading";

const Login = () => {
  const location = useLocation();
  const isAuthenticated = useSelector(state => state.auth.isAuthenticated);
  return isAuthenticated ? (
    <Redirect to="/" from={location.pathname} />
  ) : (
    <ScreenTransitionHandler
      basePath="/login"
      screens={[
        {
          path: "options",
          component: LoginOptions,
          enterDirection: "left",
          exitDirection: "left"
        },
        {
          path: "create-account",
          component: CreateAccount,
          enterDirection: "right",
          exitDirection: "left"
        },
        {
          path: "email",
          component: LoginWithEmail,
          enterDirection: "right",
          exitDirection: "right"
        },
        {
          path: "google",
          component: Loading,
          enterDirection: "right",
          exitDirection: "left"
        },
        {
          path: "account-benefits",
          component: AccountBenefits,
          enterDirection: "right",
          exitDirection: "right"
        },
        {
          path: "verifying",
          component: Loading,
          enterDirection: "right",
          exitDirection: "right"
        }
      ]}
    />
  );
};

export default Login;
