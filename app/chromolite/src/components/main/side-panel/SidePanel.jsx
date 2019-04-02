import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import styles from './SidePanel.styles';

const LOGO_PATH = '/images/logo.png';

const sidePanel = props => (
  <div className={props.classes.root}>
    <img className={props.classes.logo} src={LOGO_PATH} alt="Logo"/>
  </div>
);

export default withStyles(styles)(sidePanel);
