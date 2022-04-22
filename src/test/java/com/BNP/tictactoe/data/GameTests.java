package com.BNP.tictactoe.data;

import com.BNP.tictactoe.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class GameTests {


    private Player player = Mockito.mock(Player.class);
    private Game game;


//    private int[][] board;

    @BeforeEach
    void init() {
        game = new Game();
    }

    //I don't believe there is a need for an @AfterEach

    @Test
    public void testPlayer1Setter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given

        //when
        game.setPlayer1(player);

        //then
        final Field field = game.getClass().getDeclaredField("player1");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(game), player);
    }

    @Test
    public void testPlayer1Getter_GetsPlayer1() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Field field = game.getClass().getDeclaredField("player1");
        field.setAccessible(true);
        field.set(game, player);

        //when
        final Player result = game.getPlayer1();

        //then
        assertEquals("Field wasn't retrieved properly", result, player);
    }
    @Test
    public void testPlayer2Setter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given

        //when
        game.setPlayer2(player);

        //then
        final Field field = game.getClass().getDeclaredField("player2");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(game), player);
    }

    @Test
    public void testPlayer2Getter_GetsPlayer2() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Field field = game.getClass().getDeclaredField("player2");
        field.setAccessible(true);
        field.set(game, player);

        //when
        final Player result = game.getPlayer2();

        //then
        assertEquals("Field wasn't retrieved properly", result, player);
    }


    @Test
    public void testGameIdSetter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given

        //when
        game.setGameId("d67e99a5-4880-4717-8d6a-74990fab7b87");

        //then
        final Field field = game.getClass().getDeclaredField("gameId");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(game), "d67e99a5-4880-4717-8d6a-74990fab7b87");
    }

    @Test
    public void testGameIdGetter_GetsGameId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Field field = game.getClass().getDeclaredField("gameId");
        field.setAccessible(true);
        field.set(game, "d67e99a5-4880-4717-8d6a-74990fab7b87");

        //when
        final String result = game.getGameId();

        //then
        assertEquals("Field wasn't retrieved properly", result, "d67e99a5-4880-4717-8d6a-74990fab7b87");
    }
    @Test
    public void testGameStateSetter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        GameState gameState = GameState.NEW;

        //when
        game.setState(gameState);

        //then
        final Field field = game.getClass().getDeclaredField("state");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(game), gameState);
    }

    @Test
    public void testGameStateGetter_GetsGameState() throws NoSuchFieldException, IllegalAccessException {
        //given
        GameState gameState = GameState.FINISHED;
        final Field field = game.getClass().getDeclaredField("state");
        field.setAccessible(true);
        field.set(game, gameState);

        //when
        final GameState result = game.getState();

        //then
        assertEquals("Field wasn't retrieved properly", result, gameState);
    }
    @Test
    public void testWinnerSetter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        TicTacToe winner = TicTacToe.O;

        //when
        game.setWinner(winner);

        //then
        final Field field = game.getClass().getDeclaredField("winner");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(game), winner);
    }

    @Test
    public void testWinnerGetter_GetsWinner() throws NoSuchFieldException, IllegalAccessException {
        //given
        TicTacToe winner = TicTacToe.O;
        final Field field = game.getClass().getDeclaredField("winner");
        field.setAccessible(true);
        field.set(game, winner);

        //when
        final TicTacToe result = game.getWinner();

        //then
        assertEquals("Field wasn't retrieved properly", result, winner);
    }
    @Test
    public void testBoardSetter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        int[][] board = new int[3][3];

        //when
        game.setBoard(board);

        //then
        final Field field = game.getClass().getDeclaredField("board");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(game), board);
    }

    @Test
    public void testBoardGetter_GetsBoard() throws NoSuchFieldException, IllegalAccessException {
        //given
        int[][] board = new int[3][3];
        final Field field = game.getClass().getDeclaredField("board");
        field.setAccessible(true);
        field.set(game, board);

        //when
        final int[][] result = game.getBoard();

        //then
        assertEquals("Field wasn't retrieved properly", result, board);
    }
}
