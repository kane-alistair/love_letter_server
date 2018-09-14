package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController(value = "GameController")
@RequestMapping("/game")
public class GameController {

    @Autowired
    private Game game;

    @RequestMapping(value = "/state")
    public Game showGame(){ return game; }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<Player> gamePlayers(){ return game.getPlayers();}

    @RequestMapping(value = "/winner", method = RequestMethod.GET)
    public List<Player> winner(){ return game.currentWinner(); }

    @RequestMapping(value = "/cards-remaining", method = RequestMethod.GET)
    public int cardsRemaining(){ return game.cardsRemaining(); }

    @RequestMapping(value = "/deck", method = RequestMethod.GET)
    public Deck showDeck(){ return game.getDeck(); }

    @RequestMapping(value = "/scoreboard", method = RequestMethod.GET)
    public Map<Player, Integer> scoreBoard(){ return game.getWins(); }

    @RequestMapping(value = "/deal-in", method = RequestMethod.POST)
    public void dealIn(@RequestBody Player player){
        game.dealCard(player);
    }

    @RequestMapping(value = "/turn", method = RequestMethod.POST)
    public Game takeTurn(@RequestParam(value = "id") int id,
                         @RequestParam(value = "card") int card,
                         @RequestParam(value = "guess") int guess,
                         @RequestParam(value = "selected") int selected) {
        game.playerTakeTurn(id, card, guess, selected);
        return game;
    }
}
