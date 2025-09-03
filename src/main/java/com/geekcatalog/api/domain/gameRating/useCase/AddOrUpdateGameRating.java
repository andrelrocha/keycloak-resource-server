package com.geekcatalog.api.domain.gameRating.useCase;

import com.geekcatalog.api.domain.gameRating.GameRating;
import com.geekcatalog.api.domain.gameRating.GameRatingRepository;
import com.geekcatalog.api.domain.gameRating.validator.GameRatingValidator;
import com.geekcatalog.api.dto.gameRating.GameRatingDTO;
import com.geekcatalog.api.dto.gameRating.GameRatingResultDTO;
import com.geekcatalog.api.dto.gameRating.GameRatingReturnDTO;
import com.geekcatalog.api.service.EntityHandlerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddOrUpdateGameRating {
    private final GameRatingRepository repository;
    private final GameRatingValidator validator;
    private final EntityHandlerService entityHandlerService;

    @Transactional
    public GameRatingResultDTO addOrUpdateRating(GameRatingDTO data) {
        validator.validateRatingValue(data.rating());

        var user = entityHandlerService.getUserById(data.userId());
        var game = entityHandlerService.getGameById(data.gameId());

        var existingRating = repository.findByUserIdAndGameId(user.getId(), game.getId());

        GameRating gameRating;
        boolean isNew;
        if (existingRating != null) {
            existingRating.updateRating(data);
            gameRating = existingRating;
            isNew = false;
        } else {
            gameRating = new GameRating(data, user, game);
            isNew = true;
        }

        var saved = repository.save(gameRating);
        return new GameRatingResultDTO(new GameRatingReturnDTO(saved), isNew);
    }
}
