package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.Player;

import java.util.ArrayList;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private Game game;

    @RequestMapping(method=RequestMethod.GET, value="/")
    public ArrayList<Player> allPlayers(){
        return game.getPlayers();
    }

    @RequestMapping(method= RequestMethod.POST, value="/")
    public ArrayList<Player> addPlayer(@RequestParam(value="name") String name){
        game.addPlayer(name);
        return allPlayers();
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/")
    public ArrayList<Player> deletePlayer(@RequestParam(value="id") int id){
        game.removePlayer(id);
        return allPlayers();
    }

    @RequestMapping(method=RequestMethod.GET, value="/show")
    public Player player(@RequestParam(value="id") int id){
        return game.getPlayer(id);
    }
}
