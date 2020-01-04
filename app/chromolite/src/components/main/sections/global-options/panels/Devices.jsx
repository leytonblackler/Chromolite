import React from "react";
import styled from "styled-components";
import SectionPanel from "../../../../common/SectionPanel";
import Button from "../../../../common/Button";
import Spacer from "../../../../common/Spacer";
import { mdiChevronDown as ChevronDownIcon } from "@mdi/js";
import { useDispatch } from "react-redux";
import { logoutUser } from "../../../../../redux/actions";

const TitleContent = () => {
  const dispatch = useDispatch();
  return (
    <TitleButtonsContainer>
      <Button
        text="Sort By"
        type="outlined"
        color="blue"
        size="small"
        icon={ChevronDownIcon}
        iconPosition="right"
        onClick={() => console.log("Sort by button clicked.")}
      />
      <Spacer />
      <Button
        type="outlined"
        color="blue"
        size="small"
        text="Select All"
        onClick={() => console.log("Select all button clicked.")}
      />
      <Spacer />
      <Button
        type="outlined"
        color="blue"
        size="small"
        text="Deselect All"
        onClick={() => dispatch(logoutUser())}
      />
    </TitleButtonsContainer>
  );
};

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
