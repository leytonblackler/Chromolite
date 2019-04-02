import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import styles from './ContentPanel.styles';

const contentPanel = props => (
  <div className={props.classes.root}>Circular Std Book Regular</div>
);

export default withStyles(styles)(contentPanel);
