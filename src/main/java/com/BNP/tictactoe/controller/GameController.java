package com.BNP.tictactoe.controller;

import com.BNP.tictactoe.exception.InvalidGameException;
import com.BNP.tictactoe.exception.NotFoundException;
import com.BNP.tictactoe.models.Game;
import com.BNP.tictactoe.models.GamePlay;
import com.BNP.tictactoe.models.Player;
import com.BNP.tictactoe.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player1, Player player2) {
        log.info("start game request: {} {}", player1, player2);
        return ResponseEntity.ok(gameService.createGame(player1, player2));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws NotFoundException, InvalidGameException {
        log.info("gameplay: {}", request);
        Game game = gameService.gamePlay(request);
        return ResponseEntity.ok(game);
    }
}