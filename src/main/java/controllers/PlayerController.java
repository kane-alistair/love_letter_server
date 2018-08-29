package controllers;

import components.Player;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PlayerController {
    private ArrayList<Player> players = new ArrayList<>();

    @RequestMapping("/players")
    public ArrayList<Player> allPlayers(){
        return players;
    }

    @RequestMapping(method=RequestMethod.POST, value="/players")
    public ArrayList<Player> addPlayer(@RequestParam(value="name", defaultValue = "default") String name){
        this.players.add(new Player(name));
        return allPlayers();
    }
}
