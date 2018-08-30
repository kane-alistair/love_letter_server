package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.Player;

import java.util.ArrayList;

@RestController
public class PlayerController {
    @Autowired
    private Game game;

    @RequestMapping("/players")
    public ArrayList<Player> allPlayers(){
        return game.getPlayers();
    }

    @RequestMapping(method= RequestMethod.POST, value="/players")
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
