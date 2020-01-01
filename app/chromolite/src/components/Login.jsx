import React, { Component } from "react";
import styled from "styled-components";

class Login extends Component {
  render() {
    return <MainContainer>login page here</MainContainer>;
  }
}

const MainContainer = styled.div`
  height: 100%;
  display: flex;
  flex-direction: row;
  background-color: orange;
`;

export default Login;
