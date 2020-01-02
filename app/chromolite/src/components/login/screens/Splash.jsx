import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import { ClipLoader } from "react-spinners";
import Loading from "./Loading";

class Splash extends Component {
  componentDidMount() {
    console.log("did mount");
    const redirectToLogin = (() => {
      console.log("redirecting to login...");
      this.props.history.push("login");
    }).bind(this);
    setTimeout(redirectToLogin, 3000);
  }
  render() {
    console.log("history?", this.props.history);
    return <Loading />;
  }
}

export default Splash;
