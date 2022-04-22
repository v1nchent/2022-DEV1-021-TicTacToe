package com.BNP.tictactoe.Controllers;

import com.BNP.tictactoe.controller.GameController;
import com.BNP.tictactoe.models.Game;
import com.BNP.tictactoe.models.GamePlay;
import com.BNP.tictactoe.models.Player;
import com.BNP.tictactoe.models.TicTacToe;
import com.BNP.tictactoe.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class GameControllerTests {

    @MockBean
    private GameService gameService;
    @Autowired
    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Test
    void StartGameTest() throws Exception {
        Player p1 = new Player();
        Player p2 = new Player();
        Game game = new Game();
        game.setGameId("testingCreateGame");
        when(gameService.createGame(p1, p2)).thenReturn(game);

        mockMvc.perform(MockMvcRequestBuilders.post("game/start"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId").value("testingCreateGame"))
                .andExpect(jsonPath("$.state").value("NEW"))
                .andDo(print());
    }


    @Test
    void GamePlayTest() throws Exception {
        Game game = new Game();
        game.setGameId("testingGameplay");
        GamePlay gp = new GamePlay();

        when(gameService.gamePlay(gp)).thenReturn(game);

        mockMvc.perform(MockMvcRequestBuilders.post("game/gameplay"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId").value("testingGameplay"))
                .andDo(print());
    }
}
