import React, { Component } from 'react';
import Main from './components/main/Main.jsx';

// const electron = window.require('electron');
// const fs = electron.remote.require('fs');
// const ipcRenderer  = electron.ipcRenderer;
//https://github.com/electron/electron/issues/7300

class App extends Component {
  render() {
    return (
      <Main />
    );
  }
}

export default App;
