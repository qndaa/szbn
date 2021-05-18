import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Switch, Route} from "react-router-dom";
import UserHome from "./pages/userHome";
import Registration from "./components/Registration";
import Login from "./components/Login";

function App() {
  return (
      <BrowserRouter>
        <Switch>
            <Route exact path="/courses" component={UserHome}/>
            <Route exact path={`/registration`} component={Registration} />
            <Route exact path={`/login`} component={Login} />
        </Switch>
      </BrowserRouter>
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
  );
}

export default App;
