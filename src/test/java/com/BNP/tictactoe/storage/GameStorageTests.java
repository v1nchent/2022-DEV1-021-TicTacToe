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
        final GameStorage gameStorage = new GameStorage();
        final Map<String, Game> games = Map.ofEntries(
                Map.entry("Test", new Game())
        );
        final Field field = gameStorage.getClass().getDeclaredField("games");
        field.setAccessible(true);
        field.set(gameStorage, games);

        //when

        //then
        assertEquals("Field wasn't retrieved properly", gameStorage.getGames(), games);
    }

    @Test
    public void testGameSetter_GetsGame() throws NoSuchFieldException, IllegalAccessException {
        //given
        Game game = new Game();
        game.setGameId("TestIfGameExists");

        //when
        GameStorage.getInstance().setGame(game);

        //then
        assertEquals("Field wasn't set properly", GameStorage.getInstance().getGames.containsKey(game.getGameId()), game);
    }

    @Test
    public void testGetInstance_GetsInstance() throws NoSuchFieldException, IllegalAccessException {
        //given
        GameStorage gameStorage = new GameStorage();
        //when
        //then
        assertEquals("Field wasn't retrieved properly", GameStorage.getInstance(), gameStorage);
    }
}
