package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
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
}
