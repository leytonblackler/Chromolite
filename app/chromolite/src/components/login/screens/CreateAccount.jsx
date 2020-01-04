import React from "react";
import styled from "styled-components";
import { useHistory } from "react-router-dom";
import { motion } from "framer-motion";
import Button from "../../common/Button";
import { PANEL_MARGINS } from "../../../config/constants";
import { mdiArrowLeft as ArrowLeftIcon } from "@mdi/js";

const CreateAccount = () => {
  const history = useHistory();
  return (
    <MainContainer>
      <span style={{ color: "white" }}>create account</span>
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

export default CreateAccount;
