package com.BNP.tictactoe.storage;

import com.BNP.tictactoe.models.Game;
import com.BNP.tictactoe.models.Player;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class GameStorageTests {

    @Test
    public void testGamesGetter_GetsGames() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Game singleGame = new Game();
        singleGame.setGameId("test");
        final Map<String, Game> games = Map.ofEntries(
                Map.entry("test", singleGame)
        );
        //when
        GameStorage.getInstance().setGame(singleGame);

        //then
        assertEquals("Field wasn't retrieved properly", GameStorage.getInstance().getGames(), games);
    }

    @Test
    public void testGameSetter_GetsGame() throws NoSuchFieldException, IllegalAccessException {
        //given
        Game game = new Game();
        game.setGameId("TestIfGameExists");

        //when
        GameStorage.getInstance().setGame(game);

        //then
        assertEquals("Field wasn't set properly", GameStorage.getInstance().getGames().get("TestIfGameExists"), game);
    }
}
