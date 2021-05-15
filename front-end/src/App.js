import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Switch, Route} from "react-router-dom";
import UserHome from "./pages/userHome";

function App() {
  return (
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={UserHome}/>
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
