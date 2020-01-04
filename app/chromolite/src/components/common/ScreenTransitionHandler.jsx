import React from "react";
import PropTypes from "prop-types";
import styled from "styled-components";
import { AnimatePresence, motion } from "framer-motion";
import { Switch, Route, Redirect, useLocation } from "react-router-dom";

const screenTransition = {
  type: "tween",
  ease: "anticipate",
  duration: 0.4
};

const screenVariants = (enterDirection, exitDirection) => ({
  initial: {
    opacity: 0,
    x: enterDirection === "left" ? "-100vw" : "100vw"
  },
  in: {
    opacity: 1,
    x: 0
  },
  out: {
    opacity: 0,
    x: exitDirection === "left" ? "-100vw" : "100vw"
  }
});

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
                    variants={screenVariants(
                      screen.enterDirection,
                      screen.exitDirection
                    )}
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

// <Switch location={location} key={location.pathname}></Switch>
//

const MainContainer = styled.div`
  position: relative;
`;

const TransitionableScreen = styled(motion.div)`
  width: 100%;
  position: absolute;
`;

ScreenTransitionHandler.propTypes = {
  screens: PropTypes.array.isRequired,
  basePath: PropTypes.string.isRequired
};

export default ScreenTransitionHandler;
