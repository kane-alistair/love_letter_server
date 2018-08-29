package controllers;

import components.Player;
import components.PlayerBasics;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @RequestMapping("/player")
    public Player greeting(){
        return new Player("Bob");
    }
}
