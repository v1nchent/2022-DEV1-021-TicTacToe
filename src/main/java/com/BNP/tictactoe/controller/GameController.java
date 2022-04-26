package com.BNP.tictactoe.controller;

import com.BNP.tictactoe.exception.InvalidGameException;
import com.BNP.tictactoe.exception.NotFoundException;
import com.BNP.tictactoe.models.Game;
import com.BNP.tictactoe.models.GamePlay;
import com.BNP.tictactoe.models.Player;
import com.BNP.tictactoe.models.PlayerHolder;
import com.BNP.tictactoe.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody PlayerHolder ph) {
        log.info("start game request: {} {}", ph.getP1(), ph.getP2());
        return ResponseEntity.ok(gameService.createGame(ph.getP1(), ph.getP2()));
    }
    @PostMapping("/gameplay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws NotFoundException, InvalidGameException {
        log.info("gameplay: {}", request);
        Game game = gameService.gamePlay(request);
        return ResponseEntity.ok(game);
    }
}