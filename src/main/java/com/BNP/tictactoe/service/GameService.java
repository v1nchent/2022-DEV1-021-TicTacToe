package com.BNP.tictactoe.service;

import com.BNP.tictactoe.exception.InvalidGameException;
import com.BNP.tictactoe.exception.NotFoundException;
import com.BNP.tictactoe.models.Game;
import com.BNP.tictactoe.models.GamePlay;
import com.BNP.tictactoe.models.Player;
import com.BNP.tictactoe.models.TicTacToe;
import com.BNP.tictactoe.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.BNP.tictactoe.models.GameState.*;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player1, Player player2) {
        Game game = new Game();
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setState(NEW);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game gamePlay(GamePlay gamePlay) throws NotFoundException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())) {
            throw new NotFoundException("Game not found");
        }

        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
        if (game.getState().equals(FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }

        int[][] board = game.getBoard();
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()] = gamePlay.getType().getValue();

        Boolean xWinner = checkWinner(game.getBoard(), TicTacToe.X);
        Boolean oWinner = checkWinner(game.getBoard(), TicTacToe.O);

        if (xWinner) {
            game.setWinner(TicTacToe.X);
        } else if (oWinner) {
            game.setWinner(TicTacToe.O);
        }

        GameStorage.getInstance().setGame(game);
        return game;
    }

    public static Boolean checkWinner(int[][] board, TicTacToe ticTacToe) {
        int[] boardArray = new int[9];
        int counterIndex = 0;
        for (int[] ints : board) {
            for (int anInt : ints) {
                boardArray[counterIndex] = anInt;
                counterIndex++;
            }
        }

        int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int[] winCombination : winCombinations) {
            int counter = 0;
            for (int i : winCombination) {
                if (boardArray[i] == ticTacToe.getValue()) {
                    counter++;
                    if (counter == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
