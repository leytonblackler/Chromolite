import React, { Component } from "react";
import styled from "styled-components";
import { WINDOW_RADIUS, HEADER_HEIGHT } from "../../config/constants";
import HeaderButton from "./HeaderButton";
import { ReactComponent as MinimiseIcon } from "../../icons/minimise.svg";
import { ReactComponent as CloseIcon } from "../../icons/close.svg";

const LOGO_PATH = "/images/logo.png";

class Header extends Component {
  onMinimiseButtonClicked = () => {
    console.log("Minimise window button clicked.");
    // TODO: Minimise window.
  };

  onCloseButtonClicked = () => {
    console.log("Close window button clicked.");
    // TODO: Close window.
  };

  render() {
    return (
      <MainContainer>
        <Logo src={LOGO_PATH} alt="Logo" />
        <RightArea>
          <HeaderButton
            icon={MinimiseIcon}
            onClick={this.onMinimiseButtonClicked}
          />
          <HeaderButton icon={CloseIcon} onClick={this.onCloseButtonClicked} />
        </RightArea>
      </MainContainer>
    );
  }
}

const MainContainer = styled.div`
  -webkit-app-region: drag
  height: ${HEADER_HEIGHT}px;
  width: 100%;
  background-color: #1C232C;
  border-radius: ${WINDOW_RADIUS}px ${WINDOW_RADIUS}px 0px 0px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
`;

const Logo = styled.img`
  height: calc(${HEADER_HEIGHT}px * 0.4);
  margin-left: calc(${HEADER_HEIGHT}px * 0.4);
`;

const RightArea = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  margin-left: auto;
`;

export default Header;
