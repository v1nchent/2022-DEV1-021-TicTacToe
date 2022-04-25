package com.BNP.tictactoe.Services;

import com.BNP.tictactoe.exception.InvalidGameException;
import com.BNP.tictactoe.exception.NotFoundException;
import com.BNP.tictactoe.models.*;
import com.BNP.tictactoe.service.GameService;
import com.BNP.tictactoe.storage.GameStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class GameServiceTests {
    GameService instance;

    @BeforeEach
    void before(){
        this.instance = new GameService();
    }



    @Test
    public void CreateGameTest(){
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);
        instance.createGame(player1, player2);
        assertFalse(GameStorage.getInstance().getGames().isEmpty());
    }


    public Stream<Arguments> GamePlayProviderNormalMove() {
        Game testGame =  new Game();
        testGame.setBoard(new int[3][3]);
        testGame.setGameId("testingNormalMoves");
        testGame.setState(GameState.NEW);
        GameStorage.getInstance().setGame(testGame);
        GamePlay gp1 = new GamePlay();
        gp1.setGameId("testingNormalMoves");
        gp1.setType(TicTacToe.X);
        gp1.setCoordinateX(0);
        gp1.setCoordinateY(1);
        GamePlay gp2 = new GamePlay();
        gp2.setGameId("testingNormalMoves");
        gp2.setType(TicTacToe.O);
        gp2.setCoordinateX(2);
        gp2.setCoordinateY(2);
        return Stream.of(
                Arguments.of(gp1),
                Arguments.of(gp2)
        );
    }

    public Stream<Arguments> GamePlayProviderWinningMove() {
        Game testGame1 =  new Game();
        testGame1.setBoard(new int[][] {{1,1,0}, {0,0,0}, {0,0,0}});
        testGame1.setGameId("testingWinningXMove");
        testGame1.setState(GameState.NEW);
        GameStorage.getInstance().setGame(testGame1);
        Game testGame2 =  new Game();
        testGame2.setBoard(new int[][] {{2,2,0}, {0,0,0}, {0,0,0}});
        testGame2.setGameId("testingWinningOMove");
        testGame2.setState(GameState.NEW);
        GameStorage.getInstance().setGame(testGame2);
        GamePlay gp1 = new GamePlay();
        gp1.setGameId("testingWinningXMove");
        gp1.setType(TicTacToe.X);
        gp1.setCoordinateX(0);
        gp1.setCoordinateY(2);
        GamePlay gp2 = new GamePlay();
        gp2.setGameId("testingWinningOMove");
        gp2.setType(TicTacToe.O);
        gp2.setCoordinateX(0);
        gp2.setCoordinateY(2);
        return Stream.of(
                Arguments.of(gp1),
                Arguments.of(gp2)
        );
    }

    @ParameterizedTest
    @MethodSource("GamePlayProviderNormalMove")
    public void GamePlayNormalMoveTest(GamePlay gamePlay) throws InvalidGameException, NotFoundException {
        Game game = instance.gamePlay(gamePlay);
        int[][] board = game.getBoard();
        System.out.println(game);
        System.out.println(board);
        assertEquals(board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()], gamePlay.getType().getValue());
    }

    @ParameterizedTest
    @MethodSource("GamePlayProviderWinningMove")
    public void GamePlayWinningMoveTest(GamePlay gamePlay) throws InvalidGameException, NotFoundException {

        Game game = instance.gamePlay(gamePlay);
        System.out.println(game);
        assertSame(game.getWinner(), gamePlay.getType());
    }

    public Stream<Arguments> TrueBoardProviderP1() {
        return Stream.of(
                Arguments.of((Object) new int[][] {{1,1,1}, {0,0,0}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {1,1,1}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{1,0,0}, {1,0,0}, {1,0,0}}),
                Arguments.of((Object) new int[][] {{0,1,0}, {0,1,0}, {0,1,0}}),
                Arguments.of((Object) new int[][] {{0,0,1}, {0,0,1}, {0,0,1}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {1,1,1}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {0,0,0}, {1,1,1}}),
                Arguments.of((Object) new int[][] {{1,0,0}, {0,1,0}, {0,0,1}}),
                Arguments.of((Object) new int[][] {{0,0,1}, {0,1,0}, {1,0,0}})
        );
    }
    public Stream<Arguments> TrueBoardProviderP2() {
        return Stream.of(
                Arguments.of((Object) new int[][] {{2,2,2}, {0,0,0}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {2,2,2}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{2,0,0}, {2,0,0}, {2,0,0}}),
                Arguments.of((Object) new int[][] {{0,2,0}, {0,2,0}, {0,2,0}}),
                Arguments.of((Object) new int[][] {{0,0,2}, {0,0,2}, {0,0,2}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {2,2,2}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {0,0,0}, {2,2,2}}),
                Arguments.of((Object) new int[][] {{2,0,0}, {0,2,0}, {0,0,2}}),
                Arguments.of((Object) new int[][] {{0,0,2}, {0,2,0}, {2,0,0}})
        );
    }
    public Stream<Arguments> FalseBoardProviderP1() {
        return Stream.of(
                Arguments.of((Object) new int[][] {{1,0,1}, {0,0,0}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{2,2,2}, {0,0,0}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{2,1,2}, {0,0,0}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {1,0,1}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {2,2,2}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {2,1,2}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {0,0,0}, {1,0,1}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {0,0,0}, {2,2,2}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {0,0,0}, {2,1,2}})
        );
    }
    public Stream<Arguments> FalseBoardProviderP2() {

        return Stream.of(
                Arguments.of((Object) new int[][] {{1,1,1}, {0,0,0}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{1,0,1}, {0,0,0}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{2,1,2}, {0,0,0}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {1,1,1}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {1,0,1}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {2,1,2}, {0,0,0}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {0,0,0}, {1,1,1}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {0,0,0}, {1,0,1}}),
                Arguments.of((Object) new int[][] {{0,0,0}, {0,0,0}, {2,1,2}})
        );
    }

    @ParameterizedTest
    @MethodSource("TrueBoardProviderP1")
    public void checkWinner_multipleTrueBoardsP1(int[][] board) {
        assertTrue(GameService.checkWinner(board, TicTacToe.X));
    }

    @ParameterizedTest
    @MethodSource("TrueBoardProviderP2")
    public void checkWinner_multipleTrueBoardsP2(int[][] board) {
        assertTrue(GameService.checkWinner(board, TicTacToe.O));
    }

    @ParameterizedTest
    @MethodSource("FalseBoardProviderP1")
    public void checkWinner_multipleFalseBoardsP1(int[][] board) {
        assertFalse(GameService.checkWinner(board, TicTacToe.X));
    }

    @ParameterizedTest
    @MethodSource("FalseBoardProviderP2")
    public void checkWinner_multipleFalseBoardsP2(int[][] board) {
        assertFalse(GameService.checkWinner(board, TicTacToe.O));
    }

}
