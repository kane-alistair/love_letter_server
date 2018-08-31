package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Player;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController(value="PlayerController")
@RequestMapping("/game/players")
public class PlayerController {
    @Autowired
    private Game game;

    @RequestMapping(method=RequestMethod.GET, value="/")
    public List<Player> allPlayers(){
        return game.getPlayers();
    }

    @RequestMapping(method= RequestMethod.POST, value="/")
    public List<Player> addPlayer(@RequestBody Player newPlayer){
        game.add(newPlayer);
        return game.getPlayers();
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/")
    public List<Player> deletePlayer(@RequestParam(value="id") int id){
        game.removePlayer(id);
        return allPlayers();
    }

    @RequestMapping(method=RequestMethod.GET, value="/show")
    public Player showPlayer(@RequestParam(value="id") int id){
        return game.getPlayer(id);
    }
}
