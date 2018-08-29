package server.player;

import server.game.Deck;
import server.game.Game;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PlayerController {
    private Game game = new Game(new Deck());

    @RequestMapping("/players")
    public ArrayList<Player> allPlayers(){
        return game.getPlayers();
    }

    @RequestMapping(method=RequestMethod.POST, value="/players")
    public ArrayList<Player> addPlayer(@RequestParam(value="name", defaultValue = "default") String name){
        game.addPlayer(name);
        return allPlayers();
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/players")
    public ArrayList<Player> deletePlayer(@RequestParam(value="id", defaultValue = "default") int id){
        game.removePlayer(id);
        return allPlayers();
    }


}
