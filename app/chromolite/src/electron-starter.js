// import {
//   WINDOW_WIDTH,
//   WINDOW_HEIGHT,
//   WINDOW_SHADOW_SIZE
// } from "./constants.js";

// TODO: Fix above import instead of using constants below.
const WINDOW_WIDTH = 1000;
const WINDOW_HEIGHT = 620;
const WINDOW_SHADOW_SIZE = 15;

// Modules to control application life and create native browser window
const { app, BrowserWindow } = require("electron");
const path = require("path");

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
let mainWindow;

function createWindow() {
  // Create the browser window.
  mainWindow = new BrowserWindow({
    width: WINDOW_WIDTH + 2 * WINDOW_SHADOW_SIZE,
    height: WINDOW_HEIGHT + 2 * WINDOW_SHADOW_SIZE,
    frame: false,
    transparent: true,
    resizable: false,
    hasShadow: true,
    webPreferences: {}
  });

  // and load the index.html of the app.
  // mainWindow.loadFile("index.html");
  mainWindow.loadURL("http://localhost:3000");

  // Open the DevTools.
  // mainWindow.webContents.openDevTools();

  // Emitted when the window is closed.
  mainWindow.on("closed", function() {
    // Dereference the window object, usually you would store windows
    // in an array if your app supports multi windows, this is the time
    // when you should delete the corresponding element.
    mainWindow = null;
  });
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on("ready", function() {
  setTimeout(function() {
    createWindow();
  }, 300);
});

// Quit when all windows are closed.
app.on("window-all-closed", function() {
  // On macOS it is common for applications and their menu bar
  // to stay active until the user quits explicitly with Cmd + Q
  if (process.platform !== "darwin") app.quit();
});

app.on("activate", function() {
  // On macOS it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  if (mainWindow === null) createWindow();
});

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and require them here.
