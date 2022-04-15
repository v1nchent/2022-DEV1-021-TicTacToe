package com.BNP.tictactoe.data;

import com.BNP.tictactoe.models.Player;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class LobbyTests {

    private Player player = Mockito.mock(Player.class);
    private Game game = Mockito.mock(Game.class);


    @Test
    public void testPlayer1Setter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Lobby lobby = new Lobby();

        //when
        lobby.setPlayer1(player);

        //then
        final Field field = lobby.getClass().getDeclaredField("player1");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(lobby), player);
    }

    @Test
    public void testPlayer1Getter_GetsPlayer1() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Lobby lobby = new Lobby();
        final Field field = lobby.getClass().getDeclaredField("player1");
        field.setAccessible(true);
        field.set(lobby, player);

        //when
        final Player result = lobby.getPlayer1();

        //then
        assertEquals("Field wasn't retrieved properly", result, player);
    }

    @Test
    public void testPlayer2Setter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Lobby lobby = new Lobby();

        //when
        lobby.setPlayer2(player);

        //then
        final Field field = lobby.getClass().getDeclaredField("player2");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(lobby), player);
    }

    @Test
    public void testPlayer2Getter_GetsPlayer2() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Lobby lobby = new Lobby();
        final Field field = lobby.getClass().getDeclaredField("player2");
        field.setAccessible(true);
        field.set(lobby, player);

        //when
        final Player result = lobby.getPlayer2();

        //then
        assertEquals("Field wasn't retrieved properly", result, player);
    }

    @Test
    public void testGameSetter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Lobby lobby = new Lobby();

        //when
        lobby.setGame(game);

        //then
        final Field field = lobby.getClass().getDeclaredField("game");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(lobby), game);
    }

    @Test
    public void testGameGetter_GetsGame() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Lobby lobby = new Lobby();
        final Field field = lobby.getClass().getDeclaredField("game");
        field.setAccessible(true);
        field.set(lobby, game);

        //when
        final Game result = lobby.getGame();

        //then
        assertEquals("Field wasn't retrieved properly", result, game);
    }

}
