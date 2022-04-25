package com.BNP.tictactoe.Controllers;

import com.BNP.tictactoe.controller.GameController;
import static org.assertj.core.api.Assertions.assertThat;

import com.BNP.tictactoe.models.*;
import com.BNP.tictactoe.service.GameService;
import com.BNP.tictactoe.storage.GameStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;


@SpringBootTest
public class GameControllerTests {
    GameService gameService;
    GameController gameController;

    @BeforeEach
    void Before(){
        gameService = new GameService();
        gameController = new GameController(gameService);
    }

    @Test
    void StartGameTest() throws Exception {
        Player p1 = new Player();
        p1.setUsername("test1");
        Player p2 = new Player();
        p2.setUsername("test2");
        Game game = new Game();
        game.setGameId("testingCreateGame");

        ResponseEntity<Game> responseEntity = gameController.start(p1,p2);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        System.out.println(Objects.requireNonNull(responseEntity.getBody()).getGameId());
        Assertions.assertFalse(GameStorage.getInstance().getGames().isEmpty());
        assertEquals(GameStorage.getInstance().getGames().get(responseEntity.getBody().getGameId()).getPlayer1(), p1);
        assertEquals(GameStorage.getInstance().getGames().get(responseEntity.getBody().getGameId()).getPlayer2(), p2);
        assertArrayEquals(GameStorage.getInstance().getGames().get(responseEntity.getBody().getGameId()).getBoard(), new int[3][3]);
        assertEquals(GameStorage.getInstance().getGames().get(responseEntity.getBody().getGameId()).getState(), GameState.NEW);

    }


    @Test
    void GamePlayTest() throws Exception {
        Game game = new Game();
        game.setBoard(new int[][] {{0,1,0}, {0,0,0}, {0,0,0}});
        game.setState(GameState.NEW);
        game.setGameId("testingGameplay");
        GameStorage.getInstance().setGame(game);

        GamePlay gp = new GamePlay();
        gp.setGameId("testingGameplay");
        gp.setType(TicTacToe.X);
        gp.setCoordinateX(0);
        gp.setCoordinateY(2);


        ResponseEntity<Game> responseEntity = gameController.gamePlay(gp);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertArrayEquals(GameStorage.getInstance().getGames().get(responseEntity.getBody().getGameId()).getBoard(), new int[][] {{0,1,1}, {0,0,0}, {0,0,0}});
    }
}
