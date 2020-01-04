import firebase from "firebase/app";
import "firebase/auth";
import "firebase/firestore";

const firebaseConfig = {
  apiKey: "AIzaSyA0EthwQLjIzc_b7RcrdK9yzO07BYLHSZY",
  authDomain: "chromolite-268fe.firebaseapp.com",
  databaseURL: "https://chromolite-268fe.firebaseio.com",
  projectId: "chromolite-268fe",
  storageBucket: "chromolite-268fe.appspot.com",
  messagingSenderId: "287009188791",
  appId: "1:287009188791:web:e9c6f2f6ad3e8eaa99d632",
  measurementId: "G-9CQD3K5QD1"
};

export const myFirebase = firebase.initializeApp(firebaseConfig);
const baseDb = myFirebase.firestore();
export const db = baseDb;
