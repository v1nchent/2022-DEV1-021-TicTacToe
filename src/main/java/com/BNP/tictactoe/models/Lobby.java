package com.BNP.tictactoe.models;

import lombok.Data;

@Data
public class Lobby {
    private Player player1;
    private Player player2;
    private Game game;
}
