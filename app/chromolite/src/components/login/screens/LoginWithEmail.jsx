import React, { useState } from "react";
import styled from "styled-components";
import { useHistory } from "react-router-dom";
import { motion } from "framer-motion";
import Button from "../../common/Button";
import Spacer from "../../common/Spacer";
import { PANEL_MARGINS } from "../../../config/constants";
import { mdiArrowLeft as ArrowLeftIcon } from "@mdi/js";

import { useDispatch, useSelector } from "react-redux";
import { loginUser } from "../../../redux/actions";

const LoginWithEmail = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();
  const dispatch = useDispatch();
  const isLoggingIn = useSelector(state => state.auth.isLoggingIn);
  const loginError = useSelector(state => state.auth.loginError);
  console.log("login error", loginError);
  return (
    <MainContainer>
      <span style={{ color: "white" }}>sign in with email</span>
      {loginError && <span style={{ color: "white" }}>login error</span>}
      {isLoggingIn && <span style={{ color: "white" }}>logging in...</span>}
      <div>
        <input
          type="text"
          value={email}
          onChange={event => setEmail(event.target.value)}
        />
      </div>
      <Spacer />
      <div>
        <input
          type="password"
          value={password}
          onChange={event => setPassword(event.target.value)}
        />
      </div>
      <Spacer />
      <Button
        text="Log In"
        type="solid"
        color="grey"
        size="large"
        onClick={() => {
          dispatch(loginUser(email, password));
        }}
      />
      <Spacer />
      <Button
        text="Back"
        type="solid"
        color="grey"
        size="large"
        icon={ArrowLeftIcon}
        iconPosition="left"
        onClick={() => history.push("/login/options")}
      />
    </MainContainer>
  );
};

const MainContainer = styled(motion.div)`
  display: flex;
  flex-direction: column;
  margin: ${PANEL_MARGINS}px;
`;

export default LoginWithEmail;
