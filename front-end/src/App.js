import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Switch, Route} from "react-router-dom";
import UserHome from "./pages/userHome";
import Registration from "./components/Registration";
import Login from "./components/Login";
import React from "react";
import EnrolledCourses from "./pages/EnrolledCourses";
import FinishedCourses from "./pages/finishedCourses";
import AuthorReport from "./pages/AuthorReport";
import AuthorHome from "./pages/authorHome";
import AdminReport from "./pages/adminReport";
import AdminBlockedUsers from "./pages/adminBlockedUsers";
import AdminHome from "./pages/adminHome";

class App extends React.Component {

    constructor(props) {
        super(props);
        if (localStorage.getItem("id") == null) {
            localStorage.setItem("id", null);
            localStorage.setItem("role", null);
        }
    }

    render() {
        return (
            <BrowserRouter>
                <Switch>
                    <Route exact path={`/`} component={UserHome}/>
                    <Route exact path="/courses" component={UserHome}/>
                    <Route exact path={`/registration`} component={Registration} />
                    <Route exact path={`/login`} component={Login} />
                    <Route path={'/enrolled'} component={EnrolledCourses}/>
                    <Route path={'/finished'} component={FinishedCourses}/>
                    <Route path={'/author-report'} component={AuthorReport}/>
                    <Route path={'/author-home'} component={AuthorHome}/>
                    <Route path={'/admin-home'} component={AdminHome}/>
                    <Route path={'/admin-report'} component={AdminReport}/>
                    <Route path={'/block-unblock'} component={AdminBlockedUsers}/>
                </Switch>
            </BrowserRouter>
        )

    }




    /*<div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>*/
  ;
}

export default App;
