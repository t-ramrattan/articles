import React from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Route,
  Switch 
} from 'react-router-dom';
import Home from './components/Home';
import Article from './components/Article';

function App() {
  return (
    <Router>
      <Switch>
      <Route path='/article/:id' component={Article}/>
      <Route path='/' component={Home}/>
      </Switch>
    </Router>
  );
}

export default App;
