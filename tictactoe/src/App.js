import { Component } from 'react';
import './App.css';
import { Api } from './components/Api';
import TicTacToe from './components/tictactoe';


export class App extends Component {
  api = {}

  constructor(props) {
    super(props);
    this.api = new Api();
    this.state = {
      game: null
    }

    this.startGame = this.startGame.bind(this);
    this.reset = this.reset.bind(this);
  }


  reset(){
    console.log("game")
    this.setState({
      game: null
    })
    
    console.log("game")
  }
  async startGame(){
    const that = this;
    let playerhold = JSON.stringify({
      "p1": {
        "username": "Vincent one"
      },
      "p2": {
        "username": "Vincent two"
      }
    });
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: playerhold}
      await fetch("http://localhost:8080/game/start", 
      requestOptions
        )
        .then((response) => {
          response.json()
          .then((createdGame) => {
            that.setState({
              game : createdGame
            })
          }) 
        })
      .catch(err => {console.log(err);
      });
  }
  
  render (){

    if (this.state.game == null) {
      return (
        <div>
          <h1>Press the button to start the game.</h1>
          <button onClick={this.startGame}>Start game</button>
        </div>
      )
    }
    return(
      <div>
        <TicTacToe
        board = {this.state.game.board}
        gameId = {this.state.game.gameId}
        resetGame = {this.reset}
        />
      </div>
    )
  }
    
}

export default App;