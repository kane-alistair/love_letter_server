package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    @Autowired
    private Game game;

    @SendTo("/topic/messages")
    @MessageMapping(value="/chat")
    public String greeting(String message) throws Exception {
        return message;
    }

    @SendTo("/topic/game")
    @MessageMapping(value = "/game-state")
    public Game getGame(){ return game; }

    @SendTo("/topic/game")
    @MessageMapping(value = "/new-round")
    public Game prepRound(){
        game.prepNewRound();
        return game;
    }

    @SendTo("/topic/game")
    @MessageMapping(value = "/take-turn")
    public Game takeTurn(Turn turn) {
        int id = turn.getId();
        int card = turn.getCard();
        int guess = turn.getGuess();
        int selected = turn.getSelected();

        game.playerTakeTurn(id, card, guess, selected);
        return game;
    }
}
