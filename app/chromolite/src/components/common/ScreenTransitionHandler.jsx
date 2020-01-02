import React from "react";
import styled from "styled-components";
import { AnimatePresence, motion } from "framer-motion";
import { Switch, Route, Redirect, useLocation } from "react-router-dom";
import { PANEL_MARGINS } from "../../config/constants";

const screenVariants = {
  initial: {
    opacity: 0,
    x: "-100vw",
    scale: 0.8
  },
  in: {
    opacity: 1,
    x: 0,
    scale: 1
  },
  out: {
    opacity: 0,
    x: "100vw",
    scale: 1.2
  }
};

const screenTransition = {
  type: "tween",
  ease: "anticipate",
  duration: 0.5
};

const ScreenTransitionHandler = ({ screens, basePath }) => {
  const location = useLocation();
  return (
    <MainContainer>
      <AnimatePresence>
        <Switch location={location} key={location.pathname}>
          {screens.map(screen => {
            const ScreenComponent = screen.component;
            return (
              <Route
                key={screen.path}
                path={`${basePath}/${screen.path}`}
                component={() => (
                  <TransitionableScreen
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={screenVariants}
                    transition={screenTransition}
                  >
                    <ScreenComponent />
                  </TransitionableScreen>
                )}
              />
            );
          })}
          <Redirect from={basePath} to={`${basePath}/options`} />
        </Switch>
      </AnimatePresence>
    </MainContainer>
  );
};

const MainContainer = styled.div`
  position: relative;
`;

const TransitionableScreen = styled(motion.div)`
  width: 100%;
  position: absolute;
`;

export default ScreenTransitionHandler;
