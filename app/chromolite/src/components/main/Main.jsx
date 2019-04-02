import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import styles from './Main.styles';
import SidePanel from './side-panel/SidePanel'
import ContentPanel from './content-panel/ContentPanel'

const main = props => (
  <div className={props.classes.root}>
    <SidePanel />
    <ContentPanel />
  </div>
);

export default withStyles(styles)(main);
