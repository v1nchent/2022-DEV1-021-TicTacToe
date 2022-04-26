import { useEffect, useRef, useState, Component} from "react";
import Grid from "./grid";

export class TicTacToe extends Component {
    constructor(props) {
      super(props);
      this.state = {
        gridArray: props.board,
        gameId: props.gameId,
        firstPlayerTurn: true,
        boardIsFull: false, 
        winner: null
      }
  
      this.onTilePress = this.onTilePress.bind(this);
      this.checkIfBoardIsFull = this.checkIfBoardIsFull.bind(this);
    }
    async gamePlay(playertype, coX, coY, gameId){
      const that = this;
          let playerhold = JSON.stringify({
              "type": playertype,
              "coordinateX" : coX,
              "coordinateY" : coY,
              "gameId" : gameId
          });
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: playerhold}
        await fetch("http://localhost:8080/game/gameplay", 
        requestOptions
          )
          .then((response) => {
            response.json()
            .then((progressedGame) => {
              that.setState({
                gridArray : progressedGame.board,
                winner: progressedGame.winner
              })
            }) 
          })
        .catch(err => {console.log(err);
        });
    }
    
    checkIfBoardIsFull(){
      let boardHasEmptySpot = false;
      for (let indexX = 0; indexX < this.state.gridArray.length; indexX++) {
        for (let indexY = 0; indexY < this.state.gridArray[indexX].length; indexY++) {
          if (this.state.gridArray[indexX][indexY]===0) {
            boardHasEmptySpot = true;
          }
        }
      }
      if (!boardHasEmptySpot) {
        this.setState({
          boardIsFull: true
        })
      }
    }

    onTilePress(index){
        this.gamePlay(this.state.firstPlayerTurn?0:1 , index[0], index[1], this.state.gameId)
        let currentTurn = !this.state.firstPlayerTurn;
        this.state.firstPlayerTurn =  currentTurn;
        this.checkIfBoardIsFull()
    }



    render(){
      if(this.state.winner === null && !this.state.boardIsFull){
      
      this.checkIfBoardIsFull()
      return(
        <div>
          <Grid
          gridArray={this.state.gridArray}
          onTilePress={this.onTilePress}
          />
        </div>
        )
      }
      else if(this.state.winner === "X" || this.state.winner === "O")
      {
        for (let indexX = 0; indexX < this.state.gridArray.length; indexX++) {
          for (let indexY = 0; indexY < this.state.gridArray[indexX].length; indexY++) {
            if (this.state.gridArray[indexX][indexY]===0) {
              this.state.gridArray[indexX][indexY]=3;
            }
          }
          
        }
      return(
        <div>
          <h1>The player using {this.state.winner} has won</h1>
          <Grid
          gridArray={this.state.gridArray}
          onTilePress={this.onTilePress}
          />
          <h1>For a new game, press the following button</h1>
          <button onClick={this.props.resetGame}>Start game</button>
        </div>
      )}
      else if(this.state.winner===null && this.state.boardIsFull===true){
        return(
          <div>
          <h1>This game was a tie, you both played outstanding</h1>
          <Grid
          gridArray={this.state.gridArray}
          onTilePress={this.onTilePress}
          />
          <h1>For a new game, press the following button</h1>
          <button onClick={this.props.resetGame}>New Game</button>
        </div>
        )
      }
      }
}

export default TicTacToe;