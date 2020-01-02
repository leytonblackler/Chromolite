import React from "react";
import styled from "styled-components";
import { ClipLoader } from "react-spinners";
import { ACCENT_COLOR } from "../../../config/constants";

const Loading = () => {
  console.log("Loading...");
  return (
    <MainContainer>
      <ClipLoader size={60} color={ACCENT_COLOR} />
    </MainContainer>
  );
};

const MainContainer = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default Loading;
