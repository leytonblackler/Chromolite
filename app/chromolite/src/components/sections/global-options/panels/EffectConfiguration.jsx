import React from "react";
import SectionPanel from "../../../common/SectionPanel";

const TitleContent = () => <div>title content</div>;

const MainContent = () => <div>main content</div>;

const EffectConfiguration = () => (
  <SectionPanel
    title="Effect Configuration"
    titleContent={<TitleContent />}
    width={350}
  >
    <MainContent />
  </SectionPanel>
);

export default EffectConfiguration;
