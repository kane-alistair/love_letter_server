package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController(value = "GameController")
@RequestMapping("/game")
public class GameController {

    @Autowired
    private Game game;

    @RequestMapping("/winner")
    public List<Player> winner(){ return game.currentWinner(); }

    @RequestMapping("/cards-remaining")
    public int cardsRemaining(){ return game.cardsRemaining(); }

    @RequestMapping("/deck")
    public Deck showDeck(){ return game.getDeck(); }

    @RequestMapping("/scoreboard")
    public Map<Player, Integer> scoreBoard(){ return game.getWins(); }

    @RequestMapping(value = "/new-round", method = RequestMethod.POST)
    public Deck prepRound(){
        game.prepNewRound();
        return game.getDeck();
    }

    @RequestMapping(value = "/turn", method = RequestMethod.POST)
    public Player takeTurn(@RequestParam(value = "id") int id,
                         @RequestParam(value = "card") int card,
                         @RequestParam(value = "guess") int guess,
                         @RequestParam(value = "selected") int selected) {
        game.playerTakeTurn(id, card, guess, selected);
        return game.getPlayer(id);
    }
}
