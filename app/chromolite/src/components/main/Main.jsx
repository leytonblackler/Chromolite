import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import styles from './Main.styles';

const main = props => (
  <div className={props.classes.main}>main app content</div>
);

export default withStyles(styles)(main);
