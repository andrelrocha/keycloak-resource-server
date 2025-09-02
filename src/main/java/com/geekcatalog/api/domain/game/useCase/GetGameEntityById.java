package com.geekcatalog.api.domain.game.useCase;

import com.geekcatalog.api.domain.game.Game;
import com.geekcatalog.api.domain.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetGameEntityById {
    @Autowired
    private GameRepository repository;

    public Game getGameById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Game entity was found from the ID."));
    }
}
