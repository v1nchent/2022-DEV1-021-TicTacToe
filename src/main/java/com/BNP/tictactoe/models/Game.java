package com.BNP.tictactoe.models;

import lombok.Data;

@Data
public class Game {

    private String gameId;
    private Player player1;
    private Player player2;
    private GameState state;
    private int[][] board;
    private TicTacToe winner;

}
