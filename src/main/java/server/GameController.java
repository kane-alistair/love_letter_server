package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.Game;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private Game game;

    @RequestMapping("/winner")
    public ArrayList<Player> winner(){
        return game.currentWinner();
    }

    @RequestMapping("/cards-remaining")
    public int cardsRemaining(){
        return game.cardsRemaining();
    }

    @RequestMapping("/deck")
    public Deck showDeck(){
        return game.getDeck();
    }

    @RequestMapping("/scoreboard")
    public HashMap<Player, Integer> scoreBoard(){
        return game.getWins();
    }

    @RequestMapping(value = "/new-round", method = RequestMethod.POST)
    public void prepRound(){
        game.prepNewRound();
    }
}
