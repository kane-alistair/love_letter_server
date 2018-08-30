package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Game;

import java.util.ArrayList;

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

    @RequestMapping("/new-round")
    public void prepRound(){
        game.prepNewRound();
    }

}
