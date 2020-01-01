import React from "react";
import styled from "styled-components";
import SectionPanel from "../../../common/SectionPanel";
import Button from "../../../common/Button";
import Spacer from "../../../common/Spacer";
import { mdiChevronDown as ChevronDownIcon } from "@mdi/js";
const { ipcRenderer } = window.require("electron");

const resizeWindow = () => {
  console.log("resizing window...");
  ipcRenderer.send("resize-window", { width: 300, height: 300 });
};

const TitleContent = () => (
  <TitleButtonsContainer>
    <Button text="Sort By" icon={ChevronDownIcon} iconPosition="right" />
    <Spacer />
    <Button text="Select All" />
    <Spacer />
    <Button text="Deselect All" onClick={resizeWindow} />
  </TitleButtonsContainer>
);

const MainContent = () => <div>main content</div>;

const Devices = () => (
  <SectionPanel title="Devices" titleContent={<TitleContent />}>
    <MainContent />
  </SectionPanel>
);

const TitleButtonsContainer = styled.div`
  display: flex;
  flex-direction: row;
`;

export default Devices;
