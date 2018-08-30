package server;

import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@ComponentScan("com.kane.loveletter")
public class ApplicationConfig {
    private Deck deck = new Deck();

    @Bean
    @Scope(
            value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Game game(){
        return new Game(deck);
    }
}
