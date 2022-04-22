package com.BNP.tictactoe.Services;

import com.BNP.tictactoe.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class GameServiceTests {
    GameService instance;

    @BeforeEach
    void before(){
        this.instance = new GameService();
    }



    @Test
    public void CreateGameTest(){
        Player player = Mockito.mock(Player.class);
        assertEquals(instance.createGame(player));

    }

    @Test
    public void ConnectToGameTest(){

    }

    @Test
    public void GamePlayTest(){

    }

    public Stream<Arguments> TrueBoardProviderP1() {
        return Stream.of(
                Arguments.of(new int[][] {{1,1,1}, {0,0,0}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {1,1,1}, {0,0,0}}),
                Arguments.of(new int[][] {{1,0,0}, {1,0,0}, {1,0,0}}),
                Arguments.of(new int[][] {{0,1,0}, {0,1,0}, {0,1,0}}),
                Arguments.of(new int[][] {{0,0,1}, {0,0,1}, {0,0,1}}),
                Arguments.of(new int[][] {{0,0,0}, {1,1,1}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {0,0,0}, {1,1,1}}),
                Arguments.of(new int[][] {{1,0,0}, {0,1,0}, {0,0,1}}),
                Arguments.of(new int[][] {{0,0,1}, {0,1,0}, {1,0,0}})
        );
    }
    public Stream<Arguments> TrueBoardProviderP2() {
        return Stream.of(
                Arguments.of(new int[][] {{2,2,2}, {0,0,0}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {2,2,2}, {0,0,0}}),
                Arguments.of(new int[][] {{2,0,0}, {2,0,0}, {2,0,0}}),
                Arguments.of(new int[][] {{0,2,0}, {0,2,0}, {0,2,0}}),
                Arguments.of(new int[][] {{0,0,2}, {0,0,2}, {0,0,2}}),
                Arguments.of(new int[][] {{0,0,0}, {2,2,2}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {0,0,0}, {2,2,2}}),
                Arguments.of(new int[][] {{2,0,0}, {0,2,0}, {0,0,2}}),
                Arguments.of(new int[][] {{0,0,2}, {0,2,0}, {2,0,0}})
        );
    }
    public Stream<Arguments> FalseBoardProviderP1() {
        return Stream.of(
                Arguments.of(new int[][] {{1,0,1}, {0,0,0}, {0,0,0}}),
                Arguments.of(new int[][] {{2,2,2}, {0,0,0}, {0,0,0}}),
                Arguments.of(new int[][] {{2,1,2}, {0,0,0}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {1,0,1}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {2,2,2}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {2,1,2}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {0,0,0}, {1,0,1}}),
                Arguments.of(new int[][] {{0,0,0}, {0,0,0}, {2,2,2}}),
                Arguments.of(new int[][] {{0,0,0}, {0,0,0}, {2,1,2}})
        );
    }
    public Stream<Arguments> FalseBoardProviderP2() {
        return Stream.of(
                Arguments.of(new int[][] {{1,1,1}, {0,0,0}, {0,0,0}}),
                Arguments.of(new int[][] {{1,0,1}, {0,0,0}, {0,0,0}}),
                Arguments.of(new int[][] {{2,1,2}, {0,0,0}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {1,1,1}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {1,0,1}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {2,1,2}, {0,0,0}}),
                Arguments.of(new int[][] {{0,0,0}, {0,0,0}, {1,1,1}}),
                Arguments.of(new int[][] {{0,0,0}, {0,0,0}, {1,0,1}}),
                Arguments.of(new int[][] {{0,0,0}, {0,0,0}, {2,1,2}})
        );
    }

    @ParameterizedTest
    @MethodSource("TrueBoardProviderP1")
    public void checkWinner_multipleTrueBoardsP1(int[][] board) {
        assertEquals("All boards should let player X win", true, instance.checkWinner(board, TicTacToe.X));
    }
    @ParameterizedTest
    @MethodSource("TrueBoardProviderP2")
    public void checkWinner_multipleTrueBoardsP2(int[][] board) {
        assertEquals("All boards should let player 0 win", true, instance.checkWinner(board, TicTacToe.O));
    }

    @ParameterizedTest
    @MethodSource("FalseBoardProviderP1")
    public void checkWinner_multipleFalseBoardsP1(int[][] board) {
        assertEquals("No boards should let player X win", false, instance.checkWinner(board, TicTacToe.X));
    }

    @ParameterizedTest
    @MethodSource("FalseBoardProviderP2")
    public void checkWinner_multipleFalseBoardsP2(int[][] board) {
        assertEquals("No boards should let player O win", false, instance.checkWinner(board, TicTacToe.O));
    }

}
