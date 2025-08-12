package com.geekcatalog.api.domain.game.validator;

import com.geekcatalog.api.domain.game.GameRepository;
import com.geekcatalog.api.service.UtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameValidator {

    private final GameRepository repository;
    private final UtilsService utilsService;

    public void validateNewGame(String gameName) {
        String normalizedName = utilsService.normalizeString(gameName);
        boolean exists = repository.existsByNormalizedName(normalizedName);
        if (exists) {
            throw new IllegalArgumentException("Game with this normalized name already exists.");
        }
    }
}
