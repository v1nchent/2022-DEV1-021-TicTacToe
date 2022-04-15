package com.BNP.tictactoe.data;

import com.BNP.tictactoe.models.Player;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Field;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class PlayerTests {

    @Test
    public void testUserNameSetter_SetsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Player player = new Player();

        //when
        player.setUsername("Player 1");

        //then
        final Field field = player.getClass().getDeclaredField("username");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(player), "Player 1");
    }

    @Test
    public void testUserNameGetter_GetsUsername() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Player player = new Player();
        final Field field = player.getClass().getDeclaredField("username");
        field.setAccessible(true);
        field.set(player, "Player 1");

        //when
        final String result = player.getUsername();

        //then
        assertEquals("Fields wasn't retrieved properly", result, "Player 1");
    }

}
