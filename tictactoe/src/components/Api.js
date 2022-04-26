import React, { PureComponent } from "react";

export class Api extends PureComponent {

  constructor(props) {
    super(props);
  }

  async startGame(){
      
    let baseURL= "localhost:8080";
      return await fetch(baseURL + "/game/start", {
          method: "POST",
          mode: "CORS",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify({username: "player1", username: "player2"})
  })
  .then((response) => {
      console.log(response)
      if (response.status === 200)
      {
        return response.json();
      } else {
        console.log("not logged in")
        return false;
      }
  })
  .catch((error) => console.log("ERROR: " + error))
}

}
